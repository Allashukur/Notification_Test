import android.content.Context
import android.content.SharedPreferences

object Preferences {
    lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences("1BOOK", Context.MODE_PRIVATE)
    }

    fun setPreference(preferences: SharedPreferences) {
        Preferences.preferences = preferences
    }

    var token: String
        get() = preferences.getString(::token.name, "")!!
        set(value) {
            preferences.edit().putString(::token.name, value).apply()
        }

    fun clearAll() {
        preferences.edit().clear().apply()
    }
}