/* Implementation of 8puzzle using Breadth First Search */

/*
			Author -  Raghuvar Prajapati
			ID - 201351003(CSE)

	*/


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class BreadthFS
{
	String str = "";         // Define Initial state
	String goal = "";        //Define Goal state

	LinkedList <String> openList;// openlist

	Map<String,Integer> levelDepth;//map to store currentState and level

	Map<String,String> stateHistory;//map to store currentstate and its parent

	int nodes = 0; //counter for node generation
	int limit = 1000000; //counter for limit
	int unique = -1;
	int newValue;
	int a;

	String currState;//variable hold currState
	boolean solution = false;//flag if solution exist or not

	BreadthFS(String str,String goal)      // BFS algorithm i
	{
		openList = new LinkedList <String> ();
		levelDepth = new HashMap<String, Integer>();
		stateHistory = new HashMap<String,String>();
		this.str = str;
		this.goal = goal;
		addToOpenList(str,null);//add root
	}

	void doSearch ()
	{

		while (!openList.isEmpty())
		{

			currState = openList.removeFirst();
			if (currState.equals(goal))
			{
				solution = true;
				printSolution(currState);
				break;
			}

			if (levelDepth.get(currState) == limit)
			{
				solution = false;
				printSolution(currState);
				break;
			}

			else {

				a = currState.indexOf("0");// get index position of 0 (blank)

				//left
				while (a != 0 && a != 3 && a != 6)
				{
					String nextState = currState.substring(0,a-1)+"0"+currState.charAt(a-1)+currState.substring(a+1);//swap blank with destination
					addToOpenList(nextState, currState);
					nodes++;
					break;
				}

				//up       (if blank not in the very top of row then it able to move up)
				while (a!=0 && a!=1 && a!=2)
				{
					String nextState = currState.substring(0,a-3)+"0"+currState.substring(a-2,a)+currState.charAt(a-3)+currState.substring(a+1);//swap blank with destination
					addToOpenList(nextState, currState);
					nodes++;
					break;
				}

				//right
				while(a != 2 && a != 5 && a != 8)
				{
					String nextState = currState.substring(0,a)+currState.charAt(a+1)+"0"+currState.substring(a+2);
					addToOpenList(nextState, currState);
					nodes++;
					break;
				}

				//down
				while (a != 6 && a != 7 && a != 8)
				{
					String nextState = currState.substring(0,a)+currState.substring(a+3,a+4)+currState.substring(a+1,a+3)+"0"+currState.substring(a+4);//swap blank with destination
					addToOpenList(nextState, currState);
					nodes++;
					break;
				}

			}

		}

		if (solution)
			System.out.println("Solution Exist");

		else
		{
			System.out.println("Error! First run the BreadthFS java Code ");
		}

	}

	private void addToOpenList (String newState, String oldState)
	{
		if(!levelDepth.containsKey(newState))
		{
			newValue = oldState == null ? 0 : levelDepth.get(oldState) + 1;
			unique ++;
			levelDepth.put(newState, newValue);
			openList.add(newState);         //Specific to BFS Algorithm
			stateHistory.put(newState, oldState);
		}

	}

              /* Print all Nodes and their correspondence */

	void printSolution (String currState)
	{
		if (solution)
		{
			System.out.println("So using BFS 8Puzzle problem can be solved in " +levelDepth.get(currState)+" step(s)");
			System.out.println("Generated Node during solving the puzzle: "+ nodes);
			System.out.println("Generated Unique Node during solving the puzzle: "+ unique);
		}

		else
		{
			System.out.println("The Solution that you are looking for is not found!");
			System.out.println("Maximum Depth Limit Reached!");
			System.out.println("All generated Node : "+ nodes);
			System.out.println("Generated Unique Node : "+ unique);
		}

		String traceState = currState;
		while (traceState != null)
		{
			System.out.println(traceState + " at " + levelDepth.get(traceState));
			try
			{
				for(int z=0;z<9;z++)
				{
					System.out.print(" " + String.valueOf(traceState.charAt(z)) + " ");
					if ((z+1) % 3 == 0){System.out.println();}
				}
			}
			catch (NullPointerException e) {}
			traceState = stateHistory.get(traceState);
		}
	}
}
