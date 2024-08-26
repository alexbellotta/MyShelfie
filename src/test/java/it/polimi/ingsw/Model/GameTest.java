package it.polimi.ingsw.Model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    // -- TESTING CONSTRUCOTOR OF THE GAME ----------------------------
    // try game with 3 palyer
    @Test
    public void test3PlayerConstructor(){
        ArrayList<String> nickArrayList = new ArrayList<>();

        nickArrayList.add("mattia");
        nickArrayList.add("alessandro");
        nickArrayList.add("andrea");

        try{
            Game game = new Game(nickArrayList);
        }catch(Exception e ){
            fail(e.getMessage());
        }
    }


    //try 4 game player
    @Test
    public void test4PlayerConstructor(){
        ArrayList<String> nickArrayList = new ArrayList<>();

        nickArrayList.add("mattia");
        nickArrayList.add("alessandro");
        nickArrayList.add("andrea");
        nickArrayList.add("nicola");


        try{
            Game game = new Game(nickArrayList);
        }catch(Exception e ){
            fail(e.getMessage());
        }
    }


    // try 2 game player
    @Test
    public void test2PlayerConstructor(){
        ArrayList<String> nickArrayList = new ArrayList<>();

        nickArrayList.add("mattia");
        nickArrayList.add("alessandro");

        try{
            Game game = new Game(nickArrayList);
        }catch(Exception e ){
            fail(e.getMessage());
        }
    }


    // -- TESTING METHOD ---------------------------

    @Test
    public void testGameBoard() throws Exception {

        ArrayList<String> nickArrayList = new ArrayList<>();

        nickArrayList.add("mattia");
        nickArrayList.add("alessandro");
        nickArrayList.add("andrea");
        //nickArrayList.add("nicola");

        Game game = new Game(nickArrayList);

        int[][] draw = new int[][]{{3, 7}, {3, 8}};

        assertEquals(true, game.getBoardFromGame().getBoard()[3][3].getObjectType() != TileType.FREE);


        //stampa della board
        /*
        for (int j = 0; j < 19; j++)
            System.out.print("\t" + j+"\t\t");
        System.out.println();
        for(int i = 0; i < game.getBoardFromGame().getBoard().length; i++) {
            System.out.print("" + i+ " ");
            for (int j = 0; j < game.getBoardFromGame().getBoard()[i].length; j++) {
                System.out.print("\t" + game.getBoardFromGame().getBoard()[i][j].getObjectType()+"\t");
            }
            System.out.println();
        }
        */

        //game.getBoardFromGame().printBoard();

        game.takeObject(draw);

        // controlla che si sia liberata una delle celle pescate
        assertEquals(true, game.getBoardFromGame().getBoard()[3][7].getObjectType() == TileType.FREE);


       // game.getBoardFromGame().printBoard();

    }

    @Test
    public void testGameDeck3Player(){

        ArrayList<String> nickArrayList = new ArrayList<>();

        nickArrayList.add("mattia");
        nickArrayList.add("alessandro");
        nickArrayList.add("andrea");

        Game game = new Game(nickArrayList);

        //prova test

        assertEquals(true, game.getCommonGoal().get(0).getPoints().size()==3 );


    }


    @Test
    public void testPlaceObjectOnFullGameRotationPlayer_notTestedTakeObject(){

        // creazione game-----------------------------------

        ArrayList<String> nickArrayList = new ArrayList<>();

        nickArrayList.add("mattia");
        nickArrayList.add("alessandro");
        nickArrayList.add("andrea");

        Game game = new Game(nickArrayList);
        //---------------------------------------------------

        //creazione array oggetti da mettere in libreria--------------------------
        Tile one = new Tile(TileType.BOOKS);
        Tile two = new Tile(TileType.BOOKS);
        Tile three = new Tile(TileType.BOOKS);

        ArrayList<Tile> hand = new ArrayList();
        hand.add(one);
        hand.add(two);
        hand.add(three);
        //---------------------------------------------------------------------------------------------

        // controllo la rotazione dei player ad ogni place object
        game.getCurrentPlayer().setHand(hand);
        game.placeObject(1);
        //il current player deve essere cambiato
        assertTrue(game.getArrayListPlayer().get(1) == game.getCurrentPlayer());
        //il player precedente deve aver modificato la libreria
        // il primo getbookshelf mi ritorna la classe sotto player, il secondo la libreria vera e propria come attributo
        assertEquals(true,game.getArrayListPlayer().get(0).getBookshelfFromPlayer().getBookshelf()[3][1].getObjectType() == TileType.BOOKS);

        game.getCurrentPlayer().setHand(hand);
        game.placeObject(1);
        assertTrue(game.getArrayListPlayer().get(2) == game.getCurrentPlayer());

        game.getCurrentPlayer().setHand(hand);
        game.placeObject(1);
        assertTrue(game.getArrayListPlayer().get(0) == game.getCurrentPlayer());

    }

    @Test
    public void testTakePlusPlaceObjectGame() throws Exception {

        ArrayList<String> nickArrayList = new ArrayList<>();

        nickArrayList.add("mattia");
        nickArrayList.add("alessandro");
        nickArrayList.add("andrea");

        Game game = new Game(nickArrayList);

        //game.getBoardFromGame().printBoard();

        // -- FINE CREAZIONE DELLO STATO INIZIALE DELLA PARTITA  --------------------------------------------------------------

        // --   PRIMA PESCATA DELLA PARTITA -------------------------------------

        int[][] draw = new int[][]{{3, 7}, {3, 8}};
        // preleva da board e setta la mano
        game.takeObject(draw);
        // piazza la mano all'interno della libreria
        game.placeObject(1);
        assertEquals(true, game.getArrayListPlayer().get(0).getBookshelfFromPlayer().getBookshelf()[4][1].getObjectType() != TileType.FREE);



        // -- SECONDA PESCATA DELLA PARTITA --------------------------------------

        int[][] draw1 = new int[][]{{7, 5}}; // test 8,5 prima della sottomissione
        game.takeObject(draw1);
        game.placeObject(1);

        // -------------------------------------------------
        //stampa della board

        //game.getBoardFromGame().printBoard();

        // -- PESCATE SUCCESSIVE -----------------------------------------


    }
}
