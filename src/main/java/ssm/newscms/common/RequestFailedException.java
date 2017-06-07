/*
 * Copyright © 2015. Zhejiang Institute Of Public Security Technology Co., Ltd. All Rights Reserved.
 */

package ssm.newscms.common;

/**
 * Pre-defined (default) exception used to handle business exceptions
 * <p/>
 * Created by jiachenren on 2015-6-19.
 */
public class RequestFailedException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -686048624757623400L;

	/**
     * default constructor
     *
     * @param message message
     */
    public RequestFailedException(String message) {
        super(message);
    }
}
