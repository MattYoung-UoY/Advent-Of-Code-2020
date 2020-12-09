package main.lex;

import java_cup.runtime.Symbol;
import main.parse.sym;

%%

%class Lexer
%public
%unicode
%cup
%char

%eofval{
	 return tok(sym.EOF, null);
%eofval} 

%{
private Symbol tok(int kind, Object value) {
    return new Symbol(kind, yyline, yycolumn, value);
}
%}  

Num = [0-9]+

%%
"\n" {}
"bags" {}
"bags contain" {return tok(sym.ASSIGN, null);}
"no other bags" {return tok(sym.END_BAG, null);}
"bag" {}
[a-z]+ " " [a-z]+ {return tok(sym.BAG, yytext());}
" " {}
{Num} {return tok(sym.NUM, yytext());}
"," {return tok(sym.LST_COMP, null);}
"." {return tok(sym.STM_END, null);}
[^] {return tok(sym.error, null);}