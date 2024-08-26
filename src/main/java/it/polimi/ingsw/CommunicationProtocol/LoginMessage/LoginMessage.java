package it.polimi.ingsw.CommunicationProtocol.LoginMessage;

import it.polimi.ingsw.CommunicationProtocol.ProtocolVisitor;

import java.io.Serializable;

public abstract class LoginMessage implements Serializable {

    public abstract void accept(ProtocolVisitor visitor);
}
