package it.polimi.ingsw.Model;

import java.io.Serializable;
import java.util.*;

/**
 * The Board class represents the state of the board during the game.
 */
public class Board implements Serializable {
    /**
     * Board of the game, as matrix of Tiles.
     */
    private Tile[][] board;

    /**
     * Class constructor.
     * Creation of an empty board dependent on the number of players in the game session.
     *
     * @param numberPlayers Number of players in the game session.
     */
    public Board(int numberPlayers) {
        switch (numberPlayers) {
            case 2 -> {
                int n2 = 7;
                Tile[][] emptyBoard2 = new Tile[n2][n2];
                for (int i = 0; i < n2; i++) {
                    for (int j = 0; j < n2; j++) {
                        switch (i) {
                            case 0 -> {
                                Integer[] J = {2, 3};
                                Tile tile;
                                if (Arrays.asList(J).contains(j)) {
                                    tile = new Tile(TileType.FREE);
                                }
                                else{
                                    tile = new Tile(TileType.LOCK);
                                }
                                emptyBoard2[i][j] = tile;
                            }
                            case 1, 5 -> {
                                Integer[] J = {2, 3, 4};
                                Tile tile;
                                if (Arrays.asList(J).contains(j)) {
                                    tile = new Tile(TileType.FREE);
                                }
                                else{
                                    tile = new Tile(TileType.LOCK);
                                }
                                emptyBoard2[i][j] = tile;
                            }
                            case 2 -> {
                                Integer[] J = {1, 2, 3, 4, 5, 6};
                                Tile tile;
                                if (Arrays.asList(J).contains(j)) {
                                    tile = new Tile(TileType.FREE);
                                }
                                else{
                                    tile = new Tile(TileType.LOCK);
                                }
                                emptyBoard2[i][j] = tile;
                            }
                            case 3 -> {
                                Tile tile = new Tile(TileType.FREE);
                                emptyBoard2[i][j] = tile;
                            }
                            case 4 -> {
                                Integer[] J = {0, 1, 2, 3, 4, 5};
                                Tile tile;
                                if (Arrays.asList(J).contains(j)) {
                                    tile = new Tile(TileType.FREE);
                                }
                                else{
                                    tile = new Tile(TileType.LOCK);
                                }
                                emptyBoard2[i][j] = tile;
                            }
                            case 6 -> {
                                Integer[] J = {3, 4};
                                Tile tile;
                                if (Arrays.asList(J).contains(j)) {
                                    tile = new Tile(TileType.FREE);
                                }
                                else{
                                    tile = new Tile(TileType.LOCK);
                                }
                                emptyBoard2[i][j] = tile;
                            }
                        }
                    }
                }
                this.board = emptyBoard2;
            }
            case 3 -> {
                int n3 = 9;
                Tile[][] emptyBoard3 = new Tile[n3][n3];
                for (int i = 0; i < n3; i++) {
                    for (int j = 0; j < n3; j++) {
                        switch (i) {
                            case 0 -> {
                                Tile tile;
                                if (j == 3) {
                                    tile = new Tile(TileType.FREE);
                                }
                                else{
                                    tile = new Tile(TileType.LOCK);
                                }
                                emptyBoard3[i][j] = tile;
                            }
                            case 1 -> {
                                Integer[] J = {3, 4};
                                Tile tile;
                                if (Arrays.asList(J).contains(j)) {
                                    tile = new Tile(TileType.FREE);
                                }
                                else{
                                    tile = new Tile(TileType.LOCK);
                                }
                                emptyBoard3[i][j] = tile;
                            }
                            case 2, 6 -> {
                                Integer[] J = {2, 3, 4, 5, 6};
                                Tile tile;
                                if (Arrays.asList(J).contains(j)) {
                                    tile = new Tile(TileType.FREE);
                                }
                                else{
                                    tile = new Tile(TileType.LOCK);
                                }
                                emptyBoard3[i][j] = tile;
                            }
                            case 3 -> {
                                Integer[] J = {2, 3, 4, 5, 6, 7, 8};
                                Tile tile;
                                if (Arrays.asList(J).contains(j)) {
                                    tile = new Tile(TileType.FREE);
                                }
                                else{
                                    tile = new Tile(TileType.LOCK);
                                }
                                emptyBoard3[i][j] = tile;
                            }
                            case 4 -> {
                                Integer[] J = {1, 2, 3, 4, 5, 6, 7};
                                Tile tile;
                                if (Arrays.asList(J).contains(j)) {
                                    tile = new Tile(TileType.FREE);
                                }
                                else{
                                    tile = new Tile(TileType.LOCK);
                                }
                                emptyBoard3[i][j] = tile;
                            }
                            case 5 -> {
                                Integer[] J = {0, 1, 2, 3, 4, 5, 6};
                                Tile tile;
                                if (Arrays.asList(J).contains(j)) {
                                    tile = new Tile(TileType.FREE);
                                }
                                else{
                                    tile = new Tile(TileType.LOCK);
                                }
                                emptyBoard3[i][j] = tile;
                            }
                            case 7 -> {
                                Integer[] J = {4, 5};
                                Tile tile;
                                if (Arrays.asList(J).contains(j)) {
                                    tile = new Tile(TileType.FREE);
                                }
                                else{
                                    tile = new Tile(TileType.LOCK);
                                }
                                emptyBoard3[i][j] = tile;
                            }
                            case 8 -> {
                                Tile tile;
                                if (j == 5){
                                    tile = new Tile(TileType.FREE);
                                }
                                else{
                                    tile = new Tile(TileType.LOCK);
                                }
                                emptyBoard3[i][j] = tile;
                            }
                        }
                    }
                }
                this.board = emptyBoard3;
            }
            case 4 -> {
                int n4 = 9;
                Tile[][] emptyBoard4 = new Tile[n4][n4];
                for (int i = 0; i < n4; i++) {
                    for (int j = 0; j < n4; j++) {
                        switch (i) {
                            case 0 -> {
                                Integer[] J = {3, 4};
                                Tile tile;
                                if (Arrays.asList(J).contains(j)) {
                                    tile = new Tile(TileType.FREE);
                                }
                                else{
                                    tile = new Tile(TileType.LOCK);
                                }
                                emptyBoard4[i][j] = tile;
                            }
                            case 1, 7 -> {
                                Integer[] J = {3, 4, 5};
                                Tile tile;
                                if (Arrays.asList(J).contains(j)) {
                                    tile = new Tile(TileType.FREE);
                                }
                                else{
                                    tile = new Tile(TileType.LOCK);
                                }
                                emptyBoard4[i][j] = tile;
                            }
                            case 2, 6 -> {
                                Integer[] J = {2, 3, 4, 5, 6};
                                Tile tile;
                                if (Arrays.asList(J).contains(j)) {
                                    tile = new Tile(TileType.FREE);
                                }
                                else{
                                    tile = new Tile(TileType.LOCK);
                                }
                                emptyBoard4[i][j] = tile;
                            }
                            case 3 -> {
                                Integer[] J = {1, 2, 3, 4, 5, 6, 7, 8};
                                Tile tile;
                                if (Arrays.asList(J).contains(j)) {
                                    tile = new Tile(TileType.FREE);
                                }
                                else{
                                    tile = new Tile(TileType.LOCK);
                                }
                                emptyBoard4[i][j] = tile;
                            }
                            case 4 -> {
                                Tile tile = new Tile(TileType.FREE);
                                emptyBoard4[i][j] = tile;
                            }
                            case 5 -> {
                                Integer[] J = {0, 1, 2, 3, 4, 5, 6, 7};
                                Tile tile;
                                if (Arrays.asList(J).contains(j)) {
                                    tile = new Tile(TileType.FREE);
                                }
                                else{
                                    tile = new Tile(TileType.LOCK);
                                }
                                emptyBoard4[i][j] = tile;
                            }
                            case 8 -> {
                                Integer[] J = {4, 5};
                                Tile tile;
                                if (Arrays.asList(J).contains(j)) {
                                    tile = new Tile(TileType.FREE);
                                }
                                else{
                                    tile = new Tile(TileType.LOCK);
                                }
                                emptyBoard4[i][j] = tile;
                            }
                        }
                    }
                }
                this.board = emptyBoard4;
            }
        }
    }

    /**
     * Method that controls the state of the board and checks if it needs a refill.
     *
     * @return TRUE if the board needs to be refilled, FALSE otherwise.
     */
    public boolean checkRefill(){
        int size = this.board.length;
        for(int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!(this.board[i][j].getObjectType() == TileType.FREE || this.board[i][j].getObjectType() == TileType.LOCK)) {
                    if (i == 0) {                       //Checking first row
                        if (((this.board[i + 1][j].getObjectType() != TileType.FREE) && (this.board[i + 1][j].getObjectType() != TileType.LOCK))
                                || ((this.board[i][j + 1].getObjectType() != TileType.FREE) && (this.board[i][j + 1].getObjectType() != TileType.LOCK))) {
                            return false;
                        }
                    } else if (i == size - 1) {         //Checking last row
                        if (((this.board[i][j + 1].getObjectType() != TileType.FREE) && (this.board[i][j + 1].getObjectType() != TileType.LOCK))) {
                            return false;
                        }
                    } else if (j == 0) {                //Checking first column
                        if (((this.board[i + 1][j].getObjectType() != TileType.FREE) && (this.board[i + 1][j].getObjectType() != TileType.LOCK))
                                || ((this.board[i][j + 1].getObjectType() != TileType.FREE) && (this.board[i][j + 1].getObjectType() != TileType.LOCK))) {
                            return false;
                        }
                    } else if (j == size - 1) {         //Checking last column
                        if (((this.board[i+1][j].getObjectType() != TileType.FREE) && (this.board[i+1][j].getObjectType() != TileType.LOCK))) {
                            return false;
                        }
                    } else {                            //Checking inner matrix
                        if (((this.board[i + 1][j].getObjectType() != TileType.FREE) && (this.board[i + 1][j].getObjectType() != TileType.LOCK))
                                || ((this.board[i][j + 1].getObjectType() != TileType.FREE) && (this.board[i][j + 1].getObjectType() != TileType.LOCK))) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * This method fills the empty spaces of the board with new Tiles if the result of checkRefill is TRUE.
     *
     * @param tileDeck The TileDeck (bag) used to refill the board.
     * @throws Exception when tile's deck is empty.
     */
    public void refill(TileDeck tileDeck) throws Exception {
        if (checkRefill()) {
            int size = this.board[0].length;
            for(Tile[] tiles : this.board){
                for(int j = 0; j < size; j++){
                    if(tileDeck.tileDeck.isEmpty()){
                        throw new Exception("Tile deck is empty");
                    }
                    if(tiles[j].getObjectType() == TileType.FREE){
                        tiles[j] = tileDeck.drawNext();
                    }
                }
            }
        }
    }

    /**
     * Method that returns the current state of the board.
     *
     * @return The current state of the board.
     */
    public Tile[][] getBoard(){
        Board tempboard = new Board(this.board.length);
        tempboard.board = this.board.clone();
        return tempboard.board;
    }

    /**
     * This method checks if the drawn tiles represent a valid move.
     *
     * @param drawnArray The matrix of positions of the drawn tiles.
     * @return TRUE if the drawn tiles represents a valid move, FALSE otherwise.
     */
    public boolean checkDraw(int[][] drawnArray){
        int[][] tempArray = drawnArray.clone();
        int t;

        switch (tempArray.length) {
            case 2 -> {
                if ((tempArray[0][0] != tempArray[1][0] || (tempArray[0][1] != tempArray[1][1] + 1 && tempArray[0][1] != tempArray[1][1] - 1))
                        && (tempArray[0][1] != tempArray[1][1] || (tempArray[0][0] != tempArray[1][0] + 1 && tempArray[0][0] != tempArray[1][0] - 1))) {
                    return false;
                }
            }
            case 3 -> {
                if ((tempArray[0][0] != tempArray[1][0] || tempArray[1][0] != tempArray[2][0])
                        && (tempArray[0][1] != tempArray[1][1] || tempArray[1][1] != tempArray[2][1])) {
                    return false;
                }
                if (tempArray[0][0] != tempArray[1][0]) {
                    if (tempArray[0][0] > tempArray[1][0]) {
                        t = tempArray[1][0];
                        tempArray[1][0] = tempArray[0][0];
                        tempArray[0][0] = t;
                    }
                    if (tempArray[0][0] > tempArray[2][0]) {
                        t = tempArray[2][0];
                        tempArray[2][0] = tempArray[0][0];
                        tempArray[0][0] = t;
                    }
                    if (tempArray[1][0] > tempArray[2][0]) {
                        t = tempArray[2][0];
                        tempArray[2][0] = tempArray[1][0];
                        tempArray[1][0] = t;
                    }
                    if (tempArray[1][0] != tempArray[0][0] + 1 || tempArray[1][0] != tempArray[2][0] - 1) {
                        return false;
                    }
                }
                if (tempArray[0][1] != tempArray[1][1]) {
                    if (tempArray[0][1] > tempArray[1][1]) {
                        t = tempArray[1][1];
                        tempArray[1][1] = tempArray[0][1];
                        tempArray[0][1] = t;
                    }
                    if (tempArray[0][1] > tempArray[2][1]) {
                        t = tempArray[2][1];
                        tempArray[2][1] = tempArray[0][1];
                        tempArray[0][1] = t;
                    }
                    if (tempArray[1][1] > tempArray[2][1]) {
                        t = tempArray[2][1];
                        tempArray[2][1] = tempArray[1][1];
                        tempArray[1][1] = t;
                    }
                    if (tempArray[1][1] != tempArray[0][1] + 1 || tempArray[1][1] != tempArray[2][1] - 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * This method checks if the drawn tiles represent valid positions in the current state of the board.
     *
     * @param drawnArray The matrix of positions of the drawn tiles.
     * @return TRUE if the drawn tiles represents valid positions on the current board, FALSE otherwise.
     */
    public boolean checkBoard(int[][] drawnArray){
        int size = this.board.length;
        for (int[] ints : drawnArray) {
            if (this.board[ints[0]][ints[1]].getObjectType() == TileType.FREE
                    || this.board[ints[0]][ints[1]].getObjectType() == TileType.LOCK) {
                return false;
            }
            if (!(ints[0] == 0 || ints[1] == 0 || ints[0] == size - 1 || ints[1] == size - 1)) {
                if (!((this.board[ints[0] + 1][ints[1]].getObjectType() == TileType.FREE || this.board[ints[0] + 1][ints[1]].getObjectType() == TileType.LOCK) ||
                        (this.board[ints[0] - 1][ints[1]].getObjectType() == TileType.FREE || this.board[ints[0] - 1][ints[1]].getObjectType() == TileType.LOCK) ||
                        (this.board[ints[0]][ints[1] + 1].getObjectType() == TileType.FREE || this.board[ints[0]][ints[1] + 1].getObjectType() == TileType.LOCK) ||
                        (this.board[ints[0]][ints[1] - 1].getObjectType() == TileType.FREE || this.board[ints[0]][ints[1] - 1].getObjectType() == TileType.LOCK))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * This method modifies the state of the board emptying the tiles in the positions indicated in cellsArray.
     *
     * @param cellsArray The matrix containing the position of the Tiles taken by the currentPlayer.
     * @return An ArrayList with the Tiles drawn by the currentPlayer.
     * @throws Exception when the tiles drawn are invalid.
     */
    public ArrayList<Tile> takeObject(int[][] cellsArray) throws Exception {
        ArrayList<Tile> takenObjects = new ArrayList<>();

        if (!this.checkDraw(cellsArray)){
            throw new Exception("Invalid drawn tiles");
        }

        if(!this.checkBoard(cellsArray)){
            throw new IllegalArgumentException("Invalid drawn tiles position");
        }

        for (int[] ints : cellsArray) {
            takenObjects.add(new Tile(this.board[ints[0]][ints[1]].getObjectType()));
            this.board[ints[0]][ints[1]].setObjectType(TileType.FREE);
        }
        return takenObjects;
    }

    /**
     * Method that shows the current state of the board in the console (for debugging purpose only).
     */
    public void printBoard(){
        for (int j = 0; j < this.board.length; j++)
            System.out.format("%12S", j);
        System.out.println();
        for(int i = 0; i < this.board.length; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < this.board.length; j++) {
                System.out.format("%12S", this.board[i][j].getObjectType());
            }
            System.out.println();
        }
    }
}
