/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Entities.LightSource;
import lwjglorganic.engineTester.MainGame;
import org.lwjgl.input.Keyboard;

/**
 *
 * @author Dominik
 */
public class KeyboardControl extends Thread {

    private final MainGame mlg;
    public static boolean mode = true;

    public KeyboardControl(MainGame mlg) {
        this.mlg = mlg;
    }

    @Override
    public void run() {
        while (!this.isInterrupted()) {
            try {
                while (Keyboard.next()) {
                    handleModeSwitch();
                    handleEntityRemoval();
                }
            } catch (java.lang.IllegalStateException e) {

            }
        }
    }

    private void handleEntityRemoval() {
        if (Keyboard.isKeyDown(Keyboard.KEY_DELETE)) {

            mlg.getEntities().remove(mlg.getEnt().getSelected());
            if (mlg.getEnt().getSelected() instanceof LightSource) {
                LightSource ls = (LightSource) mlg.getEnt().getSelected();
                mlg.getLights().remove(ls.getLight());
            }
            mlg.getEnt().setSelected(null);
        }
    }

    private void handleModeSwitch() {
        if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
            mode = !mode;
        }
    }
}
