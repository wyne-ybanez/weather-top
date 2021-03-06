package utils;

import models.Reading;
import models.Station;
import play.Logger;

import java.lang.Math;
import java.util.HashMap;

/**
 *  Convert temp, wind and pressure.
 *  Undergoes conversions and returns converted values.
 *  Import to StationCtrl.
 */
public class Conversions {

    /**
     *  Converts reading code to a specified weather value.
     *
     *  @return weather value as a String
     */
    public static String convertCodeToWeather(int weatherCode) {
        String weather = "";
        // Convert Code to weather
        while (weatherCode != 0) {
            switch (weatherCode) {
                case 100:
                    weather = "Clear";
                    break;
                case 200:
                    weather = "Partial clouds";
                    break;
                case 300:
                    weather = "Cloudy";
                    break;
                case 400:
                    weather = "Light Showers";
                    break;
                case 500:
                    weather = "Heavy Showers";
                    break;
                case 600:
                    weather = "Rain";
                    break;
                case 700:
                    weather = "Snow";
                    break;
                case 800:
                    weather = "Thunder";
                    break;
                default:
                    System.out.println("Invalid code input.");
                    Logger.error("Invalid code input");
                    break;
            }
            return weather;
        }
        return weather;
    }

    /**
     *  Converts Celcius to Farenheit
     *
     *  @return double value for Farenheit
     */
    public static double convertToFahrenheit(double tempInCelcius)
    {
        // Formula for converting celcius to fahrenheit = ( X * 1.8 ) + 32
        double fahrenheitValue = (tempInCelcius * 1.8) + 32;
        return fahrenheitValue;
    }

    /**
     *  kM/hr to Beaufort Conversion
     *
     *  @return int for Beaufort value
     */
    public static int convertToBeaufort(double windSpeed)
    {
        int BeaufortValue;
        HashMap<Integer, String> BeaufortLabel = new HashMap<Integer, String>();

        if (windSpeed == 1.0) {
            BeaufortValue = 0;
            BeaufortLabel.put(BeaufortValue, "Calm");
        }
        else if ((windSpeed > 1.0) && (windSpeed <= 5.0)) {
            BeaufortValue = 1;
            BeaufortLabel.put(BeaufortValue,"Light Air");
        }
        else if ((windSpeed >= 6.0) && (windSpeed <= 11.0)) {
            BeaufortValue = 2;
            BeaufortLabel.put(BeaufortValue,"Light Breeze");
        }
        else if ((windSpeed >= 12.0) && (windSpeed <= 19.0)) {
            BeaufortValue = 3;
            BeaufortLabel.put(BeaufortValue,"Gentle Breeze");
        }
        else if ((windSpeed >= 20.0) && (windSpeed <= 28.0)) {
            BeaufortValue = 4;
            BeaufortLabel.put(BeaufortValue,"Moderate Breeze");
        }
        else if ((windSpeed >= 29.0) && (windSpeed <= 38.0)) {
            BeaufortValue = 5;
            BeaufortLabel.put(BeaufortValue,"Fresh Breeze");
        }
        else if ((windSpeed >= 39.0) && (windSpeed <= 49.0)) {
            BeaufortValue = 6;
            BeaufortLabel.put(BeaufortValue,"Strong Breeze");
        }
        else if ((windSpeed >= 50.0) && (windSpeed <= 61.0)) {
            BeaufortValue = 7;
            BeaufortLabel.put(BeaufortValue,"Near Gale");
        }
        else if ((windSpeed >= 62.0) && (windSpeed <= 74.0)) {
            BeaufortValue = 8;
            BeaufortLabel.put(BeaufortValue,"Gale");
        }
        else if ((windSpeed >= 75.0) && (windSpeed <= 88.0)) {
            BeaufortValue = 9;
            BeaufortLabel.put(BeaufortValue,"Severe Gale");
        }
        else if ((windSpeed >= 89.0) && (windSpeed <= 102.0)) {
            BeaufortValue = 10;
            BeaufortLabel.put(BeaufortValue,"Strong Storm");
        }
        else if ((windSpeed >= 103) && (windSpeed <= 117)) {
            BeaufortValue = 11;
            BeaufortLabel.put(BeaufortValue,"Violent Storm");
        }
        else {
            /*
             Assuming that anything beyond 117km/hr is a violent storm,
             hence the value defaults at 11 bft.
             */
            BeaufortValue = 11;
            System.out.println("Invalid wind speed input.");
            Logger.error("Invalid wind speed input.");
        }
    return BeaufortValue;
    }

    /**
     *  Wind Direction compass.
     *  Converts wind degree range to compass direction.
     *
     *  @return String value for the Compass direction.
     */
    public static String convertToCompassDirection(double windDirection)
    {
        String compassDirection;

        if ((windDirection > 348.75) && (windDirection < 11.25)) {
            compassDirection = "North";
        }
        else if ((windDirection > 11.25) && (windDirection < 33.75)) {
            compassDirection = "North East";
        }
        else if ((windDirection > 33.75) && (windDirection < 56.25)) {
            compassDirection = "North North East";
        }
        else if ((windDirection > 56.25) && (windDirection < 78.75)) {
            compassDirection = "East North East";
        }
        else if ((windDirection > 78.75) && (windDirection < 101.25)) {
            compassDirection = "East";
        }
        else if ((windDirection > 101.25) && (windDirection < 123.75)) {
            compassDirection = "East South East";
        }
        else if ((windDirection > 123.75) && (windDirection < 146.25)) {
            compassDirection = "South East";
        }
        else if ((windDirection > 146.25) && (windDirection < 168.75)) {
            compassDirection = "South South East";
        }
        else if ((windDirection > 168.75) && (windDirection < 191.25)) {
            compassDirection = "South";
        }
        else if ((windDirection > 191.25) && (windDirection < 213.75)) {
            compassDirection = "South South West";
        }
        else if ((windDirection > 213.75) && (windDirection < 236.25)) {
            compassDirection = "South West";
        }
        else if ((windDirection > 236.25) && (windDirection < 258.75)) {
            compassDirection = "West South West";
        }
        else if ((windDirection > 258.75) && (windDirection < 281.25)) {
            compassDirection = "West";
        }
        else if ((windDirection > 281.25) && (windDirection < 303.75)) {
            compassDirection = "West North West";
        }
        else if ((windDirection > 303.75) && (windDirection < 326.25)) {
            compassDirection = "North West";
        }
        else if ((windDirection > 326.25) && (windDirection < 348.75)) {
            compassDirection = "North North West";
        }
        // Default value is North
        else {
            compassDirection = "North";
        }
        return compassDirection;
    }

    /**
     *  Wind Chill Calculator.
     *  Takes wind direction value from reading.
     *
     *  @return String value for wind chill.
     */
    public static double windChillCalculator(double windSpeed, double temperature)
    {
        double exponent = 0.16;
        double windChillValue = 13.12 + (0.6215 * temperature) - (11.37 * Math.pow(windSpeed, exponent)) + 0.3965 * (temperature) * (Math.pow(windSpeed, exponent));
        double windChillValueRounded = (Math.round(windChillValue * 100.0) / 100.0);
        return windChillValueRounded;
    }

    /**
     *  Process all conversions regarding Station's latest reading.
     *
     *  @return Conversions of latest reading.
     */
    public static void processConversions(Station station){
        // Initiate latestReadings
        Reading latestReading;

        if (station.readings.size() > 0) {
            // Get the latest reading
            latestReading = station.readings.get(station.readings.size() - 1);

            // Station Conversions
            station.latestWeather = convertCodeToWeather(latestReading.code);
            station.temperature = latestReading.temperature;
            station.wind = convertToBeaufort(latestReading.windSpeed);
            station.pressure = latestReading.pressure;
            station.windCompass = convertToCompassDirection(latestReading.windDirection);
            station.windChill = windChillCalculator(latestReading.windSpeed, latestReading.temperature);
            station.fahrenheitTemp = convertToFahrenheit(latestReading.temperature);
        }
    }
}
