/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2013
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package com.ericsson.oss.itpf.datalayer.ddl;

public class HiveName {

    private static final String VALID_NAME_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_";

    private String name;

    /**
     * @param name
     * @throws IllegalArgumentException
     */
    public HiveName(final String name) {
        super();
        setName(name);
    }

    private boolean isValidName(final String name) {
        if (name == null || name.isEmpty()) {
            return false;
        } else {
            return charsInNameAreValid(name);
        }
    }

    private boolean charsInNameAreValid(final String name) {
        for (int idx = 0; idx < name.length(); idx++) {
            if (isInvalidNameChar(name.charAt(idx))) {
                return false;
            }
        }

        return true;
    }

    private boolean isInvalidNameChar(final char charToTest) {
        return VALID_NAME_CHARACTERS.indexOf(charToTest, 0) < 0;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(final String name) {
        if (isValidName(name)) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Name [" + name + "] contains invalid character(s).");
        }
    }

}
