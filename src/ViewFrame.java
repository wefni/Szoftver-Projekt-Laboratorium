import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ViewFrame extends JFrame{
    private JLabel questionLabel;
    private JPanel buttonPanel;
    private JButton[] buttons;
    private ViewMap map;
    private JPanel rightPanel;

    public ViewFrame(ArrayList<Component> components) {
        setTitle("Drukmákori Sivatag");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(1200, 800);

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

    public void AddPlayers(ArrayList<Player> players){
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
