import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BChecker {
    private JFrame frame;
    private JTextArea textArea;
    private JLabel counterLabel;

    public BChecker() {
        frame = new JFrame("B Checker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        JPanel panel = new JPanel(new BorderLayout());
        textArea = new JTextArea();
        textArea.setLineWrap(true);
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);

        counterLabel = new JLabel("Number of 'b's: 0");
        panel.add(counterLabel, BorderLayout.SOUTH);

        frame.add(panel);

        textArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {

                // if (Character.toLowerCase(e.getKeyChar()) == 'b') {
                //     bCount++;
                //     counterLabel.setText("Number of 'b's: " + bCount);
                // }

                String currentText = textArea.getText();
                
                int bCount = 0;
                for(int i = 0; i < currentText.length(); i++) {
                    if(currentText.substring(i,i+1).equals("b")) {
                        bCount++;
                    }
                }
                counterLabel.setText("Number of 'b's: " + bCount);

            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new BChecker();
    }
}