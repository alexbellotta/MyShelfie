package it.polimi.ingsw.Util;


/**
 * Generic implementation of Observer class.
 *
 * @param <T> type of observer
 */
public interface IObserver<T>{
    /**
     * Method.
     *
     * @param next generic type.
     */
    void OnNext(T next);
}