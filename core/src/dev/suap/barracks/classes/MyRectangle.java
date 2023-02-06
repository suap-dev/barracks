package dev.suap.barracks.classes;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class MyRectangle extends Rectangle {
	private Vector2 translationMatrix;
	private float angularVelocity;
	private float rotation;

	public MyRectangle(Vector2 origin, float width, float height, float rotation) {
		super(-width/2, -height/2, width, height);
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

	public void draw(ShapeRenderer sRenderer) {
		sRenderer.translate(translationMatrix.x, translationMatrix.y, 0);
		sRenderer.rotate(0, 0, 1, rotation);
		sRenderer.rect(x, y, width, height);
		sRenderer.identity();
	}

	public void update(float deltaTime) {
		rotation += angularVelocity * deltaTime;
	}

	/**
	 * @param angularVelocity Angular velocity in degrees
	 */
	public void setAngularVelocity(float angularVelocity) {
		this.angularVelocity = angularVelocity;
	}
}

