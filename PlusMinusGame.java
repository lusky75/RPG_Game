//
// ETNA PROJECT, 24/10/2018 by chen_l
// PlusMinusGame.java
// File description:
//      [...]
//

import java.util.*;

public class PlusMinusGame extends Game {

    private int findValue;
    
    public PlusMinusGame(String name, Key keyCondition, Key keyReward, int findValue) {
        super(name, keyCondition, keyReward);
        this.findValue = findValue;
    }

    @Override
    public void hasLost() {
        this.setLose(true);
    }

    public boolean find_value(String input) {
        int value = 0;
        
        value = Integer.parseInt(input);
        if (value > findValue)
            System.out.println("Minus");
        else if (value < findValue)
            System.out.println("Plus");
        else {
            System.out.println("Win");
            return true;
        }
        return false;
    }
    
    @Override
    public boolean play(Key k, String input) {
        if (!canPlay(k)) {
            System.out.println("Key required");
            return false;
        }
        return find_value(input);
    }

    @Override
    public boolean play(Key k, String input, Question q) {
        return true;
    }

    @Override
    public boolean canPlay(Key k) {
        if (k == null && k != getKeyCondition())
            return false;
        if (getKeyCondition() == null) {
            return true;
        }
        if (k.equals(getKeyCondition())) {
            return true;
        }
        return false;
    }

    @Override
    public Key reward() {
        return getKeyReward();
    }
}
