import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;

public class RockPaperScissorsFrame extends JFrame {
    private int playerWins = 0;
    private int computerWins = 0;
    private int ties = 0;

    private JTextField playerWinsField;
    private JTextField computerWinsField;
    private JTextField tiesField;
    private JTextArea resultsArea;

    private ArrayList<String> playerMoves = new ArrayList<>();
    private Strategy strategy;

    public RockPaperScissorsFrame() {
        setTitle("Rock Paper Scissors Game");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Choose your move"));

        // Load and resize images for buttons
        JButton rockButton = new JButton(new ImageIcon(resizeImage("src/rock.jpg", 100, 100))); // Adjust size as needed
        JButton paperButton = new JButton(new ImageIcon(resizeImage("src/paper.jpg", 100, 100))); // Adjust size as needed
        JButton scissorsButton = new JButton(new ImageIcon(resizeImage("src/scissors.jpg", 100, 100))); // Adjust size as needed
        JButton quitButton = new JButton("Quit"); // No image for Quit button

        buttonPanel.add(rockButton);
        buttonPanel.add(paperButton);
        buttonPanel.add(scissorsButton);
        buttonPanel.add(quitButton);

        add(buttonPanel, BorderLayout.NORTH);

        JPanel statsPanel = new JPanel(new GridLayout(3, 2));
        statsPanel.add(new JLabel("Player Wins:"));
        playerWinsField = new JTextField("0", 5);
        playerWinsField.setEditable(false);
        statsPanel.add(playerWinsField);

        statsPanel.add(new JLabel("Computer Wins:"));
        computerWinsField = new JTextField("0", 5);
        computerWinsField.setEditable(false);
        statsPanel.add(computerWinsField);

        statsPanel.add(new JLabel("Ties:"));
        tiesField = new JTextField("0", 5);
        tiesField.setEditable(false);
        statsPanel.add(tiesField);

        add(statsPanel, BorderLayout.CENTER);

        resultsArea = new JTextArea(10, 30);
        JScrollPane scrollPane = new JScrollPane(resultsArea);
        JPanel resultsPanel = new JPanel();
        resultsPanel.add(scrollPane);

        add(resultsPanel, BorderLayout.SOUTH);

        rockButton.addActionListener(new MoveButtonListener("Rock"));
        paperButton.addActionListener(new MoveButtonListener("Paper"));
        scissorsButton.addActionListener(new MoveButtonListener("Scissors"));
        quitButton.addActionListener(e -> System.exit(0));
    }

    private BufferedImage resizeImage(String imagePath, int width, int height) {
        try {
            BufferedImage originalImage = ImageIO.read(new File(imagePath));
            Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage bufferedResizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = bufferedResizedImage.createGraphics();
            g2d.drawImage(resizedImage, 0, 0, null);
            g2d.dispose();
            return bufferedResizedImage;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private class MoveButtonListener implements ActionListener {
        private String playerMove;

        public MoveButtonListener(String playerMove) {
            this.playerMove = playerMove;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            playerMoves.add(playerMove);

            strategy = selectRandomStrategy(); // Use a random strategy
            String computerMove = strategy.determineMove(playerMoves);
            String result = determineWinner(playerMove, computerMove);

            resultsArea.append(result + "\n");
            updateStats(result);
        }
    }

    private Strategy selectRandomStrategy() {
        Strategy[] strategies = {
                new LeastUsedStrategy(),
                new MostUsedStrategy(),
                new LastUsedStrategy(),
                new CheatStrategy()
        };
        Random random = new Random();
        return strategies[random.nextInt(strategies.length)];
    }

    private String determineWinner(String playerMove, String computerMove) {
        if (playerMove.equals(computerMove)) {
            return playerMove + " equals " + computerMove + ". (Tie)";
        } else if ((playerMove.equals("Rock") && computerMove.equals("Scissors")) ||
                (playerMove.equals("Paper") && computerMove.equals("Rock")) ||
                (playerMove.equals("Scissors") && computerMove.equals("Paper"))) {
            return playerMove + " beats " + computerMove + ". (Player wins)";
        } else {
            return computerMove + " beats " + playerMove + ". (Computer wins)";
        }
    }

    private void updateStats(String result) {
        if (result.contains("Player wins")) {
            playerWins++;
            playerWinsField.setText(String.valueOf(playerWins));
        } else if (result.contains("Computer wins")) {
            computerWins++;
            computerWinsField.setText(String.valueOf(computerWins));
        } else {
            ties++;
            tiesField.setText(String.valueOf(ties));
        }
    }
}
