package com.finwin.pickcart.sign_up

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.finwin.pickcart.login.action.LoginAction
import com.finwin.pickcart.retrofit.ApiInterface
import com.finwin.pickcart.sign_up.action.SignUpAction
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.RequestBody

class SignUpRepository {
    lateinit var INSTANCE: SignUpRepository
   lateinit var mAction: MutableLiveData<SignUpAction>
    public fun getInstance(): SignUpRepository
    {
        if (!::INSTANCE.isInitialized)
        {
            INSTANCE= SignUpRepository()
        }
        return INSTANCE
    }
    @SuppressLint("CheckResult")
    fun signUp(apiInterface: ApiInterface, body: RequestBody?) {
        val observable = apiInterface.signUp(body)
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    if (response.data.signUpData !=null) {
                        if (response.data.signUpData[0].ReturnStatus.equals("Y")){
                        mAction.value = SignUpAction(
                            SignUpAction.SIGN_UP_SUCCESS,
                            response
                        )}else{
                            mAction.value = SignUpAction(
                                SignUpAction.API_ERROR,
                                (response.data.signUpData[0].ReturnMessage
                                        ))
                        }
                    } else {

                        mAction.value = SignUpAction(
                            SignUpAction.API_ERROR,
                            (response.data.signUpData[0].ReturnMessage
                        ))
                    }
                }, { error ->
                    mAction.value =
                        SignUpAction(
                            SignUpAction.API_ERROR,
                            error.message.toString()
                        )
                }
            )

    }

    @SuppressLint("CheckResult")
    fun getBranches(apiInterface: ApiInterface, body: RequestBody?) {
        val observable = apiInterface.getBranches(body)
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    if (response.data.branchData !=null) {
                        mAction.value = SignUpAction(
                            SignUpAction.GET_BRANCH_SUCCESS,
                            response
                        )
                    } else {

                        mAction.value = SignUpAction(
                            SignUpAction.API_ERROR,
                            response
                        )
                    }
                }, { error ->
                    mAction.value =
                        SignUpAction(
                            SignUpAction.API_ERROR,
                            error.message.toString()
                        )
                }
            )
    }

    @SuppressLint("CheckResult")
    fun login(apiInterface: ApiInterface, body: RequestBody?) {
        val observable = apiInterface.login(body)
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    if (response.data.loginData.size>0) {
                        if (response.data.loginData[0].ReturnStatus.equals("Y")) {
                            mAction.value = SignUpAction(
                                SignUpAction.LOGIN_SUCCESS,
                                response
                            )
                        } else {

                            mAction.value = SignUpAction(
                                SignUpAction.API_ERROR,
                                "Please try again"
                            )
                        }
                    }
                }, { error ->
                    mAction.value =
                        SignUpAction(
                            SignUpAction.API_ERROR,
                            error.message.toString()
                        )
                }
            )

    }
}