public abstract class Player {
protected Component where;
    public void YourTurn(){
        System.out.print("$ Player.YourTurn()");
    }
    public void ChangeWhere(Component c){
        where=c;
    }
}
