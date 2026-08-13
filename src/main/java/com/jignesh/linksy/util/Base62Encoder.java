package com.jignesh.linksy.util;

public class Base62Encoder {

    private static final String CHARACTERS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int BASE = CHARACTERS.length();

    private Base62Encoder() {

    }

    public static String encode(Long id) {
        if (id == null || id < 0) {
            throw new IllegalArgumentException("ID must be a non-negative number");
        }
        if (id == 0) {
            return String.valueOf(CHARACTERS.charAt(0));
        }

        StringBuilder sb = new StringBuilder();
        long value = id;
        while (value > 0) {
            sb.append(CHARACTERS.charAt((int) (value % BASE)));
            value /= BASE;
        }
        return sb.reverse().toString();
    }
}
