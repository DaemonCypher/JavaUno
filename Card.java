public class Card {
    // Instance variables for color and type
    private String color;
    private String type;

    // Constructor to initialize the card
    public Card(String color, String type) {
        this.color = color;
        this.type = type;
    }

    // Getter for color
    public String getColor() {
        return color;
    }

    // Getter for type
    public String getType() {
        return type;
    }
    @Override
    public String toString() {
        return "Card{" + "color='" + color + '\'' + ", type='" + type + '\'' + '}';
    }
    public void setColor(String newcolor)
    {
        this.color = newcolor;
    }
}