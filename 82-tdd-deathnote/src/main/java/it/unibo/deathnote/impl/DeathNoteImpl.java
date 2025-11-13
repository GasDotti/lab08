package it.unibo.deathnote.impl;

import it.unibo.deathnote.api.DeathNote;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that implements {@link DeathNote.java}.
 */
public class DeathNoteImpl implements DeathNote {

    public static final int CAUSE_LIMIT = 40;
    public static final int DETAILS_LIMIT = 6000;
    public static final String BASE_CAUSE = "heart Attack";
    private final List<Object> content;
    private long writingTimer;

    /**
     * Base constructor, it initializes an empty DeathNote.
     */
    public DeathNoteImpl() {
        this.content = new ArrayList<>();
    }

    /**
     * @param book a DeathNoteImpl with content.
     * @return the List of Deaths written in the book.
     */
    public static List<Object> getContentOf(final DeathNoteImpl book) {
        return List.copyOf(book.content);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getRule(final int ruleNumber) {
        if (ruleNumber > 0 && ruleNumber <= RULES.size()) {
            return RULES.get(ruleNumber - 1);
        }
        throw new IllegalArgumentException();
     }

    /**
     * {@inheritDoc}
     */
    @Override
    public void writeName(final String name) {

        if (name == null || name.isEmpty()) {
            throw new NullPointerException(); // NOPMD Required by the exercise
        }
        if (!this.isNameWritten(name)) {
            content.add(new Death(name));
            writingTimer = System.currentTimeMillis();
        }
    }

    /**
     *  {@inheritDoc}
     */
    @Override
    public boolean writeDeathCause(final String cause) {
        if (this.content.isEmpty()) {
            throw new IllegalStateException();
        }
        if (
            System.currentTimeMillis() - writingTimer < CAUSE_LIMIT 
            &&
            Death.getCause((Death) this.content.get(this.content.size() - 1)).isEmpty()
            ) {
            Death.setCause((Death) this.content.getLast(), cause);

            return true;
        } else {
            Death.setCause((Death) this.content.getLast(), BASE_CAUSE);
        }

        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean writeDetails(final String details) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'writeDetails'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDeathCause(final String name) {
        for (final var d : this.content) {
            if (d.equals(name)) {
                return Death.getCause((Death) d);
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     * {@inheritDoc} 
     */
    @Override
    public String getDeathDetails(final String name) {
        for (final var d : this.content) {
            if (d.equals(name)) {
                return Death.getDetails((Death) d);
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isNameWritten(final String name) {
        for (final Object d : this.content) {
            if (d.equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override 
    public String toString() {
        String output = "";

        for (final var s : this.content) {
            output = output.concat(s.toString());
        }

        return output;
    }

    private class Death {
        private final String name;
        private String cause;
        private String details;

        Death(final String name) {
            this.name = name;
            this.cause = "";
            this.details = "";
        }

        static void setCause(final Death d, final String cause) {
            d.cause = cause;
        }

        static void setDetails(final Death d, final String details) {
            d.details = details;
        }

        public static String getCause(final Death d) {
            return d.cause;
        }

        public static String getDetails(final Death d) {
            return d.cause;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((name == null) ? 0 : name.hashCode());

            return result;
        }

        @Override
        public boolean equals(final Object obj) {
            return obj != null && this.name.equals(obj.toString());
        }

        @Override
        public String toString() {
            return this.name.concat(" ") + this.cause.concat(" ") + this.details.concat(".");
        }
    }
}
