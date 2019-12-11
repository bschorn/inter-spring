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

import java.util.List;
import java.util.function.Function;
import org.schorn.ella.context.AppContext;
import org.schorn.ella.node.ActiveNode;
import org.schorn.ella.ws.request.WSAction;
import org.schorn.ella.ws.request.WSActiveType;
import org.schorn.ella.ws.request.WSDivision;
import org.schorn.ella.ws.request.WSRequest;
import org.schorn.ella.ws.service.WSHandler;


/**
 *
 * @author bschorn
 */
public enum MetaHandlers {
    Context(WSAction.TYPE, WSActiveType.CONTEXT, MetaTypeMap.CONTEXT, AppContext.class),
    Contexts(WSAction.LIST, WSActiveType.CONTEXT, MetaListMap.CONTEXT, List.class),
    ArrayType(WSAction.TYPE, WSActiveType.ARRAY, MetaTypeMap.ARRAY, ActiveNode.ArrayType.class),
    ArrayTypes(WSAction.LIST, WSActiveType.ARRAY, MetaListMap.ARRAY, List.class),
    ObjectType(WSAction.TYPE, WSActiveType.OBJECT, MetaTypeMap.OBJECT, ActiveNode.ObjectType.class),
    ObjectTypes(WSAction.LIST, WSActiveType.OBJECT, MetaListMap.OBJECT, List.class),
    ValueType(WSAction.TYPE, WSActiveType.VALUE, MetaTypeMap.VALUE, ActiveNode.ValueType.class),
    ValueTypes(WSAction.LIST, WSActiveType.VALUE, MetaListMap.VALUE, List.class),
    FieldType(WSAction.TYPE, WSActiveType.FIELD, MetaTypeMap.FIELD, ActiveNode.ValueType.FieldType.class),
    FieldTypes(WSAction.LIST, WSActiveType.FIELD, MetaListMap.FIELD, List.class);

    WSHandler<?> handler;

    MetaHandlers(WSAction action, WSActiveType activeType, Function<WSRequest, ?> function, Class<?> classOfT) {
        this.handler = new WSHandler(WSDivision.META.id() + action.id() + activeType.id(), function, classOfT);
    }

    public WSHandler<?> handler() {
        return this.handler;
    }

    static public WSHandler<?>[] handlers() {
        int i = 0;
        WSHandler<?>[] handlers = new WSHandler<?>[MetaHandlers.values().length];
        for (MetaHandlers metaHandler : MetaHandlers.values()) {
            handlers[i++] = metaHandler.handler();
        }
        return handlers;
    }
}
