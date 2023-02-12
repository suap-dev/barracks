package dev.suap.barracks;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.Shape.Type;
import com.badlogic.gdx.utils.Array;

public class SBox2DWorldRenderer extends ShapeRenderer {
    private final Vector2 tempVec2 = Vector2.Zero;
    private final float[] vertices = new float[40];
    private final Array<Body> bodies = new Array<>();

    public void render(World world, Matrix4 projectionMatrix) {
        world.getBodies(bodies);

        super.setProjectionMatrix(projectionMatrix);
        super.begin(ShapeType.Line);

        for (Body body : bodies) {
            for (Fixture fix : body.getFixtureList()) {
                draw(fix.getType(), fix.getShape(), body.getPosition());
            }
        }
        super.end();
    }

    private void draw(Type shapeType, Shape shape, Vector2 bodyPosition) {
        super.translate(bodyPosition.x, bodyPosition.y, 0);
        switch (shapeType) {
            case Circle:
                tempVec2.set(
                        ((CircleShape) shape).getPosition().x,
                        ((CircleShape) shape).getPosition().y);
                super.circle(tempVec2.x, tempVec2.y, shape.getRadius(), 20);
                break;
            case Polygon:
                int vertexCount = ((PolygonShape) shape).getVertexCount();
                for (int i = 0; i < vertexCount; i++) {
                    ((PolygonShape) shape).getVertex(i, tempVec2);
                    vertices[2 * i] = tempVec2.x;
                    vertices[2 * i + 1] = tempVec2.y;
                }
                super.polygon(vertices, 0, vertexCount * 2);
                break;
            case Edge:
                break;
            case Chain:
                break;
            default:
                break;
        }
        super.identity();
    }
}
