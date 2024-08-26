package it.polimi.ingsw.CommunicationProtocol.LoginMessage;

import it.polimi.ingsw.CommunicationProtocol.ProtocolVisitor;

public class AcceptedInitMessage extends LoginMessage {

    public AcceptedInitMessage(){

    }


    @Override
    public void accept(ProtocolVisitor visitor) {
        visitor.visit(this);
    }
}
