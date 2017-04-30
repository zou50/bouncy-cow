package com.zou50.bouncycow;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zou50.bouncycow.states.GameStateManager;
import com.zou50.bouncycow.states.MenuState;

public class BCGame extends ApplicationAdapter {

	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;

	public static final String TITLE = "Bouncy Cow";

	private GameStateManager gsm;
	private SpriteBatch batch;
	
	@Override
	public void create () {
		gsm = new GameStateManager();
		batch = new SpriteBatch();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		gsm.push(new MenuState(gsm));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

}
