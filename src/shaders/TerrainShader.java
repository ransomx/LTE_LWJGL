/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shaders;

import Entities.Camera;
import Entities.Light;
import java.util.List;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import toolBox.Maths;

/**
 *
 * @author Dominik
 */
public class TerrainShader extends ShaderProgram{
     private static final int MAX_LIGHTS = 10;

    private static final String VERTEX_FILE = "src/shaders/terrainVertexShader.txt";
    private static final String FRAGMENT_FILE = "src/shaders/terrainFragmentShader.txt";

    private int location_transformationMatrix;
    private int location_projectionMatrix;
    private int location_viewMatrix;
    private int location_ambient;
    private int location_diffuseMultiplier;
    private int location_specularMultiplier;
    
    private int location_diffuseSwitch;
    private int location_ambientSwitch;
    private int location_specularSwitch;

    private int location_lightPosition[];
    private int location_lightColour[];
    private int location_attenuation[];

    private int location_shineDamper;
    private int location_reflectivity;

    public TerrainShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoords");
        super.bindAttribute(2, "normal");
    }

    @Override
    protected void getAllUniformLocations() {
        location_transformationMatrix = super.getUniformLocation("transformationMatrix");
        location_projectionMatrix = super.getUniformLocation("projectionMatrix");
        location_viewMatrix = super.getUniformLocation("viewMatrix");

        location_shineDamper = super.getUniformLocation("shineDamper");
        location_reflectivity = super.getUniformLocation("reflectivity");
        
        location_diffuseSwitch = super.getUniformLocation("diffuseSwitch");
        location_specularSwitch = super.getUniformLocation("specularSwitch");
        location_ambientSwitch = super.getUniformLocation("ambientSwitch");
        location_ambient = super.getUniformLocation("ambientLight");
        location_diffuseMultiplier = super.getUniformLocation("diffuseMultiplier");
        location_specularMultiplier = super.getUniformLocation("specularMultiplier");

        location_lightPosition = new int[MAX_LIGHTS];
        location_lightColour = new int[MAX_LIGHTS];
        location_attenuation = new int[MAX_LIGHTS];
        for (int i = 0; i < MAX_LIGHTS; i++) {
            location_lightPosition[i] = super.getUniformLocation("lightPosition[" + i + "]");
            location_lightColour[i] = super.getUniformLocation("lightColour[" + i + "]");
            location_attenuation[i] = super.getUniformLocation("attenuation[" + i + "]");
        }
    }

    public void loadShineVariables(float damper, float reflectivity) {
        super.loadFloat(location_shineDamper, damper);
        super.loadFloat(location_reflectivity, reflectivity);
    }

    public void loadLights(List<Light> lights) {
        for (int i = 0; i < MAX_LIGHTS; i++) {
            if (i < lights.size()) {
                super.loadVector(location_lightPosition[i], lights.get(i).getPosition());
                super.loadVector(location_lightColour[i], lights.get(i).getColour());
                super.loadVector(location_attenuation[i], lights.get(i).getAttenuation());
            } else {
                super.loadVector(location_lightPosition[i], new Vector3f(0, 0, 0));
                super.loadVector(location_lightColour[i], new Vector3f(0, 0, 0));
                super.loadVector(location_attenuation[i], new Vector3f(1, 0, 0)); //divide by zero in shader if 0,0,0
            }
        }
    }

    public void loadTransformationMatrix(Matrix4f matrix) {
        super.loadMatrix(location_transformationMatrix, matrix);
    }

    public void loadProjectionMatrix(Matrix4f projection) {
        super.loadMatrix(location_projectionMatrix, projection);
    }
    
   public void loadDiffuseSwitch(){
        super.loadBoolean(location_diffuseSwitch, ShaderVariables.diffuseSwitch);
    }
    public void loadSpecularSwitch(){
        super.loadBoolean(location_specularSwitch, ShaderVariables.specularSwitch);
    }
    public void loadAmbientSwitch(){
        super.loadBoolean(location_ambientSwitch, ShaderVariables.ambientSwitch);
    }
    public void loadDiffuseMultiplier(){
        super.loadFloat(location_diffuseMultiplier, ShaderVariables.diffuseModifier);
    }
    public void loadSpecularMultiplier(){
        super.loadFloat(location_specularMultiplier, ShaderVariables.specularModifier);
    }
    public void loadAmbientLight(){
        super.loadFloat(location_ambient, ShaderVariables.ambientModifier);
    }

    public void loadViewMatrix(Camera camera) {
        Matrix4f viewMatrix = Maths.createViewMatrix(camera);
        super.loadMatrix(location_viewMatrix, viewMatrix);
    }
}
