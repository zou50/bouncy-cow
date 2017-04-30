package com.zou50.bouncycow.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
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
    private static final int GROUND_Y_OFFSET = -50;
    private static final int TUBE_X_OFFSET = 250;

    private Cow cow;
    private Texture bg;
    private Texture ground;
    private Vector2 groundPos1, groundPos2;

    private Array<Tube> tubes;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        cow = new Cow(50, 300);
        cam.setToOrtho(false, BCGame.WIDTH / 2, BCGame.HEIGHT / 2);
        bg = new Texture("android/assets/bg.png");
        ground = new Texture("android/assets/ground.png");
        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth / 2, GROUND_Y_OFFSET);
        groundPos2 = new Vector2((cam.position.x - cam.viewportWidth / 2) + ground.getWidth(), GROUND_Y_OFFSET);

        tubes = new Array<Tube>();
        for (int i = 1; i <= TUBE_COUNT; i++)
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH) + TUBE_X_OFFSET));

    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched())
            cow.jump();
    }

    @Override
    public void update(float dt) {
        handleInput();
        updateGround();
        cow.update(dt);
        cam.position.x = cow.getPosition().x + 80;

        for (Tube tube : tubes) {
            if (cam.position.x - (cam.viewportWidth / 2) > tube.getPosTopTube().x + tube.getTopTube().getWidth())
                tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));

            if (tube.collides(cow.getBounds())) {
                gsm.set(new PlayState(gsm));
                break;
            }
        }

        if (cow.getPosition().y <= ground.getHeight() + GROUND_Y_OFFSET)
            gsm.set(new PlayState(gsm));
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
        sb.draw(ground, groundPos1.x, groundPos1.y);
        sb.draw(ground, groundPos2.x, groundPos2.y);
        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        cow.dispose();
        for (Tube tube: tubes)
            tube.dispose();
    }

    private void updateGround() {
        if (cam.position.x - (cam.viewportWidth / 2) > groundPos1.x + ground.getWidth())
            groundPos1.add(ground.getWidth() * 2, 0);
        if (cam.position.x - (cam.viewportWidth / 2) > groundPos2.x + ground.getWidth())
            groundPos2.add(ground.getWidth() * 2, 0);
    }

}
