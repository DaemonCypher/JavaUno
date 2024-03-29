import java.util.Scanner;
import java.util.Stack;

public class Player {
    private Card[] hand;
    private int handSize;
    private final int MAX_HAND_SIZE = 15; 
    private String name;
    private String chosenColor; // Field to store the chosen color for wild cards

    // Constructor
    public Player(String name) {
        hand = new Card[MAX_HAND_SIZE];
        handSize = 0;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isValidMove(Card playedCard, Card currentCard) {
        return playedCard.getColor().equals(currentCard.getColor()) ||
               playedCard.getType().equals(currentCard.getType()) ||
               "WILD".equals(playedCard.getType()) ||
               "WILDPLUS4".equals(playedCard.getType());
    }

    // Adds a card to the player's hand
    public void addCardToHand(Card card) {
        if (handSize < MAX_HAND_SIZE) {
            hand[handSize] = card;
            handSize++;
        } else {
            System.out.println("Hand is full!");
            // Or handle this scenario as per your game's rules
        }
    }
    public String getChosenColor() {
        return chosenColor;
    }

    // Play a card from the hand
    public void playCard(int index, Card currentCard, Stack<Card> discardPile, Scanner scanner) {
        if (index >= 0 && index < handSize) {
            Card cardToPlay = hand[index];

            if (isValidMove(cardToPlay, currentCard)) {
                // Valid move: play the card
                discardPile.push(cardToPlay);
                if (cardToPlay.getType().equals("WILD") || cardToPlay.getType().equals("WILDPLUS4")) {
                    System.out.println("You played a wild card! Choose a new color (RED, YELLOW, BLUE, GREEN):");
                    chosenColor = scanner.nextLine().toUpperCase();
                }
                // Shift remaining cards left
                for (int i = index; i < handSize - 1; i++) {
                    hand[i] = hand[i + 1];
                }
                hand[handSize - 1] = null; // Remove the last card
                handSize--;

                System.out.println(name + " played: " + cardToPlay);
            } else {
                // Invalid move
                System.out.println("Invalid move! You cannot play " + cardToPlay);
            }
        } else {
            System.out.println("Invalid card index! Please choose a valid card from your hand.");
        }
    }

    // Display the player's hand
    public void showHand() {
        String output = String.format("Player's %s hand:", name);
        System.out.println(output);
        for (int i = 0; i < handSize; i++) {
            System.out.println(i + ": " + hand[i]);
        }
    }

    public int getHandSize() {
        return handSize;
    }

    public Card choices(Scanner scanner, Deck gameDeck, Card currentCard, Stack<Card> discardPile) {
        Card playedCard = null;
        boolean validMoveMade = false;
        while (!validMoveMade) {
            System.out.println(name + ", choose an action:");
            System.out.println("(D)raw card, (P)lay card");
            String choice = scanner.nextLine();

            switch (choice.toUpperCase()) {
                case "D":
                    if (!gameDeck.isEmpty()) {
                        addCardToHand(gameDeck.draw());
                        validMoveMade = true;
                    } else {
                        System.out.println("Deck is empty!");
                    }
                    break;
                case "P":
                    if (handSize > 0) {
                        System.out.println("Choose a card index to play:");
                        if (scanner.hasNextInt()) {
                            int index = scanner.nextInt();
                            scanner.nextLine(); // Consume newline
                            if (index >= 0 && index < handSize && isValidMove(hand[index], currentCard)) {
                                playedCard = hand[index];
                                playCard(index, currentCard, discardPile, scanner);
                                validMoveMade = true;
                            } else {
                                System.out.println("Invalid move! You cannot play " + hand[index]);
                            }
                        } else {
                            System.out.println("Invalid input! Please enter a number.");
                            scanner.nextLine(); // Consume invalid input
                        }
                    } else {
                        System.out.println("You have no cards to play!");
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
        }
        return playedCard;
    }
}
