package com.myvideocalltest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import org.linphone.core.LinphoneCall;
import org.linphone.core.LinphoneCallParams;
import org.linphone.core.LinphoneCoreException;
import org.linphone.core.LinphoneCoreFactory;
import org.linphone.mediastream.video.AndroidVideoWindowImpl;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    LinphoneMiniManager manager;
    private SurfaceView mVideoView;
    private SurfaceView mCaptureView;
    private AndroidVideoWindowImpl androidVideoWindowImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mVideoView = (SurfaceView) findViewById(R.id.videoSurface);
        mCaptureView = (SurfaceView) findViewById(R.id.videoCaptureSurface);
        mCaptureView.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS); // Warning useless because value is ignored and automatically set by new APIs.

        manager = new LinphoneMiniManager(this);
        fixZOrder(mVideoView, mCaptureView);

        androidVideoWindowImpl = new AndroidVideoWindowImpl(mVideoView, mCaptureView, new AndroidVideoWindowImpl.VideoWindowListener() {
            public void onVideoRenderingSurfaceReady(AndroidVideoWindowImpl vw, SurfaceView surface) {
                mVideoView = surface;
                manager.getLinphoneCore().setVideoWindow(vw);
            }

            public void onVideoRenderingSurfaceDestroyed(AndroidVideoWindowImpl vw) {

            }

            public void onVideoPreviewSurfaceReady(AndroidVideoWindowImpl vw, SurfaceView surface) {
                mCaptureView = surface;
                manager.getLinphoneCore().setPreviewWindow(mCaptureView);
            }

            public void onVideoPreviewSurfaceDestroyed(AndroidVideoWindowImpl vw) {

            }
        });

        Button fab = (Button) findViewById(R.id.test);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    manager.getLinphoneCore().invite(LinphoneCoreFactory.instance().createLinphoneAddress("sip:ashwanijanghu@sip.linphone.org"));

                } catch (LinphoneCoreException e) {
                    Log.e(TAG, e.getMessage(), e);
                }
            }
        });

        Button fab2 = (Button) findViewById(R.id.start_video);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinphoneCall call = manager.getLinphoneCore().getCurrentCall();
                call.enableCamera(true);
                manager.setFrontCamAsDefault();
                LinphoneCallParams pram = call.getCurrentParamsCopy();
                pram.setVideoEnabled(true);
//                pram.setAudioBandwidth(0); // disable limitation
                manager.getLinphoneCore().updateCall(call, pram);

                manager.getLinphoneCore().adjustSoftwareVolume();

                Log.v(TAG, "Video device id is :" + manager.getLinphoneCore().getVideoDevice());
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        if (androidVideoWindowImpl != null) {
            synchronized (androidVideoWindowImpl) {
                manager.getLinphoneCore().setVideoWindow(androidVideoWindowImpl);
            }
        }

    }

    @Override
    public void onPause() {
        if (androidVideoWindowImpl != null) {
            synchronized (androidVideoWindowImpl) {
                /*
                 * this call will destroy native opengl renderer which is used by
                 * androidVideoWindowImpl
                 */
                manager.getLinphoneCore().setVideoWindow(null);
            }
        }
        super.onPause();
    }

    @Override
    public void onDestroy() {

        mCaptureView = null;
        mVideoView = null;
        if (androidVideoWindowImpl != null) {
            // Prevent linphone from crashing if correspondent hang up while you are rotating
            androidVideoWindowImpl.release();
            androidVideoWindowImpl = null;
        }
        super.onDestroy();
    }



    private void fixZOrder(SurfaceView video, SurfaceView preview) {
        video.setZOrderOnTop(false);
        preview.setZOrderOnTop(true);
        preview.setZOrderMediaOverlay(true); // Needed to be able to display control layout over
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
