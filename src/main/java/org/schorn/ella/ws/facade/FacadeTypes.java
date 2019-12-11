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

/**
 *
 * @author bschorn
 */
public enum FacadeTypes {
    EchoMessage(EchoMessage.class),
    ActiveData(ActiveData.class),
    ActiveType(ActiveType.class),
    ArrayData(ArrayData.class),
    ArrayType(ArrayType.class),
    ArrayTypes(ArrayTypes.class),
    Constraint(ConstraintData.class),
    Context(Context.class),
    Contexts(Contexts.class),
    DataType(DataType.class),
    FieldType(FieldType.class),
    FieldTypes(FieldTypes.class),
    ObjectData(ObjectData.class),
    ObjectType(ObjectType.class),
    ObjectTypes(ObjectTypes.class),
    TableType(TableType.class),
    PrimitiveData(PrimitiveData.class),
    PrimitiveType(PrimitiveType.class),
    ValueData(ValueData.class),
    ValueType(ValueType.class),
    ValueTypes(ValueTypes.class);

    Class<?> classFor;

    FacadeTypes(Class<?> classFor) {
        this.classFor = classFor;
    }

    public Class<?> targetClass() {
        return this.classFor;
    }

    static public FacadeTypes parse(Class<?> classFor) {
        for (FacadeTypes facadeType : FacadeTypes.values()) {
            if (facadeType.classFor.equals(classFor)) {
                return facadeType;
            }
        }
        return null;
    }
}
