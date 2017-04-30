package com.zou50.bouncycow.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Danny on 4/30/17.
 */
public class PlayState extends State {

    private Texture cow;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        cow = new Texture("android/assets/cow.png");
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(cow, 50, 50);
        sb.end();
    }

    @Override
    public void dispose() {

    }

}
