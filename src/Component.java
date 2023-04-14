public abstract class Component implements Flow{

    public void Act(){}
    public boolean Accept(){return false;}
    public void RemovePlayer(){}
    public void AddPLayer(){}
    public Component[] ShowNeighbours(){return null;}
    public void AddNeighbours(){}
    public void RemoveNeighbours(){}
    public void Step(){}
    public int FlowOut(){return 0;}
}
