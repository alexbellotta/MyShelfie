package it.polimi.ingsw.Util;

/**
 * Class that create a notify for place object
 * when a user want to play this action, a new instance of this class will be created
 * and then will be notified
 */
public class PlaceObjectArgs {
    /**
     * column of the bookshelf
     * identified where player want to place the list of tile
     */
    public int column;

    /**
     * nickname of the player performing the action
     */
    public String nickname;

    /**
     * Constructor class.
     * For the "message" of the action
     * @param nickname player performing the action
     * @param column column of the bookshelf
     */
    public PlaceObjectArgs(String nickname,int column){
        this.column = column;
        this.nickname = nickname;
    }
}
