package it.polimi.ingsw.Model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class PersonaGoalTest {

    @Test
    public void PersonalGoalTest(){

        PersonalGoalWithPosition[] personalGoalWithPositions = new PersonalGoalWithPosition[5];
        personalGoalWithPositions[0] = new PersonalGoalWithPosition(1,1, TileType.PLANTS);
        personalGoalWithPositions[1] = new PersonalGoalWithPosition(2,1, TileType.BOOKS);
        personalGoalWithPositions[2] = new PersonalGoalWithPosition(3,4, TileType.PLANTS);
        personalGoalWithPositions[3] = new PersonalGoalWithPosition(5,5, TileType.PLANTS);
        personalGoalWithPositions[4] = new PersonalGoalWithPosition(1,3, TileType.FRAMES);

        try{
            PersonalGoal personalGoal = new PersonalGoal(personalGoalWithPositions);
        }catch (Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void PersonalGoalConstructor_shouldThrow_onDuplicatedCoordinates(){

        //eccezzione per coordinatedoppie
        PersonalGoalWithPosition[] personalGoalWithPositions = new PersonalGoalWithPosition[5];
        personalGoalWithPositions[0] = new PersonalGoalWithPosition(1,1, TileType.PLANTS);
        personalGoalWithPositions[1] = new PersonalGoalWithPosition(1,1, TileType.BOOKS);
        personalGoalWithPositions[2] = new PersonalGoalWithPosition(3,4, TileType.PLANTS);
        personalGoalWithPositions[3] = new PersonalGoalWithPosition(5,5, TileType.PLANTS);
        personalGoalWithPositions[4] = new PersonalGoalWithPosition(1,3, TileType.FRAMES);

        try{
            PersonalGoal personalGoal = new PersonalGoal(personalGoalWithPositions);
            fail("errore calcolato per errata implementazione");
        }catch(RuntimeException e){

        }
    }
    @Test
    public void countGoalReachedTest() {
        /*
        BookShelf bookshelf = new BookShelf();

        ArrayList<ObjectCard> cardTest = new ArrayList<ObjectCard>();
        cardTest.add(ObjectType.BOOKS);
        cardTest.add(ObjectType.BOOKS);
        cardTest.add(ObjectType.BOOKS);

        bookshelf.placeObject(0,cardTest);

         */
       BookShelf bookshelfTest = new BookShelf();
/*
       for ( int i = 0; i< 6; i++){
           for ( int j = 0; j < 5; j++){
               bookshelfTest.getBookshelf()[i][j].setObjectType(ObjectType.FREE);
           }
        }

        for ( int i = 0; i< 6; i++){
            for ( int j = 0; j < 5; j++){
                bookshelfTest.getBookshelf()[i][j].getObjectType();
            }
        }
        */

        GoalDeck deck = new GoalDeck();
        PersonalGoal goal = deck.getGoal(3);


        // test aggiungendo 3 libri alla colonna 1

       Tile one = new Tile(TileType.BOOKS);
       Tile two = new Tile(TileType.BOOKS);
       Tile three = new Tile(TileType.BOOKS);

       ArrayList<Tile> hand = new ArrayList();
       hand.add(one);
       hand.add(two);
       hand.add(three);

       bookshelfTest.placeObject(1,hand);

       // controllo che il confronto con il personal goal 3 sia 1

       assertEquals(1,goal.countGoalReached(bookshelfTest));

        //test aggiungendo 3 nuovi gatti nella seconda colonna

        Tile one1 = new Tile(TileType.CATS);
        Tile two2 = new Tile(TileType.CATS);
        Tile three3 = new Tile(TileType.CATS);

        ArrayList<Tile> hand1 = new ArrayList();
        hand1.add(one1);
        hand1.add(two2);
        hand1.add(three3);

        bookshelfTest.placeObject(2,hand1);

        assertEquals(2,goal.countGoalReached(bookshelfTest));


        // aggiungo un solo frames dove dovrebbe essere per il personal goal

        Tile one11 = new Tile(TileType.FRAMES);

        ArrayList<Tile> hand2 = new ArrayList();
        hand2.add(one11);

        bookshelfTest.placeObject(2,hand2);

        assertEquals(3,goal.countGoalReached(bookshelfTest));

        assertEquals(true,bookshelfTest.getBookshelf()[3][1].getObjectType() == TileType.BOOKS);
    }


}