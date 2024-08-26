package it.polimi.ingsw.Network;

import it.polimi.ingsw.DTO.GameDto;
import it.polimi.ingsw.DTO.PlayerDTO;
import it.polimi.ingsw.View.ClientView;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * This class represent a single client that wants to connect to the server.
 */
public class ClientNew {
    /**
     * String to identify a single client.
     */
    private String hostname;

    /**
     * Server port number.
     */
    private int portNumber;

    /**
     * Socket for client.
     */
    private Socket clientSocket;

    /**
     * Object for deserialization.
     */
    private ObjectInputStream objectInputStream;

    /**
     * Object for serialization.
     */
    private ObjectOutputStream objectOutputStream;

    /**
     * Object to handle user input.
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Player's nickname.
     */
    private String nickname;

    /**
     * The view seen from the client.
     */
    private ClientView view;

    /**
     * Class constructor.
     * Initializes port number and host name.
     *
     * @param hostname string to identify a client.
     * @param portNumber server's port number.
     */
    public ClientNew( String hostname, int portNumber){
        this.hostname = hostname;
        this.portNumber = portNumber;
    }

    /**
     * Method to start the client.
     */
    public void start(){

        System.out.println("connecting to server");
        connectToStreamServer();

        //System.out.println(" configurazione del nickname");
        nicknameConfig();

        //System.out.println("waiting ...");
        configMessageFirstConnectionSetNumberPlayerOrWait();

        //System.out.println("entering pre lobby");
        preLobby();

        // lettura del game dalla prelobbygamehandler
        view.lastGameReceived = (GameDto)myReadObject();
        //updateview();
        System.out.println("giocatore corrente " + view.lastGameReceived.getCurrentPlayer().getNickname());


        String oldCurrentPlayer = "asd";
        String input;
        boolean lastTurnAlreadyNotified = false;

        // qui inizia la giocata

        while (true) {

            //updateview();

            if(nickname.equals(view.lastGameReceived.getCurrentPlayer().getNickname())){

                //pesca carte
                view.showBoard();
                System.out.println("è il tuo turno! seleziona dove pescare");
                //view.
                //view.drawuserinput
                drawUserInput();

                //clonna place
                view.showPersonalGoal();
                //view.showhand();
                view.showBookshelf();
                System.out.println("seleziona la colonna");
                columnUserInput();
            }

            view.NotifyGameChanged((GameDto) myReadObject());


            if(!oldCurrentPlayer.equals(view.lastGameReceived.getCurrentPlayer().getNickname())){
                if(!view.lastGameReceived.getCurrentPlayer().getNickname().equals(nickname))
                    System.out.println("è il turno di " + view.lastGameReceived.getCurrentPlayer().getNickname());
                oldCurrentPlayer = view.lastGameReceived.getCurrentPlayer().getNickname();
            }



            if(view.lastGameReceived.whoIsWinner()!= null)
                endGamePrint();

            if (view.lastGameReceived.getIsLastTurn() && lastTurnAlreadyNotified){
                System.out.println("\n -------- INIZIO ULTIMO TURNO -------- \n");
                lastTurnAlreadyNotified = false;
            }

            threadSleep(100);

            //chiusura del client e fine del gioco
        }
    }

    /**
     * Prints the end game display.
     */
    public void endGamePrint(){
        System.out.println("il vincitore è " + view.lastGameReceived.whoIsWinner());
        ArrayList<PlayerDTO> playerDto = view.lastGameReceived.getArrayPlayerDto();

        for (int i = 0;i < playerDto.size(); i++) {
            System.out.format("%1$5s","");
            System.out.println("il giocatore " + playerDto.get(i) + " ha ottenuto " + playerDto.get(i).getPlayerPoints() + "punti" );
        }

        System.out.println("premi invio per chiudere l'applicazione");
        scanner.nextLine();
        System.exit(0);
    }
    /**
     * Input validation (both local and on the server side) for the column in which to place the tiles in the bookshelf
     */
    public void columnUserInput(){
        String input;
        while (true){
            while(true){        //controllo input
                input = scanner.nextLine();
                if(Pattern.matches("[0-4]",input))
                    break;
                else
                    System.out.println("input non valido. specificare un numero compreso tra 0 e 4");
            }
            myWriteObject(Integer.parseInt(input));
            if (myReadObject().equals("[success]"))
                break;
            else {
                view.showBookshelf();
                System.out.println("colonna non valida. ritenta.");
            }
        }
    }
    /**
     *  Input validation (both local and on the server side) for the tiles to draw
     */
    public void drawUserInput() {
        String input;
        while(true){
            while (true){
                input = scanner.nextLine();
                try{
                    StringToIntParser(input);
                    break;
                }catch(Exception e) {
                    System.out.println("formato input non valido. ritenta.");
                }
            }
            myWriteObject(StringToIntParser(input));
            if (myReadObject().equals("[success]"))
                break;
            else
                System.out.println("pescata non valida. ritenta.");
        }
    }

    /*
    public static boolean isValidString(String str) {
        if (str == null || str.length() < 6 || str.charAt(0) != '[' || str.charAt(str.length() - 1) != ']')
            return false;

        String[] parts = str.substring(1, str.length() - 1).split("\\],\\[");
        if (parts.length < 1 || parts.length > 3)
            return false;

        for (String part : parts) {
            String[] nums = part.split(",");
            if (nums.length != 2)
                return false;
            try {
                int num1 = Integer.parseInt(nums[0]);
                int num2 = Integer.parseInt(nums[1]);
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }
     */

    /**
     *  preLobby methods. it keeps the client busy while waiting for the game to begin
     */
    public void preLobby() {
        Object obj;
        boolean flag = true;

        // creating the view
        view = new ClientView(nickname);

        while(true){
            obj = myReadObject();

            if (obj.equals("waiting..")){
                if (flag) {
                    System.out.println("waiting for all the players");
                    flag = false;
                }
            }
            else if (obj.equals("starting game")) {
                System.out.println("all client connected. thread id:" + Thread.currentThread().threadId());
                break;
            } else if (obj.equals("[too many players. you are being disconnected]")) {
                System.out.println("ci sono troppi giocatori nella lobby. stai per essere disconnesso.");
                System.exit(0);
            } else
                System.out.println("unexpected object");
        }


    }
    /**
     *  Configure the number of players for the game
     */
    public synchronized void configNumberPlayer(){
        String numberOfPlayer;

        System.out.println("Enter number of players (2,3,4)");
        numberOfPlayer = scanner.nextLine();

        while(!numberOfPlayer.equals("2") && !numberOfPlayer.equals("3") && !numberOfPlayer.equals("4")){

            System.out.println("numero inserito non valido, riprova");
            numberOfPlayer = scanner.nextLine();
        }
        myWriteObject(numberOfPlayer);
    }
    /**
     *  It allows the player to choose the players number or put them in waiting mode
     */
    public synchronized void configMessageFirstConnectionSetNumberPlayerOrWait(){
        String message;
        Object o;
        o = myReadObject();

        if(o.equals("inserisci il numero di giocatori sei il primo")){
            configNumberPlayer();
        } else if (o.equals("mancano ancora dei giocatori")) {
            System.out.println("aspettiamo altri giocatori ...");
        }
    }
    /**
     *  Set the nickname while checking its uniqueness in the lobby
     */
    public synchronized void nicknameConfig(){

        String nickname;
        Object obj;

        while(true) {
            System.out.println("Enter nickname: ");
            nickname = scanner.nextLine();
            myWriteObject(nickname);
            obj = myReadObject();
            if (!obj.equals("[error, nickname already in use]"))
                break;
            else
                System.out.println("nickname already in use! choose another one!");
        }
            this.nickname = nickname;
    }
    /**
     *  Set up the connection with the server
     */
    public synchronized void connectToStreamServer(){
        try{
            clientSocket = new Socket(hostname,portNumber);

            InputStream inputStream = clientSocket.getInputStream();
            OutputStream outputStream = clientSocket.getOutputStream();

            objectOutputStream = new ObjectOutputStream(outputStream);
            objectInputStream = new ObjectInputStream(inputStream);

        } catch (UnknownHostException e) {
            throw new RuntimeException("UnknownHostException riga 180");
        } catch (IOException e) {
            throw new RuntimeException("IOException  riga 180");
        }
    }


    // ____________________________--UTIL --------------------------

    /**
     * Updates the view after every changes.
     */
    public void updateview(){
        view.NotifyGameChanged(((GameDto) myReadObject()));
        view.showMyPoint();
        view.showBookshelf();
        // common goal
        // booskshelf
        // mano
    }
    /**
     * Parse the input string
     *
     * @param str string to parse
     * @return  array of parsed int[][]
     */
    public int[][] StringToIntParser(String str){

        int[][] array = Arrays.
                stream(str.substring(2,str.length()-2).split("\\]\\[")).
                map(row->row.split(",")).
                map(row->Arrays.stream(row).
                        filter(s->!s.isEmpty()).
                        mapToInt(Integer::parseInt).
                        toArray()).
                toArray((int[][]::new));

        return array;
    }

    /**
     * Reads objects from the network
     *
     * @return object read from the network stream
     */
    public Object myReadObject() {
        Object o;
        try {
            o = objectInputStream.readObject();
        } catch (IOException e) {
            System.out.println("There has been a network problem! the program its going to shut itself down");
            System.exit(0);
            throw new RuntimeException("error: the server closed the connection");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return o;
    }

    /**
     * Writes objects on the network.
     *
     * @param o object to write in the network stream.
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
     * Wrapper function to put the tread to sleep
     *
     * @param millis milliseconds for which the thread has to sleep
     */
    private void threadSleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
