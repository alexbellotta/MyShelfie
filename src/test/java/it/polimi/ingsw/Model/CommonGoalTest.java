package it.polimi.ingsw.Model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommonGoalTest {

    @Test
    @DisplayName("Return points ArrayList")
    void getPoints(){
    }

    @Test
    void setPoints() {
    }

    @Test
    void getGoalType() {
    }

    @Test
    void setGoalType() {
    }

    @Test
    @DisplayName("Checking common goal with 4 players")
    void checkCommonGoal() {
        BookShelf bookShelf = new BookShelf();
        Tile free = new Tile(TileType.FREE);
        Tile cat = new Tile(TileType.CATS);
        bookShelf.getBookshelf()[0][0] = cat;
        bookShelf.getBookshelf()[1][0] = cat;
        bookShelf.getBookshelf()[2][0] = free;
        bookShelf.getBookshelf()[3][0] = free;
        bookShelf.getBookshelf()[4][0] = cat;
        bookShelf.getBookshelf()[5][0] = cat;

        bookShelf.getBookshelf()[0][1] = free;
        bookShelf.getBookshelf()[1][1] = free;
        bookShelf.getBookshelf()[2][1] = free;
        bookShelf.getBookshelf()[3][1] = free;
        bookShelf.getBookshelf()[4][1] = free;
        bookShelf.getBookshelf()[5][1] = free;

        bookShelf.getBookshelf()[0][2] = cat;
        bookShelf.getBookshelf()[1][2] = cat;
        bookShelf.getBookshelf()[2][2] = free;
        bookShelf.getBookshelf()[3][2] = free;
        bookShelf.getBookshelf()[4][2] = cat;
        bookShelf.getBookshelf()[5][2] = cat;

        bookShelf.getBookshelf()[0][3] = free;
        bookShelf.getBookshelf()[1][3] = free;
        bookShelf.getBookshelf()[2][3] = free;
        bookShelf.getBookshelf()[3][3] = free;
        bookShelf.getBookshelf()[4][3] = free;
        bookShelf.getBookshelf()[5][3] = free;

        bookShelf.getBookshelf()[0][4] = cat;
        bookShelf.getBookshelf()[1][4] = cat;
        bookShelf.getBookshelf()[2][4] = free;
        bookShelf.getBookshelf()[3][4] = free;
        bookShelf.getBookshelf()[4][4] = cat;
        bookShelf.getBookshelf()[5][4] = cat;
        CommonGoal cg3 = new CommonGoal(CommonGoalType.CG4, 4);
        int points = cg3.checkCommonGoal(CommonGoalType.CG4, bookShelf);
        int points2 = cg3.checkCommonGoal(CommonGoalType.CG4, bookShelf);
        int points3 = cg3.checkCommonGoal(CommonGoalType.CG4, bookShelf);
        int points4 = cg3.checkCommonGoal(CommonGoalType.CG4, bookShelf);
        assertEquals(8, points);
        assertEquals(6, points2);
        assertEquals(4, points3);
        assertEquals(2, points4);
    }
}