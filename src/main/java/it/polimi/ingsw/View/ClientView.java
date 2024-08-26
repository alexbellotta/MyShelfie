package it.polimi.ingsw.View;
import it.polimi.ingsw.Model.PersonalGoalWithPosition;
import it.polimi.ingsw.Model.Tile;
import it.polimi.ingsw.DTO.GameDto;
import it.polimi.ingsw.Util.IObservable;
import it.polimi.ingsw.Util.Observable;
import it.polimi.ingsw.Util.PlaceObjectArgs;
import it.polimi.ingsw.Util.TakeObjectArgs;

/**
 * This class represents the object used to store game information retrieved from the server.
 * It makes use of Data Transfer Object (it.polimi.ingsw.DTO) pattern to keep track of the current state
 * of game's elements.
 */
public class ClientView implements VirtualView,ViewShowElement{
    /**
     * The game it.polimi.ingsw.DTO storing general game information.
     */
    public GameDto lastGameReceived;

    /**
     * Player's nickname.
     */
    private String nickname;

    /**
     * Observable for place object.
     */
    public final Observable<PlaceObjectArgs> placeObjectArgsObservable = new Observable<>();

    /**
     * Observable for take object.
     */
    public final Observable<TakeObjectArgs> takeObjectArgsObservable = new Observable<>();

    /**
     * Class constructor.
     * Initializes player's nickname.
     *
     * @param nickname player's nickname.
     */
    public ClientView(String nickname){
        this.nickname = nickname;
    }

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
     * @return take object observable.
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
    public void NotifyGameChanged(GameDto game) {
        lastGameReceived = game;
        // aggioranre le grafiche
        //if(nickname.equals(this.lastGameReceived.getCurrentPlayer().getNickname()))
    }

    /**
     * Method to show an error message.
     *
     * @param message the message to show.
     */
    @Override
    public void ShowMessage(String message) {System.out.println("errore: " + message +"(form CView)");
    }

    /**
     * Getter for current player's nickname.
     *
     * @return current player's nickname from the last game received.
     */
    @Override
    public String GetNickname() {
        return lastGameReceived.getCurrentPlayer().getNickname();
    }


    public void actionPlaceObject(){
    }

    public void actionTakeObjectCard(){
    }

    public void reorderHandTile(){
    }

    /**
     * Method to print board on the CLI.
     */
    public void showBoard(){
        System.out.println("board di gioco : \n");
        for (int j = 0; j < this.lastGameReceived.getBoard().length; j++)
            System.out.format("%12S", j);
        System.out.println();
        for(int i = 0; i < this.lastGameReceived.getBoard().length; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < this.lastGameReceived.getBoard().length; j++) {
                System.out.format("%12S", this.lastGameReceived.getBoard()[i][j].getObjectType());
            }
            System.out.println();
        }
    }

    /**
     * Method to print the bookshelf in the CLI.
     */
    public void showBookshelf(){
        System.out.println("bookshelf personale : \n");
        for (int j = 0; j < lastGameReceived.getBookshelf(nickname)[0].length; j++)
            System.out.format("%12S", j);
        System.out.println();
        for(int i = 0; i < lastGameReceived.getBookshelf(nickname).length; i++) {
            System.out.print("" + i + "");
            for (int j = 0; j < lastGameReceived.getBookshelf(nickname)[0].length; j++) {
                System.out.format("%12S", lastGameReceived.getBookshelf(nickname)[i][j].getObjectType());
            }
            System.out.println();
        }
    }

    /**
     * Method to print the personal goal.
     */
    public void showPersonalGoal(){

        System.out.println("Personal Goal : \n");

        for (PersonalGoalWithPosition p: lastGameReceived.getPersonalGoal(nickname).getGoalObjectWithPositions()) {
            System.out.println("x :"+ p.getX()+ " y:" + p.getY() + " type:" + p.getType());
        }

    }

    /**
     * Method to print player's points.
     */
    public void showMyPoint(){
        System.out.println(lastGameReceived.getPoint(nickname));
    }

    /**
     * Method to print last hand drawn.
     */
    @Override
    public void showhand() {
        System.out.println("la tua mano ");
        for (Tile t: lastGameReceived.getHand(nickname)) {
            System.out.println("-" + t);
        }
    }
}
