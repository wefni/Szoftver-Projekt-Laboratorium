import java.util.Scanner;

public abstract class Component implements Flow{

    public void Act(){}
    public boolean Accept(){return false;}
    public void RemovePlayer(){}
    public void AddPlayer(){}
    public Component[] ShowNeighbours(){return null;}
    public void AddNeighbours(){
        /*System.out.println("Melyik objektumot szeretnéd hozzáadni?");
        Scanner be=new Scanner(System.in);
        String info=be.nextLine();*/

    }
    public void RemoveNeighbours(){}
    public void Step(){}
    public int FlowOut(){return 0;}
}
