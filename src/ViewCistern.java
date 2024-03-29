import javax.swing.*;
import java.awt.*;
import java.text.MessageFormat;

public class ViewCistern extends ViewObject{
    Cistern pair;

    public ViewCistern(int _x, int _y, Cistern _pair) {
        this.x = _x;
        this.y = _y;
        this.pair = _pair;
    }

    /**
     * Repaints the object
     * @param g the graphics object
     */
    @Override
    public void Repaint(Graphics g) {
        super.Repaint(g);
        this.paintComponent(g);
    }

    /**
     * Paints the object
     * @param g the graphics object
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g; // cast to get 2D drawing methods

        // Load the image
        Image image = new ImageIcon("src\\images\\cistern.png").getImage();
        //set image size
        image = new ImageIcon(image.getScaledInstance(30, 30, Image.SCALE_DEFAULT)).getImage();

        // Draw the image at (x, y)
        g2d.drawImage(image, x+8, y+11, this);

        // Add text below the image
        String text = MessageFormat.format("{0}",pair.id);
        Font font = new Font("Arial", Font.PLAIN, 12);
        g2d.setFont(font);
        g2d.setColor(Color.BLACK);
        int textX = x;
        int textY = y + image.getHeight(this) + 19; // Adjust the distance as needed
        g2d.drawString(text, textX, textY);
    }
}
