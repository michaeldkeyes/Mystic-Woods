package com.mygdx.mysticwoods.ecs;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.mysticwoods.Assets;
import com.mygdx.mysticwoods.PhysicsBodyFactory;
import com.mygdx.mysticwoods.ecs.component.*;

public class EntityFactory {

    private static final String DEFAULT_PATH =
        State.IDLE.toString().toLowerCase() + "_" + Direction.RIGHT.toString().toLowerCase();

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

        final DirectionComponent directionComponent = engine.createComponent(DirectionComponent.class);
        player.add(directionComponent);

        createStateComponent(player, State.IDLE);

        final ImageComponent imageComponent = createImageComponent(
            player,
            "player/" + DEFAULT_PATH,
            x, y,
            3f, 3f
        );

        createPhysicsComponent(player, x, y, imageComponent.image.getWidth(), imageComponent.image.getHeight());

        createAnimationComponent(player, DEFAULT_PATH,
            "player/" + DEFAULT_PATH);

        engine.addEntity(player);
    }

    public void createSlime(final float x, final float y) {
        final Entity slime = engine.createEntity();

        createStateComponent(slime, State.IDLE);

        final DirectionComponent directionComponent = engine.createComponent(DirectionComponent.class);
        slime.add(directionComponent);

        final ImageComponent slimeImageComponent = createImageComponent(
            slime,
            "slime/" + DEFAULT_PATH,
            x, y,
            2f, 2f
        );

        createPhysicsComponent(slime, x, y, slimeImageComponent.image.getWidth(), slimeImageComponent.image.getHeight());

        createAnimationComponent(slime, DEFAULT_PATH, "slime/" + DEFAULT_PATH);

        engine.addEntity(slime);
    }

    private StateComponent createStateComponent(final Entity entity, final State state) {
        final StateComponent stateComponent = engine.createComponent(StateComponent.class);
        stateComponent.setState(state);

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
        animationComponent.animations.put("walk_right", assets.createAnimation("player/walk_right"));
        animationComponent.animations.put("walk_up", assets.createAnimation("player/walk_up"));
        animationComponent.animations.put("walk_down", assets.createAnimation("player/walk_down"));
        animationComponent.animations.put("idle_up", assets.createAnimation("player/idle_up"));
        animationComponent.animations.put("idle_down", assets.createAnimation("player/idle_down"));

        entity.add(animationComponent);
    }

}
