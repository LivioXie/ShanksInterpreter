package assignment1;

import java.util.List;

public class BuiltInRealToInteger extends FunctionNode{

	//constructor
	public BuiltInRealToInteger(String name, List<VariableNode> parameters, List<VariableNode> constants,
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
	    IntegerDataType output = (IntegerDataType) list.get(1);
	    output.setStore((int) (input.getStore()));
	}


}
