import java.util.*;

class H3
{
	static int n;
	static int k;
	static int[] visited=new int[n+1];
	static double[][] pathway;
	static double[][] a;

	public static void trajectory(int[] tower,int[] tempclusters)
	{
		System.out.println("\nenter no. of mules");
		Scanner scan=new Scanner(System.in);

		int mules=scan.nextInt();
		int[] alotted_mules=new int[k+1];

   		int divide=(int)Math.floor(mules/k);

   		//Finding cost of cluster
   		double[] cost=new double[k+1];

   		for(int i=1;i<=k;i++)
   		{
   			for(int j=1;j<=n;j++)
   			{
   				int pos=tower[i];
   				if(tempclusters[j]==pos && j!=pos)
   				{
   					cost[i]+=pathway[pos][j];
   					//System.out.print("123");
   				}
   			}
   		}


   		/*for(int i=1;i<=k;i++)
   		{
   			System.out.print(tower[i]+" ");
   		}*/

   		//finding number of nodes in each cluster
   		int[] nodes_in_cluster=new int[k+1];
   		for(int i=1;i<=k;i++)
   		{
   			for(int j=1;j<=n;j++)
   			{
   				if(tempclusters[j]==tower[i])
   				{
   					nodes_in_cluster[i]++;
   				}
   			}
   		}

   		//finding highest cost cluster
   		double max=0;
   		int highest_cost_cluster=0;
   		for(int i=1;i<=k;i++)
   		{
   			if(cost[i]>max)
   			{
   				max=cost[i];
   				highest_cost_cluster=i;
   			}
   		}

   		//System.out.print(highest_cost_cluster);
   		int mules1=mules;


   		//Propotional mule distribution
   		
   		/*for(int i=1;i<=k;i++)
   		{
   			if(i!=highest_cost_cluster)
	   		{	
	   			alotted_mules[i]=divide;
	   			mules1-=divide;
	   		}
   		}
   		alotted_mules[highest_cost_cluster]=mules1;*/

   		double sum_of_cost=0;
   		for(int i=1;i<=k;i++)
   		{
   			sum_of_cost+=cost[i];
   		}

   		double[] secendary_cost=new double[k+1];
   		double[] secendary_cost_temp=new double[k+1];

   		for(int i=1;i<=k;i++)
   		{
   			secendary_cost[i]=(cost[i]*mules)/sum_of_cost;
   			secendary_cost_temp[i]=secendary_cost[i];
   		}

   		//System.out.println(secendary_cost[1]);

   		double temp1=0;
		for(int i=1;i<=k;i++)
		{
			for(int j=2;j<=(k-i);j++)
			{
			if(secendary_cost_temp[j-1]<secendary_cost_temp[j])
			{
				temp1=secendary_cost_temp[j-1];
				secendary_cost_temp[j-1]=secendary_cost_temp[j];
				secendary_cost_temp[j]=temp1;
			}
			}
		}

		for(int i=1;i<=k;i++)
		{
			System.out.print(secendary_cost_temp[i]+" ");
		}
		System.out.println();
		/*for(int i=1;i<=k;i++)
		{
			System.out.print(secendary_cost_temp[i]);
		}*/

		for(int i=1;i<=k;i++)
		{
			if(secendary_cost_temp[i]>=1)
			{
				for(int j=1;j<=k;j++)
				{
					if(secendary_cost_temp[i]==secendary_cost[j])
					{
						alotted_mules[j]=(int)Math.floor(secendary_cost[i]);
						mules1-=alotted_mules[j];
					}
				}
			}
			else if(secendary_cost_temp[i]==0)
			{
				for(int j=1;j<=k;j++)
				{
					if(secendary_cost_temp[i]==secendary_cost[j])
					{
						alotted_mules[j]=0;
						mules1-=alotted_mules[j];
					}
				}
			}
			else
			{
				for(int j=1;j<=k;j++)
				{
					if(secendary_cost_temp[i]==secendary_cost[j])
					{
						alotted_mules[j]=1;
						mules1-=alotted_mules[j];
					}
				}
			}
		}
   		if(mules1>0)
   		{
   			alotted_mules[highest_cost_cluster]+=mules1;
   		}

   		System.out.println("\nPropotional mule distribution as");
   		for(int i=1;i<=k;i++)
   		{
   			System.out.println("Cluster "+i+ "= "+ alotted_mules[i]);
   		}
   		System.out.println();


   		//initial location of mules
   		int[] mule_location=new int[mules+1];
   		int[] initial_mule_location=new int[mules+1];
   		int j=1;

   		for(int i=1;i<=k;i++)
   		{
   			int x=alotted_mules[i];
   			while(x>0)
   			{
   				mule_location[j]=tower[i];
   				initial_mule_location[j]=tower[i];
   				x--;
   				j++;
   			}
   		}

   		System.out.println("Initial mules location");
   		for(int i=1;i<=mules;i++)
   		{
   			System.out.println("mule "+i+" -> "+mule_location[i]);
   		}
   		System.out.println();

   		//finding the trajectories of each mule
   		int x=n-k;
   		double min=99999.0;
   		int index=-1;
   		int[] visited=new int[n+1];

   		ArrayList<Queue<Integer>> path=new ArrayList<Queue<Integer>>(mules+1);
   		for(int i=0;i<=mules;i++)
   		{
   			Queue<Integer> temp=new LinkedList<Integer>();
   			path.add(temp);
   		}

   		for(int i=1;i<=mules;i++)
   		{
   			path.get(i).add(initial_mule_location[i]);
   		}
   		//System.out.println(path.size());

   		for(int i=1;i<=k;i++)
   		{
   			visited[tower[i]]=1;
   		}

   		while(x>0)
   		{
   			for(j=1;j<=mules;j++)
   			{
   				if(x<=0)
   					break;

   				min=99999.0;
   				index=-1;

   				for(int i=1;i<=n;i++)
   				{
   					if(i!=tempclusters[i] && tempclusters[i]==tempclusters[mule_location[j]] && visited[i]==0 && pathway[mule_location[j]][i]<=min)
   					{
   						min=pathway[mule_location[j]][i];
   						index=i;
   					}
   				}
   				if(index!=-1)
   				{
   					mule_location[j]=index;
   					//System.out.println(j);
   					path.get(j).add(index);
   					visited[index]=1;
   					x--;
   				}
   			}
   			
   		}

   		for(int i=1;i<=mules;i++)
   		{
   			path.get(i).add(initial_mule_location[i]);
   		}

   		System.out.println("Path of each mule");

   		for(int i=1;i<=mules;i++)
   		{
   			System.out.println(path.get(i));
   		}
   		
   		System.out.println("\nEnter velocity(km/h) of DM");
   		int velocity=scan.nextInt();

   		int temp2=0,temp3=0;
   		double dist;
   		for(int i=0;i<path.size();i++)
   		{
   			if(path.get(i).size()>0)
   				temp2=path.get(i).remove();
   			
   			dist=0;
   			while(path.get(i).size()>0)
   			{
   				temp3=path.get(i).remove();

   				dist+=pathway[temp2][temp3];

   				temp2=temp3;
   			}
   			if(dist>max)
   			{
   				max=dist;
   				index=i;
   			}
   		}

   		System.out.println();
   		System.out.println("mule "+index+" having highest latency cost, hence overall latency of the scnerio is = "+(max/velocity)*60+" minutes\n");
   		//System.out.println(divide);
	
	}


	public static void tower(int[] medoid,int[] tempclusters)
	{	
		double d=0.0;
		int flag=0;
		Scanner scan=new Scanner(System.in);
		
		System.out.println("\nEnter range of the tower");
		double range=scan.nextDouble();
			
		/*for(int j=1;j<=n;j++)
		{
			System.out.print(tempclusters[j]+" ");
		}
		System.out.println();
		for(int j=1;j<=k;j++)
		{
			System.out.print(medoid[j]+" ");
		}
		System.out.println();*/

		int[] tower=new int[k+1];
		tower[1]=medoid[1];
		
		int[] clutster_visited=new int[n];

		for(int i=2;i<=k;i++)
		{	
			flag=0;
			for(int j=1;j<=k;j++)
			{
				if(a[tower[j]][medoid[i]]<=range)
				{
					tower[i]=medoid[i];
					flag=1;
					break;
				}
				else
				{
					for(int m=1;m<=n;m++)
					{
						int min=999999;
						int l;
						int index1;

						for(l=1;l<=n;l++)
						{
							if(tempclusters[l]==medoid[i] && a[tower[j]][l]<=min && clutster_visited==0 && tempclusters[l]!=medoid[i])
							{
								min=a[tower[j]][l];
								index1=l;
							}
						}
						if(a[tower[j]][index]<=range)
						{
							tower[i]=l;
							flag=1;
							break;
						}
						else
						{
							clutster_visited[l]=1;
						}
					}
				}
				if(flag==0)
				{
					System.out.println("towers not in range");

					int min=999999;
					int index1;

					for(l=1;l<=n;l++)
					{
						if(tempclusters[l]==medoid[i] && a[tower[j]][l]<=min && tempclusters[l]!=medoid[i])
						{
							min=a[tower[j]][l];
							index1=l;
						}
					}
					System.out.println("Range should be = "+min+" and "+i+"th tower will be on "+index1);
					System.exit(0);
				}
			}
		}
		
		System.out.println();
		for(int i=1;i<=k;i++)
		{
			System.out.println("Tower "+i+" is at "+tower[i]);
		}
		
		for(int i=1;i<=k;i++)
		{
			if(tower[i]!=medoid[i])
			{
				for(int j=1;j<=n;j++)
				{
					if(tempclusters[j]==medoid[i])
					{
						tempclusters[j]=tower[i];
					}
				}
				medoid[i]=tower[i];
			}
		}

		/*for(int i=1;i<=n;i++)
		{
			for(int j=1;j<=n;j++)
			{
				System.out.print(a[i][j]+" ");
			}
			System.out.println();
		}*/

		trajectory(tower,tempclusters);
	}


	public static void kme()
	{
   		int[] medoid=new int[k+1];
   		int[] nonmedoid=new int[n-k+1];
   		int[] initialmedoid=new int[k+1];
   		int temp,flag=0;
   		
   		Scanner scan = new Scanner(System.in);
   		System.out.println("\nenter "+k+" initial centres");

   		for(int i=1;i<=k;i++)
   		{
   			initialmedoid[i]=scan.nextInt();
   			medoid[i]=initialmedoid[i];
   		}
 		
   		int[] clusters=new int[n+1];
   		int[] tempclusters=new int[n+1];
		double[] distances=new double[k+1];
		double[][] distances1=new double[n+1][k+1];
		double min=10000.0;
		int index=0;


   		for(int i=1;i<=n;i++)
		{
	    	Arrays.fill(distances,0);
	    	min=100000;
	    

	    	for(int j=1;j<=k;j++)
	    	{
				distances[j]=pathway[medoid[j]][i];
				if(distances[j]<min)
				{
		    		min=distances[j];
		    		index=j;
		    		clusters[i]=medoid[index];
				}
	    	}
	    	distances1[i][index]=min;
		}

		System.out.println("\ninitial clusters");

		for(int i=1;i<=k;i++)
		{
			System.out.print(medoid[i]+"  ( ");
			for(int j=1;j<=n;j++)
			{
				if(clusters[j]==medoid[i])
				{
					System.out.print(j+" ");
				}
			}
			System.out.print(")");
			System.out.println();
		}


		double[] sum=new double[k+1];
		double[] sum1=new double[k+1];
		
		for(int i=1;i<=k;i++)
		{
			double add=0;
			for(int j=1;j<=n;j++)
			{
				add+=distances1[j][i];
			}
			sum[i]=add;
			//sum1[i]=add;
		}
		
		
		while(flag==0)
		{
			int x=1;
			for(int i=1;i<=n;i++)
			{	
				int c=0;
				for(int j=1;j<=k;j++)
				{
					if(i!=medoid[j])
					{
						c++;
					}
					if(c==k)
					{
						nonmedoid[x]=i;
						x++;
					}
				}
			}
			
			double[][] distances2=new double[n+1][k+1];
			int[] tempmedoid=new int[k+1];
			
			for(int i=1;i<=k;i++)
			{
				initialmedoid[i]=medoid[i];
				tempmedoid[i]=medoid[i];
	       		}
			//int[] sum2=new int[k+1];
		
			
			for(int o=1;o<=k;o++)
			{
				for(int i=1;i<=n;i++)
				{
					for(int j=1;j<=k;j++)
					{
						sum1[j]=0;
						distances2[i][j]=0;
					}
				}
				
				for(int m=1;m<=n-k;m++)
				{
					
					tempmedoid[o]=nonmedoid[m];
					//Arrays.fill(clusters,0);
					//System.out.print(tempmedoid[o]+" ");
					
					min=1000;
					index=0;
					
					for(int i=1;i<=n;i++)
					{
					    
					    Arrays.fill(distances,0);
					   // Arrays.fill(clusters,0);
					    min=1000;
					    

					    for(int j=1;j<=k;j++)
					    {
							distances[j]=pathway[tempmedoid[j]][i];
							if(distances[j]<min)
							{
							    min=distances[j];
							    index=j;
							    clusters[i]=tempmedoid[index];
							    
							}
					    }
					    distances2[i][index]=min;
					   
					}
							
					
					
					for(int i=1;i<=k;i++)
					{
						int add=0;
						for(int j=1;j<=n;j++)
						{
							add+=distances2[j][i];
						}
						sum1[i]=add;
					}
						
					
					if(sum[o]>sum1[o])
					{	
						sum[o]=sum1[o];
						tempmedoid[o]=nonmedoid[m];
						temp=nonmedoid[m];
						nonmedoid[m]=medoid[o];
						medoid[o]=temp;
						
						
						for(int i=1;i<=n;i++)
							tempclusters[i]=clusters[i];
						
						
					}
					else
					{
						tempmedoid[o]=medoid[o];
						sum[o]=sum[o];
						medoid[o]=medoid[o];
					}	
				}
			}
			
			int count3=0;
			for(int i=1;i<=k;i++)
			{
				if(medoid[i]==initialmedoid[i])
				{
					count3++;
				}
			}
			
			if(count3==k)
				flag=1;
		}
		
		System.out.println();
		//System.out.println("After applying KMedoid algorithm, centers are "+medoid[1]+" "+medoid[2]+" "+medoid[3]+" are");
			
		for(int i=1;i<=k;i++)
		{
			System.out.print(medoid[i]+"  ( ");
			for(int j=1;j<=n;j++)
			{
				if(tempclusters[j]==medoid[i])
				{
					System.out.print(j+" ");
				}
			}
			System.out.print(")");
			System.out.println();
		}

		/*for(int i=1;i<=k;i++)
   		{
   			System.out.println(sum[i]+" ");
   		}*/
	
		tower(medoid,tempclusters);
    	}


	public static void main(String[] args)
	{
		Scanner scan=new Scanner(System.in);

		System.out.println("\nenter no. of DB's");
		n=scan.nextInt();

		a=new double[n+1][n+1];
		double points[][]=new double[n+1][2];
		double d=0.0;
		pathway=new double[n+1][n+1];

		System.out.println("\nEnter x and y coordinates of every node");

		/*medoid[1]=1;
		medoid[2]=5;
		medoid[3]=10;*/

		for(int i=1;i<=n;i++)
		{
			points[i][0]=scan.nextDouble();
			points[i][1]=scan.nextDouble();
		}

		for(int j=1;j<=n;j++)
		{
			for(int i=1;i<=n-1;i++)
			{
				d=Math.sqrt(Math.pow(Math.abs(points[i+1][0]-points[j][0]),2)+Math.pow(Math.abs(points[i+1][1]-points[j][1]),2));
				a[j][i+1]=d/1000;
				a[i+1][j]=d/1000;
			}
		}

		for(int i=1;i<=n;i++)
		{
			for(int j=1;j<=n;j++)
			{
				System.out.print(a[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("\nEnter pathway matrix");
		for(int i=1;i<=n;i++)
		{
			for(int j=1;j<=n;j++)
			{
				pathway[i][j]=scan.nextDouble();
				//pathway[j][i]=pathway[i][j];
			}
		}
	    
	    
	    	System.out.println("\nenter no. of clusters");
	   	k=scan.nextInt();

	    	kme();
	}
}
