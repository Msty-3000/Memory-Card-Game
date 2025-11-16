import javax.swing.*;
import java.awt.*;

public class Card {
    private String value;
    private JButton button;
    private boolean isMatched;
    Color lightGray = new Color(200, 200, 200);

    public Card(String value) {
        this.value = value;
        this.isMatched = false;
        button = new JButton();
        button.setBackground(Color.lightGray); // Back of the card is gray
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
    }

    public String getValue() {
        return value;
    }

    public JButton getButton() {
        return button;
    }

    public boolean isMatched() {
        return isMatched;
    }

    public void setMatched(boolean matched) {
        isMatched = matched;
        if (matched) {
            button.setEnabled(false); // Disable matched cards
        }
    }

    public void reveal() {
        button.setText(value); // Show card value
        button.setBackground(Color.WHITE); // Front of the card is white
    }

    public void hide() {
        button.setText(""); // Hide card value
        button.setBackground(Color.lightGray); // Back to gray
    }
}
