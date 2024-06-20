package com.mygdx.mysticwoods;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.mygdx.mysticwoods.ecs.component.*;

import static com.mygdx.mysticwoods.MysticWoods.UNIT_SCALE;

public class MapManager {

    private static final String TAG = MapManager.class.getSimpleName();

    private static final String ENTITY_LAYER = "entities";

    private final Assets assets;
    private final Engine engine;

    public MapManager(final Assets assets, final Engine engine) {
        this.assets = assets;
        this.engine = engine;
    }

    public TiledMap setUpMap() {
        TiledMap map = assets.loadMap();

        final MapLayer entitiesLayer = map.getLayers().get(ENTITY_LAYER);

        if (entitiesLayer == null) {
            throw new GdxRuntimeException("Map is missing entities layer");
        }

        entitiesLayer.getObjects().forEach(object -> {
            Gdx.app.log(TAG,
                "Object: " + object.getName() + object.getProperties().get("x", Float.class) + " " + object.getProperties().get(
                    "y"));

            // Create entity
            if (object.getName().equals("player")) {
                // Create player entity
                final Entity player = engine.createEntity();

                final PlayerComponent playerComponent = engine.createComponent(PlayerComponent.class);

                final StateComponent stateComponent = engine.createComponent(StateComponent.class);
                stateComponent.setState(PlayerComponent.IDLE);
                stateComponent.setDirection("right");

                final ImageComponent imageComponent = engine.createComponent(ImageComponent.class);
                imageComponent.image = assets.createImage("player/" + stateComponent.get());
                imageComponent.image.setSize(3, 3);
                imageComponent.image.setPosition(object.getProperties().get("x", Float.class) * UNIT_SCALE,
                    object.getProperties().get("y", Float.class) * UNIT_SCALE);

                final AnimationComponent animationComponent = engine.createComponent(AnimationComponent.class);
                animationComponent.animations.put(stateComponent.get(), assets.createAnimation("player/" + stateComponent.get()));

                player.add(imageComponent);
                player.add(playerComponent);
                player.add(stateComponent);
                player.add(animationComponent);

                engine.addEntity(player);
            } else if (object.getName().equals("slime")) {
                Gdx.app.log(TAG, "Creating slime");
                final Entity slime = engine.createEntity();

                final SlimeComponent slimeComponent = engine.createComponent(SlimeComponent.class);

                final StateComponent slimeStateComponent = engine.createComponent(StateComponent.class);
                slimeStateComponent.setState(SlimeComponent.IDLE);
                slimeStateComponent.setDirection("right");

                final ImageComponent slimeImageComponent = engine.createComponent(ImageComponent.class);
                slimeImageComponent.image = assets.createImage("slime/" + slimeStateComponent.get());
                slimeImageComponent.image.setSize(2f, 2f);
                slimeImageComponent.image.setPosition(object.getProperties().get("x", Float.class) * UNIT_SCALE,
                    object.getProperties().get("y", Float.class) * UNIT_SCALE);

                final AnimationComponent slimeAnimationComponent = engine.createComponent(AnimationComponent.class);

                slimeAnimationComponent.animations.put(slimeStateComponent.get(), assets.createAnimation("slime/" + slimeStateComponent.get()));

                slime.add(slimeImageComponent);
                slime.add(slimeComponent);
                slime.add(slimeStateComponent);
                slime.add(slimeAnimationComponent);

                engine.addEntity(slime);
            } else {
                Gdx.app.error(TAG, "Unknown object: " + object.getName());
            }

        });

        return map;
    }
}
