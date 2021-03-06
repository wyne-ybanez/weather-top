package controllers;

import java.util.*;
import java.util.stream.Collectors;

import models.*;

import play.Logger;
import play.mvc.Controller;

import static java.util.Comparator.comparing;
import static utils.Conversions.processConversions;
import static utils.StationAnalytics.processAnalytics;
import static utils.StationAnalytics.processTrendAnalytics;

public class Dashboard extends Controller
{
  public static void index()
  {
    Logger.info("Rendering Dashboard");

    // Get user
    Member member = Accounts.getLoggedInMember();

    // Get user's stations
    List<Station> stations = member.stations;

    // Sort Stations in alphabetical order
    List<Station> sortedStationList = stations.stream()
            .sorted(comparing(Station::getStationName))
            .collect(Collectors.toList());

    // Initiate conversions and analytics for each station
    for(Station station:sortedStationList) {
      processConversions(station);
      processAnalytics(station);
      processTrendAnalytics(station);
    }
    render ("dashboard.html", sortedStationList, member);
  }

  /**
   * Add Station to database
   *
   * First ensuring that the user is logged in.
   * Then create a new station, adding this to the member's station list.
   *
   * @param stationName
   * @param latitude
   * @param longitude
   */
  public static void addStation(String stationName, double latitude, double longitude)
  {
    Member member = Accounts.getLoggedInMember();
    Station station = new Station(stationName, latitude, longitude);
    member.stations.add(station);
    member.save();
    Logger.info("Adding Station: " + stationName
                + "\n Latitude: " + latitude
                + "\n Longitude: " + longitude);
    redirect("/dashboard");
  }

  /**
   * Delete the station from the database.
   *
   * Obtain both the member and the station.
   * Remove the station from the member's station list and delete it.
   *
   * @param id ID of the specified station
   */
  public static void deleteStation (long id)
  {
    Member member = Accounts.getLoggedInMember();
    Station station = Station.findById(id);
    member.stations.remove(station);
    member.save();
    try {
      station.delete();
    }
    catch (Exception e){
      System.err.println("Caught Exception: " + e);
    }
    Logger.info ("Removing station");
    redirect ("/dashboard");
  }
}
