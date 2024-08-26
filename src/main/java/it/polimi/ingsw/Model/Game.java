package it.polimi.ingsw.Model;
import it.polimi.ingsw.Util.EndGameArgs;
import it.polimi.ingsw.Util.GameStateChangedArgs;
import it.polimi.ingsw.Util.IObservable;
import it.polimi.ingsw.Util.Observable;

import java.io.Serializable;
import java.util.*;

/**
 * This class represents the state of the game.
 */
public class Game implements Serializable {
    /**
     * How many players are in the game.
     */
    private final int numberOfPlayers;

    /**
     * Player's array in the game.
     */
    private ArrayList<Player> arrayPlayers;

    /**
     * Game's board.
     */
    private Board board;

    /**
     * Active player in the turn.
     */
    private  Player currentPlayer;

    /**
     * List of two common goals.
     */
    private ArrayList<CommonGoal> commonGoal;

    /**
     * List (deck) of all personal goals from where players draw out their goal.
     */
    private GoalDeck goalDeck;

    /**
     * Deck of TileCards.
     */
    private TileDeck tileDeck;

    /**
     * When a player completes his bookshelf, set this boolean true and when the current
     * player is the first, start endgame.
     */
    private boolean lastTurn;

    /**
     * Adding observable element in the game as attribute.
     */
    private final Observable<GameStateChangedArgs> gameStateChangedArgsObservable = new Observable<>();

    /**
     * Adding observable element in the game as attribute.
     */
    private final Observable<EndGameArgs> endGameArgsObservable = new Observable<>();

    /**
     * Class constructor.
     * Creates a game from a list of players.
     *
     * @param nicknameList list of players in the game.
     */
    public Game (ArrayList<String> nicknameList){
        this.numberOfPlayers = nicknameList.size();
        this.goalDeck = new GoalDeck();
        this.tileDeck = new TileDeck();
        this.defineCommonGoal();
        arrayPlayers = new ArrayList<>();
        for(int i = 0; i < numberOfPlayers; i++){
            String nickname = nicknameList.get(i);
            addPlayerAtArrayList(nickname, goalDeck, commonGoal);
        }
        //Set the first player in the list as the first player to start.
        currentPlayer = arrayPlayers.get(0);
        board = new Board(this.getNumberOfPlayers());
        try {
            board.refill(tileDeck);
        }catch (Exception e) {
            System.out.print(e.getMessage());
        }
        this.lastTurn = false ;
    }

    /**
     * Getter of the current player.
     *
     * @return current player, the active player in this turn.
     */
    public Player getCurrentPlayer(){return currentPlayer;}

    /**
     * Getter for the tiles deck.
     *
     * @return the tiles deck.
     */
    public TileDeck getTileDeck(){return tileDeck;}

    /**
     * Getter for the list of players.
     *
     * @return the list of players.
     */
    public ArrayList<Player> getArrayListPlayer(){return arrayPlayers;}

    /**
     * Getter for the number of players in the game.
     *
     * @return number of players in the game.
     */
    public int getNumberOfPlayers() {return numberOfPlayers;}

    /**
     * Getter of the board.
     *
     * @return current state of the board.
     */
    public Board getBoardFromGame() {
        return board;
    }

    /**
     * Getter for the list of common goals.
     *
     * @return list of common goals for the current match.
     */
    public ArrayList<CommonGoal> getCommonGoal() {
        return commonGoal;
    }

    /**
     * Getter for the observable objects.
     *
     * @return the observable object.
     */
    public IObservable<GameStateChangedArgs> getGameStateChangeObservable(){
        return this.gameStateChangedArgsObservable;
    }

    /**
     * Getter for the observable objects.
     *
     * @return the observable object.
     */
    public IObservable<EndGameArgs> getEndGameArgsObservable(){
        return this.endGameArgsObservable;
    }

    /**
     * Method that extracts two common goals and set them for the game.
     */
    public void defineCommonGoal(){
        //Common goals creation
        this.commonGoal = new ArrayList<>();
        int random = new Random().nextInt(12);
        ArrayList<CommonGoalType> commonGoalExtraction = new ArrayList<>();
        commonGoalExtraction.addAll(Arrays.asList(CommonGoalType.values()));
        commonGoal.add( new CommonGoal(commonGoalExtraction.get(random), numberOfPlayers));
        commonGoalExtraction.remove(random);
        int random1 = new Random().nextInt(11);
        commonGoal.add( new CommonGoal(commonGoalExtraction.get(random1), numberOfPlayers));
        commonGoalExtraction.remove(random1);
    }

    /**
     * Add new player, by nickname, in the player's list.
     * and creates the player's list of the game.
     *
     * @param nickname player's nickname.
     * @param goalDeck deck from where players choose their personal goal.
     * @param commonGoal list of common goals active in the match.
     */
    public void addPlayerAtArrayList(String nickname, GoalDeck goalDeck, ArrayList<CommonGoal> commonGoal){
        arrayPlayers.add(new Player(nickname,goalDeck.drawNext(),commonGoal));
    }

    /**
     * This method receives a list of coordinate that identifies where the player wants.
     * to draw and return a list of tileCard to put in the bookshelf.
     *
     * @param drawArray list of coordinates.
     */
    public void takeObject( int[][] drawArray) throws Exception {
        ArrayList<Tile> arrayObjectDrawn = new ArrayList<Tile>(this.board.takeObject(drawArray));
        board.refill(tileDeck);
        currentPlayer.setHand(arrayObjectDrawn);
        notifyGameStateChanged();
    }

    /**
     * Method that starts place object phase  of the current player.
     * Player has to choose column and the array of tiles to deposit.
     * After this action, player's turn end and game changes the current player scanning the player's list.
     *
     * @param column int to identify the column of the bookshelf where player wants to put the drawn tiles.
     */
    public void placeObject( int column) {
        currentPlayer.placeHand(column);
        //Player has completed his bookshelf and end game phase starts.
        if (currentPlayer.getBookshelfFromPlayer().isFull() && !lastTurn){
            currentPlayer.setFirstToComplete();
            lastTurn = true;
        }
        changeTurn();
        if (currentPlayer == arrayPlayers.get(0) && lastTurn){
            notifyEndGameState(this.calculateWinner());
        }else {
            notifyGameStateChanged();
        }
    }

    /**
     * This method is used to change current player.
     * Called after placeObject method.
     */
    public void changeTurn(){
        int intCurrentPlayer = arrayPlayers.indexOf(currentPlayer);
        if(intCurrentPlayer == arrayPlayers.size()-1)
            currentPlayer = arrayPlayers.get(0);
        else{
            currentPlayer = arrayPlayers.get(intCurrentPlayer+1);
        }
    }

    /**
     * Method to calculate and set the winner.
     */
    public String calculateWinner(){
        String winner = null;
        int MAX = arrayPlayers.get(0).getPlayerPoints();
        for (Player p:arrayPlayers) {
            if ( p.getPlayerPoints() >= MAX)
                winner = p.getNickname();
        }
        return winner;
    }

    /**
     * Method that create an instance of GameStateChangedArgs.
     * It will be called when something in the model has changed and notifies that to controller and view.
     */
    void notifyGameStateChanged(){
        GameStateChangedArgs args = new GameStateChangedArgs();
        gameStateChangedArgsObservable.Notify(args);
    }

    /**
     * Method that creates an instance of EndGameArgs.
     * it will be called when the game has to end and notifies the winner's nickname.
     *
     * @param winner nickname of the player that has win the game.
     */
    void notifyEndGameState(String winner ){
        EndGameArgs args = new EndGameArgs(winner);
        endGameArgsObservable.Notify(args);
    }

    /**
     * Method that check if next turn will be the last.
     *
     * @return true if the last turn will be the last
     */
    public boolean isLastTurn(){
        return lastTurn;
    }

}
