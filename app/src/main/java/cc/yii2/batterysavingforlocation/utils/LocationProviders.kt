package cc.yii2.batterysavingforlocation.utils

import android.content.ContentResolver
import android.provider.Settings

class LocationProviders(private var cr: ContentResolver) {
    private val batterySavingString = "+network,-gps"
    private val highAccuracyString = "+network,+gps"

    private fun putString(string: String): Boolean {
        return Settings.Secure.putString(
            this.cr,
            Settings.Secure.LOCATION_PROVIDERS_ALLOWED,
            string
        )
    }

    fun batterySaving() {
        this.putString(this.batterySavingString)
    }

    fun highAccuracy() {
        this.putString(this.highAccuracyString)
    }

    fun getLocationMode(): String {
        return Settings.Secure.getString(
            this.cr,
            Settings.Secure.LOCATION_PROVIDERS_ALLOWED
        )
    }
}