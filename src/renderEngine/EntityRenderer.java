/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renderEngine;

import Entities.Entity;
import java.util.List;
import java.util.Map;
import models.RawModel;
import models.TexturedModel;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import shaders.StaticShader;
import textures.ModelTexture;
import toolBox.Maths;

/**
 *
 * @author Dominik
 */
public class EntityRenderer {
    private final StaticShader shader;
    public EntityRenderer(StaticShader shader, Matrix4f projectionMatrix) {
        this.shader = shader;
        shader.start();
        shader.loadProjectionMatrix(projectionMatrix);
        shader.stop();
    }
    
    public void render(Map<TexturedModel, List<Entity>> entities){
        for (TexturedModel model : entities.keySet()) {
            prepareTexturedModel(model);
            List<Entity> batch = entities.get(model);
            for (Entity e : batch) {
                prepareInstance(e);
                GL11.glDrawElements(GL11.GL_TRIANGLES, 
                        e.getModel().getRawModel().getVertexCount(),
                        GL11.GL_UNSIGNED_INT, 0);
            }
            unbindTexturedModel();
        }
    }
    
    private void prepareTexturedModel(TexturedModel model){
        RawModel rawModel = model.getRawModel();

        GL30.glBindVertexArray(rawModel.getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        GL20.glEnableVertexAttribArray(2);
        ModelTexture texture = model.getTexture();
        if(texture.isHasTransparency())
               MasterRenderer.disableCulling();
        
        shader.loadShineVariables(texture.getShineDamper(), texture.getReflectivity());
        
        shader.loadAmbientLight();
        shader.loadDiffuseSwitch();
        shader.loadAmbientSwitch();
        shader.loadSpecularSwitch();
        shader.loadDiffuseMultiplier();
        shader.loadSpecularMultiplier();

        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getTexture().getID());
    }
    
    private void unbindTexturedModel(){
        MasterRenderer.enableCulling();
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL20.glDisableVertexAttribArray(2);
        GL30.glBindVertexArray(0);  
    }
    
    private void prepareInstance(Entity entity){
        Matrix4f transformationMatrix = Maths.createTransformatonMatrix(
                entity.getPosition(),
                entity.getRotX(),
                entity.getRotY(),
                entity.getRotZ(),
                entity.getScale());
        
        shader.loadTransformationMatrix(transformationMatrix);
    }

    public void render(RawModel rawModel){
        GL30.glBindVertexArray(rawModel.getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL11.glDrawElements(GL11.GL_LINES, rawModel.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
        GL20.glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);  
    }
}
