import java.util.Scanner;

public class Main {
    public static void menu(String[][] hall, int rows, int seats, int ticketsCounter, int incomeCounter, int totalIncome) {
        Scanner menuScanner = new Scanner(System.in);

        System.out.println();
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
        System.out.print("> ");

        int menu = menuScanner.nextInt();

        switch (menu) {
            case 1:
                show(hall, rows, seats, ticketsCounter, incomeCounter, totalIncome);
                break;
            case 2:
                buy(hall, rows, seats, ticketsCounter, incomeCounter, totalIncome);
                break;
            case 3:
                stat(hall, rows, seats, ticketsCounter, incomeCounter, totalIncome);
                break;
            case 0:
                break;
        }
    }

    public static void show(String[][] hall, int rows, int seats, int ticketsCounter, int incomeCounter, int totalIncome) {
        System.out.println();
        System.out.println("Cinema:");

        for (int i = 0; i < hall.length; i++) {
            for (int j = 0; j < hall[i].length; j++) {
                System.out.print(hall[i][j] + " ");
            }
            System.out.println();
        }

        menu(hall, rows, seats, ticketsCounter, incomeCounter, totalIncome);
    }

    public static void buy(String[][] hall, int rows, int seats, int ticketsCounter, int incomeCounter, int totalIncome) {
        Scanner buyScanner = new Scanner(System.in);

        System.out.println();
        System.out.println("Enter a row number:");
        System.out.print("> ");
        int row = buyScanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        System.out.print("> ");
        int seat = buyScanner.nextInt();

        int ticketPrice;
        int seatCount = 60;
        int costlyTicket = 10;
        int inexpensiveTicket = 8;
        int hallHalf = 2;

        if (row > rows || seat > seats) {
            System.out.println();
            System.out.println("Wrong input!");

            buy(hall, rows, seats, ticketsCounter, incomeCounter, totalIncome);
        } else {
            if (hall[row][seat] != "B") {
                if (rows * seats > seatCount && row <= rows / hallHalf) {
                    ticketPrice = costlyTicket;
                } else if (rows * seats > seatCount && row > rows / hallHalf) {
                    ticketPrice = inexpensiveTicket;
                } else {
                    ticketPrice = costlyTicket;
                }
                incomeCounter += ticketPrice;

                hall[row][seat] = "B";

                ticketsCounter++;

                System.out.println();
                System.out.println("Ticket price: $" + ticketPrice);

                menu(hall, rows, seats, ticketsCounter, incomeCounter, totalIncome);
            } else {
                System.out.println();
                System.out.println("That ticket has already been purchased!");

                buy(hall, rows, seats, ticketsCounter, incomeCounter, totalIncome);
            }
        }
    }

    public static void stat(String[][] hall, int rows, int seats, int ticketsCounter, int incomeCounter, int totalIncome) {

        int seatCount = 60;
        int costlyTicket = 10;
        int inexpensiveTicket = 8;
        int hallHalf = 2;

        if (rows * seats > seatCount) {
            totalIncome = rows / hallHalf * seats * costlyTicket + (rows - rows / hallHalf) * seats * inexpensiveTicket;
        } else {
            totalIncome = rows * seats * costlyTicket;
        }

        System.out.println();
        System.out.println("Number of purchased tickets: " + ticketsCounter);

        int hundredPercent = 100;
        double intToDouble = 1.0;
        double percentage = ticketsCounter * intToDouble * hundredPercent / (rows * seats);

        System.out.printf("Percentage: %.2f%%%n", percentage);
        System.out.println("Current income: $" + incomeCounter);
        System.out.println("Total income: $" + totalIncome);

        menu(hall, rows, seats, ticketsCounter, incomeCounter, totalIncome);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        System.out.print("> ");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        System.out.print("> ");
        int seats = scanner.nextInt();

        int firstRow = 1;
        int firstColumn = 1;

        String[][] hall = new String[rows + firstRow][seats + firstColumn];

        int rowNumber = 1;
        int seatNumber = 1;
        int ticketsCounter = 0;
        int incomeCounter = 0;
        int totalIncome = 0;

        for (int i = 0; i < hall.length; i++) {
            for (int j = 0; j < hall[i].length; j++) {
                if (i == 0 && j == 0) {
                    hall[0][0] = " ";
                } else if (i == 0 && j != 0) {
                    hall[i][j] = String.valueOf(seatNumber);
                    seatNumber++;
                } else if (i != 0 && j == 0) {
                    hall[i][j] = String.valueOf(rowNumber);
                    rowNumber++;
                } else {
                    hall[i][j] = "S";
                }
            }
        }

        menu(hall, rows, seats, ticketsCounter, incomeCounter, totalIncome);
    }
}