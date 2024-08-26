package it.polimi.ingsw.Model;

import java.io.Serializable;

/**
 * this class represents the different types of tiles in the game.
 */
public class Tile implements Serializable {
    /**
     * Tiles' type.
     */
    private TileType tileType;

    /**
     * Class constructor.
     *
     * @param type tile's type.
     */
    public Tile(TileType type){
        this.tileType = type;
    }

    /**
     * Getter for tiles' type.
     *
     * @return tiles' type.
     */
    public TileType getObjectType() {
        return tileType;
    }

    /**
     * Setter for tiles' type.
     *
     * @param tileType tiles' type.
     */
    public void setObjectType(TileType tileType) {
        this.tileType = tileType;
    }
}
