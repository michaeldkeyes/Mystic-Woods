package com.mygdx.mysticwoods;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.mysticwoods.ecs.system.AnimationSystem;
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
    private MapManager mapManager;
    private Viewport viewport;

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        assets = new Assets(new AssetManager());
        engine = new PooledEngine();
        mapManager = new MapManager(assets, engine);
        viewport = new ExtendViewport(WORLD_WIDTH, WORLD_HEIGHT);

        engine.addSystem(new AnimationSystem());
        engine.addSystem(new RenderSystem());

        setScreen(new GameScreen(this));
    }

    @Override
    public void resize(final int width, final int height) {
        viewport.update(width, height, true);
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
