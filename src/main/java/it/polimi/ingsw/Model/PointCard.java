package it.polimi.ingsw.Model;

import java.io.Serializable;

/**
 * Class point card represents a single point.
 * linked to a common goal.
 */
public class PointCard implements Serializable {
    /**
     * Represents the actual value
     * of a point card. It can be 2, 4, 6 or 8.
     */
    private int point;

    /**
     * Class constructor.
     *
     * @param point value of point card.
     */
    public PointCard(int point){
        this.point = point;

    }

    /**
     * Getter for the point.
     *
     * @return the common goal point.
     */
    public int getPoint() {
        return point;
    }


}
