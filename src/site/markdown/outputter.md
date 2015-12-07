# Outputter

An outputter sends data through an output I/O operation, hiding the actual implementation or API being used.
	
## Interface

![Outputter class hierarchy tree][outputter-interface]

The [Outputter][outputter] consists of just two methods, both called _output_, which send an object through the accompanying output object, which may be a _Writer_ or an _OutputStream_.
	
Note that the output object is expected to be closed once the operation is finished.


[outputter]: ./apidocs/com/wandrell/pattern/outputter/Outputter.html
[outputter-interface]: ./images/outputter_class.png