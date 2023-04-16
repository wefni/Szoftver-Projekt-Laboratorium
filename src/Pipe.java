public class Pipe extends Breakable{

    public void ChangePipe()
    {
        System.out.print("$ Pipe.ChangePipe()\n");

        //egy variacio

        Pump pump1 = new Pump();
        Pump pump2 = new Pump();
        Pipe pipe1 = new Pipe();
        Pipe pipe2 = new Pipe();
        Cistern cistern = new Cistern();

        System.out.print("\t\tmechanic ");
        pipe1.Act();
        System.out.print("->pipe1\n");

        System.out.print("\t\t\tpipe ");
        pipe2.ShowNeighbours();
        System.out.print("->pipe2\n");

        System.out.print("\t\t\tpump ");
        pump1.ShowNeighbours();
        System.out.print("->pump1\n");

        System.out.print("\t\t\tpump ");
        pump2.AddNeighbours();
        System.out.print("->pump2\n");

        System.out.print("\t\t\tpipe ");
        pipe1.AddNeighbours();
        System.out.print("->pipe1\n");

        System.out.print("\t\t\tpipe ");
        pipe1.RemoveNeighbours();
        System.out.print("->pipe1\n");

        System.out.print("\t\t\tcistern ");
        cistern.RemoveNeighbours();
        System.out.print("->cistern\n");

    }
    public void PlacePump(){
        System.out.print("$ Pipe.PlacePump()");
    }
    public int FlowOut(){
        System.out.print("$ Pipe.FlowOut()");
        return 0;
    }
}
