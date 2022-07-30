package com.example.application;

import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;


public interface IBaseGpsListner extends LocationListener, GpsStatus.Listener {

public void onLocationChanged(Location location);
    public void onProviderDisabled(String location);
    public void onProviderEnabled(String provider);
    public void onStatusChanged(String provider, int status, Bundle extra);
    public void onGpsStatusChanged(int event);

}
