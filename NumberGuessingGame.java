import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGame {
    private JFrame frame;
    private JTextField guessField;
    private JButton guessButton;
    private JLabel messageLabel;
    private int randomNumber;
    private int attempts;
    private final int maxAttempts = 10;

    public NumberGuessingGame() {
        // Generate a random number between 1 and 100
        Random rand = new Random();
        randomNumber = rand.nextInt(100) + 1;
        attempts = 0;

        // Set up the frame
        frame = new JFrame("Number Guessing Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(null);

        // Set up the input field for guesses
        guessField = new JTextField();
        guessField.setBounds(150, 50, 100, 25);
        frame.add(guessField);

        // Set up the guess button
        guessButton = new JButton("Guess");
        guessButton.setBounds(150, 100, 100, 25);
        frame.add(guessButton);

        // Set up the message label
        messageLabel = new JLabel("Enter a number between 1 and 100:");
        messageLabel.setBounds(50, 20, 300, 25);
        frame.add(messageLabel);

        // Add action listener to the button
        guessButton.addActionListener(new GuessButtonListener());

        // Make the frame visible
        frame.setVisible(true);
    }

    // Action listener for the guess button
    private class GuessButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int guess = Integer.parseInt(guessField.getText());
                attempts++;
                if (guess < 1 || guess > 100) {
                    messageLabel.setText("Please enter a number between 1 and 100.");
                } else if (guess < randomNumber) {
                    messageLabel.setText("Too low! Attempts: " + attempts + "/" + maxAttempts);
                } else if (guess > randomNumber) {
                    messageLabel.setText("Too high! Attempts: " + attempts + "/" + maxAttempts);
                } else {
                    messageLabel.setText("Correct! The number was " + randomNumber + ". Attempts: " + attempts);
                    int playAgain = JOptionPane.showConfirmDialog(frame, "You guessed it! Play again?", "Play Again", JOptionPane.YES_NO_OPTION);
                    if (playAgain == JOptionPane.YES_OPTION) {
                        randomNumber = new Random().nextInt(100) + 1;
                        attempts = 0;
                        messageLabel.setText("Enter a number between 1 and 100:");
                    } else {
                        frame.dispose();
                    }
                }

                if (attempts >= maxAttempts) {
                    JOptionPane.showMessageDialog(frame, "You've reached the maximum number of attempts. The number was " + randomNumber + ".");
                    int playAgain = JOptionPane.showConfirmDialog(frame, "Play again?", "Play Again", JOptionPane.YES_NO_OPTION);
                    if (playAgain == JOptionPane.YES_OPTION) {
                        randomNumber = new Random().nextInt(100) + 1;
                        attempts = 0;
                        messageLabel.setText("Enter a number between 1 and 100:");
                    } else {
                        frame.dispose();
                    }
                }

            } catch (NumberFormatException ex) {
                messageLabel.setText("Please enter a valid number.");
            }
        }
    }

    public static void main(String[] args) {
        new NumberGuessingGame();
    }
}
