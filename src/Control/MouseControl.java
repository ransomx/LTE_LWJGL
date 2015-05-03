/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Entities.Entity;
import Entities.Light;
import java.util.Random;
import lwjglorganic.engineTester.MainGame;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author Dominik
 */
public class MouseControl extends Thread {

    private final MainGame mlg;

    public MouseControl(MainGame mlg) {
        this.mlg = mlg;
    }

    @Override
    public void run() {
        while (!this.isInterrupted()) {
            try {
                while (Mouse.next()) {
                    boolean pressed = Mouse.getEventButtonState();
                    int button = Mouse.getEventButton();
                    if (pressed) {
                        Entity e = mlg.getPicker().getSelectedEntity();
                        switch (button) {
                            case 0:
                                if (e != null) {
                                    mlg.getEnt().setSelected(e);
                                    mlg.getEnt().notifyObservers();
                                }
                                break;
                            case 1:
                                while (Mouse.isButtonDown(button)) {
                                    Vector3f position = mlg.getPicker().getCurrentTerrainPoint();
                                    if (position != null && e != null) {
                                        e.setPosition(new Vector3f(position.getX(), position.getY(), position.getZ()));
                                    }
                                }
                                break;
                            case 2:
                                if (mlg.getLights().size() < 10) {
                                    Vector3f position = mlg.getPicker().getCurrentTerrainPoint();
                                    if (position != null) {
                                        position.y += 10;
                                        Random generator = new Random();
                                        Vector3f colour = new Vector3f(generator.nextInt(255), generator.nextInt(255), generator.nextInt(255));

                                        Light l2 = mlg.getEntityFactory().createLight(position, colour, new Vector3f(1, 0.01f, 0.2f));
                                        mlg.getLights().add(l2);
                                        mlg.getEntities().add(mlg.getEntityFactory().createLightSource(l2, position));
                                    }
                                }
                                break;
                        }
                    } else {

                    }
                    Entity e = mlg.getPicker().getSelectedEntity();
                    if (e != null) {
                        e.getPosition().y += Mouse.getDWheel()/120; //range in <-120,120>
                    }
                }
            } catch (java.lang.IllegalStateException e) {

            }
        }
    }
}
