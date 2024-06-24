package com.mygdx.mysticwoods;

import com.badlogic.gdx.physics.box2d.*;

public class PhysicsBodyFactory {

    private final World world;

    public PhysicsBodyFactory(final World world) {
        this.world = world;
    }

    public Body createBody(final BodyDef.BodyType bodyType, final float x, final float y, final float width,
                           final float height,
                           final boolean isSensor) {
        final BodyDef bodyDef = new BodyDef();
        bodyDef.type = bodyType;
        bodyDef.position.set(x + (width * 0.5f), y + (height * 0.5f));
        bodyDef.fixedRotation = true;
        bodyDef.allowSleep = false;

        final Body body = world.createBody(bodyDef);

        final PolygonShape shape = new PolygonShape();
        shape.setAsBox(width * 0.5f, height * 0.5f);

        final FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.isSensor = isSensor;

        body.createFixture(fixtureDef);

        shape.dispose();

        return body;
    }

//public static Body createBody(World world, BodyDef.BodyType type, float x, float y, float width, float height, boolean isSensor) {
//        BodyDef bodyDef = new BodyDef();
//        bodyDef.type = type;
//        bodyDef.position.set(x + width / 2, y + height / 2);
//        Body body = world.createBody(bodyDef);
//        PolygonShape shape = new PolygonShape();
//        shape.setAsBox(width / 2, height / 2);
//        FixtureDef fixtureDef = new FixtureDef();
//        fixtureDef.shape = shape;
//        fixtureDef.isSensor = isSensor;
//        body.createFixture(fixtureDef);
//        shape.dispose();
//        return body;
//    }
//
//    public static Body createPlayerBody(World world, float x, float y) {
//        return createBody(world, BodyDef.BodyType.DynamicBody, x, y, 1, 1, false);
//    }
//
//    public static Body createSlimeBody(World world, float x, float y) {
//        return createBody(world, BodyDef.BodyType.DynamicBody, x, y, 1, 1, false);
//    }
//
//    public static Body createGroundBody(World world, float x, float y, float width, float height) {
//        return createBody(world, BodyDef.BodyType.StaticBody, x, y, width, height, false);
//    }
//
//    public static Body createSensor(World world, float x, float y, float width, float height) {
//        return createBody(world, BodyDef.BodyType.StaticBody, x, y, width, height, true);
//    }
//
//    public static void setPhysicsComponent(Entity entity, Body body) {
//        PhysicsComponent physicsComponent = new PhysicsComponent();
//        physicsComponent.body = body;
//        entity.add(physicsComponent);
//    }
//
//    public static void setPhysicsComponent(Entity entity, Body body, float width, float height) {
//        PhysicsComponent physicsComponent = new PhysicsComponent();
//        physicsComponent.body = body;
//        physicsComponent.width = width;
//        physicsComponent.height = height;
//        entity.add(physicsComponent);
//    }
//
//    public static void setPhysicsComponent(Entity entity, Body body, float width, float height, float offsetX, float offsetY) {
//        PhysicsComponent physicsComponent = new PhysicsComponent();
//        physicsComponent.body = body;
//        physicsComponent.width = width;
//        physicsComponent.height = height;
//        physicsComponent.offsetX =
}
