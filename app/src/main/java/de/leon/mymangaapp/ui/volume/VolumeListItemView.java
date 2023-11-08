package de.leon.mymangaapp.ui.volume;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.time.format.DateTimeFormatter;

import de.leon.mymangaapp.R;
import de.leon.mymangaapp.model.Volume;

public class VolumeListItemView extends LinearLayout {

    TextView titleTextView;
    TextView releaseDateTextView;
    TextView subTextTextView;

    Button deleteButton;
    Button editButton;
    Button detailsButton;

    private Volume volume;

    public VolumeListItemView(Context context) {
        super(context);
        init(context);
    }

    public VolumeListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.view_volume_list_item, this);

        initComponents();


    }

    private void initComponents() {

        titleTextView = findViewById(R.id.volumeListItemTitleText);
        releaseDateTextView = findViewById(R.id.volumeListItemReleaseDate);
        subTextTextView = findViewById(R.id.volumeListItemSubText);

        deleteButton = findViewById(R.id.volumeListItemDeleteButton);
        editButton = findViewById(R.id.volumeListItemEditButton);
        detailsButton = findViewById(R.id.volumeListItemDetailsButton);
    }

    public void setVolume(String seriesName, Volume volume) {
        this.volume = volume;

        titleTextView.setText(seriesName);
        releaseDateTextView.setText(volume.getReleaseDate().format(DateTimeFormatter.ofPattern(getContext().getString(R.string.date_format))));
        subTextTextView.setText(volume.getVolumeOrNote());
    }

    public Volume getVolume() {
        return volume;
    }
}