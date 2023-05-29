import org.apache.log4j.Logger;
import java.io.IOException;

public class Main
{
    private static final Logger logger = Logger.getLogger(Main.class);
    public static void main(String[] args) throws IOException
    {
        String fileName = "teszt";
        Map map = new Map(fileName);
        map.GenerateMap();
        map.AllPlayersAdded();
        map.Game();
    }
}
