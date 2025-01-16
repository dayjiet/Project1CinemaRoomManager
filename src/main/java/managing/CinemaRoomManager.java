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
    private static final int HALF = 2;

    /**
     * Displays the menu of the application and handles user input to perform the selected action.
     *
     * @param seatsGrid the 2D array representing the seating arrangement
     * @param rows the number of rows in the cinema room
     * @param seats the number of seats in each row of the cinema room
     * @param ticketCount the number of tickets purchased
     * @param currentIncome the current income of the application
     * @param totalIncome the total income of the application
     */
    public static void displayMenu(
            String[][] seatsGrid,
            int rows,
            int seats,
            int ticketCount,
            int currentIncome,
            int totalIncome
    ) {
        Scanner scanMenu = new Scanner(System.in);

        System.out.println("\n1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
        System.out.print("> ");

        int menu = scanMenu.nextInt();

        switch (menu) {
            case 1:
                displaySeats(seatsGrid, rows, seats, ticketCount, currentIncome, totalIncome);
                break;
            case 2:
                buyTicket(seatsGrid, rows, seats, ticketCount, currentIncome, totalIncome);
                break;
            case 3:
                displayStatistics(seatsGrid, rows, seats, ticketCount, currentIncome);
                break;
            default:
                break;
        }
    }

    /**
     * Displays the current state of the seating arrangement in the cinema room.
     *
     * @param seatsGrid the 2D array representing the seating arrangement
     * @param rows the number of rows in the cinema room
     * @param seats the number of seats in each row of the cinema room
     * @param ticketCount the number of tickets purchased
     * @param currentIncome the current income of the application
     * @param totalIncome the total income of the application
     */
    public static void displaySeats(
            String[][] seatsGrid,
            int rows,
            int seats,
            int ticketCount,
            int currentIncome,
            int totalIncome
    ) {
        System.out.println("\nCinema:");

        for (String[] row : seatsGrid) {
            for (String col : row) {
                System.out.print(col + " ");
            }
            System.out.println();
        }

        displayMenu(seatsGrid, rows, seats, ticketCount, currentIncome, totalIncome);
    }

    /**
     * Allows the user to buy a ticket for a seat in the cinema room and updates the seating arrangement accordingly.
     *
     * @param seatsGrid the 2D array representing the seating arrangement
     * @param rows the number of rows in the cinema room
     * @param seats the number of seats in each row of the cinema room
     * @param ticketCount the number of tickets purchased
     * @param currentIncome the current income of the application
     * @param totalIncome the total income of the application
     */
    public static void buyTicket(
            String[][] seatsGrid,
            int rows,
            int seats,
            int ticketCount,
            int currentIncome,
            int totalIncome
    ) {
        Scanner scanSeats = new Scanner(System.in);

        System.out.println("\nEnter a row number:");
        System.out.print("> ");
        int row = scanSeats.nextInt();
        System.out.println("Enter a seat number in that row:");
        System.out.print("> ");
        int seat = scanSeats.nextInt();

        int ticketPrice;

        if (row > rows || seat > seats) {
            System.out.println("\nWrong input!");

            buyTicket(seatsGrid, rows, seats, ticketCount, currentIncome, totalIncome);
        } else {
            if (!"B".equals(seatsGrid[row][seat])) {
                if (rows * seats > TOTAL_SEATS && row <= rows / HALF) {
                    ticketPrice = HIGH_PRICE_TICKET_COST;
                } else if (rows * seats > TOTAL_SEATS && row > rows / HALF) {
                    ticketPrice = LOW_PRICE_TICKET_COST;
                } else {
                    ticketPrice = HIGH_PRICE_TICKET_COST;
                }
                currentIncome += ticketPrice;

                seatsGrid[row][seat] = "B";

                ticketCount++;

                System.out.println("\nTicket price: $" + ticketPrice);

                displayMenu(seatsGrid, rows, seats, ticketCount, currentIncome, totalIncome);
            } else {
                System.out.println("\nThat ticket has already been purchased!");

                buyTicket(seatsGrid, rows, seats, ticketCount, currentIncome, totalIncome);
            }
        }
    }

    /**
     * Displays statistics of the application, including the current income, total income, the number of purchased
     * tickets, and the percentage of occupancy.
     *
     * @param seatsGrid the 2D array representing the seating arrangement
     * @param rows the number of rows in the cinema room
     * @param seats the number of seats in each row of the cinema room
     * @param ticketCount the number of tickets purchased
     * @param currentIncome the current income of the application
     */
    public static void displayStatistics(
            String[][] seatsGrid,
            int rows,
            int seats,
            int ticketCount,
            int currentIncome
    ) {
        int totalIncome;
        if (rows * seats > TOTAL_SEATS) {
            totalIncome = rows / HALF * seats * HIGH_PRICE_TICKET_COST
                    + (rows - rows / HALF) * seats * LOW_PRICE_TICKET_COST;
        } else {
            totalIncome = rows * seats * HIGH_PRICE_TICKET_COST;
        }

        System.out.println("\nNumber of purchased tickets: " + ticketCount);

        int HUNDRED = 100;
        double INTEGER_TO_DOUBLE = 1.0;
        double occupancyPercentage = ticketCount * INTEGER_TO_DOUBLE * HUNDRED / (rows * seats);

        System.out.printf("Percentage: %.2f%%%n", occupancyPercentage);
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);

        displayMenu(seatsGrid, rows, seats, ticketCount, currentIncome, totalIncome);
    }

    /**
     * The main method initializes the cinema room, processes user input, and starts the application.
     *
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

        int ROW_NUMBER = 1;
        int COLUMN_NUMBER = 1;

        String[][] seatsGrid = new String[rows + ROW_NUMBER][seats + COLUMN_NUMBER];

        int rowCount = 1;
        int seatCount = 1;
        int ticketCount = 0;
        int currentIncome = 0;
        int totalIncome = 0;

        for (int i = 0; i < seatsGrid.length; i++) {
            for (int j = 0; j < seatsGrid[i].length; j++) {
                if (i == 0 && j == 0) {
                    seatsGrid[0][0] = " ";
                } else if (i == 0) {
                    seatsGrid[i][j] = String.valueOf(seatCount);
                    seatCount++;
                } else if (j == 0) {
                    seatsGrid[i][j] = String.valueOf(rowCount);
                    rowCount++;
                } else {
                    seatsGrid[i][j] = "S";
                }
            }
        }

        displayMenu(seatsGrid, rows, seats, ticketCount, currentIncome, totalIncome);
    }
}