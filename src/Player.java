public abstract class Player {
protected Component where;
protected String name;
protected String logFile;

    public Player(String name, String logFile) {
        this.name = name;
    }
    public void YourTurn(){

    }
    public void ChangeWhere(Component c){
        where=c;
    }
}
