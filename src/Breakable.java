public abstract class Breakable extends Component{

    public void Break(){
        System.out.print(" $ Breakable.Break()");
    }
    public void Repair(){
        System.out.print(" $ Breakable.Repair()");
    }
    public int FlowOut(){
        System.out.print(" $ Breakable.FlowOut()");
        return 0;
    }
}
