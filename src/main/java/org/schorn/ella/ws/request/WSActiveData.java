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

import org.schorn.ella.node.ActiveNode;

/**
 *
 * @author bschorn
 */
public enum WSActiveData implements WSParameter {
    VALUE(ActiveNode.ValueType.class),
    OBJECT(ActiveNode.ObjectType.class),
    ARRAY(ActiveNode.ArrayType.class);

    int id;
    Class<? extends ActiveNode.ActiveType> value;

    WSActiveData(Class<? extends ActiveNode.ActiveType> activeTypeClass) {
        this.value = activeTypeClass;
        this.id = this.typeId(this.ordinal());
    }

    @Override
    public int id() {
        return this.id;
    }

    @Override
    public Object value() {
        return this.value;
    }
    static public WSActiveData parse(Object value) throws Exception {
        for (WSActiveData activeData : WSActiveData.values()) {
            if (activeData.name().equalsIgnoreCase(value.toString())) {
                return activeData;
            }
        }
        throw new Exception(String.format("No Action of '%s' found.", value.toString()));
    }

}
