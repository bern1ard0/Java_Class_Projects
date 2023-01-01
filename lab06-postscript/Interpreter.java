import structure5.*;
/**
 * An implementation of a basic PostScript interpreter.
 * This class provides the methods that interpret the postcrip
 * commands in the samples file. 
 * It intersects the functionality of the Token and Reader classes.
 * The two by providing methods 
 * which adequately 
 */
public class Interpreter {
	// Global Stack Variable.
	protected StackVector<Token> stack = new StackVector<>();
	// Global Symbol table
	protected SymbolTable table = new SymbolTable();
	/**
	 * 
	 * @param token value to be appended to stack
	 * @pre token must not be null
	 */
	public void setResource(Token token) {
		Assert.pre(token != null,"Not Valid integer. ");
		stack.push(token);
	}
	/**
	 * 
	 * Gets element (which is a Token) from stack.
	 * @return element in stack.
	 */
	public Token getResource() {
		Assert.pre(!stack.isEmpty(),"Empty Stack");
		return stack.pop();
	}
	/**
	 * prints elements in Stack.
	 * @pre Stack is not null. 
	 */
	public void pstack() {
		for (Token var: stack){
			System.out.println(var);
		}
	}
	/**
	 * adds last two elements in stack.
	 * @pre Stack must not be empty.
	 */
	public void add() {
		Assert.pre(!stack.isEmpty(),"Empty Stack");
		Token i = stack.pop();
		Token j = stack.pop();
		double sum = i.getNumber() + j.getNumber();
		stack.add(new Token(sum));
	}
	/**
	 * subtracts last two elements from the stack.
	 * @pre Stack is not empty.
	 */
	public void sub() {
		Assert.pre(!stack.isEmpty(),"Empty Stack");
		Token i = stack.pop();
		Token j = stack.pop();
		double sub = j.getNumber() - i.getNumber();
		stack.add(new Token(sub));
	}
	/**
	 * multiplies the last two values in stack.
	 * @pre stack must not be empty.
	 */
	public void mul() {
		Assert.pre(!stack.isEmpty(),"Empty Stack");
		Token i = stack.pop();
		Token j = stack.pop();
		double mul = i.getNumber() * j.getNumber();
		stack.add(new Token(mul));
	}
	/**
	 * divides the last two values in stack.
	 * @pre stack must not be empty.
	 */
	public void div() {
		Assert.pre(!stack.isEmpty(),"Empty Stack");
		Token i = stack.pop();
		Token j = stack.pop();
		double div = j.getNumber() / i.getNumber();
		stack.add(new Token(div));
	}
	/**
	 * swaps position of last two values in stack.
	 * @pre stack is not empty.
	 */
	public void exch() {
		Assert.pre(!stack.isEmpty(),"Empty Stack");
		Token i = stack.pop();
		Token j = stack.pop();
		stack.add(j);
		stack.add(i);
	}
	/**
	 * compares the value of last two elements in stack.
	 * @pre stack is not empty.
	 */
	public void eq() {
		Assert.pre(!stack.isEmpty(),"Empty Stack");
	  	Token i = stack.pop();
		Token j = stack.pop();
		stack.add(new Token(i.equals(j)));
	}
	/**
	 * compares(using the less than operator) the last two
	 * elements in stack.
	 * @pre Stack is not empty.
	 */
	public void lt() {
		Assert.pre(!stack.isEmpty(),"Empty Stack");
		double i = stack.pop().getNumber();
		double j = stack.pop().getNumber();
		stack.add(new Token(i < j));
	}
	/**
	 * compares(using a not equals logic) last two 
	 * elements in stack.
	 * @pre Stack is not empty. 
	 */
	public void ne() {
		Assert.pre(!stack.isEmpty(),"Empty Stack");
		Token i = stack.pop();
		Token j = stack.pop();
		stack.add(new Token(!i.equals(j)));
	}
	/**
	 * duplicates the last element in stack. 
	 * @pre stack must not be empty. 
	 */
	public void dup() {
		Assert.pre(!stack.isEmpty(),"Empty Stack");
		Token i = stack.peek();
		stack.push(i);
	}
	/**
	 * pops out last element from stack. 
	 * @pre Stack is not empty. 
	 */
	public void pop() {
		Assert.pre(!stack.isEmpty(),"Empty Stack");
		stack.pop();
	}
	/**
	 * adds a symbol and its corresponding value to a
	 * symbol table.
	 * @pre stack is not empty.
	 */
	public void def() {
		Assert.pre(!stack.isEmpty(),"Empty Stack");
		Token j = stack.pop();
		String symbol = stack.pop().getSymbol();
		table.add(symbol, j);
	}
	/**
	 * prints table
	 */
	public void ptable() {
		System.out.println(table.toString());
	}
	/**
	 * reads in reader.
	 * @param r reader to be interpreted.
	 */
	public void interpret(Reader r) {
		while(r.hasNext()) {
			Token n = r.next();
			if (n.isBoolean() || n.isProcedure() || n.isNumber()) {
				stack.add(n);
			}
			if (n.isSymbol()) {
				String input = n.getSymbol();
				if (input.equals("quit")) {
					break;
				}
				if (n.getSymbol().startsWith("/")) {
					String symbol = input.substring(1, input.length());
					stack.add(new Token(symbol));
				}
				if(interpret(input)) {
					continue;
				}
				if(table.contains(input)) {
					if (table.get(input).isProcedure()) {
						interpret (new Reader(table.get(input)));
					}
					if (table.get(input).isBoolean() || table.get(input).isNumber()) {
						stack.push(table.get(input));
					}
				}
			}

		}
	}
	/**
	 * determines course of action based on symbol type.
	 * @param symbol symbol that will determine course of action.
	 * @return boolean that validates if the symbol in effect.
	 */
	public boolean interpret(String symbol) {
		switch(symbol) {
			case "add": 
				add();
			  break;
			case "sub":
			   sub();
			  break;
			case "mul":
			   mul();
			  break;
			case "div":
			  	div();
			  break;
			case "exch":
				exch();
			  break;
			case "eq":
			  	eq();
			  break;
			case "lt":
			   	lt();
			  break;
			case "dup":
			   	dup();
			  break;
			case "def":
				def();
			  break;
			case "pop":
				pop();
			  break;
			case "ptable":
				ptable();
			  break;
			case "ne":
			  ne();
			  break;
			case "pstack":
				pstack();
			  break;
			default:
			  return false;
	}
	return true;
}
	public static void main(String[] args) {
		Interpreter resource = new Interpreter();
		resource.interpret(new Reader());
	}
}
