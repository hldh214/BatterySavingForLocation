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
        Settings.Secure.putString(
            contentResolver,
            Settings.Secure.LOCATION_PROVIDERS_ALLOWED,
            "+network,-gps"
        )

        getLocationMode()
    }

    private fun highAccuracy() {
        Settings.Secure.putString(
            contentResolver,
            Settings.Secure.LOCATION_PROVIDERS_ALLOWED,
            "+network,+gps"
        )

        getLocationMode()
    }

    private fun getLocationMode() {
        val locationMode = Settings.Secure.getString(
            contentResolver,
            Settings.Secure.LOCATION_PROVIDERS_ALLOWED
        )

        textView.text = locationMode
    }
}
