import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		GeneticAlgorithm alg;
		int popCap = 30;
		int muteRate = 50; 
		int rangeStart = 0; 
		int problemSize = 25;
		int maxGen = 5000;
		int childrenCap = 120;
		Scanner in = new Scanner(System.in);
		
		for(int i = 0; i < args.length; i++)
		{
			if(args[i].equals("--popCap")) {
				popCap = Integer.parseInt(args[i + 1]);
				i++;
			}
			if(args[i].equals("--muteRate")) {
				muteRate = Integer.parseInt(args[i + 1]);
				i++;
			}
			if(args[i].equals("--probSize")) {
				problemSize = Integer.parseInt(args[i + 1]);
				i++;
			}
			if(args[i].equals("--maxGen")) {
				maxGen = Integer.parseInt(args[i + 1]);
				i++;
			}
			if(args[i].equals("--chilCap")) {
				childrenCap = Integer.parseInt(args[i + 1]);
				i++;
			}
		}
		
		int rangeEnd = problemSize;
		
		Heuristic h = (t) -> {
		int AttackingQueenPairs = 0;
		for(int i = 0; i < t.length - 1; i++) {
			for(int j = 1; j + i < t.length; j++) {
				if(t[i] == t[i+j])
					AttackingQueenPairs++;
				else if(t[i] + j == t[i+j])
					AttackingQueenPairs++;
				else if(t[i] - j == t[i+j])
					AttackingQueenPairs++;
			}
		}
		return AttackingQueenPairs;
		};
		
		
		long time = System.nanoTime();
		Individual ind = null;
		try {
				
			alg = new GeneticAlgorithm(popCap, muteRate, rangeStart, rangeEnd, problemSize, maxGen, childrenCap, h);
			ind = alg.findSolution();
			time = System.nanoTime() - time;
		}
		catch(Exception e) {
			System.out.println("Failure");
		}
			
			
			System.out.println("The soultion found was:");
		for(int i = 0; i < problemSize; i++)
		{
			System.out.print(ind.getElement(i) + " ");
		}
		
		
		
		System.out.println("It took " + time + " to find the solution");
		in.nextLine();
		in.close();

	}

}
