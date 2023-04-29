public abstract class RandomBreakable extends Breakable{

    public RandomBreakable(String ID, String logFILE) {
        super(ID, logFILE);
    }

    public int FlowOut(){
        return 0;
    }
}
