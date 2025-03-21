package com.justadev.underwaterworldtesttask.core.utils

import android.app.Activity
import android.os.Process

fun Activity.quitGame() {
    finishAffinity()
    Process.killProcess(Process.myPid())
}