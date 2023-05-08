import org.apache.log4j.Logger;

import java.util.Scanner;

public class Source extends Component {

    private static final Logger logger = Logger.getLogger(Source.class);
    private int amountOfWater = 0;

    public Source(String ID) {
        super(ID);
        logger.info(this.id + "@Source | "+this.id+" létrejött \n");
    }

    public void StartFlow() {
    }

    public int GetWater() {
        return amountOfWater;
    }
}
