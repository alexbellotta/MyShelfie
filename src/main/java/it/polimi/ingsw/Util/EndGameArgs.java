package it.polimi.ingsw.Util;

/**
 * Class used to notify that someone has won the game.
 * Instance created only when the game end.
 */
public class EndGameArgs {
    /**
     * winner's nickname.
     */
    private final String winner;

    /**
     * Class constructor.
     *
     * @param winner winner's nickname.
     */
    public EndGameArgs(String winner){
        this.winner = winner;
    }

    /**
     * Getter for the winner.
     *
     * @return winner's nickname.
     */
    public String getWinner() { return this.winner;}
}
