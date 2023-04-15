import java.util.Scanner;

public class Main {

    //Do not write code into main, just below it!***********************************************************************
    public static void main(String[] args) {
        System.out.println("Welcome!");
        while (true) {
            System.out.println("Choose a use-case!");
            System.out.println("1. Start Game");
            System.out.println("2. Pick Up Pump");
            System.out.println("3. Random Break");
            System.out.println("4. Break Pipe");
            System.out.println("5. Repair Pump");
            System.out.println("6. Place Pump");
            System.out.println("7. Change Pipe");
            System.out.println("8. Configure Pump");
            System.out.println("9. Repair Pipe");
            System.out.println("10. Flow Of Water");
            System.out.println("11. Step");
            System.out.println("12. Spawn Pipe");
            System.out.println("0. Exit Skeleton\n");

            Scanner input = new Scanner(System.in);
            System.out.print("Enter the number of the use-case you would like to run: ");
            int nextUseCase = input.nextInt();

            switch (nextUseCase) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    UseCaseStartGame();
                    break;
                case 2:
                    UseCasePickUpPump();
                    break;
                case 3:
                    UseCaseRandomBreak();
                    break;
                case 4:
                    UseCaseBreakPipe();
                    break;
                case 5:
                    UseCaseRepairPump();
                    break;
                case 6:
                    UseCasePlacePump();
                    break;
                case 7:
                    UseCaseChangePipe();
                    break;
                case 8:
                    UseCaseConfigurePump();
                    break;
                case 9:
                    UseCaseRepairPipe();
                    break;
                case 10:
                    UseCaseFlowOfWater();
                    break;
                case 11:
                    UseCaseStep();
                    break;
                case 12:
                    UseCaseSpawnPipe();
                    break;
                default:
                    System.out.println("Wrong number, choose again!");
                    break;
            }
        System.out.println("");

        }
    }

    //Work from here!***************************************************************************************************
    private static void UseCaseStartGame() {
        System.out.println("Start Game use case started!");

        InitGame start=new InitGame();
        System.out.print("  initGame ");
        start.StartGame();

        System.out.println("Start Game use case done!");
    }

    private static void UseCasePickUpPump() {
        System.out.println("Pick Up Pump use case started!");
        //to do
        System.out.println("Pick Up Pump use case done!");
    }

    private static void UseCaseRandomBreak() {
        System.out.println("Random Break use case started!");
        //to do
        System.out.println("Random Break use case done!");
    }

    private static void UseCaseBreakPipe() {
        System.out.println("Break Pipe use case started!");
        //to do
        System.out.println("Break Pipe use case done!");
    }

    private static void UseCaseRepairPump() {
        System.out.println("Repair Pump use case started!");
        //to do
        System.out.println("Repair Pump use case done!");
    }

    private static void UseCasePlacePump() {
        System.out.println("Place Pump use case started!");
        //to do
        System.out.println("Place Pump use case done!");
    }

    private static void UseCaseChangePipe() {
        System.out.println("Change Pipe use case started!");
        //to do
        System.out.println("Change Pipe use case done!");
    }

    private static void UseCaseConfigurePump() {
        System.out.println("Configure Pump use case started!");
        //to do
        System.out.println("Configure Pump use case done!");
    }

    private static void UseCaseRepairPipe() {
        System.out.println("Repair Pipe use case started!");
        //to do
        System.out.println("Repair Pipe use case done!");
    }

    private static void UseCaseFlowOfWater() {
        System.out.println("Flow Of Water use case started!");
        //to do
        System.out.println("Flow Of Water use case done!");
    }

    private static void UseCaseStep() {
        System.out.println("Step use case started!");
        //to do
        System.out.println("Step use case done!");
    }

    private static void UseCaseSpawnPipe() {
        System.out.println("Spawn Pipe use case started!");
        //to do
        System.out.println("Spawn Pipe use case done!");
    }

}