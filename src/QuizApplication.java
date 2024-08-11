import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class QuizApplication {
    private Quiz quiz;
    private User user;

    public QuizApplication() {
        quiz = new Quiz();
    }

    public String registerUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        user = new User(name);
        System.out.print("Choose difficulty (easy, medium, hard): ");
        return scanner.nextLine();
    }

    public void createQuiz(String difficulty) {
        if(difficulty.equalsIgnoreCase("easy")) {
            quiz.addQuestion(new Question("What is the capital of France?",
                    new String[]{"Paris", "Rome", "Berlin", "Madrid"}, "Paris"));
            quiz.addQuestion(new Question("What is 2 + 2?",
                    new String[]{"3", "4", "5", "6"}, "4"));
        }
        else if(difficulty.equalsIgnoreCase("medium")) {
            quiz.addQuestion(new Question("What is the largest ocean on Earth?",
                    new String[]{"Atlantic", "Indian", "Arctic", "Pacific"}, "Pacific"));
        }
        else{
            quiz.addQuestion(new Question("Which planet is known as the Red Planet?",
                    new String[]{"Earth", "Mars", "Jupiter", "Saturn"}, "Mars"));
        }


    }


    public void startQuiz() {
        System.out.println("\nWelcome, " + user.getName() + "! You have 10 seconds to" +
                " answer each question. Make the most of your time. Good luck!");

        List<Question> questions = quiz.getRandomQuestions(3);
        Scanner scanner = new Scanner(System.in);
        for (Question question : questions) {
            question.displayQuestion();

            final boolean[] timedOut = {false};  // To track if the timer runs out
            //the reference to the array stays the same, which is allowed
            // in anonymous class TimerTask, but can change the value inside

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {

                @Override
                public void run() {
                    timedOut[0] = true;
                    System.out.println("\nTime's up!");
                }
            }, 10000);  // 10-second timer for each question

            System.out.print("Your answer: ");
            String answer = scanner.nextLine().trim(); //wait for user to enter text in command line and press enter
            timer.cancel();  // Stop the timer when the user answers

            boolean isCorrect = question.checkAnswer(answer);
            if (timedOut[0] || !isCorrect) {
                System.out.println("Wrong! The correct answer was " + question.getCorrectOption() + ".");

            } else {
                System.out.println("Correct!");
            }
            user.recordAnswer(!timedOut[0] && isCorrect);  // If timed out, it's incorrect
            System.out.println();
        }
        showScore();
    }

    public void showScore() {
        System.out.println("\nQuiz over! " + user.getName() + ", your final score is: " + user.getScore() + "/" + quiz.getRandomQuestions(3).size());
    }

    public void run() {
        String difficulty = registerUser();
        createQuiz(difficulty);
        startQuiz();
    }
}
