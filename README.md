# Heuristics

## HEURISTIC1:

**1. Tower placement:**

1. Here we use K-Medoid clustering algorithm to cluster the shelter points based on number of towers.
2. After clustering the center of the clusters chooses as the tower placement point because here we have assume that the range of the tower is enough to communicate between the centers.


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
