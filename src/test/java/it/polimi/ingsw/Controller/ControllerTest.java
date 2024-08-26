package it.polimi.ingsw.Controller;

import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Util.PlaceObjectArgs;
import it.polimi.ingsw.Util.TakeObjectArgs;
import it.polimi.ingsw.View.SocketServerView;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class used for controller testing.
 */
class ControllerTest {
    /**
     * Test to see if the instance of place object args is created.
     *
     * @throws Exception to handle errors.
     */
    @Test
    public void testObservableController() throws Exception{
        ArrayList<String> nickArrayList = new ArrayList<>();
        nickArrayList.add("mattia");
        nickArrayList.add("alessandro");
        nickArrayList.add("andrea");
        nickArrayList.add("nicola");

        Game game = new Game(nickArrayList);
        TestView view = new TestView();

        Controller controller = new Controller(game, view);

        int[][] draw = new int[][]{{3, 7}, {3, 8}}; // take, array
        controller.takeObject("mattia",draw);

        assertNotNull(view.lastGameReceived);

        PlaceObjectArgs args = new PlaceObjectArgs("mattia",0);
        view.placeObjectArgsObservable.Notify(args);

    }

    /**
     * Test to see if the instance of controller is correctly created..
     *
     * @throws Exception to handle errors.
     */
    @Test
    public void testCreateController() throws Exception {


        ArrayList<String> nickArrayList = new ArrayList<>();
        nickArrayList.add("mattia");
        nickArrayList.add("alessandro");
        nickArrayList.add("andrea");
        nickArrayList.add("nicola");

        Game game = new Game(nickArrayList);
        TestView view = new TestView();

        Controller controller = new Controller(game,view);
        // add observer observable, listener

        // input del giocatore corrente
        int[][] draw = new int[][]{{3, 7}, {3, 8}}; // take, array
        controller.takeObject("mattia",draw);
        controller.placeObject("mattia", 1);

        assertEquals("alessandro", game.getCurrentPlayer().getNickname());

    }

    /**
     * Test to see if the view is correctly implemented.
     */
    @Test
    public void testObservableControllerMVC(){
        ArrayList<String> nickArrayList = new ArrayList<>();
        nickArrayList.add("mattia");
        nickArrayList.add("alessandro");
        nickArrayList.add("andrea");
        nickArrayList.add("nicola");

        Game game = new Game(nickArrayList);
        TestView view = new TestView();

        Controller controller = new Controller(game, view);

        //-- GIOCATA NUMERO 1 ------------------------------------

        //game.getBoardFromGame().printBoard();
        int[][] draw = new int[][]{{3, 8}, {3, 7}}; // take, array

        // questa corrisponde alla scelta nella view dell'utente
        TakeObjectArgs args1 = new TakeObjectArgs("mattia",draw);
        // con questa chiamata notifico il cambiamento agli observer
        view.takeObjectArgsObservable.Notify(args1);

        // controllo che le tessere della board siano diventate libere
        assertSame(game.getBoardFromGame().getBoard()[3][8].getObjectType(), TileType.FREE);
        // controllo che la board venga svuotata
        assertSame(game.getBoardFromGame().getBoard()[3][7].getObjectType(), TileType.FREE);
        // la modifica ala modello è stata riferita anche alla view
        assertNotNull(view.lastGameReceived);

        PlaceObjectArgs args = new PlaceObjectArgs("mattia",1);
        view.placeObjectArgsObservable.Notify(args);

        // controllo che nella libreria sia stato posizionato qualcosa
        assertNotSame(game.getArrayListPlayer().get(0).getBookshelfFromPlayer().getBookshelf()[4][1].getObjectType(), TileType.FREE);

        // controllo che nel modello sia cambiato il giocatore corrente
        assertSame("alessandro", game.getCurrentPlayer().getNickname());

        //game.getBoardFromGame().printBoard();
        //game.getArrayListPlayer().get(0).getBookshelf().printBookshelf();

        // --- GIOCATA NUMERO DUE ------------------------------------------------

        // 2,6 - 3,6  invalid move

        int[][] draw1 = new int[][]{{4, 7}, {4, 8}}; // take, array
        // questa corrisponde alla scelta nella view dell'utente
        TakeObjectArgs args2 = new TakeObjectArgs("alessandro",draw1);
        // con questa chiamata notifico il cambiamento agli observer
        view.takeObjectArgsObservable.Notify(args2);


        assertNotNull(game.getCurrentPlayer().getCardsHand());

        PlaceObjectArgs args3 = new PlaceObjectArgs("alessandro",1);
        view.placeObjectArgsObservable.Notify(args3);

        // controllo che le tessere della board siano diventate libere
        assertNotSame(game.getBoardFromGame().getBoard()[2][6].getObjectType(), TileType.FREE);

        // controllo che la libreria si sia riempita
        assertNotSame(game.getArrayListPlayer().get(1).getBookshelfFromPlayer().getBookshelf()[4][1].getObjectType(), TileType.FREE);

        // controllo che nel modello sia cambiato il giocatore corrente
        assertSame("andrea", game.getCurrentPlayer().getNickname());

        //game.getBoardFromGame().printBoard();

        //-- GIOCATA NUMERO 3 ---------------------------

        // con questa chiamata notifico il cambiamento agli observer
        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("andrea",new int[][]{{4,6}}));

        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("andrea",1));

        // controllo che nel modello sia cambiato il giocatore corrente
        assertSame("nicola", game.getCurrentPlayer().getNickname());
        // controllo che le tessere della board siano diventate libere
        assertSame(game.getBoardFromGame().getBoard()[4][6].getObjectType(), TileType.FREE);
        // controllo che la libreria si sia riempita
        assertNotSame(game.getArrayListPlayer().get(2).getBookshelfFromPlayer().getBookshelf()[5][1].getObjectType(), TileType.FREE);

        //game.getBoardFromGame().printBoard();


        // -- GIOCATA NUMERO 4 ------------------

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("nicola",new int[][]{{2,6},{3,6}}));

        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("nicola",1));


        assertSame("mattia", game.getCurrentPlayer().getNickname());

        //game.getBoardFromGame().printBoard();
    }

    /**
     * Test to see if the current player changes after tiles are drawn and place.
     */
    @Test
    public void testObservableControllerChangePlayerAfterAllMove(){
        ArrayList<String> nickArrayList = new ArrayList<>();
        nickArrayList.add("mattia");
        nickArrayList.add("alessandro");
        nickArrayList.add("andrea");
        nickArrayList.add("nicola");

        Game game = new Game(nickArrayList);
        TestView view = new TestView();

        Controller controller = new Controller(game, view);

        //-- GIOCATA NUMERO 1 ------------------------------------

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("mattia",new int[][]{{3, 8}, {3, 7}}));

        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("mattia",1));

        // --- GIOCATA NUMERO DUE ------------------------------------------------

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("alessandro",new int[][]{{4, 7}, {4, 8}}));

        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("alessandro",1));

        //-- GIOCATA NUMERO 3 ---------------------------

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("andrea",new int[][]{{4,6}}));

        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("andrea",1));

        // -- GIOCATA NUMERO 4 ------------------

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("nicola",new int[][]{{2,6},{3,6}}));

        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("nicola",1));

        assertEquals("mattia", game.getCurrentPlayer().getNickname());

    }

    /**
     * Semi-full match test to see if the view is correctly notified about the changes.
     */
    @Test
    public void testObservableControllerFullGameplay(){
        ArrayList<String> nickArrayList = new ArrayList<>();
        nickArrayList.add("mattia");
        nickArrayList.add("alessandro");
        nickArrayList.add("andrea");
        nickArrayList.add("nicola");

        Game game = new Game(nickArrayList);
        SocketServerView view = new SocketServerView();
        Controller controller = new Controller(game, view);

        //-- GIOCATA NUMERO 1 ------------------------------------

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("mattia", new int[][]{{3, 8}, {3, 7}}));

        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("mattia", 1));

        // --- GIOCATA NUMERO 2 ------------------------------------------------

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("alessandro", new int[][]{{4, 7}, {4, 8}}));

        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("alessandro", 1));

        //-- GIOCATA NUMERO 3 ---------------------------

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("andrea", new int[][]{{4, 6}}));

        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("andrea", 1));

        // -- GIOCATA NUMERO 4 ------------------

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("nicola", new int[][]{{2, 6}, {3, 6}}));

        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("nicola", 1));

        //-- GIOCATA NUMERO 5 ------------------------------------

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("mattia", new int[][]{{3, 5}, {2, 5}, {1, 5}}));

        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("mattia", 1));

        // --- GIOCATA NUMERO 6 ------------------------------------------------

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("alessandro", new int[][]{{5, 6}, {5, 7}}));

        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("alessandro", 1));

        //-- GIOCATA NUMERO 7 ---------------------------

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("andrea", new int[][]{{5, 0}, {5, 1}}));

        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("andrea", 1));

        // -- GIOCATA NUMERO 8 ------------------

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("nicola", new int[][]{{4, 0}, {4, 1}}));

        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("nicola", 1));

        //-- GIOCATA NUMERO 9 ------------------------------------

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("mattia", new int[][]{{0, 4}, {1, 4}, {2, 4}}));

        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("mattia", 2));

        // --- GIOCATA NUMERO 10 ------------------------------------------------

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("alessandro", new int[][]{{4, 5}, {5, 5}}));

        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("alessandro", 2));

        //-- GIOCATA NUMERO 11 ---------------------------

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("andrea", new int[][]{{8, 4}, {8, 5}}));

        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("andrea", 2));

        // -- GIOCATA NUMERO 12 ------------------

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("nicola", new int[][]{{7, 4}}));

        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("nicola", 0));

        //-- GIOCATA NUMERO 13 ------------------------------------

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("mattia", new int[][]{{6, 4}, {5, 4}, {4, 4}}));

        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("mattia", 2));

        // --- GIOCATA NUMERO 14 ------------------------------------------------

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("alessandro", new int[][]{{4, 2}, {5, 2}, {6, 2}}));

        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("alessandro", 2));

        //-- GIOCATA NUMERO 15 ---------------------------

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("andrea", new int[][]{{0, 3}, {1, 3}, {2, 3}}));

        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("andrea", 2));

        // -- GIOCATA NUMERO 16 ------------------

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("nicola", new int[][]{{7, 3}, {5, 3}, {6, 3}}));

        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("nicola", 2));

        //-- GIOCATA NUMERO 17 ------------------------------------

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("mattia", new int[][]{{3, 3}, {4, 3}}));

        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("mattia", 3));

        // --- GIOCATA NUMERO 18 ------------------------------------------------

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("alessandro", new int[][]{{2, 2}, {3, 2}}));

        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("alessandro", 3));

        //-- GIOCATA NUMERO 19 ---------------------------

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("andrea", new int[][]{{3, 4}}));

        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("andrea", 3));

        // -- GIOCATA NUMERO 20 ------------------

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("nicola", new int[][]{{6, 6}, {6, 5}}));

        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("nicola", 3));

        // -- REFILLING DELLA BOARD------------------------------------------------

        //-- GIOCATA NUMERO 1 ------------------------------------

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("mattia", new int[][]{{3, 8}, {3, 7}}));

        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("mattia", 4));

        // --- GIOCATA NUMERO 2 ------------------------------------------------

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("alessandro", new int[][]{{4, 7}, {4, 8}}));

        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("alessandro", 4));

        //-- GIOCATA NUMERO 3 ---------------------------

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("andrea", new int[][]{{4, 6}}));

        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("andrea", 4));

        // -- GIOCATA NUMERO 4 ------------------

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("nicola", new int[][]{{2, 6}, {3, 6}}));

        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("nicola", 1));

        // controllo che game.board == view.board
        for(int i = 0 ; i < game.getBoardFromGame().getBoard().length; i++){
            for(int j = 0 ; j <game.getBoardFromGame().getBoard()[i].length; j++){
                assertEquals(game.getBoardFromGame().getBoard()[i][j].getObjectType(), view.lastStateGameReceived.getBoard()[i][j].getObjectType());
            }
        }

    }

    /**
     * Full match test.
     */
    @Test
    public void testObservableControllerFullGameplay2(){
        ArrayList<String> nickArrayList = new ArrayList<>();
        nickArrayList.add("mattia");
        nickArrayList.add("alessandro");
        nickArrayList.add("andrea");
        nickArrayList.add("nicola");

        Game game = new Game(nickArrayList);
        SocketServerView view = new SocketServerView();
        Controller controller = new Controller(game, view);

        //-- turno 1 ------------------------------------

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("mattia", new int[][]{{3, 8}, {3, 7}}));
        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("mattia", 1));

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("alessandro", new int[][]{{4, 7}, {4, 8}}));
        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("alessandro", 1));

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("andrea", new int[][]{{4, 6}}));
        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("andrea", 1));

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("nicola", new int[][]{{2, 6}, {3, 6}}));
        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("nicola", 1));

        //-- turno 2 ------------------------------------

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("mattia", new int[][]{{3, 5}, {2, 5}, {1, 5}}));
        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("mattia", 1));

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("alessandro", new int[][]{{5, 6}, {5, 7}}));
        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("alessandro", 1));

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("andrea", new int[][]{{5, 0}, {5, 1}}));
        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("andrea", 1));

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("nicola", new int[][]{{4, 0}, {4, 1}}));
        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("nicola", 1));

        //-- turno 3------------------------------------

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("mattia", new int[][]{{0, 4}, {1, 4}, {2, 4}}));
        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("mattia", 2));

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("alessandro", new int[][]{{4, 5}, {5, 5}}));
        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("alessandro", 2));

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("andrea", new int[][]{{8, 4}, {8, 5}}));
        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("andrea", 2));

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("nicola", new int[][]{{7, 4}}));
        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("nicola", 0));

        //-- turno 4 ------------------------------------

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("mattia", new int[][]{{6, 4}, {5, 4}, {4, 4}}));
        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("mattia", 2));

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("alessandro", new int[][]{{4, 2}, {5, 2}, {6, 2}}));
        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("alessandro", 2));

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("andrea", new int[][]{{0, 3}, {1, 3}, {2, 3}}));
        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("andrea", 2));

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("nicola", new int[][]{{7, 3}, {5, 3}, {6, 3}}));
        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("nicola", 2));

        //-- turno 5 ------------------------------------

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("mattia", new int[][]{{3, 3}, {4, 3}}));
        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("mattia", 3));

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("alessandro", new int[][]{{2, 2}, {3, 2}}));
        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("alessandro", 3));

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("andrea", new int[][]{{3, 4}}));
        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("andrea", 3));

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("nicola", new int[][]{{6, 6}, {6, 5}}));
        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("nicola", 3));

        // -- REFILLING DELLA BOARD------------------------------------------------

        //-- turno 6 ------------------------------------

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("mattia", new int[][]{{3, 8}, {3, 7}}));
        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("mattia", 0));

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("alessandro", new int[][]{{4, 7}, {4, 8}}));
        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("alessandro", 0));

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("andrea", new int[][]{{4, 6}}));
        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("andrea", 0));

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("nicola", new int[][]{{2, 6}, {3, 6}}));
        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("nicola", 0));

        //-- turno 7 ------------------------------------

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("mattia", new int[][]{{3, 5}, {2, 5}, {1, 5}}));
        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("mattia", 0));

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("alessandro", new int[][]{{5, 6}, {5, 7}}));
        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("alessandro", 0));

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("andrea", new int[][]{{5, 0}, {5, 1}}));
        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("andrea", 0));

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("nicola", new int[][]{{4, 0}, {4, 1}}));
        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("nicola", 0));

        //-- turno 8------------------------------------

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("mattia", new int[][]{{0, 4}, {1, 4}, {2, 4}}));
        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("mattia", 4));

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("alessandro", new int[][]{{4, 5}, {5, 5}}));
        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("alessandro", 4));

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("andrea", new int[][]{{8, 4}, {8, 5}}));
        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("andrea", 4));

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("nicola", new int[][]{{7, 4}}));
        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("nicola", 4));

        //-- turno 9 ------------------------------------

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("mattia", new int[][]{{6, 4}, {5, 4}, {4, 4}}));
        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("mattia", 4));

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("alessandro", new int[][]{{4, 2}, {5, 2}, {6, 2}}));
        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("alessandro", 4));

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("andrea", new int[][]{{0, 3}, {1, 3}, {2, 3}}));
        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("andrea", 4));

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("nicola", new int[][]{{7, 3}, {5, 3}, {6, 3}}));
        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("nicola", 4));

        //-- turno 10 ------------------------------------

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("mattia", new int[][]{{3, 3}, {4, 3}}));
        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("mattia", 3));

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("alessandro", new int[][]{{2, 2}, {3, 2}}));
        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("alessandro", 3));

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("andrea", new int[][]{{3, 4}}));
        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("andrea", 3));

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("nicola", new int[][]{{6, 6}, {6, 5}}));
        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("nicola", 3));

        // -- REFILLING DELLA BOARD------------------------------------------------
        //-- turno 11 ------------------------------------

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("mattia", new int[][]{{3, 8}}));
        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("mattia", 0));

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("alessandro", new int[][]{{3, 7}}));
        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("alessandro", 0));

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("andrea", new int[][]{{4, 8}}));
        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("andrea", 0));

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("nicola", new int[][]{{2, 6}, {3, 6}}));
        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("nicola", 2));

        //-- turno 12 ------------------------------------

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("mattia", new int[][]{{3, 5}, {2, 5}}));
        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("mattia", 3));

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("alessandro", new int[][]{{1, 5}}));
        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("alessandro", 4));

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("andrea", new int[][]{{5, 0}, {5, 1}}));
        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("andrea", 0));

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("nicola", new int[][]{{4, 0}, {4, 1}}));
        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("nicola", 1));

        //-- turno 13------------------------------------

        assertFalse(game.getArrayListPlayer().get(0).isFirstToComplete());
        assertFalse(game.isLastTurn());
        assertNull(view.lastStateGameReceived.whoIsWinner());

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("mattia", new int[][]{{0, 4}}));    //, {1, 4}, {2, 4}
        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("mattia", 1));

        assertTrue(game.isLastTurn());
        assertNull(view.lastStateGameReceived.whoIsWinner());

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("alessandro", new int[][]{{1, 4}, {2, 4}}));    //{{4, 5}, {5, 5}}));
        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("alessandro", 1));

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("andrea", new int[][]{{8, 4}}));    //, {8, 5}
        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("andrea", 3));

        view.takeObjectArgsObservable.Notify(new TakeObjectArgs("nicola", new int[][]{{7, 4}}));
        view.placeObjectArgsObservable.Notify(new PlaceObjectArgs("nicola", 4));

        //end of game. player 0 (mattia) has filled his library

        assertTrue(game.getArrayListPlayer().get(0).isFirstToComplete());
        assertTrue(game.isLastTurn());
        assertEquals(view.lastStateGameReceived.whoIsWinner(),game.calculateWinner());

        // controllo che game.board == view.board
        for(int i = 0 ; i < game.getBoardFromGame().getBoard().length; i++){
            for(int j = 0 ; j <game.getBoardFromGame().getBoard()[i].length; j++){
                assertEquals(game.getBoardFromGame().getBoard()[i][j].getObjectType(), view.lastStateGameReceived.getBoard()[i][j].getObjectType());
            }
        }

    }
}
