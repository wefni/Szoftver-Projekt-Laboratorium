import org.apache.log4j.Logger;

public class Saboteur extends Player{
    private static final Logger logger = Logger.getLogger(Saboteur.class);

    public Saboteur(String name) {
        super(name);
        logger.info(this.name + "@Saboteur | " + this.name + " létrejött \n");

    }

    public void YourTurn() {
        logger.info(this.name + "@YourTurn | " + this.name + " következik \n");
        where.Act(this,1);
    }
    public void ChangeWhere(Component c){
        where = c;
        logger.info(this.name + "@ChangeWhere | " + this.name + " helyzete megváltoztatva a következőre: "+c.id+"\n");
    }
}
