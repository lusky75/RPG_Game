//
// ETNA PROJECT, 18/10/2018 by chen_l
// Player.java
// File description:
//      [...]
//

import java.util.*;

public class Player implements Tangible {

    private String name;
    private Position position;
    private HealthBar healthBar;
    private Stats stats;
    private List<Item> listItem;
    private int energy;
    private Hat hat;
    private Dress dress;
    private Boots boots;
    private Sword sword;
    private char symbol;
    private int point;

    public Player(char symbol, Position position, Stats stats, HealthBar healthBar, List<Item> listItem) {
        this.symbol = symbol;
        this.position = position;
        this.stats = stats;
        this.healthBar = healthBar;
        this.listItem = listItem;
        this.point = 0;
        this.energy = 50;
    }
    
    public int getEnergy() {
        return energy;
    }

    public void reduceEnergy() {
        if (this.energy - 1 < 0)
            this.energy = 0;
        else
            this.energy -= 1;
    }

    public void removeEnergy(int energy) {
        if (this.energy - energy < 0)
            this.energy = 0;
        else
            this.energy -= energy;
    }

    public void addEnergy(int energy) {
        if (this.energy + energy > 100)
            this.energy = 100;
        else
            this.energy += energy;
    }
    
    public Hat getHat() {
        return hat;
    }

    public Dress getDress() {
        return dress;
    }

    public Boots getBoots() {
        return boots;
    }
    
    public void show_scorebar() {
        System.out.println("PV : " + this.healthBar.getCurrentPoint());
        System.out.println("Score : " + this.point + " (A : " +
                           this.stats.getAttack() + " D : " +
                           this.stats.getDefence() + ")");
    }
    
    public Sword getSword() {
        return sword;
    }

    public void setSword(Sword sword) {
        this.sword = sword;
    }
    
    public void print(boolean movable) {
        if (movable)
            System.out.println("Player moved!");
        else
            System.out.println("Player didn't move!");
    }

    public String message(Player p) {
        
        System.out.println("Opponent | PV : " + p.getHealthBar().getCurrentPoint()
                           + "\nA : " + p.getStats().getAttack()
                           + " D : " + p.getStats().getDefence());
        System.out.println("Press (q) to attack, otherwise another key");
        Scanner sc = new Scanner(System.in);
        String message = sc.next();
        return message;
    }

    public boolean move_into_action(Cell[][] world, int x, int y) {
        boolean move = false;
        Cell cell = world[x][y];
        if (cell != null) {
            if (!cell.isEmptyTangible()) {
                Iterator<Tangible> iter = cell.getTangibles().iterator();
                while (iter.hasNext()) {
                    Tangible t = iter.next();
                    if (t instanceof Hat || t instanceof Dress || t instanceof Key ||
                        t instanceof Boots || t instanceof Chest || t instanceof Exit ||
                        t instanceof Acid || t instanceof HolyWater || t instanceof Sword) {       
                        t.action(this);
                        iter.remove();
                        move = true;
                    }
                    else if (t instanceof Player) {
                        Player pl = (Player)t;
                        String s = message(pl);
                        if (s.equals("q")) {
                            pl.action(this);
                            this.action(pl);
                        } else
                            return false;
                        if (pl.getHealthBar().getCurrentPoint() == 0) {
                            this.point += 1;
                            iter.remove();
                            addEnergy(15);
                        }
                        else
                            return false;
                    }
                }
            }
        }
        return true;
    }

    public void movePosition_X(Cell[][] world, int x, int y, int new_x) {
        if (move_into_action(world, new_x, y)) {
            position.setX(new_x);
            world[new_x][y].addTangible(this);
            world[x][y].getTangibles().remove(this);
        }
    }

    public void movePosition_Y(Cell[][] world, int x, int y, int new_y) {
        if (move_into_action(world, x, new_y)) {
            position.setY(new_y);
            world[x][new_y].addTangible(this);
            world[x][y].getTangibles().remove(this);
        }
    }


    public void movePlayer(Cell[][] world, Direction direction) {
        int x = position.getX();
        int y = position.getY();
        boolean movable = true;

        if (direction == Direction.NORTH && y - 1 >= 0
            && world[x][y - 1].showAs() != '#')
            movePosition_Y(world, x, y, y - 1);
        else if (direction == Direction.SOUTH && y + 1 <= world.length - 1
                 && world[x][y + 1].showAs() != '#')
            movePosition_Y(world, x, y, y + 1);
        else if (direction == Direction.WEST && x - 1 >= 0
                 && world[x - 1][y].showAs() != '#')
            movePosition_X(world, x, y, x - 1);
        else if (direction == Direction.EAST && x + 1 <= world.length - 1
                 && world[x + 1][y].showAs() != '#')
            movePosition_X(world, x, y, x + 1);
        else
            movable = false;
        print(movable);
    }
    
    public char showAs() {
        return symbol;
    }
    
    public void action (Player player) {
        int attack = this.stats.getAttack();
        int defence_player = player.getStats().getDefence();
        if (attack - defence_player < 0)
            player.getHealthBar().removeCurrentPoint(0);
        else
            player.getHealthBar().removeCurrentPoint(attack - defence_player);
        this.energy -= 10;
    }

    public void putSword(Sword sword) {
        removeSword();
        this.sword = sword;
        this.sword.put(this);
        reduceEnergy();
    }
    
    public void removeSword() {
        if (this.sword != null) {
            this.sword.remove(this);
            this.listItem.add(this.sword);
            this.sword = null;
        }
    }

    public void putHat(Hat hat) {
        removeHat();
        this.hat = hat;
        this.hat.put(this);
        reduceEnergy();
    }

    public void removeHat() {
        if (this.hat != null) {
            this.hat.remove(this);
            this.listItem.add(this.hat);
            this.hat = null;
        }
    }

    public void putDress(Dress dress) {
        removeDress();
        this.dress = dress;
        this.dress.put(this);
        reduceEnergy();
    }

    public void removeDress() {
        if (this.dress != null) {
            this.dress.remove(this);
            this.listItem.add(this.dress);
            this.dress = null;
        }
    }

    public void putBoots(Boots boots) {
        removeBoots();
        this.boots = boots;
        this.boots.put(this);
    }

    public void removeBoots() {
        if (this.boots != null) {
            this.boots.remove(this);
            this.listItem.add(this.boots);
            this.boots = null;
        }
    }

    public void printBag() {
        if (listItem.isEmpty())
            System.out.println("Bag Empty");
        else 
            for (int i = 0; i < listItem.size(); i++)
                System.out.println((i + 1) + " -> " + listItem.get(i).getName());
    }
    
    public void attack(Player player){
        if (this.stats.getAttack() > player.getStats().getDefence()){
            int i = stats.getAttack() - player.getStats().getDefence();
            if (i < 0){
                i = 0;
            }
            player.getHealthBar().removeCurrentPoint(i);
        }
        this.energy -= 5;
    }

    public HealthBar getHealthBar() {
        return healthBar;
    }

    public void setHealthBar(HealthBar healthBar) {
        this.healthBar = healthBar;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }

    public Stats getStats() {
        return stats;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public List<Item> getListItem() {
        return listItem;
    }

}
