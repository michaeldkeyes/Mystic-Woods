package com.mygdx.mysticwoods.screen;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
    private final TextureAtlas textureAtlas;

    public GameScreen(final MysticWoods game) {
        this.assetManager = game.getAssetManager();
        this.engine = game.getEngine();
        stage = new Stage(game.getViewport());

        assetManager.load("graphics/mystic-woods.atlas", TextureAtlas.class);
        assetManager.finishLoading();

        textureAtlas = assetManager.get("graphics/mystic-woods.atlas", TextureAtlas.class);

        engine.getSystem(RenderSystem.class).setStage(stage);
    }

    @Override
    public void show() {
        Gdx.app.debug(TAG, "show");

        final Entity player = engine.createEntity();
        final ImageComponent imageComponent = engine.createComponent(ImageComponent.class);
        imageComponent.image = new Image(new TextureRegion(textureAtlas.findRegion("player"), 0, 48, 48, 48));
        imageComponent.image.setSize(4, 4);
        player.add(imageComponent);

        final Entity slime = engine.createEntity();
        final ImageComponent slimeImageComponent = engine.createComponent(ImageComponent.class);
        slimeImageComponent.image = new Image(new TextureRegion(textureAtlas.findRegion("slime"), 0, 32, 32, 32));
        slimeImageComponent.image.setSize(4, 4);
        slimeImageComponent.image.setPosition(12, 0);
        slime.add(slimeImageComponent);

        engine.addEntity(player);
        engine.addEntity(slime);
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
