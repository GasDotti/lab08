package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.impl.DeathNoteImpl;

class TestDeathNote {

    static private final String EMPTY_STRING = "";
    private DeathNote book;
    private final int[] falseRules = {0, DeathNote.RULES.size() + 1};
    private List<String> names;

    /**
     * Configuration for the tests.
     */
    @BeforeEach
    void setup() {
        book = new DeathNoteImpl();
        names = new ArrayList<>();
        names.addAll(List.of("pippo", "pluto", "paperino", "giorgia"));
    }

    /**
     * Checks if the selection of the rule is correct
     */
    @Test
    void testRule() {
        final int validRule = 1;

        for (final int i : falseRules) {
            try {
                book.getRule(i);
            } catch (final IllegalArgumentException ex) {
                assertEquals(DeathNote.RULES.get(validRule - 1), book.getRule(validRule));
            }
        }
        for (int i = 1; i <= DeathNote.RULES.size(); i++) {
            assertNotEquals(book.getRule(i), EMPTY_STRING);
            assertNotNull(book.getRule(i));
        }
    }
}
