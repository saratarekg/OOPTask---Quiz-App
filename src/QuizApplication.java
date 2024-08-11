import java.util.List;
import java.util.Scanner;

public class QuizApplication {
    private Quiz quiz;
    private User user;

    public QuizApplication() {
        quiz = new Quiz();
    }

    public void registerUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        user = new User(name);
    }

    public void createQuiz() {
        quiz.addQuestion(new Question("What is the capital of France?",
                new String[]{"Paris", "Rome", "Berlin", "Madrid"}, "Paris"));
        quiz.addQuestion(new Question("What is 2 + 2?",
                new String[]{"3", "4", "5", "6"}, "4"));
        quiz.addQuestion(new Question("Which planet is known as the Red Planet?",
                new String[]{"Earth", "Mars", "Jupiter", "Saturn"}, "Mars"));

        quiz.addQuestion(new Question("What is the largest ocean on Earth?",
                new String[]{"Atlantic", "Indian", "Arctic", "Pacific"}, "Pacific"));
    }

//    public void startQuiz() {
//        System.out.println("\nWelcome, " + user.getName() + "! Let's start the quiz.");
//        List<Question> questions = quiz.getRandomQuestions(3);
//        Scanner scanner = new Scanner(System.in);
//        for (Question question : questions) {
//            question.displayQuestion();
//            System.out.print("Your answer: ");
//            String answer = scanner.nextLine().trim();
//            if (question.checkAnswer(answer)) {
//                System.out.println("Correct!");
//                user.recordAnswer(true);
//            } else {
//                System.out.println("Wrong! The correct answer was " + question.checkAnswer(answer));
//                user.recordAnswer(false);
//            }
//            System.out.println();
//        }
//        showScore();
//    }

    public void startQuiz() {
        System.out.println("\nWelcome, " + user.getName() + "! Let's start the quiz.");
        List<Question> questions = quiz.getRandomQuestions(3);
        Scanner scanner = new Scanner(System.in);
        for (Question question : questions) {
            question.displayQuestion();
            System.out.print("Your answer: ");
            String answer = scanner.nextLine().trim();
            boolean isCorrect = question.checkAnswer(answer);
            if (isCorrect) {
                System.out.println("Correct!");
            } else {
                System.out.println("Wrong! The correct answer was " + question.getCorrectOption() + ".");
            }
            user.recordAnswer(isCorrect);
            System.out.println();
        }
        showScore();
    }

    public void showScore() {
        System.out.println("\nQuiz over! " + user.getName() + ", your final score is: " + user.getScore() + "/" + quiz.getRandomQuestions(3).size());
    }

    public void run() {
        registerUser();
        createQuiz();
        startQuiz();
    }
}
