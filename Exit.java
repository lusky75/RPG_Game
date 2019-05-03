//
// ETNA PROJECT, 17/10/2018 by chen_l
// Exit.java
// File description:
//      [...]
//

public class Exit implements Tangible {

    public void action(Player player) {
        System.out.println("Bravo vous avez fini le jeu, score " + player.getPoint());
    }
    
    public char showAs() {
        return 'E';
    }
}
