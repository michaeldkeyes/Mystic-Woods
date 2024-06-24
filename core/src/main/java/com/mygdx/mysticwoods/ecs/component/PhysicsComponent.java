package com.mygdx.mysticwoods.ecs.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Pool;

public class PhysicsComponent implements Component, Pool.Poolable {

    public static final ComponentMapper<PhysicsComponent> MAPPER = ComponentMapper.getFor(PhysicsComponent.class);

    public Body body = null;

    @Override
    public void reset() {
        body = null;
    }
}
