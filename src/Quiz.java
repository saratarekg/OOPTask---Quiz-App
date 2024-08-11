import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Quiz {
    private List<Question> questions;

    public Quiz() {
        questions = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }


    public List<Question> getRandomQuestions(int numQuestions) {
        Collections.shuffle(questions);
        return questions.subList(0, Math.min(numQuestions, questions.size()));
        // return max number of possible questions, if number of questions less than parameter then this will be our min.
    }
}
