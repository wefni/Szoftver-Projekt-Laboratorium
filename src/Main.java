import java.util.Scanner;

public class Main {

    //Do not write code into main, just below it!***********************************************************************
    public static void main(String[] args) {
      Pipe p=new Pipe("1","");  // csak teszt
      Pipe p1=new Pipe("2","");
      Mechanic m=new Mechanic("fitymahuszar69xd","valamike.txt" );
      m.where=p;
      System.out.println(m.where.id);
      p.Step(m);
      System.out.println(m.where.id);
    }
}