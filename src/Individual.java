import java.util.Comparator;

public class Individual implements Comparable<Individual> {
	int[] genetics;
	Heuristic h;

	public Individual(int[] genetics, Heuristic h) {
		this.genetics = genetics;
		this.h = h;
	}
	
	
	int[] parent1(int n) {
		int returnArray[] = new int[n];
		for(int i = 0; i <n; i++){
			returnArray[i] = genetics[i];
		}
		return returnArray;
	}
	
	int[] parent2(int n) {
		int returnArray[] = new int[genetics.length-n];
		for(int i = n; i < genetics.length; i++){
			returnArray[i-n] = genetics[i];
		}
		return returnArray;
	}
	
	int fitness() {
		return h.operation(genetics);
	}
	
	void mutate(int pos, int num) {
		genetics[pos] = num;
	}
	
	int[] getGenetics() {
		return genetics;
	}

//	public int compare(Individual o1, Individual o2) {
//		return ((Individual) o1).fitness() - ((Individual) o2).fitness();
//	}
	
	public int getElement(int n) {
		if (n < genetics.length)
			return genetics[n];
		else
			return -1;
	}

	public int compareTo(Individual arg0) {
		return fitness() - arg0.fitness();
	}


	
	
	
}
