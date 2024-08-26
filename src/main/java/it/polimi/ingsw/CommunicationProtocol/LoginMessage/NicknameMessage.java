package it.polimi.ingsw.CommunicationProtocol.LoginMessage;

import it.polimi.ingsw.CommunicationProtocol.ProtocolVisitor;

public class NicknameMessage extends LoginMessage{

    String nickname;
    public NicknameMessage(String nickname){this.nickname = nickname;}

    @Override
    public void accept(ProtocolVisitor visitor) {
        visitor.visit(this);
    }
}
