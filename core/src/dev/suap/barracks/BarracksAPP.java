package dev.suap.barracks;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
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
		final String TAG = "BarracksAPP.create()";
		// ScreenViewport - contains camera, is built from the
		viewport = new ScreenViewport();
		viewport.setUnitsPerPixel(1 / METER);
		viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		Gdx.app.log(TAG, "viewport.getWorldHeight(): " + viewport.getWorldHeight());
		Gdx.app.log(TAG, "viewport.getScreenHeight(): " + viewport.getScreenHeight());

		batch = new SpriteBatch();

		Gdx.input.setInputProcessor(new InputHandler());

		img = new Texture("studio.jpeg");
	}

	@Override
	public void render() {
		ScreenUtils.clear(Color.OLIVE);

		viewport.getCamera().update();
		batch.setProjectionMatrix(viewport.getCamera().combined);
		batch.begin();
		batch.draw(img,
				-viewport.getWorldWidth() / 2 + 1, -viewport.getWorldHeight() / 2 + 1, // bottom left corner
				img.getWidth() / METER, img.getHeight() / METER); // width and height of img in meters
		batch.end();
	}

	@Override
	public void dispose() {
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
	}

	private class InputHandler implements InputProcessor {
		private static final String TAG = "InputHandler";

		@Override
		public boolean keyDown(int keycode) {
			switch (keycode) {
				case Input.Keys.UP:
					viewport.getCamera().position.y += 1;
					Gdx.app.log(TAG, "" + viewport.getCamera().position.toString());
					break;
				case Input.Keys.DOWN:
					Gdx.app.log(TAG, "" + viewport.getCamera().position.toString());
					break;
				case Input.Keys.LEFT:
					viewport.getCamera().position.x -= 1;
					Gdx.app.log(TAG, "" + viewport.getCamera().position.toString());
					break;
				case Input.Keys.RIGHT:
					viewport.getCamera().position.x += 1;
					Gdx.app.log(TAG, "" + viewport.getCamera().position.toString());
					break;
				case Input.Keys.SPACE:
					break;
				default:
					break;
			}

			return false;
		}

		@Override
		public boolean keyUp(int keycode) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean keyTyped(char character) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean touchDown(int screenX, int screenY, int pointer, int button) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean touchUp(int screenX, int screenY, int pointer, int button) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean touchDragged(int screenX, int screenY, int pointer) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean mouseMoved(int screenX, int screenY) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean scrolled(float amountX, float amountY) {
			// TODO Auto-generated method stub
			return false;
		}

	}
}