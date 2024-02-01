package com.example.livestreamingagora

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import org.json.JSONObject
import java.nio.charset.StandardCharsets
import java.util.Base64


val TOKEN = ""
  const val BASE_URL = "https://ott.durbar.live/api/v1/app/"
const val BASE_URL_NOTIFICATION = "https://fcm.googleapis.com/"
const val SERVICE_KEY_NOTIFICATION = "key=AAAAQZFuQZc:APA91bEH_bFR0bf9OpPwyhRtOAvJ9twhWpTFoLXR6rVfQiyLs_FmkUDjn9DxqJd9hIfN7FE3q7QqSUBb3W1ALL-iUuHJAeliAf5GzAKc_fw36hMoUllPrqawZxEVvonAgoUI41Fxhgpg"

//const val BASE_URL = "http://159.223.86.243/api/v1/app/"

//https://ott.durbar.live/api/v1/app/phone-register
/*val appId = "71e0f92db4ca450c9443831e9d24cb2c"
val appCertificate = "c58a0a6c8cf2419e886f4075b5e36e7f"
val channelName = "nur_hossain"*/
val appId = "71e0f92db4ca450c9443831e9d24cb2c"
//val appId = "897cde00252a4d88994cf54f6a90e6f4"
const val CHANNEL_NAME = "durbar"
//const val CHANNEL_NAME = "nur"
val appCertificate = "c58a0a6c8cf2419e886f4075b5e36e7f"
//val appCertificate = "192136d7b6224f40a1f9403a30b14cb4"
val uid = 0

const val MY_PREF_NAME = "OTT_SHARED_PREF"
const val ACCESS_TOKEN = "ACCESS_TOKEN"
const val AGORA_TOKEN = "AGORA_TOKEN"
const val TITLE = "TITLE"

private var editor: SharedPreferences.Editor? = null
private var prefs: SharedPreferences? = null

fun setEditor(context: Context, key: String?, value: String?){
    if (editor == null){
        editor = context.getSharedPreferences(MY_PREF_NAME,Context.MODE_PRIVATE).edit()
    }
    editor?.putString(key, value)
    editor?.apply()
}

fun getSharedPref(context: Context, key: String?): String? {
    if (prefs == null){
        prefs = context.getSharedPreferences(MY_PREF_NAME,Context.MODE_PRIVATE)
    }
    return prefs?.getString(key,null)
}




