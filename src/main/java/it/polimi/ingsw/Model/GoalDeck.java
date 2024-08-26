package it.polimi.ingsw.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class represents the personal goals deck from where.
 * the players draw their personal goal for the current game.
 */
public class GoalDeck implements Serializable {
    /**
     * List of all personal goals.
     */
    ArrayList<PersonalGoal> allGoals;

    /**
     * Class constructor.
     * Add all the personal goals to the deck.
     */
    public GoalDeck()
    {
        this.allGoals = new ArrayList<>();
        allGoals.add(PersonalGoal.Goal1());
        allGoals.add(PersonalGoal.Goal2());
        allGoals.add(PersonalGoal.Goal3());
        allGoals.add(PersonalGoal.Goal4());
        allGoals.add(PersonalGoal.Goal5());
        allGoals.add(PersonalGoal.Goal6());
        allGoals.add(PersonalGoal.Goal7());
        allGoals.add(PersonalGoal.Goal8());
        allGoals.add(PersonalGoal.Goal9());
        allGoals.add(PersonalGoal.Goal10());
        allGoals.add(PersonalGoal.Goal11());
        allGoals.add(PersonalGoal.Goal12());
    }

    /**
     * Allows to take one of the personal goals by enumeration.
     * Deck is ordered from 0 to 12.
     *
     * @param i number of the goal.
     * @return goal selected by i parameter.
     */
    public PersonalGoal getGoal(int i){
        return this.allGoals.get(i);
    }

    /**
     * This method is used to draw one of the 12 personal goals.
     * It removes from the list the drawn persona goal.
     *
     * @return the drawn personal goal.
     */
    public PersonalGoal drawNext(){
        int randomIndex = new Random().nextInt(allGoals.size());
        PersonalGoal goal = this.allGoals.get(randomIndex);
        this.allGoals.remove(randomIndex);
        return goal;
    }
}
