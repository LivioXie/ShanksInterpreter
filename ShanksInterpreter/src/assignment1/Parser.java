package assignment1;

import java.util.ArrayList;
import java.util.List;


public class Parser{
	//collection of token
	private List<Token> token;
	
	//constructor
	public Parser(List<Token> token) {
		this.token = token;
	}
	
	//matchAndRemove method
	public Token matchAndRemove(Token.tokenType type) {
		if(type == token.get(0).getType())
			return token.remove(0);
		else
			return null;
	}
	
	//expectEndOfLine method
	public void expectEndOfLine() throws SyntaxErrorException {
		if(matchAndRemove(Token.tokenType.ENDOFLINE) == null)
			throw new SyntaxErrorException("No endofline");
	}
	
	//peek method
	public Token peek(int a) {
		if(token.size() >= a)
			return token.get(a-1);
		else 
			return null;
	}
	
	//parse method
	public Node parse() throws SyntaxErrorException {
		ProgramNode program  = new ProgramNode();
		BuiltInFunction(program);
		while(peek(1) != null) {
			FunctionNode function = function();
			Node ex = expression();
			expectEndOfLine();
			System.out.println(function.toString());
			System.out.println(ex.toString());
		}
		return program;
	}
	
	private void BuiltInFunction(ProgramNode program) {
		// create BuiltInRead and insert into hash map
		BuiltInRead read = new BuiltInRead("read", null, null, null, null);
		program.getFunction().put("read", read);
		// create BuiltInWrite and insert into hash map
		BuiltInWrite write = new BuiltInWrite("write", null, null, null, null);
		program.getFunction().put("write", write);
		// create BuiltInLeft and insert into hash map
		BuiltInLeft left = new BuiltInLeft("left", null, null, null, null);
		program.getFunction().put("left", left);
		// create BuiltInRight and insert into hash map
		BuiltInRight right = new BuiltInRight("right", null, null, null, null);
		program.getFunction().put("right", right);
		// create BuiltInSubstring and insert into hash map
		BuiltInSubstring subString = new BuiltInSubstring("subString", null, null, null, null);
		program.getFunction().put("subString", subString);
		// create BuiltInSquareRoot and insert into hash map
		BuiltInSquareRoot sqrt = new BuiltInSquareRoot("sqrt", null, null, null, null);
		program.getFunction().put("sqrt", sqrt);
		// create BuiltInGetRandom and insert into hash map
		BuiltInGetRandom getRandom = new BuiltInGetRandom("getRandom", null, null, null, null);
		program.getFunction().put("getRandom", getRandom);
		// create BuiltInIntegerToReal and insert into hash map
		BuiltInIntegerToReal integerToReal = new BuiltInIntegerToReal("integerToReal", null, null, null, null);
		program.getFunction().put("integerToReal", integerToReal);
		// create BuiltInRealToInteger and insert into hash map
		BuiltInRealToInteger realToInteger = new BuiltInRealToInteger("realToInteger", null, null, null, null);
		program.getFunction().put("realToInteger", realToInteger);
		// create BuiltInStart and insert into hash map
		BuiltInStart start = new BuiltInStart("start", null, null, null, null);
		program.getFunction().put("start", start);
		// create BuiltInStart and insert into hash map
		BuiltInEnd end = new BuiltInEnd("end", null, null, null, null);
		program.getFunction().put("end", end);
	}
	//expression method
	public Node expression() throws SyntaxErrorException {
		// TODO Auto-generated method stubterm();
		//call term()
		Node left = term();
		Node returnNode = null;
		//check if next token is PLUS
		if(peek(1).getType() == Token.tokenType.PLUS){
			matchAndRemove(Token.tokenType.PLUS);
			if(peek(1).getType() == Token.tokenType.PLUS)
				//if PLUS recursively call expression
				returnNode = new MathOpNode(MathOpNode.mathOperation.ADD,left,expression());
			else
				//if not call term
				returnNode = new MathOpNode(MathOpNode.mathOperation.ADD,left,term());
		//check if next token is MINUS
		}else if(peek(1).getType() == Token.tokenType.MINUS) {
				matchAndRemove(Token.tokenType.MINUS);
				if(peek(1).getType() == Token.tokenType.MINUS)
					//if MINUS recursively call expression
					returnNode = new MathOpNode(MathOpNode.mathOperation.SUBTRACT,left,expression());
				else
					//if not call term
					returnNode = new MathOpNode(MathOpNode.mathOperation.SUBTRACT,left,term());
			}else
				//return left
				return left;
		//5+5+5
		//5+5
		//5
		//return returnNode
		return returnNode;
	}

	//term method
	public Node term() throws SyntaxErrorException {
		// TODO Auto-generated method stub
		//call factor()
		Node left = factor();
		Node returnNode = null;
		//check if next token is MULTIPLY
		if(peek(1).getType() == Token.tokenType.MULTIPLY) {	
			matchAndRemove(Token.tokenType.MULTIPLY);
			if(peek(1).getType() == Token.tokenType.MULTIPLY)
				//if MULTIPLY recursively call expression
				returnNode = new MathOpNode(MathOpNode.mathOperation.MULTIPLY,left,expression()); 
			else
				////if not call factor
				returnNode = new MathOpNode(MathOpNode.mathOperation.MULTIPLY,left,factor());
		//check if next token is DIVIDE
		}else if(peek(1).getType() == Token.tokenType.DIVIDE) {
			matchAndRemove(Token.tokenType.DIVIDE);
			//if DIVIDE recursively call expression
			if(peek(1).getType() == Token.tokenType.DIVIDE)
				returnNode = new MathOpNode(MathOpNode.mathOperation.DIVIDE,left, expression());
			else
			//if not call factor
				returnNode = new MathOpNode(MathOpNode.mathOperation.DIVIDE, left,factor());
		}else
			return left;
		return returnNode;
	}
	
	//boolCompare method
	public Node boolCompare() throws SyntaxErrorException {
		System.out.println("boolCompare Reached: " + peek(1).toString());
		//call expression()
		Node left = expression();
		Node returnNode = null;
		//check if next token is greaterthan
		if(peek(1).getType() == Token.tokenType.GREATERTHAN) {
			matchAndRemove(Token.tokenType.GREATERTHAN);
			if(peek(1).getType() == Token.tokenType.GREATERTHAN)
				//if greaterthan recursively call boolCompare
				returnNode = new BooleanCompareNode(BooleanCompareNode.CompareNode.GREATERTHAN, left, boolCompare());
			else 
				//if not call expression
				returnNode = new BooleanCompareNode(BooleanCompareNode.CompareNode.GREATERTHAN, left, expression());
			//check if next token is lessthan
		}else if(peek(1).getType() == Token.tokenType.LESSTHAN) {
			matchAndRemove(Token.tokenType.LESSTHAN);
			if(peek(1).getType() == Token.tokenType.LESSTHAN)
				//if lessthan recursively call boolCompare
				returnNode = new BooleanCompareNode(BooleanCompareNode.CompareNode.LESSTHAN, left, boolCompare());
			else 
				//if not call expression
				returnNode = new BooleanCompareNode(BooleanCompareNode.CompareNode.LESSTHAN, left, expression());
			//check if next token is greaterthanorequalto
		}else if(peek(1).getType() == Token.tokenType.GREATERTHANOREQUALTO) {
			matchAndRemove(Token.tokenType.GREATERTHANOREQUALTO);
			if(peek(1).getType() == Token.tokenType.GREATERTHANOREQUALTO)
				//if greaterthanorequalto recursively call boolCompare
				returnNode = new BooleanCompareNode(BooleanCompareNode.CompareNode.GREATERTHANANDEQUALTO, left, boolCompare());
			else 
				//if not call expression
				returnNode = new BooleanCompareNode(BooleanCompareNode.CompareNode.GREATERTHANANDEQUALTO, left, expression());
			//check if next token is lessthanorequalto
		}else if(peek(1).getType() == Token.tokenType.LESSTHANOREQUALTO) {
			matchAndRemove(Token.tokenType.LESSTHANOREQUALTO);
			if(peek(1).getType() == Token.tokenType.LESSTHANOREQUALTO)
				//if lessthanorequalto recursively call boolCompare
				returnNode = new BooleanCompareNode(BooleanCompareNode.CompareNode.LESSTHANANDEQUALTO, left, boolCompare());
			else 
				//if not call expression
				returnNode = new BooleanCompareNode(BooleanCompareNode.CompareNode.LESSTHANANDEQUALTO, left, expression());
			//check if next token is equal
		}else if(peek(1).getType() == Token.tokenType.EQUAL) {
			matchAndRemove(Token.tokenType.EQUAL);
			if(peek(1).getType() == Token.tokenType.EQUAL)
				//if equal recursively call boolCompare
				returnNode = new BooleanCompareNode(BooleanCompareNode.CompareNode.EQUAL, left, boolCompare());
			else 
				//if not call expression
				returnNode = new BooleanCompareNode(BooleanCompareNode.CompareNode.EQUAL, left, expression());
			//else return left
		}else if(peek(1).getType() == Token.tokenType.COLONEQUAL) {
			matchAndRemove(Token.tokenType.COLONEQUAL);
			if(peek(1).getType() == Token.tokenType.COLONEQUAL)
				returnNode = new BooleanCompareNode(BooleanCompareNode.CompareNode.COLONEQUAL, left, boolCompare());
			else
				returnNode = new BooleanCompareNode(BooleanCompareNode.CompareNode.COLONEQUAL, left, expression());
		}else
			return left;
		//return returnNode
		return returnNode;
	}
	
	//factor method
	public Node factor() throws SyntaxErrorException {
		System.out.println("Factor Reached: " + peek(1).toString());
		Node expression = null;
		//matchAndRemove minus
		Token negative = matchAndRemove(Token.tokenType.MINUS);
		//matchAndRemove left parenthesis
		Token lparen = matchAndRemove(Token.tokenType.LPARENTHESIS);
		//if left parenthesis not null call expression and matchAndRemove right parenthesis
		if(lparen != null) {
			expression = expression();
			Token rparen = matchAndRemove(Token.tokenType.RPARENTHESIS);
			//if right parenthesis is null throw exception
			if(rparen == null) 
				throw new SyntaxErrorException("No right parenthesis");
			return expression;
		}
		//matchAndRemove number
		Token number = matchAndRemove(Token.tokenType.NUMBER);
		//if number is null throw exception
		if(number == null) {
			if(peek(1).getType() == Token.tokenType.IDENTIFIER) {
				String name = peek(1).getValue();
				matchAndRemove(Token.tokenType.IDENTIFIER);
				if(peek(2).getType() == Token.tokenType.LBRACKET) {
					Node index = expression();
					matchAndRemove(Token.tokenType.RBRACKET);
					return new VariableReferenceNode(name, index);
				}else 
					return new VariableReferenceNode(name, null);
			}else {
				throw new SyntaxErrorException("No number or variable reference");
			}
		}
		//check if number contain . if contain return RealNode else return Integer Node
		else if(number.getValue().contains(".")) {
			return new RealNode(Float.parseFloat(number.getValue()));
		}else {
		int value = Integer.parseInt(number.getValue());
		if(negative != null)
			value = -value;
		return new IntegerNode(value);
	}
}
	//function method
	public FunctionNode function() throws SyntaxErrorException {
		System.out.println("function Reached: " + peek(1).toString());
		//definde expected
		if(matchAndRemove(Token.tokenType.DEFINE) == null)
			throw new SyntaxErrorException("Define expected");
		Token functionName = matchAndRemove(Token.tokenType.IDENTIFIER); 
		//identifier expected
		if(functionName == null)
			throw new SyntaxErrorException("Identifier expected");
		//left parenthesis expected
		if(matchAndRemove(Token.tokenType.LPARENTHESIS) == null)
			throw new SyntaxErrorException("Left parenthesis expected");
		//list variable declaration
		List<VariableNode> parameterDeclaration = parameterDeclaration();
		
		//matchAndRemove right parenthesis
		matchAndRemove(Token.tokenType.RPARENTHESIS);
		expectEndOfLine();
		//lsit constant
		List<VariableNode> constants = constants();
		//list variables
		List<VariableNode> variables = variables();
		//expect indent
		if(matchAndRemove(Token.tokenType.INDENT) == null)
			throw new SyntaxErrorException("Indent expected");
		//list statement
	     List<StatementNode> statements = statements();
	    //dedent expected
		if(matchAndRemove(Token.tokenType.DEDENT) == null)
			throw new SyntaxErrorException("Dedent expected");
		//return functionNode
		return new FunctionNode(functionName.getValue(), parameterDeclaration, constants, variables, statements);
	}

	//assignment method
	public AssignmentNode assignment() throws SyntaxErrorException {
		Token identifier = null;
		String name = null;
		if(peek(1).getType() == Token.tokenType.IDENTIFIER) {
			identifier = matchAndRemove(Token.tokenType.IDENTIFIER);
			name = identifier.getValue();
		}
		Node arrayIndex = null;
		if(peek(1).getType() == Token.tokenType.LBRACKET) {
			matchAndRemove(Token.tokenType.LBRACKET);
			arrayIndex = expression();
			matchAndRemove(Token.tokenType.RBRACKET);
		}
		matchAndRemove(Token.tokenType.EQUAL);
		Node boolCompare = boolCompare();
		return new AssignmentNode(new VariableReferenceNode(name,arrayIndex), boolCompare);
	}
	
	public Node statement() throws SyntaxErrorException {
		return assignment();
	}
	//statements method
	public List<StatementNode> statements() throws SyntaxErrorException {
		// TODO Auto-generated method stub
		System.out.println("statements Reached: " + peek(1).toString());
	    List<StatementNode> statements = new ArrayList<>();
	    //repeatedly call assignment
	    while (statement() != null) {
	        statement();
	    }
	    // expect dedent
	    if(matchAndRemove(Token.tokenType.DEDENT) == null)
	    	throw new SyntaxErrorException("Dedent expected");
	    return statements; 
	}

	public List<VariableNode> constants() throws SyntaxErrorException {
		// TODO Auto-generated method stub
		System.out.println("constants Reached: " + peek(1).toString());
		//constant expected
		if(matchAndRemove(Token.tokenType.CONSTANT) == null)
			throw new SyntaxErrorException("Constant expected");
		//list constant
		List<VariableNode> constants = new ArrayList<>();
		while(true) {
			//check if next token is Identifier
			if(peek(1).getType() == Token.tokenType.IDENTIFIER) {
				//matchAndRemove identifier
				Token identifier = matchAndRemove(Token.tokenType.IDENTIFIER);
				//equal sign expected
				if(matchAndRemove(Token.tokenType.EQUAL) == null)
					throw new SyntaxErrorException("No equal sign");
				Token type = peek(1);
				Node returnNode = null;
				//check if Number
				if(type.getType() == Token.tokenType.NUMBER) {
					//if number contain . RealNode else IntegerNode and add them inside the list
					matchAndRemove(Token.tokenType.NUMBER);
					if(type.getValue().contains(".")) {
						returnNode = new RealNode(Float.parseFloat(type.getValue()));
						constants.add(new VariableNode(identifier.getValue(),returnNode,true));
					}else {
						returnNode = new IntegerNode(Integer.parseInt(type.getValue()));
						constants.add(new VariableNode(identifier.getValue(),returnNode,true));
					}
				//check if stringliteral
				}else if(type.getType() == Token.tokenType.STRINGLITERAL) {
					matchAndRemove(Token.tokenType.STRINGLITERAL);
					returnNode = new StringNode(type.getValue());
					constants.add(new VariableNode(identifier.getValue(),returnNode,true));
					//check if characterliteral
				}else if(type.getType() == Token.tokenType.CHARACTERLITERAL) {
					matchAndRemove(Token.tokenType.CHARACTERLITERAL);
					String a = type.getValue();
					returnNode = new CharacterNode(a.charAt(0));
					constants.add(new VariableNode(identifier.getValue(),returnNode,true));
					//check if boolean
				}else if(type.getType() == Token.tokenType.BOOLEAN) {
					matchAndRemove(Token.tokenType.BOOLEAN);
					returnNode = new BooleanNode(Boolean.parseBoolean(type.getValue()));
					constants.add(new VariableNode(identifier.getValue(),returnNode,true));
				}else 
					throw new SyntaxErrorException("No constant type");
			}else
				break;
			if(matchAndRemove(Token.tokenType.ENDOFLINE) == null)
				throw new SyntaxErrorException("No end of line");
		}
		return constants;
	}

	public List<VariableNode> variables() throws SyntaxErrorException {
		// TODO Auto-generated method stub
		System.out.println("variable Reached: " + peek(1).toString());
		//variables expected
		if(matchAndRemove(Token.tokenType.VARIABLES) == null)
			throw new SyntaxErrorException("variables expected");
		List<VariableNode> variable = new ArrayList<>();
		while(true) {
			//check if next token is identifier
			if(peek(1).getType() == Token.tokenType.IDENTIFIER) {
				//matchAndRemove identifier
				Token identifier = matchAndRemove(Token.tokenType.IDENTIFIER);
				while(peek(1).getType() == Token.tokenType.COMMA) {
				//comma expected
				if(matchAndRemove(Token.tokenType.COMMA) == null)
					throw new SyntaxErrorException("No comma");
				if(matchAndRemove(Token.tokenType.IDENTIFIER) == null)
					throw new SyntaxErrorException("No identifier");
				//colon expected
				}
				if(matchAndRemove(Token.tokenType.COLON) == null)
					throw new SyntaxErrorException("No colon");
				Token type = peek(1);
				Node returnNode = null;
				//check if Integer
				if(type.getType() == Token.tokenType.INT) {
					matchAndRemove(Token.tokenType.INT);
					returnNode = new IntegerNode(0);
					variable.add(new VariableNode(identifier.getValue(),returnNode,true));
					//check if string
				}else if(type.getType() == Token.tokenType.STRING) {
					matchAndRemove(Token.tokenType.STRING);
					returnNode = new StringNode("");
					variable.add(new VariableNode(identifier.getValue(),returnNode,true));
					//check if float number
				}else if(type.getType() == Token.tokenType.REAL) {
					matchAndRemove(Token.tokenType.REAL);
					returnNode = new RealNode(0);
					variable.add(new VariableNode(identifier.getValue(),returnNode,true));
					//check if character
				}else if(type.getType() == Token.tokenType.CHARACTER) {
					matchAndRemove(Token.tokenType.CHARACTER);
					String a = type.getValue();
					returnNode = new CharacterNode(a.charAt(0));
					variable.add(new VariableNode(identifier.getValue(),returnNode,true));
					//check if boolean
				}else if(type.getType() == Token.tokenType.BOOLEAN) {
					matchAndRemove(Token.tokenType.BOOLEAN);
					returnNode = new BooleanNode(Boolean.parseBoolean(type.getValue()));
					variable.add(new VariableNode(identifier.getValue(),returnNode,true));
				}else 
					throw new SyntaxErrorException("No variable type");
			}else 
				break;
			if(matchAndRemove(Token.tokenType.ENDOFLINE) == null)
				throw new SyntaxErrorException("No end of line");
		}
		return variable;
	}

	public List<VariableNode> parameterDeclaration() throws SyntaxErrorException {
		// TODO Auto-generated method stub
		System.out.println("parameterDeclaration Reached: " + peek(1).toString());
		List<VariableNode> parameterDeclaration = new ArrayList<>();
		//check if next token if right parenthesis
		while(peek(1).getType() != Token.tokenType.RPARENTHESIS) {
			//matchAndRemove identifier
			Token functionName = matchAndRemove(Token.tokenType.IDENTIFIER);
			//identifier expected
			if(functionName == null)
				throw new SyntaxErrorException("Identifier expected");
			//colon expected
			if(matchAndRemove(Token.tokenType.COLON) == null)
				throw new SyntaxErrorException("Colon expected");
			Node type = null;
			//check if next token is String
			if(peek(1).getType() == Token.tokenType.STRING ) {
				type = new StringNode("");
				matchAndRemove(Token.tokenType.STRING);
				//check if next token is int
			}else if(peek(1).getType() == Token.tokenType.INT) {
				System.out.println(peek(1).getValue());
				type = new IntegerNode(0);
				System.out.println("Test " +type.toString());
				matchAndRemove(Token.tokenType.INT);
				//check if next token is float number
			}else if(peek(1).getType() == Token.tokenType.REAL) {
				type = new RealNode(0);
				matchAndRemove(Token.tokenType.REAL);
			}else
				throw new SyntaxErrorException("type expected");
			//semicolon expected
			if(peek(1).getType() != Token.tokenType.RPARENTHESIS)
			if(matchAndRemove(Token.tokenType.SEMICOLON) == null)
				throw new SyntaxErrorException("Semicolon expected");
			boolean Variable = false;
			if(peek(1).getType() == Token.tokenType.VARIABLES) {
				matchAndRemove(Token.tokenType.VARIABLES);
				Variable = true;
			}
			//add to list
			parameterDeclaration.add(new VariableNode(functionName.getValue(), type ,Variable));
		}
		return parameterDeclaration;
	}
	
	//parse if method
	public IfNode parseIf() throws SyntaxErrorException {
		//matchAndRemove if 
		if(matchAndRemove(Token.tokenType.IF) == null)
			return null;
		//matchAndRemove left parenthesis
		if(matchAndRemove(Token.tokenType.LPARENTHESIS) == null)
			throw new SyntaxErrorException("No left parenthesis");
		//calls the boolCompare() method 
		BooleanCompareNode en = (BooleanCompareNode) boolCompare();
		//check if null if null throw error
		if(en == null)
			throw new SyntaxErrorException("error");
		//matchAndRemove right parenthesis
		if(matchAndRemove(Token.tokenType.RPARENTHESIS) == null)
			throw new SyntaxErrorException("No right parenthesis");
		//call statements() method
		List<StatementNode> st = statements();
		//check if null
		if(st == null)
			throw new SyntaxErrorException("no statement");
		IfNode nextIf = null;
		//matchAndRemove elseif
		if(matchAndRemove(Token.tokenType.ELSEIF) != null) {
			//call parseIf() method
			nextIf = parseIf();
		}else {
			//matchAndRemove if
			if(matchAndRemove(Token.tokenType.IF) != null)
				//call parseIf() method
				nextIf = parseIf();
			else {
				//call statement method
				List<StatementNode> statements = statements();
				// check if null
	            if (statements == null) {
	                throw new SyntaxErrorException("no statement");
	            }
	            nextIf = new IfNode(null, statements, null);
			}
		}
		// return IfNode(en,st,nextIf)
		return new IfNode(en,st,nextIf);
	}
	
	//parse While method
	public WhileNode parseWhile() throws SyntaxErrorException {
		//matchAndRemove while
		if(matchAndRemove(Token.tokenType.WHILE) == null)
			return null;
		//matchAndRemove left parenthesis
		if(matchAndRemove(Token.tokenType.LPARENTHESIS) == null)
			throw new SyntaxErrorException("No left parenthesis");
		//call boolCompare method
		BooleanCompareNode en = (BooleanCompareNode) boolCompare();
		//check if null
		if(en == null)
			throw new SyntaxErrorException("error");
		//matchAndRemove right parenthesis
		if(matchAndRemove(Token.tokenType.RPARENTHESIS) == null)
			throw new SyntaxErrorException("No right parenthesis");
		//call statement method
		List<StatementNode> st = statements();
		//check if null
		if(st == null)
			throw new SyntaxErrorException("error");
		//return
		return new WhileNode(en,st);
	}
	
	//parse for method
	public ForNode parseFor() throws SyntaxErrorException {
		//matchAndRemove for
		if(matchAndRemove(Token.tokenType.FOR) == null)
			return null;
		//matchAndRemove variable
		if(matchAndRemove(Token.tokenType.VARIABLES) == null)
			throw new SyntaxErrorException("no variable");
		//matchAndRemove from
		if(matchAndRemove(Token.tokenType.FROM) == null)
			throw new SyntaxErrorException("no from");
		//call expression
		Node from = expression();
		//check if null
		if (from == null)
			throw new SyntaxErrorException("no expression");
		//matchAndRemove to
		if(matchAndRemove(Token.tokenType.TO) == null)
			throw new SyntaxErrorException("no to after from expression");
		//call expression
		Node to = expression();
		//check if null
		if(to == null)
			throw new SyntaxErrorException("no to after expressiong");
		//call statement method
		List<StatementNode> st = statements();
		//check if null
		if(st == null)
			throw new SyntaxErrorException("no statement");
		//return
		return new ForNode(from,to,st);
	}
	
	//parse functionCall method
	public FunctionCallNode parseFunctionCalls() throws SyntaxErrorException {
		Token identifier = matchAndRemove(Token.tokenType.IDENTIFIER);
		//check if there is identifier
		if(identifier == null)
			return null;
		//get value of identifier
		String name = identifier.getValue();
		//matchAndRemove left parenthesis
		if(matchAndRemove(Token.tokenType.LPARENTHESIS) == null)
			throw new SyntaxErrorException("no left parenthesis");
		List<ParameterNode> parameters = new ArrayList<ParameterNode>();
		//while loop
		while(true) {
			//call expression
			Node en = expression();
			//check if null
			if(en == null)
				break;
			//check if en is an instance of VariableReference class
			if(en.getClass() == VariableReferenceNode.class) {
				//call expression
				Node value = expression();
				if(value == null)
					throw new SyntaxErrorException("no expression");
				//add to list parameters
				parameters.add(new ParameterNode((VariableReferenceNode) en,value));
			}else
				parameters.add(new ParameterNode(null,en));
			//matchAndRemove comma
			if(matchAndRemove(Token.tokenType.COMMA) == null)
				break;
		}
		//matchAndRemove right parenthesis
		if(matchAndRemove(Token.tokenType.RPARENTHESIS) == null)
			throw new SyntaxErrorException("no right parenthesis");
		//return
		return new FunctionCallNode(name, parameters);
	}
	
	public VariableNode range() throws SyntaxErrorException {
		//matchAndRemove variable
		if(matchAndRemove(Token.tokenType.VARIABLES) == null)
			return null;
		Token name = matchAndRemove(Token.tokenType.IDENTIFIER);
		//check if null
		if(name == null)
			throw new SyntaxErrorException("no identifier");
		String a = name.getValue();
		Token integer = matchAndRemove(Token.tokenType.INT);
		Token string = matchAndRemove(Token.tokenType.STRING);
		Token real = matchAndRemove(Token.tokenType.REAL);
		//check if integer null
		if(integer == null)
			throw new SyntaxErrorException("no integer");
		Node type = null;
		//check if integer is int
		if(integer.getType() == Token.tokenType.INT) {
			//matchAndRemove from
			if(matchAndRemove(Token.tokenType.FROM) == null)
				throw new SyntaxErrorException("no from");
			Token number = matchAndRemove(Token.tokenType.NUMBER);
			//check if null
			if(number == null)
				throw new SyntaxErrorException("no number");
			int from = Integer.parseInt(number.getValue());
			//matchAndRemove to
			if(matchAndRemove(Token.tokenType.TO) == null)
				throw new SyntaxErrorException("no to");
			Token number2 = matchAndRemove(Token.tokenType.NUMBER);
			//check if null
			if(number2 == null)
				throw new SyntaxErrorException("no number");
			int to = Integer.parseInt(number2.getValue());
			type = new VariableNode(a, type, true, from, to);
			//check if real is real
		}else if(real.getType() == Token.tokenType.REAL) {
			//matchAndRemove from
			if(matchAndRemove(Token.tokenType.FROM) == null)
				throw new SyntaxErrorException("no from");
			Token number = matchAndRemove(Token.tokenType.NUMBER);
			//check if null
			if(number == null)
				throw new SyntaxErrorException("no number");
			float from = Float.parseFloat(number.getValue());
			//matchAndRemove to 
			if(matchAndRemove(Token.tokenType.TO) == null)
				throw new SyntaxErrorException("no to");
			Token number2 = matchAndRemove(Token.tokenType.NUMBER);
			//check if null
			if(number2 == null)
				throw new SyntaxErrorException("no number");
			float to = Float.parseFloat(number2.getValue());
			type = new VariableNode(a, type, true, from, to);
			//check if string is string
		}else if(string.getType() == Token.tokenType.STRING) {
			type = new VariableNode(a, type, false);
		}
		//return new VariableNode
		return new VariableNode(a, type, true);
	}
}
