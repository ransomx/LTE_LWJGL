/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textures;

/**
 *
 * @author Dominik
 */
public class ModelTexture {
    private int textureID;
    
    private float shineDamper = 0;
    private float reflectivity = 1;
    
    private boolean hasTransparency = false;
    
    public ModelTexture(int id){
        this.textureID = id;
    }
    
    public ModelTexture(ModelTexture model){
        this.textureID = model.textureID;
    }

    public boolean isHasTransparency() {
        return hasTransparency;
    }

    public void setHasTransparency(boolean hasTransparency) {
        this.hasTransparency = hasTransparency;
    }
    
    public int getID(){
        return this.textureID;
    }

    public int getTextureID() {
        return textureID;
    }

    public void setTextureID(int textureID) {
        this.textureID = textureID;
    }

    public float getShineDamper() {
        return shineDamper;
    }

    public void setShineDamper(float shineDamper) {
        this.shineDamper = shineDamper;
    }

    public float getReflectivity() {
        return reflectivity;
    }

    public void setReflectivity(float reflectivity) {
        this.reflectivity = reflectivity;
    }
}
