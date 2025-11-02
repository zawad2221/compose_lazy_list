package com.example.composelazylist.utils

import android.content.Context
import com.example.composelazylist.R
import com.example.composelazylist.data.network.ErrorType
import com.example.composelazylist.data.network.Resource
import com.example.composelazylist.data.network.UiComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class NetworkErrorHandling @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun <T> handleError(error: Any): Resource<T> {
        return when (error) {
            is Exception -> {
                Resource.Error(
                    uiComponent = UiComponent.Toast(
                        message = context.getString(R.string.something_went_wrong)
                    ),
                    type = ErrorType.UNKNOWN
                )
            }

            is Throwable -> {
                Resource.Error(
                    uiComponent = UiComponent.Toast(
                        message = context.getString(R.string.something_went_wrong)
                    ),
                    type = ErrorType.UNKNOWN
                )
            }

            is Int -> {
                when (error) {
                    401 -> {
                        Resource.Error(
                            uiComponent = UiComponent.Toast(
                                message = context.getString(R.string.login_again)
                            ),
                            type = ErrorType.SESSION_EXPIRED
                        )
                    }

                    else -> {
                        Resource.Error(
                            uiComponent = UiComponent.Toast(
                                message = context.getString(R.string.something_went_wrong)
                            ),
                            type = ErrorType.NETWORK
                        )
                    }
                }
            }

            else -> {
                Resource.Error(
                    uiComponent = UiComponent.Toast(
                        message = context.getString(R.string.something_went_wrong)
                    ),
                    type = ErrorType.UNKNOWN
                )
            }
        }
    }
}