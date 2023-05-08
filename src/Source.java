import java.util.Scanner;

public class Source extends Component{
private int amountOfWater=0;

    public Source(String ID) {
        super(ID);
    }

    public void StartFlow(){
    }
    public int GetWater()
    {
        return amountOfWater;
    }
}
