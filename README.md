# Heuristics


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
