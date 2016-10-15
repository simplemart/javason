package org.simplemart.javason;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class JavasonTest {

    @DataProvider
    public Object[][] getAttributes() {
        return new Object[][] {
                {"string", "foo", json("{'string': 'foo'}")},
                {"integer", 10, json("{'integer': 10}")},
                {"double", 10.1, json("{'double': 10.1}")},
                {"boolean", true, json("{'boolean': true}")},
                {"boolean", false, json("{'boolean': false}")},
                {"empty", null, json("{'empty': null}")},
                {"array", new Integer[] {3, 4}, json("{'array': [3, 4]}")},
                {"object", j().o("nested", "a"), json("{'object': {'nested': 'a'}}")},
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
