import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //Itt hozzuk létre a játékot és indítjuk el 2 Sboteur és 2 Mechanic játékossal
        Map map = new Map("log.txt");
        map.StartGame(2,2);
        map.Game();
    }
}