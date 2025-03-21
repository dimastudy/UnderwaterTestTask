package com.justadev.underwaterworldtesttask.presentation.screen.splash

import com.justadev.underwaterworldtesttask.core.BaseViewModel


class SplashViewModel : BaseViewModel<SplashState, SplashEvent>(SplashState()) {
    override fun onEvent(intent: SplashEvent) {
        when (intent) {
            SplashEvent.EndSplash -> {
                updateState {
                    copy(splashEnded = true)
                }
            }
        }
    }


}