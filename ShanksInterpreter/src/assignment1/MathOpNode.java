package assignment1;

public class MathOpNode extends StatementNode{
	public enum mathOperation {
		ADD, SUBTRACT, MULTIPLY, DIVIDE;
	}
	private mathOperation mathOperation;
	private Node left;
	private Node right;
	
	//constructor
	public MathOpNode(mathOperation mathOperation, Node left, Node right) {
		this.mathOperation = mathOperation;
		this.left = left;
		this.right = right;
	}
	//get left Node
	public Node getLeft() {
		return left;
	}
	//get right Node
	public Node getRight() {
		return right;
	}
	//get mathOperation
	public mathOperation getMathOperation() {
		return mathOperation;
	}
	//toString method
	@Override
	public String toString() {
		return "(" + mathOperation + " " + left + " " + right + ")";
	}

}
