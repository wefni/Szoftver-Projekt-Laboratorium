import java.util.Scanner;

/**
 * A palya Component-ekbol epul fel.
 */
public abstract class Component implements Flow{

    /**
     * Kilistazza a jatekosnak az adott komponensen vegrehajthato lehetosegeket.
     */
    public void Act(){
        System.out.print("$ Component.Act()");
    }

    /**
     * Igaz erteket ad vissza, ha a jatekos ralephet-e egy komponensre, hamisat, ha nem.
     * @return bool, attol fuggoen, hogy lephet-e a jatekos.
     */
    public boolean Accept(){
        System.out.print("$ Component.Accept()");
        return false;
    }

    /**
     * Eltavolitja(lelepteti) a jatekost az adott mezorol, amin eppen all.
     */
    public void RemovePlayer(){
        System.out.print("$ Component.RemovePlayer()");
    }

    /**
     * Hozzaadja(ralepteti) a jatekost az adott mezore.
     */
    public void AddPlayer(){
        System.out.print("$ Component.AddPlayer()");
    }

    /**
     * Visszaadja az adott komponens szomszedait, akikkel kapcsolatban all.
     * @return szomszedok
     */
    public Component[] ShowNeighbours(){
        System.out.print("$ Component.ShowNeighbors()");
        return null;
    }

    /**
     * Uj szomszedot ad a mar meglevokhoz, vagyis a ket palyaelem közötti kapcsolatot hozzaadja.
     */
    public void AddNeighbours(){
        System.out.print("$ Component.AddNeighbours()");
    }

    /**
     * Eltavolitja a palyaelemek közötti kapcsolatot, vagyis mar nem lesznek szomszedok.
     */
    public void RemoveNeighbours(){
        System.out.print("$ Component.RemoveNeighbours()");
    }

    /**
     * A karakter lepeset vezenyli le.
     */
    public void Step(){
        System.out.print("$ Component.Step()");
    }

    /**
     * A víz folyását biztosító függvény.
     * @return int típusú, amely a tovabbfolyas sikeresseget jelzi
     */
    public int FlowOut(){
        System.out.print("$ Component.FlowOut()");
        return 0;
    }
}
