package lexer;

import java.util.Vector;

public class Lexer {
 
  private String text;
  private Vector<Token> tokens; 
  private static final String[] KEYWORD = {"if", "else", "while", "switch", 
    "case", "return", "int", "float", "void", "char", "string", "boolean", 
    "true", "false", "print"};
  //Constants; YOU WILL NEED TO DEFINE MORE CONSTANTS
  private static final int ZERO      =  1;
  private static final int ONE       =  2;
  private static final int B         =  0;
  private static final int OTHER     =  3;
  private static final int DELIMITER =  4;
  private static final int ERROR     =  4;
  private static final int STOP      = -2;
  // states table; THIS IS THE TABLE FOR BINARY NUMBERS; YOU SHOLD COMPLETE IT
  private static final int[][] stateTable = { 
    {ERROR,     1, ERROR, ERROR, STOP}, 
    {    2, ERROR, ERROR, ERROR, STOP}, 
    {ERROR,     3,     3, ERROR, STOP}, 
    {ERROR,     3,     3, ERROR, STOP}, 
    {ERROR, ERROR, ERROR, ERROR, STOP} 
  };
  
  //constructor
  public Lexer(String text) {
    this.text = text;
  }

  //run
  public void run () {
    tokens = new Vector<Token>();
    String line;
    int counterOfLines= 1;
    // split lines
    do {
      int eolAt = text.indexOf(System.lineSeparator());
      if (eolAt >= 0) {
        line = text.substring(0,eolAt); 
        if (text.length()>0) text = text.substring(eolAt+1);  
      } else {
        line = text;
        text = "";
      }
      splitLine (counterOfLines, line);
      counterOfLines++;
    } while ( !text.equals("") );   
  }
  
  //slit line
  private void splitLine(int row, String line) {
    int state = 0;
    int index = 0;
    char currentChar;
    String string="";
    if (line.equals("")) return; 
    //DFA working
    int go; 
    do {   
      currentChar = line.charAt(index);
      go = calculateNextState(state, currentChar);  
      if( go != STOP ) {
        string = string + currentChar;   
        state = go;
      }
      index++;        
    } while (index < line.length() && go != STOP);
    //review final state
    if (state == 3) {
      tokens.add(new Token(string, "BINARY", row));
    } else {
      if (!string.equals(" "))
        tokens.add(new Token(string, "ERROR", row));
    }
    // current char
    if( isDelimiter(currentChar))
      tokens.add(new Token(currentChar+"", "DELIMITER", row));
    else if (isOperator(currentChar) )
      tokens.add(new Token(currentChar+"", "OPERATOR", row));
    // loop
    if (index < line.length()) 
      splitLine(row, line.substring(index));
  }
  
  // calculate state
  private int calculateNextState(int state, char currentChar) {
    if (isSpace(currentChar)  || isDelimiter(currentChar)  || 
      isOperator(currentChar) || isQuotationMark(currentChar) )
      return stateTable[state][DELIMITER];
    else if (currentChar == 'b') 
      return stateTable [state][B];
    else if (currentChar == '0')
      return stateTable [state][ZERO];    
    else if (currentChar == '1')
      return stateTable [state][ONE];
    return stateTable [state][OTHER];
  }
 
  // isDelimiter
  private boolean isDelimiter(char c) {
     char [] delimiters = {':', ';', '}','{', '[',']','(',')',','};
     for (int x=0; x<delimiters.length; x++) {
      if (c == delimiters[x]) return true;      
     }
     return false;
  }
  
  // isOperator
  private boolean isOperator(char o) {
     // == and != should be handled in splitLine
     char [] operators = {'+', '-', '*','/','<','>','=','!','&','|'};
     for (int x=0; x<operators.length; x++) {
      if (o == operators[x]) return true;      
     }
     return false;
  }

  // isQuotationMark
  private boolean isQuotationMark(char o) {
     char [] quote = {'"', '\''};
     for (int x=0; x<quote.length; x++) {
      if (o == quote[x]) return true;      
     }
     return false;
  }

  // isSpace
  private boolean isSpace(char o) {
     return o == ' ';
  }
  
  // getTokens
  public Vector<Token> getTokens() {
    return tokens;
  }
  
}
