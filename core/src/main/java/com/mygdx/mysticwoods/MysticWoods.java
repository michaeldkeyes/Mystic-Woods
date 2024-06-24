package com.mygdx.mysticwoods;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.mysticwoods.ecs.EntityFactory;
import com.mygdx.mysticwoods.ecs.system.AnimationSystem;
import com.mygdx.mysticwoods.ecs.system.DebugSystem;
import com.mygdx.mysticwoods.ecs.system.PhysicsSystem;
import com.mygdx.mysticwoods.ecs.system.RenderSystem;
import com.mygdx.mysticwoods.screen.GameScreen;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class MysticWoods extends Game {

    public static final float UNIT_SCALE = 1 / 16f;
    public static final float WORLD_WIDTH = 16f;
    public static final float WORLD_HEIGHT = 9f;

    private Assets assets;
    private Engine engine;
    private EntityFactory entityFactory;
    private MapManager mapManager;
    private World world;
    private Viewport viewport;

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        assets = new Assets(new AssetManager());
        engine = new PooledEngine();
        world = new World(new Vector2(0, 0), true);
        world.setAutoClearForces(false);
        entityFactory = new EntityFactory(assets, engine, world);
        mapManager = new MapManager(assets, entityFactory);
        viewport = new ExtendViewport(WORLD_WIDTH, WORLD_HEIGHT);

        engine.addSystem(new PhysicsSystem(world));
        engine.addSystem(new AnimationSystem());
        engine.addSystem(new RenderSystem());
        engine.addSystem(new DebugSystem(world));

        setScreen(new GameScreen(this));
    }

    @Override
    public void resize(final int width, final int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void dispose() {
        super.dispose();
        assets.dispose();
        world.dispose();
    }

    public Assets getAssets() {
        return assets;
    }

    public Engine getEngine() {
        return engine;
    }

    public MapManager getMapManager() {
        return mapManager;
    }

    public Viewport getViewport() {
        return viewport;
    }
}
