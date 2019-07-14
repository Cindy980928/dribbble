package com.xiaoding.Ambled.utils

import android.util.Log
import com.xiaoding.Ambled.BuildConfig

object Logger {

    private val TAG = "Cloud"
    val debug = BuildConfig.DEBUG

    private val LOG_MAX_SIZE = 500

    /**
     * log.i
     */
    fun i(msg: String) {
        if (debug) {
            val msgs = divideMsg(msg)
            for (str in msgs) {
                Log.d(TAG, str)
            }
        }
    }

    fun i(tag: String, msg: String) {
        if (debug) {
            val msgs = divideMsg(msg)
            for (str in msgs) {
                Log.i(tag, str)
            }
        }
    }


    fun d(msg: String) {
        if (debug) {
            val msgs = divideMsg(msg)
            for (str in msgs) {
                Log.d(TAG, str)
            }
        }
    }

    private fun divideMsg(msg: String): Array<String?> {
        if (msg.length > LOG_MAX_SIZE) {
            val size = msg.length / LOG_MAX_SIZE
            val left = msg.length % LOG_MAX_SIZE
            var length = size
            if (left > 0) {
                length += 1
            }
            val result = arrayOfNulls<String>(length)
            for (i in 0 until length - 1) {
                result[i] = msg.substring(i * LOG_MAX_SIZE, (i + 1) * LOG_MAX_SIZE)
            }
            result[length - 1] = msg.substring((length - 1) * LOG_MAX_SIZE)
            return result
        }
        return arrayOf(msg)
    }

    fun d(tag: String, msg: String) {
        if (debug) {
            val msgs = divideMsg(msg)
            for (str in msgs) {
                Log.d(tag, str)
            }
        }
    }

    fun e(msg: String) {
        if (debug) {
            val msgs = divideMsg(msg)
            for (str in msgs) {
                Log.e(TAG, str)
            }
        }
    }

    fun e(tag: String, msg: String) {
        if (debug) {
            val msgs = divideMsg(msg)
            for (str in msgs) {
                Log.d(tag, str)
            }
        }
    }
}

