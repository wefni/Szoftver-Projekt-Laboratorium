import javax.swing.*;
import java.awt.*;
import static java.lang.Math.abs;

/**
 * A menu megjelenítéséért felelős osztály, JPanelből származik le.
 */
public class ViewMenu extends JPanel
{
    /**
     * A mechanic játékosok neveit tároló tömb.
     */
    String[] mechanic_nevei = new String[50];

    /**
     * A saboteur játékosok neveit tároló tömb.
     */
    String[] saboteur_nevei = new String[50];

    /**
     * A mechanic játékosok számát tároló változó.
     */
    int mechanincok_szama = 0;

    /**
     * A saboteur játékosok számát tároló változó.
     */
    int saboteurok_szama = 0;

    /**
     * A konstruktorban beállításra kerrülnek a nevet bekérő mezők, a nevek rögzítésére szolgáló gomb, valamint
     * a "Szeretnél még játékost felvenni?" kérdés és a "Igen" és "Nem" gombok.
     * @param i A játékosok számát tároló változó.
     */
    public ViewMenu(int i)
    {
            //Nevek bekérése
            JLabel nev_keres_label_1 = new JLabel(i + ". Játékos : Mechanic\n  Név: ");
            i++;
            add(nev_keres_label_1);
            JTextField felhasz_nev_1 = new JTextField(20);
            add(felhasz_nev_1);

            JLabel nev_keres_label_2 = new JLabel(i + ". Játékos : Saboteur\n  Név: ");
            i++;
            add(nev_keres_label_2);
            JTextField felhasz_nev_2 = new JTextField(20);
            add(felhasz_nev_2);

            JLabel nev_keres_label_3 = new JLabel(i + ". Játékos : Mechanic\n  Név: ");
            i++;
            add(nev_keres_label_3);
            JTextField felhasz_nev_3 = new JTextField(20);
            add(felhasz_nev_3);

            JLabel nev_keres_label_4 = new JLabel(i + ". Játékos : Saboteur\n  Név: ");
            i++;
            add(nev_keres_label_4);
            JTextField felhasz_nev_4 = new JTextField(20);
            add(felhasz_nev_4);

            //Nevek rögztítése gomb
            JButton rogzites_gomb = new JButton("Nevek rögzítése");
            rogzites_gomb.setBackground(new Color(48, 253, 0));
            add(rogzites_gomb);

            JLabel kerdes_label = new JLabel("Szeretnél még játékost felvenni?");
            add(kerdes_label);

            //Igen és Nem gombok
            JButton igen_gomb = new JButton("Igen");
            igen_gomb.setBackground(new Color(253, 173, 0));
            JButton nem_gomb = new JButton("Nem");
            nem_gomb.setBackground(new Color(0, 253, 245));

            JPanel menu_panel = new JPanel();
            menu_panel.add(igen_gomb);
            menu_panel.add(nem_gomb);
            igen_gomb.setVisible(false);
            nem_gomb.setVisible(false);
            kerdes_label.setVisible(false);

            int finalI = i;
        rogzites_gomb.addActionListener(actionEvent ->
            {
                int ures_nevek = 0;
                if(finalI < 6) //elso korben legalabb negy jatekos kell
                {
                    String[] nevek = new String[4];
                    nevek[0] = felhasz_nev_1.getText();
                    nevek[1] = felhasz_nev_2.getText();
                    nevek[2] = felhasz_nev_3.getText();
                    nevek[3] = felhasz_nev_4.getText();
                    for (int j = 0; j < 4; j++)
                    {
                        if(nevek[j].equals(""))
                        {
                            ures_nevek++;
                        }
                    }
                    if(ures_nevek == 0)
                    {
                        igen_gomb.setVisible(true);
                        nem_gomb.setVisible(true);
                        kerdes_label.setVisible(true);

                        //Karakterek neveinek beállítása
                        for (int j = 0; j < 4; j++)
                        {
                            if(j % 2 == 0)
                            {
                                mechanic_nevei[mechanincok_szama++] = nevek[j];
                            }
                            else
                            {
                                saboteur_nevei[saboteurok_szama++] = nevek[j];
                            }
                        }
                    }
                }
                else //masodik kortol kezdve maximum eggyel tobb karakter lehet, mint a masik
                {
                    int szab_szama = 0;
                    int mech_szama = 0;
                    String[] nevek = new String[4];
                    nevek[0] = felhasz_nev_1.getText();
                    nevek[1] = felhasz_nev_2.getText();
                    nevek[2] = felhasz_nev_3.getText();
                    nevek[3] = felhasz_nev_4.getText();
                    for (int j = 0; j < 4; j++)
                    {
                        if(!nevek[j].equals(""))
                        {
                            if(j % 2 == 0)
                            {
                                mech_szama++;
                            }
                            else
                            {
                                szab_szama++;
                            }
                        }
                        else
                        {
                            ures_nevek++;
                        }
                    }
                    if(ures_nevek > -1)
                    {
                        if(abs(mech_szama - szab_szama) < 2) //ha megfelelo a szamuk: megjelennek a gombok es lerakjuk oket
                        {
                            igen_gomb.setVisible(true);
                            nem_gomb.setVisible(true);
                            kerdes_label.setVisible(true);

                            //lerakas
                            for (int j = 0; j < 4; j++)
                            {
                                if(!nevek[j].equals(""))
                                {
                                    if(j % 2 == 0)
                                    {
                                        mechanic_nevei[mechanincok_szama++] = nevek[j];
                                    }
                                    else
                                    {
                                        saboteur_nevei[saboteurok_szama++] = nevek[j];
                                    }
                                }
                            }
                        }
                    }
                }
            });

            add(menu_panel);
            igen_gomb.addActionListener(new ViewFrame.Nev_Bekeres_Ujra_Listener());
            nem_gomb.addActionListener(new ViewFrame.Nev_Bekeres_Kesz_Listener());
    }

    /**
     * A mechanic jatekosok neveinek lekerdezese
     * @return a mechanic jatekosok nevei
     */
    public String[] Share_Mech_Names()
    {
        return mechanic_nevei;
    }

    /**
     * A saboteur jatekosok neveinek lekerdezese
     * @return a saboteur jatekosok nevei
     */
    public String[] Share_Sab_Names()
    {
        return saboteur_nevei;
    }

}
