/**
 * Copyright (c) 2016 Intel Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.cloudfoundry.identity.uaa.encryption;


import com.google.common.base.Preconditions;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

@Component
public class EncryptionService {
    private MessageDigest sha;
    private SecretKeySpec key;
    private SecureRandom secureRandom;
    private String salt;

    public EncryptionService(String cipher, String salt) {
        this(cipher, salt, new SecureRandom());
    }

    public EncryptionService( String cipher, String salt, SecureRandom sRandom) {
        Preconditions.checkNotNull(cipher);
        Preconditions.checkArgument(cipher.length() == 16, "Encryption key has to be 128-bit long");

        Preconditions.checkNotNull(salt);
        Preconditions.checkArgument(salt.length() == 32, "Hash salt should be 256-bit long");

        secureRandom = sRandom;
        key = new SecretKeySpec(cipher.getBytes(), "AES");
        this.salt = salt;
    }

    public byte[] hash(String toHash) {
        try {
            sha = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeEncryptionException("Unable to create encryption service", e);
        }

        return sha.digest((salt+toHash).getBytes());
    }
}