package utils;

import models.Reading;
import models.Station;

import java.util.List;

/**
 * Get Max and Min values for:
 * 1. Temperature
 * 2. Wind
 * 3. Pressure
 */
public class StationAnalytics {

    //=== Min Values
    /**
     * Get minimum Temperature reading.
     *
     * @param  readings
     * @return Reading minTempReading, the reading with the minimum temp value
     */
    public static Reading getMinTemperature (List<Reading> readings)
    {
        Reading minTempReading = null;
        if (readings.size() > 0) {
            minTempReading = readings.get(0);
            for (Reading reading : readings) {
                if (reading.temperature < minTempReading.temperature) {
                    minTempReading = reading;
                }
            }
        }
        return minTempReading;
    }

    /**
     * Get minimum Wind Speed reading.
     *
     * @param readings
     * @return Reading minWindReading, the reading with the minimum windSpeed value
     */
    public static Reading getMinWindSpeed (List<Reading> readings)
    {
        Reading minWindReading = null;
        if (readings.size() > 0) {
            minWindReading = readings.get(0);
            for (Reading reading : readings) {
                if (reading.windSpeed < minWindReading.windSpeed) {
                    minWindReading = reading;
                }
            }
        }
        return minWindReading;
    }

    /**
     * Get minimum Pressure reading.
     *
     * @param readings
     * @return Reading minPressureReading, the reading with the minimum pressure value
     */
    public static Reading getMinPressure (List<Reading> readings)
    {
        Reading minPressureReading = null;
        if (readings.size() > 0) {
            minPressureReading = readings.get(0);
            for (Reading reading : readings) {
                if (reading.pressure < minPressureReading.pressure) {
                    minPressureReading = reading;
                }
            }
        }
        return minPressureReading;
    }

    //=== Max Values

    /**
     * Get maximum Temperature reading.
     *
     * @param readings
     * @return Reading maxTempReading, the reading with the maximum temperature value
     */
    public static Reading getMaxTemperature (List<Reading> readings)
    {
        Reading maxTempReading = null;
        if (readings.size() > 0) {
            maxTempReading = readings.get(0);
            for (Reading reading : readings) {
                if (reading.temperature > maxTempReading.temperature) {
                    maxTempReading = reading;
                }
            }
        }
        return maxTempReading;
    }

    /**
     * Get maximum windSpeed reading.
     *
     * @param readings
     * @return Reading maxWindSpeedReading, the reading with the maximum windSpeed value
     */
    public static Reading getMaxWindSpeed (List<Reading> readings)
    {
        Reading maxWindReading = null;
        if (readings.size() > 0) {
            maxWindReading = readings.get(0);
            for (Reading reading : readings) {
                if (reading.windSpeed > maxWindReading.windSpeed) {
                    maxWindReading = reading;
                }
            }
        }
        return maxWindReading;
    }

    /**
     * Get maximum Pressure reading.
     *
     * @param readings
     * @return Reading maxPressureReading, the reading with the maximum pressure value
     */
    public static Reading getMaxPressure (List<Reading> readings)
    {
        Reading maxPressureReading = null;
        if (readings.size() > 0) {
            maxPressureReading = readings.get(0);
            for (Reading reading : readings) {
                if (reading.pressure > maxPressureReading.pressure) {
                    maxPressureReading = reading;
                }
            }
        }
        return maxPressureReading;
    }

    //=== Trends

    /**
     * Checks if latest figure for temp is rising or falling.
     *
     * @param readings
     * @return boolean value for tempRising, which is either true or false.
     */
    public static boolean temperatureTrend(List<Reading> readings){
        Reading latestTempReading = null;
        Boolean tempRising = false;

        // Ensures code only works if there is more than 1 reading.
        if (readings.size() > 1) {
            latestTempReading = readings.get( readings.size() - 1 ); // Get latest reading
            for (Reading reading : readings) {
                if (latestTempReading.temperature > reading.temperature) {
                    tempRising = true;
                }
            }
        }
        return tempRising;
    }

    /**
     * Checks if latest figure for wind is rising or falling.
     *
     * @param readings
     * @return boolean value for windRising, which is either true or false.
     */
    public static boolean windTrend(List<Reading> readings){
        Reading latestTempReading = null;
        Boolean windRising = false;

        // Ensures code only works if there is more than 1 reading.
        if (readings.size() > 1) {
            latestTempReading = readings.get( readings.size() - 1 ); // Get latest reading
            for (Reading reading : readings) {
                if (latestTempReading.windSpeed > reading.windSpeed) {
                    windRising = true;
                }
            }
        }
        return windRising;
    }

    /**
     * Checks if latest figure for pressure is rising or falling.
     *
     * @param readings
     * @return boolean value for pressureRising, which is either true or false.
     */
    public static boolean pressureTrend(List<Reading> readings){
        Reading latestTempReading = null;
        Boolean pressureRising = false;

        // Ensures code only works if there is more than 1 reading.
        if (readings.size() > 1) {
            latestTempReading = readings.get( readings.size() - 1 ); // Get latest reading
            for (Reading reading : readings) {
                if (latestTempReading.pressure > reading.pressure) {
                    pressureRising = true;
                }
            }
        }
        return pressureRising;
    }

    //=== Process Analytics

    /**
     * Process station analytics for latest reading.
     *
     * @param station
     * @return max/min Temperature, max/min WindSpeed, max/min Pressure
     */
    public static void processAnalytics(Station station)
    {
        if (station.readings.size() > 0) {
            // Analytics: Max & Min Values (Temperature, Wind, Pressure)
            station.maxTemperature = getMaxTemperature(station.readings).temperature;
            station.minTemperature = getMinTemperature(station.readings).temperature;
            station.maxWindSpeed = getMaxWindSpeed(station.readings).windSpeed;
            station.minWindSpeed = getMinWindSpeed(station.readings).windSpeed;
            station.maxPressure = getMaxPressure(station.readings).pressure;
            station.minPressure = getMinPressure(station.readings).pressure;
        }
    }

    /**
     * Process Trend analytics for Wind, Temp and Pressure.
     *
     * @param station
     * @return Boolean values depending if each trend is rising or falling.
     */
    public static void processTrendAnalytics(Station station)
    {
        if (station.readings.size() > 1) {
            station.tempTrend = temperatureTrend(station.readings);
            station.windTrend = windTrend(station.readings);
            station.pressureTrend = pressureTrend(station.readings);
        }
    }
}