public class Cistern extends Component{
    private int amountOfWater=0;

    public Cistern(String ID) {
        super(ID);
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
