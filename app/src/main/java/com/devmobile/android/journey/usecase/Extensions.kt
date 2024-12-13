package com.devmobile.android.journey.usecase

import android.os.Handler.Callback
import androidx.lifecycle.Observer

inline fun <T, R>debounce(duration: Long) where T: Callback, T: Observer<R>, T: Any, T: () -> R {

}
