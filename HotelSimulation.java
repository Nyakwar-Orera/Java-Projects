/**
    The HotelSimulation class simulates a hotel scenario where guests arrive with a random number of bags,
    check in at the front desk, and then proceed to their rooms. Guests with more than 2 bags can request
    assistance from a bellhop. The simulation uses semaphores to ensure that only a certain number of guests
    can check in or get help with their bags at a time.
    */
import java.util.concurrent.Semaphore;

public class HotelSimulation {
    private static final int NUM_GUESTS = 25;
    private static final int MAX_BAGS = 5;
    private static final int NUM_FRONT_DESK_EMPLOYEES = 2;
    private static final int NUM_BELLHOPS = 2;

    private static Semaphore frontDeskSem = new Semaphore(NUM_FRONT_DESK_EMPLOYEES);
    private static Semaphore bellhopSem = new Semaphore(NUM_BELLHOPS);
    private static Semaphore mutex = new Semaphore(1);

    private static int currentRoom = 1;
    /**
        * The main method creates and starts threads for the guests, front desk employees, and bellhops,
        * and waits for all guests to finish before terminating the program.
        * @param args an array of command-line arguments
        */
    public static void main(String[] args) {
    /**
        Create and start the threads for the simulation
     */ 
    for (int i = 1; i <= NUM_GUESTS; i++) {
        int numBags = (int) (Math.random() * MAX_BAGS) + 1;
        new Guest(i, numBags).start();
    }

    /**
       Wait for all guests to finish
     */ 
    while (!Guest.areAllGuestsProcessed()) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
        /**
         Terminate the program
         */ 
        System.exit(0);
    }

    /**
        * Class representing a guest thread in the simulation.
        */
    private static class Guest extends Thread {
        private static volatile boolean allGuestsProcessed = false;
        private int id;
        private int numBags;
        /**
            * Constructor for a guest thread.
            *
            * @param id the id of the guest
            * @param numBags the number of bags the guest has
            */
        public Guest(int id, int numBags) {
            this.id = id;
            this.numBags = numBags;
        }

        /**
            * The run method simulates the actions of a guest in the hotel, including checking in at the front desk,
            * getting assistance with bags from a bellhop, and retiring for the evening.
            */
        public void run() {
            try {
                System.out.println("Guest " + id + " enters hotel with " + numBags + " bag(s)");

                frontDeskSem.acquire();
                int roomNumber = currentRoom;
                currentRoom++;
                System.out.println("Guest " + id + " gets room number " + roomNumber + " from front desk employee");

                if (numBags > 2) {
                    bellhopSem.acquire();
                    System.out.println("Guest " + id + " requests help with bags");
                    mutex.acquire();
                    System.out.println("Bellhop " + (NUM_BELLHOPS - bellhopSem.availablePermits()) + " receives bags from guest " + id);
                    mutex.release();
                }

                System.out.println("Guest " + id + " enters room " + roomNumber);

                if (numBags > 2) {
                    mutex.acquire();
                    System.out.println("Bellhop " + (NUM_BELLHOPS - bellhopSem.availablePermits()) + " delivers bags to guest " + id);
                    mutex.release();
                    bellhopSem.release();
                    System.out.println("Guest " + id + " receives bags from bellhop " + (NUM_BELLHOPS - bellhopSem.availablePermits() - 1) + " and gives tip");
                }

                System.out.println("Guest " + id + " retires for the evening");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                frontDeskSem.release();
                if (id == NUM_GUESTS) {
                    allGuestsProcessed = true;
                }
            }
        }
        public static boolean areAllGuestsProcessed() {
                return allGuestsProcessed;
        }
    }

    /**
        This class represents a front desk employee thread that registers guests and assigns them a room.
        It uses a semaphore and a mutex to ensure that only one employee is accessing the current room number at a time.
        */
    private static class FrontDeskEmployee extends Thread {
        private int id;
        
        /**
            Creates a new instance of the FrontDeskEmployee class with the given ID.
            @param id The ID of the front desk employee.
            */
        public FrontDeskEmployee(int id) {
            this.id = id;
        }

        /**

            The run method of this thread. It continuously registers guests and assigns them a room.
            */
        public void run() {
            while (true) {
                try {
                    frontDeskSem.acquire();
                    mutex.acquire();
                    System.out.println("Front desk employee " + id + " registers guest and assigns room " + currentRoom);
                    mutex.release();
                    currentRoom++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    frontDeskSem.release();
                }
            }
        }
    }

    /**

        This class represents a bellhop thread that delivers bags to guests and accepts tips.

        It uses a semaphore and a mutex to ensure that only one bellhop is accessing the shared resources at a time.
        */
    private static class Bellhop extends Thread {
        private int id;

        /**

            Creates a new instance of the Bellhop class with the given ID.
            @param id The ID of the bellhop.
            */
        public Bellhop(int id) {
            this.id = id;
        }

        /**

            The run method of this thread. It continuously delivers bags to guests and accepts tips.
            */
        public void run() {
            while (true) {
                try {
                    bellhopSem.acquire();
                    mutex.acquire();
                    System.out.println("Bellhop " + id + " delivers bags to guest and accepts tip");
                    mutex.release();
                    bellhopSem.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
