import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class MemoryCardGame {

    private JFrame frame;
    private JPanel gridPanel;
    private JLabel messageLabel;
    public ArrayList<Card> cards;
    private Card firstCard;
    private Card secondCard;

    public MemoryCardGame() {
        cards = new ArrayList<>();
        setupCards();
        createGUI();
    }

    protected void setupCards() {
        String[][] translations = {
                {"One", "一"}, {"Two", "二"}, {"Three", "三"},
                {"Four", "四"}, {"Five", "五"}, {"Six", "六"},
                {"Seven", "七"}, {"Eight", "八"}, {"Nine", "九"}, {"Ten", "十"}
            };

        for (String[] pair : translations) {
            cards.add(new Card(pair[0]));
            cards.add(new Card(pair[1]));
        }

        Collections.shuffle(cards);
    }

    public void createGUI() {
        frame = new JFrame("Memory Card Game - Bilingual Edition");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        messageLabel = new JLabel("Match cards with the same meaning!", JLabel.CENTER);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 16));
        frame.add(messageLabel, BorderLayout.NORTH);

        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(5, 4, 10, 10));
        frame.add(gridPanel, BorderLayout.CENTER);

        for (Card card : cards) {
            JButton button = card.getButton();
            button.addActionListener(new CardClickListener(card));
            gridPanel.add(button);
        }

        frame.setVisible(true);
    }

    private class CardClickListener implements ActionListener {
        private final Card card;

        public CardClickListener(Card card) {
            this.card = card;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (card.isMatched() || card == firstCard || card == secondCard) {
                    return; // Ignore clicks on already matched or currently selected cards
                }

                // If unmatched cards are still revealed, hide them and reset
                if (firstCard != null && secondCard != null) {
                    firstCard.hide();
                    secondCard.hide();
                    firstCard = null;
                    secondCard = null;
                }

                card.reveal();

                if (firstCard == null) {
                    firstCard = card;
                } else if (secondCard == null) {
                    secondCard = card;
                    checkMatch();
                }

            } catch (Exception ex) {
                // This will catch any unexpected exceptions and print an error message
                JOptionPane.showMessageDialog(frame, "An error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace(); // Optionally log the error stack trace
            }
        }

        private void checkMatch() {
            try {
                if (areMatched(firstCard.getValue(), secondCard.getValue())) {
                    messageLabel.setText("Good job, keep it up!");
                    firstCard.setMatched(true);
                    secondCard.setMatched(true);
                    firstCard = null;
                    secondCard = null;

                    if (isGameFinished()) {
                        JOptionPane.showMessageDialog(frame, "Congratulations! You matched all the cards!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    messageLabel.setText("Ouch, maybe next time!");
                }
            } catch (Exception ex) {
                // Catch any exception during matching check
                JOptionPane.showMessageDialog(frame, "Error during match check: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }

        private boolean isGameFinished() {
            try {
                for (Card card : cards) {
                    if (!card.isMatched()) {
                        return false;
                    }
                }
                return true;
            } catch (Exception ex) {
                // Catch any error during game finish check
                JOptionPane.showMessageDialog(frame, "Error while checking if the game is finished: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
                return false;
            }
        }
    }

    protected boolean areMatched(String value1, String value2) {
        return (value1.equals("One") && value2.equals("一")) || (value1.equals("一") && value2.equals("One")) ||
        (value1.equals("Two") && value2.equals("二")) || (value1.equals("二") && value2.equals("Two")) ||
        (value1.equals("Three") && value2.equals("三")) || (value1.equals("三") && value2.equals("Three")) ||
        (value1.equals("Four") && value2.equals("四")) || (value1.equals("四") && value2.equals("Four")) ||
        (value1.equals("Five") && value2.equals("五")) || (value1.equals("五") && value2.equals("Five")) ||
        (value1.equals("Six") && value2.equals("六")) || (value1.equals("六") && value2.equals("Six")) ||
        (value1.equals("Seven") && value2.equals("七")) || (value1.equals("七") && value2.equals("Seven")) ||
        (value1.equals("Eight") && value2.equals("八")) || (value1.equals("八") && value2.equals("Eight")) ||
        (value1.equals("Nine") && value2.equals("九")) || (value1.equals("九") && value2.equals("Nine")) ||
        (value1.equals("Ten") && value2.equals("十")) || (value1.equals("十") && value2.equals("Ten"));
    }
}
