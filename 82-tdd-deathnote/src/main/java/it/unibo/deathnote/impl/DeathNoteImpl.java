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

    private Object Death(final String name) {
        class Death {
            private String name;
            private String cause;
            private String details;
            public Death(final String name) {
                this.name = name;
            }
            public void setCause(final String cause) {
                this.cause = cause;
            }
            public void setDetails(final String details) {
                this.details = details;
            }
            @Override
            public boolean equals(Object obj) {
                if (this.name.equals(obj)) {
                    return true;
                }
                return false;
            }
        }
        return new Death(name);
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

        if (name.equals(null) && name.isEmpty()) {
            throw new NullPointerException();
        }
        content.add(Death(name));        
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
        for (Object d : this.content) {
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
            
        }

        return output;
    }
}
