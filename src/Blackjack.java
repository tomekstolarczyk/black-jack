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
    }

    

    Blackjack() {
        startGame();
    }

    void startGame() {
      //deck
      buildDeck();

    }

    ArrayList<Card> deck;
    
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
        System.out.println("Building Deck");
        System.out.println(deck);
    }
}
