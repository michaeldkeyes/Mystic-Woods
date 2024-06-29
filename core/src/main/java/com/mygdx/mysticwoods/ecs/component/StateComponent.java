package com.mygdx.mysticwoods.ecs.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.utils.Pool;
import com.mygdx.mysticwoods.ecs.State;

public class StateComponent implements Component, Pool.Poolable {

    public static final ComponentMapper<StateComponent> MAPPER = ComponentMapper.getFor(StateComponent.class);

    private State state = State.IDLE;

    public float time = 0f;

    public void setState(final State newState) {
        state = newState;
        time = 0f;
    }

    public State getState() {
        return state;
    }

    @Override
    public void reset() {
        state = State.IDLE;
        time = 0f;
    }
}
