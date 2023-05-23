import javax.swing.*;
import java.awt.*;

public abstract class ViewObject extends JComponent {
    int x, y;
    public void Repaint(Graphics g) {
        this.paintComponent(g);
    }


}
