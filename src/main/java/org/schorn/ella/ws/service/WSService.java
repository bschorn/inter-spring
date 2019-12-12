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
package org.schorn.ella.ws.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.schorn.ella.ws.request.WSRequest;
import org.schorn.ella.ws.request.WSResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 *
 * @author bschorn
 */
@Service
public class WSService {
    static final Logger LGR = LoggerFactory.getLogger(WSService.class);

    private final Map<Integer, WSHandler> handlers = new HashMap<>();
    private final List<WSTranslator> translators = new ArrayList<>();

    /**
     *
     * @param <T>
     * @param request
     * @param classForT
     * @return
     * @throws Exception
     */
    public <T> T execute(WSRequest request, Class<T> classForT) throws Exception {

        //validate(request);

        WSHandler handler = getHandler(request);
        WSResponse response = handler.apply(request);
        if (response == null) {
            throw new Exception(String.format("There was a null response for %s", request.toString()));
        } else {
            response.throwException();
        }
        Class<?> sourceClass = response.get().getClass();
        if (handler.classOf().isInstance(response.get())) {
            sourceClass = handler.classOf();
        }
        WSTranslator translator = getTranslator(sourceClass, classForT);
        return (T) translator.apply(response.get());
    }

    public void addHandlers(WSHandler... handlers) {
        for (WSHandler handler : handlers) {
            LGR.info("{}.addHandlers() - adding handler: {}",
                    this.getClass().getSimpleName(),
                    handler.toString());
            this.handlers.put(handler.requestTypeId(), handler);
        }
    }

    public void addTranslators(WSTranslator[] translators) {
        this.translators.addAll(Arrays.asList(translators));
    }

    public WSHandler getHandler(WSRequest request) throws Exception {
        WSHandler handler = this.handlers.get(request.getRequestType());
        if (handler != null) {
            return handler;
        }
        throw new Exception(String.format("%s: there is no handler for request: %s",
                this.getClass().getSimpleName(), request.toString()));
    }

    public WSTranslator<?, ?> getTranslator(Class<?> sourceClass, Class<?> targetClass) throws Exception {
        Optional<WSTranslator> optTranslator = this.translators.stream()
                .filter(t -> t.sourceClass().equals(sourceClass) && t.targetClass().equals(targetClass))
                .findAny();

        if (optTranslator.isPresent()) {
            return optTranslator.get();
        }
        throw new Exception(String.format("%s: there is no translator for %s to %s",
                this.getClass().getSimpleName(),
                sourceClass.getName(),
                targetClass.getName()));
    }

}
