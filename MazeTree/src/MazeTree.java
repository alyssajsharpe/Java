//Alyssa Share
//Lab 6   6.7.19
//MazeTree.java

public class MazeTree {
	
	private static TreeNode root; 
	private static int finalDestRow = 7;  //8
	private static int finalDestCol = 12;  //13
 
	public  MazeTree(int[][] grid) {
		root = new TreeNode(0,0);
		make(grid, root);
	   }
	   // create a tree from a grid
	private TreeNode checkPosition(int [][]grid, TreeNode parent, char direction) {
		
		TreeNode node = new TreeNode(parent.row, parent.column);
		switch(direction) {
		//up
		case 'u': 
			int r = 0; 
				if(parent.row <= 0) {
					r = 0;
					node = null;
				}
				else {
					r = parent.row-1; 
					node = new TreeNode(r, parent.column);
				}
				break; 
		//down
		case 'd':
			int r2 = 0; 
				if(parent.row >= finalDestRow) {
					r2 = finalDestRow;
					node = null;
				}
				else {
					r2 = parent.row+1;
					node = new TreeNode(r2, parent.column);
				}	
				break;
		//left
		case 'l': 
			int c = 0;
				if(parent.column <= 0) {
					c = 0;
					node = null;
				}
				else {
					c = parent.column-1;
					node = new TreeNode(parent.row, c);
				}
			
				break;
		
		//right
		case 'r': 
			int c2 = 0;
				if(parent.column >= finalDestCol)  {
					c2 = finalDestCol;
					node = null;
				}
				else {
					c2 = parent.column+1;
					node = new TreeNode(parent.row, c2);
				}
				break;
		}
		return node; 
	}

	
	
	   private void make(int[][] grid, TreeNode parent) {
		  //Marking grid cell to corresponding to parent
		  try {
		   grid[parent.row][parent.column] = -1;
		  }
		  catch(ArrayIndexOutOfBoundsException e) {  
		  }
		  TreeNode up = checkPosition(grid,parent,'u');
		  TreeNode down = checkPosition(grid,parent,'d');
		  TreeNode left = checkPosition(grid,parent,'l');
		  TreeNode right = checkPosition(grid,parent,'r');
		  
		  if(up != null) {
		   if(valid(grid, up.row, up.column)) {
			   TreeNode t = new TreeNode(up.row,up.column);
			   parent.north = t;
			   make(grid, t);
		   }
		  }
		  if(down != null) {   
		   if(valid(grid,down.row, down.column)) {
			   TreeNode t2 = new TreeNode(down.row,down.column);
			   parent.south = t2;
			   make(grid, t2);
		   }
		  }
		  if(left != null) {
		   if(valid(grid,left.row, left.column)) {
			   TreeNode t3 = new TreeNode(left.row, left.column);
			   parent.west = t3;
			   make(grid, t3);
		   }
		  }
		  if(right != null) {
		   if(valid(grid,right.row, right.column)) {
			   TreeNode t4 = new TreeNode(right.row,right.column);
			   parent.east = t4;
			   make(grid, t4);																																								
		   }
		  } 
		  
	   }//end make
	 
	   public static String search() {
			return search(root);
	   }//end public search
	   
	   private static String search(TreeNode thisNode) {
		   String path = "";
		   String nodeStr = thisNode.toString();
		   
		   //1 of four directions : check each direction
		   if(thisNode.row == finalDestRow && thisNode.column == finalDestCol) {	
			   return nodeStr;
		   }
		   else
			   if(childExists(thisNode.west)) {
				   path = search(thisNode.west);
					   if(path != "") {
							return nodeStr + path;
						}
			   }
			   if(childExists(thisNode.east)) {
				   path = search(thisNode.east);
					   if(path != "") {
							return nodeStr + path;
						}
			   }
			   if(childExists(thisNode.north)) {
				   path = search(thisNode.north);
					   if(path != "") {
							return nodeStr + path;
						}
			   }
			   if(childExists(thisNode.south)) {
				   path = search(thisNode.south);
					   if(path != "") {
							return nodeStr + path;
					   	}
			    
			   } 
		return "";
	   }//end private search
	   
	   private static boolean childExists(TreeNode child) {
		   if(child != null) {
			   return true;
		   }
		   else
			   return false; 
	   }//end childExists
	   private boolean valid(int[][] grid, int row, int column) {
		   int cell = grid[row][column];
		   if(cell == 1) {
			   return true;
		   }
		   else
			   return false;
	   }//end valid

	 //****TreeNode Class****
	private class TreeNode{  
	   private int row=0, column=0;
	   private TreeNode north, south, east, west;
	  
	   private TreeNode(int row, int column) {
		   this.row = row;
		   this.column = column; 
		   north = null;
		   south = null;
		   east = null;
		   west = null;
	   }
	   public String toString() {
		   return "("+row+","+column+")";
	   }
	}//end TreeNode
	
		//****Main Class****
	public static void main (String [] args) {
		
		//creating grid
		int[][] grid = {
				  {1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1},
	              {1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1},
	              {0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0},
	              {1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1},
	              {1, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1},
	              {1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1},
	              {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	              {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1} };
		//Creating mazeTree object
		MazeTree maze = new MazeTree(grid);
		
		search();
		
		if(search() == "") {
			System.out.println("No solution found.");
		}
		else
			System.out.println(search());
		
	}//end main
}//end MazeTree class