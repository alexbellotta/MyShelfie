package it.polimi.ingsw.Model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;


import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void checkRefill() throws Exception {

        //Testing on empty Boards
        Board newBoard2 = new Board(2);
        assertTrue(newBoard2.checkRefill());     //Testing on an empty Board for 2 Players

        Board newBoard3 = new Board(3);
        assertTrue(newBoard3.checkRefill());     //Testing on an empty Board for 3 Players

        Board newBoard4 = new Board(4);
        assertTrue(newBoard4.checkRefill());     //Testing on an empty Board for 4 Players

        //Testing on refilled Boards
        TileDeck tileDeck = new TileDeck();
        newBoard2.refill(tileDeck);
        assertFalse(newBoard2.checkRefill());    //Testing on a refilled Board for 2 Players

        newBoard3.refill(tileDeck);
        assertFalse(newBoard3.checkRefill());    //Testing on a refilled Board for 3 Players

        newBoard4.refill(tileDeck);
        assertFalse(newBoard4.checkRefill());    //Testing on a refilled Board for 4 Players

        //Testing on Boards with non-adjacent tiles
        Board testBoard2na = new Board(2);
        int [][] adjacentArray2na = {{1, 3}, {2, 4}, {4, 2}, {6, 4}};
        for (int[] ints : adjacentArray2na){
            testBoard2na.getBoard()[ints[0]][ints[1]].setObjectType(TileType.PLANTS);
        }
        assertTrue(testBoard2na.checkRefill()); //Testing on a Board for 2 Players with non-adjacent tiles

        Board testBoard3na = new Board(3);
        int [][] adjacentArray3na = {{2, 2}, {2, 4}, {3, 8}, {5, 5}, {7, 4}};
        for (int[] ints : adjacentArray3na){
            testBoard3na.getBoard()[ints[0]][ints[1]].setObjectType(TileType.PLANTS);
        }
        assertTrue(testBoard3na.checkRefill()); //Testing on a Board for 3 Players with non-adjacent tiles

        Board testBoard4na = new Board(4);
        int [][] adjacentArray4na = {{0, 3}, {3, 3}, {4, 4}, {4, 8}, {5, 0}, {8, 4}};
        for (int[] ints : adjacentArray4na){
            testBoard4na.getBoard()[ints[0]][ints[1]].setObjectType(TileType.PLANTS);
        }
        assertTrue(testBoard4na.checkRefill()); //Testing on a Board for 4 Players with non-adjacent tiles

        //Testing on Boards with adjacent tiles
        Board testBoard2a = new Board(2);
        int [][] adjacentArray2a = {{0, 2}, {2, 2}, {2, 6}, {3, 6}};
        for (int[] ints : adjacentArray2a){
            testBoard2a.getBoard()[ints[0]][ints[1]].setObjectType(TileType.PLANTS);
        }
        assertFalse(testBoard2a.checkRefill()); //Testing on a Board for 2 Players with adjacent tiles

        Board testBoard3a = new Board(3);
        int [][] adjacentArray3a = {{3, 6}, {4, 1}, {5, 3}, {7, 5}, {8, 5}};
        for (int[] ints : adjacentArray3a){
            testBoard3a.getBoard()[ints[0]][ints[1]].setObjectType(TileType.PLANTS);
        }
        assertFalse(testBoard3a.checkRefill()); //Testing on a Board for 3 Players with adjacent tiles

        Board testBoard4a = new Board(4);
        int [][] adjacentArray4a = {{1, 3}, {2, 4}, {3, 4}, {3, 7}, {5, 5}, {7, 3}};
        for (int[] ints : adjacentArray4a){
            testBoard4a.getBoard()[ints[0]][ints[1]].setObjectType(TileType.PLANTS);
        }
        assertFalse(testBoard4a.checkRefill()); //Testing on a Board for 4 Players with adjacent tiles
    }

    @Test
    void refill() throws Exception {
        //Testing on refilled Boards
        Board testBoard2 = new Board(2);            //Testing on a refilled Board for 2 Players
        TileDeck tileDeck = new TileDeck();
        testBoard2.refill(tileDeck);
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                switch (i) {
                    case 0 -> {
                        Integer[] J = {2, 3};
                        if (Arrays.asList(J).contains(j)) {
                            assertNotSame(TileType.FREE, testBoard2.getBoard()[i][j].getObjectType());
                        }
                        else{
                            assertSame(TileType.LOCK, testBoard2.getBoard()[i][j].getObjectType());
                        }
                    }
                    case 1, 5 -> {
                        Integer[] J = {2, 3, 4};
                        if (Arrays.asList(J).contains(j)) {
                            assertNotSame(TileType.FREE, testBoard2.getBoard()[i][j].getObjectType());
                        }
                        else{
                            assertSame(TileType.LOCK, testBoard2.getBoard()[i][j].getObjectType());
                        }
                    }
                    case 2 -> {
                        Integer[] J = {1, 2, 3, 4, 5, 6};
                        if (Arrays.asList(J).contains(j)) {
                            assertNotSame(TileType.FREE, testBoard2.getBoard()[i][j].getObjectType());
                        }
                        else{
                            assertSame(TileType.LOCK, testBoard2.getBoard()[i][j].getObjectType());
                        }
                    }
                    case 3 -> assertNotSame(TileType.FREE, testBoard2.getBoard()[i][j].getObjectType());
                    case 4 -> {
                        Integer[] J = {0, 1, 2, 3, 4, 5};
                        if (Arrays.asList(J).contains(j)) {
                            assertNotSame(TileType.FREE, testBoard2.getBoard()[i][j].getObjectType());
                        }
                        else{
                            assertSame(TileType.LOCK, testBoard2.getBoard()[i][j].getObjectType());
                        }
                    }
                    case 6 -> {
                        Integer[] J = {3, 4};
                        if (Arrays.asList(J).contains(j)) {
                            assertNotSame(TileType.FREE, testBoard2.getBoard()[i][j].getObjectType());
                        }
                        else{
                            assertSame(TileType.LOCK, testBoard2.getBoard()[i][j].getObjectType());
                        }
                    }
                }
            }
        }

        Board testBoard3 = new Board(3);            //Testing on a refilled Board for 3 Players
        testBoard3.refill(tileDeck);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                switch (i) {
                    case 0 -> {
                        if (j == 3) {
                            assertNotSame(TileType.FREE, testBoard3.getBoard()[i][j].getObjectType());
                        }
                        else{
                            assertSame(TileType.LOCK, testBoard3.getBoard()[i][j].getObjectType());
                        }
                    }
                    case 1 -> {
                        Integer[] J = {3, 4};
                        if (Arrays.asList(J).contains(j)) {
                            assertNotSame(TileType.FREE, testBoard3.getBoard()[i][j].getObjectType());
                        }
                        else{
                            assertSame(TileType.LOCK, testBoard3.getBoard()[i][j].getObjectType());
                        }
                    }
                    case 2, 6 -> {
                        Integer[] J = {2, 3, 4, 5, 6};
                        if (Arrays.asList(J).contains(j)) {
                            assertNotSame(TileType.FREE, testBoard3.getBoard()[i][j].getObjectType());
                        }
                        else{
                            assertSame(TileType.LOCK, testBoard3.getBoard()[i][j].getObjectType());
                        }
                    }
                    case 3 -> {
                        Integer[] J = {2, 3, 4, 5, 6, 7, 8};
                        if (Arrays.asList(J).contains(j)) {
                            assertNotSame(TileType.FREE, testBoard3.getBoard()[i][j].getObjectType());
                        }
                        else{
                            assertSame(TileType.LOCK, testBoard3.getBoard()[i][j].getObjectType());
                        }
                    }
                    case 4 -> {
                        Integer[] J = {1, 2, 3, 4, 5, 6, 7};
                        if (Arrays.asList(J).contains(j)) {
                            assertNotSame(TileType.FREE, testBoard3.getBoard()[i][j].getObjectType());
                        }
                        else{
                            assertSame(TileType.LOCK, testBoard3.getBoard()[i][j].getObjectType());
                        }
                    }
                    case 5 -> {
                        Integer[] J = {0, 1, 2, 3, 4, 5, 6};
                        if (Arrays.asList(J).contains(j)) {
                            assertNotSame(TileType.FREE, testBoard3.getBoard()[i][j].getObjectType());
                        }
                        else{
                            assertSame(TileType.LOCK, testBoard3.getBoard()[i][j].getObjectType());
                        }
                    }
                    case 7 -> {
                        Integer[] J = {4, 5};
                        if (Arrays.asList(J).contains(j)) {
                            assertNotSame(TileType.FREE, testBoard3.getBoard()[i][j].getObjectType());
                        }
                        else{
                            assertSame(TileType.LOCK, testBoard3.getBoard()[i][j].getObjectType());
                        }
                    }
                    case 8 -> {
                        if (j == 5) {
                            assertNotSame(TileType.FREE, testBoard3.getBoard()[i][j].getObjectType());
                        }
                        else{
                            assertSame(TileType.LOCK, testBoard3.getBoard()[i][j].getObjectType());
                        }
                    }
                }
            }
        }

        Board testBoard4 = new Board(4);            //Testing on a refilled Board for 4 Players
        testBoard4.refill(tileDeck);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                switch (i) {
                    case 0 -> {
                        Integer[] J = {3, 4};
                        if (Arrays.asList(J).contains(j)) {
                            assertNotSame(TileType.FREE, testBoard4.getBoard()[i][j].getObjectType());
                        }
                        else{
                            assertSame(TileType.LOCK, testBoard4.getBoard()[i][j].getObjectType());
                        }
                    }
                    case 1, 7 -> {
                        Integer[] J = {3, 4, 5};
                        if (Arrays.asList(J).contains(j)) {
                            assertNotSame(TileType.FREE, testBoard4.getBoard()[i][j].getObjectType());
                        }
                        else{
                            assertSame(TileType.LOCK, testBoard4.getBoard()[i][j].getObjectType());
                        }
                    }
                    case 2, 6 -> {
                        Integer[] J = {2, 3, 4, 5, 6};
                        if (Arrays.asList(J).contains(j)) {
                            assertNotSame(TileType.FREE, testBoard4.getBoard()[i][j].getObjectType());
                        }
                        else{
                            assertSame(TileType.LOCK, testBoard4.getBoard()[i][j].getObjectType());
                        }
                    }
                    case 3 -> {
                        Integer[] J = {1, 2, 3, 4, 5, 6, 7, 8};
                        if (Arrays.asList(J).contains(j)) {
                            assertNotSame(TileType.FREE, testBoard4.getBoard()[i][j].getObjectType());
                        }
                        else{
                            assertSame(TileType.LOCK, testBoard4.getBoard()[i][j].getObjectType());
                        }
                    }
                    case 4 -> assertNotSame(TileType.FREE, testBoard4.getBoard()[i][j].getObjectType());
                    case 5 -> {
                        Integer[] J = {0, 1, 2, 3, 4, 5, 6, 7};
                        if (Arrays.asList(J).contains(j)) {
                            assertNotSame(TileType.FREE, testBoard4.getBoard()[i][j].getObjectType());
                        }
                        else{
                            assertSame(TileType.LOCK, testBoard4.getBoard()[i][j].getObjectType());
                        }
                    }
                    case 8 -> {
                        Integer[] J = {4, 5};
                        if (Arrays.asList(J).contains(j)) {
                            assertNotSame(TileType.FREE, testBoard4.getBoard()[i][j].getObjectType());
                        }
                        else{
                            assertSame(TileType.LOCK, testBoard4.getBoard()[i][j].getObjectType());
                        }
                    }
                }
            }
        }
    }

    @Test
    void getBoard() {
        Board testBoard = new Board(2);
        Tile[][] clonedBoard = testBoard.getBoard();
        clonedBoard[0][0].setObjectType(TileType.PLANTS);
        assertSame(testBoard.getBoard()[0][0].getObjectType(), clonedBoard[0][0].getObjectType());
    }

    @Test
    void takeObject() throws Exception {
        Board testBoard = new Board(4);
        TileDeck tileDeck = new TileDeck();
        testBoard.refill(tileDeck);
        int[][] testArray = {{0, 3}, {0, 4}};
        ArrayList<Tile> takenTiles = testBoard.takeObject(testArray);
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                int[] position = {i ,j};
                if (Arrays.equals(position, testArray[0]) || Arrays.equals(position, testArray[1])){
                    assertSame(testBoard.getBoard()[i][j].getObjectType(), TileType.FREE);
                } else {
                    assertNotSame(testBoard.getBoard()[i][j].getObjectType(), TileType.FREE);
                }
            }
        }
        /*
        int[][] testArray2 = {{1, 3}, {1, 4}, {1, 5}};
        takenTiles = testBoard.takeObject(testArray2);
        int[][] testArray3 = {{2, 3}, {2, 4}, {2, 5}};
        takenTiles = testBoard.takeObject(testArray3);
        int[][] testArray4 = {{2, 6}};
        takenTiles = testBoard.takeObject(testArray4);
        int[][] testArray5 = {{3, 8}, {4, 8}};
        takenTiles = testBoard.takeObject(testArray5);
        int[][] testArray6 = {{3, 7}, {4, 7}, {5, 7}};
        takenTiles = testBoard.takeObject(testArray6);
        int[][] testArray7 = {{3, 6}, {4, 6}, {5, 6}};
        takenTiles = testBoard.takeObject(testArray7);
        int[][] testArray8 = {{3, 5}, {4, 5}, {5, 5}};
        takenTiles = testBoard.takeObject(testArray8);
        int[][] testArray9 = {{8, 4}, {8, 5}};
        takenTiles = testBoard.takeObject(testArray9);
        int[][] testArray10 = {{4, 4}, {5, 4}};
        takenTiles = testBoard.takeObject(testArray10);
        int[][] testArray11 = {{6, 4}, {7, 4}};
        takenTiles = testBoard.takeObject(testArray11);
        int[][] testArray12 = {{4, 0}, {5, 0}};
        takenTiles = testBoard.takeObject(testArray12);
        int[][] testArray13 = {{4, 1}, {5, 1}};
        takenTiles = testBoard.takeObject(testArray13);
        int[][] testArray14 = {{4, 2}, {5, 2}, {6, 2}};
        takenTiles = testBoard.takeObject(testArray14);
        int[][] testArray15 = {{4, 3}, {5, 3}, {6, 3}};
        takenTiles = testBoard.takeObject(testArray15);
        testBoard.printBoard();*/
    }
}