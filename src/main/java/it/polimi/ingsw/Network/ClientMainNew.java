package it.polimi.ingsw.Network;

import it.polimi.ingsw.Util.ConstantOfProject;

/**
 * Class used to start a new client.
 */
public class ClientMainNew {
    /**
     * The new client.
     */
    private ClientNew clientNew;

    /**
     * Server's port number.
     */
    private int portNumber;

    /**
     * Machine identifier.
     */
    private String hostName;

    /**
     * Class constructor.
     * Initializes host name and port number.
     *
     * @param hostName Machine identifier.
     * @param portNumber Server's port number.
     */
    public ClientMainNew(String hostName,int portNumber){
        this.hostName = hostName;
        this.portNumber = portNumber;
    }

    /**
     * Main method for ClientMain class.
     *
     * @param args command line arguments.
     */
    public static void main (String[] args){
        String hostname;
        int portNumber;
        if(args.length==2){
            hostname= args[0];
            portNumber = Integer.parseInt(args[1]);
        }else {
            hostname = ConstantOfProject.SERVER_NAME;
            portNumber = ConstantOfProject.PORTNUMBER;
        }

        ClientMainNew clientMainNew = new ClientMainNew(hostname,portNumber);
        clientMainNew.start();
    }

    /**
     * Actually creates and starts a new client.
     */
    public void start(){
        clientNew = new ClientNew(hostName,portNumber);
        clientNew.start();
    }
}
