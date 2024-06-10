package com.mygdx.mysticwoods.ecs.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.Pool;

public class AnimationComponent implements Component, Pool.Poolable {

    public static final ComponentMapper<AnimationComponent> MAPPER = ComponentMapper.getFor(AnimationComponent.class);

    //public IntMap<Animation<TextureRegionDrawable>> animations = new IntMap<>();
    public ArrayMap<String, Animation> animations = new ArrayMap<>();

    @Override
    public void reset() {
        animations.clear();
    }
}
