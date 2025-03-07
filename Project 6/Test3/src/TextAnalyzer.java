import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * The TextAnalyzer uses a simple sentiment and place name analyzer.
 * It reads sentiment values from a file and calculates a sentiment score based on user input.
 * Additionally, it counts the amount of place names from a place list.
 */
public class TextAnalyzer {
    private JFrame frame;
    private JTextArea textArea;
    private JLabel sentimentLabel;
    private JLabel placeCountLabel;
    private ArrayList<String> sentimentData;
    private ArrayList<String> places;
    private int placeCount = 0;

    /**
     * Constructs a new TextAnalyzer GUI.
     * Loads sentiment data and place names into ArrayLists.
     * Initializes the GUI things.
     */
    public TextAnalyzer() {
        sentimentData = new ArrayList<>();
        places = new ArrayList<>();

        // Read sentiment values into ArrayList
        String[] sentimentLines = FileReader.toStringArray("src/sentimentvalues.txt");
        for (int i = 0; i < sentimentLines.length; i++) {
            sentimentData.add(sentimentLines[i]);
        }

        // Read places into ArrayList and convert to lowercase
        String[] placeLines = FileReader.toStringArray("src/places.txt");
        for (int i = 0; i < placeLines.length; i++) {
            places.add(placeLines[i].toLowerCase());
        }

        // Initialize GUI frame
        frame = new JFrame("Sentiment Analyzer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);

        JPanel panel = new JPanel(new BorderLayout());
        textArea = new JTextArea();
        textArea.setLineWrap(true);
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Labels for displaying sentiment score and place count
        sentimentLabel = new JLabel("Sentiment Score: 0.0");
        placeCountLabel = new JLabel("Place Count: 0");
        JPanel labelPanel = new JPanel(new GridLayout(2, 1));
        labelPanel.add(sentimentLabel);
        labelPanel.add(placeCountLabel);
        panel.add(labelPanel, BorderLayout.SOUTH);

        frame.add(panel);

        // Add key listener to update analysis on text change
        textArea.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                updateAnalysis();
            }
        });

        frame.setVisible(true);
    }

    /**
     * Updates the sentiment score and place count based on user input.
     * It processes the input text calculates sentiment and counts place occurrences.
     * Preconditions: The sentimentData and places lists must be initialized.
     * Postconditions: The sentiment score and place count are updated.
     */
    private void updateAnalysis() {
        String text = textArea.getText().toLowerCase();
        String[] words = text.split("\\s+");
        double totalScore = 0.0;
        placeCount = 0;

        // Algorithm 1: Sentiment Score Calculation (Loop + Conditional)
        for (int i = 0; i < sentimentData.size(); i++) {
            String line = sentimentData.get(i);
            String[] parts = line.split(",");
            String word = parts[0].toLowerCase(); // Get the word
            double score = Double.parseDouble(parts[1]); // Get the sentiment score

            for (int j = 0; j < words.length; j++) {
                if (words[j].equals(word)) {
                    totalScore += score;
                }
            }
        }

        // Algorithm 2: Place Name Matching (Loop + Conditional)
        for (int i = 0; i < words.length; i++) {
            if (places.contains(words[i])) {
                placeCount++;
            }
        }

        // Update GUI labels
        sentimentLabel.setText("Sentiment Score: " + totalScore);
        placeCountLabel.setText("Place Count: " + placeCount);
    }

    /**
     * The main method to start the TextAnalyzer.
     */
    public static void main(String[] args) {
        new TextAnalyzer();
    }
}
