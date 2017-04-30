package com.zou50.bouncycow.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zou50.bouncycow.BCGame;
import com.zou50.bouncycow.sprites.Cow;

/**
 * Created by Danny on 4/30/17.
 */
public class PlayState extends State {

    private Cow cow;
    private Texture bg;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        cow = new Cow(50, 300);
        cam.setToOrtho(false, BCGame.WIDTH / 2, BCGame.HEIGHT / 2);
        bg = new Texture("android/assets/bg.png");
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched())
            cow.jump();
    }

    @Override
    public void update(float dt) {
        handleInput();
        cow.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, cam.position.x - (cam.viewportWidth / 2), 0);
        sb.draw(cow.getTexture(), cow.getPosition().x, cow.getPosition().y);
        sb.end();
    }

    @Override
    public void dispose() {

    }

}
