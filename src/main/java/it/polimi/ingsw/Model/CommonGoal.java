package it.polimi.ingsw.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class CommonGoal is used to handle the twelve common goals
 * of the game.
 */
public class CommonGoal implements Serializable {
    /**
     * Represents the list of the points of each common goal.
     * it is a decrescendo list of values, the first player that completes
     * the common goal takes the higher point, the others take the lowers points.
     */
    private final ArrayList<PointCard> points;

    /**
     * Indicates the type of the common goal
     */
    private final CommonGoalType goalType;

    /**
     * Constructor for the common goal class.
     *
     * @param goalType represents the type of the common goal
     * @param numberPlayers represents the number of player in the match
     */
    public CommonGoal(CommonGoalType goalType, int numberPlayers){
        this.goalType = goalType;
        this.points = new ArrayList<>();
        PointCard pointCardFour = new PointCard(4);
        PointCard pointCardEight = new PointCard(8);
        switch (numberPlayers) {
            case 2 -> {
                this.points.add(pointCardEight);
                this.points.add(pointCardFour);
            }
            case 3 -> {
                PointCard pointCardSix = new PointCard(6);
                this.points.add(pointCardEight);
                this.points.add(pointCardSix);
                this.points.add(pointCardFour);
            }
            case 4 -> {
                PointCard pointCardTwo = new PointCard(2);
                PointCard pointCardSix = new PointCard(6);
                this.points.add(pointCardEight);
                this.points.add(pointCardSix);
                this.points.add(pointCardFour);
                this.points.add(pointCardTwo);
            }
        }
    }

    /**
     * Method getPoints retrieves the list of the points.
     *
     * @return points of type ArrayList of PointCard
     */
    public ArrayList<PointCard> getPoints() {
        return points;
    }

    /**
     * Method getGoalType retrieves the type of the common goal.
     *
     * @return goalType of type CommonGoalType
     */
    public CommonGoalType getGoalType() {
        return goalType;
    }

    /**
     * Method checkCommonGoal check and calculates the points
     * a player has earned if he has completed a common goal.
     *
     * @param goalType of type CommonGoalType, represents the type of the common goal to check
     * @param bookShelf of type BookShelf, represents the player's bookshelf
     * @return givenPoints of type int, the points that a player has earned.
     */
    public int checkCommonGoal(CommonGoalType goalType, BookShelf bookShelf){
        int givenPoints;
        switch (goalType){
            case CG1:
                if(goalType.checkCG1(bookShelf)){
                    givenPoints = this.points.get(0).getPoint();
                    this.points.remove(0);
                    return givenPoints;
                }
                break;
            case CG2:
                if(goalType.checkCG2(bookShelf)){
                    givenPoints = this.points.get(0).getPoint();
                    this.points.remove(0);
                    return givenPoints;
                }
            case CG3:
                if(goalType.checkCG3(bookShelf)){
                    givenPoints = this.points.get(0).getPoint();
                    this.points.remove(0);
                    return givenPoints;
                }
                break;
            case CG4:
                if(goalType.checkCG4(bookShelf)){
                    givenPoints = this.points.get(0).getPoint();
                    this.points.remove(0);
                    return givenPoints;
                }
                break;
            case CG5:
                if(goalType.checkCG5(bookShelf)){
                    givenPoints = this.points.get(0).getPoint();
                    this.points.remove(0);
                    return givenPoints;
                }
                break;
            case CG6:
                if(goalType.checkCG6(bookShelf)){
                    givenPoints = this.points.get(0).getPoint();
                    this.points.remove(0);
                    return givenPoints;
                }
                break;
            case CG7:
                if(goalType.checkCG7(bookShelf)){
                    givenPoints = this.points.get(0).getPoint();
                    this.points.remove(0);
                    return givenPoints;
                }
                break;
            case CG8:
                if(goalType.checkCG8(bookShelf)){
                    givenPoints = this.points.get(0).getPoint();
                    this.points.remove(0);
                    return givenPoints;
                }
                break;
            case CG9:
                if(goalType.checkCG9(bookShelf)){
                    givenPoints = this.points.get(0).getPoint();
                    this.points.remove(0);
                    return givenPoints;
                }
                break;
            case CG10:
                if(goalType.checkCG10(bookShelf)){
                    givenPoints = this.points.get(0).getPoint();
                    this.points.remove(0);
                    return givenPoints;
                }
                break;
            case CG11:
                if(goalType.checkCG11(bookShelf)){
                    givenPoints = this.points.get(0).getPoint();
                    this.points.remove(0);
                    return givenPoints;
                }
                break;
            case CG12:
                if(goalType.checkCG12(bookShelf)){
                    givenPoints = this.points.get(0).getPoint();
                    this.points.remove(0);
                    return givenPoints;
                }
                break;
        }
        return 0;
    }
}
