import javax.swing.*;
import java.awt.*;
import java.text.MessageFormat;
import java.util.HashMap;

public class ViewPipe extends ViewObject{
    Pipe pair;
    public int dirx;
    int diry;
    public ViewPipe(int _x, int _y, Pipe _pair) {
        this.x = _x;
        this.y = _y;
        this.pair = _pair;

    }
    void Direction()
    {
        // az iranyhoz kell
        System.out.println(ViewMap.viewmap.getObjects(pair.neighbours.get(1)));
        int n0x=ViewMap.viewmap.getObjects(pair.neighbours.get(0)).x;// masodik szomszed x-je
        int n0y=ViewMap.viewmap.getObjects(pair.neighbours.get(0)).y;// masodik szomszed y-je
        int n1x=ViewMap.viewmap.getObjects(pair.neighbours.get(1)).x; // elso szomszed x-je
        int n1y=ViewMap.viewmap.getObjects(pair.neighbours.get(1)).y; // elso szomszed y-je
        int dirx=n1x-n0x;
        int diry=n1y-n0y;
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
        if(pair.broken && pair.IsSloppy() && pair.getSticky()>0 && pair.isHasWaterPartOne() && pair.isHasWaterPartTwo() && pair.getUnBreakable()==0) // minden hatas rajta kiveve torhetetlen full viz
        {
            Image image = new ImageIcon("src\\images\\pipe.png").getImage();
        }
        if(pair.broken && pair.IsSloppy() && pair.getSticky()>0 && pair.isHasWaterPartOne() && !pair.isHasWaterPartTwo() && pair.getUnBreakable()==0) // minden hatas kiveve torhetetlen es csak az elsoben viz
        {
            Image image = new ImageIcon("src\\images\\pipe.png").getImage();
        }
        if(pair.broken && pair.IsSloppy() && pair.getSticky()>0 && !pair.isHasWaterPartOne() && !pair.isHasWaterPartTwo() && pair.getUnBreakable()==0) // minden hatas kiveve torhetetlen es nincs viz
        {
            Image image = new ImageIcon("src\\images\\pipe.png").getImage();
        }
        if(pair.broken && pair.IsSloppy() && pair.getSticky()==0 && !pair.isHasWaterPartOne() && !pair.isHasWaterPartTwo() && pair.getUnBreakable()==0) // sloppy es tört nincs viz
        {
            Image image = new ImageIcon("src\\images\\pipe.png").getImage();
        }
        if(pair.broken && !pair.IsSloppy() && pair.getSticky()==0 && !pair.isHasWaterPartOne() && !pair.isHasWaterPartTwo() && pair.getUnBreakable()==0) // csak tört nincs viz
        {
            Image image = new ImageIcon("src\\images\\pipe.png").getImage();
        }
        if(!pair.broken && !pair.IsSloppy() && pair.getSticky()==0 && !pair.isHasWaterPartOne() && !pair.isHasWaterPartTwo() && pair.getUnBreakable()==0) // semmilyen hatas nincs viz
        {
            Image image = new ImageIcon("src\\images\\pipe.png").getImage();
        }
        if(!pair.broken && !pair.IsSloppy() && pair.getSticky()==0 && !pair.isHasWaterPartOne() && !pair.isHasWaterPartTwo() && pair.getUnBreakable()>0) // csak törhetettlen hatas
        {
            Image image = new ImageIcon("src\\images\\pipe.png").getImage();
        }

        if(!pair.broken && !pair.IsSloppy() && pair.getSticky()==0 && !pair.isHasWaterPartOne() && !pair.isHasWaterPartTwo() && pair.getUnBreakable()>0) 
        {
            Image image = new ImageIcon("src\\images\\pipe.png").getImage();
        }
        // Load the image
        Image image = new ImageIcon("src\\images\\pipe.png").getImage();
        //set image size
        image = new ImageIcon(image.getScaledInstance(30, 30, Image.SCALE_DEFAULT)).getImage();
        //g2d.rotate(100);
        // Draw the image at (x, y)
        g2d.drawImage(image, x, y, this);



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
