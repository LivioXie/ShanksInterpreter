package assignment1;

import java.util.List;
import java.util.Random;

public class BuiltInGetRandom extends FunctionNode{

	//constructor
	public BuiltInGetRandom(String name, List<VariableNode> parameters, List<VariableNode> constants,
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
        IntegerDataType result = (IntegerDataType) list.get(0);
        Random random = new Random();
        result.setStore(random.nextInt());
    }

}
