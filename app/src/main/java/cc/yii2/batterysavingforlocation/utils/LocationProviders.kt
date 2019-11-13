package cc.yii2.batterysavingforlocation.utils

import android.content.ContentResolver
import android.content.Context
import android.provider.Settings
import android.widget.Toast

class LocationProviders(private var context: Context) {
    private var cr: ContentResolver = context.contentResolver

    val batterSavingMode = "network"
    val highAccuracyMode = "gps,network"

    private val batterySavingString = "+network,-gps"
    private val highAccuracyString = "+network,+gps"

    @Suppress("deprecation")
    private fun putString(string: String): Boolean {
        return try {
            Settings.Secure.putString(
                this.cr,
                Settings.Secure.LOCATION_PROVIDERS_ALLOWED,
                string
            )
        } catch (exception: SecurityException) {
            Toast.makeText(this.context, exception.toString(), Toast.LENGTH_LONG).show()
            false
        }
    }

    fun batterySaving(): Boolean {
        return this.putString(this.batterySavingString)
    }

    fun highAccuracy(): Boolean {
        return this.putString(this.highAccuracyString)
    }

    @Suppress("deprecation")
    fun getLocationMode(): String {
        return Settings.Secure.getString(
            this.cr,
            Settings.Secure.LOCATION_PROVIDERS_ALLOWED
        )
    }
}