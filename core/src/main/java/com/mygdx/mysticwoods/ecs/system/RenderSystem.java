package com.mygdx.mysticwoods.ecs.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.mysticwoods.ecs.component.ImageComponent;

import java.util.Comparator;

public class RenderSystem extends SortedIteratingSystem implements EntityListener {

    private static final Family FAMILY = Family.all(ImageComponent.class).get();

    private Stage stage;

    public RenderSystem() {
        super(FAMILY, new YComparator());

    }

    @Override
    public void addedToEngine(final Engine engine) {
        super.addedToEngine(engine);

        engine.addEntityListener(FAMILY, this);
    }

    @Override
    public void removedFromEngine(final Engine engine) {
        super.removedFromEngine(engine);

        engine.removeEntityListener(this);
    }

    @Override
    public void entityAdded(final Entity entity) {
        final ImageComponent imageComponent = ImageComponent.MAPPER.get(entity);

        if (imageComponent.image != null) {
            stage.addActor(imageComponent.image);
        }
    }

    @Override
    public void entityRemoved(final Entity entity) {
        final ImageComponent imageComponent = ImageComponent.MAPPER.get(entity);

        if (imageComponent.image != null) {
            stage.getRoot().removeActor(imageComponent.image);
        }
    }

    @Override
    public void update(final float deltaTime) {
        if (stage == null) {
            return;
        }

        super.update(deltaTime);

        forceSort();

        stage.getViewport().apply();
        stage.act(deltaTime);
        stage.draw();
    }

    @Override
    protected void processEntity(final Entity entity, final float deltaTime) {
    }

    public void setStage(final Stage stage) {
        this.stage = stage;
    }

    private static class YComparator implements Comparator<Entity> {

        @Override
        public int compare(final Entity entityA, final Entity entityB) {
            final ImageComponent imageComponentA = ImageComponent.MAPPER.get(entityA);
            final ImageComponent imageComponentB = ImageComponent.MAPPER.get(entityB);

            final int yDiff = (int) Math.signum(imageComponentB.image.getY() - imageComponentA.image.getY());

            if (yDiff == 0) {
                return (int) Math.signum(imageComponentA.image.getX() - imageComponentB.image.getX());
            } else {
                return yDiff;
            }
        }
    }
}
