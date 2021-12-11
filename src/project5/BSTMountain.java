package project5;

/**
 * This class implements a binary search tree that uses 
 * AVL algorithms discussed in class.
 * It represents the mountain itself which the hiker will traverse.
 * Credit: Professor Klukowska's lecture slides and ed workspace.
 * @author Max Hahn
 */
public class BSTMountain < BSTNode extends Comparable<BSTNode> >{
	private BSTNode root;
	private boolean added;

	/**
	 * Constructs an empty binary tree sorted according to
	 * the natural ordering of its elements.
	 */
	public BSTMountain() {
		root = null;
	}
	
	/**
	 * Helper method that acts as a wrapper function.
	 * Deals with special cases for the hiker 
	 * and binary search tree before calling recursive function.
	 * Recursive function finds then prints all valid ways down
	 * the mountain (traverse binary search tree to lowest level).
	 * @param h Hiker object that keeps track of supplies 
	 * in possession as it traverses the mountain
	 */
	public void findPaths(Hiker h) {
		if(h == null) {
			System.err.println("Hiker cannot be null.");
			System.exit(1);
		}
		if(root == null) {
			System.err.println("Mountain does not contain any rest stops.");
			System.exit(1);
		}
		findPathsRec(h, root, 0, root.height, 
				new StringBuffer(root.data.getLabel()));
	}
	
	/**
	 * Helper method that recursively finds then prints all valid ways down 
	 * the mountain (traverses binary search tree to lowest level).
	 * There are four base cases. The first two check whether the hiker has
	 * encountered a cliff or if they have run out of food.
	 * In both instances the hiker must forgo the current path they are on
	 * and go back to the previous node.
	 * The third case is if the hiker encounters obstacles and is unable
	 * to clear them with the current amount of supplies in possession plus
	 * the supplies found at the current node. The hiker must also forgo this
	 * path.
	 * The last case is if the hiker has reached the lowest level of the tree
	 * and clears any applicable obstacles.
	 * The path the hiker took to get to this point (node) is printed accordingly.
	 * The actual recursive part of this method uses choose-explore-unchoose.
	 * As each node is explored, the hiker's supplies and the solution log
	 * are updated accordingly.
	 * @param h the hiker object keeping track of supplies in possession
	 * @param node the current place in the mountain the hiker is at
	 * @param count the hiker's current level
	 * @param height the height of the binary search tree,
	 * equal to its highest level
	 * @param soln StringBuffer that logs each node visited and prints if the
	 * hiker's path leads to the bottom of the mountain
	 */
	private void findPathsRec(Hiker h, BSTNode node, int count, int height, StringBuffer soln) {
		//Reach cliff or run out of food
		if(h.getFood() < 0 
				|| (node.left == null 
				&& node.right == null
				&& count != height)) {
			return;
		}
		
		//Add supplies from RestStop
		addSupplies(h, node);
		
		//Obstacles cannot be overcome with supplies (recently added and accumulated)
		if(h.getRafts() < node.data.getRivers() 
				|| h.getAxes() < node.data.getFallenTrees()) {
			return;
		}
		
		//Overcome obstacles (if applicable) and consume 1 food to go to next node
		useSupplies(h, node);
		
		//If bottom of mountain reached, print path taken
		if(height == count) {
			System.out.println(soln);
			return;
		}
		
		//Recurse to left node
		if(node.left != null) {
			//Choose left node
			soln.append(" " + node.left.data.getLabel());
			//Save previous state of Hiker's supplies before recursing down left node
			Hiker previousState = new Hiker(h.getFood(), h.getRafts(), h.getAxes());
			findPathsRec(h, node.left, count + 1, height, soln);
			//After left node is unchosen, reset hiker's state
			h = previousState;
			//Unchoose left node in solution log
			soln.delete(soln.length() - 2, soln.length());
		}
		
		//Recurse right
		if(node.right != null) {
			//Choose right node in solution log
			soln.append(" " + node.right.data.getLabel());
			//Save previous state of Hiker's supplies before recursing down right node
			Hiker previousState = new Hiker(h.getFood(), h.getRafts(), h.getAxes());
			findPathsRec(h, node.right, count + 1, height, soln);
			//After right node is unchosen, reset hiker's state
			h = previousState;
			//Unchoose right node in solution log
			soln.delete(soln.length() - 2, soln.length());
		}
		//Recurse back up stack or end method
		return;
	}
	
	/**
	 * Helper method used in the recursive function that collects any supplies
	 * present at a node and adds them to hiker's possession.
	 * @param h Hiker object traversing the binary search tree
	 * @param node the current node the hiker is at
	 */
	private void addSupplies(Hiker h, BSTNode node) {
		h.addFood(node.data.getFood());
		h.addRaft(node.data.getRafts());
		h.addAxe(node.data.getAxes());
	}
	
	/**
	 * Helper method used in the recursive function that consumes supplies
	 * needed to overcome obstacles at the current node (if applicable)
	 * and one food unit as fuel to travel to the next node.
	 * @param h Hiker object traversing the binary search tree
	 * @param node the current node the Hiker is at
	 */
	private void useSupplies(Hiker h, BSTNode node) {
		h.consumeFood(1);
		h.useRaft(node.data.getRivers());
		h.useAxe(node.data.getFallenTrees());
	}
	
	/**
	 * Wrapper function that adds the specified element 
	 * (RestStop object) as data field to a new BSTNode in this tree
	 * (if it is not already present).
	 * This call will leave the tree unchanged if the tree already contains
	 * the element, returning false.
	 * @param data RestStop object (element) to be added to this tree
	 * @return true if the tree did not already contain the specified element,
	 * false otherwise
	 */
	public boolean add(RestStop data) {
		added = false;
		if(data == null) return added;
		//Replace root with a reference to the tree after new value is added
		root = add(data, root);
		return added;
	}
	
	/**
	 * Recursive function for add that returns a reference to the subtree
	 * in which the new value is added.
	 * @param data element to be added to this tree
	 * @param node node at which the recursive call is made
	 * @return a reference to the subtree in which the new value is added
	 */
	private BSTNode add(RestStop data, BSTNode node) {
		//Tree is empty or reached the place where element should be added
			//In former case, method ends right here
			//In latter case, method starts recursing back up stack
		if(node == null) {
			added = true;
			return new BSTNode(data);
		}
		//Compare labels lexicographically
		int comp = (node.data.getLabel()).compareTo(data.getLabel());
		//Recurse down left or right subtree
			//to find right location for new element
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
		//Must update heights of affected nodes in AVL tree
		updateHeight(node);
		//See whether AVL tree needs execute a rotation
		return checkForRotation(data, node);
	}
	
	/**
	 * Helper method used to update heights of affected nodes during insertion.
	 * @param node node whose height needs to be updated
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
	
	/**
	 * Helper method used to calculate the greater of two 
	 * given nodes' heights.
	 * @param x height of one node
	 * @param y height of another node
	 * @return the greater of the two given heights
	 */
	private int max(int x, int y) {
		if(x >= y) return x;
		return y;
	}
	
	/**
	 * This method checks the balance factors of each applicable node
	 * to determine whether a rotation is needed to keep the tree balanced.
	 * @param data element added to the tree
	 * @param node node at which the recursive call is made
	 * @return a reference to the subtree in which the new value was added
	 */
	private BSTNode checkForRotation(RestStop data, BSTNode node) {
		int balance = balanceFactor(node);
		
		if(balance > 1) {
			//LL rotation
			if(height(node.right.right) > height(node.right.left))
				node = rotateLeft(node);
			//RL rotation
			else {
				node.right = rotateRight(node.right);
				node = rotateLeft(node);
			}
		}
		else if(balance < -1) {
			//RR rotation
			if(height(node.left.left) > height(node.left.right))
				node = rotateRight(node);
			//LR rotation
			else {
				node.left = rotateLeft(node.left);
				node = rotateRight(node);
			}
		}
		
		//Return unchanged node pointer
		return node;
	}
	
	/**
	 * Helper method that calculates the balance factor of a given node.
	 * Prevents NullPointerException by having node passed as argument.
	 * @param node node whose balance factor is to be calculated
	 * @return balance factor of given node
	 */
	private int balanceFactor(BSTNode node) {
		if(node.right == null)
			return -node.height;
		if(node.left == null)
			return node.height;
		return node.right.height - node.left.height;
	}
	
	/**
	 * Helper method that calculates the height of a given node.
	 * Prevents NullPointerException by having node passed as argument.
	 * If the node is null, then this method will return a height of -1.
	 * @param n the node whose height is to be evaluated
	 * @return the height of the node given by the parameter
	 */
	private int height(BSTNode n) {
		return (n == null) ? -1 : n.height;
	}
	
	/**
	 * Helper method used in conjunction with rotateRight() 
	 * to execute rotations.
	 * @param y node where imbalance occurs
	 * @return reference to new root of the balanced subtree
	 */
	private BSTNode rotateLeft(BSTNode y) {
		BSTNode x = y.right;
		BSTNode z = x.left;
		x.left = y;
		y.right = z;
		updateHeight(y);
		updateHeight(x);
		return x;
	}
	
	/**
	 * Helper method used in conjuction with rotateLeft()
	 * to execution rotations. 
	 * @param y node where imbalance occurs
	 * @return reference to new root of the balanced subtree
	 */
	private BSTNode rotateRight(BSTNode y) {
		BSTNode x = y.left;
		BSTNode z = x.right;
		x.right = y;
		y.left = z;
		updateHeight(y);
		updateHeight(x);
		return x;
	}
	
	/**
	 * This is the private internal node class for this AVL BST.
	 * It stores references to left and right children, as well as
	 * a data field that contains a RestStop object.
	 * The RestStop object itself contains a label and supplies/obstacles information
	 * initialized in MountainHike.java.
	 * This class restricts unnecessary access to the tree itself while
	 * still allowing data processing to occur outside of it.
	 * @author Max Hahn
	 *
	 */
	private class BSTNode implements Comparable<BSTNode> {
		private RestStop data;
		private int height;
		private BSTNode left, right;
		
		/**
		 * Constructs a BSTNode with given data
		 * @param data RestStop object for the data field to be initialized with
		 */
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
		public int compareTo(BSTNode o) {
			return this.data.getLabel().compareTo(o.data.getLabel());
		}
	}
	

}
