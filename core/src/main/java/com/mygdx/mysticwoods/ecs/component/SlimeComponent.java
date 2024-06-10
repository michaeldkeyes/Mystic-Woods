package com.mygdx.mysticwoods.ecs.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class SlimeComponent implements Component, Pool.Poolable {

    public static final String IDLE = "idle";

    @Override
    public void reset() {
    }
}
