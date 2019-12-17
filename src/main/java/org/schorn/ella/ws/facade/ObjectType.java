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
package org.schorn.ella.ws.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import org.schorn.ella.node.ActiveNode;
/**
 *
 * @author bschorn
 */
public class ObjectType {

    @Getter
    String name;
    @Getter
    Map<String, String> attributes;
    @Getter
    List<String> base_types;

    public ObjectType(ActiveNode.ObjectType objectType) {
        this.name = objectType.name();
        if (!objectType.attributes().isEmpty()) {
            this.attributes = new HashMap<>();
            for (ActiveNode.TypeAttribute typeAttribute : objectType.attributes()) {
                this.attributes.put(typeAttribute.attributeName(), typeAttribute.attributeValue());
            }
        }
        if (!objectType.baseTypes().isEmpty()) {
            this.base_types = new ArrayList<>();
            for (ActiveNode.ObjectType baseType : objectType.baseTypes()) {
                this.base_types.add(baseType.name());
            }
        }
    }

}
