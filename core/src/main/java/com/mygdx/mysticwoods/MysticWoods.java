package com.mygdx.mysticwoods;

import com.badlogic.gdx.Game;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class MysticWoods extends Game {
    @Override
    public void create() {
        setScreen(new FirstScreen());
    }
}