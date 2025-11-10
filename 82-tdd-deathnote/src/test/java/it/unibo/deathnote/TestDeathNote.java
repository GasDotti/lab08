package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.impl.DeathNoteImpl;

class TestDeathNote {

    private static final String EMPTY_STRING = "";
    private DeathNote book;
    private final int[] falseRules = {0, DeathNote.RULES.size() + 1};
    private List<String> names;
    // List with a null element.
    private List<Object> falseNames;

    /**
     * Configuration for the tests.
     */
    @BeforeEach
    void setup() {
        book = new DeathNoteImpl();
        names = new ArrayList<>();
        names.addAll(List.of("pippo", "pluto", "paperino", "giorgia", ""));
        falseNames = new ArrayList<>();
    }

    /**
     * Checks if the selection of the rule is correct.
     */
    @Test
    void testRule() {
        final int validRule = 1;

        // Testing illegal arguments.
        for (final int i : falseRules) {
            try {
                book.getRule(i);
            } catch (final IllegalArgumentException e) {
                assertEquals(DeathNote.RULES.get(validRule - 1), book.getRule(validRule));
            }
        }
        // Testing content of the Rules.
        for (int i = 1; i <= DeathNote.RULES.size(); i++) {
            assertNotEquals(book.getRule(i), EMPTY_STRING);
            assertNotNull(book.getRule(i));
        }
    }

    /**
     * Tries the correct functioning for the action of "writing a name".
     */
    @Test
    void testWriteName() {
        for (final var n : List.of(names, falseNames)) {
            assertTrue(book.isNameWritten(n.toString()));

            try {
                book.writeName(n.toString());
                assertTrue(book.isNameWritten(n.toString()));

                // non ho capito il punto '* verify that another human has not been written in the notebook'

                assertFalse(book.isNameWritten(EMPTY_STRING));
            } catch (final NullPointerException e) {
                assertNull(n);
            }
        }
    }
}
