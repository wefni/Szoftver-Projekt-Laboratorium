import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.*;
import java.util.Map;

public class ViewMap extends JPanel {

    /**
     * A játékban szereplő objektumokat tartalmazó lista.
     */
    private HashMap<Object, ViewObject> objects;

    /**
     * A játékban szereplő komponenseket tartalmazó lista.
     */
    private ArrayList<Component> components;

    /**
     * A játékban szereplő játékosokat tartalmazó lista.
     */
    private ArrayList<Player> players;
    protected static ViewMap viewmap;
    private Component pumpaddedpipe;

    private Component spawnedPipe;
    public ViewMap(ArrayList<Component> components1) {
        viewmap=this;
        components=components1;
        objects=new LinkedHashMap<>();



        AddComponents(components);
        SetCoords();


        //map picture is too big to fit in this frame, make it smaller
        this.setPreferredSize(new Dimension(600, 800));
        //this.setBackground(Color.WHITE);
    }

    /**
     * A komponens kérdezése.
     * @param c A komponens.
     * @return A komponenshez tartozó objektum.
     */
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

    }

    /**
     * A játékosok hozzáadása a pályához.
     * @param players A játékosok listája.
     */
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
        //this.setPreferredSize(new Dimension(600, 800));
        //this.setBackground(Color.WHITE);
    }

    /**
     * A pályán megjeelelő objektumok koordináátinak beállítása.
     */
    private void SetCoords(){
        Iterator<Map.Entry<Object, ViewObject>> it = objects.entrySet().iterator();
        Map.Entry<Object, ViewObject> pair = it.next();

        pair.getValue().SetCoords(180,90); // Source-0
        pair = it.next();
        pair.getValue().SetCoords(550,650); // Cistern-1
        pair = it.next();
        pair.getValue().SetCoords(640,585); // Cistern-2
        pair = it.next();
        pair.getValue().SetCoords(830,565); // Cistern-3
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

    /**
     * A pumpa hozzáadása a pályához.
     * @param pipe Cső
     */
    public void Pumpadded(Component pipe) // pumpa hozzaadasa
    {
        pumpaddedpipe= pipe;
        for(Component i: components)
        {
            if(objects.get(i)==null)
            {
                if(i.getClass()==Pump.class) {
                    objects.put(i, new ViewPump(objects.get(pumpaddedpipe).x,objects.get(pumpaddedpipe).y,(Pump) i));
                }
                else
                {
                    objects.put(i, new ViewPipe(0,0,(Pipe) i));
                }
            }
        }

    }
    public void PipeSpawned(Component pipe) // pipe spwanolása ciszternánál
    {
        spawnedPipe = pipe;
        for(Component i: components)
        {
            if(objects.get(i)==null)
            {
                if(i.getClass()==Pipe.class) {
                    if(pipe.neighbours.get(0) == null)
                            objects.put(i, new ViewPipe(getObjects(pipe.neighbours.get(1)).x-100,getObjects(pipe.neighbours.get(1)).y, (Pipe) i));
                    else
                        objects.put(i, new ViewPipe(getObjects(pipe.neighbours.get(0)).x-100,getObjects(pipe.neighbours.get(0)).y, (Pipe) i));
                }
            }
        }
    }
    /**
     * Kirajzolás.
     * @param g Grafikus objektum
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g; // cast to get 2D drawing methods

        ImageIcon im = new ImageIcon("src\\images\\sand4.jpg");
        Image image = im.getImage();

        //set image size
        image = new ImageIcon(image.getScaledInstance(900, 800, Image.SCALE_DEFAULT)).getImage();

        // Draw the image at (x, y)
        g2d.drawImage(image,0, 0, this);

        //iterate through the hashmap and repaint every object
        try {
            Iterator<Map.Entry<Object, ViewObject>> it = objects.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Object, ViewObject> pair = it.next();
                pair.getValue().Repaint(g);
            }
        }
        catch (Exception e) {
            System.out.println("");
            return;
        }
    }
}
