package it.polimi.ingsw.Model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void placeHand() {
        ArrayList<CommonGoal> commonGoals = new ArrayList<>();
        BookShelf bookshelf;
        ArrayList<Tile> hand = new ArrayList<>();
        Tile[][] library;

        commonGoals.add( new CommonGoal(CommonGoalType.CG1, 2));
        commonGoals.add( new CommonGoal(CommonGoalType.CG2, 2));

        Player player = new Player("gilberto",PersonalGoal.Goal1(),commonGoals);
        bookshelf = player.getBookshelfFromPlayer();
        library = bookshelf.getBookshelf();

        assertTrue(bookshelf.isEmpty());

        hand.add(new Tile(TileType.PLANTS));
        player.setHand(hand);
        player.placeHand(0);
        assertSame(library[5][0].getObjectType(), TileType.PLANTS);

        hand.add(new Tile(TileType.CATS));
        player.setHand(hand);
        player.placeHand(4);
        assertSame(library[4][4].getObjectType(), TileType.CATS);
        assertSame(library[5][4].getObjectType(), TileType.PLANTS);

    }

    @Test
    void getPlayerPoints() {
        ArrayList<CommonGoal> commonGoals = new ArrayList<>();
        BookShelf bookshelf;
        ArrayList<Tile> hand = new ArrayList<>();
        Tile[][] playerLibrary;
        Tile freeObject = new Tile(TileType.FREE);
        int intBuffer;

        commonGoals.add( new CommonGoal(CommonGoalType.CG1, 2));
        commonGoals.add( new CommonGoal(CommonGoalType.CG8, 2));

        Player player = new Player("gilberto",PersonalGoal.Goal1(),commonGoals);
        bookshelf = player.getBookshelfFromPlayer();
        playerLibrary = bookshelf.getBookshelf();

        assertTrue(bookshelf.isEmpty());
        //player.checkCommonGoals();
        assertEquals(0, player.getPlayerPoints());

        player.setFirstToComplete();
        hand.add(freeObject);
        player.setHand(hand);
        player.placeHand(2);
        intBuffer = player.getPlayerPoints();
        assertEquals(1, intBuffer);

        playerLibrary[0][0] = new Tile(TileType.CATS);
        playerLibrary[0][1] = new Tile(TileType.CATS);
        playerLibrary[1][0] = new Tile(TileType.CATS);
        playerLibrary[1][1] = new Tile(TileType.CATS);

        //player.checkCommonGoals();
        intBuffer = player.getPlayerPoints();
        assertEquals(4, intBuffer);

        playerLibrary[3][0] = new Tile(TileType.PLANTS);
        playerLibrary[3][1] = new Tile(TileType.PLANTS);
        playerLibrary[4][0] = new Tile(TileType.PLANTS);
        playerLibrary[4][1] = new Tile(TileType.PLANTS);

        //player.checkCommonGoals();
        intBuffer = player.getPlayerPoints();
        assertEquals(15, intBuffer);

        playerLibrary[0][4].setObjectType(TileType.CATS);
        playerLibrary[5][0].setObjectType(TileType.CATS);
        playerLibrary[5][4].setObjectType(TileType.CATS);

        //player.checkCommonGoals();
        intBuffer = player.getPlayerPoints();
        assertEquals(23, intBuffer);

    }

    @Test
    void checkPersonalGoal() {

        ArrayList<CommonGoal> commonGoals = new ArrayList<>();
        BookShelf bookshelf;
        Tile[][] playerLibrary;

        commonGoals.add( new CommonGoal(CommonGoalType.CG3, 2));
        commonGoals.add( new CommonGoal(CommonGoalType.CG2, 2));

        Player player = new Player("gilberto",PersonalGoal.Goal1(),commonGoals);
        bookshelf = player.getBookshelfFromPlayer();
        playerLibrary = bookshelf.getBookshelf();

        assertTrue(bookshelf.isEmpty());
        //player.checkCommonGoals();
        assertEquals(0, player.getPlayerPoints());

        //so far is the same as the test before

        player.setPersonalGoal(PersonalGoal.Goal1());

        playerLibrary[0][0].setObjectType(TileType.PLANTS);
        assertEquals(1, player.getPlayerPoints());
        playerLibrary[0][2].setObjectType(TileType.FRAMES);
        assertEquals(2, player.getPlayerPoints());
        playerLibrary[1][4].setObjectType(TileType.CATS);
        assertEquals(4, player.getPlayerPoints());
        playerLibrary[2][3].setObjectType(TileType.BOOKS);
        assertEquals(6, player.getPlayerPoints());
        playerLibrary[3][1].setObjectType(TileType.TOYS);
        assertEquals(9, player.getPlayerPoints());
        playerLibrary[5][2].setObjectType(TileType.TROPHIES);
        assertEquals(12, player.getPlayerPoints());

    }
}