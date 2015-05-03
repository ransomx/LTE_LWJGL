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
public class StellarObject extends Entity {
    public static final float SOLAR_MASS = 1.9891e+30f;

    private float mass;
    private float velocity;
    private Vector3f direction;
    private float scale;
    private float rotModifierX;
    private float rotModifierY;
    private float rotModifierZ;
    private final double SCALE_CONST = 100f;
    private final Arrow arrow;

    public StellarObject(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
        super(model, position, rotX, rotY, rotZ, scale);
        this.scale = 1.0f;
        this.rotModifierX = 0f;
        this.rotModifierY = 0f;
        this.rotModifierZ = 0f;
        this.velocity = 0f;
        this.direction = new Vector3f(0,0,0);
        
        this.arrow = new Arrow(model, this.getPosition());
        arrow.getModel().getTexture().setShineDamper(0);
        arrow.getModel().getTexture().setReflectivity(1);
    }

    public float getMass() {
        return mass;
    }

    public void setMass(float mass) {
        this.mass = mass;
        this.scale *= mass/SCALE_CONST;
    }

    public float getVelocity() {
        return velocity;
    }

    public void setVelocity(float vel) {
        this.velocity = vel;
    }

    public Vector3f getDirection() {
        return direction;
    }

    public void setDirection(Vector3f direction) {
        this.direction = direction;
    }

    @Override
    public void render() {

    }

    public Arrow getArrow() {
        return arrow;
    }
    
    @Override
    public void update() {
        this.increaseRotation(rotModifierX, rotModifierY, rotModifierZ);
        this.increasePosition(direction.x*velocity, direction.y*velocity, direction.z*velocity);
        this.setScale(scale);
        this.arrow.setPosition(this.getPosition());
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
