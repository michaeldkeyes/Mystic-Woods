package com.mygdx.mysticwoods.ecs.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalIteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.mysticwoods.ecs.component.ImageComponent;
import com.mygdx.mysticwoods.ecs.component.PhysicsComponent;

public class PhysicsSystem extends IntervalIteratingSystem implements EntityListener {

    private static final String TAG = PhysicsSystem.class.getSimpleName();

    private static final Family FAMILY = Family.all(ImageComponent.class, PhysicsComponent.class).get();
    private static final float TIME_STEP = 1 / 60f;

    private final World world;

    public PhysicsSystem(final World world) {
        super(FAMILY, TIME_STEP);

        this.world = world;
    }

    @Override
    public void addedToEngine(final Engine engine) {
        super.addedToEngine(engine);

        engine.addEntityListener(FAMILY, this);
    }

    @Override
    public void removedFromEngine(final Engine engine) {
        super.removedFromEngine(engine);

        engine.removeEntityListener(this);
    }

    @Override
    public void entityAdded(final Entity entity) {
        final PhysicsComponent physicsComponent = PhysicsComponent.MAPPER.get(entity);

        if (physicsComponent.body != null) {
            physicsComponent.body.setUserData(entity);
        }
    }

    @Override
    public void entityRemoved(final Entity entity) {
        final PhysicsComponent physicsComponent = PhysicsComponent.MAPPER.get(entity);

        if (physicsComponent.body != null) {
            world.destroyBody(physicsComponent.body);
            physicsComponent.body.setUserData(null);
        }
    }

    @Override
    public void update(final float deltaTime) {
        if (world.getAutoClearForces()) {
            Gdx.app.error(TAG, "AutoClearForces is enabled. This will cause issues with the physics system.");

            world.setAutoClearForces(false);
        }

        super.update(deltaTime);

        world.clearForces();
    }

    @Override
    protected void updateInterval() {
        super.updateInterval();

        world.step(TIME_STEP, 6, 2);
    }

    @Override
    protected void processEntity(final Entity entity) {
        final PhysicsComponent physicsComponent = PhysicsComponent.MAPPER.get(entity);
        final ImageComponent imageComponent = ImageComponent.MAPPER.get(entity);

        physicsComponent.body.setLinearVelocity(physicsComponent.velocity);

        if (physicsComponent.body != null && imageComponent.image != null) {
            imageComponent.image.setPosition(physicsComponent.body.getPosition().x - imageComponent.image.getWidth() * 0.5f,
                physicsComponent.body.getPosition().y - imageComponent.image.getHeight() * 0.5f);
        }
    }

}
