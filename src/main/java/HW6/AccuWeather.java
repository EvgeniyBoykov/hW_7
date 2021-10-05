package HW6;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
public class AccuWeather<pass> implements Weather {
    //http://dataservice.accuweather.com/forecasts/v1/daily/1day/349727
    private static String PROTOCOL = "https";
    private static final String BASE_HOST = "dataservice.accuweather.com";
    private static final String FORECASTS = "forecasts";
    private static final String VERSION = "v1";
    private static final String DAILY = "daily";
    private static final String ONE_DAY = "1day";
    private static final String FIVE_DAYS = "5day";
    private static final String API_KEY = "bsefep7PGDZqycqCPDHeklaAzkF9TU0l";
    private static final String API_KEY_QUERY_PROPERTY = "apikey";
    private static final String LOCATIONS = "locations";
    private static final String CITIES = "cities";
    private static final String AUTOCOMPLETE = "autocomplete";

    private static final OkHttpClient okHTTPClient = new OkHttpClient ();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public void getWeather(String city, Period period) throws IOException {
      //http://dataservice.accuweather.com/forecasts/v1/daily/1day/349727
        switch (period) {
            case NOW:
                HttpUrl httpUrl1 = new HttpUrl.Builder()
                        .scheme(PROTOCOL)
                        .host(BASE_HOST)
                        .addPathSegment(FORECASTS)
                        .addPathSegment(VERSION)
                        .addPathSegment(DAILY)
                        .addPathSegment(ONE_DAY)
                        .addPathSegment ("349727")//detectCityKey(city))
                        .addQueryParameter(API_KEY_QUERY_PROPERTY, API_KEY)
                        .build();

                Request request1 = new Request.Builder()
                        .url(httpUrl1)
                        .build();
                Response oneDayForecastReponse = okHTTPClient.newCall(request1).execute();
                String weatherResponse = oneDayForecastReponse.body().string();

                System.out.println(weatherResponse);
                break;

                case FIVE_DAYS:
                    HttpUrl httpUrl = new HttpUrl.Builder()
                            .scheme(PROTOCOL)
                            .host(BASE_HOST)
                            .addPathSegment(FORECASTS)
                            .addPathSegment(VERSION)
                            .addPathSegment(DAILY)
                            .addPathSegment(FIVE_DAYS)
                            .addPathSegment ("349727")//detectCityKey(city))
                            .addQueryParameter(API_KEY_QUERY_PROPERTY, API_KEY)
                            .build();

                    Request request = new Request.Builder()
                            .url(httpUrl)
                            .build();
                    Response fiveDayForecastReponse = okHTTPClient.newCall(request).execute();
                    String weatherResponse1 = fiveDayForecastReponse.body().string();
                    System.out.println(weatherResponse1);
                    break;
        }
    }

   public static void main(String[] args) {
        try{
       (new AccuWeather()).getWeather("Moscow", Period.NOW);
    } catch (IOException e) {
    e.printStackTrace();}
       try{
           (new AccuWeather()).getWeather("Moscow", Period.FIVE_DAYS);
       } catch (IOException e) {
           e.printStackTrace();}
}
    private String detectCityKey (String city){
    return null; }
}

