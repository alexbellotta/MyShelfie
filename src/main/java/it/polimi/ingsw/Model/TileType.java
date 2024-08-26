package it.polimi.ingsw.Model;

import java.io.Serializable;

/**
 * Class that represents all possible tiles' type.
 * Free cell will be identified with FREE and the unavailable
 * cells are identify with LOCK. The LOCK cells depend on the number
 * of players in the match.
 */
public enum TileType implements Serializable {
    CATS, TROPHIES,BOOKS, TOYS, PLANTS, FRAMES, FREE, LOCK
}
