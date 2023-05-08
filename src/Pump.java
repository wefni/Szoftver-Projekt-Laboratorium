import org.apache.log4j.Logger;

import java.util.Scanner;

/**
 * A Pump osztaly valositja meg a pumpat.
 * A RandomBreakable-ből örököl, mivel random idöközönként el tud törni.
 */
public class Pump extends RandomBreakable
{
    private static final Logger logger = Logger.getLogger(Pump.class);
    private Pipe in;
    private Pipe out;
    private int tank;
    private int maxTank;
    private int randomBreakCounter;
    private boolean didWaterFlow;

    public Pump(String ID) {
        super(ID);
        logger.info(this.id + "@Pump | "+this.id+" létrejött \n");
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
