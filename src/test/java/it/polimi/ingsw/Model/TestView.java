package it.polimi.ingsw.Model;


import it.polimi.ingsw.DTO.GameDto;
import it.polimi.ingsw.Util.IObservable;
import it.polimi.ingsw.Util.Observable;
import it.polimi.ingsw.Util.PlaceObjectArgs;
import it.polimi.ingsw.Util.TakeObjectArgs;
import it.polimi.ingsw.View.VirtualView;
public class TestView implements VirtualView {
    public GameDto lastGameReceived;
    public final Observable<PlaceObjectArgs> placeObjectArgsObservable = new Observable<>();

    public final Observable<TakeObjectArgs> takeObjectArgsObservable = new Observable<>();

    @Override
    public IObservable<PlaceObjectArgs> getPlaceObjectObservable() {return placeObjectArgsObservable;}


    @Override
    public IObservable<TakeObjectArgs> getTakeObjectObservable(){return takeObjectArgsObservable;}


    /**
     * getter of the nickname of the currentPlayer
     * @return nickname of current player by String
     */
    @Override
    public String GetNickname(){return lastGameReceived.getCurrentPlayer().toString();}

    @Override
    public void NotifyGameChanged(GameDto game){ lastGameReceived = game;}

    @Override
    public void ShowMessage(String message){

        System.out.println("errore: " + message);

    }
}
