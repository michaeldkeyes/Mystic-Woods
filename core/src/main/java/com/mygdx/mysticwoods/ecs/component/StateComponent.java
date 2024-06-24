package com.mygdx.mysticwoods.ecs.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.utils.Pool;
import com.mygdx.mysticwoods.ecs.Direction;
import com.mygdx.mysticwoods.ecs.State;

public class StateComponent implements Component, Pool.Poolable {

    public static final ComponentMapper<StateComponent> MAPPER = ComponentMapper.getFor(StateComponent.class);

    private State state = State.IDLE;
    private Direction direction = Direction.RIGHT;
    public float time = 0f;

    public String get() {
        return state.getState() + "_" + direction.getDirection();
    }

    public void setDirection(final Direction newDirection) {
        direction = newDirection;
    }

    public void setState(final State newState) {
        state = newState;
        time = 0f;
    }

    @Override
    public void reset() {
        state = State.IDLE;
        time = 0f;
    }
}
