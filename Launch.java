import java.util.*;

public class Launch {

    public List<Question> createQuestion() {
        int random = (int)(Math.random() * (3 - 1)) + 1;

        List<Question> listQuestion = new ArrayList<Question>();
        switch(random) {
        case 1:
            listQuestion.add(new Question("13 * 12 = ?", "158", "166", "154", "156", "156"));
            listQuestion.add(new Question("11 * 15 = ?", "165", "175", "145", "185", "165"));
            listQuestion.add(new Question("15 * 12 = ?", "180", "170", "190", "160", "180"));
            listQuestion.add(new Question("7 * 9 = ?", "65", "72", "63", "54", "63"));
            break;
        case 2:
            listQuestion.add(new Question("Find x : 7 x − 8 = 4 x + 7", "3", "11", "5", "4", "5"));
            listQuestion.add(new Question("Find pgcd of 52 and 140", "3", "2", "4", "14", "4"));
            listQuestion.add(new Question("Find the error : 16 25 1 34 9 4", "4", "34", "1", "25", "34"));
            
            break;
        }
        return listQuestion;
    }
    
    public Key start_quiz(Player p) {
        boolean win = false;
        Key k1 = new Key("key1");
        Key k2 = new Key("key2");
        List<Question> listQuestions = createQuestion();
        Game g1 = new Quiz("Question", k1, k2, listQuestions);

        Scanner sc = new Scanner(System.in);
        
        while (!g1.getLose() && !win) {
            System.out.println("Welcome to the quiz");
            if (listQuestions != null) {
                Iterator<Question> iter = listQuestions.iterator();
                while (iter.hasNext()) {
                    Question q = iter.next();
                    Quiz quiz = (Quiz)g1;
                    quiz.print_question(q);
                    System.out.println("Input your answer : ");
                    String answer = sc.next();
                    win = g1.play(k1, answer, q);
                    if (!win)
                        return null;
                }
            }
        }
        p.setPoint(p.getPoint() + 1);
        System.out.println("Nice ! You've win the Quiz");
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            System.out.println("Thread Interrupted.");
        }
        return k2;
    }

    public Key start_PlusMinusGame(Key k2, Player p) {
        int attempt = -1;
        boolean win = false;
        Key k3 = new Key("key3");
        
        int random = (int)(Math.random() * (100 - 1)) + 1;
        Game g2 = new PlusMinusGame("PlusMinusGame", k2, k3, random);
        System.out.println("Welcome to the " + g2.getName() + " game!");
        System.out.println("Find the number between 1 and 100 :");
        Scanner sc = new Scanner(System.in);
        while (!g2.getLose() && !win && ++attempt < 10) {
            String answer = sc.next();
            win = g2.play(k2, answer);
        }
        if (!win) {
            System.out.println("Lose");
            return null;
        }
        p.setPoint(p.getPoint() + 1);
        System.out.println("Congrats you've find the number");
        return k3;
    }

    public Key start_life_game(Key key3, Player p) {
        Game game = new Life_game("Life Game", key3, null);
        if (game.play(key3, "ok"))
            p.setPoint(p.getPoint() + 1);
        return null;
    }
}
