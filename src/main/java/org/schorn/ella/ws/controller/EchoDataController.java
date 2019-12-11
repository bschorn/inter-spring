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

import org.schorn.ella.ws.facade.EchoMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author bschorn
 */
@RestController
@RequestMapping("/echo")
public class EchoDataController {

    //WebService api = WebService.INSTANCE;

    @GetMapping(path = "/{message}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public EchoMessage getMessage(@PathVariable String message) throws Exception {
        //WebService.Request request = api.createRequest(EchoMessage.class, WebService.CategoryAction.ECHO_REQUEST, WebService.Method.GET, WebService.ContentType.JSON);
        //request.set(WebService.Parameter.CONTEXT, "Common");
        //request.set(WebService.Parameter.MESSAGE, message);
        //return (EchoMessage) api.serviceRequest(request);
        return new EchoMessage(message);
    }

}
