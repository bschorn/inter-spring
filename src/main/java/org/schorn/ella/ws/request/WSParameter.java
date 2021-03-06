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
package org.schorn.ella.ws.request;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bschorn
 */
public interface WSParameter {

    default int typeId(int index) {
        return InternalHelper.INSTANCE.getTypeId(this.getClass(), index);
    }

    int id();

    String name();

    Object value();

    default <T> T valueAs(Class<T> classOfT) {
        return (T) this.value();
    }

    static class InternalHelper {

        static final InternalHelper INSTANCE = new InternalHelper();
        private final List<Object> subTypes;

        private InternalHelper() {
            this.subTypes = new ArrayList<>();
        }

        int getTypeId(Class<? extends WSParameter> parameterClass, int index) {
            int subIndex = this.subTypes.indexOf(parameterClass);
            if (subIndex == -1) {
                this.subTypes.add(parameterClass);
                subIndex = this.subTypes.indexOf(parameterClass);
            }
            int base = 10;
            Double base10 = Math.pow(base + index, subIndex + 1);
            System.out.println(String.format("WSParameter.getTypeId() %s -> %d", parameterClass.getSimpleName(), base10.intValue()));
            return base10.intValue();
        }
    }

}
