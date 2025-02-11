package assignment1;

import java.util.List;

public class BuiltInStart extends FunctionNode{

	//constructor
	public BuiltInStart(String name, List<VariableNode> parameters, List<VariableNode> constants,
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
        RealDataType result = (RealDataType) list.get(0);
        result.setStore(0);
    }
}
