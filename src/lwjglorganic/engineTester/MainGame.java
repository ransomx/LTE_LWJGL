/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lwjglorganic.engineTester;

import Control.KeyboardControl;
import Control.MouseControl;
import Entities.Camera;
import Entities.Entity;
import Entities.EntityFactory;
import Entities.EntityObject;
import Entities.EntityObserver;
import Entities.Light;
import Entities.Player;
import guis.GuiRenderer;
import guis.GuiTexture;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import models.TexturedModel;
import renderEngine.DisplayManager;
import org.lwjgl.opengl.Display;

import org.lwjgl.util.vector.Vector3f;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.ModelFactory;
import renderEngine.ModelPreloader;
import renderEngine.OBJLoader;
import renderEngine.TerrainFactory;
import terrains.Terrain;
import textures.ModelTexture;
import toolBox.MousePicker;

/**
 *
 * @author Dominik
 */
public class MainGame {

    private List<Entity> entities;
    private Camera camera;
    private EntityFactory entityFactory;
    private List<Light> lights;

    private KeyboardControl keyboard;
    private MouseControl mouse;

    private Boolean stopped = false;

    private List<GuiTexture> guis;
    private GuiRenderer guiRenderer;
    private Loader loader;

    private Player player;
    private ModelFactory modelFactory;
    private TerrainFactory terrainFactory;

    private MousePicker picker;
    private EntityObserver ent;

    public MainGame(EntityObserver ent) {
        DisplayManager.createDisplay();
        loader = new Loader();
        guiRenderer = new GuiRenderer(loader);

        guis = new ArrayList<>();
        entities = new CopyOnWriteArrayList<>();
        lights = new CopyOnWriteArrayList<>();

        camera = new Camera();
        this.ent = ent;

        ModelPreloader preloader = new ModelPreloader(loader);
        entityFactory = new EntityFactory(preloader);
        modelFactory = new ModelFactory(preloader);
        terrainFactory = new TerrainFactory(preloader);
    }

    public EntityObserver getEnt() {
        return ent;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public EntityFactory getEntityFactory() {
        return entityFactory;
    }

    public MousePicker getPicker() {
        return picker;
    }

    public List<Light> getLights() {
        return lights;
    }
    private Terrain terrain;

    public void start() {
        MasterRenderer renderer = new MasterRenderer();
        terrain = terrainFactory.createObject(ModelPreloader.Model.TERRAIN_ICE);
        picker = new MousePicker(renderer.getProjectionMatrix4f(), camera, terrain, entities);

        keyboard = new KeyboardControl(this);
        mouse = new MouseControl(this);
        keyboard.start();
        mouse.start();
        
        Light l2 = getEntityFactory().createLight(new Vector3f(-10,10,0), new Vector3f(255f,180f,100f), new Vector3f(0.01f, 0.01f, 0.01f));
        getLights().add(l2);
        getEntities().add(getEntityFactory().createLightSource(l2, new Vector3f(-300,100,0)));
        getEntities().get(0).setScale(10f);

        EntityObject obj2 = entityFactory.createEntityObject(new TexturedModel(ModelPreloader.dragon),
                new Vector3f(-45, terrain.getHeightOfTerrain(-45, 12), 12));
        obj2.setScale(1f);
        entities.add(obj2);

        EntityObject obj3 = entityFactory.createEntityObject(new TexturedModel(OBJLoader.loadObjModel("object/bunny", loader), new ModelTexture(loader.loadTexture("object/bunnyUV"))),
                new Vector3f(-42, terrain.getHeightOfTerrain(-42, -58), -58));
        entities.add(obj3);
        obj3.setScale(3f);

        while (!Display.isCloseRequested()) {
            picker.update();
            camera.calculateMovement();
            for (Entity entity : entities) {
                entity.update();
            }

            Vector3f terrainPoint = picker.getCurrentTerrainPoint();
            if (terrainPoint != null) {

            }
            for (Entity entity : entities) {
                renderer.processTerrain(terrain);
                renderer.processEntity(entity);
            }
            renderer.render(lights, camera);
            guiRenderer.render(guis);
            DisplayManager.updateDisplay();
        }
        renderer.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();
        keyboard.interrupt();
        mouse.interrupt();
    }

    public Player getPlayer() {
        return player;
    }

    public Boolean isStopped() {
        return stopped;
    }

    public void setStopped(Boolean stopped) {
        this.stopped = stopped;
    }

    public void changeStopped() {
        this.stopped = !stopped;
    }
}
