import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * A játék grafikus felületének főablaka.
 */

public class ViewFrame extends JFrame
{
    /**
     * A játékosok száma.
     */
    private static int jatekosok_sorszama = 1;

    /**
     * A jelenlegi kártya száma.
     */
    private static int currentCard = 1;

    /**
     * A kártyák közötti váltást végző objektum.
     */
    private static CardLayout cl;

    /**
     * A kártyák panelje.
     */
    static JPanel cardPanel = new JPanel();

    /**
     * A bal oldali panel.
     */
    private JPanel upLeftPanel;

    /**
     * A jobb oldali panel.
     */
    private JPanel upRightPanel;

    /**
     * A bal oldali szöveg.
     */
    private JLabel leftText;

    /**
     * A jobb oldali szöveg.
     */
    private JLabel rightText;

    /**
     * A menü.
     */
    private ViewMenu menu;

    /**
     * A játékmező.
     */
    private ViewField field;

    /**
     * A konstrukorban beállításra kerülnek a szükséges paraméterek,
     * valamint a kártyák paneljéhez hozzáadásra kerülnek a kártyák.
     * @param components A komponentek listája.
     */
    public ViewFrame(ArrayList<Component> components)
    {
        setTitle("Drukmákori Sivatag");
        setLayout(new BorderLayout());
        setSize(400, 300);


        //Panelek közötti váltás
        cl = new CardLayout();
        cardPanel.setLayout(cl);
        menu = new ViewMenu(jatekosok_sorszama);

        //Hozzáadja és beállítja a számát, amelyen meg fog jelenni
        cardPanel.add(menu, "1");
        field = new ViewField(components);
        cardPanel.add(field, "2");

        getContentPane().add(cardPanel, BorderLayout.CENTER);
        setVisible(true);
        //setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //UpPanel with point and actual player
        upLeftPanel = new JPanel();
        upRightPanel = new JPanel();

        leftText = new JLabel("");
        rightText = new JLabel("");

        // Hozzáadjuk a szövegeket az up panelhez
        upLeftPanel.add(leftText);
        upRightPanel.add(rightText);

        upLeftPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        upRightPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        upLeftPanel.setSize(600,100);
        upRightPanel.setSize(600,100);

        add(upLeftPanel,BorderLayout.NORTH);
        add(upRightPanel,BorderLayout.NORTH);
    }

    /**
     *
     * @param mWater
     * @param sWater
     * @param aktP
     */
    public void UpdateUpPanel(int mWater,int sWater,Player aktP)
    {
        // Inicializáljuk a bal és jobb szövegeket
        leftText.setText("Szabotőrök pontszáma: "+sWater+"  Szerelők pontszáma: "+mWater);
        rightText.setText("Aktuális játékos: "+aktP.name);
    }

    /**
     * A játékosok beállítása a field számára.
     * @param players A játékosok listája.
     */
    public void AddPlayers(ArrayList<Player> players)
    {
        field.SetPlayers(players);
    }

    /**
     * A "Nem" gombhoz tartozó listener. Ha felhasználó(k) nem szeretne több játékost már felvenni,
     * akkor a játék elindul.
     */
    static class Nev_Bekeres_Kesz_Listener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            //Ha kész a névbekérés, akkor a kettes panel fog megjelenni vagyis a pálya
            currentCard = 2;
            cl.show(cardPanel, "" + (currentCard));
            Map.map.SetIndul(true);
        }
    }

    /**
     * A "Igen" gombhoz tartozó listener. Ha felhasználó(k) szeretne több játékost felvenni,
     * akkor egy új névbekérő panel jelenik meg és ennek a segítségével meg tudja tenni.
     */
    static class Nev_Bekeres_Ujra_Listener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            jatekosok_sorszama += 4;
            JPanel bekeres_ujra = new ViewMenu(jatekosok_sorszama);

            cardPanel.add(bekeres_ujra, "20");
            currentCard = 20;
            cl.show(cardPanel, "" + (currentCard));
        }
    }

    /**
     * A játék végén a "Kilépés" gombhoz tartozó listener.
     * Ha a játék véget ér, akkor a Kilépés gombbal lehet kilépni.
     */
    static class Winner_Listener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.exit(10);
        }
    }

    /**
     * A játék végén a győztes csapat nevét megjeleníteni segítő függvény.
     * @param gyoztes
     */
    public void CreatWinnerCard(String gyoztes)
    {
        JPanel winner = new ViewWinner(gyoztes);
        cardPanel.add(winner, "3");
        currentCard = 3;
        cl.show(cardPanel, "" + (currentCard));

    }

    /**
     * A mechanic játékosok neveit adja vissza.
     * @return mechanic játékosok nevei.
     */
    public String[] Get_Mech_Names_From_Menu()
    {
        String[] mech_names = menu.Share_Mech_Names();
        return mech_names;
    }

    /**
     * A sabotour játékosok neveit adja vissza.
     * @return sabotour játékosok nevei.
     */
    public String[] Get_Sab_Names_From_Menu()
    {
        String[] sab_names = menu.Share_Sab_Names();
        return sab_names;
    }
    public void Pumpeadded(Component pipe)
    {
        field.Pumpadded(pipe);
    }
}
