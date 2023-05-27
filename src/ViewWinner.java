import javax.swing.*;
import java.awt.*;
import java.io.*;
public class ViewWinner extends JPanel
{
    public ViewWinner (String gyoztes)
    {
        JLabel nyertes_csapat = new JLabel(gyoztes);
        add(nyertes_csapat);

        JButton kilepes_gomb = new JButton("Kilépés");
        kilepes_gomb.setBackground(new Color(253, 0, 0));
        kilepes_gomb.addActionListener(new ViewFrame.Winner_Listener());
        add(kilepes_gomb);
    }
}
