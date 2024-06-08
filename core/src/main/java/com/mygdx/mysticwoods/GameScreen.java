package com.mygdx.mysticwoods;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * First screen of the application. Displayed after the application is created.
 */
public class GameScreen extends ScreenAdapter {

    private static final String TAG = GameScreen.class.getSimpleName();

    private final AssetManager assetManager;
    private final Stage stage;
    private final Texture texture;
    private final Viewport viewport;

    public GameScreen(final MysticWoods game) {
        this.assetManager = game.getAssetManager();
        this.viewport = game.getViewport();
        stage = new Stage(viewport);

        assetManager.load("graphics/player.png", Texture.class);
        assetManager.finishLoading();

        this.texture = assetManager.get("graphics/player.png", Texture.class);
    }

    @Override
    public void show() {
        Gdx.app.debug(TAG, "show");

        final Image playerImage = new Image(texture);
        playerImage.setPosition(1, 1);
        playerImage.setSize(1, 1);
        playerImage.setScaling(Scaling.fill);

        stage.addActor(playerImage);
    }

    @Override
    public void render(final float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        assetManager.dispose();
    }
}
