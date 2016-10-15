package org.simplemart.javason;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class JavasonTest {

    @DataProvider
    public Object[][] getAttributes() {
        return new Object[][] {
                {"string", "foo", json("{'string': 'foo'}")},
                {"number", 10, json("{'number': 10}")},
        };
    }

    @Test(dataProvider = "getAttributes")
    public void attributes(String name, Object value, String expectedJson) {
        String actualJson = j().o(name, value).toString();
        assertThat(actualJson).isEqualTo(expectedJson);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void illegalArguments() {
        new Javason().o(null, "foo");
    }

    private String json(String str) {
        return str.replace('\'', '"');
    }

    private Javason j() {
        return new Javason();
    }
}
