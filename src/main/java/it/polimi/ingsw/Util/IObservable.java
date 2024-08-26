package it.polimi.ingsw.Util;

/**
 * Interface representing generic version of Observable pattern.
 *
 * @param <T> generic type.
 */
public interface IObservable<T>{

    /**
     * Generic version of Observable's update method
     *
     * @param observer generic observer
     */
    void Subscribe( IObserver<T> observer);
}
