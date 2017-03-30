# New Programmming language Parser, Lexer, Semantic Analyzer

This new programming language compiler is developed by using parsing, lexing, and a semantic analyzer to validate the language rules. 

## The rules of the language are as follows:

```<PROGRAM> à '{' <BODY> '}’
<BODY> -> {<PRINT>';'|<ASSIGNMENT>';'|<VARIABLE>';’|<WHILE>|<IF>|<RETURN>';'}
<ASSIGNMENT> -> identifier '=' <EXPRESSION>
<VARIABLE> -> ('int'|'float'|'boolean'|'char’|'string'|'void')identifier
<WHILE> -> 'while' '(' <EXPRESSION> ')' <PROGRAM>
<IF> -> 'if' '(' <EXPRESSION> ')' <PROGRAM> ['else' <PROGRAM>]
<RETURN> -> 'return'
<PRINT> -> ’print’ ‘(‘ <EXPRESSION> ‘)’
<EXPRESSION> -> <X> {'|' <X>}
<X> -> <Y> {'&' <Y>}
<Y> -> ['!'] <R>
<R> -> <E> {('>'|'<'|'=='|'!=') <E>}
<E> -> <A> {(’+'|'-’) <A>}
<A> -> <B> {('*'|'/') <B>}
<B> -> ['-'] <C>
<C> -> integer | octal | hexadecimal | binary | true | false | string | char | float | identifier|'(' <EXPRESSION> ')
 ```



<img width="1432" alt="screen shot 2017-03-30 at 7 27 27 am" src="https://cloud.githubusercontent.com/assets/11728523/24509120/66434134-151a-11e7-96f4-4f2f13e326a0.png">


The input code on the left is validated and tokenized into its respective tokens that make up the language.
