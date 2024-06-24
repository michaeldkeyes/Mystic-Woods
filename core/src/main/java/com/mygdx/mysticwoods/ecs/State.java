package com.mygdx.mysticwoods.ecs;

public enum State {

    IDLE("idle"),
    WALK("walk"),
    ATTACK("attack"),
    DEAD("dead");

    private final String stateString;

    State(final String state) {
        this.stateString = state;
    }

    public String getState() {
        return stateString;
    }

}
