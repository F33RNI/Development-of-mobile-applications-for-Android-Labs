package com.example.cube;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;

public class MyGLRenderer implements GLSurfaceView.Renderer{

    private Cube cube;  //(NEW)
    private static float angleCube = 0;    //rotational angle in degree for cube
    private static float speedCube = 0.5f; //rotational speed for cube

    //Constructor
    public MyGLRenderer(Context context){
        cube = new Cube(context);          //(NEW)
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config){
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);  //Set color's clear-value to black
        gl.glClearDepthf(1.0f);                              // Set depth's clear-value to farthest
        gl.glEnable(GL10.GL_DEPTH_TEST);                        //Enables depth-buffer for hidden surface removal
        gl.glDepthFunc(GL10.GL_LEQUAL);                         //The type of depth testing to do
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);//nice perspective view
        gl.glShadeModel(GL10.GL_SMOOTH);                        //Enable smooth shading of color
        gl.glDisable(GL10.GL_DITHER);                           //Disable dithering for better performance

        //Setup Texture, each time the surface is created (NEW)
        cube.loadTexture(gl);                                   //Load images into textures (NEW)
        gl.glEnable(GL10.GL_TEXTURE_2D);                        //Enable texture (NEW)
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height){
        if(height ==0) height =1; //To prevent divide bby zero
        float aspect = (float) width/height;

        //Set the viewport (display area) to cover the entire window
        gl.glViewport(0,0,width,height);
        //Setup perspective projection, with aspect ratio matches viewport
        gl.glMatrixMode(GL10.GL_PROJECTION); //Select projection matrix
        gl.glLoadIdentity();                 //Reset projection matrix
        // Use perspective projection
        GLU.gluPerspective(gl, 45, aspect, 0.1f, 100.f);

        gl.glMatrixMode(GL10.GL_MODELVIEW); //Select model-view matrix
        gl.glLoadIdentity();                //Reset


    }

    @Override
    public void onDrawFrame(GL10 gl){
        //Clear color and depth buffers
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        //Render the cube
        gl.glLoadIdentity();                            //reset the model-view matrix
        gl.glTranslatef(0.0f, 0.0f, -6.0f);   //translate into the screen
        gl.glRotatef(angleCube, 0.15f, 1.0f, 0.3f);//rotate
        cube.draw(gl);

        //update the rotation angle after each refresh.
        angleCube += speedCube;
    }
}
