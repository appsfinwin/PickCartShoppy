package com.finwin.pickcart.login.pojo

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    val `data`: Data
)

data class Data(
    @SerializedName("Table")
    val loginData: List<LoginData>
)

data class LoginData(
    val BranchId: Int,
    val CustId: Int,
    val Name: String,
    val ReturnId: Int,
    val ReturnMessage: String,
    val ReturnStatus: String
)