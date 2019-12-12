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
package org.schorn.ella.ws.request;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 *
 * @author bschorn
 */
public interface WSRequest {

    static public WSRequest create(WSParameter... parameters) {
        return new Impl(parameters);
    }

    void set(WSParameter parameter, Object value);

    Object get(WSParameter parameter);

    <T extends WSParameter> T get(Class<T> classOfT);

    int getRequestType();

    WSContentType getContentType();

    /**
     * Implementation
     */
    static class Impl implements WSRequest {

        private final WSParameter[] parameters;
        private WSContentType contentType;

        private final Map<WSParameter, Object> map;

        private Impl(WSParameter... parameters) {
            this.map = new HashMap<>();
            this.parameters = parameters;
        }

        @Override
        public String toString() {
            StringJoiner joiner = new StringJoiner(", ", "WSParameters: [", "]");
            for (WSParameter wsParameter : this.parameters) {
                joiner.add(String.format(wsParameter.toString()));
            }
            return String.format("WSRequest(%s) -> %s",
                    this.contentType.name(),
                    joiner.toString());
        }

        @Override
        public void set(WSParameter parameter, Object value) {
            this.map.put(parameter, value);
        }

        @Override
        public Object get(WSParameter parameter) {
            return this.map.get(parameter);
        }

        @Override
        public <T extends WSParameter> T get(Class<T> classOfT) {
            for (WSParameter parameter : this.parameters) {
                if (classOfT.isInstance(parameter)) {
                    return (T) parameter;
                }
            }
            return (T) null;
        }

        @Override
        public int getRequestType() {
            int paramSum = 0;
            for (WSParameter parameter : this.parameters) {
                if (parameter != null) {
                    if (parameter instanceof WSContentType) {
                        this.contentType = (WSContentType) parameter;
                    } else {
                        paramSum += parameter.id();
                    }
                }
            }
            return paramSum;
        }

        @Override
        public WSContentType getContentType() {
            return this.contentType;
        }
    }
}
