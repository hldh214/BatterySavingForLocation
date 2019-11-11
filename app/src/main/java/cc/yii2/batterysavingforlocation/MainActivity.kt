package cc.yii2.batterysavingforlocation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getLocationMode()
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.button_battery_saving -> batterySaving()
            R.id.button_high_accuracy -> highAccuracy()
        }

        // https://github.com/RichyHBM/Monochromatic/wiki/Enabling-WRITE_SECURE_SETTINGS-permission
        // https://android.stackexchange.com/a/212866/259952
        Toast.makeText(this, "done", Toast.LENGTH_LONG).show()
    }

    private fun batterySaving() {
        Settings.Secure.putInt(
            contentResolver,
            Settings.Secure.LOCATION_MODE,
            Settings.Secure.LOCATION_MODE_BATTERY_SAVING
        )

        getLocationMode()
    }

    private fun highAccuracy() {
        Settings.Secure.putInt(
            contentResolver,
            Settings.Secure.LOCATION_MODE,
            Settings.Secure.LOCATION_MODE_HIGH_ACCURACY
        )

        getLocationMode()
    }

    private fun getLocationMode() {
        val locationMode = Settings.Secure.getInt(contentResolver, Settings.Secure.LOCATION_MODE)
        val locationModeString: String

        locationModeString = when (locationMode) {
            Settings.Secure.LOCATION_MODE_BATTERY_SAVING -> "BATTERY_SAVING"
            Settings.Secure.LOCATION_MODE_HIGH_ACCURACY -> "HIGH_ACCURACY"
            else -> "Unknown: $locationMode"
        }

        textView.text = locationModeString
    }
}
