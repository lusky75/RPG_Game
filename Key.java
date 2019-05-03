//
// ETNA PROJECT, 24/10/2018 by chen_l
// Key.java
// File description:
//      [...]
//

import java.util.*;

public class Key implements Tangible {

    private String name;

    public Key(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void action(Player player) {
        System.out.print(String.format("\033[2J"));
        Launch launch = new Launch();
        Key key2 = launch.start_quiz(player);
        if (key2 == null) {
            System.out.println("Key required");
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                System.out.println("Interrupted.");
            }
            return;
        }
        System.out.print(String.format("\033[2J"));
        Key key3 = launch.start_PlusMinusGame(key2, player);
        if (key3 == null) {
            System.out.println("Key required");
            return;
        }
        System.out.print(String.format("\033[2J"));
        Key key4 = launch.start_life_game(key3, player);
        player.removeEnergy(10);
    }
    
    public char showAs() {
        return 'K';
    }
}
