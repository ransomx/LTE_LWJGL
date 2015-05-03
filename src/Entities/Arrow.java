/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import models.TexturedModel;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author Dominik
 */
public class Arrow extends Entity{

    public Arrow(TexturedModel model, Vector3f position) {
        super(model, position, 0f, 0f, 0f, 1f);
    }
    
    @Override
    public void render() {
        
    }

    @Override
    public void update() {
        Matrix4f matrix = new Matrix4f();
        matrix.m03 = getPosition().x;
        matrix.m13 = getPosition().y;
        matrix.m23 = getPosition().z;
        
        Vector3f rot = new Vector3f(direction);
        Matrix4f.rotate((float) 0, rot, matrix, matrix);
        this.increaseRotation(matrix.m03, matrix.m13, matrix.m23);
    }
    
}
