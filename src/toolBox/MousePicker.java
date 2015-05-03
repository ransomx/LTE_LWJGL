/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toolBox;

import Entities.Camera;
import Entities.Entity;
import java.util.List;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;
import terrains.Terrain;

/**
 *
 * @author Dominik
 */
public class MousePicker {

    private static final int RECURSION_COUNT = 200;
    private static final float RAY_RANGE = 600;

    private Vector3f currentRay;
    private Matrix4f projectionMatrix;
    private Matrix4f viewMatrix;
    private final Camera camera;

    private Terrain terrain;
    private Vector3f currentTerrainPoint;
    private List<Entity> entities;

    public MousePicker(Matrix4f projectionMatrix, Camera camera, Terrain terrain, List<Entity> list) {
        this.projectionMatrix = projectionMatrix;
        this.camera = camera;
        this.viewMatrix = Maths.createViewMatrix(camera);
        this.terrain = terrain;
        this.entities = list;
    }

    public Vector3f getCurrentTerrainPoint() {
        return currentTerrainPoint;
    }

    public Vector3f getCurrentRay() {
        return currentRay;
    }

    private Vector3f calculateMouseRay() {
        float mouseX = Mouse.getX();
        float mouseY = Mouse.getY();
        Vector2f normalizedCoords = getNormalizedDeviceCoords(mouseX, mouseY);
        Vector4f clipCoords = new Vector4f(normalizedCoords.x, normalizedCoords.y, -1f, 1f);
        Vector4f eyeCoords = toEyeCoords(clipCoords);
        Vector3f worldRay = toWorldCoords(eyeCoords);
        return worldRay;
    }

    private Vector3f toWorldCoords(Vector4f eyeCoords) {
        Matrix4f invertedView = Matrix4f.invert(viewMatrix, null);
        Vector4f rayWorld = Matrix4f.transform(invertedView, eyeCoords, null);
        Vector3f mouseRay = new Vector3f(rayWorld.x, rayWorld.y, rayWorld.z);
        mouseRay.normalise();
        return mouseRay;
    }

    private Vector4f toEyeCoords(Vector4f clipCoords) {
        Matrix4f invertedProjection = Matrix4f.invert(projectionMatrix, null);
        Vector4f eyeCoords = Matrix4f.transform(invertedProjection, clipCoords, null);
        return new Vector4f(eyeCoords.x, eyeCoords.y, -1f, 0f);

    }

    private Vector2f getNormalizedDeviceCoords(float mouseX, float mouseY) {
        float x = (2f * mouseX) / Display.getWidth() - 1;
        float y = (2f * mouseY) / Display.getHeight() - 1f;
        return new Vector2f(x, y);
    }

    public void update() {
        viewMatrix = Maths.createViewMatrix(camera);
        currentRay = calculateMouseRay();
        if (intersectionInRange(0, RAY_RANGE, currentRay)) {
            currentTerrainPoint = binarySearch(0, 0, RAY_RANGE, currentRay);
        } else {
            currentTerrainPoint = null;
        }
    }

    private Vector3f getPointOnRay(Vector3f ray, float distance) {
        Vector3f camPos = camera.getPosition();
        Vector3f start = new Vector3f(camPos.x, camPos.y, camPos.z);
        Vector3f scaledRay = new Vector3f(ray.x * distance, ray.y * distance, ray.z * distance);
        return Vector3f.add(start, scaledRay, null);
    }
    
    public Entity getSelectedEntity(){
        currentRay = calculateMouseRay();
        return getEntity(currentRay);
    }

    private Vector3f binarySearch(int count, float start, float finish, Vector3f ray) {
        float half = start + ((finish - start) / 2f);
        if (count >= RECURSION_COUNT) {
            Vector3f endPoint = getPointOnRay(ray, half);
            Terrain t = getTerrain(endPoint.getX(), endPoint.getZ());
            if (t != null) {
                return endPoint;
            } else {
                return endPoint;
            }
        }
        if (intersectionInRange(start, half, ray)) {
            return binarySearch(count + 1, start, half, ray);
        } else {
            return binarySearch(count + 1, half, finish, ray);
        }
    }

    private boolean sphereIntersection(Vector3f sphereOrigin, float radius, Vector3f rayDirection, Vector3f rayOrigin) {
        Vector3f Q = new Vector3f(
                rayOrigin.x - sphereOrigin.x,
                rayOrigin.y - sphereOrigin.y,
                rayOrigin.z - sphereOrigin.z);
        float c = Q.length();
        float v = Vector3f.dot(Q, rayDirection);
        float d = 8 * 8 - (c * c - v * v);

        return d < 0.0? false : true;
    }

    private Entity returnClosest(Entity origin, Entity next) {
        if (origin == null) {
            return next;
        }
        if (next == null) {
            return origin;
        }
        Vector3f p = new Vector3f(
                origin.getPosition().x - camera.getPosition().x,
                origin.getPosition().y - camera.getPosition().y,
                origin.getPosition().z - camera.getPosition().z);
        Vector3f q = new Vector3f(
                next.getPosition().x - camera.getPosition().x,
                next.getPosition().y - camera.getPosition().y,
                next.getPosition().z - camera.getPosition().z);

        return p.length() < q.length() ? origin : next;
    }

    private Entity getEntity(Vector3f ray) {
        Entity ret = null;
        for (Entity e : entities) {
            if (sphereIntersection(e.getPosition(), e.getSize(), ray, camera.getPosition())) {
                ret = returnClosest(ret, e);
            }
        }
        return ret;
    }

    private boolean intersectionInRange(float start, float finish, Vector3f ray) {
        Vector3f startPoint = getPointOnRay(ray, start);
        Vector3f endPoint = getPointOnRay(ray, finish);
        if (!isUnderGround(startPoint) && isUnderGround(endPoint)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isUnderGround(Vector3f testPoint) {
        Terrain terrain = getTerrain(testPoint.getX(), testPoint.getZ());
        float height = 0;
        if (terrain != null) {
            height = terrain.getHeightOfTerrain(testPoint.getX(), testPoint.getZ());
        }
        if (testPoint.y < height) {
            return true;
        } else {
            return false;
        }
    }

    private Terrain getTerrain(float worldX, float worldZ) {
        return terrain;
    }

}
