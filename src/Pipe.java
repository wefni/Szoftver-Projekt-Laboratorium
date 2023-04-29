import java.util.Objects;
import java.util.Scanner;

public class Pipe extends Breakable{
private double capacity;
private int sticky;
private int unBreakable;
private int sloppy;
private int hasWater;
private boolean hasWaterPartOne;
private boolean hasWaterPartTwo;

    public Pipe(String ID, String logFILE) {
        super(ID, logFILE);
    }

    public boolean Accept()
{
    return onComponent.isEmpty();
}

    public void ChangePipe()
    {

    }
    public void PlacePump(){
        System.out.print("$ Pipe.PlacePump()");
    }
    public int FlowOut(){
        return 0;
    }

    public void Act(){
    }

    public void AddNeighbours(){
    }

    public void Step(){
    }

    public void RemovePlayer(){
    }
    public Component RandomEnd(){return null;}
    public void MakeSloppy(){}
    public void MakeSticky(){}
    public void Tick(){}
    public boolean IsSloppy()
    {
        return sloppy>0;
    }
    public int GetWater()
    {
        if(hasWaterPartOne && hasWaterPartTwo)
            return 2;
        if(hasWaterPartOne || hasWaterPartTwo)
            return 1;

        return 0;
    }

}
