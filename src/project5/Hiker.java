package project5;

/**
 * This class represents a hiker traveling down the mountain.
 * It keeps track of all the supplies the hiker has and
 * updates such information as the hiker traverses the mountain and
 * consumes supplies.
 * @author Max
 */
public class Hiker {
	private int food, rafts, axes;
	
	public Hiker(int food, int rafts, int axes) {
		this.validateSupplies(food, rafts, axes);
		this.food = food;
		this.rafts = rafts;
		this.axes = axes;
	}
	
	public Hiker() {
		this.food = 0;
		this.rafts = 0;
		this.axes = 0;
	}
	
	private void validateSupplies(int food, int rafts, int axes) {
		if(food < 0 || rafts < 0 || axes < 0) {
			System.err.println("Supplies must be greater than or equal to zero.");
		}
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
	
	public void consumeFood(int n) {
		this.food -= n;
	}
	
	public void useRaft(int n) {
		this.rafts -= n;
	}
	
	public void useAxe(int n) {
		this.axes -= n;
	}
	
	public void addFood(int n) {
		this.food += n;
	}
	
	public void addRaft(int n) {
		this.rafts += n;
	}
	
	public void addAxe(int n) {
		this.axes += n;
	}
}
