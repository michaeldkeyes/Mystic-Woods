package com.mygdx.mysticwoods.ecs;

public enum Direction {

    UP("up"),
    DOWN("down"),
    LEFT("left"),
    RIGHT("right");

    private final String directionString;

    Direction(final String direction) {
        this.directionString = direction;
    }

    public String getDirection() {
        return directionString;
    }
}
