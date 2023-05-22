package com.example.photocube;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/** * Created by Kalidoss on 18/07/16. */


public class MyGLRender implements GLSurfaceView.Renderer {
    private PhotoCube cube;
    public  float mAngleX,mAngleY;

    // Constructor    public MyGLRender(Context context) {
    cube = new PhotoCube(context);
}

    // Call back when the surface is first created or re-created.

    @Override

    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glClear(GLES20.GL_DEPTH_BUFFER_BIT | GLES20.GL_COLOR_BUFFER_BIT);
        gl.glClearDepthf(1.0f);            // Set depth's clear-value to farthest

        gl.glEnable(GL10.GL_DEPTH_TEST);   // Enables depth-buffer for hidden surface removal

        gl.glDepthFunc(GL10.GL_LEQUAL);    // The type of depth testing to do

        gl.glShadeModel(GL10.GL_SMOOTH);   // Enable smooth shading of color

        gl.glDisable(GL10.GL_DITHER);      // Disable dithering for better performance



        // Setup Texture, each time the surface is created (NEW)

        cube.loadTexture(gl);             // Load images into textures (NEW)

        gl.glEnable(GL10.GL_TEXTURE_2D);  // Enable texture (NEW)

        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT,
                GL10.GL_FASTEST);
        gl.glEnable(GL10.GL_LIGHT1);   // Enable Light 1 (NEW)

        gl.glEnable(GL10.GL_LIGHT0);   // Enable the default Light 0 (NEW)

    }

    // Call back after onSurfaceCreated() or whenever the window's size changes

    @Override

    public void onSurfaceChanged(GL10 gl, int width, int height) {
        gl.glViewport(0, 0, width, height);
        float ratio = (float) width / height;
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glFrustumf(-ratio, ratio, -1, 1, 1, 10);
    }

    // Call back to draw the current frame.

    @Override

    public void onDrawFrame(final GL10 gl) {
        // Clear color and depth buffers

        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        // ----- Render the Cube -----

        gl.glLoadIdentity();

        // Reset the model-view matrix

        gl.glTranslatef(0, 0, -3.0f);  // Translate into the screen

        gl.glFrontFace(GL10.GL_CCW);    // Set the front face

        gl.glEnable(GL10.GL_CULL_FACE); // Enable cull face

        gl.glCullFace(GL10.GL_BACK);

        //rotate cube
        gl.glRotatef(mAngleX,0.0f, 1.0f, 0.0f);
        gl.glRotatef(mAngleY, 1, 0, 0);
        cube.draw(gl);
    }
}