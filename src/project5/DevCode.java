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
	
	
	
	
	
	//BSTMOUNTAIN
	//DEV CODE to test BST and AVL tree
	/*
	public static void main(String[] args) {
		//BSTMountain<RestStop> m = new BSTMountain<>();
		BSTMountain<RestStop> m = new BSTMountain<RestStop>();
		m.testTree();
		//m.testExample();
		
		//Can use these prints to to test solely tree construction or tree construction + obstacles & supplies
		System.out.println(m.toStringTree());
		//System.out.println(m.toStringTreeDetailed());
	}
	
	public void testTree() {
		//Ver 1: fundamental cases
		
		add(new RestStop("A"));
		add(new RestStop("C"));
		add(new RestStop("B"));
		
		
		//Ver 2: tech spec example
		
		add(new RestStop("J"));
		add(new RestStop("E"));
		add(new RestStop("P"));
		add(new RestStop("G"));
		add(new RestStop("B"));
		add(new RestStop("L"));
		add(new RestStop("I"));
		add(new RestStop("R"));
		add(new RestStop("Q"));
		add(new RestStop("N"));
		add(new RestStop("F"));
		add(new RestStop("A"));
		add(new RestStop("H"));
		add(new RestStop("D"));
		add(new RestStop("C"));
		add(new RestStop("K"));
		add(new RestStop("M"));
		add(new RestStop("O"));
		
		
		//Ver 3: G4G example
		
		add(new RestStop("10"));
		add(new RestStop("20"));
		add(new RestStop("30"));
		add(new RestStop("40"));
		add(new RestStop("50"));
		add(new RestStop("25"));
	}
	
	public void testExample() {
		//FOOD RAFT AXE RIVER FALLENTREE
		
		//Ver 1: fundamental cases
			//BST labels + supplies/obstacles (+ soln(s))
			//Testing 0, 1, 2, or 3 nodes with or without conditions
			//Conditions: different permutations of yes/no for each "slot" in a length 5 list
				//Each "slot" represents food, rafts, axes, rivers, fallen trees
				//"Yes" means 1, 2, 3, or any large number
				//"No" means 0
				//E.g. 1 1 1 0 0 (1 food, 1 raft, 1 axe, 0 rivers, 0 fallen trees)
		
		add(new RestStop("J", 2, 0, 0, 0, 0));
		add(new RestStop("E", 0, 1, 1, 2, 1));
		add(new RestStop("P", 0, 0, 0, 0, 0));
		add(new RestStop("G", 0, 0, 0, 0, 0));
		//add(new RestStop("L", 0, 0, 0, 0, 0));
		
		
		//Ver 2: tech spec example
			//BST labels + supplies/obstacles (+ soln(s))
			//Solns: J P L N M; J P L N O
		
		add(new RestStop("J", 2, 1, 0, 0, 0));
		add(new RestStop("E", 0, 0, 0, 0, 1));
		add(new RestStop("P"));
		add(new RestStop("G", 1, 0, 1, 1, 0));
		add(new RestStop("B", 1, 0, 0, 0, 0));
		add(new RestStop("L", 2, 0, 0, 1, 0));
		add(new RestStop("I", 1, 0, 0, 0, 1));
		add(new RestStop("R", 1, 1, 1, 0, 0));
		add(new RestStop("Q"));
		add(new RestStop("N", 0, 0, 1, 0, 0));
		add(new RestStop("F"));
		add(new RestStop("A"));
		add(new RestStop("H"));
		add(new RestStop("D", 1, 0, 1, 0, 0));
		add(new RestStop("C"));
		add(new RestStop("K"));
		add(new RestStop("M"));
		add(new RestStop("O"));
		
		Hiker h = new Hiker();
		findPaths(h);
		
		//Ver 3: AVL just labels
		
		//Ver 4: AVL labels + supplies/obstacles (+ soln)
	}
	*/
	
	//Reach cliff or run out of food
	/*System.out.println("Node: " + node.label);
	System.out.println("Food: " + h.getFood());
	System.out.println("Count: " + count + " vs. height: " + height); 
	System.out.println("Left: " + node.left + "  right: " + node.right + "\n");*/
	
	//Obstacles cannot be overcome with supplies (recently added and accumulated)
	/*System.out.println("Node: " + node.label);
	System.out.println("Rafts: " + h.getRafts() + " vs. rivers: " + node.rivers);
	System.out.println("Axes: " + h.getAxes() + " vs. fallen trees: " + node.fallenTrees + "\n");*/
	
	//checkForRotation()
	//GfG AVL code
	/*
	int leftComp = (data.getLabel()).compareTo(node.left.data.getLabel());
	int rightComp = (data.getLabel()).compareTo(node.right.data.getLabel());
	//Rotate tree
	//Note: balance should never be >2 or <-2
	
	//LL Rotation
	if(balance > 1 && leftComp < 0)
		return balanceLL(node);
	//RR Rotation
	if(balance < -1 && rightComp > 0)
		return balanceRR(node);
	//LR Rotation
	if(balance > 1 && leftComp > 0) {
		return balanceLR(node);
	}
	//RL Rotation
	if(balance < -1 && rightComp < 0) {
		return balanceRL(node);
	}
	*/
	
	//toString methods:
	/*
	public String toStringTree() {
		StringBuffer sb = new StringBuffer();
		toStringTree(sb, root, 0);
		return sb.toString();
	}
	
	private void toStringTree(StringBuffer sb, BSTNode node, int level) {
		if(level > 0) {
			for(int i = 0; i < level-1; i++) {
				sb.append("   ");
			}
			sb.append("|--");
		}
		if(node == null) {
			sb.append("->\n");
			return;
		}
		else
			sb.append(node.data.getLabel() + "\n");
		
		toStringTree(sb, node.left, level+1);
		toStringTree(sb, node.right, level+1);
	}

	public String toStringTreeDetailed() {
		StringBuffer sb = new StringBuffer();
		toStringTreeDetailed(sb, root, 0);
		return sb.toString();
	}
	
	private void toStringTreeDetailed(StringBuffer sb, BSTNode node, int level) {
		if(level > 0) {
			for(int i = 0; i < level-1; i++) {
				sb.append("   ");
			}
			sb.append("|--");
		}
		if(node == null) {
			sb.append("->\n");
			return;
		}
		else
			sb.append(node.data.getLabel() 
					+ ", H: " + node.height
					+ ", F: " + node.data.getFood() 
					+ ", Ra: " + node.data.getRafts() 
					+ ", A: " + node.data.getAxes() 
					+ ", Ri: " + node.data.getRivers() 
					+ ", T: " + node.data.getFallenTrees() + "\n");
		
		toStringTreeDetailed(sb, node.left, level+1);
		toStringTreeDetailed(sb, node.right, level+1);
	}
	*/
	
	//Rotation code from slides
	/*
	//Returns reference to new root of subtree after LL rotation is performed
	private BSTNode balanceLL(BSTNode A) {
		BSTNode B = A.left;
		A.left = B.right;
		B.right = A;
		updateHeight(A);
		updateHeight(B);
		return B;
	}
	
	//DOUBLE CHECK THIS
	private BSTNode balanceRR(BSTNode A) {
		BSTNode B = A.right;
		A.right = B.left;
		B.left = A;
		updateHeight(A);
		updateHeight(B);
		return B;
	}
	
	//Returns reference to new root of subtree after LR rotation is performed
	private BSTNode balanceLR(BSTNode A) {
		BSTNode B = A.left;
		BSTNode C = B.right;
		A.left = C.right;
		B.right = C.left;
		C.left = B;
		C.right = A;
		updateHeight(A);
		updateHeight(B);
		updateHeight(C);
		return C;
	}
	
	//DOUBLE CHECK
	private BSTNode balanceRL(BSTNode A) {
		BSTNode B = A.right;
		BSTNode C = B.left;
		A.right = C.left;
		B.left = C.right;
		C.right = B;
		C.left = A;
		updateHeight(A);
		updateHeight(B);
		updateHeight(C);
		return C;
	}
	*/
}
