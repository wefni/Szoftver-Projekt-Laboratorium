import java.util.Scanner;

public abstract class Component implements Flow{

    public void Act(){
        System.out.println("$ Component.Act()");
    }
    public boolean Accept(){
        System.out.print("$ Component.Accept()");
        return false;
    }
    public void RemovePlayer(){
        System.out.print("$ Component.RemovePlayer()");
    }
    public void AddPlayer(){
        System.out.print("$ Component.AddPlayer()");
    }
    public Component[] ShowNeighbours(){
        System.out.print("$ Component.ShowNeighbors()");
        return null;
    }
    public void AddNeighbours(){
        System.out.print("$ Component.AddNeighbours()");
        /*System.out.println("Melyik objektumot szeretnéd hozzáadni?");
        Scanner be=new Scanner(System.in);
        String info=be.nextLine();*/

    }
    public void RemoveNeighbours(){
        System.out.print("$ Component.RemoveNeighbours()");
    }
    public void Step(){
        System.out.print("$ Component.Step()");
    }
    public int FlowOut(){
        System.out.print("$ Component.FlowOut()");
        return 0;
    }
}
