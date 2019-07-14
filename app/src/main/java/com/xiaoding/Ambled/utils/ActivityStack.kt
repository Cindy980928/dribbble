package com.xiaoding.Ambled.utils

import android.app.Activity
import java.util.*

class ActivityStack {

    private val mActivityArray = LinkedList<Activity>()

    val countActivity: Int
        @Synchronized get() = mActivityArray.size

    val isEmpty: Boolean
        @Synchronized get() = mActivityArray.size == 0

    @Synchronized
    fun push(a: Activity) {
        if (!mActivityArray.contains(a)) {
            mActivityArray.addFirst(a)
        }
    }

    @Synchronized
    fun remove(a: Activity) {
        mActivityArray.remove(a)
    }


    @Synchronized
    fun pop(): Activity {
        return mActivityArray.removeFirst()
    }

    @Synchronized
    operator fun hasNext(): Boolean {
        return mActivityArray.size > 0
    }

    @Synchronized
    fun finishAll() {
        while (hasNext()) {
            val a = pop()
            if (!a.isFinishing) {
                a.finish()
            }
        }
    }

    @Synchronized
    fun finishAllExpect(except: Activity) {
        while (hasNext()) {
            val a = pop()
            if (a !== except) {
                a.finish()
            }
        }
    }

}