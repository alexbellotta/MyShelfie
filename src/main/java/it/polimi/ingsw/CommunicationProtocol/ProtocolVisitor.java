package it.polimi.ingsw.CommunicationProtocol;

import it.polimi.ingsw.CommunicationProtocol.MoveMessage.*;
import it.polimi.ingsw.CommunicationProtocol.LoginMessage.AcceptedInitMessage;
import it.polimi.ingsw.CommunicationProtocol.LoginMessage.NicknameMessage;
import it.polimi.ingsw.CommunicationProtocol.LoginMessage.NumberPlayerMessage;

public interface ProtocolVisitor {
    void visit(TakeMessage message);
    void visit(PlaceMessage message);
    void visit(NicknameMessage nicknameMessage);
    void visit(NumberPlayerMessage numberPlayerMessage);
    void visit(AcceptedInitMessage acceptedInitMessage);
    void visit(RejectedTakeMessage rejectedTakeMessage);
    void visit(RejectedPlaceMessage rejectedPlaceMessage);
    void visit(AcceptedMessagePhase acceptedMessagePhase);
}
