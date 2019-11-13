package cc.yii2.batterysavingforlocation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import cc.yii2.batterysavingforlocation.utils.LocationProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var locationProvider: LocationProviders = LocationProviders(contentResolver)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getLocationMode()
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.button_battery_saving -> this.locationProvider.batterySaving()
            R.id.button_high_accuracy -> this.locationProvider.highAccuracy()
        }

        // https://github.com/RichyHBM/Monochromatic/wiki/Enabling-WRITE_SECURE_SETTINGS-permission
        // https://android.stackexchange.com/a/212866/259952
        Toast.makeText(this, "done", Toast.LENGTH_LONG).show()
    }

    private fun getLocationMode() {
        val locationMode = this.locationProvider.getLocationMode()

        textView.text = locationMode
    }
}
