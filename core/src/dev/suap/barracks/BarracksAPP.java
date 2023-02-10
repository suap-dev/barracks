package dev.suap.barracks;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class BarracksAPP extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	ScreenViewport viewport;

	private static final float METER = 16;

	@Override
	public void create() {
		viewport = new ScreenViewport();
		viewport.setUnitsPerPixel(1 / METER);
		viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
	}

	@Override
	public void render() {
		ScreenUtils.clear(Color.OLIVE);

		batch.setProjectionMatrix(viewport.getCamera().combined);
		batch.begin();
		batch.draw(img, -viewport.getWorldWidth() / 2 + 1, -viewport.getWorldHeight() / 2 + 1, img.getWidth() / METER,
				img.getHeight() / METER);
		batch.end();

	}

	@Override
	public void dispose() {
	}

	@Override
	public void resize(int width, int height) {
	}
}