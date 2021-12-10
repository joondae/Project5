package project5;

/**
 * This class represents a single rest stop.
 * It stores the label of the rest stop along with
 * a list of supplies a hiker can collect and 
 * a list of obstacles a hiker may encounter at this rest stop.
 * @author Max
 */
public class RestStop implements Comparable<RestStop> {
	//any alphanumeric characters are valid
	private String label;
	private int food, rafts, axes, rivers, fallenTrees;
	
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

	public RestStop(String label, int food, int rafts, int axes, int rivers, int fallenTrees) {
		this.validateLabel(label);
		this.validateSuppliesAndObstacles(food, rafts, axes, rivers, fallenTrees);
		this.label = label;
		//FIX?
		this.food = food;
		this.rafts = rafts;
		this.axes = axes;
		this.rivers = rivers;
		this.fallenTrees = fallenTrees;
	}
	
	public RestStop(String label) {
		this.validateLabel(label);
		this.label = label;
		//FIX?
		this.food = 0;
		this.rafts = 0;
		this.axes = 0;
		this.rivers = 0;
		this.fallenTrees = 0;
	}
	
	private void validateLabel(String label) {
		if(label.isBlank() || label == null) {
			System.err.println("Label must comprise of alphanumeric characters.");
			//Necessary?
			System.exit(1);
		}
		
		int parsedChar;
		for(int i = 0; i < label.length(); i++) {
			try {
				parsedChar = (int) label.charAt(i);
				if(parsedChar < 48 || (parsedChar > 57 && parsedChar < 65) 
						|| (parsedChar > 90 && parsedChar < 97) 
						|| parsedChar > 122) {
					System.err.println("Label must comprise of alphanumeric characters.");
					//Necessary?
					System.exit(1);
				}
			} catch(Exception e) {
				System.err.println("Label must comprise of alphanumeric characters.");
				//Necessary?
				System.exit(1);
			}
		}
	}

	private void validateSuppliesAndObstacles(int food, int rafts, int axes, int rivers, int fallenTrees) {
		if(food < 0 || rafts < 0 || axes < 0 || rivers < 0 || fallenTrees < 0) {
			System.err.println("Supplies and obstacles must be greater than or equal to zero.");
			//Necessary?
			System.exit(1);
		}
	}
	
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
	
	
	public String getLabel() {
		return this.label;
	}
	
	public int getFood() {
		return this.food;
	}
	
	public int getRafts() {
		return this.rafts;
	}
	
	public int getAxes() {
		return this.axes;
	}
	
	public int getRivers() {
		return this.rivers;
	}
	
	public int getFallenTrees() {
		return this.fallenTrees;
	}
	
	@Override
	public int compareTo(RestStop o) {
		return this.label.compareTo(o.label);
	}
}