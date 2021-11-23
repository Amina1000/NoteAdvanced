package com.cocos.develop.noteadvanced.rx

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * homework com.cocos.develop.coshub.rx
 *
 * @author Amina
 * 14.10.2021
 */
class SchedulerProvider {
    fun ui(): Scheduler = AndroidSchedulers.mainThread()
    fun io(): Scheduler = Schedulers.io()
}