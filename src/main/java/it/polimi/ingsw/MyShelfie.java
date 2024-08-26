package it.polimi.ingsw;

import it.polimi.ingsw.Network.ClientMainNew;
import it.polimi.ingsw.Network.ServerMainNew;

import java.io.IOException;
import java.util.Scanner;


/**
 * MyShelfie class use to starting server and client
 * @author Pejano Mattia
 */
public class MyShelfie {

    /**
     * Method for launching cli interface.
     *
     * @param args args.
     * @throws IOException when input mismatch happens.
     */
    private static void launchCLI(String[] args) throws IOException{

        Scanner scanner = new Scanner(System.in);
        int launcher;

        System.out.println("Select server or client :\n1)server" +"\n" +"2)client");

        launcher = scanner.nextInt();

        switch(launcher) {
            case(1) ->{
                String[] argsServer = new String[] {args[1]};
                System.out.println("launching server");
                ServerMainNew.main(argsServer);

            }
            case(2)-> {
                System.out.println("launching client");
                ClientMainNew.main(args);
            }
        }

    }

    /**
     * it.polimi.ingsw.MyShelfie method.
     * @param args args.
     * @throws IOException for input mismatch.
     */
    public static void main(String[] args) throws IOException{

        System.out.println("Myshelfie boardgame starting...");
        launchCLI(args);

    }
}
