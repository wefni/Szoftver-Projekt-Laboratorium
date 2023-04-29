import java.util.Scanner;

/**
 * A Pump osztaly valositja meg a pumpat.
 * A RandomBreakable-ből örököl, mivel random idöközönként el tud törni.
 */
public class Pump extends RandomBreakable
{
    private Pipe in;
    private Pipe out;
    private int tank;
    private int maxTank;
    private int randomBreakCounter;
    private boolean didWaterFlow;

    public Pump(String ID, String logFILE) {
        super(ID, logFILE);
    }

    /**
     * A pumpa kimnetet es bemenetet allitani kepes fuggveny.
     */

    public void ConfigurePump() {

    }

    /**
     * A víz folyását biztosító függvény.
     * @return int típusú, amely a tovabbfolyas sikeresseget jelzi
     */
    public int FlowOut(){
        return 0;
    }

    public boolean Accept(){
        return true;
    }

    public void AddPlayer(){
    }
    public int GetWater()
    {
        return tank;
    }
}
