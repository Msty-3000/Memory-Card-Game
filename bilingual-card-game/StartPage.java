import javax.swing.*;
import java.awt.*;

public class StartPage {
    public StartPage() {
        JFrame frame = new JFrame("Memory Card Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Welcome to Memory Card Game!", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        frame.add(welcomeLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2, 20, 20));
        frame.add(buttonPanel, BorderLayout.CENTER);

        JButton startButton = new JButton("Chinese-English");

        startButton.addActionListener(e -> {
            new MemoryCardGame();
            frame.dispose();
        });

        buttonPanel.add(startButton);

        frame.setVisible(true);
    }
}

