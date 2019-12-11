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
import java.util.List;
import lombok.Getter;
import org.schorn.ella.node.ActiveNode;

/**
 *
 * @author bschorn
 */
public class FieldType {

    @Getter
    String name;

    @Getter
    String data_type;

    @Getter
    Constraints constraints;

    public FieldType(ActiveNode.ValueType.FieldType fieldType) {
        this.name = fieldType.name();
        this.data_type = fieldType.dataType().name();
        this.constraints = new Constraints();
        /*
        hack alert
         */
        // what are the type of constraints for the underlying dataType?
        for (ActiveNode.Constraints.ConstraintType constraintType : fieldType.dataType().constraintTypes()) {
            // was this type used?
            ActiveNode.Constraints.ConstraintData constraintData = fieldType.constraints().constraint(constraintType);
            if (constraintData != null) {
                // collect data values into list (object since we don't know what types to expect)
                List<Object> constraintValues = new ArrayList<>();
                for (Object constraintValue : constraintData.constraintValues()) {
                    // null, skip
                    if (constraintValue == null) {
                        continue;
                    }
                    if (constraintValue instanceof ActiveNode.ValueData) {
                        Object value = ((ActiveNode.ValueData) constraintValue).activeValue();
                        if (value != null) {
                            if (value instanceof Number) {
                                constraintValues.add((Number) value);
                            } else {
                                constraintValues.add(value.toString());
                            }
                        }
                    } else if (constraintValue instanceof Number) {
                        // numbers need to stay as numbers
                        constraintValues.add((Number) constraintValue);
                    } else {
                        constraintValues.add(constraintValue.toString());
                    }
                }
                if (constraintValues.size() == 1) {
                    // if there is only one entry, treat as name:value pair
                    this.constraints.put(constraintType.name(), constraintValues.get(0));
                } else if (constraintValues.size() > 1) {
                    // if this is more than one, treat as an name:array
                    this.constraints.put(constraintType.name(), constraintValues);
                }
            }
        }
    }

}
