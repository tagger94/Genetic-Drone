# Defensive Coding
## Route.java, line 62 function getDistance
This method is designed to return to the caller the total distance a route takes. Before if can return this value, it must calculate it, which can take a long time. When we started implement threading of this calculation, we wanted to ensure that any errors with the thread would not crash the program. Originally we had this as two seperate methods, getDistance and calcDistance. The thread would call calcDistance and evolveGeneration would call getDistance. By combining the two, if any error occurs during the calculation on the part of the thread (and that it does not have to do with values being used) the evolveGeneration function can handle the calculation. So getDistance is smart and checks to see if it has been calculated, if already done then return, otherwise do the math. 

## WorkerManager.java, line 42 function run_workermanager
This line has us calling run_workers. When we do this, we pass a start and end index for the worker to process. In this line, and in the run_worker function, we check to make sure this will not go out of bounds of the array and thus cause issues.


# Corrected Code Smells
## Route.java
In this object we have to store an array of parcels that make up the route. Originally we were going to use an array of integers to store the index of the package as it relates to the ParcelList object. We deemed this smelly since that would require us to pass the ParcelList object every time we did anything with the Route.

## GeneticDrone.java
In our main method, before we had added the threading, each generation was handled by a loop. When adding the threading, we maintained that loop code and added a polling loop inside it to prevent continuing until all the calculations were finished. Now, we moved the outer loop into a recursive call that can end itself and track workers better.

# Threading
see WorkerManager. For each generation, each child of that generation has route and we need to find the distnace. 
The evovleGeneration function runs on a single thread since its operations are quite short. But if the route size is large enough, the time it takes to calculate the distance of each route gets much longer. We saw this as a perfect place to implement threading since the calculation of each routes distance does not rely on any other information. Now at the after each evolveGeneration call, we have the threads find the distance of the routes and cache them for the next evolveGeneration to use.

# Design Patterns
## Flyweight
We share the ParcelList and its Parcels with all routes.

## Module
Many of the GA methods are singletons. By grouping them into GA, it makes the number of files smaller.

## Adapter
In ConnectionManager, we have to translate or adapt data from the population object into something that the UI can understand. This also helps to avoid sending more data than required.