package assignment1;

public class VariableNode extends Node{
	
	public enum variableType{
		  INTEGER, REAL, BOOLEAN, STRING, CHARACTER, 
		}
	
	private String name;
	private Node type;
	private boolean changeAble;
	private Object from;
	private Object to;
	private variableType type1;
	

	//constructor for int
	public VariableNode(String name, Node type, boolean changeAble, int from, int to) {
		this.name = name;
		this.type = type;
		this.changeAble = changeAble;
		this.from = from;
		this.to = to;
	}
	
	//constructor for string
	public VariableNode(String name, Node type, boolean changeAble, String from, String to) {
		this.name = name;
		this.type = type;
		this.changeAble = changeAble;
		this.from = from;
		this.to = to;
	}
	
	//constructor for float
	public VariableNode(String name, Node type, boolean changeAble, float from, float to) {
		this.name = name;
		this.type = type;
		this.changeAble = changeAble;
		this.from = from;
		this.to = to;
	}
	
	//constructor
	public VariableNode(String name, Node type, boolean changeAble) {
		this.name = name;
		this.type = type;
		this.changeAble = changeAble;
	}

	
	//get from
	public Object getFrom() {
		return from;
	}

	//get to
	public Object getTo() {
		return to;
	}

	//get name
	public String getName() {
		return name;
	}

	//get type
	public Node getType() {
		return type;
	}
	
	//get changeAble
	public boolean isChangeAble() {
		return changeAble;
	}
	
	//get type1
	public variableType getType1() {
		return type1;
	}
	
	//toString method
	@Override
	public String toString() {
		return "VariableNode [name=" + name + ", type=" + type + ", changeAble=" + changeAble + ", from=" + from
				+ ", to=" + to + "]";
	}
	
 
}
