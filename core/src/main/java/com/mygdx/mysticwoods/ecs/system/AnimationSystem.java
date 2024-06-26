package com.mygdx.mysticwoods.ecs.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.mysticwoods.ecs.component.AnimationComponent;
import com.mygdx.mysticwoods.ecs.component.ImageComponent;
import com.mygdx.mysticwoods.ecs.component.StateComponent;

public class AnimationSystem extends IteratingSystem {

    private static final Family FAMILY = Family.all(AnimationComponent.class, StateComponent.class, ImageComponent.class).get();

    public AnimationSystem() {
        super(FAMILY);
    }

    @Override
    public void processEntity(final Entity entity, final float deltaTime) {
        final AnimationComponent animationComponent = AnimationComponent.MAPPER.get(entity);
        final StateComponent stateComponent = StateComponent.MAPPER.get(entity);

        @SuppressWarnings("unchecked")
        final Animation<TextureRegionDrawable> animation = animationComponent.animations.get(stateComponent.get());

        if (animation == null) {
            Gdx.app.debug("AnimationSystem", "No animation found for state: " + stateComponent.get());
            return;
        }

        final ImageComponent imageComponent = ImageComponent.MAPPER.get(entity);

        final TextureRegionDrawable textureRegionDrawable = animation.getKeyFrame(stateComponent.time);
        imageComponent.image.setDrawable(textureRegionDrawable);

        stateComponent.time += deltaTime;
    }
}
