/*
 * Copyright 2013 Jin Kwon <jinahya at gmail.com>.
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


package com.googlecode.jinahya.rfc3550;


import com.googlecode.jinahya.io.BitInput;
import com.googlecode.jinahya.io.BitOutput;
import java.io.IOException;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class RTPHeader {


    public void read(final BitInput input) throws IOException {

        version = input.readUnsignedInt(2);

        padding = input.readBoolean();

        extension = input.readBoolean();

        contributingSourceCount = input.readUnsignedInt(4);

        marker = input.readUnsignedInt(1);

        payloadType = input.readUnsignedInt(7);

        sequenceNumber = input.readUnsignedInt(16);

        timestamp = input.readInt(32);

        synchronizationSourceIdentifier = input.readInt(32);

        for (int i = 0; i < contributingSourceCount; i++) {
            contributingSourceIdentifiers[i] = input.readInt(32);
        }
    }


    public void write(final BitOutput output) throws IOException {
        throw new UnsupportedOperationException("not implemented yet");
    }


    public int getVersion() {
        return version;
    }


    public void setVersion(final int version) {
        this.version = version;
    }


    public boolean isPadding() {
        return padding;
    }


    public void setPadding(final boolean padding) {
        this.padding = padding;
    }


    public boolean isExtension() {
        return extension;
    }


    public void setExtension(final boolean extension) {
        this.extension = extension;
    }


//    public int getContributingSourceCount() {
//        return contributingSourceCount;
//    }
//
//
//    public void setContributingSourceCount(final int contributingSourceCount) {
//        this.contributingSourceCount = contributingSourceCount;
//    }
    public int getMarker() {
        return marker;
    }


    public void setMarker(final int marker) {
        this.marker = marker;
    }


    public int getPayloadType() {
        return payloadType;
    }


    public void setPayloadType(final int payloadType) {
        this.payloadType = payloadType;
    }


    public int getSequenceNumber() {
        return sequenceNumber;
    }


    public void setSequenceNumber(final int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }


    public int getTimestamp() {
        return timestamp;
    }


    public void setTimestamp(final int timestamp) {
        this.timestamp = timestamp;
    }


    public int getSynchronizationSourceIdentifier() {
        return synchronizationSourceIdentifier;
    }


    public void setSynchronizationSourceIdentifier(
        final int synchronizationSourceIdentifier) {

        this.synchronizationSourceIdentifier = synchronizationSourceIdentifier;
    }


    public int[] getContributingSourceIdentifiers() {
        return contributingSourceIdentifiers;
    }


    public void setContributingSourceIdentifiers(
        final int[] contributingSourceIdentifiers) {

        if (contributingSourceIdentifiers == null) {
            throw new NullPointerException(
                "null contributingSourceIdentifiers");
        }

        if (contributingSourceIdentifiers.length > 0x0F) {
            throw new IllegalArgumentException(
                "contributingSourceIdentifiers.length("
                + contributingSourceIdentifiers.length + ") > 0x0F");
        }

        this.contributingSourceIdentifiers = contributingSourceIdentifiers;

        contributingSourceCount = this.contributingSourceIdentifiers.length;
    }


    private int version; // 2 bits


    private boolean padding; // 1 bit


    private boolean extension; // 1 bit


    private int contributingSourceCount; // 4 bits;


    private int marker; // 1 bit


    private int payloadType; // 7 bits


    private int sequenceNumber; // 16 bits


    private int timestamp; // 32 bits


    private int synchronizationSourceIdentifier; // 32 bits


    private int[] contributingSourceIdentifiers; // 32 bits / item
}

