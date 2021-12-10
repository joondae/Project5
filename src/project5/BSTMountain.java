package project5;

/**
 * This class implements a binary search tree that uses 
 * AVL algorithms discussed in class.
 * It represents the mountain itself which the hiker will traverse.
 * Credit: Professor Klukowska's lecture slides.
 * @author Max
 */
public class BSTMountain < BSTNode extends Comparable<BSTNode> >{
	private BSTNode root;
	//Necessary?
	private boolean added;
	
	public BSTMountain() {
		root = null;
	}
	
	//DEV CODE to test BST and AVL tree
	
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
		/*
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
		*/
		
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
		/*
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
		*/
		Hiker h = new Hiker();
		findPaths(h);
		
		//Ver 3: AVL just labels
		
		//Ver 4: AVL labels + supplies/obstacles (+ soln)
	}
	
	
	public void findPaths(Hiker h) {
		if(h == null) {
			System.err.println("Hiker cannot be null.");
			System.exit(1);
		}
		if(root == null) {
			System.err.println("Mountain does not contain any rest stops.");
			System.exit(1);
		}
		findPathsRec(h, root, 0, root.height, new StringBuffer(root.data.getLabel()));
	}
	
	private void findPathsRec(Hiker h, BSTNode node, int count, int height, StringBuffer soln) {
		//FIX ORDER AND CONDITIONS
		//Reach cliff or run out of food
		if(h.getFood() < 0 
				|| (node.left == null 
				&& node.right == null
				&& count != height)) {
			/*System.out.println("Node: " + node.label);
			System.out.println("Food: " + h.getFood());
			System.out.println("Count: " + count + " vs. height: " + height); 
			System.out.println("Left: " + node.left + "  right: " + node.right + "\n");*/
			return;
		}
		
		//Add supplies from RestStop
		addSupplies(h, node);
		
		//Obstacles cannot be overcome with supplies (recently added and accumulated)
		if(h.getRafts() < node.data.getRivers() || h.getAxes() < node.data.getFallenTrees()) {
			/*System.out.println("Node: " + node.label);
			System.out.println("Rafts: " + h.getRafts() + " vs. rivers: " + node.rivers);
			System.out.println("Axes: " + h.getAxes() + " vs. fallen trees: " + node.fallenTrees + "\n");*/
			return;
		}
		
		//Overcome obstacles (if applicable) and consume 1 food to go to next RestStop
		useSupplies(h, node);
		
		//If reached the end, print path taken
		if(height == count) {
			System.out.println(soln);
			return;
		}
		
		//Recurse left
		//Remove condition? Add more conditions?
		if(node.left != null) {
			soln.append(" " + node.left.data.getLabel());
			
			
			Hiker previousState = new Hiker(h.getFood(), h.getRafts(), h.getAxes());
			
			
			findPathsRec(h, node.left, count + 1, height, soln);
			//Re-add or remove supplies
			//resetSupplies(h, node.left);
			
			
			h = previousState;
			
			
			//Find big-O for this
			soln.delete(soln.length() - 2, soln.length());
		}
		
		//Recurse right
		//Remove condition? Add more conditions?
		if(node.right != null) {
			soln.append(" " + node.right.data.getLabel());
			
			
			Hiker previousState = new Hiker(h.getFood(), h.getRafts(), h.getAxes());
			
			
			findPathsRec(h, node.right, count + 1, height, soln);
			//Re-add or remove supplies
			//resetSupplies(h, node.right);
			
			
			h = previousState;
			
			
			//Find big-O for this
			soln.delete(soln.length() - 2, soln.length());
		}
		
		//System.out.println("Reached bottom of method.");
		return;
	}
	
	private void addSupplies(Hiker h, BSTNode node) {
		h.addFood(node.data.getFood());
		h.addRaft(node.data.getRafts());
		h.addAxe(node.data.getAxes());
	}
	
	private void useSupplies(Hiker h, BSTNode node) {
		h.consumeFood(1);
		h.useRaft(node.data.getRivers());
		h.useAxe(node.data.getFallenTrees());
	}
	
	private void resetSupplies(Hiker h, BSTNode node) {
		h.addFood(1 - node.data.getFood());
		h.addRaft(node.data.getRivers() - node.data.getRafts());
		h.addAxe(node.data.getFallenTrees() - node.data.getAxes());
	}
	
	public boolean add(RestStop data) {
		added = false;
		if(data == null) return added;
		//replace root with reference to tree after new value is added
		root = add(data, root);
		return added;
	}
	
	private BSTNode add(RestStop data, BSTNode node) {
		//Tree is empty
		if(node == null) {
			added = true;
			return new BSTNode(data);
		}
		//Compare using natural ordering of elements???
		int comp = (node.data.getLabel()).compareTo(data.getLabel());
		//Add to left subtree
		if(comp > 0) {
			node.left = add(data, node.left);
		}
		//Add to right subtree
		else if(comp < 0) {
			node.right = add(data, node.right);
		}
		//Duplicate found - do not add
		else {
			added = false;
			return node;
		}
		//DOUBLE CHECK THIS
		updateHeight(node);
		//AVL CODE
		//NOTE: if rotation occurs, may have to change height argument of recursive calls
		return checkForRotation(data, node);
		//return node;
	}
	
	//AVL CODE
	
	private BSTNode checkForRotation(RestStop data, BSTNode node) {
		int balance = balanceFactor(node);
		/*
		//Check for NPE
		if(node.left == null || node.right == null) {
			return node;
		}
		*/
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
		
		
		if(balance > 1) { 
			if(height(node.right.right) > height(node.right.left))
				node = rotateLeft(node);
			else {
				node.right = rotateRight(node.right);
				node = rotateLeft(node);
			}
		}
		else if(balance < -1) {
			if(height(node.left.left) > height(node.left.right))
				node = rotateRight(node);
			else {
				node.left = rotateLeft(node.left);
				node = rotateRight(node);
			}
		}
		
		
		//Return unchanged node pointer
		return node;
	}
	
	private int height(BSTNode n) {
		return n == null ? -1 : n.height;
	}
	
	
	
	private BSTNode rotateLeft(BSTNode y) {
		BSTNode x = y.right;
		BSTNode z = x.left;
		x.left = y;
		y.right = z;
		updateHeight(y);
		updateHeight(x);
		return x;
	}
	
	private BSTNode rotateRight(BSTNode y) {
		BSTNode x = y.left;
		BSTNode z = x.right;
		x.right = y;
		y.left = z;
		updateHeight(y);
		updateHeight(x);
		return x;
	}
	
	
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
	
	private void updateHeight(BSTNode node) {
		//If node is a leaf
		if(node.left == null && node.right == null)
			node.height = 0;
		else if(node.left == null)
			node.height = node.right.height + 1;
		else if(node.right == null)
			node.height = node.left.height + 1;
		else
			node.height = 1 + max(node.left.height, node.right.height);
	}
	
	private int max(int x, int y) {
		if(x >= y) return x;
		return y;
	}
	
	//AVL CODE
	
	private int balanceFactor(BSTNode node) {
		if(node.right == null)
			return -node.height;
		if(node.left == null)
			return node.height;
		return node.right.height - node.left.height;
	}
	
	
	//DEV CODE
	
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
	
	
	private class BSTNode implements Comparable<BSTNode> {
		private RestStop data;
		private int height;
		private BSTNode left, right;
		
		public BSTNode(RestStop data) {
			if(data == null) {
				System.err.println("Data cannot be null.");
				System.exit(1);
			}
			this.data = data;
			this.height = 0;
			this.left = null;
			this.right = null;
		}
		
		/*
		public BSTNode(RestStop data, BSTNode left, BSTNode right) {
			if(data == null) {
				System.err.println("Data cannot be null.");
				System.exit(1);
			}
			this.data = data;
			this.height = 0;
			this.left = left;
			this.right = right;
		}
		*/
		
		@Override
		public int compareTo(BSTNode o) {
			return this.data.getLabel().compareTo(o.data.getLabel());
		}
	}
	

}
