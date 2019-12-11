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
package org.schorn.ella.ws.facade;

import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import lombok.Getter;
import org.schorn.ella.node.ActiveNode;
import org.schorn.ella.node.ActiveNode.Role;
import org.schorn.ella.node.BondType;

/**
 *
 * @author bschorn
 */
public class TableType extends ObjectType {

    @Getter
    List<String> fieldNames;

    public TableType(ActiveNode.ObjectType objectType) {
        super(objectType);
        this.fieldNames = objectType.schema().memberDefs().stream()
                .filter(md -> md.activeType().role().equals(Role.Value))
                .filter(md -> !md.bondType().equals(BondType.AUTOMATIC))
                .map(md -> md.activeType().name())
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(",", "", "");
        this.fieldNames.stream()
                .forEach(str -> joiner.add(str));
        return joiner.toString();
    }
}
