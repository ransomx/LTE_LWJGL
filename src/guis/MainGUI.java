/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guis;

import Entities.EntityObserver;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import lwjglorganic.engineTester.ControlPanel;
import lwjglorganic.engineTester.MainGame;
import org.lwjgl.opengl.Display;

/**
 *
 * @author Dominik
 */
public class MainGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    private MainGame mgp;

    public MainGUI() {
        // Create a new canvas and set its size.
        Canvas canvas = new Canvas();
        // Must be 640*480 to match the size of an Env3D window
        canvas.setSize(800, 600);
        // This is the magic!  The setParent method attaches the 
        // opengl window to the awt canvas.
        try {
            Display.setParent(canvas);
        } catch (Exception e) {
        }
        ControlPanel control = new ControlPanel();
        control.setPreferredSize(new Dimension(800, 200));

        // Construct the GUI as normal
        add(control, BorderLayout.SOUTH);
        add(canvas, BorderLayout.CENTER);

        pack();
        setVisible(true);
        setResizable(false);

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                mgp.changeStopped();
                System.exit(0);
            }
        });
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        EntityObserver entObserver = new EntityObserver();
        entObserver.addObserver(control);
        mgp = new MainGame(entObserver);
        mgp.start();
    }

}
