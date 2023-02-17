package io.github.lc.oss.commons.email;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.github.lc.oss.commons.testing.AbstractMockTest;

public class AbstractEmailServiceTest extends AbstractMockTest {
    private static class TestService extends AbstractEmailService {
        @Override
        protected String getUsername() {
            return null;
        }

        @Override
        protected String getPassword() {
            return null;
        }

        @Override
        public void send(Message message) {
        }
    }

    @Test
    public void test_isNotBlank_String() {
        AbstractEmailService service = new TestService();

        Assertions.assertFalse(service.isNotBlank((String) null));
        Assertions.assertFalse(service.isNotBlank(""));
        Assertions.assertFalse(service.isNotBlank(" \t \r \n \t "));

        Assertions.assertTrue(service.isNotBlank("a"));
        Assertions.assertTrue(service.isNotBlank(" a "));
        Assertions.assertTrue(service.isNotBlank(" \t \r a \n \t "));
    }

    @Test
    public void test_isNotBlank_Collection() {
        AbstractEmailService service = new TestService();

        Assertions.assertFalse(service.isNotBlank((Collection<?>) null));
        Assertions.assertFalse(service.isNotBlank(new HashSet<>()));

        /*
         * Note: This one is considered not blank because the collection is not empty,
         * we do not inspect the contents of each element so despite this being an array
         * of empty strings the collection itself is not considered empty.
         */
        Assertions.assertTrue(service.isNotBlank(Arrays.asList("")));
    }

    @Test
    public void test_isNotBlank_Other() {
        AbstractEmailService service = new TestService();

        Assertions.assertFalse(service.isNotBlank((Object) null));

        Assertions.assertTrue(service.isNotBlank(new Object()));
    }
}
