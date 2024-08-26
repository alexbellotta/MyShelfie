package it.polimi.ingsw.Controller;

import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.View.VirtualView;
import java.io.IOException;
import it.polimi.ingsw.DTO.*;
import it.polimi.ingsw.Util.ConstantOfProject;

/**
 * Controller class of MVC pattern.
 * Controls when and how the model changes.
 */
public class Controller{
    /**
     * The view of the game.
     */
    private VirtualView view;

    /**
     * Game representation.
     */
    private final Game game;

    /**
     * Controller constructor.
     * Initializes game and view.
     *
     * @param game the game representation.
     * @param view the view of the game.
     */
    public Controller(Game game,VirtualView view ){
        this.game = game;
        this.view = view;
        initializeGame(game);
        initializeView(view);
    }

    /**
     * Method to set which tiles the player has chosen to draw.
     *
     * @param nickname the player that perform the action
     * @param arrayCells the array of coordinates, representing the position of the chosen tiles
     * @throws Exception when the action performed is not valid
     */
    public void takeObject (String nickname, int[][] arrayCells ) throws Exception {
        if (game.getCurrentPlayer().getNickname().equals(nickname) && game.getCurrentPlayer().getCardsHand() == null ) {
            if( this.bookshelfEnoughSpaceForTakeObject(arrayCells)) {
                game.takeObject(arrayCells);
            }
            else{
                throw new RuntimeException("You don't have enough space in your bookshelf.");
            }
        }
        else{
            throw new RuntimeException("It's not your turn.");
        }
    }

    /**
     * Method to place the chosen tiles in the player's bookshelf.
     *
     * @param nickname the player's nickname performing the action.
     * @param column the column where the player want to place the tiles.
     * @throws IOException when the action performed is not valid.
     * @throws ClassNotFoundException when the action performed is not valid.
     * @throws InterruptedException when the action performed is not valid.
     */
    public void placeObject(String nickname, int column) throws IOException, ClassNotFoundException, InterruptedException{
        if( game.getCurrentPlayer().getNickname().equals(nickname) && game.getCurrentPlayer().getCardsHand() != null){
            if (this.bookshelfEnoughSpaceWithColumn(column) >= game.getCurrentPlayer().getCardsHand().size()) {
                game.placeObject(column);
            }
            else {
                throw new RuntimeException("You don't have enough space in your bookshelf.");
            }
        }
        else{
            throw new RuntimeException("Not your turn to place.");
        }
    }


    /**
     * Method to initialize the observable pattern between controller and model.
     *
     * @param game The class containing model's information.
     */
    void initializeGame(Game game) {
        game.getGameStateChangeObservable().Subscribe(args -> OnGameStateChanged(game));
        game.getEndGameArgsObservable().Subscribe(args ->OnEndGame(game, args.getWinner()));
    }

    /**
     * Method to initialize the observable pattern between controller and view.
     *
     * @param view the class containing the view's information to control.
     */
    void initializeView(VirtualView view){
        view.getPlaceObjectObservable().Subscribe(args -> {
            try {
                this.placeObject(args.nickname, args.column);
            }catch (Exception e){
                String errorMessage = e.getMessage();
                view.ShowMessage(errorMessage);
                throw new IllegalArgumentException("Exception from getPlace.");
            }
        });

        view.getTakeObjectObservable().Subscribe(args->{
            try{
                this.takeObject(args.nickname,args.drawArray);
            }
            catch(Exception e){
                String errorMessage = e.getMessage();
                view.ShowMessage(errorMessage);
                throw new IllegalArgumentException("Exception from getTake.");
            }
        });
    }

    /**
     * Method to create an instance to notify model's changes.
     *
     * @param game Class representing the model of the MVC pattern.
     */
    void OnGameStateChanged(Game game){
        GameDto gameDto = new GameDto(game);
        view.NotifyGameChanged(gameDto);
    }

    /**
     * Method that create an instance to notify model's changes and set winner.
     *
     * @param game Class representing the model of the MVC pattern.
     * @param winner Winner's nickname.
     */
    void OnEndGame(Game game, String winner){
        GameDto gameDto = new GameDto(game,winner);
        view.NotifyGameChanged(gameDto);
    }

    /**
     * Method that counts how many free cells has the bookshelf in a column.
     *
     * @param column the column to check.
     * @return number of free cells.
     */
    public int bookshelfEnoughSpaceWithColumn(int column){
        int freeSpace = 0;
        for(int i = 0; i < 6; i++){
            if( game.getCurrentPlayer().getBookshelfFromPlayer().getBookshelf()[i][column].getObjectType() == TileType.FREE)
                freeSpace++;
        }
        return freeSpace;
    }

    /**
     * Method that checks if there is enough space in the bookshelf to perform a "take" action.
     * The current player can use takeObject method only if the bookshelf has enough space in one of its columns.
     *
     * @return true if there is enough space
     */
    public boolean bookshelfEnoughSpaceForTakeObject(int[][] arrayCells){
        for(int i = 0; i < ConstantOfProject.BOOKSHELF_COL; i++){
            int freeSpace = 0;
            for( int j = 0 ; j < ConstantOfProject.BOOKSHELF_ROW; j++){
                if(game.getCurrentPlayer().getBookshelfFromPlayer().getBookshelf()[j][i].getObjectType() == TileType.FREE)
                    freeSpace++;
                if( freeSpace >= arrayCells.length){
                    return true;
                }
            }
        }
        return false;
    }

}
