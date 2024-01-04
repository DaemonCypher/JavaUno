import java.util.*;

public class Deck
{
  private Stack < Card > deck;

  enum Color
  { RED, YELLOW, BLUE, GREEN, ANY }
  enum Type
  { ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, SKIP, REVERSE,
    PLUS2, WILD, WILDPLUS4
  }

  public Deck ()
  {
    deck = new Stack <> ();
    initializeDeck ();
  }

  private void initializeDeck ()
  {
    // Add regular cards
    for (Color color:Color.values ())
    {

	    if (color != Color.ANY)
	    {			// Exclude 'ANY' for regular cards
	        for (Type type:Type.values ())
	        {

		        if (!type.equals (Type.WILD) && !type.equals (Type.WILDPLUS4))
		        {
                    deck.push (new Card (color.name (), type.name ()));
                    deck.push (new Card (color.name (), type.name ()));	// Add a second card of the same type for each color
		        }

	        }
	    }

    }
    // Add wild cards
    for (int i = 0; i < 4; i++)
    {				
	    deck.push (new Card (Color.ANY.name (), Type.WILD.name ()));
	    deck.push (new Card (Color.ANY.name (), Type.WILDPLUS4.name ()));
    }
    // Shuffle the deck
    Collections.shuffle (deck);
  }

  public boolean isEmpty ()
  {
    return deck.isEmpty ();
  }

  public Card draw ()
  {
    if (!deck.isEmpty ())
    {
	    return deck.pop ();
    }
    return null;		// or throw an exception if the deck is empty
  }

}
