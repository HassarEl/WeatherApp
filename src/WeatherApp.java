import netscape.javascript.JSObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

// retretive weather data from API - this backend logic will fetch the latest weather
// data from the external API and return it. The GUI will
// display this data to the user
public class WeatherApp {
    // fetch weather data for given location
    public static JSObject getWeatherData(String locationName) {
        // get location coordinates using the geolocation API
        JSONArray locationData = getLocationData(locationName);

        return null;
    }

    // retrieves geographic coordinates for given location name
    private static JSONArray getLocationData(String locationName){
        //replace any whitespace in location nama to + to adhere to API's request format
        locationName = locationName.replaceAll(" ", "+");

        // build API url with location parameter
        String urlString ="https://geocoding-api.open-meteo.com/v1/search?name=" +
                locationName + "&count=10&language=en&format=json";

        try{
            // call api and get a responce
            HttpURLConnection conn = fetchApiResponce(urlString);

            // check responce status
            // 200 means Successful conncetion
            if(conn.getRequestCode() != 200){
                System.out.println("Error: could not conncet to API");
                return null;
            }else{
                // start the API results
                StringBuilder resultJson = new StringBuilder();
                Scanner scanner = new Scanner(conn.getInputStream());

                // read and store the resulting json data into our string builder
                while(scanner.hasNext()){
                    resultJson.append(scanner.nextLine());
                }

                // close scanner
                scanner.close();

                // class url connection
                conn.disconnect();

                // parse the JSON string into a JSON  obj
                JSONParser parser = new JSONParser();
                JSONObject resultJsonObj = (JSONObject) parser.parse(String.valueOf(resultJson));

                // get the list of location data the API gtenertoted from the location name
                JSONArray locationData = (JSONArray) resultJsonObj.get("results");

                return locationData;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static HttpURLConnection fetchApiResponce(String urlString){
        try{
            // attempt to create connection
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // set request methode to get
            conn.setRequestMethod("GET");

            // connect to API
            conn.connect();
            return conn;
        }catch (IOException e){
            e.printStackTrace();
        }

        // could not make connection
        return null;
    }

}
