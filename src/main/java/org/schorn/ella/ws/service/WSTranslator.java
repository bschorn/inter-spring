/*
 * The MIT License
 *
 * Copyright 2019 bschorn.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.schorn.ella.ws.service;

import java.util.function.Function;

/**
 * Translates internals into an externally acceptable object of type R
 *
 * @author bschorn
 * @param <T>
 * @param <R>
 */
public class WSTranslator<T, R> implements Function<T, R> {

    private final Class<T> sourceClass;
    private final Class<R> targetClass;
    private final Function<T, R> function;

    public WSTranslator(Class<T> sourceClass, Class<R> targetClass, Function<T, R> function) {
        this.sourceClass = sourceClass;
        this.targetClass = targetClass;
        this.function = function;
    }

    public Class<T> sourceClass() {
        return this.sourceClass;
    }

    public Class<R> targetClass() {
        return this.targetClass;
    }

    @Override
    public R apply(T t) {
        return this.function.apply(t);
    }
}
