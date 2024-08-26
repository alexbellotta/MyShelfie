package it.polimi.ingsw.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Class used to handle the different common goals checking methods.
 */
public enum CommonGoalType implements Serializable {
    CG1, CG2, CG3, CG4, CG5, CG6, CG7, CG8, CG9, CG10, CG11, CG12;

    /**
     * TWO_SQUARES - Number of 2x2 squares needed for common goal 1.
     */
    private static final int TWO_SQUARES = 2;

    /**
     * TWO_COLUMNS_DIFFERENT_TYPES - Number of columns with 6 different types.
     */
    private static final int TWO_COLUMNS_DIFFERENT_TYPES = 2;

    /**
     * FOUR_GROUPS_FOUR_EQUAL_TYPES - Number of 4 adjacent tiles with the same type.
     */
    private static final int FOUR_GROUPS_FOUR_EQUAL_TYPES = 4;

    /**
     * SIX_GROUPS_TWO_DIFFERENT_TYPES - Number of 2 adjacent tiles with the same type.
     */
    private static final int SIX_GROUPS_TWO_DIFFERENT_TYPES = 6;

    /**
     * COLS - Constant used to define bookshelf's columns number.
     */
    private static final int COLS = 5;

    /**
     * ROWS - Constant used to define bookshelf's rows number.
     */
    private static final int ROWS = 6;

    CommonGoalType() {
    }

    /**
     * Method checkCG1 checks if bookshelf has 2 2x2 squares, each
     * formed by 4 equals types.
     *
     * @param bookShelf of type BookShelf is the player's bookshelf
     * @return boolean true if bookshelf matches the pattern
     */
    public boolean checkCG1(BookShelf bookShelf) {
        ArrayList<TileType[]> squares = findSquares(bookShelf.getBookshelf());
        return countGroupsWithSameElements(squares) >= TWO_SQUARES;
    }

    /**
     * Method checkCG2 checks if bookshelf has 2 columns each formed by 6
     * different types of tiles.
     *
     * @param bookShelf of type BookShelf is the player's bookshelf
     * @return boolean true if bookshelf matches the pattern
     */
    public boolean checkCG2(BookShelf bookShelf) {
        int columnsCounter = 0;
        for(int j = 0; j < COLS; ++j) {
            if (!hasDuplicatesOrFreeInColumn(bookShelf.getBookshelf(), j)) {
                ++columnsCounter;
            }
        }
        return columnsCounter >= TWO_COLUMNS_DIFFERENT_TYPES;
    }

    /**
     * Method checkCG3 checks if bookshelf has 4 groups, each formed by 4 equals types.
     *
     * @param bookShelf of type BookShelf is the player's bookshelf
     * @return boolean true if bookshelf matches the pattern
     */
    public boolean checkCG3(BookShelf bookShelf) {
        ArrayList<TileType[]> pairs = getAdjacentQuads(bookShelf.getBookshelf());
        return countGroupsWithSameElements(pairs) >= FOUR_GROUPS_FOUR_EQUAL_TYPES;
    }

    /**
     * Method checkCG4 checks if bookshelf has 6 groups, each formed by 2 equals types.
     *
     * @param bookShelf of type BookShelf is the player's bookshelf
     * @return boolean true if bookshelf matches the pattern
     */
    public boolean checkCG4(BookShelf bookShelf) {
        ArrayList<TileType[]> pairs = getAdjacentPairs(bookShelf.getBookshelf());
        return countGroupsWithSameElements(pairs) >= SIX_GROUPS_TWO_DIFFERENT_TYPES;
    }

    /**
     * Method checkCG5 checks if bookshelf has 3 columns, each formed by
     * tiles of max 3 different types.
     *
     * @param bookShelf of type BookShelf is the player's bookshelf
     * @return boolean true if bookshelf matches the pattern
     */
    public boolean checkCG5(BookShelf bookShelf) {
        int columnsCounter = 0;
        for(int j = 0; j <= COLS-1; ++j) {
            if (hasMaxThreeTypesInColumn(bookShelf.getBookshelf(), j) <= 3) {
                ++columnsCounter;
            }
        }
        return columnsCounter >= 3;
    }

    /**
     * Method checkCG6 checks if bookshelf has 2 lines each formed by 5 different
     * types of tiles. One line can show the
     * same or a different combination of the
     * other line.
     *
     * @param bookShelf of type BookShelf is the player's bookshelf
     * @return boolean true if bookshelf matches the pattern
     */
    public boolean checkCG6(BookShelf bookShelf) {
        int rowsCounter = 0;
        for(int i = 0; i < ROWS; ++i) {
            if (!hasDuplicatesOrFreeInRow(bookShelf.getBookshelf(), i)) {
                ++rowsCounter;
            }
        }
        return rowsCounter >= 2;
    }

    /**
     * Method checkCG7 checks if bookshelf has 4 lines each formed by 5 tiles of
     * maximum three different types. One
     * line can show the same or a different
     * combination of another line.
     *
     * @param bookShelf of type BookShelf is the player's bookshelf
     * @return boolean true if bookshelf matches the pattern
     */
    public boolean checkCG7(BookShelf bookShelf) {
        int rowsCounter = 0;
        for(int i = 0; i < ROWS; ++i) {
            if (hasMaxThreeTypesInRow(bookShelf.getBookshelf(), i) <= 3) {
                ++rowsCounter;

            }
        }
        return rowsCounter >= 4;
    }

    /**
     * Method checkCG8 checks if bookshelf has the same object in corners.
     *
     * @param bookShelf of type BookShelf is the player's bookshelf
     * @return boolean true if bookshelf matches the pattern
     */
    public boolean checkCG8(BookShelf bookShelf) {
        if(bookShelf.getBookshelf()[0][0].getObjectType() != TileType.FREE && bookShelf.getBookshelf()[0][4].getObjectType() != TileType.FREE && bookShelf.getBookshelf()[5][4].getObjectType() != TileType.FREE && bookShelf.getBookshelf()[5][0].getObjectType() != TileType.FREE) {
            return bookShelf.getBookshelf()[0][0].getObjectType() == bookShelf.getBookshelf()[0][4].getObjectType() && bookShelf.getBookshelf()[0][4].getObjectType() == bookShelf.getBookshelf()[5][4].getObjectType() && bookShelf.getBookshelf()[5][4].getObjectType() == bookShelf.getBookshelf()[5][0].getObjectType();
        }
        return false;
    }

    /**
     * Method checkCG9 checks if bookshelf has 8 tiles of the same type
     * without restriction on their position.
     *
     * @param bookShelf of type BookShelf is the player's bookshelf
     * @return boolean true if bookshelf matches the pattern
     */
    public boolean checkCG9(BookShelf bookShelf) {
        int counter = 0;
        for(int i = 0; i <= ROWS-1; ++i) {
            for(int j = 0; j <= COLS-1; ++j) {
                if (bookShelf.getBookshelf()[i][j].getObjectType() != TileType.FREE) {
                    Tile current = bookShelf.getBookshelf()[i][j];
                    for(int h = 0; h <= ROWS-1; ++h) {
                        for(int k = 0; k <= COLS-1; ++k) {
                            if (bookShelf.getBookshelf()[h][k].getObjectType() != TileType.FREE && current.getObjectType() == bookShelf.getBookshelf()[h][k].getObjectType()) {
                                ++counter;
                            }
                        }
                    }
                    if (counter == 8) {
                        return true;
                    }
                    counter = 0;
                }
            }
        }
        return false;
    }

    /**
     * Method checkCG10 checks if bookshelf has 5 tiles of the
     * same type forming an X.
     *
     * @param bookShelf of type BookShelf is the player's bookshelf
     * @return boolean true if bookshelf matches the pattern
     */
    public boolean checkCG10(BookShelf bookShelf) {
        if (bookShelf.getBookshelf()[1][1].getObjectType() != TileType.FREE && bookShelf.getBookshelf()[1][1].getObjectType() == bookShelf.getBookshelf()[0][0].getObjectType() && bookShelf.getBookshelf()[0][0].getObjectType() == bookShelf.getBookshelf()[0][2].getObjectType() && bookShelf.getBookshelf()[0][2].getObjectType() == bookShelf.getBookshelf()[2][2].getObjectType() && bookShelf.getBookshelf()[2][2].getObjectType() == bookShelf.getBookshelf()[2][0].getObjectType()) {
            return true;
        } else if (bookShelf.getBookshelf()[1][2].getObjectType() != TileType.FREE && bookShelf.getBookshelf()[1][2].getObjectType() == bookShelf.getBookshelf()[0][1].getObjectType() && bookShelf.getBookshelf()[0][1].getObjectType() == bookShelf.getBookshelf()[0][3].getObjectType() && bookShelf.getBookshelf()[0][3].getObjectType() == bookShelf.getBookshelf()[2][3].getObjectType() && bookShelf.getBookshelf()[2][3].getObjectType() == bookShelf.getBookshelf()[2][1].getObjectType()) {
            return true;
        } else if (bookShelf.getBookshelf()[1][3].getObjectType() != TileType.FREE && bookShelf.getBookshelf()[1][3].getObjectType() == bookShelf.getBookshelf()[0][2].getObjectType() && bookShelf.getBookshelf()[0][2].getObjectType() == bookShelf.getBookshelf()[0][4].getObjectType() && bookShelf.getBookshelf()[0][4].getObjectType() == bookShelf.getBookshelf()[2][4].getObjectType() && bookShelf.getBookshelf()[2][4].getObjectType() == bookShelf.getBookshelf()[2][2].getObjectType()) {
            return true;
        } else if (bookShelf.getBookshelf()[2][1].getObjectType() != TileType.FREE && bookShelf.getBookshelf()[2][1].getObjectType() == bookShelf.getBookshelf()[1][0].getObjectType() && bookShelf.getBookshelf()[1][0].getObjectType() == bookShelf.getBookshelf()[1][2].getObjectType() && bookShelf.getBookshelf()[1][2].getObjectType() == bookShelf.getBookshelf()[3][2].getObjectType() && bookShelf.getBookshelf()[3][2].getObjectType() == bookShelf.getBookshelf()[3][0].getObjectType()) {
            return true;
        } else if (bookShelf.getBookshelf()[2][2].getObjectType() != TileType.FREE && bookShelf.getBookshelf()[2][2].getObjectType() == bookShelf.getBookshelf()[1][1].getObjectType() && bookShelf.getBookshelf()[1][1].getObjectType() == bookShelf.getBookshelf()[1][3].getObjectType() && bookShelf.getBookshelf()[1][3].getObjectType() == bookShelf.getBookshelf()[3][3].getObjectType() && bookShelf.getBookshelf()[3][3].getObjectType() == bookShelf.getBookshelf()[3][1].getObjectType()) {
            return true;
        } else if (bookShelf.getBookshelf()[2][3].getObjectType() != TileType.FREE && bookShelf.getBookshelf()[2][3].getObjectType() == bookShelf.getBookshelf()[1][2].getObjectType() && bookShelf.getBookshelf()[1][2].getObjectType() == bookShelf.getBookshelf()[1][4].getObjectType() && bookShelf.getBookshelf()[1][4].getObjectType() == bookShelf.getBookshelf()[3][4].getObjectType() && bookShelf.getBookshelf()[3][4].getObjectType() == bookShelf.getBookshelf()[3][2].getObjectType()) {
            return true;
        } else if (bookShelf.getBookshelf()[3][1].getObjectType() != TileType.FREE && bookShelf.getBookshelf()[3][1].getObjectType() == bookShelf.getBookshelf()[2][0].getObjectType() && bookShelf.getBookshelf()[2][0].getObjectType() == bookShelf.getBookshelf()[2][2].getObjectType() && bookShelf.getBookshelf()[2][2].getObjectType() == bookShelf.getBookshelf()[4][2].getObjectType() && bookShelf.getBookshelf()[4][2].getObjectType() == bookShelf.getBookshelf()[4][0].getObjectType()) {
            return true;
        } else if (bookShelf.getBookshelf()[3][2].getObjectType() != TileType.FREE && bookShelf.getBookshelf()[3][2].getObjectType() == bookShelf.getBookshelf()[2][1].getObjectType() && bookShelf.getBookshelf()[2][1].getObjectType() == bookShelf.getBookshelf()[2][3].getObjectType() && bookShelf.getBookshelf()[2][3].getObjectType() == bookShelf.getBookshelf()[4][3].getObjectType() && bookShelf.getBookshelf()[4][3].getObjectType() == bookShelf.getBookshelf()[4][1].getObjectType()) {
            return true;
        } else if (bookShelf.getBookshelf()[3][3].getObjectType() != TileType.FREE && bookShelf.getBookshelf()[3][3].getObjectType() == bookShelf.getBookshelf()[2][2].getObjectType() && bookShelf.getBookshelf()[2][2].getObjectType() == bookShelf.getBookshelf()[2][4].getObjectType() && bookShelf.getBookshelf()[2][4].getObjectType() == bookShelf.getBookshelf()[4][4].getObjectType() && bookShelf.getBookshelf()[4][4].getObjectType() == bookShelf.getBookshelf()[4][2].getObjectType()) {
            return true;
        } else if (bookShelf.getBookshelf()[4][1].getObjectType() != TileType.FREE && bookShelf.getBookshelf()[4][1].getObjectType() == bookShelf.getBookshelf()[3][0].getObjectType() && bookShelf.getBookshelf()[3][0].getObjectType() == bookShelf.getBookshelf()[3][2].getObjectType() && bookShelf.getBookshelf()[3][2].getObjectType() == bookShelf.getBookshelf()[5][2].getObjectType() && bookShelf.getBookshelf()[5][2].getObjectType() == bookShelf.getBookshelf()[5][0].getObjectType()) {
            return true;
        } else if (bookShelf.getBookshelf()[4][2].getObjectType() != TileType.FREE && bookShelf.getBookshelf()[4][2].getObjectType() == bookShelf.getBookshelf()[3][1].getObjectType() && bookShelf.getBookshelf()[3][1].getObjectType() == bookShelf.getBookshelf()[3][3].getObjectType() && bookShelf.getBookshelf()[3][3].getObjectType() == bookShelf.getBookshelf()[5][3].getObjectType() && bookShelf.getBookshelf()[5][3].getObjectType() == bookShelf.getBookshelf()[2][0].getObjectType()) {
            return true;
        } else return bookShelf.getBookshelf()[4][3].getObjectType() != TileType.FREE && bookShelf.getBookshelf()[4][3].getObjectType() == bookShelf.getBookshelf()[3][2].getObjectType() && bookShelf.getBookshelf()[3][2].getObjectType() == bookShelf.getBookshelf()[3][4].getObjectType() && bookShelf.getBookshelf()[3][4].getObjectType() == bookShelf.getBookshelf()[5][4].getObjectType() && bookShelf.getBookshelf()[5][4].getObjectType() == bookShelf.getBookshelf()[5][2].getObjectType();
    }

    /**
     * Method checkCG11 checks if bookshelf has 5 tiles of equals type
     * forming a diagonal.
     *
     * @param bookShelf of type BookShelf is the player's bookshelf
     * @return boolean true if bookshelf matches the pattern
     */
    public boolean checkCG11(BookShelf bookShelf) {
        if (bookShelf.getBookshelf()[0][0].getObjectType() != TileType.FREE && bookShelf.getBookshelf()[0][0].getObjectType() == bookShelf.getBookshelf()[1][1].getObjectType() && bookShelf.getBookshelf()[1][1].getObjectType() == bookShelf.getBookshelf()[2][2].getObjectType() && bookShelf.getBookshelf()[2][2].getObjectType() == bookShelf.getBookshelf()[3][3].getObjectType() && bookShelf.getBookshelf()[3][3].getObjectType() == bookShelf.getBookshelf()[4][4].getObjectType()) {
            return true;
        } else if (bookShelf.getBookshelf()[1][0].getObjectType() != TileType.FREE && bookShelf.getBookshelf()[1][0].getObjectType() == bookShelf.getBookshelf()[2][1].getObjectType() && bookShelf.getBookshelf()[2][1].getObjectType() == bookShelf.getBookshelf()[3][2].getObjectType() && bookShelf.getBookshelf()[3][2].getObjectType() == bookShelf.getBookshelf()[4][3].getObjectType() && bookShelf.getBookshelf()[4][3].getObjectType() == bookShelf.getBookshelf()[5][4].getObjectType()) {
            return true;
        } else if (bookShelf.getBookshelf()[0][4].getObjectType() != TileType.FREE && bookShelf.getBookshelf()[0][4].getObjectType() == bookShelf.getBookshelf()[1][3].getObjectType() && bookShelf.getBookshelf()[1][3].getObjectType() == bookShelf.getBookshelf()[2][2].getObjectType() && bookShelf.getBookshelf()[2][2].getObjectType() == bookShelf.getBookshelf()[3][1].getObjectType() && bookShelf.getBookshelf()[3][1].getObjectType() == bookShelf.getBookshelf()[4][0].getObjectType()) {
            return true;
        } else {
            return bookShelf.getBookshelf()[1][4].getObjectType() != TileType.FREE && bookShelf.getBookshelf()[1][4].getObjectType() == bookShelf.getBookshelf()[2][3].getObjectType() && bookShelf.getBookshelf()[2][3].getObjectType() == bookShelf.getBookshelf()[3][2].getObjectType() && bookShelf.getBookshelf()[3][2].getObjectType() == bookShelf.getBookshelf()[4][1].getObjectType() && bookShelf.getBookshelf()[4][1].getObjectType() == bookShelf.getBookshelf()[5][0].getObjectType();
        }
    }

    /**
     * Method checkCG12
     * checks if bookshelf has 5 columns of increasing or decreasing
     * height. Starting from the first column on
     * the left or on the right, each next column
     * must be made of exactly one more tile.
     * Tiles can be of any type.
     *
     * @param bookShelf of type BookShelf is the player's bookshelf
     * @return boolean true if bookshelf matches the pattern
     */
    public boolean checkCG12(BookShelf bookShelf) {
        int[] tilesNumber = new int[COLS];
        for(int i = 0; i < tilesNumber.length; i++){
            tilesNumber[i] = tilesInColumn(bookShelf.getBookshelf(), i);
        }
        return isConsecutive(tilesNumber) || isConsecutiveDecrescent(tilesNumber);
    }

    /**
     * Checks if there are duplicates tiles in a bookshelf's column
     *
     * @param bookshelf the player's bookshelf
     * @param columnIndex the index of the column to check.
     * @return true if the column has duplicates in it.
     */
    public static boolean hasDuplicatesOrFreeInColumn(Tile[][] bookshelf, int columnIndex) {
        Set<TileType> seenElements = new HashSet<>();
        for(int i = 0; i < ROWS; ++i) {
            if (bookshelf[i][columnIndex].getObjectType() != TileType.FREE) {
                TileType element = bookshelf[i][columnIndex].getObjectType();
                if (seenElements.contains(element)) {
                    return true;
                }
                seenElements.add(element);
            }else{
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if there are duplicates tiles in a bookshelf's row
     *
     * @param bookshelf the player's bookshelf
     * @param rowIndex the index of the row to check.
     * @return true if the row has duplicates in it.
     */
    public static boolean hasDuplicatesOrFreeInRow(Tile[][] bookshelf, int rowIndex) {
        Set<TileType> seenElements = new HashSet<>();
        for(int j = 0; j < COLS; ++j) {
            if (bookshelf[rowIndex][j].getObjectType() != TileType.FREE) {
                TileType ele = bookshelf[rowIndex][j].getObjectType();
                if (seenElements.contains(ele)) {
                    return true;
                }
                seenElements.add(ele);
            }else{
                //if a tile is FREE the row is not complete
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if there are three types of tile in a bookshelf's column
     *
     * @param bookshelf the player's bookshelf
     * @param columnIndex the index of the column to check.
     * @return number of columns with max 3 types
     */
    public static int hasMaxThreeTypesInColumn(Tile[][] bookshelf, int columnIndex) {
        ArrayList<TileType> seenElements = new ArrayList<>();
        for(int i = 0; i < ROWS; ++i) {
            if (bookshelf[i][columnIndex].getObjectType() != TileType.FREE) {
                TileType element = bookshelf[i][columnIndex].getObjectType();
                if (!seenElements.contains(element)) {
                    seenElements.add(element);
                }
            }else{
                return 4;
            }
        }
        if(seenElements.size() == 0) {
            return 4;
        }else{
            return seenElements.size();
        }
    }

    /**
     * Checks if there are three types of tile in a bookshelf's row
     *
     * @param bookshelf the player's bookshelf
     * @param rowIndex the index of the row to check.
     * @return number of rows with max 3 types.
     */
    public static int hasMaxThreeTypesInRow(Tile[][] bookshelf, int rowIndex) {
        ArrayList<TileType> seenElements = new ArrayList<>();
        for(int j = 0; j < COLS; ++j) {
            if (bookshelf[rowIndex][j].getObjectType() != TileType.FREE) {
                TileType element = bookshelf[rowIndex][j].getObjectType();
                if (!seenElements.contains(element)) {
                    seenElements.add(element);
                }
            }else{
                return 4;
            }
        }
        if(seenElements.size() == 0) {
            return 4;
        }else{
            return seenElements.size();
        }
    }

    /**
     * Method getAdjacentPairs checks if an element in position [i][j] has the same type of the
     * elements in position [i+1][j] or [i][j+1].
     *
     * @param bookshelf the player's bookshelf
     * @return An ArrayList made of ObjectTypes' arrays.
     * @see TileType
     */
    public static ArrayList<TileType[]> getAdjacentPairs(Tile[][] bookshelf) {
        ArrayList<TileType[]> pairs = new ArrayList<>();
        for(int i = 0; i < ROWS; ++i) {
            for(int j = 0; j < COLS; ++j) {
                if (bookshelf[i][j].getObjectType() != TileType.FREE){
                    TileType[] pair;
                    if (j < COLS-1 && bookshelf[i][j + 1].getObjectType() != TileType.FREE) {
                        pair = new TileType[]{bookshelf[i][j].getObjectType(), bookshelf[i][j + 1].getObjectType()};
                        pairs.add(pair);
                    }
                    if (i < ROWS-1 && bookshelf[i + 1][j].getObjectType() != TileType.FREE) {
                        pair = new TileType[]{bookshelf[i][j].getObjectType(), bookshelf[i + 1][j].getObjectType()};
                        pairs.add(pair);
                    }
                }
            }
        }
        return pairs;
    }

    /**
     * Method getAdjacentQuads retrieves the groups of 4 elements made by the
     * same type.
     *
     * @param bookshelf the player's bookshelf
     * @return An ArrayList made of ObjectTypes' arrays.
     * @see TileType
     */
    public static ArrayList<TileType[]> getAdjacentQuads(Tile[][] bookshelf) {
        ArrayList<TileType[]> quads = new ArrayList<>();
        for(int i = 0; i < bookshelf.length; ++i) {
            for(int j = 0; j < bookshelf[i].length; ++j) {
                if (bookshelf[i][j].getObjectType() != TileType.FREE) {
                    TileType[] quad;
                    if (j < bookshelf[i].length - 3 && bookshelf[i][j + 1].getObjectType() != TileType.FREE && bookshelf[i][j + 2].getObjectType() != TileType.FREE && bookshelf[i][j + 3].getObjectType() != TileType.FREE) {
                        quad = new TileType[]{bookshelf[i][j].getObjectType(), bookshelf[i][j + 1].getObjectType(), bookshelf[i][j + 2].getObjectType(), bookshelf[i][j + 3].getObjectType()};
                        quads.add(quad);
                    }
                    if (i < bookshelf.length - 3 && bookshelf[i + 1][j].getObjectType() != TileType.FREE && bookshelf[i + 2][j].getObjectType() != TileType.FREE & bookshelf[i + 3][j].getObjectType() != TileType.FREE) {
                        quad = new TileType[]{bookshelf[i][j].getObjectType(), bookshelf[i + 1][j].getObjectType(), bookshelf[i + 2][j].getObjectType(), bookshelf[i + 3][j].getObjectType()};
                        quads.add(quad);
                    }
                }
            }
        }
        return quads;
    }

    /**
     * Method findSquares retrieves all possible 2x2 squares in the bookshelf.
     *
     * @param bookshelf the player's bookshelf
     * @return An ArrayList made of ObjectTypes' arrays.
     * @see TileType
     */
    public static ArrayList<TileType[]> findSquares(Tile[][] bookshelf) {
        ArrayList<TileType[]> squares = new ArrayList<>();
        for (int i = 0; i < ROWS-1; i++) {
            for (int j = 0; j < COLS-1; j++) {
                if(bookshelf[i][j].getObjectType() != TileType.FREE && bookshelf[i][j + 1].getObjectType() != TileType.FREE && bookshelf[i + 1][j].getObjectType() != TileType.FREE && bookshelf[i + 1][j + 1].getObjectType() != TileType.FREE) {
                    TileType[] square;
                    square = new TileType[]{bookshelf[i][j].getObjectType(), bookshelf[i][j + 1].getObjectType(), bookshelf[i + 1][j].getObjectType(), bookshelf[i + 1][j + 1].getObjectType()};
                    squares.add(square);
                }
            }
        }
        return squares;
    }


    /**
     * Method countGroupsWithSameElements calculates how many
     * groups of ObjectType are made of the same element.
     *
     * @param groups ArrayList made of ObjectTypes' arrays
     * @return count, of type int, that is the number of groups
     * made with all the same element
     */
    public static int countGroupsWithSameElements(ArrayList<TileType[]> groups) {
        int count = 0;
        for (TileType[] vector : groups) {
            boolean allEqual = true;
            for (int i = 1; i < vector.length; ++i) {
                if (vector[i] != vector[0]) {
                    allEqual = false;
                    break;
                }
            }
            if (allEqual) {
                ++count;
            }
        }
        return count;
    }

    /**
     * Method tilesInColumns calculates how many tiles
     * different from FREE are present.
     *
     * @param bookshelf of type BookShelf is the player's bookshelf
     * @param columnIndex of type int is a single column of the bookshelf
     * @return counter of type int.
     */
    public static int tilesInColumn(Tile[][] bookshelf, int columnIndex){
        int counter = 0;
        for(int i = ROWS-1; i >=0; i--) {
            if (bookshelf[i][columnIndex].getObjectType() != TileType.FREE) {
                counter++;
            }else{
                return counter;
            }
        }
        return counter;
    }

    /**
     * Checks if every element in the array minor than the successive
     *
     * @param arr the array to check
     * @return true if the array has crescents elements
     */
    public static boolean isConsecutive(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i+1] - arr[i] != 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if every element in the array mayor than the successive
     * @param arr the array to check
     * @return true if the array has decrescendo elements
     */
    public static boolean isConsecutiveDecrescent(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i+1] - arr[i] != -1) {
                return false;
            }
        }
        return true;
    }


}


