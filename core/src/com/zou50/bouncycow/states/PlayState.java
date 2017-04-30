package com.zou50.bouncycow.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zou50.bouncycow.BCGame;
import com.zou50.bouncycow.sprites.Cow;
import com.zou50.bouncycow.sprites.Tube;

/**
 * Created by Danny on 4/30/17.
 */
public class PlayState extends State {

    private Cow cow;
    private Texture bg;
    private Tube tube;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        cow = new Cow(50, 300);
        cam.setToOrtho(false, BCGame.WIDTH / 2, BCGame.HEIGHT / 2);
        bg = new Texture("android/assets/bg.png");
        tube = new Tube(100);
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
        sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
        sb.draw(tube.getBottomTube(), tube.getPosBottomTube().x, tube.getPosBottomTube().y);
        sb.end();
    }

    @Override
    public void dispose() {

    }

}
