package cc.yii2.batterysavingforlocation.bg

import android.service.quicksettings.Tile
import cc.yii2.batterysavingforlocation.utils.LocationProviders
import android.service.quicksettings.TileService as BaseTileService

class TileService : BaseTileService() {
    override fun onClick() {
        super.onClick()

        val locationProvider = LocationProviders(this)

        when (locationProvider.getLocationMode()) {
            locationProvider.highAccuracyMode -> locationProvider.batterySaving()
            locationProvider.batterSavingMode -> locationProvider.highAccuracy()
        }

        this.stateChange()
    }

    override fun onStartListening() {
        super.onStartListening()

        this.stateChange()
    }

    override fun onStopListening() {
        super.onStopListening()

        qsTile.state = Tile.STATE_UNAVAILABLE
        qsTile.updateTile()
    }

    private fun stateChange() {
        val locationProvider = LocationProviders(this)

        qsTile.state = when (locationProvider.getLocationMode()) {
            locationProvider.highAccuracyMode -> Tile.STATE_ACTIVE
            locationProvider.batterSavingMode -> Tile.STATE_INACTIVE
            else -> Tile.STATE_UNAVAILABLE
        }

        qsTile.label = when (locationProvider.getLocationMode()) {
            locationProvider.highAccuracyMode -> "highAccuracy"
            locationProvider.batterSavingMode -> "batterSaving"
            else -> "???"
        }

        qsTile.updateTile()
    }
}
