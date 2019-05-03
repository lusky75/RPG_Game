//
// ETNA PROJECT, 24/10/2018 by chen_l
// Question.java
// File description:
//      [...]
//

public class Question {

    private String question;
    private String answer;
    private String reponse1;
    private String reponse2;
    private String reponse3;
    private String reponse4;


    public Question(String question, String reponse1, String reponse2, String reponse3, String reponse4, String answer)
    {
        this.question = question;
        this.answer = answer;
        this.reponse1 = reponse1;
        this.reponse2 = reponse2;
        this.reponse3 = reponse3;
        this.reponse4 = reponse4;
    }

    public String getReponse1() {
        return reponse1;
    }

    public String getReponse2() {
        return reponse2;
    }

    public String getReponse3() {
        return reponse3;
    }

    public String getReponse4() {
        return reponse4;
    }
    
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
