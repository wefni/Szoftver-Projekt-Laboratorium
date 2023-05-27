import java.util.Scanner;

public abstract class Breakable extends Component{
protected boolean broken;

    public Breakable(String ID) {
        super(ID);
    }

    public void Break()
    {
        broken = true;
    }
    public void Repair()
    {
        broken = false;
    }
    public int FlowOut(){
        return 0;
    }
}
