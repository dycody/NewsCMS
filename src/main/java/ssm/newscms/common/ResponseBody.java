/*
 * Copyright © 2015. Zhejiang Institute Of Public Security Technology Co., Ltd. All Rights Reserved.
 */

package ssm.newscms.common;

/**
 * @author renjc
 */
public enum ResponseBody {

    /**
     * Unknown error occurred
     */
    UNKONWN("Unknown error occurred"),

    /**
     * The request could not be understood by the server due to malformed
     * syntax.
     */
    BAD_REQUEST("Bad Request"),

    /**
     * The request requires user authentication.
     */
    UNAUTHORIZED("Unauthorized"),

    /**
     * The request attempting to authenticate with a principal that doesn't
     * exist in the system (e.g. by specifying a username that doesn't relate to
     * a user account).
     */
    UNKNOWN_ACCOUNT("Unknown Account"),

    /**
     * The request attempting to authenticate with credential(s) that do not
     * match the actual credentials associated with the account principal.
     */
    INCORRECT_CREDENTIALS("Incorrect Credentials"),

    /**
     * An error during the Authentication process. The server understood the
     * request, but is refusing to fulfill it.
     */
    FORBIDDEN("Forbidden");

    /**
     * response message
     */
    private final String msg;

    /**
     * default constructor
     *
     * @param msg response message
     */
    ResponseBody(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return msg;
    }
}