package com.mygdx.mysticwoods.ecs.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Pool;

public class PhysicsComponent implements Component, Pool.Poolable {

    public static final ComponentMapper<PhysicsComponent> MAPPER = ComponentMapper.getFor(PhysicsComponent.class);

    public Body body = null;
    public Vector2 velocity = new Vector2(0, 0);

    @Override
    public void reset() {
        body = null;
        velocity.set(0, 0);
    }
}
