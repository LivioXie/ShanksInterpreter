package assignment1;

import java.util.ArrayList;
import java.util.HashMap;

public class Lexer {
	
	//create an arraylist to add token
	private ArrayList<Token> token = new ArrayList<>();
	//hashmap to add words
	private HashMap<String, Token.tokenType> knownWords = new HashMap<String, Token.tokenType>();
	//keep track of current number line
	private int lineNumber = 0;
	//previousIndent
	private int previousIndent = 0;
	/**
	 * lex method that accepts a single string and returns nothing. 
	 * The lex method must use one or more state machine(s) to iterate over the input string and create appropriate Tokens. 
	 * Any character not allowed by your state machine(s) throw an exception.
	 * @param s
	 * @throws Exception
	 */
	public void Lex(String s) throws SyntaxErrorException {
		//adding keywords to the hashmap
		knownWords.put("while", Token.tokenType.WHILE);
		knownWords.put("if", Token.tokenType.IF);
		knownWords.put("for", Token.tokenType.FOR);
		knownWords.put("do", Token.tokenType.DO);
		knownWords.put("integer" , Token.tokenType.INT);
		knownWords.put("double", Token.tokenType.DOUBLE);
		knownWords.put("break", Token.tokenType.BREAK);
		knownWords.put("continue", Token.tokenType.CONTINUE);
		knownWords.put("catch", Token.tokenType.CATCH);
		knownWords.put("return", Token.tokenType.RETURN);
		knownWords.put("try", Token.tokenType.TRY);
		knownWords.put("throw", Token.tokenType.THROW);
		knownWords.put("default", Token.tokenType.DEFAULT);
		knownWords.put("repeat", Token.tokenType.REPEAT);
		knownWords.put("from", Token.tokenType.FROM);
		knownWords.put("to", Token.tokenType.TO);
		knownWords.put("else", Token.tokenType.ELSE);
		knownWords.put("elseif", Token.tokenType.ELSEIF);
		knownWords.put("define", Token.tokenType.DEFINE);
		knownWords.put("string", Token.tokenType.STRING);
		knownWords.put("real", Token.tokenType.REAL);
		knownWords.put("constants", Token.tokenType.CONSTANT);
		knownWords.put("variables", Token.tokenType.VARIABLES);
		//currentState 
		int currentState = 1;
		//String to store character
		String acc =  "";	
		//get currentIndent
		int currentIndent = getIndent(s);
		//increment lineNumber when Lex method called
		lineNumber++;
		//if indentation level is greater than lastline output one or more indent
		for(int i = 0; i < currentIndent - previousIndent; i++)
			token.add(new Token(Token.tokenType.INDENT, "", lineNumber));
		//if the indentation level is less than the last line output one or more dedent
		for(int i = 0; i< previousIndent - currentIndent; i++)
			token.add(new Token(Token.tokenType.DEDENT, "", lineNumber));
		previousIndent = currentIndent;
		//for loop to loop through each character of the string s
		for(int i = 0; i < s.length();i++) {
			char c = s.charAt(i);
			//switch statement
			switch(currentState) {
			//currentState 1
			case 1: 
				/**
				 * add to character to string acc
				 * currentState become 2 if letter
				 * currentState become 3 if digit
				 * currentState become 4 if \"
				 * currentState become 5 if \'
				 * currentState become 6 if {
				 */
				if(Character.isLetter(c)) {
					acc += c;
					currentState = 2;
				}else if(Character.isDigit(c)) {
					acc += c;
					currentState = 3;
				}else if(c == '\"') {
					acc += c;
					currentState = 4;
				}else if(c == '\''){
					acc += c;
					currentState = 5;
				}else if(c == '{') {
					acc += c;
					currentState = 6;
				}else{
					//Store all the punctuation inside a String[]
					final String[] validOperator = {".", "=", "(" , ")", "{", "}" ,"+",
							"++", "-", "--", "<=", ">=", "==","!=","*",";", "<" , ">", "/", ":", ","
							,":="};
					//for loop if operator inside validOperator
					for(String operator : validOperator) {
						if(s.substring(i).startsWith(operator)) {
							if(operator == ".")
								token.add(new Token(Token.tokenType.POINT, "",lineNumber));
							else if(operator == ":=")
								token.add(new Token(Token.tokenType.COLONEQUAL, "", lineNumber));
							else if(operator == "=")
								token.add(new Token(Token.tokenType.EQUAL, "",lineNumber));
							else if(operator == "(")
								token.add(new Token(Token.tokenType.LPARENTHESIS,"",lineNumber));
							else if(operator == ")")
								token.add(new Token(Token.tokenType.RPARENTHESIS, "",lineNumber));
							else if(operator == "{")
								token.add(new Token(Token.tokenType.RBRACKET, "",lineNumber));
							else if(operator == "}")
								token.add(new Token(Token.tokenType.LBRACKET, "",lineNumber));
							else if(operator == "+")
								token.add(new Token(Token.tokenType.PLUS, "",lineNumber));
							else if(operator == "++")
								token.add(new Token(Token.tokenType.INCREMENT, "",lineNumber));
							else if(operator == "-")
								token.add(new Token(Token.tokenType.MINUS, "",lineNumber));
							else if(operator == "--")
								token.add(new Token(Token.tokenType.DECREMENT, "",lineNumber));
							else if(operator == "<=")
								token.add(new Token(Token.tokenType.GREATERTHANOREQUALTO, "",lineNumber));
							else if(operator == ">=")
								token.add(new Token(Token.tokenType.LESSTHANOREQUALTO, "",lineNumber));
							else if(operator == "==")
								token.add(new Token(Token.tokenType.EQUALTO, "",lineNumber));
							else if(operator == "!=")
								token.add(new Token(Token.tokenType.NOTEQUALTO, "",lineNumber));
							else if(operator == "*")
								token.add(new Token(Token.tokenType.MULTIPLY, "",lineNumber));
							else if(operator == ";")
								token.add(new Token(Token.tokenType.SEMICOLON,"",lineNumber));
							else if(operator == "<")
								token.add(new Token(Token.tokenType.GREATERTHAN,"",lineNumber));
							else if(operator == ">")
								token.add(new Token(Token.tokenType.LESSTHAN,"", lineNumber));
							else if(operator == "/")
								token.add(new Token(Token.tokenType.DIVIDE, "", lineNumber));
							else if(operator == ":")
								token.add(new Token(Token.tokenType.COLON, "", lineNumber));
							else if(operator == ",")
								token.add(new Token(Token.tokenType.COMMA, "", lineNumber));
							}
						}
					}
				break;
			//currentState 2
			case 2: //Word
				if(Character.isLetter(c) || Character.isDigit(c)) {
					acc += c;
					if(knownWords.containsKey(acc)) {
						token.add(new Token(knownWords.get(acc),"",lineNumber));
						acc = "";
						currentState = 1;
					}
				}else if(Character.isWhitespace(c)){
					//add new token to the arraylist as tokenType WORD, acc, and lineNumber
					token.add(new Token(Token.tokenType.IDENTIFIER, acc,lineNumber));
					acc = "";
					currentState = 1;
				}
				break;
			//currentState 3
			case 3: //Number
				if(Character.isDigit(c) || c == '.') {
					acc += c;
				}else if(Character.isWhitespace(c)) {
					//add new token to the arraylist as tokenType NUMBER, acc, and lineNumber
					token.add(new Token(Token.tokenType.NUMBER, acc,lineNumber));
					acc = "";
					currentState = 1;
				}
				break;
			//currentState 4
			case 4:
				if(c == '\"') {
					acc += c;
				}else if(Character.isWhitespace(c)) {
					//add new token to the arraylist as tokenType STRINGLITER, acc, and lineNumber
					token.add(new Token(Token.tokenType.STRINGLITERAL,acc,lineNumber));
					acc = "";
					currentState = 1;
				}
				break;	
			//currentState 5
			case 5:
				if(c == '\'') {
					acc += c;
				}else if(Character.isWhitespace(c)) {
					token.add(new Token(Token.tokenType.CHARACTERLITERAL,acc,lineNumber));
					acc = "";
					currentState = 1;
				}
				break;
			//currentState 6
			case 6: //comment
				if(c == '}') {
					//add new token to the arraylist as tokenType COMMENT, acc, and lineNumber
					token.add(new Token(Token.tokenType.COMMENT, "",lineNumber));
					currentState = 1;
					}
				break;
			default:
				throw new SyntaxErrorException("Invalid Character");
			} 
		}
		//add new token to the arraylist as tokenType ENDOFLINE and acc
		token.add(new Token(Token.tokenType.ENDOFLINE,"",lineNumber));
		//DEDENTs to get back to level 0.
		}
	
	//getIndent method
	private int getIndent(String s) {
		// TODO Auto-generated method stub
		int i = 0;
		for(char c:s.toCharArray()) {
			if(c == '\t')
				i+=4;
		}
		return i/4;
	}
	//get method for arraylist
	public ArrayList<Token> getToken() {
		return token;
	}
	//get method for lineNumber
	public int getLineNumber() {
		return lineNumber;
	}
}