Hotel Simulation using Java Threads and Semaphores

Compilation
The following command was used to compile the program:
javac HotelSimulation.java

Running
The following command can be used to run the program:
java HotelSimulation

Additional Details
The program assumes that the user has Java version 8 or above installed.
The program creates 25 guest threads, 2 front desk threads, and 2 bellhop threads.
Each guest thread is randomly assigned a number of bags (between 0 and 5).
The program uses Java Semaphores for mutual exclusion and coordination.
The program does not use the "synchronized" keyword or Java data structures with built-in mutual exclusion.
Each thread prints its own activities to the console using a shared print method.
The program prints out each step of each task of each thread with identifying numbers.
The program output matches the sample output provided in the project description.
The program does not use sleeping or busy waiting (polling) as a means of coordination.
Mutual exclusion is minimized to allow for maximum concurrency.
The program terminates when all guest threads have retired for the evening and all other threads have joined.