package assignment1;

import java.util.List;

public class BuiltInEnd extends FunctionNode{

	//constructor
	public BuiltInEnd(String name, List<VariableNode> parameters, List<VariableNode> constants,
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
        RealDataType end = (RealDataType) list.get(1);
        RealDataType result = (RealDataType) list.get(0);
        result.setStore(end.getStore() - 1);
    }

}
