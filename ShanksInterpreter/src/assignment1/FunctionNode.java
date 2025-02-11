package assignment1;

import java.util.List;

public class FunctionNode extends Node{
	private String name;
	private List<VariableNode> parameters;
	private List<VariableNode> constants;
	private List<VariableNode> variables;
	private List<StatementNode> statements;
	private boolean variadic;
	
	
	public boolean isVariadic() {
		return variadic;
	}
	//constructor
	public FunctionNode(String name, List<VariableNode> parameters, List<VariableNode> constants,
			List<VariableNode> variables, List<StatementNode> statements) {
		this.name = name;
		this.parameters = parameters;
		this.constants = constants;
		this.variables = variables;
		this.statements = statements;
	}
	
	//get name
	public String getName() {
		return name;
	}

	//get parameter
	public List<VariableNode> getParameters() {
		return parameters;
	}

	//get constants
	public List<VariableNode> getConstants() {
		return constants;
	}

	//get variables
	public List<VariableNode> getVariables() {
		return variables;
	}

	//get statements
	public List<StatementNode> getStatements() {
		return statements;
	}

	//toString method
	@Override
	public String toString() {
		return "FunctionNode [name=" + name + ", parameters=" + parameters + ", constants=" + constants + ", variables="
				+ variables + "]";
	}

}
