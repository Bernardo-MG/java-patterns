/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2014 the original author or authors.
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.wandrell.pattern.prototype;

/**
 * Interface for the prototype pattern.
 * <p>
 * This is a substitute for the {@code Cloneable} interface, to avoid the
 * problems and unneeded complications which usually come from it.
 * <p>
 * Every class inheriting this interface should implement it, even if it's
 * parent already does, and even if it is an abstract class.
 * <p>
 * This way the {@code createNewInstance} method always returns an instance of
 * the current class.
 * <p>
 * The easiest way to implement the interface is just using a copy constructor
 * to generate a copy of the object on the {@code createNewInstance} method.
 * <p>
 * The returned class should keep as few dependencies shared with the originator
 * as possible. For example, if the original contains a collection, the created
 * instance should have a copy of that collection, but not the same collection.
 * 
 * @author Bernardo Martínez Garrido
 */
public interface NewInstantiable {

    /**
     * Creates a copy of the object.
     * <p>
     * This method serves to implement the prototype pattern, creating a copy of
     * the original as exact as possible.
     * <p>
     * The returned copy will be as detached as possible from the originator's
     * dependencies.
     * 
     * @return a copy of the object
     */
    public NewInstantiable createNewInstance();

}
