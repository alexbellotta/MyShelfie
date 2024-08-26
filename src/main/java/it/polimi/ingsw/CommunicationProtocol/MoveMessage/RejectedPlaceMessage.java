package it.polimi.ingsw.CommunicationProtocol.MoveMessage;

import it.polimi.ingsw.CommunicationProtocol.Message;
import it.polimi.ingsw.CommunicationProtocol.ProtocolVisitor;

public class RejectedPlaceMessage extends Message {
    @Override
    public void accept(ProtocolVisitor visitor) {
        visitor.visit(this);
    }
}
