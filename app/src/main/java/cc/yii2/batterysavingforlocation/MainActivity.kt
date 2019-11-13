package cc.yii2.batterysavingforlocation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import cc.yii2.batterysavingforlocation.utils.LocationProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.enableButton()
        this.getLocationMode()
    }

    fun onClick(view: View) {
        val locationProvider = LocationProviders(this)

        val result = when (view.id) {
            R.id.button_battery_saving -> locationProvider.batterySaving()
            R.id.button_high_accuracy -> locationProvider.highAccuracy()
            else -> false
        }

        if (!result) {
            return
        }

        this.enableButton()

        // https://github.com/RichyHBM/Monochromatic/wiki/Enabling-WRITE_SECURE_SETTINGS-permission
        // https://android.stackexchange.com/a/212866/259952
        // adb shell pm grant cc.yii2.batterysavingforlocation android.permission.WRITE_SECURE_SETTINGS
        // adb shell dumpsys package cc.yii2.batterysavingforlocation | grep WRITE_SECURE_SETTINGS
        Toast.makeText(this, "done", Toast.LENGTH_LONG).show()
    }

    private fun getLocationMode() {
        val locationProvider = LocationProviders(this)
        val locationMode = locationProvider.getLocationMode()

        textView.text = locationMode
    }

    private fun enableButton() {
        val locationProvider = LocationProviders(this)

        when (locationProvider.getLocationMode()) {
            locationProvider.batterSavingMode -> {
                button_battery_saving.isEnabled = false
                button_high_accuracy.isEnabled = true
            }
            locationProvider.highAccuracyMode -> {
                button_battery_saving.isEnabled = true
                button_high_accuracy.isEnabled = false
            }
        }
    }
}
