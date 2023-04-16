/**
 * Az osztaly felelos a palyaelemek legeneralasaert.
 */
public class InitGame {

    /**
     * A StartGame letrehozza a kezdeti palyaelemeket es osszekapcsolja azokat, ugy,
     * hogy letrejojjen a palya, amelyen kesobb, majd a jatek fog zajlani.
     */
    public void StartGame(){
        System.out.print("$ InitGame.StartGame()\n");

        Map map = new Map();
        Source source = new Source();
        Pipe pipe1 = new Pipe();

        System.out.print("\t\tsource ");
        source.AddNeighbours();
        System.out.print("->pipe1\n");

        System.out.print("\t\tpipe1 ");
        pipe1.AddNeighbours();
        System.out.print("->source\n");

        Pump pump = new Pump();

        System.out.print("\t\tpipe1 ");
        pipe1.AddNeighbours();
        System.out.print("->pump\n");

        System.out.print("\t\tpump ");
        pump.AddNeighbours();
        System.out.print("->pipe1\n");

        Pipe pipe2=new Pipe();

        System.out.print("\t\tpump ");
        pump.AddNeighbours();
        System.out.print("->pipe2\n");

        System.out.print("\t\tpipe2 ");
        pipe2.AddNeighbours();
        System.out.print("->pump\n");

        Cistern cistern=new Cistern();

        System.out.print("\t\tcistern ");
        cistern.AddNeighbours();
        System.out.print("->pipe2\n");

        System.out.print("\t\tpipe2 ");
        pipe2.AddNeighbours();
        System.out.print("->cistern\n");

        Mechanic mechanic=new Mechanic();

        System.out.print("\t\tcistern ");
        cistern.AddPlayer();
        System.out.print("->mechanic\n");

        System.out.print("\t\tmechanic ");
        mechanic.ChangeWhere();
        System.out.print("->cistern\n");

        Saboteur saboteur=new Saboteur();

        System.out.print("\t\tsource ");
        source.AddPlayer();
        System.out.print("->saboteur\n");

        System.out.print("\t\tsaboteur ");
        saboteur.ChangeWhere();
        System.out.print("->source\n");

    }
}
