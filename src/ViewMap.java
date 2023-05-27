import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ViewMap extends JPanel {

    private HashMap<Object, ViewObject> objects;
    private ArrayList<Player> players;
    protected static ViewMap viewmap;
    public ViewMap(ArrayList<Component> components) {
        viewmap=this;
        objects=new HashMap<>();
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
                ViewPump viewPump = new ViewPump(i * 10, i * 10, (Pump) components.get(i));
                objects.put(components.get(i), viewPump);
                System.out.println(components.get(i).id);
            }
            if (components.get(i).getClass() == Pipe.class) {
                ViewPipe viewPipe = new ViewPipe(i * 10, i * 10, (Pipe) components.get(i));
                objects.put(components.get(i), viewPipe);
            }
            if (components.get(i).getClass() == Cistern.class) {
                ViewCistern viewCistern = new ViewCistern(i *10, i * 10, (Cistern) components.get(i));
                objects.put(components.get(i), viewCistern);
            }
            if (components.get(i).getClass() == Source.class) {
                ViewSource viewSource = new ViewSource(i * 10, i * 10, (Source) components.get(i));
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

        public void AddPlayers(ArrayList<Player> players){
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
        //first is the Source
      /*  pair.getValue().SetCoords(20, 200);
        //there are 3 Cisterns
        pair = it.next();
        pair.getValue().SetCoords(400, 500);
        pair = it.next();
        pair.getValue().SetCoords(350, 500);
        pair = it.next();
        pair.getValue().SetCoords(300, 500);
       */ //from here on there are the Pipes
        pair = it.next();
        pair.getValue().SetCoords(200, 500);
        pair = it.next();
        pair.getValue().SetCoords(0, 0);
        pair = it.next();
        pair.getValue().SetCoords(0, 0);
        pair = it.next();
        pair.getValue().SetCoords(0, 0);
        pair = it.next();
        pair.getValue().SetCoords(0, 0);
        pair = it.next();
        pair.getValue().SetCoords(0, 0);
        pair = it.next();
     /*   //10th done!
        pair.getValue().SetCoords(150, 500);
        pair = it.next();
        pair.getValue().SetCoords(0, 0);
        pair = it.next();
        pair.getValue().SetCoords(0, 0);
        pair = it.next();
        pair.getValue().SetCoords(0, 0);
        pair = it.next();
        pair.getValue().SetCoords(0, 0);
        pair = it.next();
        pair.getValue().SetCoords(0, 0);
        pair = it.next();
        pair.getValue().SetCoords(0, 0);
        pair = it.next();
        pair.getValue().SetCoords(0, 0);
        pair = it.next();
        pair.getValue().SetCoords(0, 0);
        pair = it.next();
        pair.getValue().SetCoords(0, 0);
        pair = it.next();
        //20th done!
        pair.getValue().SetCoords(0, 0);
        pair = it.next();
        pair.getValue().SetCoords(0, 0);
        pair = it.next();
        pair.getValue().SetCoords(0, 0);
        pair = it.next();
        pair.getValue().SetCoords(0, 0);
        pair = it.next();
        pair.getValue().SetCoords(0, 0);
        pair = it.next();
        pair.getValue().SetCoords(0, 0);
        pair = it.next();
        pair.getValue().SetCoords(0, 0);
        pair = it.next();
        pair.getValue().SetCoords(0, 0);
        pair = it.next();
        pair.getValue().SetCoords(0, 0);
        pair = it.next();
        //Pump from here
        pair.getValue().SetCoords(120, 90);
        pair = it.next();
        //30th
        pair.getValue().SetCoords(500, 34);
        pair = it.next();
        pair.getValue().SetCoords(400, 80);
        pair = it.next();
        pair.getValue().SetCoords(60, 450);
        pair = it.next();
        pair.getValue().SetCoords(98, 300);
        pair = it.next();
        pair.getValue().SetCoords(0, 0);
        pair = it.next();
        pair.getValue().SetCoords(0, 0);
        pair = it.next();
        pair.getValue().SetCoords(0, 0);
        pair = it.next();
        pair.getValue().SetCoords(0, 0);
        pair = it.next();
        pair.getValue().SetCoords(0, 0);
        pair = it.next();
        pair.getValue().SetCoords(0, 0);
        pair = it.next();
        //40th
        pair.getValue().SetCoords(0, 0);
        pair = it.next();
        pair.getValue().SetCoords(0, 0);
        pair = it.next();
        pair.getValue().SetCoords(0, 0);
        pair = it.next();*/
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
