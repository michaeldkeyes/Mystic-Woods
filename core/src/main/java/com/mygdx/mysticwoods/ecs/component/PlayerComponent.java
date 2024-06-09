package com.mygdx.mysticwoods.ecs.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class PlayerComponent implements Component, Pool.Poolable {

    public static final String IDLE = "idle";
    public static final String WALK = "walk";
    public static final String ATTACK = "attack";
    public static final String DEAD = "dead";

    public static String getRegionName(final String state) {
        return "player/" + state;
    }

    @Override
    public void reset() {
    }
}
