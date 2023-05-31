import javax.swing.*;
import java.awt.*;
import java.text.MessageFormat;

public class ViewPump extends ViewObject{

    private Pump pair;
    public ViewPump(int _x, int _y, Pump _pair) {
        this.x = _x;
        this.y = _y;
        this.pair = _pair;
    }

    /**
     * A pumpa kirajzolása.
     * @param g a grafikus objektum
     */
    @Override
    public void Repaint(Graphics g) {
        super.Repaint(g);
        this.paintComponent(g);
    }

    /**
     * A pumpa kirajzolása.
     * @param g a grafikus objektum
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g; // cast to get 2D drawing methods


        String typeOfPump = pair.GetWater() + "water";
        if(pair.broken)
            typeOfPump = "broken";
        // Load the image
        Image image = new ImageIcon("src\\images\\pump" + typeOfPump + ".png").getImage();
        //set image size
        image = new ImageIcon(image.getScaledInstance(50, 50, Image.SCALE_DEFAULT)).getImage();

        // Draw the image at (x, y)
        g2d.drawImage(image, x-15, y-15, this);

        // Add text below the image
        String text = MessageFormat.format("{0}",pair.id);
        Font font = new Font("Arial", Font.PLAIN, 12);
        g2d.setFont(font);
        g2d.setColor(Color.BLACK);
        int textX = x;
        int textY = y + image.getHeight(this) - 10 ; // Adjust the distance as needed
        g2d.drawString(text, textX, textY);
    }
}
