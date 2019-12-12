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
import org.schorn.ella.context.AppContext;
import org.schorn.ella.node.ActiveNode;
import org.schorn.ella.ws.facade.ArrayType;
import org.schorn.ella.ws.facade.ArrayTypes;
import org.schorn.ella.ws.facade.Context;
import org.schorn.ella.ws.facade.Contexts;
import org.schorn.ella.ws.facade.FieldType;
import org.schorn.ella.ws.facade.FieldTypes;
import org.schorn.ella.ws.facade.ObjectType;
import org.schorn.ella.ws.facade.ObjectTypeMembers;
import org.schorn.ella.ws.facade.ObjectTypes;
import org.schorn.ella.ws.facade.TableType;
import org.schorn.ella.ws.facade.ValueType;
import org.schorn.ella.ws.facade.ValueTypes;
import org.schorn.ella.ws.service.WSTranslator;

/**
 *
 * @author bschorn
 */
public enum MetaTranslators {
    Context(new WSTranslator<AppContext, Context>(AppContext.class, Context.class, object -> new Context((AppContext) object))),
    //Context(new WSTranslator<AppContext, Response>(AppContext.class, Response.class, object -> new Context((AppContext) object))),
    Contexts(new WSTranslator<List, Contexts>(List.class, Contexts.class, object -> new Contexts((List<AppContext>) object))),
    ArrayType(new WSTranslator<ActiveNode.ArrayType, ArrayType>(ActiveNode.ArrayType.class, ArrayType.class, object -> new ArrayType((ActiveNode.ArrayType) object))),
    ArrayTypes(new WSTranslator<List, ArrayTypes>(List.class, ArrayTypes.class, object -> new ArrayTypes((List<ActiveNode.ArrayType>) object))),
    ObjectType(new WSTranslator<ActiveNode.ObjectType, ObjectType>(ActiveNode.ObjectType.class, ObjectType.class, object -> new ObjectType((ActiveNode.ObjectType) object))),
    ObjectTypeMembers(new WSTranslator<ActiveNode.ObjectType, ObjectTypeMembers>(ActiveNode.ObjectType.class, ObjectTypeMembers.class, object -> new ObjectTypeMembers((ActiveNode.ObjectType) object))),
    ObjectTypes(new WSTranslator<List, ObjectTypes>(List.class, ObjectTypes.class, object -> new ObjectTypes((List<ActiveNode.ObjectType>) object))),
    ValueType(new WSTranslator<ActiveNode.ValueType, ValueType>(ActiveNode.ValueType.class, ValueType.class, object -> new ValueType((ActiveNode.ValueType) object))),
    ValueTypes(new WSTranslator<List, ValueTypes>(List.class, ValueTypes.class, object -> new ValueTypes((List<ActiveNode.ValueType>) object))),
    FieldType(new WSTranslator<ActiveNode.ValueType.FieldType, FieldType>(ActiveNode.ValueType.FieldType.class, FieldType.class, object -> new FieldType((ActiveNode.ValueType.FieldType) object))),
    FieldTypes(new WSTranslator<List, FieldTypes>(List.class, FieldTypes.class, object -> new FieldTypes((List<ActiveNode.ValueType.FieldType>) object))),
    TableType(new WSTranslator<ActiveNode.ObjectType, TableType>(ActiveNode.ObjectType.class, TableType.class, object -> new TableType((ActiveNode.ObjectType) object)));

    WSTranslator<?, ?> translator;

    MetaTranslators(WSTranslator<?, ?> translator) {
        this.translator = translator;
    }

    public WSTranslator<?, ?> translator() {
        return this.translator;
    }

    static public WSTranslator<?, ?>[] translators() {
        int i = 0;
        WSTranslator<?, ?>[] translators = new WSTranslator<?, ?>[MetaTranslators.values().length];
        for (MetaTranslators metaTranslator : MetaTranslators.values()) {
            translators[i++] = metaTranslator.translator();
        }
        return translators;
    }

}
