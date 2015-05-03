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
public class LightSource extends Entity{
    private Light light;

    public LightSource(TexturedModel model, Vector3f position, Light light) {
        super(model, position, 0, 0, 0, 3f);
        this.light = light;
        this.setScale(1f);
        this.getModel().getTexture().setReflectivity(1f);
        this.getModel().getTexture().setShineDamper(0f);
    }

    public Light getLight(){
        return this.light;
    }
    
    public void setLight(Light light){
        this.light = light;
    }
    
    @Override
    public void render() {
    }

    @Override
    public void update() {
        if(getLight()!=null)
             getLight().setPosition(this.getPosition());
    }
    
}
