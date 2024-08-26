package it.polimi.ingsw.CommunicationProtocol;

import it.polimi.ingsw.DTO.GameDto;

/**
 * Class for the game dto messages.
 */
public class GameDtoMessage {
    /**
     * Actual game dto.
     */
    private GameDto gameDto;

    /**
     * Class constructor.
     *
     * @param gameDto actual game dto.
     */
    public GameDtoMessage(GameDto gameDto){
        this.gameDto= gameDto;
    }

    /**
     * Getter for the game dto.
     *
     * @return the actual game dto.
     */
    public GameDto getGameDto() {
        return gameDto;
    }
}
