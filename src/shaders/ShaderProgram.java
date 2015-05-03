/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shaders;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author Dominik
 */
public abstract class ShaderProgram {

    private final int programID;
    private final int vertexShaderID;
    private final int fragmentShaderID;

    private static final FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);

    /**
     * Basic constructor
     * flow: Load shaders, tie both together,
     * attach shaders, link & validate.
     * @param vertexFile
     * @param fragmentFile
     */
    public ShaderProgram(String vertexFile, String fragmentFile) {
        this.vertexShaderID = loadShader(vertexFile, GL20.GL_VERTEX_SHADER);
        this.fragmentShaderID = loadShader(fragmentFile, GL20.GL_FRAGMENT_SHADER);
        this.programID = GL20.glCreateProgram(); //ties both together
        GL20.glAttachShader(programID, vertexShaderID); //attach vertex shader
        GL20.glAttachShader(programID, fragmentShaderID); //attach fragment shader
        this.bindAttributes(); //linked properly
        GL20.glLinkProgram(programID);
        GL20.glValidateProgram(programID);
        this.getAllUniformLocations();
    }

    protected abstract void getAllUniformLocations();

    protected int getUniformLocation(String uniformName) {
        return GL20.glGetUniformLocation(programID, uniformName);
    }

    /**
     * Self explanatory.
     * @param location
     * @param value
     */
    protected void loadFloat(int location, float value) {
        GL20.glUniform1f(location, value);
    }

    /**
     * Self explanatory.
     * @param location
     * @param vector
     */
    protected void loadVector(int location, Vector3f vector) {
        GL20.glUniform3f(location, vector.x, vector.y, vector.z);
    }

    /**
     * Self explanatory.
     * @param location
     * @param value
     */
    protected void loadBoolean(int location, boolean value) {
        float toLoad = 0;
        if (value) {
            toLoad = 1;
        }
        GL20.glUniform1f(location, toLoad);
    }

    protected void loadMatrix(int location, Matrix4f matrix) {
        matrix.store(matrixBuffer);
        matrixBuffer.flip();
        GL20.glUniformMatrix4(location, false, matrixBuffer);
    }

    /**
     * Starts shader program.
     */
    public void start() {
        GL20.glUseProgram(programID);
    }

    /**
     * Stops the shader program.
     */
    public void stop() {
        GL20.glUseProgram(0);
    }

    /**
     * Cleans up shaders from memory:
     * Stops the program,
     * detach shaders,
     * delete shaders,
     * delete whole program.
     */
    public void cleanUp() {
        stop();
        GL20.glDetachShader(programID, vertexShaderID);
        GL20.glDetachShader(programID, fragmentShaderID);
        GL20.glDeleteShader(vertexShaderID);
        GL20.glDeleteShader(fragmentShaderID);
        GL20.glDeleteProgram(programID);
    }

    protected abstract void bindAttributes();

    /**
     * Binds an attribute
     * @param attribute
     * @param variableName
     */
    protected void bindAttribute(int attribute, String variableName) {
        GL20.glBindAttribLocation(programID, attribute, variableName);
    }

    /**
     * Loads a shader file (vertex/fragment) return ID of newly created shader.
     */
    private static int loadShader(String file, int type) {
        StringBuilder shaderSource = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                shaderSource.append(line).append("\n");
            }
            reader.close();

        } catch (IOException e) {
            System.err.println("Could not read file");
            e.printStackTrace();
            System.exit(-1);
        }
        int shaderID = GL20.glCreateShader(type);
        GL20.glShaderSource(shaderID, shaderSource);
        GL20.glCompileShader(shaderID);
        if (GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
            System.out.println(GL20.glGetShaderInfoLog(shaderID, 500));
            System.err.println("Could not compile shader");
            System.exit(-1);
        }
        return shaderID;
    }
}
