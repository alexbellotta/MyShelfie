package it.polimi.ingsw.Util;

/**
 * Class that create a notify for take object
 * when a user want to play this action, a new instance of this class will be created
 * and then will be notified.
 */
public class TakeObjectArgs{
    /**
     * array of 1 to 3 tile draw out by the player
     */
    public int[][] drawArray;

    /**
     * nickname of the player performing the action
     */
    public String nickname;

    /**
     * Constructor class for the "message" of the action.
     *
     * @param nickname player performing the action.
     * @param drawArray list of tile draw out.
     */
    public TakeObjectArgs(String nickname, int[][] drawArray){
        this.drawArray = drawArray;
        this.nickname = nickname;
    }
}
