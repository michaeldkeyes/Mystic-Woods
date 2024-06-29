package com.mygdx.mysticwoods.ecs.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.mygdx.mysticwoods.ecs.Direction;
import com.mygdx.mysticwoods.ecs.State;
import com.mygdx.mysticwoods.ecs.component.DirectionComponent;
import com.mygdx.mysticwoods.ecs.component.PhysicsComponent;
import com.mygdx.mysticwoods.ecs.component.PlayerComponent;
import com.mygdx.mysticwoods.ecs.component.StateComponent;

public class PlayerMovementSystem extends IteratingSystem {

    private static final Family FAMILY =
        Family.all(DirectionComponent.class, PlayerComponent.class, PhysicsComponent.class, StateComponent.class).get();

    public PlayerMovementSystem() {
        super(FAMILY);
    }

    @Override
    protected void processEntity(final Entity entity, final float deltaTime) {
        final DirectionComponent directionComponent = DirectionComponent.MAPPER.get(entity);
        final PhysicsComponent physicsComponent = PhysicsComponent.MAPPER.get(entity);
        final StateComponent stateComponent = StateComponent.MAPPER.get(entity);

        physicsComponent.velocity.set(0, 0);

        if (stateComponent.getState() == State.WALK) {
            if (directionComponent.isLeft) {
                physicsComponent.velocity.x = -1;
            }

            if (directionComponent.isRight) {
                physicsComponent.velocity.x = 1;
            }

            if (directionComponent.isUp) {
                physicsComponent.velocity.y = 1;
            }

            if (directionComponent.isDown) {
                physicsComponent.velocity.y = -1;
            }
        }

        // probably move this to the physics system
        physicsComponent.velocity.nor().scl(350 * deltaTime);

    }
}
