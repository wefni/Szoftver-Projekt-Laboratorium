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
        System.out.print("$ Pump.FlowOut()");
        return 0;
    }
}
