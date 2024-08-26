package it.polimi.ingsw.Util;

import java.util.ArrayList;

/**
 * Generic implementations of Observable pattern
 * this class contain the logic behind a generic observable
 *
 * @param <T> type
 */
public class Observable<T> implements IObservable<T> {
    /**
     * Locker objects.
     */
    private final Object locker = new Object();

    /**
     * Observers list.
     */
    private final ArrayList<IObserver<T>> observers = new ArrayList<>();

    /**
     * Method to subscribe observer.
     *
     * @param observer generic observer.
     */
    public void Subscribe( IObserver<T> observer){

        synchronized (locker){
            observers.add(observer);
        }
    }

    /**
     * Getter for the observers.
     *
     * @return observers list.
     */
    ArrayList<IObserver<T>> getObservers(){
        synchronized (locker){
            return new ArrayList<>(observers);
        }
    }

    /**
     * Method to notify observers.
     *
     * @param args generic.
     */
    public void Notify( T args ){
        ArrayList<IObserver<T>> observers = getObservers();
        for(IObserver<T> observer : observers){
            observer.OnNext(args);
        }
    }

    /**
     * Method to unsubscribe observer.
     *
     * @param observer the observe to unsubscribe.
     */
    public void UnSubscribe(IObserver<T> observer){
        synchronized (locker){
            observers.remove(observer);
        }
    }
}
