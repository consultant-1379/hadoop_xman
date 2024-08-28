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
package ericsson.hadoop.hive.table;

public class HiveName {

    private static final String VALID_NAME_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_";

    private String name;

    /**
     * @param name
     * @throws IllegalArgumentException
     */
    public HiveName(final String name) throws IllegalArgumentException {
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
        int idx = 0;

        while (idx < name.length()) {
            if (isValidNameChar(name.charAt(idx))) {
                idx++;
            } else {
                return false;
            }
        }

        return true;
    }

    private boolean isValidNameChar(final char charToTest) {
        return VALID_NAME_CHARACTERS.indexOf(charToTest, 0) >= 0;
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
     * @throws IllegalArgumentException
     */
    public void setName(final String name) throws IllegalArgumentException {
        if (isValidName(name)) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Name [" + name + "] contains invalid character(s).");
        }
    }

}
