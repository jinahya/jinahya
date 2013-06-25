/*
 * Copyright 2013 Jin Kwon <onacit at gmail.com>.
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


package com.googlecode.jinahya.jvm.cff;


import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public abstract class attribute_info {


    public static final String NAME_ConstantValue = "ConstantValue";


    public static final String NAME_Code = "Code";


    public static final String NAME_StackMapTable = "StackMapTable";


    public static final String NAME_Exceptions = "Exceptions";


    public static final String NAME_InnerClasses = "InnerClasses";


    public static final String NAME_EnclosingMethod = "EnclosingMethod";


    public static final String NAME_Synthetic = "Synthetic";


    public static final String NAME_Signature = "Signature";


    public static final String NAME_SourceFile = "SourceFile";


    public static final String NAME_SourceDebugExtension = "SourceDebugExtension";


    public static final String NAME_LineNumberTable = "LineNumberTable";


    public static final String NAME_LocalVariableTable = "LocalVariableTable";


    public static final String NAME_LocalVariableTypeTable = "LocalVariableTypeTable";


    public static final String NAME_Deprecated = "Deprecated";


    public static final String NAME_RuntimeVisibleAnnotations =
        "RuntimeVisibleAnnotations";


    public static final String NAME_RuntimeInvisibleAnnotations =
        "RuntimeInvisibleAnnotations";


    public static final String NAME_RuntimeVisibleParameterAnnotations =
        "RuntimeVisibleParameterAnnotations";


    public static final String NAME_RuntimeInvisibleParameterAnnotations =
        "RuntimeInvisibleParameterAnnotations";


    public static final String NAME_AnnotationDefault = "AnnotationDefault";


    public static final String NAME_BootstrapMethods = "BootstrapMethods";


    public static enum NAME {


        ConstantValue(NAME_ConstantValue, ConstantValue_attribute.class),
        Code(NAME_Code, Code_attribute.class),
        StackMapTable(NAME_StackMapTable),
        Exceptions(NAME_Exceptions, Exceptions_attribute.class),
        InnerClasses(NAME_InnerClasses),
        EnclosingMethod(NAME_EnclosingMethod),
        Synthetic(NAME_Synthetic),
        Signature(NAME_Signature),
        SourceFile(NAME_SourceFile),
        SourceDebugExtension(NAME_SourceDebugExtension),
        LineNumberTable(NAME_LineNumberTable),
        LocalVariableTable(NAME_LocalVariableTable),
        LocalVariableTypeTable(NAME_LocalVariableTypeTable),
        Deprecated(NAME_Deprecated),
        RuntimeVisibleAnnotations(NAME_RuntimeVisibleAnnotations),
        RuntimeInvisibleAnnotations(NAME_RuntimeInvisibleAnnotations),
        RuntimeVisibleParameterAnnotations(
        NAME_RuntimeVisibleParameterAnnotations),
        RuntimeInvisibleParameterAnnotations(
        NAME_RuntimeInvisibleParameterAnnotations),
        AnnotationDefault(NAME_AnnotationDefault),
        BootstrapMethods(NAME_BootstrapMethods);


        public static NAME fromName(final String name) {

            for (NAME value : values()) {
                if (value.name().equals(name)) {
                    return value;
                }
            }

            throw new IllegalArgumentException("Unknown name: " + name);
        }


        public static attribute_info newAttribute_info(final String name) {
            try {
                return fromName(name).getType().newInstance();
            } catch (InstantiationException ie) {
                throw new RuntimeException(ie);
            } catch (IllegalAccessException iae) {
                throw new RuntimeException(iae);
            }
        }


        private NAME(final String name,
                     final Class<? extends attribute_info> type) {

            this.name = name;
            this.type = type;
        }


        /**
         *
         * @param name
         *
         * @deprecated
         */
        @Deprecated
        private NAME(final String name) {
            this(name, Unknown_attribute.class);
        }


        private Class<? extends attribute_info> getType() {
            return type;
        }


        private final String name;


        private final Class<? extends attribute_info> type;


    }


    protected abstract void readInfo(DataInput input) throws IOException;


    protected abstract void writeInfo(DataOutput output) throws IOException;


    transient ClassFile classFile;


}

