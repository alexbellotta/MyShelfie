package it.polimi.ingsw.Network;

import java.io.ObjectInputStream;

/**
 * Class used to handle the messages' content.
 */
public class ObjectReceiver implements Runnable{
    /**
     * Object used to recover objects previously serialized.
     */
    private ObjectInputStream objectInputStream;

    /**
     * Class constructor.
     * Initializes the ObjectInputStream object.
     *
     * @param ois ObjectInputStream object
     */
    public ObjectReceiver(ObjectInputStream ois){
        this.objectInputStream = ois;
    }

    /**
     * Method to handle the received object.
     */
    public void run(){

        Object receivedObj;

    }
}
