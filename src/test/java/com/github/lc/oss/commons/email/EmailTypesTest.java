package com.github.lc.oss.commons.email;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.github.lc.oss.commons.testing.AbstractMockTest;

public class EmailTypesTest extends AbstractMockTest {
    @Test
    public void test_caching() {
        Set<EmailTypes> expected = new HashSet<>(Arrays.asList(EmailTypes.values()));
        Set<EmailTypes> actual = EmailTypes.all();

        Assertions.assertNotSame(expected, actual);
        Assertions.assertEquals(expected, actual);
        Assertions.assertTrue(expected.containsAll(actual));
        Assertions.assertTrue(actual.containsAll(expected));

        Assertions.assertTrue(EmailTypes.hasName("PlainText"));
        Assertions.assertTrue(EmailTypes.hasName("HTML"));

        Assertions.assertSame(EmailTypes.PlainText, EmailTypes.byName("PlainText"));
        Assertions.assertSame(EmailTypes.PlainText, EmailTypes.byName("pLAINtExt"));

        Assertions.assertSame(EmailTypes.HTML, EmailTypes.tryParse("html"));
        Assertions.assertSame(EmailTypes.HTML, EmailTypes.tryParse("hTmL"));
    }

    @Test
    public void test_mimes() {
        Assertions.assertEquals("text/plain", EmailTypes.PlainText.getMime());
        Assertions.assertEquals("text/html", EmailTypes.HTML.getMime());
    }
}
