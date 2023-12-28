import java.awt.Color;
import java.awt.Graphics;

public class Paddle {
    private int x,y; // Position
    private int width, height; // Dimensions

    public Paddle(int x, int y, int width, int height) {
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
    }

    public void moveLeft() {
        x -= 7; // Adjust movement speed as needed
    }

    public void moveRight() {
        x += 7; // Adjust movement speed as needed
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, width, height);
    }

    public int getX () {
        return x;
    }

    public int getY () {
        return y;
    }

    public int getWidth () {
        return width;
    }

    public int getHeight () {
        return height;
    }
    
}
