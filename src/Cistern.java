public class Cistern extends Component{
    private int amountOfWater=0;
    public void PickUpPump(){


    }
    public void SpawnPipe(){
        System.out.println("$ Cistern.SpawnPipe()");
        Pipe p = new Pipe();
        System.out.println("\t\t\tpipe");
        p.AddNeighbours();

    }
    public int FlowOut(){
        System.out.println("$ Cistern.FlowOut()");
        return 0;
    }

    public void AddNeighbours(){
        System.out.println("$ Cistern.AddNeighbours()");
        System.out.println("->pipe");
    }
    public int GetWater()
    {
        return amountOfWater;
    }
}
