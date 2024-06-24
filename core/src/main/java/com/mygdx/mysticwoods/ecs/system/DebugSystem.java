package com.mygdx.mysticwoods.ecs.system;

import com.badlogic.ashley.systems.IntervalSystem;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;

public class DebugSystem extends IntervalSystem implements Disposable {

    private final Box2DDebugRenderer debugRenderer;
    private OrthographicCamera camera;
    private final World world;

    public DebugSystem(final World world) {
        super(1 / 60f);

        this.debugRenderer = new Box2DDebugRenderer();
        this.world = world;
    }

    @Override
    protected void updateInterval() {
        debugRenderer.render(world, camera.combined);
    }

    @Override
    public void dispose() {
        debugRenderer.dispose();
    }

    public void setCamera(final Camera camera) {
        this.camera = (OrthographicCamera) camera;
    }
}
