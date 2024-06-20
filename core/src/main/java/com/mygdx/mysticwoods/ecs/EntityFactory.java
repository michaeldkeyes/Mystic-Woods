package com.mygdx.mysticwoods.ecs;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.mygdx.mysticwoods.Assets;
import com.mygdx.mysticwoods.ecs.component.*;

public class EntityFactory {

    private final Assets assets;
    private final Engine engine;

    public EntityFactory(final Assets assets, final Engine engine) {
        this.assets = assets;
        this.engine = engine;
    }

    public void createPlayer(final float x, final float y) {
        final Entity player = engine.createEntity();

        final PlayerComponent playerComponent = engine.createComponent(PlayerComponent.class);

        final StateComponent stateComponent = engine.createComponent(StateComponent.class);
        stateComponent.setState(PlayerComponent.IDLE);
        stateComponent.setDirection("right");

        final ImageComponent imageComponent = engine.createComponent(ImageComponent.class);
        imageComponent.image = assets.createImage("player/" + stateComponent.get());
        imageComponent.image.setSize(3f, 3f);
        imageComponent.image.setPosition(x, y);

        final AnimationComponent animationComponent = engine.createComponent(AnimationComponent.class);
        animationComponent.animations.put(stateComponent.get(), assets.createAnimation("player/" + stateComponent.get()));

        player.add(imageComponent);
        player.add(playerComponent);
        player.add(stateComponent);
        player.add(animationComponent);

        engine.addEntity(player);
    }

    public void createSlime(final float x, final float y) {
        final Entity slime = engine.createEntity();

        final SlimeComponent slimeComponent = engine.createComponent(SlimeComponent.class);

        final StateComponent slimeStateComponent = engine.createComponent(StateComponent.class);
        slimeStateComponent.setState(SlimeComponent.IDLE);
        slimeStateComponent.setDirection("right");

        final ImageComponent slimeImageComponent = engine.createComponent(ImageComponent.class);
        slimeImageComponent.image = assets.createImage("slime/" + slimeStateComponent.get());
        slimeImageComponent.image.setSize(2f, 2f);
        slimeImageComponent.image.setPosition(x, y);

        final AnimationComponent slimeAnimationComponent = engine.createComponent(AnimationComponent.class);

        slimeAnimationComponent.animations.put(slimeStateComponent.get(), assets.createAnimation("slime/" + slimeStateComponent.get()));

        slime.add(slimeImageComponent);
        slime.add(slimeComponent);
        slime.add(slimeStateComponent);
        slime.add(slimeAnimationComponent);

        engine.addEntity(slime);
    }

}
