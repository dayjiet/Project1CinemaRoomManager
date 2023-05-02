# Cinema Room Manager

A Java application for managing a cinema room, allowing users to view the seats, purchase tickets, and view statistics. 
The application uses a 2D array to represent the seating arrangement, integers to represent the number of rows, seats, 
tickets purchased, and the income and total income of the application.

## Features

- Show the seats
- Purchase a ticket
- View statistics (current income, total income, available seats, and occupancy percentage)

## Usage

### Show the seats
The `displaySeats` method displays the current state of the seating arrangement in the Cinema Room by iterating through 
the 2D array and printing each row.

### Purchase a ticket
The `purchaseTicket` method allows the user to buy a ticket for a seat in the Cinema Room and displays the cost of the 
seat. The seat cost depends on the number of rows, seats, and the row number selected by the user. The cost for a seat 
in the first half of the hall is $10 and the cost for a seat in the second half of the hall is $8.

### View Statistics
The displayStatistics method allows you to show statistics of the application, such as the current income, total income, 
the number of available seats, and the percentage of occupancy.

## Notes
This application was created for demonstration purposes only and may need modification to be used in a real-world 
scenario.