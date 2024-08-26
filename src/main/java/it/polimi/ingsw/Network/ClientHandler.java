package it.polimi.ingsw.Network;

import it.polimi.ingsw.DTO.GameDto;
import it.polimi.ingsw.Util.PlaceObjectArgs;
import it.polimi.ingsw.Util.TakeObjectArgs;

import java.io.*;
import java.net.Socket;

/**
 * This class handles the server's connection with a single client.
 */
public class ClientHandler implements Runnable{
    /**
     * The socket for the connection with this handler's client.
     */
    private Socket clientSocket;

    /**
     * Object to serializes primitive types.
     */
    private ObjectOutputStream objectOutputStream;

    /**
     * Object to deserializes primitive types.
     */
    private ObjectInputStream objectInputStream;

    /**
     * Client's game handler.
     */
    private GameHandlerNew gameHandlerNew;

    /**
     * The actual server.
     */
    private ServerMainNew server;

    /**
     * Player's nickname.
     */
    private String nickname;


    /**
     * Class constructor.
     * Initializes the socket, a game handler and the server.
     *
     * @param clientSocket socket for client.
     * @param gameHandlerNew The controller for the game this handler's client has joined
     * @param server
     */
    public ClientHandler(Socket clientSocket, GameHandlerNew gameHandlerNew, ServerMainNew server){
        this.clientSocket = clientSocket;
        this.gameHandlerNew = gameHandlerNew;
        this.server = server;

        openStream();
    }

    /**
     * Send message on the network.
     *
     * @param message the message to send.
     */
    public synchronized void sendMessage(Object message){
        myWriteObject(message);
    }

    /**
     * Method to stop the client.
     *
     * @throws IOException when input mismatch.
     */
    public synchronized void stopClient() throws IOException{
        objectOutputStream.close();
        objectInputStream.close();
        clientSocket.close();
    }

    /**
     * Actually opens the stream to receive the objects.
     */
    public synchronized void openStream(){
        try {
            InputStream inputStream = clientSocket.getInputStream();
            OutputStream outputStream = clientSocket.getOutputStream();

            objectOutputStream = new ObjectOutputStream(outputStream);
            objectInputStream = new ObjectInputStream(inputStream);
        }catch (IOException e ){
            e.printStackTrace();
        }
    }

    /**
     * Check and then set the nickname chosen by the player.
     */
    public synchronized void nicknameConfig(){
        String str;

        while(true) {
            str = myReadObject().toString();
            if (!gameHandlerNew.getNicknames().contains(str)) {
                myWriteObject("[nick accepted]");
                break;
            }
            else {
                myWriteObject("[error, nickname already in use]");
            }
        }
        this.nickname = str;
    }

    /**
     * Set number of players in the match.
     *
     * @return the number of players.
     */
    public synchronized Integer configNumberOfPlayers(){

        Integer numberOfPlayers = Integer.parseInt(myReadObject().toString());
        return numberOfPlayers;
    }

    /**
     * Allows the first client connected to config the number of
     * players in the match.
     */
    public synchronized void firstClientConnectToConfigNumberOfPlayers(){

        if(nickname.equals(gameHandlerNew.getNicknames().get(0))){

            myWriteObject("inserisci il numero di giocatori sei il primo");

            Integer numberOfPlayer = configNumberOfPlayers();
            System.out.println("numero dei giocatori " + numberOfPlayer);

            gameHandlerNew.setNumberOfPlayers(numberOfPlayer);


        }else {
            myWriteObject("mancano ancora dei giocatori");
        }
    }

    /**
     * Method that check if the game is in the pre lobby phase.
     * It means that there are missing players.
     *
     * @return true if the game is in the pre lobby phase.
     */
    public boolean preLobbyClientHandler(){

        while(true){
            myWriteObject("waiting..");

            if(gameHandlerNew.getNumberOfPlayers()  <= gameHandlerNew.getNicknames().size() && gameHandlerNew.getNumberOfPlayers() != -1){
                if(gameHandlerNew.getNicknames().indexOf(nickname) < gameHandlerNew.getNumberOfPlayers()){
                    myWriteObject("starting game");

                    System.out.println("starting game");
                    // create MVC
                    gameHandlerNew.createGame();
                    System.out.println("game created");

                    // sending first game
                    myWriteObject(new GameDto(gameHandlerNew.getGame()));

                    return false;
                }
                else{
                    myWriteObject("[too many players. you are being disconnected]");
                    System.out.println("too many players. " + nickname + " is getting disconnected.");
                    Thread.currentThread().interrupt();
                    return true;
                }
            }
            threadSleep(1000);
        }

    }

    /**
     * Allows the start of the game.
     */
    public void run(){

        //configuration nickname
        nicknameConfig();
        System.out.println("nick inserito " + nickname);

        // add new nickname at gameHandler nickname list
        gameHandlerNew.getNicknames().add(nickname);

        // setting the number of players only the client connected is the first of the list
        firstClientConnectToConfigNumberOfPlayers();

        // preLobby waiting creating game with all player necessary
        if(preLobbyClientHandler())     //if true it means the user handled by this thread is in excess, and it needs to close his thread on the server.
            return;



        while(true){

            if(nickname.equals(gameHandlerNew.getGame().getCurrentPlayer().getNickname())) {
                System.out.println("gioca il player:" + nickname);

                drawUserInputValidation();

                columnUserInputValidation();

            }

            threadSleep(300);

            myWriteObject(new GameDto(gameHandlerNew.getGame()));

        }
    }

    /**
     * Validation method for the user's input.
     */
    public void columnUserInputValidation(){
        while (true) {      //column validation. it keeps cycling back and forth between client and server till a "good" column is selected (ie one with enough space)
            int column = (int) myReadObject();
            System.out.println("piazza nella colonna " + column + " della sua libreria");

            try {
                gameHandlerNew.getSocketServerView().placeObjectArgsObservable.Notify((new PlaceObjectArgs(nickname, column)));
                myWriteObject("[success]");
                break;
            } catch (IllegalArgumentException e) {
                //System.out.println("error while placing object (from clientHandler)" + e.getMessage());
                myWriteObject("[error]");
            }
        }
    }

    /**
     * Validation method for the drawn player's hand.
     */
    public void drawUserInputValidation() {
        while(true) {       //draw validation. it keeps going back and forth till all the selected cells are drawable (ie not free nor lock)

            int[][] takeArray = (int[][]) myReadObject();
            System.out.print("pesca in ");
            for(int i=0;i<takeArray.length;i++)
                System.out.print("["+takeArray[i][0] + "," + takeArray[i][1] + "] ");
            try {
                gameHandlerNew.getSocketServerView().takeObjectArgsObservable.Notify(new TakeObjectArgs(nickname, takeArray));
                myWriteObject("[success]");
                break;
            }
            catch (IllegalArgumentException e) {
                //System.out.println("error while taking object (from clientHandler)" + e.getMessage());
                myWriteObject("[error]");
            }
        }
    }

    /**
     * Method to read objects in the network.
     *
     * @return the object read.
     */
    public Object myReadObject() {
        Object o;
        try {
            o = objectInputStream.readObject();
        } catch (IOException e) {
            System.out.println("There has been a network problem! the program its going to shut itself down");
            System.exit(0);
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return o;
    }

    /**
     * Write objects during the communication.
     *
     * @param o the object to write.
     */
    public void myWriteObject(Object o) {
        try {
            objectOutputStream.writeObject(o);
            objectOutputStream.flush();
            objectOutputStream.reset();
        } catch (IOException e) {
            System.out.println("There has been a network problem! the program its going to shut itself down");
            System.exit(0);
            throw new RuntimeException(e);
        }
    }

    /**
     * Personalized method to allow thread sleep.
     *
     * @param millis time of sleep.
     */
    private void threadSleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Getter for the current game handler.
     *
     * @return current game handler.
     */
    public GameHandlerNew getGameHandlerNew() {
        return gameHandlerNew;
    }

    /**
     * Set the game handler.
     *
     * @param gameHandlerNew the game handler to set.
     */
    public void setGameHandlerNew(GameHandlerNew gameHandlerNew) {
        this.gameHandlerNew = gameHandlerNew;
    }
}
