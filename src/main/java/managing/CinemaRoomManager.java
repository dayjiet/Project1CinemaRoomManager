package managing;

import java.util.Scanner;

/**
 * The CinemaRoomManager class manages the seating arrangement and ticket sales in a cinema room.
 * It provides methods for displaying the menu, showing the seats, purchasing tickets, and displaying statistics.
 */
public class CinemaRoomManager {
    private static final int TOTAL_SEATS = 60;
    private static final int HIGH_PRICE_TICKET_COST = 10;
    private static final int LOW_PRICE_TICKET_COST = 8;
    private static final int HALF_HALL = 2;

    /**
     * Displays the menu of the application and handles user input to perform the selected action.
     * @param seatingGrid the 2D array representing the seating arrangement
     * @param rows the number of rows in the cinema room
     * @param seats the number of seats in each row of the cinema room
     * @param ticketCount the number of tickets purchased
     * @param currentIncome the current income of the application
     * @param totalIncome the total income of the application
     */
    public static void displayMenu(String[][] seatingGrid, int rows, int seats, int ticketCount,
                                   int currentIncome, int totalIncome) {
        Scanner scanMenu = new Scanner(System.in);

        System.out.println();
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
        System.out.print("> ");

        int menu = scanMenu.nextInt();

        switch (menu) {
            case 1:
                displaySeats(seatingGrid, rows, seats, ticketCount, currentIncome, totalIncome);
                break;
            case 2:
                buyTicket(seatingGrid, rows, seats, ticketCount, currentIncome, totalIncome);
                break;
            case 3:
                displayStatistics(seatingGrid, rows, seats, ticketCount, currentIncome);
                break;
            case 0:
                break;
        }
    }

    /**
     * Displays the current state of the seating arrangement in the cinema room.
     * @param seatingGrid the 2D array representing the seating arrangement
     * @param rows the number of rows in the cinema room
     * @param seats the number of seats in each row of the cinema room
     * @param ticketCount the number of tickets purchased
     * @param currentIncome the current income of the application
     * @param totalIncome the total income of the application
     */
    public static void displaySeats(String[][] seatingGrid, int rows, int seats, int ticketCount, int currentIncome, int totalIncome) {
        System.out.println();
        System.out.println("Cinema:");

        for (String[] row : seatingGrid) {
            for (String col : row) {
                System.out.print(col + " ");
            }
            System.out.println();
        }

        displayMenu(seatingGrid, rows, seats, ticketCount, currentIncome, totalIncome);
    }

    /**
     * Allows the user to buy a ticket for a seat in the cinema room and updates the seating arrangement accordingly.
     * @param seatingGrid the 2D array representing the seating arrangement
     * @param rows the number of rows in the cinema room
     * @param seats the number of seats in each row of the cinema room
     * @param ticketCount the number of tickets purchased
     * @param currentIncome the current income of the application
     * @param totalIncome the total income of the application
     */
    public static void buyTicket(String[][] seatingGrid
            , int rows, int seats, int ticketCount, int currentIncome, int totalIncome) {
        Scanner scanSeats = new Scanner(System.in);

        System.out.println();
        System.out.println("Enter a row number:");
        System.out.print("> ");
        int row = scanSeats.nextInt();
        System.out.println("Enter a seat number in that row:");
        System.out.print("> ");
        int seat = scanSeats.nextInt();

        int ticketPrice;

        if (row > rows || seat > seats) {
            System.out.println();
            System.out.println("Wrong input!");

            buyTicket(seatingGrid, rows, seats, ticketCount, currentIncome, totalIncome);
        } else {
            if (!"B".equals(seatingGrid[row][seat])) {
                if (rows * seats > TOTAL_SEATS && row <= rows / HALF_HALL) {
                    ticketPrice = HIGH_PRICE_TICKET_COST;
                } else if (rows * seats > TOTAL_SEATS && row > rows / HALF_HALL) {
                    ticketPrice = LOW_PRICE_TICKET_COST;
                } else {
                    ticketPrice = HIGH_PRICE_TICKET_COST;
                }
                currentIncome += ticketPrice;

                seatingGrid[row][seat] = "B";

                ticketCount++;

                System.out.println();
                System.out.println("Ticket price: $" + ticketPrice);

                displayMenu(seatingGrid, rows, seats, ticketCount, currentIncome, totalIncome);
            } else {
                System.out.println();
                System.out.println("That ticket has already been purchased!");

                buyTicket(seatingGrid, rows, seats, ticketCount, currentIncome, totalIncome);
            }
        }
    }

    /**
     * Displays statistics of the application, including the current income, total income,
     * the number of purchased tickets, and the percentage of occupancy.
     * @param seatingGrid the 2D array representing the seating arrangement
     * @param rows the number of rows in the cinema room
     * @param seats the number of seats in each row of the cinema room
     * @param ticketCount the number of tickets purchased
     * @param currentIncome the current income of the application
     */
    public static void displayStatistics(String[][] seatingGrid
            , int rows, int seats, int ticketCount, int currentIncome) {
        int totalIncome;
        if (rows * seats > TOTAL_SEATS) {
            totalIncome = rows / HALF_HALL * seats * HIGH_PRICE_TICKET_COST + (rows - rows / HALF_HALL) * seats * LOW_PRICE_TICKET_COST;
        } else {
            totalIncome = rows * seats * HIGH_PRICE_TICKET_COST;
        }

        System.out.println();
        System.out.println("Number of purchased tickets: " + ticketCount);

        int HUNDRED = 100;
        double INTEGER_TO_DOUBLE = 1.0;
        double occupancyPercentage = ticketCount * INTEGER_TO_DOUBLE * HUNDRED / (rows * seats);

        System.out.printf("Percentage: %.2f%%%n", occupancyPercentage);
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);

        displayMenu(seatingGrid, rows, seats, ticketCount, currentIncome, totalIncome);
    }

    /**
     * The main method initializes the cinema room, processes user input, and starts the application.
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        System.out.print("> ");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        System.out.print("> ");
        int seats = scanner.nextInt();

        int FIRST_ROW = 1;
        int FIRST_COLUMN = 1;

        String[][] seatingGrid = new String[rows + FIRST_ROW][seats + FIRST_COLUMN];

        int rowCount = 1;
        int seatCount = 1;
        int ticketCount = 0;
        int currentIncome = 0;
        int totalIncome = 0;

        for (int i = 0; i < seatingGrid.length; i++) {
            for (int j = 0; j < seatingGrid[i].length; j++) {
                if (i == 0 && j == 0) {
                    seatingGrid[0][0] = " ";
                } else if (i == 0) {
                    seatingGrid[i][j] = String.valueOf(seatCount);
                    seatCount++;
                } else if (j == 0) {
                    seatingGrid[i][j] = String.valueOf(rowCount);
                    rowCount++;
                } else {
                    seatingGrid[i][j] = "S";
                }
            }
        }

        displayMenu(seatingGrid, rows, seats, ticketCount, currentIncome, totalIncome);
    }
}