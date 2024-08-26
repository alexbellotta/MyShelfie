package it.polimi.ingsw.Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GoalDeckTest {

    @Test
    public void testGoalDeck(){
        try{
            GoalDeck deck = new GoalDeck();
        }catch (Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void testDrawNext(){

        GoalDeck deck = new GoalDeck();

        PersonalGoal goal = deck.drawNext();

        assertEquals(11, deck.allGoals.size());

    }
}
