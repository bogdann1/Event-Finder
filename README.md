# Viagogo Event-Finder
The program accepts a user location as a pair of coordinates, and returns a list of the five closest events, along with the cheapest price. The program randomly generates seed data in a world that ranges from -10 to 10 on both X and Y axis. Each coordinate can hold only one event that has a unique numerid identifier (eg. 1,2,3,..). Each event has zero or more tickets, which has a non-zero price expressed in US Dollars. The distance between two points is computet as Manhattan distance.
## Getting Started
These instructions will get you a copy of the project up and running on your local machine.
### Prerequisites
```
Java SE Development Kit 8
```
### Build & Run instructions: 
Head to the src/ file of the project, after you clone it.
Run the following commands in your terminal:
```
>javac *.java
>java Example
Insert coordinates in the following format: x,y > 
2,4
```
After you type the input coordinates in the instructed format (eg. 2,3 or 4,2 ... pair x,y ), without exceeding the imposed limits -10 and 10, the terminal should display the result in the following format:
```
Event 147 - $23.05, Distance 1
Event 173 - $10.11, Distance 1
Event 160 - $49.19, Distance 1
Event 172 - $11.40, Distance 3
Event 136 - $22.04, Distance 4
```
### Assumptions I have made.
These are some assumptions I made regarding the system. Some of them were made in a convenient way for the suggested scenario (eg. `%` chances for seeding, virtualizing the world in a 2-D Array) and some of them are not relevant for the performance of the system (eg. ticket prices).
* if the input is not correct, I assumed the program should terminate, displaying a suggestive error
* the map should be a `2-D Array` for the proposed scenario
* `50%` chances for a map location to have an event
* `50%` for an event to be populated with tickets
* the map is square matrix shaped
* the map has its centre at `(0,0)` co-ordinates
* the minimum ticket price is `1` 
* the maximum ticket price is `100`
* the minimum number of available tickets for an event is `1`
* the maximum number of available tickets for an event is `50`
* the minimum ticket price is `10`
* the maximum ticket price is `100`
### Questions to consider
1. How might you change your program if you needed to support multiple events at the
same location?
   - Considering the design and actual dependencies of the current system, the Location class should be modified such that it holds more than one event. This also comes with minor modifications for some of the methods of the class (would required iterating through the events for `printEventCheapestTicket()` and `hasTicketsAvailable()` methods).
   - Another class that would be affected is the Map class, that handles the populating methods for the 2-D map. It would be necessary to modify the `generateLocationWithRandomEvent()` such that it considers generating a list of Events that would be fed to the Location's constructor.
   
  1. How would you change your program if you were working with a much larger world
size?
     - There were two possible data structures that I considered to store the map: a `Map/HashMap` or a Tree-like data structure, more precisely an `R-Tree` data structure.
     - For the HashMap option, it would look something like `HashMap<Pair,Event>`. However, a HashMap may store the data in a more efficient way than a 2-D Array, but it does not offer any advantages for the purpose of the system - **Nearest Neighbor Search**
     - Thus, an **R-Tree** data structures seems to be the most appealing solution for storing the data, but especially for finding the Nearest Neighbor of a point. 
     - In this way, the data would be divided into **regions/rectangles**. A non-leaf node stores two pieces of data: a way to identify its children, and a **bounding box** for the children (hence, dividing the map in regions, and each region being subdivided in more regions as new entries are added).
     - The leaves of the R-Tree would be actual Event objects as they are already defined.
     - The insertion process is the disadvantage of using such a data structure for virtualizing our map. This is given because at every insertion, the tree is traversed recursively from the root and examines all rectangles at each step. However, we may consider that when working with a much larger world size, this process would not be repeated every time the system is started, as it should be a process handled separately.
     - The most important aspect of using an R-Tree is that it gives `O(log n)` average complexity for searching algorithms, but it is especially practical for **Nearest Neighbor Search** as the entries are already grouped in regions. As follows, the problem reduces to a **Range Search** around position Q, let's say. We find a number of events around Q (eg. O1, O2, O3, ...), we compute the distances and display them ordered by the distance. If our desired number of events found (_n_) is not achieved, we begin the process again from the closest neighbor of Q, expanding the region of search.
