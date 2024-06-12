package com.mygdx.mysticwoods;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;

public class Assets {

    private static final String TAG = Assets.class.getSimpleName();

    private static final String ATLAS = "graphics/mystic-woods.atlas";
    private static final String MAP = "graphics/map/map1.tmx";

    private final AssetManager assetManager;

    public Assets(final AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    public void load() {
        assetManager.load(ATLAS, TextureAtlas.class);

        assetManager.setLoader(TiledMap.class, new TmxMapLoader(assetManager.getFileHandleResolver()));
        assetManager.load(MAP, TiledMap.class);

        assetManager.finishLoading();
    }

    public Image createImage(final String regionName) {
        if (!assetManager.isLoaded(ATLAS)) {
            Gdx.app.error(TAG, "Atlas not loaded");
        }

        final TextureAtlas textureAtlas = assetManager.get(ATLAS, TextureAtlas.class);
        final TextureAtlas.AtlasRegion region = textureAtlas.findRegion(regionName);

        return new Image(region);
    }

    public Animation<TextureRegionDrawable> createAnimation(final String regionName) {
        if (!assetManager.isLoaded(ATLAS)) {
            Gdx.app.error(TAG, "Atlas not loaded");
        }

        final TextureAtlas textureAtlas = assetManager.get(ATLAS, TextureAtlas.class);
        final Array<TextureAtlas.AtlasRegion> atlasRegions = textureAtlas.findRegions(regionName);
        final Array<TextureRegionDrawable> drawables = new Array<>(atlasRegions.size);
        for (final TextureAtlas.AtlasRegion region : new Array.ArrayIterable<>(atlasRegions)) {
            drawables.add(new TextureRegionDrawable(region));
        }

        return new Animation<>(1 / 8f, drawables, Animation.PlayMode.LOOP);
    }

    public TiledMap loadMap() {
        if (!assetManager.isLoaded(MAP)) {
            Gdx.app.error(TAG, "Map not loaded");
        }

        return assetManager.get(MAP);
    }

    public void dispose() {
        assetManager.dispose();
    }


}
