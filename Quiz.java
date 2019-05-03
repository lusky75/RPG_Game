//
// ETNA PROJECT, 24/10/2018 by chen_l
// Quiz.java
// File description:
//      [...]
//

import java.util.*;

public class Quiz extends Game {

    private List<Question> listQuestion;
    
    public Quiz(String name, Key keyCondition, Key keyReward, List<Question> listQuestion) {
        super(name, keyCondition, keyReward);
        this.listQuestion = listQuestion;
    }

    @Override
    public void hasLost() {
        this.setLose(true);
    }
    
    public void print_question(Question q) {
        System.out.println(q.getQuestion());
        System.out.println(q.getReponse1());
        System.out.println(q.getReponse2());
        System.out.println(q.getReponse3());
        System.out.println(q.getReponse4());
    }
    
    public boolean find_answer(String input, Question q) {
        String answer = "";
        int i = 0;

        Scanner sc = new Scanner(input);
        answer = sc.next();
        if (!answer.equals(q.getAnswer())) {
            System.out.println("Wrong");
            hasLost();
            return false;
        }   
        System.out.println("Correct");
        return true;
    }
    
    @Override
    public boolean play(Key k, String input, Question q) {
        if (!canPlay(k)) {
            System.out.println("Key required");
            return false;
        }
        if (input == null)
            return false;
        return find_answer(input, q);
    }

    @Override
    public boolean play(Key k, String input) {
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
