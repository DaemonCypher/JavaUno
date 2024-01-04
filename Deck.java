import java.util.*;

public class Deck {
    private Stack<Card> deck;

    enum Color { RED, YELLOW, BLUE, GREEN }
    enum Type { ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, SKIP, REVERSE, PLUS2, WILD, WILDPLUS4 }

    public Deck() {
        deck = new Stack<>();
        initializeDeck();
    }

    private void initializeDeck() {
        for(Color color : Color.values()) {
            for(Type type : Type.values()) {
                // Add one card of each type for each color
                deck.push(new Card(color.name(), type.name()));

                // If not a wild card, add a second card of the same type for each color
                /*if (!type.equals(Type.WILD) && !type.equals(Type.WILDPLUS4)) {
                    deck.push(new Card(color.name(), type.name()));
                }
                */
            }
        }

        // Shuffle the deck
        Collections.shuffle(deck);
    }

    public boolean isEmpty() {
        return deck.isEmpty();
    }

    public Card draw() {
        if (!deck.isEmpty()) {
            return deck.pop();
        }
        return null; // or throw an exception if the deck is empty
    }

}
