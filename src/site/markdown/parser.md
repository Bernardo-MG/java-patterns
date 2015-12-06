# Parser

A parser receives a data structure, and transforms it into another equivalent data structure.
	
## Interface

![Parser interface][parser-interface]

The [Parser][parser] only contains one method, _parse_, which takes and input object and generates and output structure.
	
The actual way this is achieved will depend on each implementation.
	
It should be noted that if the parsing process is complex, it may be possible, and desirable, to divide it into several chained parsers, each of them receiving as input the output of the previous parser.


[parser]: ./apidocs/com/wandrell/pattern/parser/Parser.html
[parser-interface]: ./images/parser_class.png