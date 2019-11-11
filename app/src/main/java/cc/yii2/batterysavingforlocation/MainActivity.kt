package cc.yii2.batterysavingforlocation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun batterySaving() {
        Settings.Secure.putInt(
            contentResolver,
            Settings.Secure.LOCATION_MODE,
            Settings.Secure.LOCATION_MODE_BATTERY_SAVING
        )

        // https://github.com/RichyHBM/Monochromatic/wiki/Enabling-WRITE_SECURE_SETTINGS-permission
        // https://android.stackexchange.com/a/212866/259952
        Toast.makeText(this, "done?", Toast.LENGTH_SHORT).show()
    }
}
