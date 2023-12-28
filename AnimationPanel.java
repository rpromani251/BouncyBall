import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class AnimationPanel extends JPanel implements KeyListener {
    private Ball ball;
    private Paddle paddle;
    private static Timer timer;
    private JButton restartButton;
    private JLabel counterLabel;
    private static int counter;

    public AnimationPanel() {
        ball = new Ball(300, 40, 15, 600, 600);
        paddle = new Paddle(300, 550, 25 , 3); // Adjust as needed
        restartButton = new JButton("Restart");
        counter = 0;
        counterLabel = new JLabel("Score: "+ counter);

        setBackground(Color.LIGHT_GRAY);

        // Add the counter to the top right corner
        counterLabel.setHorizontalAlignment(JLabel.RIGHT);
        add(counterLabel, BorderLayout.NORTH);

        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restart(); // Call the restart method
            }
        });

        // Add the restart button to the top left corner
        restartButton.setHorizontalAlignment(JButton.LEFT);
        add(restartButton, BorderLayout.NORTH);
        
        timer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ball.update();
                collisionUpdate();
                checkIfGameOver();
                repaint();
            }
        });
        timer.start();

        addKeyListener(this);
        setFocusable(true);
    }

    public void collisionUpdate() {
        // Check for ball collision with paddle
        if (ball.getY() + ball.getRadius() >= paddle.getY() -20  &&
            ball.getY() <= paddle.getY() + paddle.getHeight() -20 &&
            ball.getX() + ball.getRadius() >= paddle.getX() &&
            ball.getX() <= paddle.getX() + paddle.getWidth()) {
            ball.bounceVertical(); // Reverse vertical velocity due to collision with paddle
            ball.bounceHorizontal(); // Reverse horizontal velocity due to collision with paddle

            // Increment the counter
            incrementCounter();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ball.draw(g);
        paddle.draw(g);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(600, 600);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_A) {
            paddle.moveLeft();
        } else if (keyCode == KeyEvent.VK_D) {
            paddle.moveRight();
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public void restart() {
        timer.stop(); // Stops the existing timer

        // Re-initialize objects
        ball = new Ball(300, 40, 15, 600, 600); 
        paddle = new Paddle(300, 550, 25 , 3);

        resetCounter(); // Reset counter

        timer.start(); // Restart timer
        
        repaint(); // Repaints panel

        // Request focus for the panel
        this.requestFocusInWindow();
    }

    private void incrementCounter() {
        counter++; // Increment the counter
        counterLabel.setText("Score: "+ counter); // Update the counter
    }
    
    private void resetCounter() {
        counter = 0;
        counterLabel.setText("Score: "+counter);
    }

    private void checkIfGameOver() {
        if (ball.getVY() >= 100) {
            gameOver();
        }
    }

    private void gameOver() {
        // Stop timer
        timer.stop();

        // Display game over message to panel
        JLabel gameOverMessageLabel = new JLabel("GAME OVER!");
        // gameOverMessageLabel.setFont(new Font("Arial", Font.PLAIN, 24)); // Customize font
        gameOverMessageLabel.setHorizontalAlignment(JLabel.CENTER);

        add(gameOverMessageLabel, BorderLayout.CENTER); // Add label to the center

        resetCounter();


    }
}
