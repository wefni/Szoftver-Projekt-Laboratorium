import javax.swing.*;
import java.awt.*;
import java.io.*;

import static java.lang.Math.abs;

public class ViewMenu extends JPanel
{
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
                            InputStream In = new ByteArrayInputStream(nevek[j].getBytes());
                            System.setIn(In);

                            if(j % 2 == 0)
                            {
                                //SetPlayer(nevek[j]);
                            }
                            else
                            {
//                            ViewFrame.jatekosok[j].setNev(nevek[j]);
//                            ViewFrame.jatekosok[j].setKarakter("Saboteur");
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
                    if(ures_nevek > 0)
                    {
                        if(abs(mech_szama - szab_szama) < 2) //ha megfelelo a szamuk: megjelennek a gombok es lerakjuk oket
                        {
                            igen_gomb.setVisible(true);
                            nem_gomb.setVisible(true);
                            kerdes_label.setVisible(true);

                            //lerakas
                            for (int j = 0; j < 4; j++)
                            {
                                InputStream In = new ByteArrayInputStream(nevek[j].getBytes());
                                System.setIn(In);
                                if(!nevek[j].equals(""))
                                {
                                    if(j % 2 == 0)
                                    {
                                        //SetPlayer(nevek[j]);
                                    }
                                    else
                                    {
        //                            ViewFrame.jatekosok[j].setNev(nevek[j]);
        //                            ViewFrame.jatekosok[j].setKarakter("Saboteur");
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
}
