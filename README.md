# Genetic Drone

## 339 Proposal & group info
Ken Kohl
Alex Berns
Rex Borseth

Our goal is to develop an application to determine the best path for a drone delivering packages. This problem is a derivation of the traveling salesman problem, thus it is usually an NP-Complete problem. We plan to find a possible solution by using a genetic algorithm. This GA will be multithreaded to speed up calculating the fitness of each member of the population. We will develop this project in Java using either the JavaFX library or HTML for the UI. 


Due to the nature of the GA, the solution might not be the absolute best but will allow us to find a good enough solution quickly, thus ideal for a real world use.


The UI allow the user to add packages that consist of x and y coordinates for the drone to travel to. The UI will display the list along with a graph showing the best route.
