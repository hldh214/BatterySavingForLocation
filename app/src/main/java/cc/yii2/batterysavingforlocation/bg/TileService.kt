package cc.yii2.batterysavingforlocation.bg

import android.service.quicksettings.Tile
import cc.yii2.batterysavingforlocation.utils.LocationProviders
import android.service.quicksettings.TileService as BaseTileService

class TileService : BaseTileService() {
    private var locationProvider: LocationProviders = LocationProviders(this)

    override fun onClick() {
        super.onClick()

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
        qsTile.state = when (this.locationProvider.getLocationMode()) {
            this.locationProvider.highAccuracyMode -> Tile.STATE_ACTIVE
            this.locationProvider.batterSavingMode -> Tile.STATE_INACTIVE
            else -> Tile.STATE_UNAVAILABLE
        }

        qsTile.updateTile()
    }
}
