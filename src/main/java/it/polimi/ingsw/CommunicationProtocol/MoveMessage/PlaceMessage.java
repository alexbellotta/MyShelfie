package it.polimi.ingsw.CommunicationProtocol.MoveMessage;

import it.polimi.ingsw.CommunicationProtocol.Message;
import it.polimi.ingsw.CommunicationProtocol.ProtocolVisitor;

/**
 * Class representing a "place" message.
 */
public class PlaceMessage extends Message {
    /**
     * The column where player wants to put the drawn tiles.
     */
    private int column;

    /**
     * Class constructor.
     *
     * @param column The column where player wants to put the drawn tiles.
     */
    public PlaceMessage(int column){
        this.column= column;
    }

    /**
     * Getter for the column number.
     *
     * @return The columns number.
     */
    public int getColumn() {
        return column;
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
