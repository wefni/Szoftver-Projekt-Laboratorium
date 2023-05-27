import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class ViewField extends JPanel
{
    private JLabel questionLabel;
    private JPanel buttonPanel;
    private JButton[] buttons;
    private ViewMap map;
    private JPanel rightPanel;
    private static int i = 1;

    public ViewField(ArrayList<Component> components) {


        // Left panel with PNG image
        map = new ViewMap(components);
        map.setPreferredSize(new Dimension(900, 800));

        // Right panel with question and buttons
        rightPanel = new JPanel(new BorderLayout());
        questionLabel = new JLabel("Üdv a Drukmákori Sivatagban!");
        questionLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        rightPanel.add(questionLabel, BorderLayout.NORTH);

        // Buttons
        buttons = new JButton[100];

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(10, 1));
        for(int i = 0; i < 10; i++)
        {
            buttons[i] = new JButton("Button " + i);
            buttonPanel.add(buttons[i]);
        }

        rightPanel.add(buttonPanel, BorderLayout.CENTER);


        // Add left and right panels to the frame
        add(map, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
    }
    public void WriteQuestion(String question)
    {
        questionLabel.setText(question);
        repaint();
    }

    public void AddPlayers(ArrayList<Player> players)
    {
        map.AddPlayers(players);
    }
    public void WriteOptions(String[] options)
    {
        buttonPanel.removeAll();
        buttonPanel.setLayout(new GridLayout(options.length, 1));
        for(int i = 0; i < options.length; i++)
        {
            buttons[i].setText(options[i]);
            //make buttons refresh on JFrame

            buttonPanel.add(buttons[i]);
        }
        repaint();
    }
    public void InitiatePainting()
    {
        map.repaint();
    }
}
