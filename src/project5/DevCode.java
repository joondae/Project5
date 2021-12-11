package project5;

public class DevCode {

	//MOUNTAINHIKE
	//validateInputFile()
	//CONSOLIDATE WITH ABOVE IF STATEMENT?
	/*
	if(args[0] == null) {
		System.err.println("Usage Error:"
				+ " the program expects file name as an argument.\n");
		System.exit(1);
	}
	*/
	
	//processInputFile()
	//Is there a need to deal with non-visible ascii characters?
	//\n considered a whitespace delimiter regex used
	//Split on leading and trailing whitespace (necessary?)
	//String[] arr = br.readLine().split("\\s");
	
	
	
	
	
	//RESTSTOP
	//storing supplies and obstacles in arrays instead of int fields:
	//food, raft, axe
	//private String[] supplies;
	//river, fallen tree
	//private String[] obstacles;

	/*
	public RestStop(String label, String[] supplies, String[] obstacles) {
		this.validateLabel(label);
		this.validateSupplies(supplies);
		this.validateObstacles(obstacles);
		this.label = label;
		this.supplies = supplies;
		this.obstacles = obstacles;
	}
	*/
	/*
	private void validateSupplies(String[] supplies) {
		if(supplies == null) {
			//this.supplies = new String[0];
			return;
		}
		
		for(int i = 0; i < supplies.length; i++) {
			if(supplies[i] == null 
					|| (!supplies[i].equals("food") 
					&& !supplies[i].equals("raft") 
					&& !supplies[i].equals("axe"))) {
				System.err.println("Supplies must either be \"food\", \"raft\", or \"axe\".");
				//Necessary?
				System.exit(1);
			}
		}
	}
	
	private void validateObstacles(String[] obstacles) {
		if(obstacles == null) {
			//this.obstacles = new String[0];
			return;
		}
		
		for(int i = 0; i < obstacles.length; i++) {
			if(obstacles[i] == null 
					|| (!obstacles[i].equals("river") 
					&& !obstacles[i].equals("fallen tree"))) {
				System.err.println("Obstacles must either be \"river\" or \"fallen tree\".");
				//Necessary?
				System.exit(1);
			}
		}
	}
	*/
}
