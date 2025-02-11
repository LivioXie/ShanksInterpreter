package assignment1;

public class ParameterNode extends Node{

	private VariableReferenceNode variable;
	private Node notVar;
	
	//constructor
	public ParameterNode(VariableReferenceNode variable, Node notVar) {
		this.variable = variable;
		this.notVar = notVar;
	}

	//get variable
	public VariableReferenceNode getVariable() {
		return variable;
	}

	//get notVar
	public Node getNotVar() {
		return notVar;
	}

	//toString method
	@Override
	public String toString() {
		return "ParameterNode [variable=" + variable + ", notVar=" + notVar + "]";
	}

	public boolean isVar() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
