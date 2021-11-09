package com.serranoie.data.crypteck.errors

object ErrorCodes {

    /**
     *
     * Any number > 300 and < 600
     * is an http error. Internal app
     * errors should be negative to
     * prevent collision.
     *
     * */

    const val OK_BUT_INFO_NOT_AVAILABLE = -11
    const val EXCEPTION_ON_REQUEST = -12
    const val EMPTY_RESULT = -13
    const val TAG_NOT_FOUND = -14
}