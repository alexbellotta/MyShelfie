package it.polimi.ingsw.CommunicationProtocol;

import java.io.Serializable;

public abstract class Message implements Serializable {
    public abstract void accept(ProtocolVisitor visitor);
}
