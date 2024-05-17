package com.hotelmanager.service;

import org.springframework.stereotype.Service;

@Service
public class DistanceService {

    private static final int earthRadius = 6371;

    private static float[] toCartesian(double lat, double lon)
    {
        float latRad = (float)Math.toRadians(lat);
        float lonRad = (float)Math.toRadians(lon);
        float x = (float)(earthRadius * lonRad * Math.cos(latRad));
        float y = (float)(earthRadius * latRad);
        return new float[] {x, y};
    }

    public static int distance(float lat1, float lon1, float lat2, float lon2)
    {
        float[] userPos = toCartesian(lat1, lon1);
        float[] hotelPos = toCartesian(lat2, lon2);

        float deltaX = userPos[0] - hotelPos[0];
        float deltaY = userPos[1] - hotelPos[1];

        float distance = (float)(Math.sqrt(deltaX*deltaX +deltaY *deltaY));
        return (int)Math.floor(distance);
    }

}
