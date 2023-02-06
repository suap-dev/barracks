package dev.suap.barracks.classes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import dev.suap.barracks.interfaces.Entity;
import dev.suap.barracks.interfaces.Rotatable;

public class MyRectangle extends Rectangle implements Entity, Rotatable {
	private Vector2 translationMatrix;
	private float angularVelocity;
	private float rotation;
	private Color color = Color.WHITE;

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public MyRectangle(Vector2 origin, float width, float height, float rotation) {
		super(-width / 2, -height / 2, width, height);
		this.translationMatrix = origin;
		this.rotation = rotation;
	}

	public MyRectangle(Vector2 origin, float width, float height) {
		this(origin, width, height, 0);
	}

	public MyRectangle(float x, float y, float width, float height, float rotation) {
		super(0, 0, width, height);
		this.translationMatrix = new Vector2(x, y);
		this.rotation = rotation;
		this.angularVelocity = 0;
	}

	public MyRectangle(float x, float y, float width, float height) {
		this(x, y, width, height, 0);
	}

	@Override
	public void draw(ShapeRenderer shapeRenderer) {
		shapeRenderer.translate(translationMatrix.x, translationMatrix.y, 0);
		shapeRenderer.rotate(0, 0, 1, rotation);
		shapeRenderer.setColor(color);
		shapeRenderer.rect(x, y, width, height);
		shapeRenderer.identity();
	}

	@Override
	public void update(float deltaTime) {
		rotation += angularVelocity * deltaTime;
	}

	/**
	 * @param angularVelocity Angular velocity in degrees.
	 */
	@Override
	public void setAngularVelocity(float angularVelocity) {
		this.angularVelocity = angularVelocity;
	}

	@Override
	public Vector2 getOrigin() {
		return translationMatrix;
	}

	@Override
	public void setOrigin(Vector2 origin) {
		this.translationMatrix = origin;
	}

	@Override
	public float getAngularVelocity() {
		return angularVelocity;
	}

	@Override
	public float getRotation() {
		return rotation;
	}

	@Override
	public void setRotation(float rotation) {
		this.rotation = rotation;
	}
}
