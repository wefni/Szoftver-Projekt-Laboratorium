import javax.swing.*;
import java.awt.*;
import java.text.MessageFormat;

public class ViewSource extends ViewObject{
    Source pair;

    public ViewSource(int _x, int _y, Source _pair) {
        this.x = _x;
        this.y = _y;
        this.pair = _pair;
    }

    /**
     * A forrás újrarajzolása.
     * @param g a grafikus objektum
     */
    @Override
    public void Repaint(Graphics g) {
        super.Repaint(g);
        this.paintComponent(g);
    }

    /**
     * A forrás kirajzolása.
     * @param g a grafikus objektum
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g; // cast to get 2D drawing methods

        // Load the image
        Image image = new ImageIcon("src\\images\\source.png").getImage();
        //set image size
        image = new ImageIcon(image.getScaledInstance(240, 180, Image.SCALE_DEFAULT)).getImage();

        // Draw the image at (x, y)
        g2d.drawImage(image, x-183, y-104, this);

        // Add text below the image
        String text = MessageFormat.format("{0}",pair.id);
        Font font = new Font("Arial", Font.PLAIN, 12);
        g2d.setFont(font);
        g2d.setColor(Color.BLACK);
        int textX = x - 120;
        int textY = y + 40 ; // Adjust the distance as needed
        g2d.drawString(text, textX, textY);

        Image image2 = new ImageIcon("src\\images\\varos.png").getImage();
        //set image size
        image2 = new ImageIcon(image2.getScaledInstance(270, 180, Image.SCALE_DEFAULT)).getImage();

        // Draw the image at (x, y)
        g2d.drawImage(image2, 645, 560, this);
    }
}
