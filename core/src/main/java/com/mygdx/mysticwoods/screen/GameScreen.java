package com.mygdx.mysticwoods.screen;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.mysticwoods.Assets;
import com.mygdx.mysticwoods.MysticWoods;
import com.mygdx.mysticwoods.ecs.component.*;
import com.mygdx.mysticwoods.ecs.system.RenderSystem;

public class GameScreen extends ScreenAdapter {

    private static final String TAG = GameScreen.class.getSimpleName();

    private final Assets assets;
    private final Engine engine;
    private final Stage stage;

    public GameScreen(final MysticWoods game) {
        this.assets = game.getAssets();
        this.engine = game.getEngine();
        stage = new Stage(game.getViewport());

        assets.loadAtlas();

        engine.getSystem(RenderSystem.class).setStage(stage);
    }

    @Override
    public void show() {
        Gdx.app.debug(TAG, "show");

        final Entity player = engine.createEntity();

        final PlayerComponent playerComponent = engine.createComponent(PlayerComponent.class);

        final StateComponent stateComponent = engine.createComponent(StateComponent.class);
        stateComponent.setState(PlayerComponent.IDLE);
        stateComponent.setDirection("right");

        final ImageComponent imageComponent = engine.createComponent(ImageComponent.class);
        imageComponent.image = assets.createImage("player/" + stateComponent.get());
        imageComponent.image.setSize(4, 4);

        final AnimationComponent animationComponent = engine.createComponent(AnimationComponent.class);
        animationComponent.animations.put(stateComponent.get(), assets.createAnimation("player/" + stateComponent.get()));

        player.add(imageComponent);
        player.add(playerComponent);
        player.add(stateComponent);
        player.add(animationComponent);

        final Entity slime = engine.createEntity();

        final SlimeComponent slimeComponent = engine.createComponent(SlimeComponent.class);

        final StateComponent slimeStateComponent = engine.createComponent(StateComponent.class);
        slimeStateComponent.setState(SlimeComponent.IDLE);
        slimeStateComponent.setDirection("right");

        final ImageComponent slimeImageComponent = engine.createComponent(ImageComponent.class);
        slimeImageComponent.image = assets.createImage("slime/" + slimeStateComponent.get());
        slimeImageComponent.image.setSize(2.64f, 2.64f);
        slimeImageComponent.image.setPosition(12, 0);

        final AnimationComponent slimeAnimationComponent = engine.createComponent(AnimationComponent.class);

        slimeAnimationComponent.animations.put(slimeStateComponent.get(), assets.createAnimation("slime/" + slimeStateComponent.get()));

        slime.add(slimeImageComponent);
        slime.add(slimeComponent);
        slime.add(slimeStateComponent);
        slime.add(slimeAnimationComponent);

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
        assets.dispose();
    }
}
