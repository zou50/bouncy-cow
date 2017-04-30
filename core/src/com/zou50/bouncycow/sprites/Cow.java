package com.zou50.bouncycow.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Danny on 4/30/17.
 */
public class Cow {

    private static final int GRAVITY = -15;
    private static final int MOVEMENT = 100;
    private Vector3 position;
    private Vector3 velocity;
    private Texture texture;
    private Animation cowAnimation;
    private Rectangle bounds;
    private Sound boing;

    public Cow(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        texture = new Texture("android/assets/cowframes.png");
        cowAnimation = new Animation(new TextureRegion(texture), 4, 0.5f);
        bounds = new Rectangle(x, y, texture.getWidth() / 4, texture.getHeight());
        boing = Gdx.audio.newSound(Gdx.files.internal("android/assets/boing.ogg"));
    }

    public void update(float dt) {
        cowAnimation.update(dt);
        if (position.y > 0)
            velocity.add(0, GRAVITY, 0);
        velocity.scl(dt);
        position.add(MOVEMENT * dt, velocity.y, 0);
        if (position.y < 0)
            position.y = 0;

        velocity.scl(1/dt);
        bounds.setPosition(position.x, position.y);
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getTexture() {
        return cowAnimation.getFrame();
    }

    public void jump() {
        velocity.y = 250;
        boing.play(0.5f);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void dispose() {
        texture.dispose();
        boing.dispose();
    }

}
