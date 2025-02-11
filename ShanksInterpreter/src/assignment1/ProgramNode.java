package assignment1;

import java.util.HashMap;

public class ProgramNode extends Node {
	
	//hashmap
	public HashMap<String, FunctionNode> function = new HashMap<String, FunctionNode>();
	
	//get method
	public HashMap<String, FunctionNode> getFunction() {
		return function;
	}

	//toString method
	@Override
	public String toString() {
		return "ProgramNode [function=" + function + "]";
	}

	
}

