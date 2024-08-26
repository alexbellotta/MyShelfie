package it.polimi.ingsw.View;

import it.polimi.ingsw.Util.*;
import it.polimi.ingsw.DTO.GameDto;

/**
 * Interface of the Virtual it.polimi.ingsw.View.
 * These methods are necessary to create a view in both side client and server.
 */
public interface VirtualView {
        /**
         * Getter for place object observable
         *
         * @return place object observable
         */
        public IObservable<PlaceObjectArgs> getPlaceObjectObservable();

        /**
         * Getter for take object observable
         *
         * @return take object observable
         */
        public IObservable<TakeObjectArgs> getTakeObjectObservable();

        /**
         * Method to notify changes.
         *
         * @param game current game.
         */
        public void NotifyGameChanged(GameDto game);

        /**
         * Method to print message.
         *
         * @param message the message to print.
         */
        public void ShowMessage ( String message);

        /**
         * Getter for the nickname of the view's owner
         */
        public String GetNickname();
}
