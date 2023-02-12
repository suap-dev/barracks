package dev.suap.barracks;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
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
import dev.suap.barracks.physics.SBox2DWorldRenderer;

public class BarracksAPP extends ApplicationAdapter {
	ScreenViewport viewport;
	World world;
	Box2DDebugRenderer debugRenderer;
	ShapeRenderer shapeRenderer;
	SBox2DWorldRenderer sRenderer;

	private static final float METER = 128;

	@Override
	public void create() {
		Gdx.input.setInputProcessor(new InputHandler());
		final String DEBUG_TAG = "BarracksAPP.create()"; // for debugging

		viewport = new ScreenViewport();
		viewport.setUnitsPerPixel(1 / METER);
		viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		// shapeRenderer.setProjectionMatrix(viewport.getCamera().combined); /* we're
		// doing this in SBox2DWorldRenderer now */

		sRenderer = new SBox2DWorldRenderer();

		world = new World(new Vector2(0, -10), true);

		// debugRenderer = new Box2DDebugRenderer(
		// 		true,
		// 		true,
		// 		false,
		// 		true,
		// 		false,
		// 		false);

		new SBody(world, new Vector2(0, 1), BodyType.DynamicBody)
				.attachCircle(0.2f);
		new SBody(world, new Vector2(-0.1f, -1f), BodyType.DynamicBody)
				.attachCircle(0.2f);
		new SBody(world, new Vector2(0, -viewport.getWorldHeight() / 2), BodyType.StaticBody)
				.attachBox(viewport.getWorldWidth() - 1, 1)
				.attachBox(new Vector2(-viewport.getWorldWidth() / 2 + 1, 1), 1, 1)
				.attachBox(new Vector2(viewport.getWorldWidth() / 2 - 1, 1), 1, 1);
	}

	@Override
	public void render() {
		world.step(1 / 60f, 1, 1);
		ScreenUtils.clear(Color.DARK_GRAY);
		// viewport.getCamera().update(); // required if we manipulate the camera
		sRenderer.render(world, viewport.getCamera().combined);
	}

	@Override
	public void dispose() {
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
	}
}