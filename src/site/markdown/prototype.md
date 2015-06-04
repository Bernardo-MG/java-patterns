# Prototype

A prototype allows creating copies of itself through a method, instead of depending on constructors or factories.
	
It should be noted that Java offers it's own interface for this pattern, the _cloneable_ interface, but it tends to give more problems than those it solves.
	
## Interface

![Prototype interface](./images/prototype_class_tree.png)

The [Prototype][prototype] interface is very simple, it just offers a single method named _createNewInstance_ which returns a copy of the prototype.
	
The easiest way to implement this is just through a copy constructor, which will be called by the method.
	
```
public class TheClass implements Prototype {

	private String text = "";
	
	public TheClass(){
		super()
	}
	
	public TheClass(final TheClass theClass){
		super()
		
		text = theClass.text;
	}

	@Override
	public void createNewInstance() {
		return new TheClass(this);
	}
	
}
```

[prototype]: ./apidocs/com/wandrell/pattern/prototype/Prototype.html