/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  android.annotation.TargetApi
 *  android.media.MediaCodec
 *  android.media.MediaCodec$BufferInfo
 *  android.media.MediaCrypto
 *  android.media.MediaFormat
 *  android.view.Surface
 */
package org.linphone.mediastream;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.view.Surface;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import org.linphone.mediastream.Log;

@TargetApi(value=16)
public class AACFilter {
    int sampleRate;
    int channelCount;
    int bitrate;
    MediaCodec encoder;
    MediaCodec.BufferInfo encoderBufferInfo;
    ByteBuffer[] encoderOutputBuffers;
    ByteBuffer[] encoderInputBuffers;
    MediaCodec decoder;
    MediaCodec.BufferInfo decoderBufferInfo;
    ByteBuffer[] decoderOutputBuffers;
    ByteBuffer[] decoderInputBuffers;
    boolean initialized = false;
    private static AACFilter singleton;

    public static AACFilter instance() {
        if (singleton == null) {
            singleton = new AACFilter();
        }
        return singleton;
    }

    public boolean preprocess(int sampleRate, int channelCount, int bitrate, boolean sbr_enabled) {
        MediaFormat mediaFormat;
        if (this.initialized) {
            return true;
        }
        this.sampleRate = sampleRate;
        this.channelCount = channelCount;
        this.bitrate = bitrate;
        byte[] asc = null;
        try {
            mediaFormat = MediaFormat.createAudioFormat((String)"audio/mp4a-latm", (int)sampleRate, (int)channelCount);
            mediaFormat.setInteger("aac-profile", 39);
            mediaFormat.setInteger("bitrate", bitrate);
            this.encoder = MediaCodec.createByCodecName((String)"OMX.google.aac.encoder");
            this.encoder.configure(mediaFormat, null, null, 1);
            this.encoder.start();
            this.encoderBufferInfo = new MediaCodec.BufferInfo();
            for (int ascPollCount = 0; asc == null && ascPollCount < 1000; ++ascPollCount) {
                int encInBufIdx = -1;
                encInBufIdx = this.encoder.dequeueOutputBuffer(this.encoderBufferInfo, 0);
                if (encInBufIdx < 0 || this.encoderBufferInfo.flags != 2) continue;
                asc = new byte[this.encoderBufferInfo.size];
                this.encoder.getOutputBuffers()[encInBufIdx].get(asc, 0, this.encoderBufferInfo.size);
                this.encoder.getOutputBuffers()[encInBufIdx].position(0);
                this.encoder.releaseOutputBuffer(encInBufIdx, false);
            }
            this.encoderOutputBuffers = this.encoder.getOutputBuffers();
            this.encoderInputBuffers = this.encoder.getInputBuffers();
            if (asc == null) {
                Log.e("Sigh, failed to read asc from encoder");
            }
        }
        catch (Exception exc) {
            Log.e(exc, "Unable to create AAC Encoder");
            return false;
        }
        Log.i("AAC encoder initialized");
        try {
            mediaFormat = null;
            if (asc != null) {
                mediaFormat = MediaFormat.createAudioFormat((String)"audio/mp4a-latm", (int)0, (int)0);
                ByteBuffer ascBuf = ByteBuffer.wrap(asc);
                mediaFormat.setByteBuffer("csd-0", ascBuf);
            } else {
                mediaFormat = MediaFormat.createAudioFormat((String)"audio/mp4a-latm", (int)sampleRate, (int)channelCount);
                mediaFormat.setInteger("bitrate", bitrate);
            }
            this.decoder = MediaCodec.createByCodecName((String)"OMX.google.aac.decoder");
            this.decoder.configure(mediaFormat, null, null, 0);
            this.decoder.start();
            this.decoderOutputBuffers = this.decoder.getOutputBuffers();
            this.decoderInputBuffers = this.decoder.getInputBuffers();
            this.decoderBufferInfo = new MediaCodec.BufferInfo();
        }
        catch (Exception exc) {
            Log.e(exc, "Unable to create AAC Decoder");
            return false;
        }
        Log.i("AAC decoder initialized");
        this.initialized = true;
        return true;
    }

    public boolean pushToDecoder(byte[] data, int size) {
        try {
            if (data != null && this.decoder != null) {
                return AACFilter.queueData(this.decoder, this.decoderInputBuffers, data, size);
            }
            return false;
        }
        catch (Exception e) {
            Log.e(e, "Push to decoder failed");
            return false;
        }
    }

    public int pullFromDecoder(byte[] b) {
        try {
            int result = AACFilter.dequeueData(this.decoder, this.decoderOutputBuffers, this.decoderBufferInfo, b);
            if (result == -3) {
                this.decoderOutputBuffers = this.decoder.getOutputBuffers();
                return this.pullFromDecoder(b);
            }
            return result;
        }
        catch (Exception e) {
            return 0;
        }
    }

    public boolean pushToEncoder(byte[] data, int size) {
        try {
            if (data != null && this.encoder != null) {
                return AACFilter.queueData(this.encoder, this.encoderInputBuffers, data, size);
            }
            return false;
        }
        catch (Exception e) {
            Log.e(e, "Push to encoder failed");
            return false;
        }
    }

    public int pullFromEncoder(byte[] b) {
        try {
            int result = AACFilter.dequeueData(this.encoder, this.encoderOutputBuffers, this.encoderBufferInfo, b);
            if (result == -3) {
                this.encoderOutputBuffers = this.encoder.getOutputBuffers();
                return this.pullFromDecoder(b);
            }
            return result;
        }
        catch (Exception e) {
            return 0;
        }
    }

    public boolean postprocess() {
        if (this.initialized) {
            this.encoder.flush();
            Log.i("Stopping encoder");
            this.encoder.stop();
            Log.i("Stopping decoder");
            this.decoder.flush();
            this.decoder.stop();
            Log.i("Release encoder");
            this.encoder.release();
            Log.i("Release decoder");
            this.decoder.release();
            this.encoder = null;
            this.decoder = null;
            this.initialized = false;
        }
        return true;
    }

    private static boolean queueData(MediaCodec codec, ByteBuffer[] inputBuffers, byte[] data, int size) {
        int bufdex = codec.dequeueInputBuffer(0);
        if (bufdex >= 0) {
            inputBuffers[bufdex].position(0);
            inputBuffers[bufdex].put(data, 0, size);
            codec.queueInputBuffer(bufdex, 0, size, 0, 0);
            return true;
        }
        return false;
    }

    private static int dequeueData(MediaCodec codec, ByteBuffer[] ouputBuffers, MediaCodec.BufferInfo bufferInfo, byte[] b) {
        for (int pcmbufPollCount = 0; pcmbufPollCount < 1; ++pcmbufPollCount) {
            int decBufIdx = codec.dequeueOutputBuffer(bufferInfo, 100);
            if (decBufIdx >= 0) {
                if (b.length < bufferInfo.size) {
                    Log.e("array is too small " + b.length + " < " + bufferInfo.size);
                }
                if (bufferInfo.flags == 2) {
                    Log.i("JUST READ MediaCodec.BUFFER_FLAG_CODEC_CONFIG buffer");
                }
                ouputBuffers[decBufIdx].get(b, 0, bufferInfo.size);
                ouputBuffers[decBufIdx].position(0);
                codec.releaseOutputBuffer(decBufIdx, false);
                return bufferInfo.size;
            }
            if (decBufIdx == -3) {
                return -3;
            }
            if (decBufIdx == -2) {
                Log.i("MediaCodec.INFO_OUTPUT_FORMAT_CHANGED");
                Log.i("CHANNEL_COUNT: " + codec.getOutputFormat().getInteger("channel-count"));
                Log.i("SAMPLE_RATE: " + codec.getOutputFormat().getInteger("sample-rate"));
                continue;
            }
            if (decBufIdx != -1) continue;
        }
        return 0;
    }
}

