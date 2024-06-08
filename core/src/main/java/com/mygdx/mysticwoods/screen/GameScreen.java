package com.mygdx.mysticwoods.screen;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.mysticwoods.MysticWoods;
import com.mygdx.mysticwoods.ecs.component.ImageComponent;
import com.mygdx.mysticwoods.ecs.system.RenderSystem;

/**
 * First screen of the application. Displayed after the application is created.
 */
public class GameScreen extends ScreenAdapter {

    private static final String TAG = GameScreen.class.getSimpleName();

    private final AssetManager assetManager;
    private final Engine engine;
    private final Stage stage;

    public GameScreen(final MysticWoods game) {
        this.assetManager = game.getAssetManager();
        this.engine = game.getEngine();
        stage = new Stage(game.getViewport());

        engine.getSystem(RenderSystem.class).setStage(stage);
    }

    @Override
    public void show() {
        Gdx.app.debug(TAG, "show");

        assetManager.load("graphics/player.png", Texture.class);
        assetManager.finishLoading();

        final Texture texture = assetManager.get("graphics/player.png", Texture.class);

        final Entity player = engine.createEntity();
        final ImageComponent imageComponent = engine.createComponent(ImageComponent.class);
        imageComponent.image = new Image(texture);
        imageComponent.image.setSize(4, 4);
        player.add(imageComponent);

        engine.addEntity(player);
    }

    @Override
    public void render(final float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }

        engine.update(delta);
    }

    @Override
    public void dispose() {
        stage.dispose();
        assetManager.dispose();
    }
}
