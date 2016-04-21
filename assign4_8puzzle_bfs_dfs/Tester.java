/* Tester Class for Implementation of 8puzzle*/

/*
			Author -  Raghuvar Prajapati
			ID - 201351003(CSE)

	*/



import java.util.Scanner;


public class Tester {
	public static void main(String[] args)
	{
        Scanner reader = new Scanner(System.in);
				System.out.println("\n ***************  8Puzzle Problem Using BFS and DFS *************** \n ");
        System.out.println("Enter the Initial state of Input Matrix\n ");
        System.out.println("You can put your Input Matrix as(Numbers Continuously) : 234015786");
        String input=reader.next();


        System.out.println("Press key 1 for DFS and 2 for BFS");
        int n = 0;
        do{n=reader.nextInt();}while(n!=1 && n!=2);

        if(n==1)
        {
          DepthFS d1=new DepthFS(input,"123456780");
          d1.doSearch();
        }
        else if(n==2)
        {
          BreadthFS b1=new BreadthFS(input,"123456780");
        b1.doSearch();
        }
	}
}
