
/*
 * The MIT License
 *
 * Copyright 2018 bschorn.
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
package org.schorn.ella.ws.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bschorn
 */
public interface Functions {

    static String getStackTraceAsString(Throwable throwable) {
        if (throwable.getMessage() != null) {
            return throwable.getMessage();
        }
        StringWriter stringWriter = new StringWriter();
        throwable.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }
    static String stackTraceToString(Throwable throwable) {
        StringWriter stringWriter = new StringWriter();
        throwable.printStackTrace(new PrintWriter(stringWriter));
        if (throwable.getMessage() != null) {
            return String.format("%s -> %s", throwable.getMessage(), stringWriter.toString());
        } else {
        	return stringWriter.toString();
        }
    }
    /**
     * Utility Function
     * 
     * Returns pre-seeded lists of nulls.
     * 
     * @param classOfT - element type
     * @param size - initial capacity 
     * @return
     */
    
    /**
     * Utility Function
     * 
     * Returns pre-sized ArrayList with the initValue
     * 
     * @param classOfT - element type
     * @param size - initial capacity & initialized to defaultT 
     * @param initValue - initialization value for each element from 0..size
     * @return
     */
    static <T> ArrayList<T> createPresizedArrayList(Class<T> classOfT, int size, T initValue) {
    	List<T> list = new ArrayList<>(size);
    	while (--size >= 0) {
    		list.add(initValue);
    	}
    	return (ArrayList<T>) list;
    }

    static String wildcardToRegex(String wildcard) {
        StringBuilder s = new StringBuilder(wildcard.length());
        s.append('^');
        for (int i = 0, is = wildcard.length(); i < is; i++) {
            char c = wildcard.charAt(i);
            switch (c) {
                case '*':
                    s.append(".*");
                    break;
                case '?':
                    s.append(".");
                    break;
                // escape special regexp-characters
                case '(':
                case ')':
                case '[':
                case ']':
                case '$':
                case '^':
                case '.':
                case '{':
                case '}':
                case '|':
                case '\\':
                    s.append("\\");
                    s.append(c);
                    break;
                default:
                    s.append(c);
                    break;
            }
        }
        s.append('$');
        return (s.toString());
    }
}