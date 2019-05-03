//
// ETNA PROJECT, 24/10/2018 by chen_l
// GameRunner.java
// File description:
//      [...]
//

import java.util.*;

public class GameRunner {

    private String input;
    private Key keyToPlay;
    private Question q;

    public GameRunner(Key key,String input) {
        this.keyToPlay = key;
        this.input = input;
    }
    
    public boolean playGame(Game game) {
        return game.play(keyToPlay, input, q);
    }

}
