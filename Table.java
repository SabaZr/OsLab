import java.util.Random;

public class Table {
    private String[] items = new String[2];

    public synchronized void placeRandomItems() {
        Random random = new Random();
        items[0] = getRandomIngredient();
        do {
            items[1] = getRandomIngredient();
        } while (items[0].equals(items[1])); // Ensure items are different

        System.out.println("Agent placed " + items[0] + " and " + items[1] + " on the table.");
    }


    public synchronized boolean hasNeededIngredients(String smokerIngredient) {
        int countDifferent = 0;
        for (String item : items) {
            if (item != null && !item.equals(smokerIngredient)) {
                countDifferent++;
            }
        }
        return countDifferent == 2;
    }

    public synchronized void resetTable() {
        items[0] = null;
        items[1] = null;
    }

    private String getRandomIngredient() {
        String[] ingredients = {"tobacco", "paper", "matches"};
        Random random = new Random();
        return ingredients[random.nextInt(3)];
    }
}