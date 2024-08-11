public class User {
    private String name;
    private int score;

    public User(String name) {
        this.name = name;
        this.score = 0;
    }

    public void recordAnswer(boolean isCorrect) {
        if (isCorrect) {
            score++;
        }
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }
}
