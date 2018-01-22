package com.example.android.earthquakereport;


import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    /**
     * The part of the location string from the USGS service that we use to determine
     * whether or not there is a location offset present ("5km N of Cairo, Egypt").
     */
    private static final String LOCATION_SEPARATOR = " of ";

    /**
     * Constructs a new {@link EarthquakeAdapter}
     *
     * @param context     of the app
     * @param earthquakes is the list of earthquakes, which is the data source of the adapter
     */
    EarthquakeAdapter(@NonNull Context context, List<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // Check if there is an existing list item view (called convertView) that we can reuse;
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item, parent, false);
        }

        // Find the earthquake at the given position in the list of earthquakes
        Earthquake currentEarthquake = getItem(position);

        assert currentEarthquake != null;
        // Find the TextView with the Id magnitude
        TextView magnitudeView = listItemView.findViewById(R.id.magnitude);
        // Format the magnitude to show one deciml place
        String formattedMagnitude = formatMagnitude(currentEarthquake.getMagnitude());
        // Display the magnitude of the current earthquake in that TextView
        magnitudeView.setText(formattedMagnitude);

        // Set the proper background color on the magnitude circle
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();
        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());
        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);


        // Get the original location string from the Earthquake Object
        String originalLocation = currentEarthquake.getLocation();

        // If the original location string contains primary location (San Francisco, CA) and
        // location offset ( 87 Km of..), than  split in two strings to be displayed into differents TextViews
        String primaryLocation;
        String locationOffSet;

        // Check whether the originalLocation string contains the " of " text
        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            // If contains, split in two different strings
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            // First string displays the offset with the SEPARATOR
            locationOffSet = parts[0] + LOCATION_SEPARATOR;
            // Second string with the location
            primaryLocation = parts[1];
        } else {
            // If there is no "of" text in the originalLocation, set the default locationOffSet to "Near By"
            locationOffSet = getContext().getString(R.string.near_the);
            // The primaryLocation will be the full location string
            primaryLocation = originalLocation;
        }

        // Find the TextView with the Id location of location offset
        TextView locationOffSetView = listItemView.findViewById(R.id.location_offset);
        // Display the location of the current earthquake in that TextView
        locationOffSetView.setText(locationOffSet);

        // Find the TextView with the Id location of primary location
        TextView primaryLocationView = listItemView.findViewById(R.id.primary_location);
        // Display the location of the current earthquake in that TextView
        primaryLocationView.setText(primaryLocation);

        // Create a new Date Object from the time in milliseconds of the earthquake
        Date dateObject = new Date(currentEarthquake.getTimeInMilliseconds());

        // Find the TextView with the Id date
        TextView dateView = listItemView.findViewById(R.id.date);
        // Format teh Date String
        String formattedDate = formatDate(dateObject);
        // Display the date of the current earthquake in that TextView
        dateView.setText(formattedDate);

        // Find the TextView with view ID time
        TextView timeView = listItemView.findViewById(R.id.time);
        // Format the time string (i.e. "4:30PM")
        String formattedTime = formatTime(dateObject);
        // Display the time of the current earthquake in that TextView
        timeView.setText(formattedTime);

        // Return the list item view that is now showing the appropriate data
        return listItemView;
    }

    /**
     * Return the formatted magnitude string showing 1 decimal place (i.e. "3.2")
     * from a decimal magnitude value.
     */
    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    /**
     * @param magnitude of the earthquake
     * @return the color for the magnitude circle based on the intensity of the earthquake
     */
    private int getMagnitudeColor(double magnitude) {
        int magnitudeResourceColorId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeResourceColorId = R.color.magnitude1;
                break;
            case 2:
                magnitudeResourceColorId = R.color.magnitude2;
                break;
            case 3:
                magnitudeResourceColorId = R.color.magnitude3;
                break;
            case 4:
                magnitudeResourceColorId = R.color.magnitude4;
                break;
            case 5:
                magnitudeResourceColorId = R.color.magnitude5;
                break;
            case 6:
                magnitudeResourceColorId = R.color.magnitude6;
                break;
            case 7:
                magnitudeResourceColorId = R.color.magnitude7;
                break;
            case 8:
                magnitudeResourceColorId = R.color.magnitude8;
                break;
            case 9:
                magnitudeResourceColorId = R.color.magnitude9;
                break;
            default:
                magnitudeResourceColorId = R.color.magnitude10plus;
                break;
        }

        return ContextCompat.getColor(getContext(), magnitudeResourceColorId);
    }

    /**
     * @return the formatted date string (i.e. Mar 3, 1984) from a Date Object
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * @return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }


}
