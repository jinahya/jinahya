/*
 * Copyright 2011 Jin Kwon <jinahya at gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.googlecode.jinahya.jvms.classfile.attribute;


import com.googlecode.jinahya.jvms.classfile.attribute.stackmap.StackMapTable;



/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public enum AttributeName {

    ConstantValue(ConstantValue.class),
    Code(Code.class),
    StackMapTable(StackMapTable.class),
    Exceptions(Exceptions.class),
    InnerClasses(InnerClasses.class),
    EnclosingMethod(EnclosingMethod.class),
    Synthetic(Synthetic.class),
    Signature(Signature.class),
    SourceFile(SourceFile.class),
    SourceDebugExtension(SourceDebugExtension.class),
    LineNumberTable(LineNumberTable.class),
    LocalVariableTable(LocalVariableTable.class),
    LocalVariableTypeTable(LocalVariableTypeTable.class),
    Deprecated(Deprecated.class),
    RuntimeVisibleAnnotations(RuntimeVisibleAnnotations.class),
    RuntimeInvisibleAnnotations(RuntimeInvisibleAnnotations.class),
    RuntimeVisibleParameterAnnotations(
        RuntimeVisibleParameterAnnotations.class),
    RuntimeInvisibleParameterAnnotations(
        RuntimeInvisibleParameterAnnotations.class),
    AnnotationDefault(AnnotationDefault.class);


    /**
     * Creates a new instance.
     *
     * @param attributeType target attribute type
     */
    private AttributeName(final Class<? extends Attribute> attributeType) {
        this.attributeType = attributeType;
    }


    /**
     * Creates a new target attribute.
     *
     * @return new attribute
     */
    public Attribute newAttribute() {
        try {
            return attributeType.newInstance();
        } catch (InstantiationException ie) {
            throw new RuntimeException(
                "failed to create a new instance of " + attributeType, ie);
        } catch (IllegalAccessException iae) {
            throw new RuntimeException(
                "failed to create a new instance of " + attributeType, iae);
        }
    }


    private final Class<? extends Attribute> attributeType;


}

