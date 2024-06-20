package com.mygdx.mysticwoods;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.mygdx.mysticwoods.ecs.EntityFactory;

import static com.mygdx.mysticwoods.MysticWoods.UNIT_SCALE;

public class MapManager {

    private static final String TAG = MapManager.class.getSimpleName();

    private static final String ENTITY_LAYER = "entities";

    private final Assets assets;
    private final EntityFactory entityFactory;

    public MapManager(final Assets assets, final EntityFactory entityFactory) {
        this.assets = assets;
        this.entityFactory = entityFactory;
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

            final float x = object.getProperties().get("x", Float.class) * UNIT_SCALE;
            final float y = object.getProperties().get("y", Float.class) * UNIT_SCALE;

            // Create entity
            if (object.getName().equals("player")) {
                entityFactory.createPlayer(x, y);
            } else if (object.getName().equals("slime")) {
                entityFactory.createSlime(x, y);
            } else {
                Gdx.app.error(TAG, "Unknown object: " + object.getName());
            }

        });

        return map;
    }
}
