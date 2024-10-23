package example;

import java.util.Scanner;

public class Game {
    manDrawer drawer = new manDrawer(5);
    RandomWord randomWord = new RandomWord();
    WordMaskOperator wordMaskOperator = new WordMaskOperator();

    public void start() {
        Scanner scanner = new Scanner(System.in);
        String input;
        int amount_fail;

        while (true) {
            System.out.println("Меню: [Н]овая игра / [В]ыход");
            input = scanner.nextLine();

            if (input.equalsIgnoreCase("Н")) {
                amount_fail = 0;
                wordMaskOperator.clearBuffer();
                String guessedWord = randomWord.getRandomWord();
                wordMaskOperator.setWord(guessedWord);
                System.out.println("Случайное слово загадано!");
                wordMaskOperator.maskOutput();

                while (!wordMaskOperator.userWin()) {
                    System.out.println("\nВведите букву:");
                    String letter = scanner.nextLine();
                    if (wordMaskOperator.isLetterAlreadyUsed(letter)) {
                        System.out.printf("Вы уже ввели эту букву: %s%n", letter);
                    } else {
                        wordMaskOperator.useInputLetters(letter);

                        if (wordMaskOperator.containsLetter(letter)) {
                            System.out.println("Вы угадали букву!");
                            if (wordMaskOperator.updateMask(letter)) {
                                wordMaskOperator.maskOutput();
                            }
                        } else {
                            System.out.println("Вы не угадали букву.");
                            amount_fail++;
                            System.out.printf("Текущее кол-во ошибок: %s/5%n", amount_fail);
                            drawer.draw(amount_fail);
                        }
                    }

                    if (amount_fail == 5) {
                        System.out.println("Вы проиграли.");
                        System.out.printf("Слово, которое нужно было угадать: %s\n", guessedWord);
                        break;
                    }

                    if (wordMaskOperator.userWin()) {
                        System.out.println("Вы выиграли!");
                    }
                }

            } else if (input.equalsIgnoreCase("В")) {
                System.out.println("Выход из игры.");
                System.exit(0);
            } else {
                System.out.println("Некорректный ввод! Попробуйте снова.");
            }
        }
    }
}
