/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renderEngine;

import java.util.List;
import models.RawModel;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import shaders.ShaderVariables;
import shaders.TerrainShader;
import terrains.Terrain;
import textures.ModelTexture;
import toolBox.Maths;

/**
 *
 * @author Dominik
 */
public class TerrainRenderer {

    private TerrainShader shader;
    

    public TerrainRenderer(TerrainShader shader, Matrix4f projectionMatrix) {
        this.shader = shader;
        shader.start();
        shader.loadProjectionMatrix(projectionMatrix);
        shader.stop();
    }

    public void render(List<Terrain> terrains) {
        for (Terrain terrain : terrains) {
            prepareTerrain(terrain);
            loadModelMatrix(terrain);
            GL11.glDrawElements(GL11.GL_TRIANGLES,
                    terrain.getModel().getVertexCount(),
                    GL11.GL_UNSIGNED_INT, 0);
            
            unbindTexturedModel();
        }
    }

    private void prepareTerrain(Terrain terrain) {
        RawModel rawModel = terrain.getModel();

        GL30.glBindVertexArray(rawModel.getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        GL20.glEnableVertexAttribArray(2);
        ModelTexture texture = terrain.getTexture();
        if (texture.isHasTransparency()) {
            MasterRenderer.disableCulling();
        }
        shader.loadShineVariables(texture.getShineDamper()*ShaderVariables.globalShineDamper,
                texture.getReflectivity()*ShaderVariables.specularModifier);
        
        shader.loadAmbientLight();
        shader.loadDiffuseSwitch();
        shader.loadAmbientSwitch();
        shader.loadSpecularSwitch();
        shader.loadDiffuseMultiplier();
        shader.loadSpecularMultiplier();

        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getID());
    }

    private void unbindTexturedModel() {
        MasterRenderer.enableCulling();
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL20.glDisableVertexAttribArray(2);
        GL30.glBindVertexArray(0);
    }

    private void loadModelMatrix(Terrain terrain) {
        Matrix4f transformationMatrix = Maths.createTransformatonMatrix(
                new Vector3f(terrain.getX(), 0, terrain.getZ()),
                0,
                0,
                0,
                1);

        shader.loadTransformationMatrix(transformationMatrix);
    }
}
