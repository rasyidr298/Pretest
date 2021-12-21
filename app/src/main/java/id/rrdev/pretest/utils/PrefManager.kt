package id.rrdev.pretest.utils

import android.content.Context
import android.content.SharedPreferences

private const val PREFS_NAME = "app_name"

class PrefManager(context: Context) {

    private val sp: SharedPreferences by lazy {
        context.applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    val spe: SharedPreferences.Editor by lazy {
        sp.edit()
    }

    fun clearAllPref() {
//        sp.edit().remove(PREF_AUTH_APP).apply()
    }

//    fun saveAuthData(list: AuthData) {
//        val gson = Gson()
//        val json: String = gson.toJson(list)
//        spe.putString(PREF_AUTH_APP, json)
//        spe.apply()
//    }
//
//    fun getAuthData(): AuthData? {
//        val gson = Gson()
//        val json: String? = sp.getString(PREF_AUTH_APP, "")
//        return gson.fromJson(json, AuthData::class.java)
//    }

    var spToken: String?
        get() = sp.getString("spToken", "")
        set(value) {
            spe.putString("spToken", value)
            spe.apply()
        }

//    var spAuthPhone: Boolean
//        get() = sp.getBoolean(PREF_AUTH_PHONE, false)
//        set(value) {
//            spe.putBoolean(PREF_AUTH_PHONE, value)
//            spe.apply()
//        }
}
