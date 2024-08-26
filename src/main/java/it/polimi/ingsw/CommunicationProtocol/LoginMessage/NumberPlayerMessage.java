package it.polimi.ingsw.CommunicationProtocol.LoginMessage;

import it.polimi.ingsw.CommunicationProtocol.ProtocolVisitor;

public class NumberPlayerMessage extends LoginMessage {

    int numberOfPlayer;

    public NumberPlayerMessage(int numberOfPlayer){
        this.numberOfPlayer = numberOfPlayer;
    }

    @Override
    public void accept(ProtocolVisitor visitor) {

        visitor.visit(this);
    }
}
