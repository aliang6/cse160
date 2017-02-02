/**
 * TicketMachine models a ticket machine that issues
 * flat-fare tickets.
 * The price of a ticket is specified via the constructor.
 * Instances will check to ensure that a user only enters
 * sensible amounts of money, and will only print a ticket
 * if enough money has been input.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2011.07.31
 */
public class TicketMachine
{
    // The price of a ticket to Brooklyn
    private int priceB;
    // The price of a ticket to Queens
    private int priceQ;
    // The price of a ticket to Manhattan
    private int priceM;
    // The price of a ticket to Staten Island
    private int priceS;
    // The price of a ticket to the Bronx
    private int priceX;
    // The amount of money entered by a customer so far.
    private int balance;
    // The total amount of money collected by this machine.
    private int total;
    // Discount Amount
    private int discountAmount;

    /**
     * Create a machine that issues tickets of the given price.
     */
    public TicketMachine(int costB, int costQ, int costM, int costS, int costX)
    {
        priceB = costB;
        priceQ = costQ;
        priceM = costM;
        priceS = costS;
        priceX = costX;
        balance = 0;
        total = 0;
        discountAmount = 1; //Works by divinding the price by the discount amount
    }

    //Ticket Accessors
    public int getPriceB()
    {
        return priceB;
    }

    public int getPriceQ()
    {
        return priceQ;
    }

    public int getPriceM()
    {
        return priceM;
    }

    public int getPriceS()
    {
        return priceS;
    }

    public int getPriceX()
    {
        return priceX;
    }

    //Discount Modifier
    public void changeDiscount(String code){
        if(code.equals("ABC")) {
            discountAmount = 2;
            System.out.println("50% off");
        }
        else {
            discountAmount = 1;
            System.out.println("Wrong code");
        }
    }

    /**
     * Return The amount of money already inserted for the
     * next ticket.
     */
    public int getBalance()
    {
        return balance;
    }

    /**
     * Receive an amount of money from a customer.
     * Check that the amount is sensible.
     */
    public void insertMoney(int amount)
    {
        if(amount > 0) {
            balance = balance + amount;
        }
        else {
            System.out.println("Use a positive amount rather than: " +
                amount);
        }
    }

    /**
     * Print a ticket if enough money has been inserted, and
     * reduce the current balance by the ticket price. Print
     * an error message if more money is required.
     */
    public void printTicket(int borough) //Wanted to use String but that requires Java 7
    {
        switch (borough) {
            case 1: //Brooklyn
            if(balance >= priceB/discountAmount) {
                // Simulate the printing of a ticket.
                System.out.println("##################");
                System.out.println("# The BlueJ Line");
                System.out.println("# Ticket to Brooklyn");
                System.out.println("# " + priceB/discountAmount + " cents.");
                System.out.println("##################");
                System.out.println();

                // Update the total collected with the price.
                total = total + priceB/discountAmount;
                // Reduce the balance by the prince.
                balance = balance - priceB/discountAmount;
            }
            else {
                System.out.println("You must insert at least: " +
                    (priceB/discountAmount - balance) + " more cents.");

            }
            break;

            case 2:
            if(balance >= priceM/discountAmount) {
                // Simulate the printing of a ticket.
                System.out.println("##################");
                System.out.println("# The BlueJ Line");
                System.out.println("# Ticket to Manhattan");
                System.out.println("# " + priceM/discountAmount + " cents.");
                System.out.println("##################");
                System.out.println();

                // Update the total collected with the price.
                total = total + priceM/discountAmount;
                // Reduce the balance by the prince.
                balance = balance - priceM/discountAmount;
            }
            else {
                System.out.println("You must insert at least: " +
                    (priceM/discountAmount - balance) + " more cents.");

            }
            break;

            case 3:
            if(balance >= priceQ/discountAmount) {
                // Simulate the printing of a ticket.
                System.out.println("##################");
                System.out.println("# The BlueJ Line");
                System.out.println("# Ticket to Queens");
                System.out.println("# " + priceQ/discountAmount + " cents.");
                System.out.println("##################");
                System.out.println();

                // Update the total collected with the price.
                total = total + priceQ/discountAmount;
                // Reduce the balance by the prince.
                balance = balance - priceQ/discountAmount;
            }
            else {
                System.out.println("You must insert at least: " +
                    (priceQ/discountAmount - balance) + " more cents.");

            }
            break;

            case 4:
            if(balance >= priceS/discountAmount) {
                // Simulate the printing of a ticket.
                System.out.println("##################");
                System.out.println("# The BlueJ Line");
                System.out.println("# Ticket to Staten Island");
                System.out.println("# " + priceS/discountAmount + " cents.");
                System.out.println("##################");
                System.out.println();

                // Update the total collected with the price.
                total = total + priceS/discountAmount;
                // Reduce the balance by the prince.
                balance = balance - priceS/discountAmount;
            }
            else {
                System.out.println("You must insert at least: " +
                    (priceS/discountAmount - balance) + " more cents.");

            }
            break;
            
            case 5:
            if(balance >= priceX/discountAmount) {
                // Simulate the printing of a ticket.
                System.out.println("##################");
                System.out.println("# The BlueJ Line");
                System.out.println("# Ticket to the Bronx");
                System.out.println("# " + priceX/discountAmount + " cents.");
                System.out.println("##################");
                System.out.println();

                // Update the total collected with the price.
                total = total + priceX/discountAmount;
                // Reduce the balance by the prince.
                balance = balance - priceX/discountAmount;
            }
            else {
                System.out.println("You must insert at least: " +
                    (priceX/discountAmount - balance) + " more cents.");

            }
            break;

            default:
            System.out.println("Borough not recognized");
            break;
        }
    }

    /**
     * Return the money in the balance.
     * The balance is cleared.
     */
    public int refundBalance()
    {
        int amountToRefund;
        amountToRefund = balance;
        balance = 0;
        return amountToRefund;
    }
}