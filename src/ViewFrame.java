import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class ViewFrame extends JFrame{
    private static int i = 1;

    private static int currentCard = 1;
    private static CardLayout cl;
    static JPanel cardPanel = new JPanel();

    public ViewFrame(ArrayList<Component> components)
    {
        setTitle("Drukmákori Sivatag");
        setLayout(new BorderLayout());
        setSize(400, 400);


        //Panelek közötti váltás
        cl = new CardLayout();
        cardPanel.setLayout(cl);
        JPanel menu = new ViewMenu(i);

        //Hozzáadja és beállítja a számát, amelyen meg fog jelenni
        cardPanel.add(menu, "1");
        JPanel palya = new ViewField(components);
        cardPanel.add(palya, "2");

        //cardPanel.add(ranglista, "3");

        getContentPane().add(cardPanel, BorderLayout.CENTER);
        setVisible(true);
        //setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /*public ViewFrame(ArrayList<Component> components)
    {

    }*/

    static class Nev_Bekeres_Kesz_Listener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String igen = "nem";
            InputStream In = new ByteArrayInputStream(igen.getBytes());
            System.setIn(In);

            //Ha kész a névbekérés, akkor a kettes panel fog megjelenni vagyis a pálya
            currentCard = 2;
            cl.show(cardPanel, "" + (currentCard));
        }
    }
    static class Nev_Bekeres_Ujra_Listener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String igen = "igen";
            InputStream In = new ByteArrayInputStream(igen.getBytes());
            System.setIn(In);

            i += 4;
            JPanel bekeres_ujra = new ViewMenu(i);

            cardPanel.add(bekeres_ujra, "20");
            currentCard = 20;
            cl.show(cardPanel, "" + (currentCard));
        }
    }
}
