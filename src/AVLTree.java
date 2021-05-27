import java.util.*;

public class AVLTree implements Iterable<Integer> {
    protected class Node {
    	protected Node left = null;
    	protected Node right = null;
    	protected Node parent = null;
    	protected int height = 1;
    	protected int value;

    	protected Node(int val) {
            this.value = val;
        }
    }
    
    protected Node root;
    
    //You may add fields here.
    protected Deque<Node> nodesInserted = new ArrayDeque<Node>();
    protected Deque<String> rotations = new ArrayDeque<String>();
    protected boolean wasRotated=false;

    public AVLTree() {
    	this.root = null;
    }
    
    //You may add lines of code to both "insert" and "insertNode" functions.
	public void insert(int value) {
    	root = insertNode(this.root,value);
    	if (!wasRotated) //if no rotations were made, we push the string "null" to the rotations ArrayDeque
    	{
            rotations.push("null");

        }
        wasRotated=false;//returning the var to its default value before each insertion(false)
    }
	
	protected Node insertNode(Node node, int value) {
		/* 1.  Perform the normal BST search and insert */

        if (node == null) {
        	Node inserted_node = new Node(value);
            nodesInserted.push(inserted_node);
            return inserted_node;
        }

        if (value < node.value) {
            node.left  = insertNode(node.left, value);
            node.left.parent = node;
        }
        else {
            node.right = insertNode(node.right, value);
            node.right.parent = node;
        }
            
        /* 2. Update height of this ancestor node */
        node.height = Math.max(getNodeHeight(node.left), getNodeHeight(node.right)) + 1;

        /* 3. Get the balance factor of this ancestor node to check whether
        this node became unbalanced */
        // If this node becomes unbalanced, then there are 4 cases

        // Left Left Case
        int balance = getBalanceFactor(node);
        
        // Left Cases            
        if (balance > 1) {
            wasRotated=true;//indicates that rotations have been made

            if (value > node.left.value) {
                node.left = leftRotate(node.left);
                rotations.push(ImbalanceCases.LEFT_RIGHT.toString());//indicates the case for the insertion
            }
            else {
                rotations.push(ImbalanceCases.LEFT_LEFT.toString());//indicates the case for the insertion
            }
            nodesInserted.push(node.left);//the node that the rotations were made on
            node = rightRotate(node);
        } // Right Cases
        else if (balance < -1) {
            wasRotated=true;

            if (value < node.right.value) {
                node.right = rightRotate(node.right);
                rotations.push(ImbalanceCases.RIGHT_LEFT.toString());//indicates the case for the insertion
            }
            else {
                rotations.push(ImbalanceCases.RIGHT_RIGHT.toString());//indicates the case for the insertion
            }
            nodesInserted.push(node.right);//the node that the rotations were made on
            node = leftRotate(node);

        }

        return node;
    }
    
    protected Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        //Update parents
        if(T2 != null) {
        	T2.parent = y;
        }
        
        x.parent = y.parent;
        y.parent = x;
        
        updateHeight(y);
        updateHeight(x);

        // Return new root
        return x;
    }

    protected Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;
        
        //Update parents
        if(T2 != null) {
        	T2.parent = x;
        }
        
        y.parent = x.parent;
        x.parent = y;
        
        updateHeight(x);
        updateHeight(y);

        // Return new root
        return y;
    }
    
    protected int getBalanceFactor(Node n) {
        return n == null ? 0 : getNodeHeight(n.left) - getNodeHeight(n.right);
    }
    
    protected int getNodeHeight(Node node) {
        return (node==null) ? 0 : node.height;
    }
    
    private void updateHeight(Node n)
    {
        n.height = Math.max(getNodeHeight(n.left), getNodeHeight(n.right)) + 1;
    }
   
    public void printTree() {
    	TreePrinter.print(this.root);
    }
    
    private static class TreePrinter {
        private static void print(Node root) {
            if(root == null) {
                System.out.println("(XXXXXX)");
            } else {    
                final int height = root.height;
                final int halfValueWidth = 4;
                int elements = 1;
                
                List<Node> currentLevel = new ArrayList<Node>(1);
                List<Node> nextLevel    = new ArrayList<Node>(2);
                currentLevel.add(root);
                
                // Iterating through the tree by level
                for(int i = 0; i < height; i++) {
                    String textBuffer = createSpaceBuffer(halfValueWidth * ((int)Math.pow(2, height-1-i) - 1));
        
                    // Print tree node elements
                    for(Node n : currentLevel) {
                        System.out.print(textBuffer);
        
                        if(n == null) {
                            System.out.print("        ");
                            nextLevel.add(null);
                            nextLevel.add(null);
                        } else {
                            System.out.printf("(%6d)", n.value);
                            nextLevel.add(n.left);
                            nextLevel.add(n.right);
                        }
                        
                        System.out.print(textBuffer);
                    }
        
                    System.out.println();
                    
                    if(i < height - 1) {
                        printNodeConnectors(currentLevel, textBuffer);
                    }
        
                    elements *= 2;
                    currentLevel = nextLevel;
                    nextLevel = new ArrayList<Node>(elements);
                }
            }
        }
        
        private static String createSpaceBuffer(int size) {
            char[] buff = new char[size];
            Arrays.fill(buff, ' ');
            
            return new String(buff);
        }
        
        private static void printNodeConnectors(List<Node> current, String textBuffer) {
            for(Node n : current) {
                System.out.print(textBuffer);
                if(n == null) {
                    System.out.print("        ");
                } else {
                    System.out.printf("%s      %s",
                            n.left == null ? " " : "/", n.right == null ? " " : "\\");
                }
    
                System.out.print(textBuffer);
            }
    
            System.out.println();
        }
    }
    
    private abstract class BaseBSTIterator implements Iterator<Integer> {
        private List<Integer> values;
        private int index;
        public BaseBSTIterator(Node root) {
            values = new ArrayList<>();
            addValues(root);
            
            index = 0;
        }
        
        @Override
        public boolean hasNext() {
            return index < values.size();
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            
            return values.get(index++);
        }
        
        protected void addNode(Node node) {
            values.add(node.value);
        }
        
        abstract protected void addValues(Node node);
    }
    
    public class InorderIterator extends BaseBSTIterator {
        public InorderIterator(Node root) {
            super(root);
        }

        @Override
        protected void addValues(Node node) {
            if (node != null) {
                addValues(node.left);
                addNode(node);
                addValues(node.right);
            }
        }    
      
    }
    
    public class PreorderIterator extends BaseBSTIterator {

        public PreorderIterator(Node root) {
            super(root);
        }

        @Override
        protected void addValues(AVLTree.Node node) {
            if (node != null) {
                addNode(node);
                addValues(node.left);
                addValues(node.right);
            }
        }        
    }
    
    @Override
    public Iterator<Integer> iterator() {
        return getInorderIterator();
    }
    
    public Iterator<Integer> getInorderIterator() {
        return new InorderIterator(this.root);
    }
    
    public Iterator<Integer> getPreorderIterator() {
        return new PreorderIterator(this.root);
    }
}
