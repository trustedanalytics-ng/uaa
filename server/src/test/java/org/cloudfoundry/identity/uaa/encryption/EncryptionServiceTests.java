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

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.security.SecureRandom;
import java.util.Base64;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;

@RunWith(MockitoJUnitRunner.class)
public class EncryptionServiceTests {

    private static final String ProperHashResult = "jVxfRzp42MAbwvZj3nyMkZKPXriLhRh2uH7lMvxsmbw=";

    private EncryptionService encryptionService;

    @Mock
    private static SecureRandom secureRandom;

    @Before
    public void setUp() {
        encryptionService = new FakeEncryptionService(secureRandom);
      }

    @Test
    public void shouldHash() {
        String userMail = "email@example.com";

        String hashedMail = new String(Base64.getEncoder().encode(encryptionService.hash(userMail)));

        assertEquals(ProperHashResult, hashedMail);
    }

    @Test
    public void shouldHashSameResultForSameInput() {
        String userMail = "email@example.com";

        String hashedMail_first = new String(Base64.getEncoder().encode(encryptionService.hash(userMail)));
        String hashedMail_second = new String(Base64.getEncoder().encode(encryptionService.hash(userMail)));

        assertEquals(ProperHashResult,hashedMail_first);
        assertEquals(ProperHashResult,hashedMail_second);
    }
}