package dev.suap.barracks.physics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

public class SBody {
    private final Body body;

    public SBody(World world, Vector2 position, BodyType bodyType) {
        // create a body
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = bodyType;
        bodyDef.position.x = position.x;
        bodyDef.position.y = position.y;

        body = world.createBody(bodyDef);
    }

    public Body getBody() {
        return body;
    }

    public SBody attachBox(float width, float height) {
        return attachBox(Vector2.Zero, width, height);
    }

    public SBody attachBox(Vector2 offset, float width, float height) {
        return attachBox(offset, width, height, 1, 0.5f, 0.5f);
    }

    public SBody attachBox(float width, float height, float density, float friction, float restitution) {
        return attachBox(Vector2.Zero, width, height, density, friction, restitution);
    }

    public SBody attachBox(Vector2 offset, float width, float height, float density, float friction,
            float restitution) {
        PolygonShape box = new PolygonShape();
        box.setAsBox(width / 2, height / 2, offset, 0.0f);
        attach(box, density, friction, restitution);
        box.dispose();

        // return this for chaining
        return this;
    }

    public SBody attachCircle(float radius) {
        return attachCircle(Vector2.Zero, radius);
    }

    public SBody attachCircle(float radius, float density, float friction, float restitution) {
        return attachCircle(Vector2.Zero, radius, density, friction, restitution);
    }

    // some defaults
    public SBody attachCircle(Vector2 offset, float radius) {
        return attachCircle(offset, radius, 1, 0.5f, 0.5f);
    }

    public SBody attachCircle(Vector2 offset, float radius, float density, float friction, float restitution) {
        CircleShape circle = new CircleShape();
        circle.setPosition(offset);
        circle.setRadius(radius);

        // attach circle
        attach(circle, density, friction, restitution);
        circle.dispose();

        // return this for chaining
        return this;
    }

    private void attach(Shape shape, float density, float friction, float restitution) {
        // create a fixtureDef with a given shape
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.friction = friction;
        fixtureDef.restitution = restitution;
        fixtureDef.density = density;

        body.createFixture(fixtureDef);
    }
}