/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  android.graphics.Bitmap
 *  android.graphics.Bitmap$Config
 *  android.graphics.Canvas
 *  android.graphics.Paint
 *  android.graphics.Rect
 *  android.opengl.GLSurfaceView
 *  android.opengl.GLSurfaceView$Renderer
 *  android.view.Surface
 *  android.view.Surface$OutOfResourcesException
 *  android.view.SurfaceHolder
 *  android.view.SurfaceHolder$Callback
 *  android.view.SurfaceView
 *  javax.microedition.khronos.egl.EGLConfig
 *  javax.microedition.khronos.opengles.GL10
 */
package org.linphone.mediastream.video;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.opengl.GLSurfaceView;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import org.linphone.mediastream.Log;
import org.linphone.mediastream.video.display.OpenGLESDisplay;

public class AndroidVideoWindowImpl {
    private SurfaceView mVideoRenderingView;
    private SurfaceView mVideoPreviewView;
    private boolean useGLrendering;
    private Bitmap mBitmap = null;
    private Surface mSurface = null;
    private VideoWindowListener mListener = null;
    private Renderer renderer;

    public AndroidVideoWindowImpl(SurfaceView renderingSurface, SurfaceView previewSurface, VideoWindowListener listener) {
        this.mVideoRenderingView = renderingSurface;
        this.mVideoPreviewView = previewSurface;
        this.useGLrendering = renderingSurface instanceof GLSurfaceView;
        this.mListener = listener;
        this.init();
    }

    public AndroidVideoWindowImpl(SurfaceView renderingSurface, SurfaceView previewSurface) {
        this.mVideoRenderingView = renderingSurface;
        this.mVideoPreviewView = previewSurface;
        this.useGLrendering = renderingSurface instanceof GLSurfaceView;
    }

    public void init() {
        this.mVideoRenderingView.getHolder().addCallback(new SurfaceHolder.Callback(){

            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                Log.i("Video display surface is being changed.");
                if (!AndroidVideoWindowImpl.this.useGLrendering) {
                    AndroidVideoWindowImpl androidVideoWindowImpl = AndroidVideoWindowImpl.this;
                    synchronized (androidVideoWindowImpl) {
                        AndroidVideoWindowImpl.this.mBitmap = Bitmap.createBitmap((int)width, (int)height, (Bitmap.Config)Bitmap.Config.RGB_565);
                        AndroidVideoWindowImpl.this.mSurface = holder.getSurface();
                    }
                }
                if (AndroidVideoWindowImpl.this.mListener != null) {
                    AndroidVideoWindowImpl.this.mListener.onVideoRenderingSurfaceReady(AndroidVideoWindowImpl.this, AndroidVideoWindowImpl.this.mVideoRenderingView);
                }
                Log.w("Video display surface changed");
            }

            public void surfaceCreated(SurfaceHolder holder) {
                Log.w("Video display surface created");
            }

            public void surfaceDestroyed(SurfaceHolder holder) {
                if (!AndroidVideoWindowImpl.this.useGLrendering) {
                    AndroidVideoWindowImpl androidVideoWindowImpl = AndroidVideoWindowImpl.this;
                    synchronized (androidVideoWindowImpl) {
                        AndroidVideoWindowImpl.this.mSurface = null;
                        AndroidVideoWindowImpl.this.mBitmap = null;
                    }
                }
                if (AndroidVideoWindowImpl.this.mListener != null) {
                    AndroidVideoWindowImpl.this.mListener.onVideoRenderingSurfaceDestroyed(AndroidVideoWindowImpl.this);
                }
                Log.d("Video display surface destroyed");
            }
        });
        if (this.mVideoPreviewView != null) {
            this.mVideoPreviewView.getHolder().addCallback(new SurfaceHolder.Callback(){

                public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                    Log.i("Video preview surface is being changed.");
                    if (AndroidVideoWindowImpl.this.mListener != null) {
                        AndroidVideoWindowImpl.this.mListener.onVideoPreviewSurfaceReady(AndroidVideoWindowImpl.this, AndroidVideoWindowImpl.this.mVideoPreviewView);
                    }
                    Log.w("Video preview surface changed");
                }

                public void surfaceCreated(SurfaceHolder holder) {
                    Log.w("Video preview surface created");
                }

                public void surfaceDestroyed(SurfaceHolder holder) {
                    if (AndroidVideoWindowImpl.this.mListener != null) {
                        AndroidVideoWindowImpl.this.mListener.onVideoPreviewSurfaceDestroyed(AndroidVideoWindowImpl.this);
                    }
                    Log.d("Video preview surface destroyed");
                }
            });
        }
        if (this.useGLrendering) {
            this.renderer = new Renderer();
            ((GLSurfaceView)this.mVideoRenderingView).setRenderer((GLSurfaceView.Renderer)this.renderer);
            ((GLSurfaceView)this.mVideoRenderingView).setRenderMode(0);
        }
    }

    public void release() {
    }

    public void setListener(VideoWindowListener l) {
        this.mListener = l;
    }

    public Surface getSurface() {
        if (this.useGLrendering) {
            Log.e("View class does not match Video display filter used (you must use a non-GL View)");
        }
        return this.mVideoRenderingView.getHolder().getSurface();
    }

    public Bitmap getBitmap() {
        if (this.useGLrendering) {
            Log.e("View class does not match Video display filter used (you must use a non-GL View)");
        }
        return this.mBitmap;
    }

    public void setOpenGLESDisplay(int ptr) {
        if (!this.useGLrendering) {
            Log.e("View class does not match Video display filter used (you must use a GL View)");
        }
        this.renderer.setOpenGLESDisplay(ptr);
    }

    public void requestRender() {
        ((GLSurfaceView)this.mVideoRenderingView).requestRender();
    }

    public synchronized void update() {
        if (this.mSurface != null) {
            try {
                Canvas canvas = this.mSurface.lockCanvas(null);
                canvas.drawBitmap(this.mBitmap, 0.0f, 0.0f, null);
                this.mSurface.unlockCanvasAndPost(canvas);
            }
            catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            catch (Surface.OutOfResourcesException e) {
                e.printStackTrace();
            }
        }
    }

    public static int rotationToAngle(int r) {
        switch (r) {
            case 0: {
                return 0;
            }
            case 1: {
                return 90;
            }
            case 2: {
                return 180;
            }
            case 3: {
                return 270;
            }
        }
        return 0;
    }

    private static class Renderer
    implements GLSurfaceView.Renderer {
        int ptr = 0;
        boolean initPending = false;
        int width;
        int height;

        public void setOpenGLESDisplay(int ptr) {
            Renderer renderer = this;
            synchronized (renderer) {
                if (this.ptr != 0 && ptr != this.ptr) {
                    this.initPending = true;
                }
                this.ptr = ptr;
            }
        }

        public void onDrawFrame(GL10 gl) {
            Renderer renderer = this;
            synchronized (renderer) {
                if (this.ptr == 0) {
                    return;
                }
                if (this.initPending) {
                    OpenGLESDisplay.init(this.ptr, this.width, this.height);
                    this.initPending = false;
                }
                OpenGLESDisplay.render(this.ptr);
            }
        }

        public void onSurfaceChanged(GL10 gl, int width, int height) {
            this.width = width;
            this.height = height;
            this.initPending = true;
        }

        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        }
    }

    public static interface VideoWindowListener {
        public void onVideoRenderingSurfaceReady(AndroidVideoWindowImpl var1, SurfaceView var2);

        public void onVideoRenderingSurfaceDestroyed(AndroidVideoWindowImpl var1);

        public void onVideoPreviewSurfaceReady(AndroidVideoWindowImpl var1, SurfaceView var2);

        public void onVideoPreviewSurfaceDestroyed(AndroidVideoWindowImpl var1);
    }

}

