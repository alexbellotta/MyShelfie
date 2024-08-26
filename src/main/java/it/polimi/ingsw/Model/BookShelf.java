package it.polimi.ingsw.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class represents the player's bookshelf during the game.
 */
public class BookShelf implements Serializable {
    /**
     * Constant for the row's number.
     */
    private static final int DEFINE_ROW = 6;

    /**
     * Constant for the column's number.
     */
    private static final int DEFINE_COL = 5;

    /**
     * Bookshelf defined as matrix of Tiles.
     */
    private Tile[][] bookshelf;

    /**
     * Defines if a player is the first to complete his bookshelf.
     */
    private boolean hasEndCard;

    /**
     * Class constructor.
     * Initializes every cell in the bookshelf to FREE value.
     */
    public BookShelf() {
        hasEndCard = false;
        bookshelf = new Tile[DEFINE_ROW][DEFINE_COL];
        for ( int i = 0; i< DEFINE_ROW; i++){
            for ( int j = 0; j < DEFINE_COL; j++){
                this.getBookshelf()[i][j] = new Tile(TileType.FREE);
            }
        }
    }

    /**
     * Getter for the bookshelf.
     *
     * @return the bookshelf.
     */
    public Tile[][] getBookshelf() {
        return bookshelf;
    }

    /**
     * Setter method for the bookshelf.
     *
     * @param bookshelf the bookshelf to set.
     */
    public void setBookshelf(Tile[][] bookshelf) {
        this.bookshelf=bookshelf;
    }

    /**
     * Calculates the score given by the adjacent tiles in the bookshelf.
     *
     * @return the score of the bookshelf for the adjacent tiles.
     */
    public int checkAdjacentObjects() {
        Tile[][] library = this.getBookshelf();
        int score = 0;
        int occurrences;
        TileType[] enumType = TileType.values();
        int enumSize = TileType.values().length; //the possible number of values of the enum
        int i;
        int x,y = 0;
        boolean flag = false;

        boolean[][] firstSupportLibrary = new boolean[DEFINE_ROW][DEFINE_COL];
        boolean[][] secondSupportLibrary = new boolean[DEFINE_ROW][DEFINE_COL];
        //I loop trough each possible ObjectType type
        for(i=0;i<enumSize;i++) {

            //on each iteration I change the enum type, if the type is free or lock I skip that iteration (they are useless score-wise)
            if (enumType[i] == TileType.LOCK || enumType[i] == TileType.FREE)
                continue;

            //for each iteration (ie each time I change objectType) I set to false all the cells
            for(x = 0;x<DEFINE_ROW;x++) {
                Arrays.fill(firstSupportLibrary[x], false);
                Arrays.fill(secondSupportLibrary[x], false);
            }
            //one by one I loop through the various type of card and I set a true in supportLibrary in each position in which is present the correct type of card
            //ie at the end of this snipped of code I have a true
            for (x = 0; x < DEFINE_ROW; x++)
                for (y = 0; y < DEFINE_COL; y++)
                    if (library[x][y].getObjectType() == enumType[i])
                        firstSupportLibrary[x][y] = true;

            //now I have a matrix of true/false. I have to find the true cells that have adjacent true
            // cells, AND I have to distinguish between groups of true cells that are not adjacent
            // what a mess!

            //I first begin to eliminate all the cells that don't have adjacent cell (ie they don't assign any score)
            for (x = 0; x < DEFINE_ROW; x++)
                for (y = 0; y < DEFINE_COL; y++)
                    if (firstSupportLibrary[x][y])
                        if (!cellHasAdjacentObject(firstSupportLibrary, x, y))
                            firstSupportLibrary[x][y] = false;

            //i=i; //here for debug purposes

            for(;;){
                //now that I have just groups of true cells I have to count the cells in a single group at a time
                //I copy a true cell in the second support library, then I copy all the adjacent cells of the first cell and so on, till all the cells in a group are in secondSupportLibrary
                flag = false;
                for (x = 0; x < DEFINE_ROW; x++) {
                    for (y = 0; y < DEFINE_COL; y++)
                        if (firstSupportLibrary[x][y]) {        //cycle till it finds a true cell (a cell that is part of a group)
                            flag = true;
                            break;
                        }
                if(flag)
                    break;
                }

                if (x == DEFINE_ROW && y == DEFINE_COL)
                    break;          //if I loop trough firstSupportLibrary and there is no cell set to true it means that I don't have groups of cells or
                                    // that I have already counted all cells group, so I stop looping and I skip to the next ObjectType


                //I add the first true cell to secondSupportLibrary (and I remove that from firstSupportLibrary)
                firstSupportLibrary[x][y] = false;
                secondSupportLibrary[x][y] = true;

                //I continue from the first cell that I copied
                for (x = 0; x < DEFINE_ROW; x++)
                    for (y = 0; y < DEFINE_COL; y++)
                        if (secondSupportLibrary[x][y])
                            copyAllAdjacentObject(firstSupportLibrary, secondSupportLibrary, x, y);
                // I now have all the cells of a single group in secondLibrarySupport. I just need to count them and assign a score to them.
                occurrences = 0;
                for (x = 0; x < DEFINE_ROW; x++)
                    for (y = 0; y < DEFINE_COL; y++)
                        if (secondSupportLibrary[x][y])
                            occurrences++;
                //it assigns the score based on the number of adjacent cards
                if (occurrences >= 6)
                    score += 8;
                else if (occurrences == 5)
                    score += 5;
                else if (occurrences == 4)
                    score += 3;
                else if (occurrences == 3)
                    score += 2;
            }
        }
        return score;
    }

    /**
     * It copies all the adjacent cells of the cell specified by x,y from the in matrix to the out matrix.
     *
     * @param in input matrix.
     * @param out output matrix.
     * @param x the first coordinate of the cell.
     * @param y the second coordinate of the cell.
     */
    private void copyAllAdjacentObject(boolean[][] in, boolean[][] out, int x, int y)
    {
        if(x>0)
            if(in[x-1][y]) {
                out[x-1][y] = true;
                in[x-1][y] = false;
            }
        if(x<DEFINE_ROW-1)
            if(in[x+1][y]){
                out[x+1][y] = true;
                in[x+1][y] = false;
            }
        if(y>0)
            if(in[x][y-1]){
                out[x][y-1] = true;
                in[x][y-1] = false;
            }
        if(y<DEFINE_COL-1)
            if(in[x][y+1]) {
                out[x][y + 1] = true;
                in[x][y + 1] = false;
            }
    }

    /**
     * Checks if the specified cell has adjacent object. it also works in cells that themselves are set to false.
     *
     * @param matrix the boolean matrix to check.
     * @param x the first coordinate of the cell to check.
     * @param y the second coordinate of the cell to check.
     * @return true: the cell in matrix[x][y] has adjacent true cell, false: otherwise.
     */
    private boolean cellHasAdjacentObject(boolean matrix[][], int x, int y)
    {

        if(x<0 || x>DEFINE_ROW || y<0 || y>DEFINE_COL )
            return false; //it should throw an exception instead
        if(x>0)
            if(matrix[x-1][y])
                return true;
        if(x<DEFINE_ROW-1)
            if(matrix[x+1][y])
                return true;
        if(y>0)
            if(matrix[x][y-1])
                return true;
        if(y<DEFINE_COL-1)
            if (matrix[x][y+1])
                return true;
        return false;
    }

    /**
     * Check if the bookshelf is empty.
     *
     * @return true if the bookshelf is empty.
     */
    public boolean isEmpty() {

        int i,j;
        for(i=0;i<DEFINE_ROW;i++)
            for(j=0;j<DEFINE_COL;j++)
                if(bookshelf[i][j].getObjectType() != TileType.FREE)
                    return false;
        return true;
    }

    /**
     * Checks if every cell in bookshelf is filled.
     *
     * @return true if every cell in bookshelf is filled.
     */
    public boolean isFull() {

        int i,j;
        for(i=0;i<DEFINE_ROW;i++)
            for(j=0;j<DEFINE_COL;j++)
                if(bookshelf[i][j].getObjectType() == TileType.FREE)
                    return false;
        return true;
    }

    /**
     * Add the tiles in objectList to the column specified by the column attribute.
     * the function expects that the first element (the one in objectList.get(0)) is the one that has to go the farthest down in the column.
     *
     * @param column indicate in which column the cards will be added.
     * @param objectList contains the cards to be added to the board.
     * @return true: the cards have been successfully added to the board; false: there has been an error while adding the cards to the board.
     */
    public boolean placeObject(int column, ArrayList<Tile> objectList) {

        //find the first empty cell in the column
        int i,k;
        if (column >= DEFINE_COL)
            throw new IndexOutOfBoundsException("You tried to place the tiles in a non-existing column");
        for(i=DEFINE_ROW-1;i>=0;i--)
            if(bookshelf[i][column].getObjectType() == TileType.FREE)
                break;
        //check that I have enough empty cells to fit the objectList in the column
        if((i+1)<objectList.size()) {
            throw new RuntimeException("There is not enough space in the column to fit all the tiles.");
            //
        }
        //add the content of objectList to the column
        for(k=0;k<objectList.size();k++){
            bookshelf[i][column].setObjectType(objectList.get(k).getObjectType());
            i--;
        }
        return true;
    }

    /**
     * Method used to print the current state of the bookshelf.
     */
    public void printBookshelf(){
        for (int j = 0; j < 5; j++)
            System.out.format("%12S", j);
        System.out.println();
        for(int i = 0; i < this.getBookshelf().length; i++) {
            System.out.print("" +i+ "");
            for (int j = 0; j < this.getBookshelf()[i].length; j++) {
                System.out.format("%12S", this.getBookshelf()[i][j].getObjectType());
            }
            System.out.println();
        }

    }
}
