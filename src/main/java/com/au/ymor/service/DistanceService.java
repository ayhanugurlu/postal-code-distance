package com.au.ymor.service;

/**
 * Created by Ayhan.Ugurlu on 04/10/2018
 */
public interface DistanceService {

    /**
     * Calculate two geo point distance
     *
     * @param latitude
     * @param longitude
     * @param latitude2
     * @param longitude2
     * @return
     */
    double calculateDistance(double latitude, double longitude,
                             double latitude2, double longitude2);


    String getFormattedLocationInDegree(double latitude, double longitude);
}
