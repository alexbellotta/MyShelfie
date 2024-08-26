package it.polimi.ingsw.Model;


import java.io.Serializable;

/**
 * This class is used to represents a personal goal
 * as a group of row index, column index and tile type.
 */
public class PersonalGoalWithPosition implements Serializable {
    /**
     * Tile's type row.
     */
    private final int x;

    /**
     * Tile's type column.
     */
    private final int y;

    /**
     * Tile's type in position (x,y).
     */
    private final TileType type;

    /**
     * Class constructor.
     * This method takes in input the position (x,y) and the type of the tile in the
     * bookshelf.
     *
     * @param x    row of the tile.
     * @param y    column of the tile.
     * @param type type of the tile.
     */
    public PersonalGoalWithPosition(int x, int y, TileType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    /**
     * Getter for the row index of the tile.
     *
     * @return the row index.
     */
    public int getX() {
        return this.x;
    }

    /**
     * Getter for the column index of the tile.
     *
     * @return the column index.
     */
    public int getY() {
        return this.y;
    }

    /**
     * Getter for the tile's type.
     *
     * @return the tile's type.
     */
    public TileType getType() {
        return this.type;
    }
}
