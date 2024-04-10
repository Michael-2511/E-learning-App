import java.util.List;
public class Question {
    private String statement;
    private String correctAnswer; // sau private String

    public Question(String statement, String correctAnswer) {
        this.statement = statement;
        this.correctAnswer = correctAnswer;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
