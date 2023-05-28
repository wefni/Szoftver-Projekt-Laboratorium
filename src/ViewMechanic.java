import javax.swing.*;
import java.awt.*;

public class ViewMechanic extends ViewObject{
    Mechanic pair;

    public ViewMechanic(int _x, int _y, Mechanic _pair) {
        this.x = _x;
        this.y = _y;
        this.pair = _pair;
    }
    @Override
    public void Repaint(Graphics g) {
        super.Repaint(g);
        this.paintComponent(g);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
         x=ViewMap.viewmap.getObjects(pair.where).x;// ahol all
         y=ViewMap.viewmap.getObjects(pair.where).y;// ahol all
        Graphics2D g2d = (Graphics2D) g; // cast to get 2D drawing methods

        // Load the image
        Image image = new ImageIcon("src\\images\\mechanic.png").getImage();
        //set image size
        image = new ImageIcon(image.getScaledInstance(30, 30, Image.SCALE_DEFAULT)).getImage();

        // Draw the image at (x, y)
        g2d.drawImage(image, x, y, this);

        // Add text below the image
        String text = "Mech:" + x + ", " + y;
        Font font = new Font("Arial", Font.PLAIN, 12);
        g2d.setFont(font);
        g2d.setColor(Color.BLACK);
        int textX = x;
        int textY = y + image.getHeight(this) + 15; // Adjust the distance as needed
        g2d.drawString(text, textX, textY);
    }
}
