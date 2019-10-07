import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Arrays;

public class GeneticAlgorithm {
	Individual[] Population;
	PriorityQueue<Individual> sortQueue;
	int popCap;
	int muteRate;
	int rangeStart;
	int rangeEnd;
	int problemSize;
	int childrenCap;
	Random rand;
	int maxGen;
	int currentGen;
	Heuristic h;
	
	public GeneticAlgorithm( int popCap, int muteRate, int rangeStart, int rangeEnd, int problemSize, int maxGen, int childrenCap, Heuristic h) {
		this.popCap = popCap;
		this.muteRate = muteRate;
		this.rangeStart = rangeStart;
		this.rangeEnd = rangeEnd;
		this.problemSize = problemSize;
		rand = new Random();
		this.maxGen = maxGen;
		this.childrenCap = childrenCap;
		currentGen = 0;
		this.h = h;
		
		Population = new Individual[popCap];
		sortQueue = new PriorityQueue<Individual>();
		initialPop();
	}
	
	public Individual findSolution() {
		Individual ind = null;
		boolean found = false;
		while(!found) {
			ind = checkSol(ind);
			if (ind != null)
				found = true;
			
			if (found)
				break;
			
			nextGeneration();
			currentGen++;
			if(currentGen >= maxGen)
				throw new RuntimeException("The generation limit has been exceeded");
		}
		
		
		return ind;
	}
	
	private void nextGeneration() {
		for(int i = 0; i <childrenCap; i++)
		{
			int fusionPoint = rand.nextInt(problemSize - 1);
			int parent1 = rand.nextInt(popCap);
			int parent2 = rand.nextInt(popCap);
			while (parent1 == parent2) {
				parent2 = rand.nextInt(popCap);
			}
			int[] genetics = combine(Population[parent1].parent1(fusionPoint), Population[parent2].parent2(fusionPoint));
			Individual ind = new Individual(genetics, h);
			if (rand.nextInt(100) < muteRate)
				ind.mutate( rand.nextInt(problemSize), rand.nextInt(rangeEnd - rangeStart) + rangeStart);
			sortQueue.add(new Individual(genetics, h));
		}
		for(int i = 0; i < Population.length; i++ )
		{
			Population[i] = sortQueue.remove();			
		}
		sortQueue.clear();
		
	}

	private int[] combine(int[] parent1, int[] parent2) {
		int[] combined= new int[parent1.length + parent2.length];
		for(int i = 0; i < parent1.length; i++) {
			combined[i] = parent1[i];
		}
		for(int i = parent1.length; i < combined.length; i++) {
			combined[i] = parent2[i - parent1.length];
		}
		
		return combined;
	}

	void initialPop() {
		for(int i = 0; i < childrenCap; i++)
		{
			int[] genetics = new int[problemSize];
			for(int j = 0; j < problemSize; j++) {
				genetics[j] = rand.nextInt(rangeEnd - rangeStart) + rangeStart;
			}
			sortQueue.add(new Individual(genetics, h));
		}
		for(int i = 0; i < Population.length; i++ )
		{
			Population[i] = sortQueue.remove();			
		}
		sortQueue.clear();
		
	}

	Individual checkSol(Individual ind) {
		for(Individual in : Population) {
			if(in.fitness() == 0) {
				System.out.println(currentGen);
				return in;
			}
		}
		
		return null;
	}
	
}
