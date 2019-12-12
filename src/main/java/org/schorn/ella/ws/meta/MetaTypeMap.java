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
import org.schorn.ella.ws.request.WSResponse;


/**
 *
 * @author bschorn
 */
public interface MetaTypeMap {

    static public final Function<WSRequest, WSResponse> CONTEXT = r -> {
        String context_str = (String) r.get(WSActiveType.CONTEXT);
        if (context_str != null) {
            Optional<AppContext> optContext = AppContext.valueOf(context_str);
            if (optContext.isPresent()) {
                return new WSResponse(optContext.get());
            } else {
                return new WSResponse(new Exception(
                        String.format("AppContext: %s was not found. %s: %s",
                                context_str,
                                WSRequest.class.getSimpleName(),
                                r.toString())));
            }
        } else {
            return new WSResponse(new Exception(
                    String.format("AppContext was not provided in request. %s: %s",
                            context_str,
                            WSRequest.class.getSimpleName(),
                            r.toString())));
        }
    };

    static public final Function<WSRequest, WSResponse> ARRAY = r -> {
        String context_str = (String) r.get(WSActiveType.CONTEXT);
        String array_type = (String) r.get(WSActiveType.ARRAY);
        if (context_str != null && array_type != null) {
            Optional<AppContext> optContext = AppContext.valueOf(context_str);
            if (optContext.isPresent()) {
                AppContext context = optContext.get();
                ActiveNode.ArrayType arrayType = ActiveNode.ArrayType.get(context, array_type);
                if (arrayType != null) {
                    return new WSResponse(arrayType);
                } else {
                    return new WSResponse(new Exception(
                            String.format("ArrayType: %s.%s was not found. %s: %s",
                                    context_str,
                                    array_type,
                                    WSRequest.class.getSimpleName(),
                                    r.toString())));
                }
            } else {
                return new WSResponse(new Exception(
                        String.format("AppContext: %s was not found. %s: %s",
                                context_str,
                                WSRequest.class.getSimpleName(),
                                r.toString())));
            }
        } else {
            return new WSResponse(new Exception(
                    String.format("AppContext was not provided in request. %s: %s",
                            context_str,
                            WSRequest.class.getSimpleName(),
                            r.toString())));
        }
    };

    static public final Function<WSRequest, WSResponse> OBJECT = r -> {
        String context_str = (String) r.get(WSActiveType.CONTEXT);
        String object_type = (String) r.get(WSActiveType.OBJECT);
        if (context_str != null && object_type != null) {
            Optional<AppContext> optContext = AppContext.valueOf(context_str);
            if (optContext.isPresent()) {
                AppContext context = optContext.get();
                ActiveNode.ObjectType objectType = ActiveNode.ObjectType.get(context, object_type);
                if (objectType != null) {
                    return new WSResponse(objectType);
                } else {
                    return new WSResponse(new Exception(
                            String.format("ObjectType: %s.%s was not found. %s: %s",
                                    context_str,
                                    object_type,
                                    WSRequest.class.getSimpleName(),
                                    r.toString())));
                }
            } else {
                return new WSResponse(new Exception(
                        String.format("AppContext: %s was not found. %s: %s",
                                context_str,
                                WSRequest.class.getSimpleName(),
                                r.toString())));
            }
        } else {
            return new WSResponse(new Exception(
                    String.format("AppContext was not provided in request. %s: %s",
                            context_str,
                            WSRequest.class.getSimpleName(),
                            r.toString())));
        }
    };

    static public final Function<WSRequest, WSResponse> VALUE = r -> {
        String context_str = (String) r.get(WSActiveType.CONTEXT);
        String value_type = (String) r.get(WSActiveType.VALUE);
        if (context_str != null && value_type != null) {
            Optional<AppContext> optContext = AppContext.valueOf(context_str);
            if (optContext.isPresent()) {
                AppContext context = optContext.get();
                ActiveNode.ValueType valueType = ActiveNode.ValueType.get(context, value_type);
                if (valueType != null) {
                    return new WSResponse(valueType);
                } else {
                    return new WSResponse(new Exception(
                            String.format("ValueType: %s.%s was not found. %s: %s",
                                    context_str,
                                    value_type,
                                    WSRequest.class.getSimpleName(),
                                    r.toString())));
                }
            } else {
                return new WSResponse(new Exception(
                        String.format("AppContext: %s was not found. %s: %s",
                                context_str,
                                WSRequest.class.getSimpleName(),
                                r.toString())));
            }
        } else {
            return new WSResponse(new Exception(
                    String.format("AppContext was not provided in request. %s: %s",
                            context_str,
                            WSRequest.class.getSimpleName(),
                            r.toString())));
        }
    };

    static public final Function<WSRequest, WSResponse> FIELD = r -> {
        String context_str = (String) r.get(WSActiveType.CONTEXT);
        String field_type = (String) r.get(WSActiveType.FIELD);
        if (context_str != null && field_type != null) {
            Optional<AppContext> optContext = AppContext.valueOf(context_str);
            if (optContext.isPresent()) {
                AppContext context = optContext.get();
                //return new Response(ActiveNode.ValueType.FieldType.get(context, field_type));
                ActiveNode.ValueType.FieldType fieldType = ActiveNode.ValueType.FieldType.get(context, field_type);
                if (fieldType != null) {
                    return new WSResponse(fieldType);
                } else {
                    return new WSResponse(new Exception(
                            String.format("FieldType: %s.%s was not found. %s: %s",
                                    context_str,
                                    field_type,
                                    WSRequest.class.getSimpleName(),
                                    r.toString())));
                }
            } else {
                return new WSResponse(new Exception(
                        String.format("AppContext: %s was not found. %s: %s",
                                context_str,
                                WSRequest.class.getSimpleName(),
                                r.toString())));
            }
        } else {
            return new WSResponse(new Exception(
                    String.format("AppContext was not provided in request. %s: %s",
                            context_str,
                            WSRequest.class.getSimpleName(),
                            r.toString())));
        }
    };

}
