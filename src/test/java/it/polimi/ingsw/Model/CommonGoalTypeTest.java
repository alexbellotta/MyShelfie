package it.polimi.ingsw.Model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CommonGoalTypeTest {
    CommonGoalTypeTest() {
    }

    @Test
    @DisplayName("CG4 - 6 groups 2 equals types")
    void checkCG4() {
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
        Assertions.assertTrue(CommonGoalType.CG4.checkCG4(bookShelf));
    }

    @Test
    @DisplayName("CG3 - 4 groups 4 equals types")
    void checkCG3() {
        BookShelf bookShelf = new BookShelf();
        Tile free = new Tile(TileType.FREE);
        Tile cat = new Tile(TileType.CATS);
        bookShelf.getBookshelf()[0][0] = cat;
        bookShelf.getBookshelf()[1][0] = cat;
        bookShelf.getBookshelf()[2][0] = cat;
        bookShelf.getBookshelf()[3][0] = cat;
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
        bookShelf.getBookshelf()[2][2] = cat;
        bookShelf.getBookshelf()[3][2] = cat;
        bookShelf.getBookshelf()[4][2] = free;
        bookShelf.getBookshelf()[5][2] = free;

        bookShelf.getBookshelf()[0][3] = free;
        bookShelf.getBookshelf()[1][3] = free;
        bookShelf.getBookshelf()[2][3] = free;
        bookShelf.getBookshelf()[3][3] = free;
        bookShelf.getBookshelf()[4][3] = free;
        bookShelf.getBookshelf()[5][3] = free;

        bookShelf.getBookshelf()[0][4] = free;
        bookShelf.getBookshelf()[1][4] = free;
        bookShelf.getBookshelf()[2][4] = free;
        bookShelf.getBookshelf()[3][4] = free;
        bookShelf.getBookshelf()[4][4] = free;
        bookShelf.getBookshelf()[5][4] = free;
        Assertions.assertTrue(CommonGoalType.CG3.checkCG3(bookShelf));
    }

    @Test
    @DisplayName("CG8 - 4 equals corners")
    void checkCG8() {
        BookShelf bookShelf = new BookShelf();
        Tile free = new Tile(TileType.FREE);
        Tile cat = new Tile(TileType.CATS);
        bookShelf.getBookshelf()[0][0] = cat;
        bookShelf.getBookshelf()[1][0] = cat;
        bookShelf.getBookshelf()[2][0] = cat;
        bookShelf.getBookshelf()[3][0] = cat;
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
        bookShelf.getBookshelf()[2][2] = cat;
        bookShelf.getBookshelf()[3][2] = cat;
        bookShelf.getBookshelf()[4][2] = free;
        bookShelf.getBookshelf()[5][2] = free;

        bookShelf.getBookshelf()[0][3] = free;
        bookShelf.getBookshelf()[1][3] = free;
        bookShelf.getBookshelf()[2][3] = free;
        bookShelf.getBookshelf()[3][3] = free;
        bookShelf.getBookshelf()[4][3] = free;
        bookShelf.getBookshelf()[5][3] = free;

        bookShelf.getBookshelf()[0][4] = cat;
        bookShelf.getBookshelf()[1][4] = free;
        bookShelf.getBookshelf()[2][4] = free;
        bookShelf.getBookshelf()[3][4] = free;
        bookShelf.getBookshelf()[4][4] = free;
        bookShelf.getBookshelf()[5][4] = cat;
        Assertions.assertTrue(CommonGoalType.CG8.checkCG8(bookShelf));
    }

    @Test
    @DisplayName("CG1 - 2 2x2 squares 4 equals tiles")
    void checkCG1(){
        BookShelf bookShelf = new BookShelf();
        Tile free = new Tile(TileType.FREE);
        Tile cat = new Tile(TileType.CATS);
        bookShelf.getBookshelf()[0][0] = free;
        bookShelf.getBookshelf()[1][0] = free;
        bookShelf.getBookshelf()[2][0] = free;
        bookShelf.getBookshelf()[3][0] = free;
        bookShelf.getBookshelf()[4][0] = free;
        bookShelf.getBookshelf()[5][0] = free;

        bookShelf.getBookshelf()[0][1] = cat;
        bookShelf.getBookshelf()[1][1] = cat;
        bookShelf.getBookshelf()[2][1] = free;
        bookShelf.getBookshelf()[3][1] = free;
        bookShelf.getBookshelf()[4][1] = free;
        bookShelf.getBookshelf()[5][1] = free;

        bookShelf.getBookshelf()[0][2] = cat;
        bookShelf.getBookshelf()[1][2] = cat;
        bookShelf.getBookshelf()[2][2] = free;
        bookShelf.getBookshelf()[3][2] = cat;
        bookShelf.getBookshelf()[4][2] = cat;
        bookShelf.getBookshelf()[5][2] = free;

        bookShelf.getBookshelf()[0][3] = free;
        bookShelf.getBookshelf()[1][3] = free;
        bookShelf.getBookshelf()[2][3] = free;
        bookShelf.getBookshelf()[3][3] = cat;
        bookShelf.getBookshelf()[4][3] = cat;
        bookShelf.getBookshelf()[5][3] = free;

        bookShelf.getBookshelf()[0][4] = free;
        bookShelf.getBookshelf()[1][4] = free;
        bookShelf.getBookshelf()[2][4] = free;
        bookShelf.getBookshelf()[3][4] = free;
        bookShelf.getBookshelf()[4][4] = free;
        bookShelf.getBookshelf()[5][4] = free;
        Assertions.assertTrue(CommonGoalType.CG1.checkCG1(bookShelf));
    }

    @Test
    @DisplayName("CG5 - 3 columns max 3 types")
    void checkCG5() {
        BookShelf bookShelf = new BookShelf();
        Tile free = new Tile(TileType.FREE);
        Tile cat = new Tile(TileType.CATS);
        Tile plant = new Tile(TileType.PLANTS);
        Tile trophy = new Tile(TileType.TROPHIES);
        bookShelf.getBookshelf()[0][0] = cat;
        bookShelf.getBookshelf()[1][0] = trophy;
        bookShelf.getBookshelf()[2][0] = trophy;
        bookShelf.getBookshelf()[3][0] = trophy;
        bookShelf.getBookshelf()[4][0] = trophy;
        bookShelf.getBookshelf()[5][0] = plant;

        bookShelf.getBookshelf()[0][1] = cat;
        bookShelf.getBookshelf()[1][1] = cat;
        bookShelf.getBookshelf()[2][1] = cat;
        bookShelf.getBookshelf()[3][1] = plant;
        bookShelf.getBookshelf()[4][1] = trophy;
        bookShelf.getBookshelf()[5][1] = cat;

        bookShelf.getBookshelf()[0][2] = plant;
        bookShelf.getBookshelf()[1][2] = plant;
        bookShelf.getBookshelf()[2][2] = plant;
        bookShelf.getBookshelf()[3][2] = plant;
        bookShelf.getBookshelf()[4][2] = plant;
        bookShelf.getBookshelf()[5][2] = cat;

        bookShelf.getBookshelf()[0][3] = free;
        bookShelf.getBookshelf()[1][3] = free;
        bookShelf.getBookshelf()[2][3] = free;
        bookShelf.getBookshelf()[3][3] = free;
        bookShelf.getBookshelf()[4][3] = free;
        bookShelf.getBookshelf()[5][3] = free;

        bookShelf.getBookshelf()[0][4] = free;
        bookShelf.getBookshelf()[1][4] = free;
        bookShelf.getBookshelf()[2][4] = free;
        bookShelf.getBookshelf()[3][4] = free;
        bookShelf.getBookshelf()[4][4] = free;
        bookShelf.getBookshelf()[5][4] = free;
        Assertions.assertTrue(CommonGoalType.CG5.checkCG5(bookShelf));
    }

    @Test
    @DisplayName("CG6 - 8 occurrences of a tile")
    void checkCG9() {
        BookShelf bookShelf = new BookShelf();
        Tile free = new Tile(TileType.FREE);
        Tile cat = new Tile(TileType.CATS);

        bookShelf.getBookshelf()[0][0] = free;
        bookShelf.getBookshelf()[1][0] = free;
        bookShelf.getBookshelf()[2][0] = free;
        bookShelf.getBookshelf()[3][0] = free;
        bookShelf.getBookshelf()[4][0] = free;
        bookShelf.getBookshelf()[5][0] = free;

        bookShelf.getBookshelf()[0][1] = free;
        bookShelf.getBookshelf()[1][1] = free;
        bookShelf.getBookshelf()[2][1] = free;
        bookShelf.getBookshelf()[3][1] = free;
        bookShelf.getBookshelf()[4][1] = free;
        bookShelf.getBookshelf()[5][1] = free;

        bookShelf.getBookshelf()[0][2] = free;
        bookShelf.getBookshelf()[1][2] = free;
        bookShelf.getBookshelf()[2][2] = free;
        bookShelf.getBookshelf()[3][2] = free;
        bookShelf.getBookshelf()[4][2] = free;
        bookShelf.getBookshelf()[5][2] = free;

        bookShelf.getBookshelf()[0][3] = cat;
        bookShelf.getBookshelf()[1][3] = cat;
        bookShelf.getBookshelf()[2][3] = cat;
        bookShelf.getBookshelf()[3][3] = cat;
        bookShelf.getBookshelf()[4][3] = cat;
        bookShelf.getBookshelf()[5][3] = cat;

        bookShelf.getBookshelf()[0][4] = cat;
        bookShelf.getBookshelf()[1][4] = cat;
        bookShelf.getBookshelf()[2][4] = free;
        bookShelf.getBookshelf()[3][4] = free;
        bookShelf.getBookshelf()[4][4] = free;
        bookShelf.getBookshelf()[5][4] = free;

        Assertions.assertTrue(CommonGoalType.CG9.checkCG9(bookShelf));
    }

    @Test
    @DisplayName("CG11 - 5 equals tile forming a diagonal")
    void checkCG11() {
        BookShelf bookShelf = new BookShelf();
        Tile free = new Tile(TileType.FREE);
        Tile cat = new Tile(TileType.CATS);

        bookShelf.getBookshelf()[0][0] = cat;
        bookShelf.getBookshelf()[1][0] = free;
        bookShelf.getBookshelf()[2][0] = free;
        bookShelf.getBookshelf()[3][0] = free;
        bookShelf.getBookshelf()[4][0] = free;
        bookShelf.getBookshelf()[5][0] = free;

        bookShelf.getBookshelf()[0][1] = free;
        bookShelf.getBookshelf()[1][1] = cat;
        bookShelf.getBookshelf()[2][1] = free;
        bookShelf.getBookshelf()[3][1] = free;
        bookShelf.getBookshelf()[4][1] = free;
        bookShelf.getBookshelf()[5][1] = free;

        bookShelf.getBookshelf()[0][2] = free;
        bookShelf.getBookshelf()[1][2] = free;
        bookShelf.getBookshelf()[2][2] = cat;
        bookShelf.getBookshelf()[3][2] = free;
        bookShelf.getBookshelf()[4][2] = free;
        bookShelf.getBookshelf()[5][2] = free;

        bookShelf.getBookshelf()[0][3] = free;
        bookShelf.getBookshelf()[1][3] = free;
        bookShelf.getBookshelf()[2][3] = free;
        bookShelf.getBookshelf()[3][3] = cat;
        bookShelf.getBookshelf()[4][3] = free;
        bookShelf.getBookshelf()[5][3] = free;

        bookShelf.getBookshelf()[0][4] = free;
        bookShelf.getBookshelf()[1][4] = free;
        bookShelf.getBookshelf()[2][4] = free;
        bookShelf.getBookshelf()[3][4] = free;
        bookShelf.getBookshelf()[4][4] = cat;
        bookShelf.getBookshelf()[5][4] = free;
        Assertions.assertTrue(CommonGoalType.CG11.checkCG11(bookShelf));
    }

    @Test
    @DisplayName("CG7 - 4 lines max 3 different types")
    void checkCG7() {
        BookShelf bookShelf = new BookShelf();
        Tile free = new Tile(TileType.FREE);
        Tile cat = new Tile(TileType.CATS);

        bookShelf.getBookshelf()[0][0] = cat;
        bookShelf.getBookshelf()[0][1] = cat;
        bookShelf.getBookshelf()[0][2] = cat;
        bookShelf.getBookshelf()[0][3] = cat;
        bookShelf.getBookshelf()[0][4] = cat;


        bookShelf.getBookshelf()[1][0] = cat;
        bookShelf.getBookshelf()[1][1] = cat;
        bookShelf.getBookshelf()[1][2] = cat;
        bookShelf.getBookshelf()[1][3] = cat;
        bookShelf.getBookshelf()[1][4] = cat;


        bookShelf.getBookshelf()[2][0] = cat;
        bookShelf.getBookshelf()[2][1] = cat;
        bookShelf.getBookshelf()[2][2] = cat;
        bookShelf.getBookshelf()[2][3] = cat;
        bookShelf.getBookshelf()[2][4] = cat;


        bookShelf.getBookshelf()[3][0] = cat;
        bookShelf.getBookshelf()[3][1] = cat;
        bookShelf.getBookshelf()[3][2] = cat;
        bookShelf.getBookshelf()[3][3] = cat;
        bookShelf.getBookshelf()[3][4] = cat;


        bookShelf.getBookshelf()[4][0] = free;
        bookShelf.getBookshelf()[4][1] = free;
        bookShelf.getBookshelf()[4][2] = free;
        bookShelf.getBookshelf()[4][3] = free;
        bookShelf.getBookshelf()[4][4] = free;

        bookShelf.getBookshelf()[5][0] = free;
        bookShelf.getBookshelf()[5][1] = free;
        bookShelf.getBookshelf()[5][2] = free;
        bookShelf.getBookshelf()[5][3] = free;
        bookShelf.getBookshelf()[5][4] = free;

        Assertions.assertTrue(CommonGoalType.CG7.checkCG7(bookShelf));
    }

    @Test
    @DisplayName("CG2 - 2 columns all different objects")
    void checkCG2() {
        BookShelf bookShelf = new BookShelf();
        Tile free = new Tile(TileType.FREE);
        Tile cat = new Tile(TileType.CATS);
        Tile trophy = new Tile(TileType.TROPHIES);
        Tile plant = new Tile(TileType.PLANTS);
        Tile frame = new Tile(TileType.FRAMES);
        Tile toy = new Tile(TileType.TOYS);
        Tile book = new Tile(TileType.BOOKS);

        bookShelf.getBookshelf()[0][0] = cat;
        bookShelf.getBookshelf()[1][0] = book;
        bookShelf.getBookshelf()[2][0] = trophy;
        bookShelf.getBookshelf()[3][0] = frame;
        bookShelf.getBookshelf()[4][0] = plant;
        bookShelf.getBookshelf()[5][0] = toy;

        bookShelf.getBookshelf()[0][1] = toy;
        bookShelf.getBookshelf()[1][1] = cat;
        bookShelf.getBookshelf()[2][1] = frame;
        bookShelf.getBookshelf()[3][1] = plant;
        bookShelf.getBookshelf()[4][1] = trophy;
        bookShelf.getBookshelf()[5][1] = book;

        bookShelf.getBookshelf()[0][2] = cat;
        bookShelf.getBookshelf()[1][2] = plant;
        bookShelf.getBookshelf()[2][2] = trophy;
        bookShelf.getBookshelf()[3][2] = frame;
        bookShelf.getBookshelf()[4][2] = toy;
        bookShelf.getBookshelf()[5][2] = toy;

        bookShelf.getBookshelf()[0][3] = free;
        bookShelf.getBookshelf()[1][3] = free;
        bookShelf.getBookshelf()[2][3] = free;
        bookShelf.getBookshelf()[3][3] = free;
        bookShelf.getBookshelf()[4][3] = free;
        bookShelf.getBookshelf()[5][3] = free;

        bookShelf.getBookshelf()[0][4] = free;
        bookShelf.getBookshelf()[1][4] = free;
        bookShelf.getBookshelf()[2][4] = free;
        bookShelf.getBookshelf()[3][4] = free;
        bookShelf.getBookshelf()[4][4] = free;
        bookShelf.getBookshelf()[5][4] = free;
        Assertions.assertTrue(CommonGoalType.CG2.checkCG2(bookShelf));
    }

    @Test
    @DisplayName("CG6 - 2 rows all different objects")
    void checkCG6() {
        BookShelf bookShelf = new BookShelf();
        Tile free = new Tile(TileType.FREE);
        Tile cat = new Tile(TileType.CATS);
        Tile trophy = new Tile(TileType.TROPHIES);
        Tile plant = new Tile(TileType.PLANTS);
        Tile frame = new Tile(TileType.FRAMES);
        Tile toy = new Tile(TileType.TOYS);

        bookShelf.getBookshelf()[0][0] = cat;
        bookShelf.getBookshelf()[0][1] = trophy;
        bookShelf.getBookshelf()[0][2] = plant;
        bookShelf.getBookshelf()[0][3] = frame;
        bookShelf.getBookshelf()[0][4] = toy;


        bookShelf.getBookshelf()[1][0] = cat;
        bookShelf.getBookshelf()[1][1] = plant;
        bookShelf.getBookshelf()[1][2] = trophy;
        bookShelf.getBookshelf()[1][3] = frame;
        bookShelf.getBookshelf()[1][4] = toy;


        bookShelf.getBookshelf()[2][0] = cat;
        bookShelf.getBookshelf()[2][1] = frame;
        bookShelf.getBookshelf()[2][2] = toy;
        bookShelf.getBookshelf()[2][3] = plant;
        bookShelf.getBookshelf()[2][4] = free;


        bookShelf.getBookshelf()[3][0] = free;
        bookShelf.getBookshelf()[3][1] = free;
        bookShelf.getBookshelf()[3][2] = free;
        bookShelf.getBookshelf()[3][3] = free;
        bookShelf.getBookshelf()[3][4] = free;


        bookShelf.getBookshelf()[4][0] = free;
        bookShelf.getBookshelf()[4][1] = free;
        bookShelf.getBookshelf()[4][2] = free;
        bookShelf.getBookshelf()[4][3] = free;
        bookShelf.getBookshelf()[4][4] = free;

        bookShelf.getBookshelf()[5][0] = free;
        bookShelf.getBookshelf()[5][1] = free;
        bookShelf.getBookshelf()[5][2] = free;
        bookShelf.getBookshelf()[5][3] = free;
        bookShelf.getBookshelf()[5][4] = free;
        Assertions.assertTrue(CommonGoalType.CG6.checkCG6(bookShelf));
    }

    @Test
    @DisplayName("CG10 - 5 equals tiles forming an X")
    void checkCG10() {
        BookShelf bookShelf = new BookShelf();
        Tile free = new Tile(TileType.FREE);
        Tile cat = new Tile(TileType.CATS);

        bookShelf.getBookshelf()[0][0] = cat;
        bookShelf.getBookshelf()[1][0] = free;
        bookShelf.getBookshelf()[2][0] = cat;
        bookShelf.getBookshelf()[3][0] = free;
        bookShelf.getBookshelf()[4][0] = free;
        bookShelf.getBookshelf()[5][0] = free;

        bookShelf.getBookshelf()[0][1] = free;
        bookShelf.getBookshelf()[1][1] = cat;
        bookShelf.getBookshelf()[2][1] = free;
        bookShelf.getBookshelf()[3][1] = free;
        bookShelf.getBookshelf()[4][1] = free;
        bookShelf.getBookshelf()[5][1] = free;

        bookShelf.getBookshelf()[0][2] = cat;
        bookShelf.getBookshelf()[1][2] = free;
        bookShelf.getBookshelf()[2][2] = cat;
        bookShelf.getBookshelf()[3][2] = free;
        bookShelf.getBookshelf()[4][2] = free;
        bookShelf.getBookshelf()[5][2] = free;

        bookShelf.getBookshelf()[0][3] = free;
        bookShelf.getBookshelf()[1][3] = free;
        bookShelf.getBookshelf()[2][3] = free;
        bookShelf.getBookshelf()[3][3] = free;
        bookShelf.getBookshelf()[4][3] = free;
        bookShelf.getBookshelf()[5][3] = free;

        bookShelf.getBookshelf()[0][4] = free;
        bookShelf.getBookshelf()[1][4] = free;
        bookShelf.getBookshelf()[2][4] = free;
        bookShelf.getBookshelf()[3][4] = free;
        bookShelf.getBookshelf()[4][4] = free;
        bookShelf.getBookshelf()[5][4] = free;
        Assertions.assertTrue(CommonGoalType.CG10.checkCG10(bookShelf));
    }

    @Test
    @DisplayName("CG12 - columns with decrescendos number")
    void checkCG12() {
        BookShelf bookShelf = new BookShelf();
        Tile free = new Tile(TileType.FREE);
        Tile cat = new Tile(TileType.CATS);
        bookShelf.getBookshelf()[0][0] = free;
        bookShelf.getBookshelf()[1][0] = free;
        bookShelf.getBookshelf()[2][0] = free;
        bookShelf.getBookshelf()[3][0] = free;
        bookShelf.getBookshelf()[4][0] = cat;
        bookShelf.getBookshelf()[5][0] = cat;

        bookShelf.getBookshelf()[0][1] = free;
        bookShelf.getBookshelf()[1][1] = free;
        bookShelf.getBookshelf()[2][1] = free;
        bookShelf.getBookshelf()[3][1] = cat;
        bookShelf.getBookshelf()[4][1] = cat;
        bookShelf.getBookshelf()[5][1] = cat;

        bookShelf.getBookshelf()[0][2] = free;
        bookShelf.getBookshelf()[1][2] = free;
        bookShelf.getBookshelf()[2][2] = cat;
        bookShelf.getBookshelf()[3][2] = cat;
        bookShelf.getBookshelf()[4][2] = cat;
        bookShelf.getBookshelf()[5][2] = cat;

        bookShelf.getBookshelf()[0][3] = free;
        bookShelf.getBookshelf()[1][3] = cat;
        bookShelf.getBookshelf()[2][3] = cat;
        bookShelf.getBookshelf()[3][3] = cat;
        bookShelf.getBookshelf()[4][3] = cat;
        bookShelf.getBookshelf()[5][3] = cat;

        bookShelf.getBookshelf()[0][4] = cat;
        bookShelf.getBookshelf()[1][4] = cat;
        bookShelf.getBookshelf()[2][4] = cat;
        bookShelf.getBookshelf()[3][4] = cat;
        bookShelf.getBookshelf()[4][4] = cat;
        bookShelf.getBookshelf()[5][4] = cat;
        Assertions.assertTrue(CommonGoalType.CG12.checkCG12(bookShelf));
    }

    @Test
    @DisplayName("STATIC - Column has duplicates")
    void hasDuplicatesInColumn() {
        BookShelf bookShelf = new BookShelf();
        Tile cat = new Tile(TileType.CATS);
        Tile trophy = new Tile(TileType.TROPHIES);
        Tile plant = new Tile(TileType.PLANTS);
        Tile frame = new Tile(TileType.FRAMES);
        Tile book = new Tile(TileType.BOOKS);
        bookShelf.getBookshelf()[0][0] = cat;
        bookShelf.getBookshelf()[1][0] = book;
        bookShelf.getBookshelf()[2][0] = trophy;
        bookShelf.getBookshelf()[3][0] = frame;
        bookShelf.getBookshelf()[4][0] = plant;
        bookShelf.getBookshelf()[5][0] = cat;
        Assertions.assertTrue(CommonGoalType.hasDuplicatesOrFreeInColumn(bookShelf.getBookshelf(), 0));
    }

    @Test
    @DisplayName("STATIC - Row has duplicates")
    void hasDuplicatesInRows() {
        BookShelf bookShelf = new BookShelf();
        Tile cat = new Tile(TileType.CATS);
        Tile trophy = new Tile(TileType.TROPHIES);
        Tile plant = new Tile(TileType.PLANTS);
        Tile frame = new Tile(TileType.FRAMES);

        bookShelf.getBookshelf()[0][0] = cat;
        bookShelf.getBookshelf()[0][1] = trophy;
        bookShelf.getBookshelf()[0][2] = plant;
        bookShelf.getBookshelf()[0][3] = frame;
        bookShelf.getBookshelf()[0][4] = cat;
        Assertions.assertTrue(CommonGoalType.hasDuplicatesOrFreeInRow(bookShelf.getBookshelf(), 0));
    }

    @Test
    @DisplayName("STATIC - Column has max 3 types")
    void hasMaxThreeTypesInColumn() {
        BookShelf bookShelf = new BookShelf();
        Tile plant = new Tile(TileType.PLANTS);
        Tile frame = new Tile(TileType.FRAMES);
        Tile book = new Tile(TileType.BOOKS);
        bookShelf.getBookshelf()[0][0] = book;
        bookShelf.getBookshelf()[1][0] = book;
        bookShelf.getBookshelf()[2][0] = book;
        bookShelf.getBookshelf()[3][0] = frame;
        bookShelf.getBookshelf()[4][0] = plant;
        bookShelf.getBookshelf()[5][0] = book;
        Assertions.assertEquals(3, CommonGoalType.hasMaxThreeTypesInColumn(bookShelf.getBookshelf(), 0));
    }

    @Test
    @DisplayName("STATIC - Row has max 3 types")
    void hasMaxThreeTypesInRow() {
        BookShelf bookShelf = new BookShelf();
        Tile cat = new Tile(TileType.CATS);
        Tile trophy = new Tile(TileType.TROPHIES);
        Tile plant = new Tile(TileType.PLANTS);

        bookShelf.getBookshelf()[0][0] = cat;
        bookShelf.getBookshelf()[0][1] = trophy;
        bookShelf.getBookshelf()[0][2] = plant;
        bookShelf.getBookshelf()[0][3] = cat;
        bookShelf.getBookshelf()[0][4] = cat;
        Assertions.assertEquals(3, CommonGoalType.hasMaxThreeTypesInRow(bookShelf.getBookshelf(), 0));
    }

    @Test
    void getAdjacentPairs() {
    }

    @Test
    void getAdjacentQuads() {
    }

    @Test
    void countVectorsWithEqualValues() {
    }
}