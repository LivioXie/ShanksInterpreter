package assignment1;

public class Token {
	
	public enum tokenType{
		NUMBER, IDENTIFIER, ENDOFLINE,
		POINT, EQUAL, LPARENTHESIS, RPARENTHESIS, RBRACKET, 
		LBRACKET, PLUS, INCREMENT, MINUS, DECREMENT, GREATERTHANOREQUALTO, 
		LESSTHANOREQUALTO, EQUALTO, NOTEQUALTO, MULTIPLY, STRINGLITERAL, 
		WHILE, IF, FOR, DO, INT, DOUBLE, BREAK, CONTINUE, CATCH, RETURN, TRY, THROW, DEFAULT, 
		SEMICOLON, CHARACTERLITERAL, COMMENT, INDENT, DEDENT, GREATERTHAN, LESSTHAN, DIVIDE, 
		DEFINE, COLON, VARIABLES, STRING, REAL, CONSTANT, BOOLEAN, COMMA, CHARACTER, REPEAT, 
		FROM, TO, ELSE, ELSEIF, COLONEQUAL;
	}
	
	private tokenType type;
	private String value;
	private int lineNumber;
	
	//Constructor
	public Token(Token.tokenType type, String value, int lineNumber) {
		this.type = type;
		this.value = value;
		this.lineNumber = lineNumber;
	}

	//get tokenType type
	public tokenType getType() {
		return type;
	}

	//get String value
	public String getValue() {
		return value;
	}
	
	//get int LineNumber
	public int getLineNumber() {
		return lineNumber;
	}

	//toString method
	@Override
	public String toString() {
		if(value  == "")
			return type +" "+ value;
		else
			return (type + "(" + value + ")" + " ");
	}
	
}
