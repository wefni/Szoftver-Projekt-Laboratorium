import javax.swing.*;
import java.awt.*;

public abstract class ViewObject extends JComponent {
    int x, y;
    public void Repaint(Graphics g) {
        this.paintComponent(g);
    }

    public void SetCoords(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
