package com.zou50.bouncycow.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zou50.bouncycow.BCGame;

/**
 * Created by Danny on 4/30/17.
 */
public class PlayState extends State {

    private Texture cow;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        cow = new Texture("android/assets/cow.png");
        cam.setToOrtho(false, BCGame.WIDTH / 2, BCGame.HEIGHT / 2);
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(cow, 50, 50);
        sb.end();
    }

    @Override
    public void dispose() {

    }

}
