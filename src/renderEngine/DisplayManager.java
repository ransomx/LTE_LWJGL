/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renderEngine;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

/**
 *
 * @author Dominik
 */
public class DisplayManager {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int FPSCAP = 60;
    
    public static void createDisplay(){
        try {
            ContextAttribs attribs =new ContextAttribs(3, 2)
                    .withForwardCompatible(true)
                    .withProfileCore(true);
            
            Display.setDisplayMode(new DisplayMode(WIDTH,HEIGHT));
            Display.create(new PixelFormat(), attribs);
            Display.setTitle("LTE viewer");
        } catch (LWJGLException ex) {
            Logger.getLogger(DisplayManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        GL11.glViewport(0, 0, WIDTH, HEIGHT);
    }
    public static void updateDisplay(){
        Display.sync(FPSCAP);
        Display.update();
    }
    public static void closeDisplay(){
        Display.destroy();
    }
    
}
