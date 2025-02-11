package assignment1;

public class BooleanCompareNode extends Node {
	public enum CompareNode{
		LESSTHAN, GREATERTHAN, LESSTHANANDEQUALTO, GREATERTHANANDEQUALTO, EQUAL, COLONEQUAL;
	}
	
	private CompareNode type;
	private Node left;
	private Node right;
	
	//constructor
	public BooleanCompareNode(CompareNode type, Node left, Node right) {
		this.type = type;
		this.left = left;
		this.right = right;
	}
	
	//get type
	public CompareNode getType() {
		return type;
	}
	
	//get left
	public Node getLeft() {
		return left;
	}
	
	//get right
	public Node getRight() {
		return right;
	}
	
	//toString method
	@Override
	public String toString() {
		return "BooleanCompareNode [type=" + type + ", left=" + left + ", right=" + right + "]";
	}
}
