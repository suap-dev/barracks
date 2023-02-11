package dev.suap.barracks;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class BarracksAPP extends ApplicationAdapter {
	ScreenViewport viewport;
	World world;
	Box2DDebugRenderer debugRenderer;

	private static final float METER = 128;

	@Override
	public void create() {
		Gdx.input.setInputProcessor(new InputHandler());
		final String TAG = "BarracksAPP.create()";

		viewport = new ScreenViewport();
		viewport.setUnitsPerPixel(1 / METER);
		viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		Gdx.app.log(TAG, "viewport.getWorldHeight(): " + viewport.getWorldHeight());
		Gdx.app.log(TAG, "viewport.getScreenHeight(): " + viewport.getScreenHeight());


		world = new World(new Vector2(0, -10), true);

		debugRenderer = new Box2DDebugRenderer(
			true,
			true,
			true,
			true,
			true,
			true
		);

		Body ball = body(0, 0, BodyType.DynamicBody);
		attachShapeToBody(circleShape(0, 0, 0.2f), ball);

		Body ground = body(0, -viewport.getWorldHeight()/2, BodyType.StaticBody);
		attachShapeToBody(boxShape(0, 0, viewport.getWorldWidth()-1, 1), ground);


	}

	@Override
	public void render() {
		world.step(1f/60f, 1, 1);

		ScreenUtils.clear(Color.DARK_GRAY);

		debugRenderer.render(world, viewport.getCamera().combined);
	}

	Body body(float x, float y, BodyType bodyType) {
		// create a body
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = bodyType;
		bodyDef.position.set(x, y);
		return world.createBody(bodyDef);
	}

	// WARNING: This method disposes of the given shape after using it
		void attachShapeToBody(Shape shape, Body body) {
		// create a fixtureDef with a given shape
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;

		body.createFixture(fixtureDef);
		shape.dispose();
	}

	// WARNING: U need to dispose of the shape after you finish using it!
	CircleShape circleShape(float x, float y, float radius) {
		// create a circle
		CircleShape circle = new CircleShape();
		circle.setPosition(new Vector2(x, y));
		circle.setRadius(radius);

		return circle;
	}

	PolygonShape boxShape(float x, float y, float width, float height) {
		PolygonShape box = new PolygonShape();
		box.setAsBox(width/2, height/2, new Vector2(x, y), 0f);

		return box;
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
			Gdx.app.log(TAG, "camera coordinates: " + viewport.getCamera().position.toString());

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