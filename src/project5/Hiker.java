package project5;

/**
 * This class represents a hiker traveling down the mountain.
 * It keeps track of all the supplies the hiker has and
 * updates such information as the hiker traverses the mountain and
 * consumes supplies.
 * @author Max Hahn
 */
public class Hiker {
	private int food, rafts, axes;
	
	/**
	 * Constructs a new Hiker object with specified supplies.
	 * Note that each supply is single use.
	 * @param food number of food units in possession,
	 * used as fuel to travel from one node to another
	 * @param rafts number of rafts in possession,
	 * used to overcome obstacle "river"
	 * @param axes number of axes in possession,
	 * used to overcome obstacle "fallen tree"
	 */
	public Hiker(int food, int rafts, int axes) {
		this.validateSupplies(food, rafts, axes);
		this.food = food;
		this.rafts = rafts;
		this.axes = axes;
	}
	
	/**
	 * Constructs a new Hiker object with all supplies defaulted at zero.
	 */
	public Hiker() {
		this.food = 0;
		this.rafts = 0;
		this.axes = 0;
	}
	
	/**
	 * Helper method that ensures invalid values aren't being used
	 * to initialize a Hiker object. 
	 * @param food number of food units to initialize with
	 * @param rafts number of rafts to initialize with
	 * @param axes number of axes to initialize with
	 */
	private void validateSupplies(int food, int rafts, int axes) {
		if(food < 0 || rafts < 0 || axes < 0) {
			System.err.println("Supplies must be greater than"
					+ " or equal to zero.");
		}
	}
	
	/**
	 * Getter method that returns the number of food units in possession.
	 * @return number of food units in possession
	 */
	public int getFood() {
		return this.food;
	}
	
	/**
	 * Getter method that returns the number of rafts in possession.
	 * @return number of rafts in possession
	 */
	public int getRafts() {
		return this.rafts;
	}
	
	/**
	 * Getter method that returns the number of axes in possession.
	 * @return number of axes in possession
	 */
	public int getAxes() {
		return this.axes;
	}
	
	/**
	 * Helper method that decrements food units by 
	 * amount specified by parameter.
	 * @param n number of food units to decrement current supply by
	 */
	public void consumeFood(int n) {
		this.food -= n;
	}
	
	/**
	 * Helper method that decrements rafts by amount specified by parameter.
	 * @param n number of rafts to decrement current amount by
	 */
	public void useRaft(int n) {
		this.rafts -= n;
	}
	
	/**
	 * Helper method that decrements axes by amount specified by parameter.
	 * @param n number of axes to decrement current amount by
	 */
	public void useAxe(int n) {
		this.axes -= n;
	}
	
	/**
	 * Helper method that increments food units by 
	 * amount specified by parameter.
	 * @param n number of food units to increment current supply by
	 */
	public void addFood(int n) {
		this.food += n;
	}
	
	/**
	 * Helper method that increments rafts by amount specified by parameter.
	 * @param n number of rafts to increment current amount by
	 */
	public void addRaft(int n) {
		this.rafts += n;
	}
	
	/**
	 * Helper method that increments axes by amount specified by parameter.
	 * @param n number of axes to increment current amount by
	 */
	public void addAxe(int n) {
		this.axes += n;
	}
}
