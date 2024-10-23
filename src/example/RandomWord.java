package example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class RandomWord {

    private final Map<String, List<String>> categories = new HashMap<>();
    private String word;

    public RandomWord() {
        loadCategories();
    }

    private void loadCategories() {
        try {

            String jsonValue = Files.readString(Paths.get("resource/data.json")).trim();
            final String jsonString = jsonValue.substring(1, jsonValue.length() - 1).trim();
            final String[] keyValuePair = jsonString.split("],");
            for (String pair : keyValuePair) {
                String[] keyValue = pair.split(":\\s*\\[");
                String key = keyValue[0].trim().replace("\"", "");
                String valueArray = keyValue[1].trim().replaceAll("[\\[\\]\"]", "");
                List<String> values = Arrays.asList(valueArray.split("\\s*,\\s*"));
                categories.put(key, values);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getRandomWord() {
        Scanner scan = new Scanner(System.in);
        Random random = new Random();
        while (true) {
            System.out.println("Доступные категории: " + categories.keySet());
            System.out.print("Выберите категорию: ");
            String chosenCategory = scan.nextLine();

            if (categories.containsKey(chosenCategory)) {
                List<String> words = categories.get(chosenCategory);

                if (words != null && !words.isEmpty()) {
                    word = words.get(random.nextInt(words.size()));
                    return word;
                } else {
                    System.out.println("В выбранной категории нет слов.");
                }
            }
        }
    }

}
