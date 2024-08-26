package it.polimi.ingsw.Model;

import java.io.Serializable;

/**
 * This class represents a single personal goal.
 */
public class PersonalGoal implements Serializable {
    /**
     * Integer's array that represents the point a player can earn.
     */
    private static final int [] points = { 1,2,4,6,9,12};

    /**
     * Column and position of the tiles in a personal goal.
     */
    private PersonalGoalWithPosition[] goalObjectWithPositions;

    /**
     * Class constructor.
     * Creates a personal goal, a class that has two coordinate for row and
     * column and one parameter for the tile card type.
     *
     * @param goalObjectWithPositions set of position and tile's type
     */
    public PersonalGoal(PersonalGoalWithPosition[] goalObjectWithPositions){
        this.goalObjectWithPositions = goalObjectWithPositions;
        for( int i = 0; i < goalObjectWithPositions.length ; i++){
            for ( int j = 0; j <goalObjectWithPositions.length; j++){
                if( i != j) {
                    if (goalObjectWithPositions[i].getX() == goalObjectWithPositions[j].getX() && goalObjectWithPositions[i].getY() == goalObjectWithPositions[j].getY())
                        throw new RuntimeException("Coordinates not valid.");
                }
            }

        }
    }

    /**
     * Confronts personal goal and bookshelf of the player finding equals tiles in equals position.
     *
     * @param bookshelf player's bookshelf.
     * @return how many tiles are in the same position between bookshelf and the personal goal.
     */
    public int countGoalReached( BookShelf bookshelf){
        int objectsInCorrectPosition = 0;
        for(int i = 0; i < bookshelf.getBookshelf().length; i++){
            for ( int j = 0; j< bookshelf.getBookshelf()[i].length; j++){
                TileType objectToControl = bookshelf.getBookshelf()[i][j].getObjectType();
                for (PersonalGoalWithPosition goalObject:this.goalObjectWithPositions) {
                    boolean isInCorrectPosition = goalObject.getX() == i && goalObject.getY()== j && goalObject.getType().equals(objectToControl);
                    if ( isInCorrectPosition)
                        objectsInCorrectPosition++;
                }

            }
        }

        return objectsInCorrectPosition;
    }

    /**
     * Creates personal goal number 1.
     *
     * @return personal goal number 1.
     */
    public static PersonalGoal Goal1(){
        PersonalGoalWithPosition Pos1 = new PersonalGoalWithPosition(0,0, TileType.PLANTS);
        PersonalGoalWithPosition Pos2 = new PersonalGoalWithPosition(0,2, TileType.FRAMES);
        PersonalGoalWithPosition Pos3 = new PersonalGoalWithPosition(1,4, TileType.CATS);
        PersonalGoalWithPosition Pos4 = new PersonalGoalWithPosition(2,3, TileType.BOOKS);
        PersonalGoalWithPosition Pos5 = new PersonalGoalWithPosition(3,1, TileType.TOYS);
        PersonalGoalWithPosition Pos6 = new PersonalGoalWithPosition(5,2, TileType.TROPHIES);
        PersonalGoalWithPosition[] array = {Pos1, Pos2, Pos3, Pos4, Pos5, Pos6};
        return new PersonalGoal(array);
    }

    /**
     * Creates personal goal number 2.
     *
     * @return personal goal number 2.
     */
    public static PersonalGoal Goal2(){
        PersonalGoalWithPosition Pos1 = new PersonalGoalWithPosition(1,1, TileType.PLANTS);
        PersonalGoalWithPosition Pos2 = new PersonalGoalWithPosition(2,0, TileType.CATS);
        PersonalGoalWithPosition Pos3 = new PersonalGoalWithPosition(2,2, TileType.TOYS);
        PersonalGoalWithPosition Pos4 = new PersonalGoalWithPosition(3,4, TileType.BOOKS);
        PersonalGoalWithPosition Pos5 = new PersonalGoalWithPosition(4,3, TileType.TROPHIES);
        PersonalGoalWithPosition Pos6 = new PersonalGoalWithPosition(5,4, TileType.FRAMES);
        PersonalGoalWithPosition[] array = {Pos1, Pos2, Pos3, Pos4, Pos5, Pos6};
        return new PersonalGoal(array);
    }

    /**
     * Creates personal goal number 3.
     *
     * @return personal goal number 3.
     */
    public static PersonalGoal Goal3(){
        PersonalGoalWithPosition Pos1 = new PersonalGoalWithPosition(1,0, TileType.FRAMES);
        PersonalGoalWithPosition Pos2 = new PersonalGoalWithPosition(1,3, TileType.TOYS);
        PersonalGoalWithPosition Pos3 = new PersonalGoalWithPosition(2,2, TileType.PLANTS);
        PersonalGoalWithPosition Pos4 = new PersonalGoalWithPosition(3,1, TileType.CATS);
        PersonalGoalWithPosition Pos5 = new PersonalGoalWithPosition(3,4, TileType.TROPHIES);
        PersonalGoalWithPosition Pos6 = new PersonalGoalWithPosition(5,0, TileType.BOOKS);
        PersonalGoalWithPosition[] array = {Pos1, Pos2, Pos3, Pos4, Pos5, Pos6};
        return new PersonalGoal(array);
    }

    /**
     * Creates personal goal number 4.
     *
     * @return personal goal number 4.
     */
    public static PersonalGoal Goal4(){
        PersonalGoalWithPosition Pos1 = new PersonalGoalWithPosition(0,4, TileType.TOYS);
        PersonalGoalWithPosition Pos2 = new PersonalGoalWithPosition(2,0, TileType.TROPHIES);
        PersonalGoalWithPosition Pos3 = new PersonalGoalWithPosition(2,2, TileType.FRAMES);
        PersonalGoalWithPosition Pos4 = new PersonalGoalWithPosition(3,3, TileType.PLANTS);
        PersonalGoalWithPosition Pos5 = new PersonalGoalWithPosition(4,1, TileType.BOOKS);
        PersonalGoalWithPosition Pos6 = new PersonalGoalWithPosition(4,2, TileType.CATS);
        PersonalGoalWithPosition[] array = {Pos1, Pos2, Pos3, Pos4, Pos5, Pos6};
        return new PersonalGoal(array);
    }

    /**
     * Creates personal goal number 5.
     *
     * @return personal goal number 5.
     */
    public static PersonalGoal Goal5(){
        PersonalGoalWithPosition Pos1 = new PersonalGoalWithPosition(1,1, TileType.TROPHIES);
        PersonalGoalWithPosition Pos2 = new PersonalGoalWithPosition(3,1, TileType.FRAMES);
        PersonalGoalWithPosition Pos3 = new PersonalGoalWithPosition(3,2, TileType.BOOKS);
        PersonalGoalWithPosition Pos4 = new PersonalGoalWithPosition(4,4, TileType.PLANTS);
        PersonalGoalWithPosition Pos5 = new PersonalGoalWithPosition(5,0, TileType.TOYS);
        PersonalGoalWithPosition Pos6 = new PersonalGoalWithPosition(5,3, TileType.CATS);
        PersonalGoalWithPosition[] array = {Pos1, Pos2, Pos3, Pos4, Pos5, Pos6};
        return new PersonalGoal(array);
    }

    /**
     * Creates personal goal number 6.
     *
     * @return personal goal number 6.
     */
    public static PersonalGoal Goal6(){
        PersonalGoalWithPosition Pos1 = new PersonalGoalWithPosition(0,2, TileType.TROPHIES);
        PersonalGoalWithPosition Pos2 = new PersonalGoalWithPosition(0,4, TileType.CATS);
        PersonalGoalWithPosition Pos3 = new PersonalGoalWithPosition(2,3, TileType.BOOKS);
        PersonalGoalWithPosition Pos4 = new PersonalGoalWithPosition(4,1, TileType.TOYS);
        PersonalGoalWithPosition Pos5 = new PersonalGoalWithPosition(4,3, TileType.FRAMES);
        PersonalGoalWithPosition Pos6 = new PersonalGoalWithPosition(5,0, TileType.PLANTS);
        PersonalGoalWithPosition[] array = {Pos1, Pos2, Pos3, Pos4, Pos5, Pos6};
        return new PersonalGoal(array);
    }

    /**
     * Creates personal goal number 7.
     *
     * @return personal goal number 7.
     */
    public static PersonalGoal Goal7(){
        PersonalGoalWithPosition Pos1 = new PersonalGoalWithPosition(0,0, TileType.CATS);
        PersonalGoalWithPosition Pos2 = new PersonalGoalWithPosition(1,3, TileType.FRAMES);
        PersonalGoalWithPosition Pos3 = new PersonalGoalWithPosition(2,1, TileType.PLANTS);
        PersonalGoalWithPosition Pos4 = new PersonalGoalWithPosition(3,0, TileType.TROPHIES);
        PersonalGoalWithPosition Pos5 = new PersonalGoalWithPosition(4,4, TileType.TOYS);
        PersonalGoalWithPosition Pos6 = new PersonalGoalWithPosition(5,2, TileType.BOOKS);
        PersonalGoalWithPosition[] array = {Pos1, Pos2, Pos3, Pos4, Pos5, Pos6};
        return new PersonalGoal(array);
    }

    /**
     * Creates personal goal number 8.
     *
     * @return personal goal number 8.
     */
    public static PersonalGoal Goal8(){
        PersonalGoalWithPosition Pos1 = new PersonalGoalWithPosition(0,4, TileType.FRAMES);
        PersonalGoalWithPosition Pos2 = new PersonalGoalWithPosition(1,1, TileType.CATS);
        PersonalGoalWithPosition Pos3 = new PersonalGoalWithPosition(2,2, TileType.TROPHIES);
        PersonalGoalWithPosition Pos4 = new PersonalGoalWithPosition(3,0, TileType.PLANTS);
        PersonalGoalWithPosition Pos5 = new PersonalGoalWithPosition(4,3, TileType.BOOKS);
        PersonalGoalWithPosition Pos6 = new PersonalGoalWithPosition(5,3, TileType.TOYS);
        PersonalGoalWithPosition[] array = {Pos1, Pos2, Pos3, Pos4, Pos5, Pos6};
        return new PersonalGoal(array);
    }

    /**
     * Creates personal goal number 9.
     *
     * @return personal goal number 9.
     */
    public static PersonalGoal Goal9(){
        PersonalGoalWithPosition Pos1 = new PersonalGoalWithPosition(0,2, TileType.PLANTS);
        PersonalGoalWithPosition Pos2 = new PersonalGoalWithPosition(2,2, TileType.PLANTS);
        PersonalGoalWithPosition Pos3 = new PersonalGoalWithPosition(3,4, TileType.PLANTS);
        PersonalGoalWithPosition Pos4 = new PersonalGoalWithPosition(4,1, TileType.PLANTS);
        PersonalGoalWithPosition Pos5 = new PersonalGoalWithPosition(4,4, TileType.PLANTS);
        PersonalGoalWithPosition Pos6 = new PersonalGoalWithPosition(5,0, TileType.PLANTS);
        PersonalGoalWithPosition[] array = {Pos1, Pos2, Pos3, Pos4, Pos5, Pos6};
        return new PersonalGoal(array);
    }

    /**
     * Creates personal goal number 10.
     *
     * @return personal goal number 10.
     */
    public static PersonalGoal Goal10(){
        PersonalGoalWithPosition Pos1 = new PersonalGoalWithPosition(0,4, TileType.TOYS);
        PersonalGoalWithPosition Pos2 = new PersonalGoalWithPosition(1,1, TileType.CATS);
        PersonalGoalWithPosition Pos3 = new PersonalGoalWithPosition(2,2, TileType.BOOKS);
        PersonalGoalWithPosition Pos4 = new PersonalGoalWithPosition(3,0, TileType.TROPHIES);
        PersonalGoalWithPosition Pos5 = new PersonalGoalWithPosition(4,3, TileType.PLANTS);
        PersonalGoalWithPosition Pos6 = new PersonalGoalWithPosition(5,3, TileType.FRAMES);
        PersonalGoalWithPosition[] array = {Pos1, Pos2, Pos3, Pos4, Pos5, Pos6};
        return new PersonalGoal(array);
    }

    /**
     * Creates personal goal number 11.
     *
     * @return personal goal number 11.
     */
    public static PersonalGoal Goal11(){
        PersonalGoalWithPosition Pos1 = new PersonalGoalWithPosition(0,2, TileType.PLANTS);
        PersonalGoalWithPosition Pos2 = new PersonalGoalWithPosition(1,1, TileType.BOOKS);
        PersonalGoalWithPosition Pos3 = new PersonalGoalWithPosition(2,0, TileType.TOYS);
        PersonalGoalWithPosition Pos4 = new PersonalGoalWithPosition(3,3, TileType.FRAMES);
        PersonalGoalWithPosition Pos5 = new PersonalGoalWithPosition(4,1, TileType.CATS);
        PersonalGoalWithPosition Pos6 = new PersonalGoalWithPosition(5,3, TileType.TROPHIES);
        PersonalGoalWithPosition[] array = {Pos1, Pos2, Pos3, Pos4, Pos5, Pos6};
        return new PersonalGoal(array);
    }

    /**
     * Creates personal goal number 12.
     *
     * @return personal goal number 12.
     */
    public static PersonalGoal Goal12(){
        PersonalGoalWithPosition Pos1 = new PersonalGoalWithPosition(0,2, TileType.BOOKS);
        PersonalGoalWithPosition Pos2 = new PersonalGoalWithPosition(1,1, TileType.PLANTS);
        PersonalGoalWithPosition Pos3 = new PersonalGoalWithPosition(2,2, TileType.FRAMES);
        PersonalGoalWithPosition Pos4 = new PersonalGoalWithPosition(3,3, TileType.TROPHIES);
        PersonalGoalWithPosition Pos5 = new PersonalGoalWithPosition(4,4, TileType.TOYS);
        PersonalGoalWithPosition Pos6 = new PersonalGoalWithPosition(5,0, TileType.CATS);
        PersonalGoalWithPosition[] array = {Pos1, Pos2, Pos3, Pos4, Pos5, Pos6};
        return new PersonalGoal(array);
    }

    /**
     * Getter to return personal goal represented as positions of tiles.
     *
     * @return the personal goal represented as positions of tiles.
     */
    public PersonalGoalWithPosition[] getGoalObjectWithPositions() {
        return goalObjectWithPositions;
    }
}




