import java.util.Scanner;

public class Pump extends RandomBreakable{

    public void ConfigurePump()
    {
        System.out.print("$ Pump.ConfigurePump()\n");

        Pump pump = new Pump();

        System.out.print("\t\tmechanic ");
        pump.Act();
        System.out.print("->pump\n");

        System.out.print("\t\tsaboteur ");
        pump.Act();

    }
    public int FlowOut()
    {
        System.out.println("$ Pump.FlowOut()");
        Scanner be=new Scanner(System.in);
        System.out.println("El van törve a következő cső?");
        String valasztas=be.nextLine();
        if("nem".equals(valasztas))
        {
            Pipe pipe2=new Pipe();
            System.out.print("\t\t\t\t\t\t\t\t pipe2 ");
            pipe2.FlowOut();
        }
        return 0;
    }
}
