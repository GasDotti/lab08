package it.unibo.deathnote.impl;

import it.unibo.deathnote.api.DeathNote;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that implements {@link DeathNote.java}.
 */
public class DeathNoteImpl implements DeathNote {

    /**
     * Base constructor, it initializes an empty DeathNote.
     */
    public DeathNoteImpl() {
        
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'writeDeathName'");
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isNameWritten'");
    }
}
