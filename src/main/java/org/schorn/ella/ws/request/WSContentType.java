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

import org.springframework.http.MediaType;

/**
 *
 * @author bschorn
 */
public enum WSContentType implements WSParameter {
    JSON(MediaType.APPLICATION_JSON),
    YAML("application/x-yaml"),
    XML(MediaType.APPLICATION_XML),
    HTML(MediaType.TEXT_HTML),
    FORM(MediaType.MULTIPART_FORM_DATA),
    TXT(MediaType.TEXT_PLAIN),
    CSV(MediaType.parseMediaType("text/csv")),
    XLS(MediaType.parseMediaType("application/vnd.ms-excel")),
    XLSX(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")),
    BIN(MediaType.parseMediaType("application/octet-stream"));

    private final int id;
    private final String value;
    private final MediaType mediaType;

    WSContentType(String value) {
        this.value = value;
        this.id = this.typeId(this.ordinal());
        this.mediaType = null;
    }
    WSContentType(MediaType mediaType) {
        this.mediaType = mediaType;
        this.value = mediaType.getType();
        this.id = this.typeId(this.ordinal());
    }

    @Override
    public int id() {
        return this.id;
    }

    @Override
    public Object value() {
        return this.value;
    }

    public MediaType mediaType() {
        return this.mediaType;
    }

    @Override
    public String toString() {
        return String.format("%s.%s",
                this.getClass().getSimpleName(),
                this.name());
    }

    static public WSContentType parse(Object value) throws Exception {
        for (WSContentType contentType : WSContentType.values()) {
            if (contentType.name().equalsIgnoreCase(value.toString())) {
                return contentType;
            }
        }
        throw new Exception(String.format("No Content_Type of '%s' found.", value.toString()));
    }
}
