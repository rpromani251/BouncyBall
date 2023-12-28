import java.awt.Color;
import java.awt.Graphics;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Ball {

    // This class represents the ball, containing attributes of position, velocity, and radius
    // Contains methods to update its state and to draw it

    private double x; // Position
    private double y;
    private double vx, vy; // Velocity
    private double accelerationDueToGravity; // Added gravity
    private int radius;
    private int panelWidth; // Width of the animation panel
    private int panelHeight; // Height of the animation panel
    private boolean isBouncing; // To control bounce behavior
    private int numBounces; // To count the number of bounces

    private static FileWriter writer; // Declare FileWriter



    public Ball(int x, int y, int radius, int panelWidth, int panelHeight) {
        this.x = x; this.y = y; this.radius = radius;
        this.panelWidth = panelWidth; this.panelHeight = panelHeight;

        // create instance of Random class
        Random rand = new Random();

        vx = rand.nextInt(3) + rand.nextDouble(); // Randomized initial horizontal velocity
        vy = 3.8; // Initial vertical velocity
        accelerationDueToGravity = 0.3;
        isBouncing = true;
        numBounces = 0;

        // Initialize FileWriter (append mode)
        try {
            writer = new FileWriter("ball_data.txt", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        // Print to console current data:
        System.out.printf(
            "Number of Bounces: %d, Position: %f, %f; Velocity; %f, %f \n"
            , numBounces, x, y, vx, vy);

        // Update position using velocity;
        x += vx;
        y += vy;

        // Update velocity using acceleration;
        if (vy>0) {
            vy += accelerationDueToGravity;
        }
        if (vy<0) {
            vy += accelerationDueToGravity;
        }

        // Bounce off the walls
        if (x < 0 || x > panelWidth - (radius*2)) {
            vx *= -1; // Reverse horizontal velocity
            // System.out.println("Horizontal velocity inverted");
            this.vy += .1;
        }

        // // Bounce off the floor: If you do not want the game to end on floor touch
        if (y > panelHeight - (radius*2)) {
            vy *= -0.8; // Reduce vertical velocity due to bounce (elasticity)
            y = panelHeight - (radius*2); // Prevent ball from going through the floor
            numBounces ++;
            // System.out.println("Vertical velocity inverted");
        }

        // // End game on floor bounce
        // if (y > panelHeight - (radius*2)) {
        //     vy = 1000;
        // }


        // Stop the bouncing if the bounce height becomes too small
        if (isBouncing && numBounces >=30 && Math.abs(vy) < 0.09) {
            isBouncing = false;
            vy = 0; // Stops vertical motion
        }

        // Write velocity and y value to file
        try {
            writer.write(vy + "," + y + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void closeWriter() {
        // Close the FileWriter
        try {
            if (writer != null) {
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval((int)x, (int)y, radius*2, radius*2);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getVX() {
        return vx;
    }

    public double getVY() {
        return vy;
    }

    public int getRadius() {
        return radius;
    }

    public void bounceVertical() {
        this.vy *= -1;
    }

    public void bounceHorizontal() {
        this.vx *= -1.05;
        this.vy += .1;
    }


    
}
