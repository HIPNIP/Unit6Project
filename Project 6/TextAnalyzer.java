import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class TextAnalyzer{
    private JFrame frame;
    private JTextArea textArea;
    private JLabel sentimentLabel;
    private Map<String, Double> sentimentMap;

    public TextAnalyzer() {
        sentimentMap = new HashMap<>();
        loadSentimentData("sentiment.txt"); // Ensure "sentiment.txt" exists with words and values

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

    private void loadSentimentData(String filePath) {
        String[] lines = FileReader.toStringArray(filePath);
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length == 2) {
                sentimentMap.put(parts[0].toLowerCase(), Double.parseDouble(parts[1]));
            }
        }
    }

    private void updateSentimentScore() {
        String text = textArea.getText().toLowerCase();
        String[] words = text.split("\\s+");
        double totalScore = 0.0;

        for (String word : words) {
            totalScore += sentimentMap.getOrDefault(word, 0.0);
        }

        sentimentLabel.setText("Sentiment Score: " + totalScore);
    }

    public static void main(String[] args) {
        new TextAnalyzer();
    }
}
