package james.monochrome.data.tiles;

import android.content.Context;

import james.monochrome.data.PositionData;
import james.monochrome.utils.TileUtils;

public class EmptyTileData extends TileData {

    public EmptyTileData(Context context, PositionData position) {
        super(context, TileUtils.TILE_EMPTY, position);
    }

    @Override
    public void onTouch() {
    }

    @Override
    public void onEnter() {
    }

    @Override
    public void onExit() {
    }

    @Override
    public boolean canEnter() {
        return true;
    }
}
