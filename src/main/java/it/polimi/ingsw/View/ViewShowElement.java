package it.polimi.ingsw.View;

/**
 * Interface with methods to show the current status
 * of changeable parts of the game.
 */
public interface ViewShowElement {
    //void actionPlaceObject();
   // void actionTakeObjectCard();
    //Non va bene così
    //void reorderHandTile();

    /**
     * Method to show the board.
     */
    void showBoard();

    /**
     * Method to show the bookshelf.
     */
    void showBookshelf();

    /**
     * Method to show the player's points.
     */
    void showMyPoint();

    /**
     * Method to show the personal goal pattern.
     */
    void showPersonalGoal();

    /**
     * Method to show the player's drawn hand.
     */
    void showhand();
}
