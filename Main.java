import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    private Stack<Player> players;
    private Deck gameDeck;
    private Card currentCard; // Top card of the discard pile
    private Scanner scanner;
    private Stack<Card> discardPile;

    public Main() {
        players = new Stack<>();
        gameDeck = new Deck();
        scanner = new Scanner(System.in);
    }

    public void initializeGame() {
        System.out.println("How many players are there:");
        int playerCount = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over

        for (int i = 0; i < playerCount; i++) {
            System.out.println("Enter player " + (i + 1) + "'s name:");
            String name = scanner.nextLine();
            players.push(new Player(name));
        }

        dealCards();
        startGame();
    }

    private void dealCards() {
        // Assuming each player gets 7 cards to start
        for (int i = 0; i < 7; i++) {
            for (Player player : players) {
                if (!gameDeck.isEmpty()) {
                    player.addCardToHand(gameDeck.draw());
                }
            }
        }
    }

    public void multidraw(int x, Player player) {
        for (int i = 0; i < x; i++) {
            player.addCardToHand(gameDeck.draw());
        }
    }

    private void startGame() {
        discardPile = new Stack<>();
        currentCard = gameDeck.draw();
        discardPile.push(currentCard);
    
        boolean gameEnded = false;
        boolean isReverse = false;
        int currentPlayerIndex = 0;
    
        while (!gameEnded) {
            Player currentPlayer = players.get(currentPlayerIndex);
            System.out.println("Current card: " + currentCard);
    
            currentPlayer.showHand();
            Card playedCard = currentPlayer.choices(scanner, gameDeck, currentCard, discardPile);
    
            if (playedCard != null) {
                currentCard = playedCard;
                discardPile.push(currentCard);
    
                // Update the current card for a wild card with the chosen color
                if (currentCard.getType().equals("WILD") || currentCard.getType().equals("WILDPLUS4")) {
                    String chosenColor = currentPlayer.getChosenColor(); // Method to get chosen color from player
                    currentCard = new Card(chosenColor, currentCard.getType()); // Set the new color for the current card
                }
    
                // Handle other action cards
                handleActionCard(currentCard, currentPlayer, isReverse);
            }
    
            currentPlayerIndex = getNextPlayerIndex(currentPlayerIndex, isReverse, players.size());
    
            if (currentPlayer.getHandSize() == 0) {
                System.out.println(currentPlayer.getName() + " has won!");
                gameEnded = true;
            }
        }
    }
    
    private void handleActionCard(Card card, Player currentPlayer, boolean isReverse) {
        if (card.getType().equals("PLUS2")) {
            multidraw(2, currentPlayer);
        } else if (card.getType().equals("WILDPLUS4")) {
            multidraw(4, currentPlayer);
        } else if (card.getType().equals("REVERSE")) {
            isReverse = !isReverse; // Toggle the direction of play
            System.out.println("Play direction reversed!");
        } else if (card.getType().equals("SKIP")) {
            System.out.println(currentPlayer.getName() + " is skipped!");
        }
    }

    private int getNextPlayerIndex(int currentIndex, boolean isReverse, int playerCount) {
        if (isReverse) {
            return (currentIndex - 1 + playerCount) % playerCount;
        } else {
            return (currentIndex + 1) % playerCount;
        }
    }

    public static void main(String[] args) {
        Main game = new Main();
        game.initializeGame();
    }
}
