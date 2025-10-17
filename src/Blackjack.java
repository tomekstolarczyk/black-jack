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

        public int getValue() {
            if (value == "A") {
                return 11;
            } else if (value == "K" || value == "Q" || value == "J") {
                return 10;
            } else {
                return Integer.parseInt(value); 
            }
        }

        public boolean isAce() {
            return value == "A";
        }

        public String getImagePath() {
            return "./cards/" + toString() + ".png";
        }
    }

    ArrayList<Card> deck;
    Random random = new Random(); //for deck shuffling

    Card card;

    //dealer
    Card hiddenCard;
    ArrayList<Card> dealerHand;
    int dealerSum;
    int dealerAceCount;

    //player 
    ArrayList<Card> playerHand;
    int playerSum;
    int playerAceCount;

    //window
    int boardWidth = 600;
    int boardHeight = boardWidth;

    int cardWidth = 110; //ratio 1/1.4
    int cardHeight = 154;
    
    JFrame frame = new JFrame("Black Jack");
    JPanel gamePanel = new JPanel() {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            try {
                //draw hidden card 
                Image hiddenCardImg = new ImageIcon(getClass().getResource("./cards/BACK.png")).getImage();
                g.drawImage(hiddenCardImg, 20, 20, cardWidth, cardHeight, null);

                //draw dealer's hand 
                for (int i = 0; i < dealerHand.size(); i++) {
                    Card card = dealerHand.get(i);
                    Image cardImg = new ImageIcon(getClass().getResource(card.getImagePath())).getImage();
                    g.drawImage(cardImg, cardWidth + 25 + (cardWidth+5)*i, 20, cardWidth, cardHeight, null);
                }

                // draw players hand 
                for (int i = 0; i < playerHand.size(); i++) {
                    Card card = playerHand.get(i);
                    Image cardImg = new ImageIcon(getClass().getResource(card.getImagePath())).getImage();
                    g.drawImage(cardImg, 20 + (cardWidth+5)*i, 320, cardWidth, cardHeight, null);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    };
    JPanel buttonPanel = new JPanel();
    JButton hitButton = new JButton("Hit!");
    JButton stayButton = new JButton("Stay -_-");

    Blackjack() {
        startGame();

        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gamePanel.setLayout(new BorderLayout());
        gamePanel.setBackground(new Color(53,101,77));
        frame.add(gamePanel);

        hitButton.setFocusable(false);
        buttonPanel.add(hitButton);
        stayButton.setFocusable(false);
        buttonPanel.add(stayButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

    }

    public void startGame() {
      //deck
        buildDeck();
        shuffleDeck();

        //dealer
        dealerHand = new ArrayList<Card>();
        dealerSum = 0;
        dealerAceCount = 0;
        
        hiddenCard = deck.remove(deck.size()-1); //remove card at last index 
        dealerSum += hiddenCard.getValue();
        dealerAceCount += hiddenCard.isAce() ? 1 : 0;

        card = deck.remove(deck.size()-1);
        dealerSum += card.getValue();
        dealerAceCount += card.isAce() ? 1 : 0;
        dealerHand.add(card);

        System.out.println("Dealer: ");
        System.out.println(hiddenCard);
        System.out.println(dealerHand);
        System.out.println(dealerSum);
        System.out.println(dealerAceCount);

        //player 

        playerHand = new ArrayList<Card>();
        playerSum = 0;
        playerAceCount = 0;
        
        for (int i = 0; i<2; i++) {
            card = deck.remove(deck.size()-1);
            playerSum += card.getValue();
            playerAceCount += card.isAce() ? 1 : 0;
            playerHand.add(card);
        }

        System.out.println("Player: ");
        System.out.println(playerHand);
        System.out.println(playerSum);
        System.out.println(playerAceCount);

    }
    
    public void buildDeck() {

        deck = new ArrayList<Card>();
        String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        String[] types = {"H", "D", "C", "S"};
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
