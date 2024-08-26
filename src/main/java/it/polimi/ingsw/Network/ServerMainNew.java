package it.polimi.ingsw.Network;

import it.polimi.ingsw.Util.ConstantOfProject;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * This class represents the executable for the game on the server.
 */
public class ServerMainNew {
    /**
     * Server's port number.
     */
    private int portNumber;

    /**
     * Socket for server.
     */
    private ServerSocket serverSocket;

    /**
     * The game handler object.
     */
    private GameHandlerNew gameHandlernew;

    /**
     * clients list to handle their connections.
     */
    private ArrayList<ClientHandler> clientHandlers;

    /**
     * Class constructor.
     * Initializes the game handler and the clients' list.
     *
     * @param portNumber server's port number.
     */
    public ServerMainNew(int portNumber){
        this.portNumber = portNumber;
        clientHandlers = new ArrayList<>();
        gameHandlernew = new GameHandlerNew();
    }

    /**
     * The server's main.
     *
     * @param args the command line arguments.
     */
    public static void main (String[] args){
        int portNumber;
        if (args.length == 1) {
            portNumber = Integer.parseInt(args[0]);
        } else
            portNumber = ConstantOfProject.PORTNUMBER;

        ServerMainNew serverMain = new ServerMainNew(portNumber);

        serverMain.start();
    }

    /**
     * Method to read the connections from the given port
     * and to create threads to handle them.
     */
    public synchronized void start(){
        try {
            serverSocket = new ServerSocket(portNumber);
            System.out.println("server ready on port : " + portNumber);

        }catch(IOException e ){
            e.printStackTrace();
        }

        while(true){
            try {
                //if(gameHandlernew.getNumberOfPlayers().equals(-1) || gameHandlernew.getNumberOfPlayers().equals(clientHandlers.size()))
                Socket clientSocket = serverSocket.accept();
                System.out.println("ClientNew connected : " + clientSocket.getInetAddress());

                ClientHandler clientHandler = new ClientHandler(clientSocket, gameHandlernew, this);
                clientHandlers.add(clientHandler);

                // lista d ithread qui e poi join....
                Thread t = new Thread(clientHandler);
                t.start();


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Method to stop the server.
     */
    public synchronized void stop(){
        try {
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method to broadcast messages.
     *
     * @param message the message.
     * @param sender the sender.
     * @throws IOException when input mismatch
     */
    public synchronized void broadcastMessage(Object message, ClientHandler sender) throws IOException{

        for (ClientHandler client :clientHandlers) {
            if(client != sender)
                client.sendMessage(message);
        }
    }

    /**
     * Method to broadcast messages.
     *
     * @param message the message.
     * @param sender the sender.
     */
    public synchronized void broadcastMessageToOne(Object message, ClientHandler sender, ClientHandler receiver){

        for (ClientHandler client :clientHandlers) {
            if(client != sender && client.equals(receiver))
                client.sendMessage(message);
        }
    }
}
