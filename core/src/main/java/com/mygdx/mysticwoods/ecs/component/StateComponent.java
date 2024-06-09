package com.mygdx.mysticwoods.ecs.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.utils.Pool;

public class StateComponent implements Component, Pool.Poolable {

    public static final ComponentMapper<StateComponent> MAPPER = ComponentMapper.getFor(StateComponent.class);

    private String state = "idle";
    private String direction = "right";
    public float time = 0f;

    public String get() {
        return state + "_" + direction;
    }

    public void setDirection(final String newDirection) {
        direction = newDirection;
    }

    public void setState(final String newState) {
        state = newState;
        time = 0f;
    }

    @Override
    public void reset() {
        state = "IDLE";
        time = 0f;
    }
}
