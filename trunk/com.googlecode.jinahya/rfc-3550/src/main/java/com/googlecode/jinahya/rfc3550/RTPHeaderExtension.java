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
public class RTPHeaderExtension {


    public void read(final BitInput input) throws IOException {

        profile = input.readInt(16);

        extensions = new int[input.readUnsignedInt(16)];
        
        for (int i = 0; i < extensions.length; i++) {
            extensions[i] = input.readInt(32);
        }
    }


    public void write(final BitOutput output) throws IOException {

        output.writeInt(16, profile);

        output.writeUnsignedInt(16, extensions.length);

        for (int i = 0; i < extensions.length; i++) {
            output.writeInt(32, extensions[i]);
        }
    }


    // ----------------------------------------------------------------- profile
    public int getProfile() {
        return profile;
    }


    public void setProfile(final int profile) {
        this.profile = profile;
    }


    public byte[] getProfileBytes() {

        final byte[] profileBytes = new byte[2];

        profileBytes[0] = (byte) (profile >> 8);
        profileBytes[1] = (byte) (profile & 0xFF);

        return profileBytes;
    }


    public void setProfileBytes(final byte[] profileBytes) {

        if (profileBytes == null) {
            throw new NullPointerException("null profileBytes");
        }

        if (profileBytes.length != 2) {
            throw new IllegalArgumentException(
                "profileBytes.length(" + profileBytes.length + ") != 2");
        }


        profile = ((profileBytes[0] & 0xFF) << 8) | (profileBytes[1] & 0xFF);
    }


    // -------------------------------------------------------------- extensions
    public int[] getExtensions() {
        return extensions;
    }


    public void setExtensions(final int[] extensions) {

        if (extensions == null) {
            throw new NullPointerException("null extensions");
        }

        if (extensions.length > 0xFFFF) { // 65535
            throw new IllegalArgumentException("extensions.length > 0xFFFF");
        }

        this.extensions = extensions;
    }


    public byte[] getExtensionBytes() {

        final byte[] extensionBytes = new byte[extensions.length * 4];

        for (int i = 0; i < extensionBytes.length; i += 4) {
            extensionBytes[i] = (byte) ((extensions[i] >> 24) & 0xFF);
            extensionBytes[i + 1] = (byte) ((extensions[i] >> 16) & 0xFF);
            extensionBytes[i + 2] = (byte) ((extensions[i] >> 8) & 0xFF);
            extensionBytes[i + 3] = (byte) ((extensions[i]) & 0xFF);
        }

        return extensionBytes;
    }


    public void setExtensionBytes(final byte[] extensionBytes) {

        if (extensionBytes == null) {
            throw new NullPointerException("null extensionBytes");
        }

        if (extensionBytes.length > 0x03FFFC) { // 262140 = 65535 * 4
            throw new IllegalArgumentException(
                "extensionBytes.length(" + extensionBytes.length
                + ") > 0x03FFFC");
        }

        if (extensionBytes.length % 4 != 0) {
            throw new IllegalArgumentException(
                "extensionBytes.length(" + extensionBytes.length
                + ") % 4 != 0");
        }

        extensions = new int[extensionBytes.length / 4];
        for (int i = 0; i < extensions.length; i++) {
            extensions[i] = ((extensionBytes[i * 4] & 0xFF) << 24)
                            | ((extensionBytes[i * 4 + 1] & 0xFF) << 16)
                            | ((extensionBytes[i * 4 + 2] & 0xFF) << 8)
                            | (extensionBytes[i * 4 + 3] & 0xFF);
        }
    }


    private int profile; // 16 bits


    private int[] extensions; // 32 bits / item
}

