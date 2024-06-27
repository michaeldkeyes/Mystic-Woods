package com.mygdx.mysticwoods.input;

import com.badlogic.gdx.InputAdapter;
import com.mygdx.mysticwoods.ecs.component.PlayerComponent;

import static com.badlogic.gdx.Input.Keys.*;

public class PlayerInputProcessor extends InputAdapter {

    private final PlayerComponent playerComponent;

    public PlayerInputProcessor(final PlayerComponent playerComponent) {
        this.playerComponent = playerComponent;
    }

    @Override
    public boolean keyDown(final int keycode) {
        switch (keycode) {
            case LEFT, A:
                playerComponent.isMovingLeft = true;
                break;
            case RIGHT, D:
                playerComponent.isMovingRight = true;
                break;
            case UP, W:
                playerComponent.isMovingUp = true;
                break;
            case DOWN, S:
                playerComponent.isMovingDown = true;
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
                playerComponent.isMovingLeft = false;
                break;
            case RIGHT, D:
                playerComponent.isMovingRight = false;
                break;
            case UP, W:
                playerComponent.isMovingUp = false;
                break;
            case DOWN, S:
                playerComponent.isMovingDown = false;
                break;
            default:
                return false;
        }

        return true;
    }

}
