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

/**
 *
 * @author bschorn
 */
public enum WSAction implements WSParameter {
    TYPE,
    LIST,
    QUERY,
    CREATE,
    REPLACE,
    UPDATE,
    DELETE,
    PAGE,
    UPLOAD,
    DOWNLOAD;

    int id;

    WSAction() {
        this.id = this.typeId(this.ordinal());
    }

    @Override
    public int id() {
        return this.id;
    }

    @Override
    public Object value() {
        return this;
    }

    @Override
    public String toString() {
        return String.format("%s.%s",
                this.getClass().getSimpleName(),
                this.name());
    }

    static public WSAction parse(Object value) throws Exception {
        for (WSAction action : WSAction.values()) {
            if (action.name().equalsIgnoreCase(value.toString())) {
                return action;
            }
        }
        throw new Exception(String.format("No Action of '%s' found.", value.toString()));
    }
}
