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

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.impl.DeathNoteImpl;

class TestDeathNote {

    private static final String EMPTY_STRING = "";

    private DeathNote book = new DeathNoteImpl();

    private final int[] falseRules = {0, DeathNote.RULES.size() + 1};
    private final List<String> names = new ArrayList<>();

    /**
     * Configuration for the tests.
     */
    @BeforeEach
    void setup() {
        book = new DeathNoteImpl();
        names.addAll(List.of("pippo", "pluto", "paperino", "giorgia"));
    }

    /**
     * Checks if the selection of the rule is correct.
     */
    @Test
    void testRule() {
        final int validRule = 1;
        names.add((String) null);

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
    @SuppressFBWarnings(
        value = "DCN_NULLPOINTER_EXCEPTION", 
        justification = "Catch required by the exercise"
        )
    void testWriteName() {
        for (final String n : names) {
            assertFalse(book.isNameWritten(n));

            try {
                book.writeName(n);
                assertTrue(book.isNameWritten(n));

                if (EMPTY_STRING.equals(n)) {
                    assertFalse(book.isNameWritten(EMPTY_STRING));
                }

            } catch (final NullPointerException e) { // NOPMD Required by the exercise

                assertNull(n);
            }
        }
    }

    @Test
    void testWrtingCause() throws InterruptedException {
        // Asserts the book is empty
        assertEquals(DeathNoteImpl.getContentOf((DeathNoteImpl) book), new ArrayList<>());

        try {
            assertFalse(book.writeDeathCause("Throw Exception"), "The Exception wasn't thrown.");
        } catch (final IllegalStateException e) {
            assertEquals(e.getClass(), IllegalStateException.class);
        }

        for (final String n : names) {
            String cause = "karting accident";

            book.writeName(n);
            final long writingNameAtTime = System.currentTimeMillis();

            if (names.indexOf(n) % 2 == 0) {
                Thread.sleep(DeathNoteImpl.CAUSE_LIMIT);

                assertTrue(System.currentTimeMillis() - writingNameAtTime > DeathNoteImpl.CAUSE_LIMIT);
                assertFalse(book.writeDeathCause(cause));
                assertEquals(DeathNoteImpl.BASE_CAUSE, book.getDeathCause(n));
            } else {
                assertTrue(System.currentTimeMillis() - writingNameAtTime < DeathNoteImpl.CAUSE_LIMIT);

                assertTrue(book.writeDeathCause(cause));
                cause = "changing cause";
                assertFalse(book.writeDeathCause(cause));
                assertNotEquals(cause, book.getDeathCause(n));
            }
        }
    }

    @Test
    void testDetails() throws InterruptedException {
        assertEquals(DeathNoteImpl.getContentOf((DeathNoteImpl) book), new ArrayList<>());
        String details = "ran for too long";
        long writingNameAtTime;

        try {
            book.writeDetails("Throw Exception");
        } catch (final IllegalStateException e) {
            assertEquals(e.getClass(), IllegalStateException.class);
        }

        for (final String n : names) {
            book.writeName(n);
            writingNameAtTime = System.currentTimeMillis();

            // Test di corretto funzionamento dei Details.
            assertEquals(EMPTY_STRING, book.getDeathDetails(n));
            assertTrue(System.currentTimeMillis() - writingNameAtTime < DeathNoteImpl.DETAILS_LIMIT);
            assertTrue(book.writeDetails(details));
            assertEquals(details, book.getDeathDetails(n));

            // Controllo che non si possa modificare il contenuto.
            details = "change details";
            assertFalse(book.writeDetails(details));
            assertNotEquals(details, book.getDeathDetails(n));
        }

        // Test di mancata scrittura dei Details.
        book.writeName("Last_Name");
        writingNameAtTime = System.currentTimeMillis();
        Thread.sleep(DeathNoteImpl.DETAILS_LIMIT);
        assertTrue(System.currentTimeMillis() - writingNameAtTime > DeathNoteImpl.DETAILS_LIMIT);
        assertFalse(book.writeDetails(details));
    }
}
