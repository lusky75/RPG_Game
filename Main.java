import java.lang.*;
import java.util.*;

public class Main {

    public static void main(String [] args) {

        System.out.println("Bienvenue au RPG !");
        System.out.println();
        Map map = new Map();
        World w = map.createMap(21, 21);
        Player p = map.create_player(0, 0, 0, 0, 'J');
        Player ennemy1 = map.create_player(15, 18, 5, 2, 'M');
        Player ennemy2 = map.create_player(14, 11, 4, 1, 'M');
        Exit e = new Exit();
        Key key = new Key("key");
        
        w.registerTangibleAt(0, 0, p);
        w.registerTangibleAt(15, 18, ennemy1);
        w.registerTangibleAt(14, 11, ennemy2);
        w.registerTangibleAt(19, 16, e);
        w.registerTangibleAt(0, 2, key);
        map.put_random_object(w, 20, 21, 21);
        w.print();
        map.print_keyboard();
        map.start_game(p, w);
    }

}
