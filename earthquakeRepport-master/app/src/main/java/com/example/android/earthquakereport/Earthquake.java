package com.example.android.earthquakereport;

class Earthquake {

    /**
     * Magnitude of the earthquake
     */
    private double mMagnitude;
    /**
     * Location of the earthquake
     */
    private String mLocation;
    /**
     * time of the earthquake
     */
    private long mTimeInMilliseconds;
    /**
     * Website URL of the earthquake
     */
    private String mUrl;


    /**
     * Constructs a new {@link Earthquake} object.
     *
     * @param magnitude          is the magnitude (size) of the earthquake
     * @param location           is the location where the earthquake happened
     * @param timeInMilliseconds is the time in milliseconds (from the Epoch) when the
     *                           earthquake happened
     * @param url                is the website URL to find information about earthquakes
     */
    Earthquake(double magnitude, String location, long timeInMilliseconds, String url) {
        this.mMagnitude = magnitude;
        this.mLocation = location;
        this.mTimeInMilliseconds = timeInMilliseconds;
        this.mUrl = url;
    }

    /**
     * @return the magnitude of the earthquake
     */
    double getMagnitude() {
        return mMagnitude;
    }

    /**
     * @return the location of the earthquake
     */
    String getLocation() {
        return mLocation;
    }

    /**
     * @return the time in milliseconds
     */
    long getTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }

    /**
     * @return the website URL to find information about earthquakes
     */
    String getUrl() {
        return mUrl;
    }

}
