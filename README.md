# Heuristics

## HEURISTIC1:

**1. Tower placement:**

1. Here we use K-Medoid clustering algorithm to cluster the shelter points based on number of towers.
2. After clustering, the center of the clusters chooses as the tower placement point because here we have assume that the range of the tower is enough to communicate between the centers.


**2.Data mule Distribution:**

1. Mule is distributed uniformly,
   if number of mules = m
   
      number of towers = t
      
      number of mules to each cluster = floor(m/t)
      
2. After uniform distribution if some mules are unallotted to any tower then those will be given to the tower(cluster) which is having highest cost. 
   Cost is calculated as the sum of distances between tower to the each shelter point of same cluster.
   

**3. Mule Trajectory:**

1. All mules initially at the center(Tower).
2. Every mule start from tower to the nearest shelter point of the same cluster.
3. Next time stamp each mule will go to the next nearest shelter point of same cluster.
4. This process will going on until all shelter points have covered.
5. After covering every shelter point each mule will reverse back to the tower with shortest path.

## Heuristic 2(Idea):

**1. Tower placement:**

*-Same as Heuristic 1.*


**2.Data mule Distribution:**

1. Let G1,G2,…,Gn be the group of DB’s covered by T1,T2,…,Tn, then we find cost of each group using TSP (Travelling Salesman Problem).
2. Let X1,X2,…Xn be the cost of each group. We will calculate

   C1=X1*mX1+X2+…+Xn,

   C2=X2*mX1+X2+…+Xn,

   .

   .

   .

   CnXn*mX1+X2+…+Xn.

   (C1,C2,…Cn will be secondary costs of each group).
  
3. Now arrange C1,C2,…,Cn in descending order.
4. Initially distribute floorC1,floorC2,…floorCn No. of mules to T1,T2,…,T3 respectively.
5. Remaining data mules will be given to the tower which is having the greatest cost. 

 *Note: - If any tower having secondary cost < 1, then that tower will have only one Data mule.*
 

**3.Mule Trajectory:**

*-Same as Heuristic 1.*


## Heuristic 3(idea):

**1. Tower Placement:**
1. Let G1, G2,…,Gn is the group of Shelter points and let P1, P2,..., Pn be the center of groups respectively and the available number of towers is n.
Note: In the first heuristic, we have assumed that the towers will form a  connected network after placement (Range is enough to connect the tower in Disaster-affected area).

2. Assign a tower in MCS

3. Repeat for all Pi which is not MCS.
            
            Suppose if the range of the tower is given (r), P1 is the MCS and the distance between P1 and Pi is greater then the range of the tower.
            
            Then we have to check the distance between P1(MCS) and the nearest Shelter points from the center of the same cluster where Pi lies.
            
            If we found the Shelter point which is in the range of MCS(P1) then we place the ith tower on it.
         Until all the SP’s groups are checked
         
   Pi - Center of the ith cluster or group
         

**2. Data Mule Distribution:**

*-Same as Heuristic 2.*


**3. Mule Trajectory:**

*-Same as Heuristic 1.*





## Discussion(for further heuristics):
1. If only one tower is placed and we are not able to put more than one towers then undo the step 2 and we assume that there is only one cluster and that is the whole disaster-affected area here we need to find the cluster center based on cost. Here the available towers will be unused. 
Note: Cost of a group is equal to the summation of distance between every shelter point and Group center of the group.
  
2. If more than one tower can be placed but not all the towers, then we need to merge the clusters which have no tower with the nearest cluster which have a tower in its cluster center.


3. If all the available towers are placed then it is fine (best condition)
