package dev.suap.barracks.physics;

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
    private final float[] triangle = new float[6];
    private final Array<Body> bodies = new Array<>();

    public void render(World world, Matrix4 projectionMatrix, ShapeType shapeType) {
        world.getBodies(bodies);

        super.setProjectionMatrix(projectionMatrix);
        super.begin(shapeType);

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
                super.circle(((CircleShape) shape).getPosition().x, ((CircleShape) shape).getPosition().y,
                        shape.getRadius(), 20);
                break;
            case Polygon:
                int vertexCount = ((PolygonShape) shape).getVertexCount();

                // we're drawing the polygon using triangles.

                // zero=th vertex - used to draw every triangle
                ((PolygonShape) shape).getVertex(0, tempVec2);
                triangle[0] = tempVec2.x;
                triangle[1] = tempVec2.y;

                // we iterate over all triangles required to draw the polygon.
                // we start with 1-th vertex and go one by one
                // untill we're at the one before last vertex
                for (int n = 1; n < vertexCount - 1; n++) {
                    // n-th vertex becomes the 1-st vertex of current triangle
                    ((PolygonShape) shape).getVertex(n, tempVec2);
                    triangle[2] = tempVec2.x;
                    triangle[3] = tempVec2.y;

                    // n+1-th vertex becomes the 2-nd vertex of current triangle
                    ((PolygonShape) shape).getVertex(n + 1, tempVec2);
                    triangle[4] = tempVec2.x;
                    triangle[5] = tempVec2.y;

                    super.triangle(triangle[0], triangle[1], // 0-th vertex
                            triangle[2], triangle[3], // 1-th vertex
                            triangle[4], triangle[5]); // 2-nd vertex
                }
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
