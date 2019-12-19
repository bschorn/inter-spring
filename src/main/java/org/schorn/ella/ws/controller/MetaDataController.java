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
package org.schorn.ella.ws.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;
import org.schorn.ella.services.ActiveServices;
import org.schorn.ella.ws.facade.ArrayType;
import org.schorn.ella.ws.facade.ArrayTypes;
import org.schorn.ella.ws.facade.Context;
import org.schorn.ella.ws.facade.Contexts;
import org.schorn.ella.ws.facade.ExceptionMessage;
import org.schorn.ella.ws.facade.FieldType;
import org.schorn.ella.ws.facade.FieldTypes;
import org.schorn.ella.ws.facade.ObjectTypeMembers;
import org.schorn.ella.ws.facade.ObjectTypes;
import org.schorn.ella.ws.facade.ValueType;
import org.schorn.ella.ws.facade.ValueTypes;
import org.schorn.ella.ws.meta.MetaHandlers;
import org.schorn.ella.ws.meta.MetaTranslators;
import org.schorn.ella.ws.request.WSAction;
import org.schorn.ella.ws.request.WSActiveType;
import org.schorn.ella.ws.request.WSContentType;
import org.schorn.ella.ws.request.WSDivision;
import org.schorn.ella.ws.request.WSRequest;
import org.schorn.ella.ws.service.WSService;
import org.schorn.ella.ws.util.Functions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *
 *
 *
 * @author bschorn
 */
@RestController
@RequestMapping("/meta")
@Api(value = "Meta Data API")
public class MetaDataController {

    static final Logger LGR = LoggerFactory.getLogger(MetaDataController.class);

    @Autowired
    WSService service;

    @ExceptionHandler(Exception.class)
    public ExceptionMessage handleException(HttpServletRequest request, Exception exception) {
        LGR.error("{}.handleException() - EndPoint: {} Exception: {}",
                this.getClass().getSimpleName(),
                request.getRequestURL().toString(),
                Functions.getStackTraceAsString(exception));
        return new ExceptionMessage(exception, request.getRequestURL().toString());
    }
    @PostConstruct
    private void init() {
        this.service.addTranslators(MetaTranslators.translators());
        this.service.addHandlers(MetaHandlers.handlers());
    }

    /*
    @GetMapping("/")
    public void metaPage() {

    }

    @GetMapping("/{action}")
    public void metaActionPage(@PathVariable String action) {

    }
    */
    @ApiOperation(value = "Get the meta data for the context name", response = Context.class)
    @GetMapping("/type/context/{context_name}")
    public Context getContext(@PathVariable String context_name) throws Exception {
        WSRequest request = WSRequest.create(WSDivision.META, WSAction.TYPE,
                WSActiveType.CONTEXT, WSContentType.JSON);
        request.set(WSActiveType.CONTEXT, context_name);
        return service.execute(request, Context.class);
    }

    @GetMapping("/list/context/*")
    public Contexts getContexts() throws Exception {
        WSRequest request = WSRequest.create(WSDivision.META, WSAction.LIST,
                WSActiveType.CONTEXT, WSContentType.JSON);
        return service.execute(request, Contexts.class);
    }

    @GetMapping("/type/object_type/{context_name}/{object_type_name}")
    public ObjectTypeMembers getObjectType(@PathVariable String context_name, @PathVariable String object_type_name) throws Exception {
        WSRequest request = WSRequest.create(WSDivision.META, WSAction.TYPE,
                WSActiveType.OBJECT, WSContentType.JSON);
        request.set(WSActiveType.CONTEXT, context_name);
        request.set(WSActiveType.OBJECT, object_type_name);
        return service.execute(request, ObjectTypeMembers.class);
    }

    @GetMapping("/html/object_type/{context_name}/{object_type_name}")
    public Response getObjectTypeHtml(@PathVariable String context_name, @PathVariable String object_type_name) throws Exception {
        String html = ActiveServices.contentTypeOutput().getHTMLForm(context_name, object_type_name);
        return Response.ok(html).build();
    }

    @GetMapping("/list/object_type/{context_name}/{object_type_name}")
    public ObjectTypes getObjectTypes(@PathVariable String context_name, @PathVariable String object_type_name) throws Exception {
        WSRequest request = WSRequest.create(WSDivision.META, WSAction.LIST,
                WSActiveType.OBJECT, WSContentType.JSON);
        request.set(WSActiveType.CONTEXT, context_name);
        request.set(WSActiveType.OBJECT, object_type_name);
        return service.execute(request, ObjectTypes.class);
    }

    @GetMapping("/type/array_type/{context_name}/{array_type_name}")
    public ArrayType getArrayType(@PathVariable String context_name, @PathVariable String array_type_name) throws Exception {
        WSRequest request = WSRequest.create(WSDivision.META, WSAction.TYPE,
                WSActiveType.ARRAY, WSContentType.JSON);
        request.set(WSActiveType.CONTEXT, context_name);
        request.set(WSActiveType.ARRAY, array_type_name);
        return service.execute(request, ArrayType.class);
    }

    @GetMapping("/list/array_type/{context_name}/{array_type_name}")
    public ArrayTypes getArrayTypes(@PathVariable String context_name, @PathVariable String array_type_name) throws Exception {
        WSRequest request = WSRequest.create(WSDivision.META, WSAction.LIST,
                WSActiveType.ARRAY, WSContentType.JSON);
        request.set(WSActiveType.CONTEXT, context_name);
        request.set(WSActiveType.ARRAY, array_type_name);
        return service.execute(request, ArrayTypes.class);
    }

    @GetMapping("/type/value_type/{context_name}/{value_type_name}")
    public ValueType getValueType(@PathVariable String context_name, @PathVariable String value_type_name) throws Exception {
        WSRequest request = WSRequest.create(WSDivision.META, WSAction.TYPE,
                WSActiveType.VALUE, WSContentType.JSON);
        request.set(WSActiveType.CONTEXT, context_name);
        request.set(WSActiveType.VALUE, value_type_name);
        return service.execute(request, ValueType.class);
    }

    @GetMapping("/list/value_type/{context_name}/{value_type_name}")
    public ValueTypes getValueTypes(@PathVariable String context_name, @PathVariable String value_type_name) throws Exception {
        WSRequest request = WSRequest.create(WSDivision.META, WSAction.LIST,
                WSActiveType.VALUE, WSContentType.JSON);
        request.set(WSActiveType.CONTEXT, context_name);
        request.set(WSActiveType.VALUE, value_type_name);
        return service.execute(request, ValueTypes.class);
    }

    @GetMapping("/type/field_type/{context_name}/{field_type_name}")
    public FieldType getFieldType(@PathVariable String context_name, @PathVariable String field_type_name) throws Exception {
        WSRequest request = WSRequest.create(WSDivision.META, WSAction.TYPE,
                WSActiveType.FIELD, WSContentType.JSON);
        request.set(WSActiveType.CONTEXT, context_name);
        request.set(WSActiveType.FIELD, field_type_name);
        return service.execute(request, FieldType.class);
    }

    @GetMapping("/list/field_type/{context_name}/{field_type_name}")
    public FieldTypes getFieldTypes(@PathVariable String context_name, @PathVariable String field_type_name) throws Exception {
        WSRequest request = WSRequest.create(WSDivision.META, WSAction.LIST,
                WSActiveType.FIELD, WSContentType.JSON);
        request.set(WSActiveType.CONTEXT, context_name);
        request.set(WSActiveType.FIELD, field_type_name);
        return service.execute(request, FieldTypes.class);
    }

    /*
    @GetMapping(path = "/Context/{context_name}/TableType/{object_type_name}.csv", produces = "text/csv; charset=utf-8")
    public void getTableType(@PathVariable String context_name, @PathVariable String object_type_name, HttpServletResponse response) throws Exception {
        WebService.Request request = appService.createRequest(TableType.class, WebService.CategoryAction.META_TYPE, WebService.Method.GET, WebService.ContentType.CSV);
        request.set(WebService.Parameter.CONTEXT, context_name);
        request.set(WebService.Parameter.OBJECT_TYPE, object_type_name);
        response.setHeader("Cache-Control", "must-revalidate");
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s.csv\"", object_type_name));
        response.setStatus(HttpServletResponse.SC_OK);
        TableType tableType = (TableType) appService.serviceRequest(request);
        IOUtils.copy(new ByteArrayInputStream(tableType.toString().getBytes(Charset.forName("UTF-8"))), response.getOutputStream());
        response.getOutputStream().close();
    }
*/
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addValueType(@RequestBody ValueType valueType) {
        // ...
    }

}
