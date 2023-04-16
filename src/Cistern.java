public class Cistern extends Component{

    public void PickUpPump(){
        System.out.println("$ Cistern.PickUpPump()");
    }
    public void SpawnPipe(){
        System.out.print("$ Cistern.SpawnPipe()");
    }
    public int FlowOut(){
        System.out.print("$ Cistern.FlowOut()");
        return 0;
    }
}
