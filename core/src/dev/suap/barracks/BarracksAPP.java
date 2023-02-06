package dev.suap.barracks;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

public class BarracksAPP extends ApplicationAdapter {
	ShapeRenderer sRenderer;
	Rectangle r1;

	@Override
	public void create() {
		sRenderer = new ShapeRenderer();
		r1 = new Rectangle(100, 100, 200, 100);
	}

	@Override
	public void render() {
		ScreenUtils.clear(0, 0.07f, 0.1f, 1);
		sRenderer.begin(ShapeType.Filled);
		sRenderer.rect(r1.x, r1.y, r1.width, r1.height);
		sRenderer.end();
	}

	@Override
	public void dispose() {
		sRenderer.dispose();
	}
}
