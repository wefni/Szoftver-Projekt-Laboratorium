import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.text.MessageFormat;
import java.util.HashMap;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

public class ViewPipe extends ViewObject{
    Pipe pair;
    double n0x;
    double n0y;
    double n1x;
    double n1y;
    public ViewPipe(int _x, int _y, Pipe _pair) {
        this.x = _x;
        this.y = _y;
        this.pair = _pair;

    }

    /**
     * A cső irányát adja meg.
     */
    void Direction()
    {
        // az iranyhoz kell
         n0x=ViewMap.viewmap.getObjects(pair.neighbours.get(0)).x;// masodik szomszed x-je
         n0y=ViewMap.viewmap.getObjects(pair.neighbours.get(0)).y;// masodik szomszed y-je
         n1x=ViewMap.viewmap.getObjects(pair.neighbours.get(1)).x; // elso szomszed x-je
         n1y=ViewMap.viewmap.getObjects(pair.neighbours.get(1)).y; // elso szomszed y-je
    }

    /**
     * A cső kirajzolása.
     * @param g a grafikus objektum
     */
    @Override
    public void Repaint(Graphics g) {
        super.Repaint(g);
        this.paintComponent(g);
    }

    /**
     * A cső kirajzolása.
     * @param g a grafikus objektum
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(pair.neighbours.size()==2)
            Direction();

        Graphics2D g2d = (Graphics2D) g; // cast to get 2D drawing methods
        if(pair.neighbours.size()==2)
            Direction();
        // Load the image
        Image image = new ImageIcon("src\\images\\pipe.png").getImage();
        //set image size
        image = new ImageIcon(image.getScaledInstance(30, 30, Image.SCALE_DEFAULT)).getImage();
        // Draw the image at (x, y)
        AffineTransform trans = new AffineTransform();
        if(pair.getSticky()>0) // ragadós
        {
            g2d.setStroke(new BasicStroke(7));
            g2d.setColor(Color.white);
            g2d.draw(new Line2D.Double(n0x+15,n0y+15,n1x+15,n1y+15));
        }
        if(pair.IsSloppy()) // made by Bűn János
        {
            g2d.setStroke(new BasicStroke(6));
            g2d.setColor(Color.green);
            g2d.draw(new Line2D.Double(n0x+15,n0y+15,n1x+15,n1y+15));
        }
        // átszinezi ha törhetettlen
        if(pair.getUnBreakable()>0)
        {
            g2d.setStroke(new BasicStroke(5));
            g2d.setColor(Color.yellow);
            g2d.draw(new Line2D.Double(n0x+15,n0y+15,n1x+15,n1y+15));
        }
        g2d.setStroke(new BasicStroke(3));
        g2d.setColor(Color.black);
        g2d.draw(new Line2D.Double(n0x+15,n0y+15,n1x+15,n1y+15));
        double dirx = n1x - n0x;
        double diry = n1y - n0y;
        double dirlenght= sqrt(dirx*dirx+diry*diry);
        double normx=dirx*(1/dirlenght);
        double normy=diry*(1/dirlenght);
        x=(int)(n0x + normx * (dirlenght / 2) + 15);
        y=(int)(n0y + normy * (dirlenght / 2) + 15);
        if(pair.neighbours.get(0).getClass()== Pump.class) { // ha pumpa akkor az outpot fele nyomja a vizet
            Pump p = (Pump) pair.neighbours.get(0);

            if (pair.isHasWaterPartOne()) {
                g2d.setColor(Color.blue);

                if (p.getOut() == pair) { // lehet a 0 as szomszed az nem arra van csatlakoztatva kinezetben
                    g2d.draw(new Line2D.Double(n0x + 15, n0y + 15, n0x + normx * (dirlenght / 2) + 15, n0y + normy * (dirlenght / 2) + 15));
                } else {
                    g2d.draw(new Line2D.Double(n0x + normx * (dirlenght / 2) + 15, n0y + normy * (dirlenght / 2) + 15, n1x + 15, n1y + 15));
                }
            }
            if (pair.isHasWaterPartTwo()) {
                g2d.setColor(Color.blue);
                if (p.getOut() == pair) {
                    g2d.draw(new Line2D.Double(n0x + normx * (dirlenght / 2) + 15, n0y + normy * (dirlenght / 2) + 15, n1x + 15, n1y + 15));
                } else {
                    g2d.draw(new Line2D.Double(n0x + 15, n0y + 15, n0x + normx * (dirlenght / 2) + 15, n0y + normy * (dirlenght / 2) + 15));
                }
            }
        }else if(pair.neighbours.get(0).getClass()== Source.class)// a sourcnal csak leefle mehet es a ciszternel meg csak fentrol johet
        {
            if (pair.isHasWaterPartOne()) {
                g2d.setColor(Color.blue);

                    g2d.draw(new Line2D.Double(n0x + 15, n0y + 15, n0x + normx * (dirlenght / 2) + 15, n0y + normy * (dirlenght / 2) + 15));

            }
            if (pair.isHasWaterPartTwo()) {
                g2d.setColor(Color.blue);

                    g2d.draw(new Line2D.Double(n0x + normx * (dirlenght / 2) + 15, n0y + normy * (dirlenght / 2) + 15, n1x + 15, n1y + 15));

            }
        }else
        {
            if (pair.isHasWaterPartOne()) {
                g2d.setColor(Color.blue);


                g2d.draw(new Line2D.Double(n0x + normx * (dirlenght / 2) + 15, n0y + normy * (dirlenght / 2) + 15, n1x + 15, n1y + 15));
            }
            if (pair.isHasWaterPartTwo()) {
                g2d.setColor(Color.blue);

                g2d.draw(new Line2D.Double(n0x + 15, n0y + 15, n0x + normx * (dirlenght / 2) + 15, n0y + normy * (dirlenght / 2) + 15));

            }
        }

        if(pair.broken) // ha torott pirosra szinezi
        {
            g2d.setColor(Color.RED);

            g2d.draw(new Line2D.Double(n0x+15,n0y+15,n1x+15,n1y+15));

        }





        // Add text below the image
        String text = MessageFormat.format("{0}",pair.id);
        Font font = new Font("Arial", Font.PLAIN, 12);
        g2d.setFont(font);
        g2d.setColor(Color.BLACK);
        int textX = x;
        int textY = y + image.getHeight(this) -15 ; // Adjust the distance as needed
        g2d.drawString(text, textX, textY);
    }

}
