import javax.swing.*;
import java.awt.*;

public class ViewMechanic extends ViewObject{
    Mechanic pair;

    public ViewMechanic(int _x, int _y, Mechanic _pair) {
        this.x = _x;
        this.y = _y;
        this.pair = _pair;
    }

    /**
     * Újrarajzolja az objektumot
     * @param g a grafikus objektum
     */
    @Override
    public void Repaint(Graphics g) {
        super.Repaint(g);
        this.paintComponent(g);
    }

    /**
     * Kirajzolja az objektumot
     * @param g a grafikus objektum
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
         x=ViewMap.viewmap.getObjects(pair.where).x-15;// ahol all
         y=ViewMap.viewmap.getObjects(pair.where).y-15;// ahol all
        Graphics2D g2d = (Graphics2D) g; // cast to get 2D drawing methods

        // Load the image
        Image image = new ImageIcon("src\\images\\asd.png").getImage();
        //set image size
        image = new ImageIcon(image.getScaledInstance(45, 55, Image.SCALE_DEFAULT)).getImage();

        // Draw the image at (x, y)
        g2d.drawImage(image, x, y, this);

        // Add text below the image
        String text = pair.name;
        Font font = new Font("Arial", Font.PLAIN, 12);
        g2d.setFont(font);
        g2d.setColor(Color.BLACK);
        int textX = x;
        int textY = y - 10; // Adjust the distance as needed
        g2d.drawString(text, textX, textY);
    }
}
