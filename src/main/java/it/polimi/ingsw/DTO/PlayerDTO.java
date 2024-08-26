package it.polimi.ingsw.DTO;

import it.polimi.ingsw.Model.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class represents a usage of DTO pattern ( Data Transfer Object) for the players in the game.
 */
public class PlayerDTO implements Serializable {
    /**
     * Player's nickname.
     */
    private String nickname;

    /**
     * Matrix representation of bookshelf.
     */
    private Tile[][] bookshelf;

    /**
     * Player's personal goal.
     */
    private PersonalGoal personalGoal;

    /**
     * First common goal representation.
     */
    private CommonGoal commonGoal0;

    /**
     * Second common goal representation.
     */
    private CommonGoal commonGoal1;

    /**
     * Player's points.
     */
    private int playerPoints;

    /**
     * Tiles chosen by the player during take phase.
     */
    private ArrayList<Tile> cardsHand;

    /**
     * Class constructor.
     *
     * @param player an instance of Player class.
     */
    public PlayerDTO(Player player){
        this.nickname = player.getNickname();
        this.cardsHand = player.getCardsHand();
        this.commonGoal0 = player.getCommonGoal0();
        this.commonGoal1 = player.getCommonGoal1();
        this.bookshelf = player.getBookshelfFromPlayer().getBookshelf();
        this.playerPoints = player.getPlayerPoints();
        this.personalGoal = player.getPersonalGoal();
    }

    /**
     * Getter for the nickname.
     *
     * @return the player's nickname.
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Getter for the bookshelf.
     *
     * @return the bookshelf.
     */
    public Tile[][] getBookshelf() {
        return bookshelf;
    }

    /**
     * Getter for the personal goal.
     *
     * @return the personal goal.
     */
    public PersonalGoal getPersonalGoal() {
        return personalGoal;
    }

    /**
     * Getter for the first common goal.
     *
     * @return the first common goal.
     */
    public CommonGoal getCommonGoal0() {
        return commonGoal0;
    }

    /**
     * Getter for the second common goal.
     *
     * @return the second common goal.
     */
    public CommonGoal getCommonGoal1() {
        return commonGoal1;
    }

    /**
     * Getter for the player's points.
     *
     * @return the player's points.
     */
    public int getPlayerPoints() {
        return playerPoints;
    }

    /**
     * Getter for the player's hand.
     *
     * @return the player's hand.
     */
    public ArrayList<Tile> getCardsHand() {
        return cardsHand;
    }
}
