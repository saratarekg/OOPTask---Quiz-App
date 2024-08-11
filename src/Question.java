public class Question {
    private String question;
    private String[] possibleAnswers;
    private String correctAnswer;

    public Question(String question, String[] possibleAnswers, String correctAnswer) {
        this.question = question;
        this.possibleAnswers = possibleAnswers;
        this.correctAnswer = correctAnswer;
    }

    public String getCorrectOption() {
        return correctAnswer;
    }

    public boolean checkAnswer(String userAnswer) {
        return userAnswer.equalsIgnoreCase(correctAnswer);
    }

    public void displayQuestion() {
        System.out.println(question);
        for (int i = 0; i < possibleAnswers.length; i++) {
            System.out.println(("a" + (i+1)) + ". " + possibleAnswers[i]);
        }
    }


}

