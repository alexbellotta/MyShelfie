package it.polimi.ingsw.Model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


class BookShelfTest {

    private static final int DEFINE_ROW = 6;
    private static final int DEFINE_COL = 5;

    @Test
    void checkAdjacentObjects() {
        BookShelf bookShelf = new BookShelf();
        Tile[][] tile = bookShelf.getBookshelf();

        tile[0][0].setObjectType(TileType.CATS);
        assertEquals(0, bookShelf.checkAdjacentObjects());
        tile[1][0].setObjectType(TileType.CATS);
        assertEquals(0, bookShelf.checkAdjacentObjects());
        tile[2][0].setObjectType(TileType.CATS);
        assertEquals(2, bookShelf.checkAdjacentObjects());
        tile[3][0].setObjectType(TileType.CATS);
        assertEquals(3, bookShelf.checkAdjacentObjects());
        tile[4][0].setObjectType(TileType.CATS);
        assertEquals(5, bookShelf.checkAdjacentObjects());
        tile[5][0].setObjectType(TileType.CATS);
        assertEquals(8, bookShelf.checkAdjacentObjects());

        tile[0][1].setObjectType(TileType.PLANTS);
        assertEquals(8, bookShelf.checkAdjacentObjects());
        tile[1][1].setObjectType(TileType.PLANTS);
        assertEquals(8, bookShelf.checkAdjacentObjects());
        tile[2][1].setObjectType(TileType.PLANTS);
        assertEquals(10, bookShelf.checkAdjacentObjects());
        tile[3][1].setObjectType(TileType.PLANTS);
        assertEquals(11, bookShelf.checkAdjacentObjects());
        tile[4][1].setObjectType(TileType.PLANTS);
        assertEquals(13, bookShelf.checkAdjacentObjects());
        tile[5][1].setObjectType(TileType.PLANTS);
        assertEquals(16, bookShelf.checkAdjacentObjects());

        tile[2][2].setObjectType(TileType.TROPHIES);
        assertEquals(16, bookShelf.checkAdjacentObjects());
        tile[2][3].setObjectType(TileType.TROPHIES);
        assertEquals(16, bookShelf.checkAdjacentObjects());
        tile[2][4].setObjectType(TileType.TROPHIES);
        assertEquals(18, bookShelf.checkAdjacentObjects());
        tile[1][2].setObjectType(TileType.TROPHIES);
        assertEquals(19, bookShelf.checkAdjacentObjects());
        tile[3][2].setObjectType(TileType.TROPHIES);
        assertEquals(21, bookShelf.checkAdjacentObjects());
        tile[4][2].setObjectType(TileType.TROPHIES);
        assertEquals(24, bookShelf.checkAdjacentObjects());

    }

    @Test
    void isEmpty() {
        BookShelf bookShelf = new BookShelf();
        Tile[][] objectArray = bookShelf.getBookshelf();

        assertTrue(bookShelf.isEmpty());

        objectArray[0][0].setObjectType(TileType.PLANTS);
        assertFalse(bookShelf.isEmpty());
    }

    @Test
    void isFull() {
        BookShelf bookShelf = new BookShelf();
        Tile[][] objectArray = bookShelf.getBookshelf();
        int i,j;

        assertFalse(bookShelf.isFull());
        for(i=0;i<DEFINE_ROW;i++)
            for(j=0;j<DEFINE_COL;j++)
                objectArray[i][j].setObjectType(TileType.BOOKS);

        assertTrue(bookShelf.isFull());

        objectArray[0][0].setObjectType(TileType.FREE);
        assertFalse(bookShelf.isFull());
    }

    @Test
    void placeObject() {
        BookShelf bookShelf = new BookShelf();
        Tile[][] tile = bookShelf.getBookshelf();
        int i,j;

        ArrayList<Tile> array1 = new ArrayList<>();
        ArrayList<Tile> array2 = new ArrayList<>();
        ArrayList<Tile> array3 = new ArrayList<>();

        array1.add(new Tile(TileType.BOOKS));

        array2.add(new Tile(TileType.CATS));
        array2.add(new Tile(TileType.FRAMES));

        array3.add(new Tile(TileType.TROPHIES));
        array3.add(new Tile(TileType.TOYS));
        array3.add(new Tile(TileType.PLANTS));

        assertTrue(bookShelf.isEmpty());

        assertTrue(bookShelf.placeObject(0,array1));
        assertSame(tile[DEFINE_ROW - 1][0].getObjectType(), TileType.BOOKS);

        for(i=0;i<DEFINE_ROW;i++)
            for(j=0;j<DEFINE_COL;j++)
                if(i==DEFINE_ROW-1 && j==0)
                    continue;
                else
                    assertSame(tile[i][j].getObjectType(), TileType.FREE);

        assertTrue(bookShelf.placeObject(0,array2));
        assertSame(tile[DEFINE_ROW - 1][0].getObjectType(), TileType.BOOKS);
        assertSame(tile[DEFINE_ROW - 2][0].getObjectType(), TileType.CATS);
        assertSame(tile[DEFINE_ROW - 3][0].getObjectType(), TileType.FRAMES);

        for(i=0;i<DEFINE_ROW;i++)
            for(j=0;j<DEFINE_COL;j++)
                if((i==DEFINE_ROW-1 || i==DEFINE_ROW-2 || i==DEFINE_ROW-3) && j==0)
                    continue;
                else
                    assertSame(tile[i][j].getObjectType(), TileType.FREE);

        assertTrue(bookShelf.placeObject(0,array3));
        assertSame(tile[DEFINE_ROW - 1][0].getObjectType(), TileType.BOOKS);
        assertSame(tile[DEFINE_ROW - 2][0].getObjectType(), TileType.CATS);
        assertSame(tile[DEFINE_ROW - 3][0].getObjectType(), TileType.FRAMES);
        assertSame(tile[DEFINE_ROW - 4][0].getObjectType(), TileType.TROPHIES);
        assertSame(tile[DEFINE_ROW - 5][0].getObjectType(), TileType.TOYS);
        assertSame(tile[DEFINE_ROW - 6][0].getObjectType(), TileType.PLANTS);

        for(i=0;i<DEFINE_ROW;i++)
            for(j=1;j<DEFINE_COL;j++)
                assertSame(tile[i][j].getObjectType(), TileType.FREE);


        assertSame(tile[DEFINE_ROW - 1][0].getObjectType(), TileType.BOOKS);
        assertSame(tile[DEFINE_ROW - 2][0].getObjectType(), TileType.CATS);
        assertSame(tile[DEFINE_ROW - 3][0].getObjectType(), TileType.FRAMES);
        assertSame(tile[DEFINE_ROW - 4][0].getObjectType(), TileType.TROPHIES);
        assertSame(tile[DEFINE_ROW - 5][0].getObjectType(), TileType.TOYS);
        assertSame(tile[DEFINE_ROW - 6][0].getObjectType(), TileType.PLANTS);

        for(i=0;i<DEFINE_ROW;i++)
            for(j=1;j<DEFINE_COL;j++)
                assertSame(tile[i][j].getObjectType(), TileType.FREE);

        tile[DEFINE_ROW-5][0].setObjectType(TileType.FREE);
        tile[DEFINE_ROW-6][0].setObjectType(TileType.FREE);
        assertTrue(bookShelf.placeObject(0,array2));
        assertSame(tile[DEFINE_ROW - 1][0].getObjectType(), TileType.BOOKS);
        assertSame(tile[DEFINE_ROW - 2][0].getObjectType(), TileType.CATS);
        assertSame(tile[DEFINE_ROW - 3][0].getObjectType(), TileType.FRAMES);
        assertSame(tile[DEFINE_ROW - 4][0].getObjectType(), TileType.TROPHIES);
        assertSame(tile[DEFINE_ROW - 5][0].getObjectType(), TileType.CATS);
        assertSame(tile[DEFINE_ROW - 6][0].getObjectType(), TileType.FRAMES);

        for(i=0;i<DEFINE_ROW;i++)
            for(j=1;j<DEFINE_COL;j++)
                assertSame(tile[i][j].getObjectType(), TileType.FREE);
    }
}