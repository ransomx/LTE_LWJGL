/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import models.TexturedModel;
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author Domík
 */
public class Player extends Entity{
    private final Camera camera;

    public Player(Camera camera, TexturedModel model, Vector3f position) {
        super(model, position, 0, 0, 0, 1f);
        this.camera = camera;
    }

    @Override
    public void render() {
    }

    @Override
    public void update() {
        this.camera.getPosition().x = this.getPosition().x;
        this.camera.getPosition().y = this.getPosition().y+5;
        this.camera.getPosition().z = this.getPosition().z;
        this.camera.calculateMovement();
    }
    
    
    
}
