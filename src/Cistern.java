import org.apache.log4j.Logger;

public class Cistern extends Component{

    private static final Logger logger = Logger.getLogger(Cistern.class);
    private int amountOfWater=0;

    public Cistern(String ID) {
        super(ID);
        logger.info(this.id + "@Cistern | "+this.id+" létrejött \n");
    }

    public void PickUpPump(){


    }
    public void SpawnPipe(){

    }
    public int FlowOut(){
        return 0;
    }

    public void AddNeighbours(){
    }
    public int GetWater()
    {
        return amountOfWater;
    }
}
