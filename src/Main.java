import java.util.Scanner;

public class Main {

    //Do not write code into main, just below it!***********************************************************************
    public static void main(String[] args) {
      Pipe p=new Pipe("1","");  // csak teszt
      Pipe p1=new Pipe("2","");
      Mechanic m=new Mechanic();
      m.where=p;
      System.out.println(m.where.id);
      p.Step(p1,m);
      System.out.println(m.where.id);
    }
}