package com.example.nasaexample.util

import android.content.Context
import android.content.SharedPreferences

class SharedPrefenceHelper(context: Context) {
    private val sharedPref: SharedPreferences =
        context.getSharedPreferences("CpanelSharedPreferences", Context.MODE_PRIVATE)

    fun savePinCode(pinCode: String) {
        sharedPref.edit().putString(PIN_CODE, pinCode).apply()
    }

    fun getPinCode(): String? {
        return sharedPref.getString(PIN_CODE, "1111")
    }
    companion object {
        private const val PIN_CODE = "pinCode"
    }
}