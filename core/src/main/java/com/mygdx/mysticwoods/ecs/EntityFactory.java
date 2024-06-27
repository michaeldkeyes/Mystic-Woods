package com.mygdx.mysticwoods.ecs;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.mysticwoods.Assets;
import com.mygdx.mysticwoods.PhysicsBodyFactory;
import com.mygdx.mysticwoods.ecs.component.*;

public class EntityFactory {

    private final Assets assets;
    private final Engine engine;
    private final PhysicsBodyFactory physicsBodyFactory;

    public EntityFactory(final Assets assets, final Engine engine, final World world) {
        this.assets = assets;
        this.engine = engine;
        this.physicsBodyFactory = new PhysicsBodyFactory(world);
    }

    public void createPlayer(final float x, final float y) {
        final Entity player = engine.createEntity();

        final PlayerComponent playerComponent = engine.createComponent(PlayerComponent.class);
        player.add(playerComponent);

        final StateComponent stateComponent = createStateComponent(player, State.IDLE, Direction.RIGHT);

        final ImageComponent imageComponent = createImageComponent(
            player,
            "player/" + stateComponent.get(),
            x, y,
            3f, 3f
        );

        createPhysicsComponent(player, x, y, imageComponent.image.getWidth(), imageComponent.image.getHeight());

        createAnimationComponent(player, stateComponent.get(), "player/" + stateComponent.get());

        engine.addEntity(player);
    }

    public void createSlime(final float x, final float y) {
        final Entity slime = engine.createEntity();

        final StateComponent slimeStateComponent = createStateComponent(slime, State.IDLE, Direction.RIGHT);

        final ImageComponent slimeImageComponent = createImageComponent(
            slime,
            "slime/" + slimeStateComponent.get(),
            x, y,
            2f, 2f
        );

        createPhysicsComponent(slime, x, y, slimeImageComponent.image.getWidth(), slimeImageComponent.image.getHeight());

        createAnimationComponent(slime, slimeStateComponent.get(), "slime/" + slimeStateComponent.get());

        engine.addEntity(slime);
    }

    private StateComponent createStateComponent(final Entity entity, final State state, final Direction direction) {
        final StateComponent stateComponent = engine.createComponent(StateComponent.class);
        stateComponent.setState(state);
        stateComponent.setDirection(direction);

        entity.add(stateComponent);

        return stateComponent;
    }

    private ImageComponent createImageComponent(final Entity entity, final String path, final float x, final float y,
                                                final float width, final float height
    ) {
        final ImageComponent imageComponent = engine.createComponent(ImageComponent.class);
        imageComponent.image = assets.createImage(path);
        imageComponent.image.setSize(width, height);
        imageComponent.image.setPosition(x, y);

        entity.add(imageComponent);

        return imageComponent;
    }

    private void createPhysicsComponent(final Entity entity, final float x, final float y, final float width, final float height) {
        final PhysicsComponent physicsComponent = engine.createComponent(PhysicsComponent.class);

        physicsComponent.body = physicsBodyFactory.createBody(BodyDef.BodyType.DynamicBody, x, y, width, height, false);

        entity.add(physicsComponent);
    }

    private void createAnimationComponent(final Entity entity, final String animationName, final String path) {
        final AnimationComponent animationComponent = engine.createComponent(AnimationComponent.class);
        animationComponent.animations.put(animationName, assets.createAnimation(path));

        entity.add(animationComponent);
    }

}
