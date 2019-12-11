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
import org.schorn.ella.ws.request.WSRequest;

/**
 * Access internals to satisfy an external request.
 *
 * @author bschorn
 * @param <T>
 */
public class WSHandler<T> implements Function<WSRequest, T> {

    private final int requestTypeId;
    private final Function<WSRequest, T> handler;
    private final Class<T> classOf;

    public WSHandler(int requestTypeId, Function<WSRequest, T> handler, Class<T> classOfT) {
        this.requestTypeId = requestTypeId;
        this.handler = handler;
        this.classOf = classOfT;
    }

    @Override
    public T apply(WSRequest request) {
        return this.handler.apply(request);
    }

    public int requestTypeId() {
        return this.requestTypeId;
    }

    public Class<T> classOf() {
        return this.classOf;
    }

    @Override
    public String toString() {
        return String.format("[%d] - %s",
                this.requestTypeId,
                this.classOf.getName());
    }
}
