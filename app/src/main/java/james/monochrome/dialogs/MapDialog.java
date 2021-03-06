package james.monochrome.dialogs;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import james.monochrome.R;
import james.monochrome.adapters.MapTileAdapter;
import james.monochrome.data.PositionData;
import james.monochrome.utils.MapUtils;
import jp.wasabeef.blurry.Blurry;

public class MapDialog extends AppCompatDialog {

    private String mapKey;
    private PositionData position;
    private Blurry.ImageComposer image;

    public MapDialog(Context context, String mapKey, PositionData position, Blurry.ImageComposer image) {
        super(context, R.style.AppTheme_Dialog_FullScreen_Fading);
        this.mapKey = mapKey;
        this.position = position;
        this.image = image;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_map);

        if (mapKey == null || position == null) return;

        ImageView background = (ImageView) findViewById(R.id.background);
        image.into(background);
        background.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) dismiss();
                return false;
            }
        });

        int[][][][] map = MapUtils.getMap(getContext(), mapKey);
        int lengthY = map.length, lengthX = map[0].length;

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), lengthX));
        recyclerView.setAdapter(new MapTileAdapter(getContext(), position, lengthX, lengthY));
    }
}
