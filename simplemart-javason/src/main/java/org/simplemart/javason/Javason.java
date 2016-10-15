package org.simplemart.javason;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Javason {

    private final Map<String, Object> attributes = new HashMap<>();

    public Javason o(String name, Object value) {
        require(name, "name");
        attributes.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder jsonString = new StringBuilder("{");
        String s = attributes.entrySet().stream().map(Javason::toString).collect(Collectors.joining(", "));
        jsonString.append(s);
        return jsonString.append("}").toString();
    }

    private void require(Object arg, String argName) {
        if (arg == null) {
            throw new IllegalArgumentException(argName + " must not be null");
        }
    }

    private static String toString(Entry<String, Object> e) {
        return '"' + e.getKey() + '"' + ": " + toString(e.getValue());
    }

    private static String toString(Object v) {
        if (v instanceof String) {
            return '"' + v.toString() + '"';
        }
        return v.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Javason().o("name", "Javason").toString());
    }
}
