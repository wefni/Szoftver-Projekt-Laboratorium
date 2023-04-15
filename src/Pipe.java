public class Pipe extends Breakable{

    public void ChangePipe(){
        System.out.print("$ Pipe.ChangePipe()");
    }
    public void PlacePump(){
        System.out.print("$ Pipe.PlacePump()");
    }
    public int FlowOut(){
        System.out.print("$ Pipe.FlowOut()");
        return 0;
    }
}
