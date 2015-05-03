/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author Dominik
 */
public class Camera {

    private Vector3f position = new Vector3f(0, 0, 0);
    private Vector3f angle;
    private float turnSpeed = 1f;
    private float moveSpeed = .5f;
    private Vector3f forward = new Vector3f();
    private Vector3f left = new Vector3f();
    private Vector3f up = new Vector3f();

    public Camera() {
        this.angle = new Vector3f(0, 0, 0);
    }

    public Vector3f getAngle() {
        return angle;
    }

    private void calculateCamera() {
        calculatePitch();
        calculateYaw();
        calculateMoveVector();
        calculatePosition();
    }

    private void calculatePosition() {
        if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
            Vector3f toReduce = forward.translate(forward.x * moveSpeed, forward.y * moveSpeed, forward.z * moveSpeed);
            Vector3f.sub(position, toReduce, position);
        } else if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
            Vector3f toReduce = forward.translate(forward.x * moveSpeed, forward.y * moveSpeed, forward.z * moveSpeed);
            Vector3f.add(position, toReduce, position);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
            Vector3f toReduce = left.translate(left.x * moveSpeed, left.y * moveSpeed, left.z * moveSpeed);
            Vector3f.sub(position, toReduce, position);
        } else if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
            Vector3f toReduce = left.translate(left.x * moveSpeed, left.y * moveSpeed, left.z * moveSpeed);
            Vector3f.add(position, toReduce, position);
        }
    }

    public Vector3f getPosition() {
        return position;
    }

    private void calculatePitch() {
        if (Mouse.isButtonDown(0)) {
            angle.x -= Math.toRadians((Mouse.getY() - Display.getHeight() / 2) * turnSpeed * 0.01f);
        }

    }

    private void calculateYaw() {
        if (Mouse.isButtonDown(0)) {
            angle.y += (float) Math.toRadians((Mouse.getX() - Display.getWidth() / 2) * turnSpeed * 0.01f);
        }
    }

    private void calculateMoveVector() {
        Vector3f fy = new Vector3f((float) Math.cos(angle.y), 0f, (float) Math.sin(angle.y));
        Vector3f fz = new Vector3f((float) Math.cos(angle.y), 0f, (float) Math.sin(angle.y));
        Vector3f fx = new Vector3f((float)Math.sin(-angle.y) * (float)Math.cos(angle.x), (float)Math.sin(angle.x),
                (float)Math.cos(-angle.y) * (float)Math.cos(angle.x));
        
        fx.normalise(forward);
        fy.normalise(left);
        fz.normalise(up);
    }

    public void calculateMovement() {
        calculateCamera();
    }
}
