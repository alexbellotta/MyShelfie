package it.polimi.ingsw.DTO;

import it.polimi.ingsw.Model.*;
import java.io.Serializable;
import java.util.ArrayList;


/**
 * This class represents a usage of DTO pattern ( Data Transfer Object).
 * An instance of this class is implemented when something in the model has
 * changed and the view has to be notified.
 * Inside this class there are changeable objects like board, bookshelf etc..
 */
public class GameDto implements Serializable {
    /**
     * Game's board simplified to a matrix of tile.
     */
    private Tile[][] board;

    /**
     * arrayList of the players in the game converted to a DTO class that represent a static vision of an instance.
     */
    private ArrayList<PlayerDTO> arrayPlayerDto;

    /**
     * Represents the current player.
     */
    private PlayerDTO currentPlayer;

    /**
     * Winner's nickname.
     */
    private String winner;

    /**
     * Notify the client when the last turn begins.
     */
    private boolean isLastTurn;

    /**
     * Class constructor.
     * This method creates an instance of GameDto that represents the static state of the model.
     * In this instance we can find all the elements needed for the view and the user.
     *
     * @param game an instance of the model.
     */
    public GameDto(Game game){
        board = game.getBoardFromGame().getBoard();
        isLastTurn = game.isLastTurn();
        //Creating new arrayList of player based on DTO pattern
        this.arrayPlayerDto = new ArrayList<>();
        for(Player player : game.getArrayListPlayer()){
            arrayPlayerDto.add(new PlayerDTO(player));
        }
        currentPlayer = new PlayerDTO(game.getCurrentPlayer());
    }

    /**
     * Class constructor.
     * This method creates an instance of GameDto that represents the static state of the model.
     * In this instance we can find all the elements needed for the view and the user.
     *
     * @param game game an instance of the model.
     * @param winner winner's nickname.
     */
    public GameDto(Game game, String winner){
        board = game.getBoardFromGame().getBoard();
        isLastTurn = game.isLastTurn();
        //Creating new arrayList of player based on DTO pattern
        this.arrayPlayerDto = new ArrayList<>();
        for(Player player : game.getArrayListPlayer()){
            arrayPlayerDto.add(new PlayerDTO(player));
        }
        currentPlayer = new PlayerDTO(game.getCurrentPlayer());
        this.winner = winner;
    }

    /**
     * Getter for the board.
     *
     * @return game's board.
     */
    public Tile[][] getBoard() {
        return board;
    }

    /**
     * Getter for the player's Array List.
     *
     * @return player's array list.
     */
    public ArrayList<PlayerDTO> getArrayPlayerDto() {
        return arrayPlayerDto;
    }

    /**
     * Getter for current player.
     *
     * @return the current player.
     */
    public PlayerDTO getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Method to show the winner.
     *
     * @return the winner's nickname.
     */
    public String whoIsWinner(){
        return winner;
    }

    /**
     * Method to show a player's points.
     *
     * @param nickname the player's nickname.
     * @return the player's points.
     */
    public int getPoint(String nickname){
        for (PlayerDTO p: arrayPlayerDto) {
            if (p.getNickname().equals(nickname)){
                return p.getPlayerPoints();
            }
        }
        return -1;
    }

    /**
     * Method to show a player's bookshelf.
     *
     * @param nickname the player's nickname.
     * @return the player's bookshelf.
     */
    public Tile[][] getBookshelf(String nickname){
        for (PlayerDTO p: arrayPlayerDto) {
            if(p.getNickname().equals(nickname))
                return p.getBookshelf();
        }
        return null;
    }

    /**
     * Getter to check if the next turn will be the last.
     *
     * @return true if the next turn will be the last.
     */
    public boolean getIsLastTurn() {
        return isLastTurn;
    }

    /**
     * Getter for the player's personal goal.
     *
     * @param nickname player's nickname.
     * @return the player's personal goal.
     */
    public PersonalGoal getPersonalGoal(String nickname){
        PersonalGoal personalGoal = null;
        for (PlayerDTO p: arrayPlayerDto) {
            if(p.getNickname().equals(nickname))
                personalGoal = p.getPersonalGoal();

        }
        return personalGoal;
    }

    /**
     * Getter for the player's hand.
     *
     * @param nickname the player's nickname.
     * @return the player's hand.
     */
    public ArrayList<Tile> getHand(String nickname){
        for (PlayerDTO p: arrayPlayerDto) {
            if(p.getNickname().equals(nickname))
                return p.getCardsHand();
        }
        return null;
    }

}
