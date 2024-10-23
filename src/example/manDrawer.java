package example;

public class manDrawer {
    Humans human = new Humans();
    int attemptsLeft;

    public manDrawer(int maxAttempts) {
        this.attemptsLeft = maxAttempts;
    }

    public void draw(int incorrectGuesses) {
        human.updateHangmanDrawingMatrix(incorrectGuesses);
        human.printHangman();
    }

}