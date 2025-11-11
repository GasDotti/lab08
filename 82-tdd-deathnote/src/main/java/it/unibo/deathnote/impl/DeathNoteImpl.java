package it.unibo.deathnote.impl;

import it.unibo.deathnote.api.DeathNote;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that implements {@link DeathNote.java}.
 */
public class DeathNoteImpl implements DeathNote {

    private final List<Object> content; 

    /**
     * Base constructor, it initializes an empty DeathNote.
     */
    public DeathNoteImpl() {
        this.content = new ArrayList<>();
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
        content.add(new Death(name));
    }

    /**
     *  {@inheritDoc}
     */
    @Override
    public boolean writeDeathCause(final String cause) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'writeDeathCause'");
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDeathCause'");
    }

    /**
     * {@inheritDoc} 
     */
    @Override
    public String getDeathDetails(final String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDeathDetails'");
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
        }

        void setCause(final String cause) {
            this.cause = cause;
        }

        void setDetails(final String details) {
            this.details = details;
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
