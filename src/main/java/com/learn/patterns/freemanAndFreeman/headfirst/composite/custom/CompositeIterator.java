package com.learn.patterns.freemanAndFreeman.headfirst.composite.custom;


import java.util.Iterator;
import java.util.Stack;

public class CompositeIterator implements Iterator {
	Stack stack = new Stack();
   
	public CompositeIterator(Iterator iterator) {
		stack.push(iterator);
	}
   
	public Object next() {
		if (hasNext()) {
			Iterator iterator = (Iterator) stack.peek();
			FileUnit component = (FileUnit) iterator.next();
			if (component instanceof Directory) {
				stack.push(component.createIterator());
			} 
			return component;
		} else {
			return null;
		}
	}
  
	public boolean hasNext() {
		if (stack.empty()) {
			return false;
		} else {
			Iterator iterator = (Iterator) stack.peek();
         if(iterator != null){
            if (!iterator.hasNext()) {
               stack.pop();
               return hasNext();
            } else {
               return true;
            }
         }

		}
      return false;
	}
   
	public void remove() {
		throw new UnsupportedOperationException();
	}
}


