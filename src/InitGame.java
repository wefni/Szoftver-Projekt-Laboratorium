public class InitGame {

    public void StartGame(){
        System.out.println("InitGame$start.StartGame()");
        Map map=new Map();
        Source source=new Source();
        Pipe pipe1=new Pipe();

        System.out.println("    Source$source.AddNeighbours(pipe1)");
        source.AddNeighbours();

        System.out.println("        Pipe$pipe1.AddNeighbours(source)");
        pipe1.AddNeighbours();

        Pump pump=new Pump();
        System.out.println("            Pipe$pipe1.AddNeighbours(pump)");
        pipe1.AddNeighbours();

        System.out.println("                Pump$pump1.AddNeighbours(pipe1)");
        pump.AddNeighbours();
        Pipe pipe2=new Pipe();

        System.out.println("                    Pump$pump1.AddNeighbours(pipe2)");
        pump.AddNeighbours();

        System.out.println("                        Pipe$pipe2.AddNeighbours(pump)");
        pipe2.AddNeighbours();

        Cistern cistern=new Cistern();
        System.out.println("                            Cistern$cistern.AddNeighbours(pipe2)");
        cistern.AddNeighbours();

        System.out.println("                                Pipe$pipe2.AddNeighbours(cistern)");
        pipe2.AddNeighbours();

        Mechanic mechanic=new Mechanic();
        System.out.println("                                    Cistern$cistern.AddPlayer(mechanic)");
        cistern.AddPlayer();

        System.out.println("                                        Mechanic$mechanic.ChangeWhere(cistern)");
        mechanic.ChangeWhere();

        Saboteur saboteur=new Saboteur();

        System.out.println("                                            Source$source.AddPlayer(saboteur)");
        source.AddPlayer();

        System.out.println("                                                Saboteur$saboteur.ChangeWhere(source)");
        saboteur.ChangeWhere();

    }
}
