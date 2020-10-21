public class Geo {
    //https://stackoverflow.com/questions/27928/calculate-distance-between-two-latitude-longitude-points-haversine-formula
    //https://keisan.casio.com/exec/system/1224587128
    public static double distance(Location location1,Location location2){
        return (haversine(location1.getLatitude(),location1.getLongitude(),location2.getLatitude(),location2.getLongitude()));
    }

    //NB Haversine presumes a perfect spheroid, which the earth is not.
    //https://www.geeksforgeeks.org/haversine-formula-to-find-distance-between-two-points-on-a-sphere/
    static double haversine(double lat1, double lon1,
                            double lat2, double lon2)
    {
        // distance between latitudes and longitudes
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        // convert to radians
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        // apply formulae
        double a = Math.pow(Math.sin(dLat / 2), 2) +
                Math.pow(Math.sin(dLon / 2), 2) *
                        Math.cos(lat1) *
                        Math.cos(lat2);
        double rad = 6378.137;//Source keisan.casio.com. Org. 6371
        double c = 2 * Math.asin(Math.sqrt(a));
        return rad * c;
    }

}



