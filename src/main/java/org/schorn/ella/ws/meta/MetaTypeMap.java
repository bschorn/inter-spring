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
package org.schorn.ella.ws.meta;

import java.util.Optional;
import java.util.function.Function;
import org.schorn.ella.context.AppContext;
import org.schorn.ella.node.ActiveNode;
import org.schorn.ella.ws.request.WSActiveType;
import org.schorn.ella.ws.request.WSRequest;


/**
 *
 * @author bschorn
 */
public interface MetaTypeMap {

    static public final Function<WSRequest, AppContext> CONTEXT = r -> {
        String context_str = (String) r.get(WSActiveType.CONTEXT);
        if (context_str != null) {
            Optional<AppContext> optContext = AppContext.valueOf(context_str);
            if (optContext.isPresent()) {
                return optContext.get();
            }
        }
        return null;
    };

    static public final Function<WSRequest, ActiveNode.ArrayType> ARRAY = r -> {
        String context_str = (String) r.get(WSActiveType.CONTEXT);
        String array_type = (String) r.get(WSActiveType.ARRAY);
        if (context_str != null && array_type != null) {
            Optional<AppContext> optContext = AppContext.valueOf(context_str);
            if (optContext.isPresent()) {
                AppContext context = optContext.get();
                return ActiveNode.ArrayType.get(context, array_type);
            }
        }
        return null;
    };

    static public final Function<WSRequest, ActiveNode.ObjectType> OBJECT = r -> {
        String context_str = (String) r.get(WSActiveType.CONTEXT);
        String object_type = (String) r.get(WSActiveType.OBJECT);
        if (context_str != null && object_type != null) {
            Optional<AppContext> optContext = AppContext.valueOf(context_str);
            if (optContext.isPresent()) {
                AppContext context = optContext.get();
                return ActiveNode.ObjectType.get(context, object_type);
            }
        }
        return null;
    };

    static public final Function<WSRequest, ActiveNode.ValueType> VALUE = r -> {
        String context_str = (String) r.get(WSActiveType.CONTEXT);
        String value_type = (String) r.get(WSActiveType.VALUE);
        if (context_str != null && value_type != null) {
            Optional<AppContext> optContext = AppContext.valueOf(context_str);
            if (optContext.isPresent()) {
                AppContext context = optContext.get();
                return ActiveNode.ValueType.get(context, value_type);
            }
        }
        return null;
    };

    static public final Function<WSRequest, ActiveNode.ValueType.FieldType> FIELD = r -> {
        String context_str = (String) r.get(WSActiveType.CONTEXT);
        String field_type = (String) r.get(WSActiveType.FIELD);
        if (context_str != null && field_type != null) {
            Optional<AppContext> optContext = AppContext.valueOf(context_str);
            if (optContext.isPresent()) {
                AppContext context = optContext.get();
                return ActiveNode.ValueType.FieldType.get(context, field_type);
            }
        }
        return null;
    };

}
