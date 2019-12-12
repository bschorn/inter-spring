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
package org.schorn.ella.ws.admin;

import java.util.function.Function;
import org.schorn.ella.context.AppContext;
import org.schorn.ella.ws.meta.*;
import org.schorn.ella.ws.request.WSAction;
import org.schorn.ella.ws.request.WSActiveType;
import org.schorn.ella.ws.request.WSDivision;
import org.schorn.ella.ws.request.WSRequest;
import org.schorn.ella.ws.request.WSResponse;
import org.schorn.ella.ws.service.WSHandler;


/**
 *
 * @author bschorn
 */
public enum AdminHandlers {
    Context(WSAction.PAGE, WSActiveType.CONTEXT, MetaTypeMap.CONTEXT, AppContext.class);

    WSHandler handler;

    AdminHandlers(WSAction action, WSActiveType activeType, Function<WSRequest, WSResponse> function, Class<?> classOfT) {
        this.handler = new WSHandler(WSDivision.META.id() + action.id() + activeType.id(), function, classOfT);
    }

    public WSHandler handler() {
        return this.handler;
    }

    static public WSHandler[] handlers() {
        int i = 0;
        WSHandler[] handlers = new WSHandler[AdminHandlers.values().length];
        for (AdminHandlers metaHandler : AdminHandlers.values()) {
            handlers[i++] = metaHandler.handler();
        }
        return handlers;
    }
}
