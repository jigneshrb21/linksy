package com.jignesh.linksy.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Base62EncoderTest {

    @Test
    void encodeZeroReturnsZeroCharacter() {
        assertEquals("0", Base62Encoder.encode(0L));
    }

    @Test
    void encodeSixtyOneReturnsLastBase62Character() {
        assertEquals("Z", Base62Encoder.encode(61L));
    }

    @Test
    void encodeSixtyTwoRollsOver() {
        assertEquals("10", Base62Encoder.encode(62L));
    }

    @Test
    void encodeNullThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Base62Encoder.encode(null));
    }

    @Test
    void encodeNegativeThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Base62Encoder.encode(-1L));
    }
}
