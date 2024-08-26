package it.polimi.ingsw.Network;

import it.polimi.ingsw.Controller.Controller;
import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.View.SocketServerView;

import java.util.ArrayList;

/**
 * Class that represents the Game handler for the current match.
 */
public class GameHandlerNew {
    /**
     * Client handlers' list.
     */
    private ArrayList<ClientHandler> clientHandlers;

    /**
     * List of players' nicknames.
     */
    private ArrayList<String> nicknames;

    /**
     * Player's number in the match.
     */
    private Integer numberOfPlayers;

    /**
     * The controller for the game this handler's client has joined
     */
    private Controller controller;

    /**
     * The socket for the connection with this handler's client
     */
    private SocketServerView socketServerView;

    /**
     * The current game.
     */
    private Game game;

    /**
     * Class constructor.
     * Initializes nicknames' list and players' number in the match.
     */
    public GameHandlerNew(){
        this.nicknames = new ArrayList<>();
        this.numberOfPlayers = -1;
    }

    /**
     * Creates the actual game.
     */
    public synchronized void createGame(){

        if(game == null) {
            this.game = new Game(nicknames);
            this.socketServerView = new SocketServerView();
            this.controller = new Controller(game, socketServerView);
        }
    }

    /**
     * Getter for client handlers list.
     *
     * @return client handlers' list.
     */
    public ArrayList<ClientHandler> getClientHandlers() {
        return clientHandlers;
    }

    /**
     * Getter for nicknames' list.
     *
     * @return Nicknames' list.
     */
    public ArrayList<String> getNicknames() {
        return nicknames;
    }

    /**
     * Getter for players' number.
     *
     * @return Player's number.
     */
    public Integer getNumberOfPlayers() {
        return numberOfPlayers;
    }

    /**
     * Getter for the controller.
     *
     * @return the controller.
     */
    public Controller getController() {
        return controller;
    }

    /**
     * Getter for view.
     *
     * @return the view.
     */
    public SocketServerView getSocketServerView() {
        return socketServerView;
    }

    /**
     * Getter for the game.
     *
     * @return the game.
     */
    public Game getGame() {
        return game;
    }

    /**
     * setter for players' number.
     *
     * @param numberOfPlayers players' number.
     */
    public void setNumberOfPlayers(Integer numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }
}
