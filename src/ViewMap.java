import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.Map;

public class ViewMap extends JPanel {

    private HashMap<Object, ViewObject> objects;
    private ArrayList<Player> players;
    protected static ViewMap viewmap;
    public ViewMap(ArrayList<Component> components) {
        viewmap=this;
        objects=new LinkedHashMap<>();
        AddComponents(components);

        SetCoords();

        //map picture is too big to fit in this frame, make it smaller
        this.setPreferredSize(new Dimension(600, 800));
        this.setBackground(Color.WHITE);

    }

    public void SetPlayer(Player player)
    {
       players.add(player);
    }

    public ViewObject getObjects(Component c) {
        return objects.get(c);
    }
    public void AddComponents(ArrayList<Component> components) {
        for (int i = 0; i < components.size(); i++) {
            if (components.get(i).getClass() == Pump.class) {
                ViewPump viewPump = new ViewPump(0, 0, (Pump) components.get(i));
                objects.put(components.get(i), viewPump);
                System.out.println(components.get(i).id);
            }
            if (components.get(i).getClass() == Pipe.class) {
                ViewPipe viewPipe = new ViewPipe(0, 0, (Pipe) components.get(i));
                objects.put(components.get(i), viewPipe);
            }
            if (components.get(i).getClass() == Cistern.class) {
                ViewCistern viewCistern = new ViewCistern(0, 0, (Cistern) components.get(i));
                objects.put(components.get(i), viewCistern);
            }
            if (components.get(i).getClass() == Source.class) {
                ViewSource viewSource = new ViewSource(0, 0, (Source) components.get(i));
                objects.put(components.get(i), viewSource);
            }
        }
        // az iranyhoz kell
        for (Component i: components)
        {
            if(i.getClass() == Pipe.class)
            {
                ViewPipe p=(ViewPipe) objects.get(i);
                p.Direction();
            }
        }
    }
    public void AddPipe(Pipe pipe) {
        ViewPipe viewPipe = new ViewPipe(0, 0, pipe);
        objects.put(pipe, viewPipe);
    }

    public void AddPump(Pump pump, Component neighbour1, Component neighbour2) {
        int x = 0, y=0;
        x += objects.get(neighbour1).getX();
        y += objects.get(neighbour1).getY();
        if(neighbour2 != null){
            x += objects.get(neighbour2).getX();
            y += objects.get(neighbour2).getY();
        }
        else {
            //random sivatagi koordinatak
            x += 600;
            y += 555;
        }

        x = (int)x/2;
        y = (int)y/2;
        ViewPump viewPump = new ViewPump(x, y, pump);
        objects.put(pump, viewPump);
    }

        public void AddPlayers(ArrayList<Player> players)
        {
            for(int i = 0; i< players.size(); i++){
                if(players.get(i).getClass() == Saboteur.class){
                    //Saboteur-t a Source-ra kell rakni!
                    ViewSaboteur viewSaboteur = new ViewSaboteur(i*20, i*20, (Saboteur) players.get(i));
                    objects.put(players.get(i), viewSaboteur);
                }
                //Mechanic-ot a Cistern-re kell rakni!
                if(players.get(i).getClass() == Mechanic.class){
                    ViewMechanic viewMechanic = new ViewMechanic(i*20, i*20, (Mechanic) players.get(i));
                    objects.put(players.get(i), viewMechanic);
                }
        }
        this.setPreferredSize(new Dimension(600, 800));
        this.setBackground(Color.WHITE);
    }

    //itt kellene a koordinátákat bele hardCode-olni a pályába, eleg fájdalmas
    private void SetCoords(){
        Iterator<Map.Entry<Object, ViewObject>> it = objects.entrySet().iterator();
        Map.Entry<Object, ViewObject> pair = it.next();

        pair.getValue().SetCoords(100,50); // Source-0
        pair = it.next();
        pair.getValue().SetCoords(550,650); // Cistern-1
        pair = it.next();
        pair.getValue().SetCoords(650,600); // Cistern-2
        pair = it.next();
        pair.getValue().SetCoords(800,600); // Cistern-3
        pair = it.next();
        pair.getValue().SetCoords(800,550); // Pipe-4
        pair = it.next();
        pair.getValue().SetCoords(700,550); // Pipe-5
        pair = it.next();
        pair.getValue().SetCoords(575,550); // Pipe-6
        pair = it.next();
        pair.getValue().SetCoords(400,600); // Pipe-7
        pair = it.next();
        pair.getValue().SetCoords(400,650); // Pipe-8
        pair = it.next();
        pair.getValue().SetCoords(100,600); // Pipe-9
        pair = it.next();
        pair.getValue().SetCoords(200,525); // Pipe-10
        pair = it.next();
        pair.getValue().SetCoords(300,500); // Pipe-11
        pair = it.next();
        pair.getValue().SetCoords(450,500); // Pipe-12
        pair = it.next();
        pair.getValue().SetCoords(600,450); // Pipe-13
        pair = it.next();
        pair.getValue().SetCoords(450,300); // Pipe-14
        pair = it.next();
        pair.getValue().SetCoords(700,450); // Pipe-15
        pair = it.next();
        pair.getValue().SetCoords(800,400); // Pipe-16
        pair = it.next();
        pair.getValue().SetCoords(800,200); // Pipe-17
        pair = it.next();
        pair.getValue().SetCoords(700,250); // Pipe-18
        pair = it.next();
        pair.getValue().SetCoords(650,150); // Pipe-19
        pair = it.next();
        pair.getValue().SetCoords(650,250); // Pipe-20
        pair = it.next();
        pair.getValue().SetCoords(450,100); // Pipe-21
        pair = it.next();
        pair.getValue().SetCoords(200,50); // Pipe-22
        pair = it.next();
        pair.getValue().SetCoords(325,200); // Pipe-23
        pair = it.next();
        pair.getValue().SetCoords(300,325); // Pipe-24
        pair = it.next();
        pair.getValue().SetCoords(200,300); // Pipe-25
        pair = it.next();
        pair.getValue().SetCoords(200,400); // Pipe-26
        pair = it.next();
        pair.getValue().SetCoords(75,300); // Pipe-27
        pair = it.next();
        pair.getValue().SetCoords(200,150); // Pipe-28
        pair = it.next();
        pair.getValue().SetCoords(75,100); // Pipe-29
        pair = it.next();
        pair.getValue().SetCoords(50,200); // Pump-30
        pair = it.next();
        pair.getValue().SetCoords(400,150); // Pump-31
        pair = it.next();
        pair.getValue().SetCoords(300,250); // Pump-32
        pair = it.next();
        pair.getValue().SetCoords(450,50); // Pump-33
        pair = it.next();
        pair.getValue().SetCoords(700,200); // Pump-34
        pair = it.next();
        pair.getValue().SetCoords(600,375); // Pump-35
        pair = it.next();
        pair.getValue().SetCoords(800,300); // Pump-36
        pair = it.next();
        pair.getValue().SetCoords(800,500); // Pump-37
        pair = it.next();
        pair.getValue().SetCoords(550,500); // Pump-38
        pair = it.next();
        pair.getValue().SetCoords(275,550); // Pump-39
        pair = it.next();
        pair.getValue().SetCoords(150,650); // Pump-40
        pair = it.next();
        pair.getValue().SetCoords(50,500); // Pump-41
        pair = it.next();
        pair.getValue().SetCoords(100,400); // Pump-42
        pair = it.next();
        pair.getValue().SetCoords(300,450); // Pump-43
        

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //iterate through the hashmap and repaint every object
        Iterator<Map.Entry<Object, ViewObject>> it = objects.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<Object, ViewObject> pair = it.next();
            pair.getValue().Repaint(g);
        }
    }
}
