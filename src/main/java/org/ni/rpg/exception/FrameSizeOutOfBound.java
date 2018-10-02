package org.ni.rpg.exception;

import org.ni.rpg.utils.Constants;

/**
 * Created by nazmul on 9/29/2018.
 */
public class FrameSizeOutOfBound extends Exception {
    public FrameSizeOutOfBound() {
        super(Constants.messageFrameSizeOutOfBound);
    }

    public FrameSizeOutOfBound(String s) {
        super(s);
    }
}
