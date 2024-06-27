package com.mygdx.mysticwoods.ecs.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.mysticwoods.ecs.component.PhysicsComponent;
import com.mygdx.mysticwoods.ecs.component.PlayerComponent;

public class PlayerMovementSystem extends IteratingSystem {

    private static final Family FAMILY = Family.all(PlayerComponent.class, PhysicsComponent.class).get();

    private final Vector2 velocity;

    public PlayerMovementSystem() {
        super(FAMILY);

        velocity = new Vector2();
    }

    @Override
    protected void processEntity(final Entity entity, final float deltaTime) {
        final PhysicsComponent physicsComponent = PhysicsComponent.MAPPER.get(entity);
        final PlayerComponent playerComponent = PlayerComponent.MAPPER.get(entity);

        physicsComponent.velocity.set(0, 0);

        if (playerComponent.isMovingLeft) {
            physicsComponent.velocity.x = -1;
        }

        if (playerComponent.isMovingRight) {
            physicsComponent.velocity.x = 1;
        }

        if (playerComponent.isMovingUp) {
            physicsComponent.velocity.y = 1;
        }

        if (playerComponent.isMovingDown) {
            physicsComponent.velocity.y = -1;
        }

        physicsComponent.velocity.nor().scl(350 * deltaTime);

    }
}
