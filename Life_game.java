import java.util.*;

public class Life_game extends Game{

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    private String name;
    private Key keyCondition;
    private Key keyReward;
    
    public Life_game(String name, Key keyCondition, Key keyReward) {
        super(name, keyCondition, keyReward);
    }

    @Override
    public boolean play(Key k, String input) {
        if (!canPlay(k)) {
            System.out.println("Key required");
            return false;
        }
        if (input == null)
            return false;
        return launch_life_game();
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

    @Override
    public void hasLost() {
        this.setLose(true);
    }
    
    
    public String getName() {
        return name;
    }

    public Key getKeyCondition() {
        return keyCondition;
    }

    public Key getKeyReward() {
        return keyReward;
    }
    
    public int neighborCount(int row, int col, int [][] society) {
        int count = 0;
        for(int i = row - 1; i <= row + 1; i++) {
            if (i >= 0 && i < society.length)
                for(int j = col - 1; j <= col + 1; j++)
                    if (j >= 0 && j < society[i].length)
                        if (i != row || j != col)
                            if (society[i][j] == 1)
                                count++;
        }
        return count;
    }
    
    public int [][] generate_life(int [][]grille) {
        int [][] new_grille = new int[grille.length][grille[0].length];
        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille[i].length; j++)
                new_grille[i][j] = grille[i][j];
        }
        for (int i = 0; i < grille.length; i++)
            for (int j = 0; j < grille[i].length; j++)
                if (i >= 0 && i < grille.length - 1 && j >= 0 && j < grille[i].length - 1) {
                    if (((neighborCount(i, j, grille) == 2) || (neighborCount(i, j, grille) == 3))
                        && (grille[i][j] == 1))
                        new_grille[i][j] = 1;
                    else if (neighborCount(i, j, grille) == 3 && grille[i][j] == 0)
                        new_grille[i][j] = 1;
                    else
                        new_grille[i][j] = 0;
                }
        return new_grille;
    }

    public void print_array(int [][] array) {
        for (int i = 0; i < array.length; i++) {
            for(int j = 0; j < array[i].length; j++) {
                if (array[i][j] == 1)
                    System.out.print(ANSI_RED + Integer.toString(array[i][j]) + " " + ANSI_RESET);
                else
                    System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean isEmpty(int [][] array) {
        for (int i = 0; i < array.length; i++)
            for (int j = 0; j < array[i].length; j++)
                if (array[i][j] != 0)
                    return false;
        return true;
    }

    public int [][] fill_random_array(int n) {
        int a = 0;
        int b = 0;
        int [][] new_grille = new int [10][10];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10 ;j++) {
                new_grille[i][j] = 0;
            }
        }
        for (int l = 0; l < n; l++) {
            a = (int)(Math.random() * (9 - 1)) + 1;
            b = (int)(Math.random() * (9 - 1)) + 1;
            new_grille[a][b] = 1;
        }
        return new_grille;
    }

    public boolean compare(int [][] a, int [][] b) {
        for (int i = 0; i < a.length; i++) {
            for(int j = 0; j < a[i].length; j++) {
                if (a[i][j] != b[i][j])
                    return false;
            }
        }
        return true;
    }

    public boolean loop(int count, int is_perfect, int [][] new_grille) {
        long lastSec = 0;
        print_array(new_grille);
        while(count < 100 && is_perfect < 2){
            long sec = System.currentTimeMillis() / 1000;
            if (sec != lastSec) {
                if (isEmpty(new_grille)) {
                    System.out.println("You've lost!");
                    return false;
                }
                if (compare(new_grille, generate_life(new_grille)))
                    is_perfect++;
                System.out.println("----------------");
                System.out.println(count++);
                lastSec = sec;
                new_grille = generate_life(new_grille);
                System.out.println("----------------");
                print_array(new_grille);
            }
        }
        System.out.println("You've won with " + count + " rounds.");
        return true;
    }
    
    public boolean launch_life_game() {
        int nb_cells = 0;
        boolean isRight = false;
        int is_perfect = 0;
        System.out.println("Hello ! You'll play the game of the life.");
        while (!isRight) {
            System.out.println("Choose a random number between 10 and 100 cells");
            Scanner sc = new Scanner(System.in);
            String s = sc.next();
            nb_cells = Integer.parseInt(s);
            if (nb_cells >= 10 && nb_cells <= 100)
                isRight = true;
        }
        int [][] new_grille = fill_random_array(nb_cells);
        int count = 1;
        return loop(count, is_perfect, new_grille);
    }
}
