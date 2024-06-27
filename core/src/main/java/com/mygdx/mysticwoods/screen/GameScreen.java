package com.mygdx.mysticwoods.screen;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.mysticwoods.Assets;
import com.mygdx.mysticwoods.MapManager;
import com.mygdx.mysticwoods.MysticWoods;
import com.mygdx.mysticwoods.ecs.component.PlayerComponent;
import com.mygdx.mysticwoods.ecs.system.DebugSystem;
import com.mygdx.mysticwoods.ecs.system.RenderSystem;
import com.mygdx.mysticwoods.input.PlayerInputProcessor;

public class GameScreen extends ScreenAdapter {

    private static final String TAG = GameScreen.class.getSimpleName();

    private final Assets assets;
    private final Engine engine;
    private final MapManager mapManager;
    private final Stage stage;

    public GameScreen(final MysticWoods game) {
        this.assets = game.getAssets();
        this.engine = game.getEngine();
        this.mapManager = game.getMapManager();
        stage = new Stage(game.getViewport());

        assets.load();

        engine.getSystem(RenderSystem.class).setStage(stage);
        engine.getSystem(DebugSystem.class).setCamera(stage.getCamera());

        final TiledMap map = mapManager.setUpMap();

        setUpInputProcessor();

        engine.getSystem(RenderSystem.class).setMap(map);
    }

    @Override
    public void show() {
        Gdx.app.debug(TAG, "show");
    }

    @Override
    public void render(final float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }

        // limit the delta time
        engine.update(Math.min(delta, 0.25f));
    }

    @Override
    public void dispose() {
        stage.dispose();
        assets.dispose();
    }

    private void setUpInputProcessor() {
        Entity player = engine.getEntitiesFor(Family.all(PlayerComponent.class).get()).first();
        if (player == null) {
            Gdx.app.error(TAG, "Player entity not found");
            return;
        }
        final PlayerComponent playerComponent = PlayerComponent.MAPPER.get(player);

         final PlayerInputProcessor playerInputProcessor = new PlayerInputProcessor(playerComponent);
         Gdx.input.setInputProcessor(playerInputProcessor);
    }
}
