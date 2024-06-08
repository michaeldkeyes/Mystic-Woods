package com.mygdx.mysticwoods.ecs.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Pool;

public class ImageComponent implements Component, Pool.Poolable {

    public static final ComponentMapper<ImageComponent> MAPPER = ComponentMapper.getFor(ImageComponent.class);

    public Image image = null;

    @Override
    public void reset() {
        image = null;
    }
}
