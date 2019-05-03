import java.lang.*;
import java.util.*;

public class Map {

    public Player create_player(int x, int y, int attack, int defence, char joueur) {
        List<Item> item = new ArrayList<Item>();
        HealthBar hb = new HealthBar("health", 3, 3);
        return new Player(joueur, new Position(x, y), new Stats(attack, defence), hb, item);
    }
    
    public World createMap(int r, int c) {
        World w = new World(r, c);
        for (int i = 0; i < w.getWorld().length; i++)
            for (int j = 0; j < w.getWorld()[i].length; j++)
                w.registerCellAt(i, j, new Cell(new Ground()));
        int [][] chemin = {
            {1, 3, 5, 10, 12, 17},
            {1, 3, 5, 6, 8, 10, 13, 15, 17, 19},
            {1, 3, 6, 8, 10, 13, 15, 17, 19},
            {3, 5, 6, 8, 9, 10, 13, 15, 17, 19},
            {0, 1, 3, 6, 13, 15},
            {5, 6, 8, 9, 10, 12, 13, 15, 16, 17, 18, 19},
            {1, 2, 3, 8},
            {1, 8, 10, 12, 13, 14, 15, 16, 17, 18, 19},
            {0, 1, 2, 3, 4, 5, 6, 8, 9, 10, 18},
            {6, 12, 14, 15},
            {0, 1, 2, 3, 4, 6, 8, 9, 10, 11, 12, 15, 17, 18, 19},
            {6, 8, 12, 13, 14, 15, 17, 19},
            {1, 2, 3, 4, 8, 9, 19},
            {0, 1, 2, 12, 13, 14, 15, 17, 19},
            {1, 7, 8, 9, 10, 12, 13, 14 ,15, 17, 19},
            {1, 3, 5, 9, 14, 15, 16, 17, 19},
            {1, 3, 5, 6, 7, 8, 9, 11, 13, 14 , 15, 16, 17, 19},
            {1, 3, 5, 11, 13, 14, 15, 16, 17, 19},
            {3, 5, 10, 11, 14, 19},
            {0, 1, 2, 3, 5, 10, 14, 17, 19}
        };            
        for (int i = 0; i < chemin.length; i++)
            for (int j = 0; j < chemin[i].length; j++)
                w.registerTangibleAt(i, chemin[i][j], new Wall());
        for (int i = 0; i < r;i++) {
            w.registerTangibleAt(i, 20, new Wall());
            for (int j = 0; j < c; j++) {
                w.registerTangibleAt(20, j, new Wall());
            }
        }
        return w;
    }

    public static void put_random_object(World w, int n, int x, int y) {
        int i = 0;

        while (i < n) {
            int object = (int)(Math.random() * (7 - 1)) + 1;
            int a = (int)(Math.random() * (x - 1)) + 1;
            int b = (int)(Math.random() * (y - 1)) + 1;
            if (w.getWorld()[a][b].showAs() != '#') {
                switch(object) {
                case 1:
                    w.registerTangibleAt(a, b, new Acid(2));
                    i++;
                    break;
                case 2:
                    w.registerTangibleAt(a, b, new Hat(2, "Hat"));
                    i++;
                    break;
                case 3:
                    w.registerTangibleAt(a, b, new Dress(1, 2, "Dress"));
                    i++;
                    break;
                case 4:
                    w.registerTangibleAt(a, b, new HolyWater(2));
                    i++;
                    break;
                case 5:
                    w.registerTangibleAt(a, b, new Boots(2, 1, "Boots"));
                    i++;
                    break;
                case 6:
                    w.registerTangibleAt(a, b, new Sword(2, "Sword"));
                }
            }
        }
    }

    public void print_item(List<Item> items) {
        for (Item item : items)
            System.out.print(item.getName() + " ");
        System.out.println();
    }

    public void print_keyboard() {
        System.out.println();
        System.out.println("   q (attack)       ___      tab (show score) ");
        System.out.println("e (get item)    ___| w |___    g (drop item)");
        System.out.println("i (inventory)  |_a | s | d_|  p (display equipement)");
    }
    
    public void print_info(Player p) {
        System.out.print(String.format("\033[2J"));
        System.out.println("Information : ");
        System.out.println("D : (Dress), S : (Sword), B : (Boots)");
        System.out.println("A : (Acid), W : (HolyWater), H (Hat)");
        System.out.println("K : (Key), M : (Ennemy), E : (Exit)");
        System.out.println();
        System.out.print("x : " + p.getPosition().getX() + " y : "
                         + p.getPosition().getY() + "  ");
        System.out.println("Exit : (16,19)");
        System.out.println("Energy : " + p.getEnergy());
    }

    public void print_gear(Player p) {
        if (p.getHat() != null)
            System.out.print(p.getHat().getName() + " ");
        if (p.getDress() != null)
            System.out.print(p.getDress().getName() + " ");
        if (p.getBoots() != null)
            System.out.print(p.getBoots().getName() + " ");
        if (p.getSword() != null)
            System.out.print(p.getSword().getName() + " ");
        System.out.println();
    }

    public void start_game(Player p, World w) {
        boolean display_info = true;
        while ((!(p.getPosition().getY() == 16 && p.getPosition().getX() == 19))
               && (p.getHealthBar().getCurrentPoint() != 0)
               && (p.getEnergy() != 0)){            
            Scanner sc = new Scanner(System.in);
            String move = sc.nextLine();
            display_info = true;
            switch(move) {
            case "\t":
                display_info = false;
                print_info(p);
                p.show_scorebar();
                break; 
            case "a":
                p.movePlayer(w.getWorld(), Direction.NORTH);
                break;
            case "d":
                p.movePlayer(w.getWorld(), Direction.SOUTH);
                break;
            case "w":
                p.movePlayer(w.getWorld(), Direction.WEST);
                break;
            case "s":
                p.movePlayer(w.getWorld(), Direction.EAST);
                break;
            case "g":
                if (p.getSword() != null) {
                    w.registerTangibleAt(p.getPosition().getX(), p.getPosition().getY(), p.getSword());
                    System.out.println(p.getPosition().getX() + " OK");
                    p.getSword().remove(p);
                    p.setSword(null);
                }
                break;
            case "e":
                Cell cell = w.getWorld()[p.getPosition().getX()][p.getPosition().getY()];
                if (cell != null) {
                    Iterator<Tangible> iter = cell.getTangibles().iterator();
                    while (iter.hasNext()) {
                        Tangible t = iter.next();
                        if (t instanceof Sword) {
                            t.action(p);
                            iter.remove();
                        }
                    }
                }
                break;
            case "i":
                display_info = false;
                print_info(p);
                System.out.print("Inventory : ");
                print_item(p.getListItem());
                break;
            case "p":
                display_info = false;
                print_info(p);
                System.out.print("Equipment : ");
                print_gear(p);
                break;
            }
            if (p.getBoots() == null)
                p.reduceEnergy();
            if (display_info)
                print_info(p);
            w.print();
            print_keyboard();
        }
        if (p.getHealthBar().getCurrentPoint() == 0 || p.getEnergy() == 0)
            System.out.println("Game over ! You've lost !");
        else
            System.out.println("Congratulation ! You've finished the game. Your score " + p.getPoint());
    }
}
