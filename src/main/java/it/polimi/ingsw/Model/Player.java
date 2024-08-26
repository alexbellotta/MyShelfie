package it.polimi.ingsw.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class represents the player in the game.
 */
public class Player implements Serializable {
    /**
     * Player's nickname.
     */
    private final String nickname;

    /**
     * Boolean to identify the first player that completes his bookshelf.
     */
    private boolean firstToComplete;

    /**
     * Player's bookshelf.
     */
    private final BookShelf bookshelf;

    /**
     * Player's personal goal.
     */
    private PersonalGoal personalGoal;

    /**
     * Common goal number 1.
     */
    private final CommonGoal commonGoal0;

    /**
     * Common goal number 2.
     */
    private final CommonGoal commonGoal1;

    /**
     * Points from common goal 1.
     */
    private int commonGoal0Points = 0;

    /**
     * Points from common goal 2.
     */
    private int commonGoal1Points = 0;

    /**
     * The groups of tile drawn by the player.
     */
    private ArrayList<Tile> cardsHand;

    /**
     * Class constructor.
     *
     * @param nickname player's nickname.
     * @param personalGoal player's personal goal.
     * @param commonGoals list of the common goal for the current match.
     */
    public Player(String nickname, PersonalGoal personalGoal, ArrayList<CommonGoal> commonGoals) {
        this.nickname = nickname;
        this.bookshelf = new BookShelf();
        this.commonGoal0 = commonGoals.get(0);
        this.commonGoal1 = commonGoals.get(1);
        this.personalGoal = personalGoal;
    }

    /**
     * Getter for the player's hand.
     *
     * @return the player's hand.
     */
    public ArrayList<Tile> getCardsHand(){return cardsHand;}

    /**
     * Getter for player's bookshelf.
     *
     * @return the player's bookshelf.
     */
    public BookShelf getBookshelfFromPlayer() {
        return bookshelf;
    }

    /**
     * Getter for player's nickname.
     *
     * @return the player's nickname.
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Tells a player if he is the first to fill his bookshelf.
     */
    public void setFirstToComplete(){
        firstToComplete = true;
    }

    /**
     * Method that check if a player is the first.
     * to complete his bookshelf.
     *
     * @return true if the player is the first to complete his bookshelf.
     */
    public boolean isFirstToComplete() {
        return firstToComplete;
    }

    /**
     * Place some cards in a temporary location, it set them.
     * down on the bookshelf with place hand method.
     *
     * @param array contains the tiles that the player wants to put in the bookshelf.
     */
    public void setHand(ArrayList<Tile> array) {
        cardsHand = new ArrayList<>();
        int i;

        if(array.size() == 0)
            throw new RuntimeException("There are no cards present in the hand. There should be at least one card present.");
        else
            for(i=0;i< array.size();i++)
                cardsHand.add(i,new Tile(array.get(i).getObjectType()));
    }

    /**
     * This method place in the bookshelf the cards given to the player by setHand method and.
     * automatically recalculate their points.
     *
     * @param c column in which a player places the tiles in the bookshelf.
     */
    public void placeHand(int c){
        boolean status = bookshelf.placeObject(c,cardsHand);
        if(!status)
            throw new RuntimeException("There was a problem while placing the player's hand in it's bookshelf. The player bookshelf has not been changed.");
        checkCommonGoals();
        cardsHand = null;
    }

    /**
     * Support function for getPlayerPoints method.
     */
    public void checkCommonGoals(){
        if(commonGoal0Points == 0)
            commonGoal0Points = commonGoal0.checkCommonGoal(commonGoal0.getGoalType(), bookshelf);
        if(commonGoal1Points == 0)
            commonGoal1Points = commonGoal1.checkCommonGoal(commonGoal1.getGoalType(), bookshelf);
    }

    /**
     * Calculates the total points scored by the player.
     * (ie from adjacent card + personal objective cards + common goals cards + firstToComplete card).
     *
     * @return the total points scored by the player.
     */
    public int getPlayerPoints() {
        int score = 0;
        //Adjacent cards points
        score += bookshelf.checkAdjacentObjects();
        //firstToComplete card points
        if(firstToComplete)
            score += 1;
        checkCommonGoals();
        score += commonGoal0Points;
        score += commonGoal1Points;
        //personal goals points
        switch (this.personalGoal.countGoalReached(bookshelf)) {
            case 0 -> score += 0;
            case 1 -> score += 1;
            case 2 -> score += 2;
            case 3 -> score += 4;
            case 4 -> score += 6;
            case 5 -> score += 9;
            case 6 -> score += 12;
            default -> throw new RuntimeException("Error while calculating player points.");

        }
        return score;
    }

    /**
     * Set the player personal goal, useful during debug/testing.
     *
     * @param goal the new personal goal of the player.
     */
    public void setPersonalGoal(PersonalGoal goal){
            this.personalGoal = goal;
    }

    /**
     * Getter for the personal goal.
     *
     * @return the personal goal.
     */
    public PersonalGoal getPersonalGoal(){
            return this.personalGoal;
    }

    /**
     * Method to check the personal goal.
     *
     * @param bookshelf player's bookshelf.
     */
    public void checkPersonalGoal(BookShelf bookshelf) {
        this.personalGoal.countGoalReached(bookshelf);
    }

    /**
     * Getter for the first common goal.
     *
     * @return The first common goal.
     */
    public CommonGoal getCommonGoal0(){
        return commonGoal0;
    }

    /**
     * Getter for the second common goal.
     *
     * @return The second common goal.
     */
    public CommonGoal getCommonGoal1() {
        return commonGoal1;
    }


}
