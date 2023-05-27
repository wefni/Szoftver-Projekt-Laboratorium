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
        Image image = new ImageIcon("src\\images\\pipe.png").getImage();
        //set image size
        image = new ImageIcon(image.getScaledInstance(30, 30, Image.SCALE_DEFAULT)).getImage();
        // Draw the image at (x, y)
        AffineTransform trans = new AffineTransform();
       // int px=30;
        /*int py=30;
        int dirx;
        int diry;
        if(n0y>n1y) {
            dirx = n0x - n1x;
            diry = n0y - n1y;

        }
        else
        {
             dirx = n1x - n0x;
             diry = n1y - n0y;
        }
        trans.translate(x,y);
       /* double dirlenght= sqrt(dirx*dirx+diry*diry);
        double pipedirlenght= sqrt(30*30+30*30);
        double normx=dirx*(1/dirlenght);
        double normy=diry*(1/dirlenght);
        double normpipex=px*(1/pipedirlenght);
        double normpipey=py*(1/pipedirlenght);
        double degree=normx*normpipex+normy*normpipey;*/
        //System.out.println(degree);
       // trans.rotate(Math.toRadians(degree),15,15);
        //trans.translate(n0x+10,n0y+10);
        //g2d.drawImage(image, trans, this);
        //g2d.drawLine(n0x,n0y,n1x,n1y);
        //g2d.fillRect(n0x,n0y,n1x,n1y);
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
        }else // a sourcnal csak leefle mehet es a ciszternel meg csak fentrol johet
        {
            if (pair.isHasWaterPartOne()) {
                g2d.setColor(Color.blue);

                if (n0y<=n1y) {
                    g2d.draw(new Line2D.Double(n0x + 15, n0y + 15, n0x + normx * (dirlenght / 2) + 15, n0y + normy * (dirlenght / 2) + 15));
                } else {
                    g2d.draw(new Line2D.Double(n0x + normx * (dirlenght / 2) + 15, n0y + normy * (dirlenght / 2) + 15, n1x + 15, n1y + 15));
                }
            }
            if (pair.isHasWaterPartTwo()) {
                g2d.setColor(Color.blue);
                if (n0y<=n1y) {
                    g2d.draw(new Line2D.Double(n0x + normx * (dirlenght / 2) + 15, n0y + normy * (dirlenght / 2) + 15, n1x + 15, n1y + 15));
                } else {
                    g2d.draw(new Line2D.Double(n0x + 15, n0y + 15, n0x + normx * (dirlenght / 2) + 15, n0y + normy * (dirlenght / 2) + 15));
                }
            }
        }
        if(pair.broken)
        {
            g2d.setColor(Color.RED);

            g2d.draw(new Line2D.Double(n0x+15,n0y+15,n1x+15,n1y+15));

        }



        // Add text below the image
        String text = MessageFormat.format("{0}: {1}, {2}",pair.id,x,y);
        Font font = new Font("Arial", Font.PLAIN, 12);
        g2d.setFont(font);
        g2d.setColor(Color.BLACK);
        int textX = x;
        int textY = y + image.getHeight(this) + 15; // Adjust the distance as needed
        g2d.drawString(text, textX, textY);
    }

}
