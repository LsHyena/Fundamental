import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.Scanner;

 public class MuseumTicketSystem {
            public static void main(String[] args) {
                Scanner scanner = new Scanner(System.in);

                // Museum ticket price
                double ticketPrice = 149.0;

                // Maximum tickets per day
                int maxTicketsPerDay = 10;
                int ticketsSoldToday = 0;
                int ticketsSoldTomorrow = 0;
                // Max days to book
                int maxDaysToBook = 365;

                // Get current date
                LocalDate today = LocalDate.now();

                // Input booking date
                System.out.println("Enter the booking date (yyyy-mm-dd): ");
                String bookingDateInput = scanner.next();
                LocalDate bookingDate = LocalDate.parse(bookingDateInput);

                // Check if booking date is valid
                if (bookingDate.isBefore(today) || bookingDate.isAfter(today.plusDays(maxDaysToBook))) {
                    System.out.println("Invalid booking date. You can only book tickets for dates within the next " + maxDaysToBook + " days.");
                    return;
                }

                // Check if max tickets for the booking date have been reached
                if (ticketsSoldToday + ticketsSoldTomorrow >= maxTicketsPerDay) {
                    System.out.println("Sorry, maximum tickets for today and tomorrow have been sold.");
                    return;
                }

                // Number of tickets to sell
                System.out.print("Enter the number of tickets to sell: ");
                int numOfTickets = scanner.nextInt();

                // Check if number of tickets requested exceeds remaining tickets for the booking date
                if (ticketsSoldToday + ticketsSoldTomorrow + numOfTickets > maxTicketsPerDay) {
                    System.out.println("Sorry, only " + (maxTicketsPerDay - ticketsSoldToday - ticketsSoldTomorrow) + " tickets available for the booking date (" + bookingDate + ").");
                    return;
                }

                // Calculate total pric
                double totalPrice = ticketPrice * numOfTickets;

                // Display total price
                System.out.println("Total Price: $" + totalPrice);

                // Accept payment
                System.out.print("Enter payment amount: $");
                double paymentAmount = scanner.nextDouble();

                // Check if payment is sufficient
                if (paymentAmount < totalPrice) {
                    System.out.println("Payment is insufficient. Transaction canceled.");
                } else {
                    // Calculate change
                    double change = paymentAmount - totalPrice;
                    System.out.println("Change: $" + change);

                    // Update tickets sold for the appropriate date
                    if (bookingDate.equals(today))
                        ticketsSoldToday += numOfTickets;
                    else if (bookingDate.equals(today.plusDays(1)))
                        ticketsSoldTomorrow += numOfTickets;

                    // Issue tickets
                    System.out.println("Tickets issued successfully for the booking date (" + bookingDate + ").");
                }

                scanner.close();
    }
}

