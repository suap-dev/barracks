package dev.suap.barracks;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

import dev.suap.barracks.classes.MyRectangle;

public class BarracksAPP extends ApplicationAdapter {
	ShapeRenderer sRenderer;
	Rectangle r1;
	MyRectangle r2;
	float windowWidth;
	float windowHeight;

	@Override
	public void create() {
		windowWidth = Gdx.graphics.getWidth();
		windowHeight = Gdx.graphics.getHeight();
		sRenderer = new ShapeRenderer();

		r1 = new Rectangle(10, 10, 200, 100);

		r2 = new MyRectangle(new Vector2(windowWidth / 2, windowHeight / 2), 200, 100);
		r2.setColor(Color.OLIVE);
		// r2.setAngularVelocity(-18);
	}

	@Override
	public void render() {
		// update
		r2.update(Gdx.graphics.getDeltaTime());
		// Gdx.app.log("r2", r2.toString());

		if (Gdx.input.isKeyPressed(Keys.ENTER))
			r2.addInterpolatedRotation(90, 1.5f, Interpolation.pow3);

		// draw
		ScreenUtils.clear(0, 0.07f, 0.1f, 1);
		sRenderer.begin(ShapeType.Filled);
		r2.draw(sRenderer);
		sRenderer.end();
	}
}