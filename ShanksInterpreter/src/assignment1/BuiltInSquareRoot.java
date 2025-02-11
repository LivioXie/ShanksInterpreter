package assignment1;

import java.util.List;

public class BuiltInSquareRoot extends FunctionNode{

	//constructor
	public BuiltInSquareRoot(String name, List<VariableNode> parameters, List<VariableNode> constants,
			List<VariableNode> variables, List<StatementNode> statements) {
		super(name, parameters, constants, variables, statements);
		// TODO Auto-generated constructor stub
	}
	
	@Override
    public boolean isVariadic() {
        return false;
    }
	 //execute method 
	public void execute(List<InterpreterDataType> list) {
		RealDataType input = (RealDataType) list.get(0);
		RealDataType output = (RealDataType) list.get(1);
		output.setStore(Math.sqrt(input.getStore()));
	}

}
