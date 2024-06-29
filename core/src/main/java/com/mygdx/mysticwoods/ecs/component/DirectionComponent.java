package com.mygdx.mysticwoods.ecs.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.utils.Pool;
import com.mygdx.mysticwoods.ecs.Direction;

public class DirectionComponent implements Component, Pool.Poolable {

    public static final ComponentMapper<DirectionComponent> MAPPER = ComponentMapper.getFor(DirectionComponent.class);

    public boolean isUp = false;
    public boolean isDown = false;
    public boolean isLeft = false;
    public boolean isRight = false;
    private Direction direction = Direction.RIGHT;

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(final Direction direction) {
        this.direction = direction;
    }

    @Override
    public void reset() {
        isUp = false;
        isDown = false;
        isLeft = false;
        isRight = false;
        direction = Direction.RIGHT;
    }
}
