import org.apache.log4j.Logger;
import java.io.IOException;

public class Main
{
    private static final Logger logger = Logger.getLogger(Main.class);

    private static final Object lock = new Object();
    public static void main(String[] args) throws IOException
    {
        String fileName = "teszt";
        Map map = new Map(fileName);
        map.GenerateMap();

        /*synchronized (lock)
        {
            try
            {
                System.out.print("megyen");
                lock.wait();
                if (map.GetIndul())
                {
                    lock.notifyAll();
                }
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        System.out.print("elindult");*/
        do
        {
            System.out.print("");
        }
        while (!map.GetIndul());
        map.AllPlayersAdded();
        map.Game();
    }
}
