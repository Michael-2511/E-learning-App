import java.util.List;
public class Question {
    private String statement;
    private List<String> answerChoices;
    private int correctAnswer; // sau private String

    public Question(String statement, List<String> answerChoices, int correctAnswer) {
        this.statement = statement;
        this.answerChoices = answerChoices;
        this.correctAnswer = correctAnswer;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public List<String> getAnswerChoices() {
        return answerChoices;
    }

    public void setAnswerChoices(List<String> answerChoices) {
        this.answerChoices = answerChoices;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
