#  CLI text game of UNO
Java CLI implementation of the game uno



# Running the code on Docker
```
docker build -t uno .
 docker run -it --name card-game uno
```
# Running the Code Locally
```
javac *.java
java main.java
```
# Example
```
How many players are there:
2
Enter player 1's name:
a
Enter player 2's name:
b
Current card: Card{color='YELLOW', type='SKIP'}
Player's a hand:
0: Card{color='GREEN', type='SKIP'}
1: Card{color='RED', type='SKIP'}
2: Card{color='BLUE', type='SIX'}
3: Card{color='BLUE', type='SEVEN'}
4: Card{color='GREEN', type='ZERO'}
5: Card{color='GREEN', type='EIGHT'}
6: Card{color='YELLOW', type='NINE'}
a, choose an action:
(D)raw card, (P)lay card
p
Choose a card index to play:
1
a played: Card{color='RED', type='SKIP'}
a is skipped!
Current card: Card{color='RED', type='SKIP'}
Player's b hand:
0: Card{color='YELLOW', type='THREE'}
1: Card{color='GREEN', type='PLUS2'}
2: Card{color='BLUE', type='FIVE'}
3: Card{color='YELLOW', type='PLUS2'}
4: Card{color='ANY', type='WILD'}
5: Card{color='BLUE', type='FOUR'}
6: Card{color='GREEN', type='THREE'}
b, choose an action:
(D)raw card, (P)lay card
p
Choose a card index to play:
4
You played a wild card! Choose a new color (RED, YELLOW, BLUE, GREEN):
green
b played: Card{color='ANY', type='WILD'}
Current card: Card{color='GREEN', type='WILD'}
Player's a hand:
0: Card{color='GREEN', type='SKIP'}
1: Card{color='BLUE', type='SIX'}
2: Card{color='BLUE', type='SEVEN'}
3: Card{color='GREEN', type='ZERO'}
4: Card{color='GREEN', type='EIGHT'}
5: Card{color='YELLOW', type='NINE'}
a, choose an action:
(D)raw card, (P)lay card
```
