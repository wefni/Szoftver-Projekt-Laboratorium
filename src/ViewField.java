import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ViewField extends JPanel implements ActionListener
{
    private JLabel questionLabel;
    private JPanel buttonPanel;
    private JButton[] buttons;
    private ViewMap viewmap;
    private JPanel rightPanel;
    private static int i = 1;
    private final Object lock = new Object();
    private int buttonPressed = -1;
    public static ViewField instance = null;

    public ViewField(ArrayList<Component> components) {

        instance = this;

        // Left panel with PNG image

        viewmap = new ViewMap(components);
        viewmap.setPreferredSize(new Dimension(900, 800));

      

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
        add(viewmap, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
    }
    public void WriteQuestion(String question)
    {
        questionLabel.setText(question);
        repaint();
    }

    public void AddPlayers(ArrayList<Player> players)
    {
        viewmap.AddPlayers(players);
    }
    public String WriteOptions(String[] options)
    {
        buttonPanel.removeAll();
        buttonPanel.setLayout(new GridLayout(options.length, 1));
        int j = 0;
        for(int i = 0; i < options.length; i++)
        {
            if(options[i] != null && options[i] != "") {
                buttons[j].setText(options[i]);
                buttons[j].addActionListener(this);
                buttonPanel.add(buttons[j++]);
            }
        }
        repaint();
        synchronized (lock)
        {
            try
            {
                lock.wait();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        return buttons[buttonPressed].getText();
    }

    public void actionPerformed(ActionEvent e)
    {
        for(int i = 0; i < buttons.length; i++)
        {
            if(e.getSource() == buttons[i])
            {
                buttonPressed = i;
                synchronized (lock)
                {
                    lock.notify();
                }
            }
        }
    }
    public void InitiatePainting()
    {
        viewmap.repaint();
    }


    public void SetPlayers(ArrayList<Player> players)
    {
        //viewmap.AddPlayers(players);
    }
}
