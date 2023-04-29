import java.util.Scanner;

public class Source extends Component{
private int amountOfWater=0;
    public void StartFlow(){
        System.out.println("$ Source.StartFlow()");

        Pipe pipe1=new Pipe();
        Scanner be=new Scanner(System.in);
        System.out.println("El van törve a következő cső?");
        String valasztas=be.nextLine();
        if("nem".equals(valasztas))
        {
            System.out.print("\t\t\t\t pipe1 ");
            pipe1.FlowOut();
        }
    }
    public int GetWater()
    {
        return amountOfWater;
    }
}
