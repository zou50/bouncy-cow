package com.zou50.bouncycow.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.zou50.bouncycow.BCGame;
import com.zou50.bouncycow.sprites.Cow;
import com.zou50.bouncycow.sprites.Tube;

/**
 * Created by Danny on 4/30/17.
 */
public class PlayState extends State {

    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;

    private Cow cow;
    private Texture bg;

    private Array<Tube> tubes;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        cow = new Cow(50, 300);
        cam.setToOrtho(false, BCGame.WIDTH / 2, BCGame.HEIGHT / 2);
        bg = new Texture("android/assets/bg.png");

        tubes = new Array<Tube>();
        for (int i = 1; i <= TUBE_COUNT; i++)
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));

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
        cam.position.x = cow.getPosition().x + 80;

        for (Tube tube : tubes) {
            if (cam.position.x - (cam.viewportWidth / 2) > tube.getPosTopTube().x + tube.getTopTube().getWidth())
                tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));

            if (tube.collides(cow.getBounds()))
                gsm.set(new PlayState(gsm));
        }

        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, cam.position.x - (cam.viewportWidth / 2), 0);
        sb.draw(cow.getTexture(), cow.getPosition().x, cow.getPosition().y);
        for (Tube tube : tubes) {
            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBotTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
        }
        sb.end();
    }

    @Override
    public void dispose() {

    }

}
