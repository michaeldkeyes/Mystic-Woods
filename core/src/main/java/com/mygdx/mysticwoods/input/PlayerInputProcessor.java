package com.mygdx.mysticwoods.input;

import com.badlogic.gdx.InputAdapter;
import com.mygdx.mysticwoods.ecs.Direction;
import com.mygdx.mysticwoods.ecs.State;
import com.mygdx.mysticwoods.ecs.component.DirectionComponent;
import com.mygdx.mysticwoods.ecs.component.StateComponent;

import static com.badlogic.gdx.Input.Keys.*;

public class PlayerInputProcessor extends InputAdapter {

    private final DirectionComponent directionComponent;
    private final StateComponent stateComponent;

    public PlayerInputProcessor(final StateComponent stateComponent, final DirectionComponent directionComponent) {
        this.stateComponent = stateComponent;
        this.directionComponent = directionComponent;
    }

    @Override
    public boolean keyDown(final int keycode) {
        switch (keycode) {
            case LEFT, A:
                stateComponent.setState(State.WALK);
                directionComponent.isLeft = true;
                directionComponent.setDirection(Direction.LEFT);
                break;
            case RIGHT, D:
                stateComponent.setState(State.WALK);
                directionComponent.isRight = true;
                directionComponent.setDirection(Direction.RIGHT);
                break;
            case UP, W:
                stateComponent.setState(State.WALK);
                directionComponent.isUp = true;
                directionComponent.setDirection(Direction.UP);
                break;
            case DOWN, S:
                stateComponent.setState(State.WALK);
                directionComponent.isDown = true;
                directionComponent.setDirection(Direction.DOWN);
                break;
            default:
                return false;
        }

        return true;
    }

    @Override
    public boolean keyUp(final int keycode) {
        switch (keycode) {
            case LEFT, A:
                directionComponent.isLeft = false;
                break;
            case RIGHT, D:
                directionComponent.isRight = false;
                break;
            case UP, W:
                directionComponent.isUp = false;
                break;
            case DOWN, S:
                directionComponent.isDown = false;
                break;
            default:
                return false;
        }

        if (!directionComponent.isUp && !directionComponent.isDown && !directionComponent.isLeft && !directionComponent.isRight) {
            stateComponent.setState(State.IDLE);
        }

        return true;
    }

}
