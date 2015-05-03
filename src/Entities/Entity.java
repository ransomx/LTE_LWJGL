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
public abstract class Entity {
    private TexturedModel model;
    private Vector3f position;
    private float rotX,rotY,rotZ;
    private float scale;
    protected Vector3f direction;
    protected int size;
    protected float rotModifierX;
    protected float rotModifierY;
    protected float rotModifierZ;

    public Entity(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
        this.model = model;
        this.position = position;
        this.rotX = rotX;
        this.rotY = rotY;
        this.rotZ = rotZ;
        this.scale = scale;
        this.rotModifierX = 0f;
        this.rotModifierY = 0f;
        this.rotModifierZ = 0f;
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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
    public Vector3f getDirection() {
        return direction;
    }

    public void setDirection(Vector3f direction) {
        this.direction = direction;
    }
    
    public abstract void render();
    
    public abstract void update();
    
    public void increasePosition(float dx, float dy, float dz){
        this.position.x+=dx;
        this.position.y+=dy;
        this.position.z+=dz;
    }
    
    public void increaseRotation(float dx, float dy, float dz){
        this.rotX+=dx;
        this.rotY+=dy;
        this.rotZ+=dz;
    }

    public TexturedModel getModel() {
        return model;
    }

    public void setModel(TexturedModel model) {
        this.model = model;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public float getRotX() {
        return rotX;
    }

    public void setRotX(float rotX) {
        this.rotX = rotX;
    }

    public float getRotY() {
        return rotY;
    }

    public void setRotY(float rotY) {
        this.rotY = rotY;
    }

    public float getRotZ() {
        return rotZ;
    }

    public void setRotZ(float rotZ) {
        this.rotZ = rotZ;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }
    
    
    
}
