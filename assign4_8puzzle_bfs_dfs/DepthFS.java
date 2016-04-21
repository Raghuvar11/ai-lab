/* Implementation of 8puzzle using Depth First Search */

/*
			Author -  Raghuvar Prajapati
			ID - 201351003(CSE)

	*/


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class DepthFS
{

String str = "";  			 // Initialize Initial state
String goal = ""; 			//Initialize Goal state

LinkedList <String> openList;			// Openlist

Map<String,Integer> levelDepth;

Map<String,String> stateHistory;

int nodes = 0; //counter for node generation
int limit = 1000000000; //counter for limit
int unique = -1;
int newValue;//counter for level depth
int a;

String currState;
boolean solution = false; // If solution does not exist

DepthFS(String str,String goal)      // Depth First Search Algorithm
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

		else {
			// Backtrack if reach limit depth; it is basicly just not generate any more node and retrive head of openlist

			if (levelDepth.get(currState) < limit)
			{
				a = currState.indexOf("0");
				//left
				while (a != 0 && a != 3 && a != 6)
				{
					String nextState = currState.substring(0,a-1)+"0"+currState.charAt(a-1)+currState.substring(a+1);
					addToOpenList(nextState, currState);
					nodes++;
					break;
				}

				//up
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
					String nextState = currState.substring(0,a)+currState.charAt(a+1)+"0"+currState.substring(a+2);//swap blank with destination
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

	}

	if (solution)
		System.out.println("Solution Exist");

	else
	{
		System.out.println("Error! First Run the DepthFS java code");
	}

}

private void addToOpenList (String newState, String oldState)
{
	if(!levelDepth.containsKey(newState))
	{// check repeated state
		newValue = oldState == null ? 0 : levelDepth.get(oldState) + 1;
		unique ++;
		levelDepth.put(newState, newValue);
		openList.addFirst(newState);
		stateHistory.put(newState, oldState);
	}
}

void printSolution (String currState)
{
	if (solution)
	{
		System.out.println("So using DFS 8Puzzle Problem can be solved in " +levelDepth.get(currState)+" step(s)");
		System.out.println("Generated Node during solving the puzzle : "+ nodes);
		System.out.println("Generated Unique Node during solving the puzzle : "+ unique);
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
			for(int z=0;z<9;z++){
				System.out.print(" " + String.valueOf(traceState.charAt(z)) + " ");
				if ((z+1) % 3 == 0){System.out.println();}
			}
		}
		catch (NullPointerException e) {}
		traceState = stateHistory.get(traceState);

	}
}
}
