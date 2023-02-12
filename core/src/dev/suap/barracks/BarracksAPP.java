package dev.suap.barracks;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import dev.suap.barracks.physics.SBody;

public class BarracksAPP extends ApplicationAdapter {
	ScreenViewport viewport;
	World world;
	Box2DDebugRenderer debugRenderer;
	ShapeRenderer shapeRenderer;
	Array<SBody> sBodies = new Array<>();

	private static final float METER = 128;

	@Override
	public void create() {
		Gdx.input.setInputProcessor(new InputHandler());
		final String TAG = "BarracksAPP.create()";

		viewport = new ScreenViewport();
		viewport.setUnitsPerPixel(1 / METER);
		viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(viewport.getCamera().combined);

		Gdx.app.log(TAG, "viewport.getWorldHeight(): " + viewport.getWorldHeight());
		Gdx.app.log(TAG, "viewport.getScreenHeight(): " + viewport.getScreenHeight());

		world = new World(new Vector2(0, -10), true);

		debugRenderer = new Box2DDebugRenderer(
				true,
				true,
				false,
				true,
				false,
				false);
		sBodies.add(
				new SBody(world, new Vector2(0, 1), BodyType.DynamicBody)
						.attachCircle(0.2f));
		sBodies.add(new SBody(world, new Vector2(-0.1f, -1f), BodyType.DynamicBody)
				.attachCircle(0.2f));
		sBodies.add(new SBody(world, new Vector2(0, -viewport.getWorldHeight() / 2), BodyType.StaticBody)
				.attachBox(viewport.getWorldWidth() - 1, 1)
				.attachBox(new Vector2(-viewport.getWorldWidth() / 2 + 1, 1), 1, 1)
				.attachBox(new Vector2(viewport.getWorldWidth() / 2 - 1, 1), 1, 1));

		for (SBody sBody : sBodies) {
			sBody.setRenderer(shapeRenderer);
		}
	}

	@Override
	public void render() {
		world.step(1 / 60f, 1, 1);

		ScreenUtils.clear(Color.DARK_GRAY);
		viewport.getCamera().update();
		// debugRenderer.render(world, viewport.getCamera().combined);
		shapeRenderer.begin(ShapeType.Line);
		for (SBody sBody : sBodies) {
			sBody.draw();
		}
		shapeRenderer.end();
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
					break;
				case Input.Keys.DOWN:
					viewport.getCamera().position.y -= 1;
					break;
				case Input.Keys.LEFT:
					viewport.getCamera().position.x -= 1;
					break;
				case Input.Keys.RIGHT:
					viewport.getCamera().position.x += 1;
					break;
				case Input.Keys.SPACE:
					break;
				default:
					break;
			}
			Gdx.app.log(TAG, "camera coordinates: " + viewport.getCamera().position);

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