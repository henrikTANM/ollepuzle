import java.util.*;

public class Main {
    private static List<Card> cards = new ArrayList<Card>();

    public static void main(String[] args) {
        cards.add(new Card(8, 5, 1, 2));
        cards.add(new Card(6, 7, 3, 2));
        cards.add(new Card(3, 5, 8, 2));
        cards.add(new Card(5, 2, 3, 7));
        cards.add(new Card(1, 6, 4, 7));
        cards.add(new Card(5, 1, 3, 8));
        cards.add(new Card(4, 5, 7, 8));
        cards.add(new Card(3, 6, 4, 1));
        cards.add(new Card(5, 1, 8, 4));

        List<Card> solution = findSolution(cards, new ArrayList<>(), new ArrayList<>());
    }

//    public static String visualize(List<Card> solution) {
//
//    }

    public static List<Card> findSolution(List<Card> input, List<Card> output, List<Integer> buffer) {
        for (int i = 0; i < 9; i++) {
            if (!buffer.contains(i)) {
                Card currentCard = input.get(i);
                for (int j = 0; j < 4; j++) {
                    Map<Card, Integer> comparables = new HashMap<Card, Integer>();
                    if (output.size() == 1 || output.size() == 2) {
                        comparables.put(output.get(output.size() - 1), 0);
                    }
                    if (output.size() == 3 || output.size() == 6) {
                        comparables.put(output.get(output.size() - 3), 1);
                    }
                    if (output.size() == 4 || output.size() == 5 || output.size() == 7 || output.size() == 8) {
                        comparables.put(output.get(output.size() - 1), 0);
                        comparables.put(output.get(output.size() - 3), 1);
                    }
                    if (compare(currentCard, comparables) || output.size() == 0) {
                        buffer.add(i);
                        output.add(currentCard);
                        if (output.size() == 9) {
                            return output;
                        }
                        System.out.println(buffer);
                        output = findSolution(input, output, buffer);
                        //System.out.println(buffer);
                        if (output.size() == 9) {
                            return output;
                        } else {
                            for (Integer k : buffer) {
                                if (k == i) {
                                    buffer.remove(k);
                                    break;
                                }
                            }
                            output.remove(currentCard);
                        }
                    }
                }
            }
        }
        return output;
    }

    public static boolean compare(Card card, Map<Card, Integer> comparables) {
        if (comparables.size() == 0) {
            return false;
        }

        List<List<Integer>> matchingPairs = new ArrayList<>(Arrays.asList(
        new ArrayList<>(Arrays.asList(1, 2)),
        new ArrayList<>(Arrays.asList(2, 1)),
        new ArrayList<>(Arrays.asList(3, 4)),
        new ArrayList<>(Arrays.asList(4, 3)),
        new ArrayList<>(Arrays.asList(5, 6)),
        new ArrayList<>(Arrays.asList(6, 5)),
        new ArrayList<>(Arrays.asList(7, 8)),
        new ArrayList<>(Arrays.asList(8, 7))));

        for (Card comparable : comparables.keySet()) {
            int picture1 = 0;
            int picture2 = 0;
            if (comparables.get(comparable) == 0) {
                picture1 = card.getLeftDrawing();
                picture2 = comparable.getRightDrawing();
            }
            if (comparables.get(comparable) == 1) {
                picture1 = card.getUpperDrawing();
                picture2 = comparable.getBottomDrawing();
            }
            if (!matchingPairs.contains(new ArrayList<>(Arrays.asList(picture1, picture2)))) {
                return false;
            }
        }
        return true;
    }
}