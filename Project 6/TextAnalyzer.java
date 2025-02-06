import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TextAnalyzer {
    private JFrame frame;
    private JTextArea textArea;
    private JLabel sentimentLabel;
    private String[] sentimentData;

    public TextAnalyzer() {
        sentimentData = FileReader.toStringArray("sentiment.txt"); // Ensure "sentiment.txt" exists with words and values

        frame = new JFrame("Sentiment Analyzer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        JPanel panel = new JPanel(new BorderLayout());
        textArea = new JTextArea();
        textArea.setLineWrap(true);
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);

        sentimentLabel = new JLabel("Sentiment Score: 0.0");
        panel.add(sentimentLabel, BorderLayout.SOUTH);

        frame.add(panel);

        textArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                updateSentimentScore();
            }
        });

        frame.setVisible(true);
    }

    private void updateSentimentScore() {
        String text = textArea.getText().toLowerCase();
        String[] words = text.split("\\s+");
        double totalScore = 0.0;

        for (String line : sentimentData) {
            String[] parts = line.split(",");
            if (parts.length == 2) {
                String word = parts[0].toLowerCase();
                double score = Double.parseDouble(parts[1]);
                for (String inputWord : words) {
                    if (inputWord.equals(word)) {
                        totalScore += score;
                    }
                }
            }
        }

        sentimentLabel.setText("Sentiment Score: " + totalScore);
    }

    public static void main(String[] args) {
        new TextAnalyzer();
    }
}
