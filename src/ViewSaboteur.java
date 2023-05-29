import javax.swing.*;
import java.awt.*;

public class ViewSaboteur extends ViewObject{
    Saboteur pair;

    public ViewSaboteur(int _x, int _y, Saboteur _pair) {
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
        Graphics2D g2d = (Graphics2D) g; // cast to get 2D drawing methods
        x=ViewMap.viewmap.getObjects(pair.where).x-15;// ahol all
        y=ViewMap.viewmap.getObjects(pair.where).y-15;// ahol all
        // Load the image
        Image image = new ImageIcon("src\\images\\sab2.png").getImage();
        //set image size
        image = new ImageIcon(image.getScaledInstance(60, 65, Image.SCALE_DEFAULT)).getImage();

        // Draw the image at (x, y)
        g2d.drawImage(image, x, y - 15, this);

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
