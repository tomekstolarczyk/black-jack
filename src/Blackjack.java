import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class Blackjack {

    private class Card {

        String type;
        String value;

        Card(String type, String value) {
            this.type = type;
            this.value = value;
        }

        public String toString() {
            return value + "-" + type;
        }
    }

    

    Blackjack() {
        startGame();
    }

    void startGame() {
      //deck
        buildDeck();
        shuffleDeck();
    }

    ArrayList<Card> deck;
    Random random = new Random();
    
    public void buildDeck() {

        deck = new ArrayList<Card>();
        String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        String[] types = {"Hearts", "Diamonds", "Clubs", "Spades"};
        for (String value : values) {
            for (String type : types) {
                Card card = new Card(type, value);
                deck.add(card);
            }
        }
        System.out.println("-----------------------------------------");
        System.out.println("Building Deck: \n");
        System.out.println(deck);
    }

    public void shuffleDeck() {

        for (int i = 0; i < deck.size(); i++) {

            int randomIndex = random.nextInt(deck.size());
            Card currentCard = deck.get(i);
            Card randomCard = deck.get(randomIndex);
            deck.set(i, randomCard);
            deck.set(randomIndex, currentCard);
        }

        System.out.println("-----------------------------------------");
        System.out.println("Shuffling Deck: \n");
        System.out.println(deck);
    }
}
