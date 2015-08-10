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
/**
 * Provides interfaces and basic implementations for the prototype pattern.
 * <p>
 * The prototype is a creational pattern which allows creating a copy of an
 * existing class. With this pattern new instances of a class may be created
 * using a defined method, instead of through constructors.
 * <p>
 * This is not a simple pattern to implement, attention should be paid to things
 * such as dependencies, or otherwise they may not work correctly after creating
 * the new instance.
 * <p>
 * Java already has it's own interface for the prototype pattern, the
 * {@link Cloneable} interface. But trying to use it usually gives more problems
 * than those it solves, and so appears the need to create an alternative.
 * <h2>Interfaces</h2>
 * <p>
 * The {@link com.wandrell.pattern.prototype.Prototype Prototype} offers the
 * {@link com.wandrell.pattern.prototype.Prototype#createNewInstance
 * createNewInstance} method as a way to create new instances from a prototype.
 */
package com.wandrell.pattern.prototype;
