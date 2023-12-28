import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class BouncingBallApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Bouncing ball Animation");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                AnimationPanel animationPanel = new AnimationPanel();
                frame.add(animationPanel);

                // Add a window listener to close the FileWriter when the application is closed
                frame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                });

                frame.pack();
                frame.setVisible(true);

            }
        });
    }
}