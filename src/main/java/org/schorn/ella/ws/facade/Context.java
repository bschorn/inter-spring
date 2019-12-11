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

import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import org.schorn.ella.context.AppContext;
import org.schorn.ella.node.ActiveNode.Role;

/**
 *
 * @author bschorn
 */
public class Context {

    @Getter
    String context;
    @Getter
    List<FieldType> field_types;
    @Getter
    List<ValueType> value_types;
    @Getter
    List<ArrayType> array_types;
    @Getter
    List<ObjectType> object_types;

    public Context(AppContext context) {
        this.context = context.name();
        this.field_types = context.fieldTypes().stream()
                .map(ft -> new FieldType(ft))
                .collect(Collectors.toList());
        this.value_types = context.valueTypes().stream()
                .map(vt -> new ValueType(vt))
                .collect(Collectors.toList());
        this.array_types = context.arrayTypes().stream()
                .filter(at -> at.memberDef().activeType().role() == Role.Value)
                .map(at -> new ArrayType(at))
                .collect(Collectors.toList());
        this.object_types = context.objectTypes().stream()
                .map(ot -> new ObjectType(ot))
                .collect(Collectors.toList());
    }
}
