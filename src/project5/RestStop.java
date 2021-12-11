package project5;

/**
 * This class represents a single rest stop.
 * It stores the label of the rest stop along with
 * a list of supplies a hiker can collect and 
 * a list of obstacles a hiker may encounter at this rest stop.
 * @author Max Hahn
 */
public class RestStop implements Comparable<RestStop> {
	//Note that only alphanumeric characters are valid
	private String label;
	private int food, rafts, axes, rivers, fallenTrees;
	
	/**
	 * Constructs a RestStop object with specified supplies and obstacles.
	 * @param label label for the node in the binary search tree
	 * @param food number of food units to initialize this RestStop object with
	 * @param rafts number of rafts to initialize this RestStop object with
	 * @param axes number of axes to initialize this RestStop object with
	 * @param rivers number of rivers to initialize this RestStop object with
	 * @param fallenTrees number of fallen trees to initialize this RestStop object with
	 */
	public RestStop(String label, int food, int rafts, int axes, 
			int rivers, int fallenTrees) {
		this.validateLabel(label);
		this.validateSuppliesAndObstacles(food, rafts, axes, 
				rivers, fallenTrees);
		this.label = label;
		this.food = food;
		this.rafts = rafts;
		this.axes = axes;
		this.rivers = rivers;
		this.fallenTrees = fallenTrees;
	}
	
	/**
	 * Constructs a RestStop object with specified label.
	 * Supplies and obstacles are defaulted to zero.
	 * @param label label for the node in the binary search tree
	 */
	public RestStop(String label) {
		this.validateLabel(label);
		this.label = label;
		this.food = 0;
		this.rafts = 0;
		this.axes = 0;
		this.rivers = 0;
		this.fallenTrees = 0;
	}
	
	/**
	 * Helper method that ensures label contains alphanumeric characters only.
	 * @param label label for the node in the binary search tree
	 */
	private void validateLabel(String label) {
		if(label.isBlank() || label == null) {
			System.err.println("Label must comprise of"
					+ " alphanumeric characters.");
			System.exit(1);
		}
		
		//Iterate through label
		int parsedChar;
		for(int i = 0; i < label.length(); i++) {
			parsedChar = (int) label.charAt(i);
			//Ensure each character of label is alphanumeric
			if(parsedChar < 48 || (parsedChar > 57 && parsedChar < 65) 
					|| (parsedChar > 90 && parsedChar < 97) 
					|| parsedChar > 122) {
				System.err.println("Label must comprise of"
						+ " alphanumeric characters.");
				System.exit(1);
			}
		}
	}

	/**
	 * Helper method the ensures supplies and obstacles
	 * are not initialized with invalid values.
	 * @param food number of food units to initialize this RestStop object with
	 * @param rafts number of rafts to initialize this RestStop object with
	 * @param axes number of axes to initialize this RestStop object with
	 * @param rivers number of rivers to initialize this RestStop object with
	 * @param fallenTrees number of fallen trees to initialize this RestStop object with
	 */
	private void validateSuppliesAndObstacles(int food, int rafts, int axes, 
			int rivers, int fallenTrees) {
		if(food < 0 || rafts < 0 || axes < 0 
				|| rivers < 0 || fallenTrees < 0) {
			System.err.println("Supplies and obstacles"
					+ " must be greater than or equal to zero.");
			System.exit(1);
		}
	}
	
	/**
	 * Getter method that returns the label of this RestStop object
	 * @return label of this RestStop object
	 */
	public String getLabel() {
		return this.label;
	}
	
	/**
	 * Getter method that returns the number of food units contained
	 * by this RestStop object.
	 * @return the number of food units of this RestStop object
	 */
	public int getFood() {
		return this.food;
	}
	
	/**
	 * Getter method that returns the number of rafts contained
	 * by this RestStop object
	 * @return the number of rafts of this RestStop object
	 */
	public int getRafts() {
		return this.rafts;
	}
	
	/**
	 * Getter method that returns the number of axes contained
	 * by this RestStop object
	 * @return the number of axes of this RestStop object
	 */
	public int getAxes() {
		return this.axes;
	}
	
	/**
	 * Getter method that returns the number of rivers contained
	 * by this RestStop object
	 * @return the number of rivers of this RestStop object
	 */
	public int getRivers() {
		return this.rivers;
	}
	
	/**
	 * Getter method that returns the number of fallen trees contained
	 * by this RestStop object
	 * @return the number of fallen trees of this RestStop object
	 */
	public int getFallenTrees() {
		return this.fallenTrees;
	}
	
	/**
	 * Compares this object's label lexicographically 
	 * with the specified object's label for order.
	 * Returns a negative integer, zero, or positive integer
	 * as this object is less than, equal to, or greater than
	 * the specified object.
	 * @param o the object to be compared
	 * @return a negative integer, zero, or positive integer as this object is 
	 * lexicographically less than, equal to, or greater than the specified object
	 */
	@Override
	public int compareTo(RestStop o) {
		return this.label.compareTo(o.label);
	}
}