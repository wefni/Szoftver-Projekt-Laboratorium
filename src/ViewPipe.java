import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.text.MessageFormat;
import java.util.HashMap;

import static java.lang.Math.abs;

public class ViewPipe extends ViewObject{
    Pipe pair;
    int n0x;
    int n0y;
    int n1x;
    int n1y;
    public ViewPipe(int _x, int _y, Pipe _pair) {
        this.x = _x;
        this.y = _y;
        this.pair = _pair;

    }
    void Direction()
    {
        // az iranyhoz kell
        //System.out.println(ViewMap.viewmap.getObjects(pair.neighbours.get(1)));
         n0x=ViewMap.viewmap.getObjects(pair.neighbours.get(0)).x;// masodik szomszed x-je
         n0y=ViewMap.viewmap.getObjects(pair.neighbours.get(0)).y;// masodik szomszed y-je
         n1x=ViewMap.viewmap.getObjects(pair.neighbours.get(1)).x; // elso szomszed x-je
         n1y=ViewMap.viewmap.getObjects(pair.neighbours.get(1)).y; // elso szomszed y-je
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
        // Load the image
        //Image image = new ImageIcon("src\\images\\pipe.png").getImage();
        //set image size
        //image = new ImageIcon(image.getScaledInstance(50, 50, Image.SCALE_DEFAULT)).getImage();
        // Draw the image at (x, y)
        AffineTransform trans = new AffineTransform();
        trans.translate(x,y);
        //double degree=dirx*x+diry*(y+1);
        //System.out.println(degree);
        //trans.rotate(Math.toRadians(degree));
        //g2d.drawImage(image, trans, this);
        g2d.draw(new Line2D.Double(n0x,n0y,n1x,n1y));


        if(pair.broken)
        {
            Image imagebroken = new ImageIcon("src\\images\\pipe.png").getImage(); // broken kep
            //g2d.drawImage(image, x, y, this);

        }


        // Add text below the image
        String text = MessageFormat.format("{0}: {1}, {2}",pair.id,x,y);
        Font font = new Font("Arial", Font.PLAIN, 12);
        g2d.setFont(font);
        g2d.setColor(Color.BLACK);
        int textX = x;
        //int textY = y + image.getHeight(this) + 15; // Adjust the distance as needed
        //g2d.drawString(text, textX, textY);
    }

}
