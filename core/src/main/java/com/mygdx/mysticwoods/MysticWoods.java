package com.mygdx.mysticwoods;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.mysticwoods.ecs.system.RenderSystem;
import com.mygdx.mysticwoods.screen.GameScreen;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class MysticWoods extends Game {

    public static final float WORLD_WIDTH = 16f;
    public static final float WORLD_HEIGHT = 9f;

    private AssetManager assetManager;
    private Engine engine;
    private Viewport viewport;

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        assetManager = new AssetManager();
        engine = new PooledEngine();
        viewport = new ExtendViewport(WORLD_WIDTH, WORLD_HEIGHT);

        engine.addSystem(new RenderSystem());

        setScreen(new GameScreen(this));
    }

    @Override
    public void resize(final int width, final int height) {
        viewport.update(width, height, true);
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public Engine getEngine() {
        return engine;
    }

    public Viewport getViewport() {
        return viewport;
    }
}
