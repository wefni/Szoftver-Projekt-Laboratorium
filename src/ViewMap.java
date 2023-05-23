import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ViewMap extends JPanel {

    private ArrayList<ViewObject> objects;
    public ViewMap() {
        objects = new ArrayList<>();
        objects.add(new ViewPump(40,40));
        objects.add(new ViewPump(120,140));
        objects.add(new ViewPump(330,420));
        objects.add(new ViewPump(669,200));
        objects.add(new ViewPump(90,500));
        //map picture is too big to fit in this frame, make is smaller
        this.setPreferredSize(new Dimension(600, 800));
        this.setBackground(Color.WHITE);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(ViewObject object : objects)
        {
            object.Repaint(g);
        }
    }
}
