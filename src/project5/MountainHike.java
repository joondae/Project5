package project5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * This program uses a binary search tree to model a mountain hiking expedition.
 * A hiker starts at the top of the mountain 
 * and must find their way down with limited resources
 * to survive and overcome obstacles.
 * This program lists all possible paths the hiker can take.
 * The mountain itself is represented by a binary search tree.
 * Nodes in the tree represent rest stops.
 * The hiker may restock their supplies and/or encounter obstacles at these stops.
 * Obstacles can be passed by using certain supplies.
 * Some supplies only provide fuel for the hiker to move from one stop to the next.
 * Once the hiker starts down a path they cannot go back.
 *  
 * This class is responsible for parsing and validating the command line arguments, 
 * reading and parsing the input file, producing any error messages, 
 * handling any exceptions thrown by other classes, and producing output.
 * 
 * Tech spec: https://cs.nyu.edu/~joannakl/cs102_f21/projects/project5.html
 * 
 * @author Max Hahn
 */
public class MountainHike {
	/**
	 * The main() method of this program. 
	 * @param args String array that should only contain one element.
	 * The element is provided through the command line and 
	 * should only contain the file name to be used to build the tree.
	 */
	public static void main(String[] args) {
		//Validate input file
		MountainHike mh = new MountainHike();
		mh.validateInputFile(args);
		
		//Create AVL tree and hiker
		BSTMountain<RestStop> m = new BSTMountain<RestStop>();
		Hiker h = new Hiker();
		
		//Parse input file
		mh.processInputFile(m, new File(args[0]));
		
		//Find and print all paths down mountain
		m.findPaths(h);
	}
	
	/**
	 * Helper method that validates input file by 
	 * checking the command line argument, input file path, and input file.
	 * Note that other command line and input file checks may be possible.
	 * @param args array of Strings provided by the command line 
	 * when the program is started.
	 * The first string should be the name of an input file 
	 * containing all points on the mountain.
	 */
	private void validateInputFile(String[] args) {
		//Validate command line argument
		if(args.length != 1 || args[0] == null) {
			System.err.println("Usage Error:"
					+ " the program expects file name"
					+ " as an argument.\n");
			System.exit(1);
		}
		
		//Validate input file path
		File inputFile = new File(args[0]);
		if(!inputFile.exists()) {
			System.err.println("Error: the file " 
					+ inputFile.getAbsolutePath() 
					+ " does not exist.\n");
			System.exit(1);
		}

		//Validate input file
		if (!inputFile.canRead()){
			System.err.println("Error: the file " 
					+ inputFile.getAbsolutePath() 
					+ " cannot be opened for reading.\n");
			System.exit(1);
		}
	}
	
	/**
	 * Helper method that processes input file to build a binary search tree.
	 * Each line of the input file describes a node on the mountain.
	 * Its format follows LABEL SUPPLIES OBSTACLES.
	 * Label is a String composed of only alphanumeric characters
	 * that is the name of the rest stop.
	 * The comparison of labels using natural ordering determines the tree shape.
	 * Valid supplies the hiker may get at a rest stop are
	 * food, raft, and axe (case sensitive).
	 * Valid obstacles the hiker may encounter at a rest stop are
	 * river and fallen tree (case sensitive).
	 * There may be multiple of the same kind of supplies 
	 * and/or obstacles at a rest stop.
	 * Note that all supplies must be listed before the obstacles.
	 * Any words not obstacles are ignored after an obstacle is listed.
	 * @param m the binary search tree to be built
	 * @param inputFile the input file containing node data for the tree
	 */
	private void processInputFile(BSTMountain<RestStop> m, File inputFile) {
		//Validate BufferedReader
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(inputFile));
		} catch (FileNotFoundException e) {
			System.err.println("Error: the file " 
					+ inputFile.getAbsolutePath() 
					+ "cannot be opened for reading.");
			System.exit(1);
		}
		
		//Process label, supplies, and obstacles
		try {
			while(br.ready()) {
				//Split by whitespace
					//Note this ignores leading and trailing whitespace
					//\s for space including tabs
					//+ for multiple occurences
					//\ for escaping
				String[] arr = br.readLine().split("\\s+");
				//Prevent IndexOutOfBoundsException and ignore blank lines
				if(arr.length > 0) {
					//Process label
					String label = processLabel(arr);
					
					//No more processing to be done
						//I.e. node without supplies/obstacles
					//Skip to next iteration
					if(arr.length == 1) {
						m.add(new RestStop(label));
						continue;
					}
					
					processSuppliesAndObstacles(arr, label, m);
				}
				
			}
		} catch(IOException e) {
			System.err.println("Error: I/O exception occured while reading " 
					+ inputFile.getAbsolutePath() 
					+ ". Make sure file is properly formatted.");
			System.exit(1);
		}
		
		try {
			br.close();
		} catch(IOException e) {
			System.err.println("Error: I/O exception occured"
					+ " while closing BufferedReader."
					+ " Make sure file is properly formatted.");
			System.exit(1);
		}
	}
	
	/**
	 * Helper method that ensures the label of the current node
	 * being processed is comprised of alphanumeric characters 
	 * then returns it.
	 * Otherwise, notify user of error then terminate the program.
	 * @param arr String array split on whitespace containing 
	 * all data for the current node to be added to the tree
	 * @return a valid label, if applicable
	 */
	private String processLabel(String[] arr) {
		//Second condition checks if label contains non-alphanumeric characters
		if(arr[0] == null || !arr[0].matches("\\w")) {
			System.err.println("Label must be alphanumeric.");
			System.exit(1);
		}
		return arr[0];
	}
	
	/**
	 * Helper method that processes raw data on each line of
	 * the input file, following the conventions outlined in
	 * the tech spec as well as the documentation for processInputFile().
	 * @param arr String array split on whitespace containing
	 * all data for the current node to be added to the tree
	 * @param label the valid label returned by processLabel()
	 * @param m the binary search tree being built
	 */
	private void processSuppliesAndObstacles(String[] arr, String label, 
			BSTMountain<RestStop> m) {
		//Counters for supplies and obstacles
		int food = 0, rafts = 0, axes = 0;
		int rivers = 0, fallenTrees = 0;
		
		//Process supplies
		//Current index of array
		int count = 1;
		//Iterate through input file line
		while(count < arr.length) {
			String value = arr[count];
			if(value != null) {
				//Increment supply counters if applicable
				//Ignore anything other than a supply or an obstacle
				if(value.equals("food")) food++;
				else if(value.equals("raft")) rafts++;
				else if(value.equals("axe")) axes++;
				//Ignore valid supplies after encountering "river"
				else if(value.equals("river")){
					rivers++;
					count++;
					break;
				}
				//Ignore valid supplies after encountering "fallen tree"
				else if(value.equals("fallen")) {
					//Prevent IndexOutOfBoundsException
					//and ensure "fallen" is followed by "tree"
					if(count + 1 < arr.length 
							&& arr[count + 1].equals("tree")) {
						fallenTrees++;
						count++;
						break;
					}
				}
			}
			count++;
		}

		//Process obstacles
		//Iterate trhough rest of input file line
		while(count < arr.length) {
			String value = arr[count];
			if(value != null) {
				//Increment obstacle counters if applicable
				//Ignore anything other than an obstacle
				if(value.equals("river")) rivers++;
				else if(value.equals("fallen")) {
					//Prevent IndexOutOfBoundsException
					//and ensure "fallen" is followed by "tree"
					if(count + 1 < arr.length 
							&& arr[count + 1].equals("tree"))
						fallenTrees++;
				}
			}
			count++;
		}
		
		//Add a node with the properly processed data fields to the tree
		m.add(new RestStop(label, food, rafts, axes, rivers, fallenTrees));
	}
}
