package assignment1;

import java.util.List;

public class FunctionCallNode extends Node{

	private String name;
	private List<ParameterNode> parameter;
	
	//constructor
	public FunctionCallNode(String name, List<ParameterNode> parameter) {
		this.name = name;
		this.parameter = parameter;
	}

	//get name
	public String getName() {
		return name;
	}

	//get parameter
	public List<ParameterNode> getParameter() {
		return parameter;
	}

	//toString mehtod
	@Override
	public String toString() {
		return "FunctionCallNode [name=" + name + "]";
	}

	public boolean isVariadic() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
