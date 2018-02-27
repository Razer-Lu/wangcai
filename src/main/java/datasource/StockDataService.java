package datasource;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.net.URL;

public class StockDataService {
    private static final String  ALPHA_VANTAGE_API_KEY="Z9KHJ3Y68MS2K55P";
    private static String url = "https://www.alphavantage.co/query?function=%s&symbol=%s&interval=%s&time_period=%d&series_type=%s&apikey=" + ALPHA_VANTAGE_API_KEY;


    /**
     * Get EMA for a stock symbol
     *
     * @param symbol The name of the equity of your choice. For example: symbol=SSO
     * @param interval Time interval between two consecutive data points in the time series.
     *                 The following values are supported: 1min, 5min, 15min, 30min, 60min, daily, weekly, monthly
     * @param timePeriod Number of data points used to calculate each moving average value.
     *                   Positive integers are accepted (e.g., time_period=60, time_period=200)
     * @param seriesType The desired price type in the time series. Four types are supported: close, open, high, low
     *
     * @return JSONObject
     * */
    public static JSONObject getStockEMA(String symbol, String interval, int timePeriod, String seriesType) {
        String requestUrl = String.format(url, "EMA", symbol, interval, timePeriod, seriesType);
        return sendRequest(requestUrl);
    }


    private static JSONObject sendRequest(String url) {
        JSONObject jsonObject = null;
        try {
            URL request = new URL(url);
            JSONTokener tokener = new JSONTokener(request.openStream());
            jsonObject = new JSONObject(tokener);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }
}
