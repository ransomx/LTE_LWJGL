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
 * @author Dominik
 */
public class EntityObject extends Entity {
    private float velocity;
    private float scale;

    public EntityObject(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
        super(model, position, rotX, rotY, rotZ, scale);
        this.scale = 1.0f;
        
        this.velocity = 0f;
        this.direction = new Vector3f(0,0,0);
        this.size = 50;
    }

    public float getVelocity() {
        return velocity;
    }

    public void setVelocity(float vel) {
        this.velocity = vel;
    }

    @Override
    public void render() {

    }
    
    @Override
    public void update() {
        this.increaseRotation(rotModifierX, rotModifierY, rotModifierZ);
        this.increasePosition(direction.x*velocity, direction.y*velocity, direction.z*velocity);
    }

    public float getRotModifierX() {
        return rotModifierX;
    }

    public void setRotModifierX(float rotModifierX) {
        this.rotModifierX = rotModifierX;
    }

    public float getRotModifierY() {
        return rotModifierY;
    }

    public void setRotModifierY(float rotModifierY) {
        this.rotModifierY = rotModifierY;
    }

    public float getRotModifierZ() {
        return rotModifierZ;
    }

    public void setRotModifierZ(float rotModifierZ) {
        this.rotModifierZ = rotModifierZ;
    }
    
    
}
