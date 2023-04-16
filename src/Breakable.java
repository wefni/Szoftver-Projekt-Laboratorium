public abstract class Breakable extends Component{

    public void Break(){
        System.out.println(" $ Breakable.Break()");
    }
    public void Repair(){
        System.out.println(" $ Breakable.Repair()");
    }
    public int FlowOut(){
        System.out.println(" $ Breakable.FlowOut()");
        return 0;
    }
}
