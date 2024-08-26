package it.polimi.ingsw.View;

import it.polimi.ingsw.DTO.GameDto;
import it.polimi.ingsw.Util.IObservable;
import it.polimi.ingsw.Util.Observable;
import it.polimi.ingsw.Util.PlaceObjectArgs;
import it.polimi.ingsw.Util.TakeObjectArgs;

/**
 * Class that represents the virtual side of the socket communication.
 * It represents a static vision of the model for TCP communication
 */
public class SocketServerView implements VirtualView {
    /**
     * Attributes fo game model.
     */
    public GameDto lastStateGameReceived;

    /**
     * Observable for place object.
     */
    public final Observable<PlaceObjectArgs> placeObjectArgsObservable = new Observable<>();

    /**
     * Observable for take object.
     */
    public final Observable<TakeObjectArgs> takeObjectArgsObservable = new Observable<>();

    /**
     * Getter for place object observable.
     *
     * @return place object observable.
     */
    @Override
    public IObservable<PlaceObjectArgs> getPlaceObjectObservable() {
        return placeObjectArgsObservable;
    }

    /**
     * Getter for take object observable.
     *
     * @return place take observable.
     */
    @Override
    public IObservable<TakeObjectArgs> getTakeObjectObservable() {
        return takeObjectArgsObservable;
    }

    /**
     * Method to notify when changes occurred in the game.
     *
     * @param game current game.
     */
    @Override
    public void NotifyGameChanged(GameDto game) {lastStateGameReceived = game;}

    /**
     * Method to show an error message.
     *
     * @param message the message to show.
     */
    @Override
    public void ShowMessage(String message) {System.out.println("errore: " + message + "(from SSView)");}

    /**
     * Getter for current player's nickname.
     *
     * @return current player's nickname from the last game received.
     */
    @Override
    public String GetNickname() {
        return lastStateGameReceived.getCurrentPlayer().toString();
    }

    /**
     * Method to print board on the CLI.
     */
    public void showBoard(){
        System.out.println("board di gioco : \n");
        for (int j = 0; j < lastStateGameReceived.getBoard().length; j++)
            System.out.format("%12S", j);
        System.out.println();
        for(int i = 0; i < lastStateGameReceived.getBoard().length; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < lastStateGameReceived.getBoard().length; j++) {
                System.out.format("%12S", lastStateGameReceived.getBoard()[i][j].getObjectType());
            }
            System.out.println();
        }
    }
}
