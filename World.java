//
// ETNA PROJECT, 17/10/2018 by chen_l
// Word.java
// File description:
//      [...]
//

import java.util.*;

public class World {

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_CYAN = "\u001B[36m";
    
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_TEST_BACKGROUND = "\u001B[43m";
    
    private Cell[][] world;

    public World(int width, int height) {
        world = new Cell[width][height];
    }

    public World(Cell[][] world) {
        this.world = world;
    }

    public void registerCellAt(int row, int column, Cell cell) {
        this.world[row][column] = cell;
    }


    public void registerTangibleAt(int row, int column, Tangible tangible) {
        this.world[row][column].addTangible(tangible);
    }

    public void registerReactionAt(int row, int column, Reaction reaction) {
        this.world[row][column].addReaction(reaction);
    }

    public void print() {
        String s;
        for (int a = 0; a < 10; a++)
            System.out.print(" ");
        for (int b = 0; b < world.length + 1; b++)
            System.out.print(ANSI_BLUE_BACKGROUND + " " + ANSI_RESET);
        System.out.println();
        for (int i = 0; i < world.length; i++) {
            for (int k = 0; k < 10; k++) {
                System.out.print(" ");
            }
            System.out.print(ANSI_BLUE_BACKGROUND + " " + ANSI_RESET);
            for(int j = 0; j < world[i].length; j++) {
                s = "" + world[i][j].showAs();
                switch(world[i][j].showAs()) {
                case '#':
                    System.out.print(ANSI_BLUE + ANSI_BLUE_BACKGROUND + s + ANSI_RESET);
                    break;
                case '.':
                    System.out.print(ANSI_YELLOW + s + ANSI_RESET);
                    break;
                case 'J':
                    System.out.print(ANSI_RED + s + ANSI_RESET);
                    break;
                case 'M':
                    System.out.print(ANSI_RED + s + ANSI_RESET);
                    break;
                case 'W':
                    System.out.print(ANSI_CYAN + s + ANSI_RESET);
                    break;
                case 'K':
                    System.out.print(ANSI_GREEN + s + ANSI_RESET);
                    break;
                case 'E':
                    System.out.print(ANSI_TEST_BACKGROUND + ANSI_RED + s + ANSI_RESET);
                    break;
                default:
                    System.out.print(s);
                    break;
                }
            }
            System.out.println();
        }
    }

    public Cell[][] getWorld() {
        return world;
    }
}
