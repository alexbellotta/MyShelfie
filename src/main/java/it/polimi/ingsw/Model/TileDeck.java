package it.polimi.ingsw.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class that represents the deck from which the tiles are drawn.
 */
public class TileDeck implements Serializable {
    /**
     * Tiles' list.
     */
    ArrayList<Tile> tileDeck;

    /**
     * Class constructor.
     * Initializes the list with all the tiles.
     */
    public TileDeck() {
       this.tileDeck = new ArrayList<>();
       for ( int i = 0 ; i < 22 ; i++)
           tileDeck.add( new Tile(TileType.PLANTS));
        for ( int i = 0 ; i < 22 ; i++)
            tileDeck.add( new Tile(TileType.TROPHIES));
        for ( int i = 0 ; i < 22 ; i++)
            tileDeck.add( new Tile(TileType.BOOKS));
        for ( int i = 0 ; i < 22 ; i++)
            tileDeck.add( new Tile(TileType.FRAMES));
        for ( int i = 0 ; i < 22 ; i++)
            tileDeck.add( new Tile(TileType.CATS));
        for ( int i = 0 ; i < 22 ; i++)
            tileDeck.add( new Tile(TileType.TOYS));
    }

    /**
     * Method to draw a tile from deck.
     *
     * @return the drawn tile.
     */
    public Tile drawNext(){
        int random = new Random().nextInt(tileDeck.size());
        Tile tileDraw = this.tileDeck.get(random);
        this.tileDeck.remove(random);
        return tileDraw;
    }

    /**
     * Getter for the tile deck.
     *
     * @return the tile deck.
     */
    public ArrayList<Tile> getTileDeck(){return tileDeck;}
}
