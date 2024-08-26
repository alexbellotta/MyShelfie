package it.polimi.ingsw.Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class TileDeckTest {

    public void testObjectcardDeck(){

        try{
            TileDeck deck = new TileDeck();
        }catch (Exception e){
            fail(e.getMessage());
        }
    }
    @Test
    public void testExtraction(){

        TileDeck deck = new TileDeck();

        assertEquals(132, deck.tileDeck.size());

        Tile extractCard = deck.drawNext();

        assertEquals(131, deck.tileDeck.size());

    }



}
