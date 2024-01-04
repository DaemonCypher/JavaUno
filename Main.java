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

        if (!gameDeck.isEmpty()) {
            currentCard = gameDeck.draw();
            System.out.println("Initial card on discard pile: " + currentCard);
        }
    
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

    private void startGame() {
        discardPile = new Stack<>();
        currentCard = gameDeck.draw();
        discardPile.push(currentCard); // Initialize the discard pile with the first card
        boolean gameEnded = false;
        while (!gameEnded) {
            
            for (Player player : players) 
            {
                
                System.out.println("Current card: " + currentCard);
                player.showHand();
                player.choices(scanner, gameDeck, currentCard, discardPile);
    
                // Update the current card only if a new card is played
                if (!discardPile.isEmpty() && discardPile.peek() != currentCard) {
                    currentCard = discardPile.peek();
                }
    
                // Implement game end logic here
                // For example, check if any player has an empty hand
        }
    }
}

    public static void main(String[] args) {
        Main game = new Main();
        game.initializeGame();
    }
}
