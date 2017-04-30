package com.zou50.bouncycow.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.zou50.bouncycow.BCGame;
import com.zou50.bouncycow.sprites.Cow;

/**
 * Created by Danny on 4/30/17.
 */
public class MenuState extends State {

    private Cow cow;

    private Texture background;
    private Texture playButton;
    private Texture title;

    private Rectangle playBounds;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        cow = new Cow(0, 0);
        background = new Texture("android/assets/bg.png");
        playButton = new Texture("android/assets/playbtn.png");
        title = new Texture("android/assets/title.png");
        playBounds = new Rectangle(
                (BCGame.WIDTH / 2) - (playButton.getWidth() / 2),
                BCGame.HEIGHT / 2 - playButton.getHeight(),
                playButton.getWidth(),
                playButton.getHeight()
        );
        cam.setToOrtho(false, BCGame.WIDTH, BCGame.HEIGHT);
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            mouse.x = Gdx.input.getX();
            mouse.y = Gdx.input.getY();
            if (playBounds.contains(mouse.x, mouse.y))
                gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        cow.update(dt);
        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0, 0, BCGame.WIDTH, BCGame.HEIGHT);
        sb.draw(title, (BCGame.WIDTH / 2) - (title.getWidth() / 2), BCGame.HEIGHT - title.getHeight() - 100);
        sb.draw(playButton, (BCGame.WIDTH / 2) - (playButton.getWidth() / 2), BCGame.HEIGHT / 2);
        sb.draw(cow.getTexture(), (BCGame.WIDTH / 2) - (cow.getTexture().getRegionWidth() / 2), BCGame.HEIGHT / 2 + playButton.getHeight());
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        title.dispose();
        playButton.dispose();
        cow.dispose();
    }

}
