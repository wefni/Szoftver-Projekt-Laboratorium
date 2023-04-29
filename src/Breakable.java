public abstract class Breakable extends Component{
protected boolean broken;

    public Breakable(String ID, String logFILE) {
        super(ID, logFILE);
    }

    public void Break(){
    }
    public void Repair(){
    }
    public int FlowOut(){
        return 0;
    }
}
