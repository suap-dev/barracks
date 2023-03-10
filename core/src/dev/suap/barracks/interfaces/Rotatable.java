package dev.suap.barracks.interfaces;

import com.badlogic.gdx.math.Vector2;

public interface Rotatable {
    /**
     * @return Returns the value of intended rotation in degrees per unit of time.
     */
    public float getAngularVelocity();

    /**
     * @param angularVelocity Angular velocity in degrees. The shape isn't requred
     *                        to act on this value.
     */
    public void setAngularVelocity(float angularVelocity);

    /**
     * @return Return a point representing the center of rotation.
     */
    public Vector2 getOrigin();

    /**
     * @param origin A point around which the rotation is applied.
     */
    public void setOrigin(Vector2 origin);

    /**
     * @return Current rotation in degrees.
     */
    public float getRotation();

    /**
     * @param rotation Angle in degrees. It sets the rotation of a shape.
     */
    public void setRotation(float rotation);
}
