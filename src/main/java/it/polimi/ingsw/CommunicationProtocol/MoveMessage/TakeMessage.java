package it.polimi.ingsw.CommunicationProtocol.MoveMessage;

import it.polimi.ingsw.CommunicationProtocol.Message;
import it.polimi.ingsw.CommunicationProtocol.ProtocolVisitor;

/**
 * Class representing a "take" message.
 */
public class TakeMessage extends Message {
    /**
     * Tiles taken from the board.
     */
    private int[][] takeArray;

    /**
     * Class constructor.
     *
     * @param takeArray tiles taken from the board.
     */
    public TakeMessage(int[][] takeArray){
        this.takeArray = takeArray;
    }

    /**
     * Getter for the player's hand.
     *
     * @return the drawn tiles.
     */
    public int[][] getTakeArray() {
        return takeArray;
    }

    /**
     * Accept method for visitor pattern.
     *
     * @param visitor visitor protocol.
     */
    public void accept(ProtocolVisitor visitor){
        visitor.visit(this);
    }
}
