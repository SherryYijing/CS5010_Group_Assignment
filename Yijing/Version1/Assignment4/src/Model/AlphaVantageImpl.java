package Model;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class AlphaVantageImpl implements AlphaVantage {
  //the API key needed to use this web service.
  private final String apiKey = "6ZESL7619O96BABK";  // Our own free API key.
  private Map<String, Map<String, Map<String, Double>>> prices;
  @Override
  public URL getURL(String stockSymbol) throws MalformedURLException {
    URL url = null;
    try {
      /*
      create the URL. This is the query to the web service. The query string includes the type of
      query (DAILY stock prices), stock symbol to be looked up, the API key and the format of the
      returned data (values:json).
       */
      url = new URL("https://www.alphavantage"
              + ".co/query?function=TIME_SERIES_DAILY"
              + "&outputsize=full"
              + "&symbol"
              + "=" + stockSymbol + "&apikey=" + apiKey + "&datatype=csv");
    } catch (MalformedURLException e) {
      throw new RuntimeException("the alpha vantage API has either changed or no longer works");
    }
    return url;
  }

  @Override
  public StringBuilder getDataSource(String stockSymbol) throws MalformedURLException {
    URL url = getURL(stockSymbol);

    InputStream in = null;
    StringBuilder output = new StringBuilder();

    try {
      in = url.openStream();
      int b;

      while ((b = in.read()) != -1) {
        output.append((char) b);
      }
    } catch (IOException e) {
      throw new IllegalArgumentException("No price data found for " + stockSymbol);
    }
    return output;
  }

  @Override
  public void getPrices(String stockSymbol)
          throws MalformedURLException {
    StringBuilder output = getDataSource(stockSymbol);
    String[] value = output.toString().split("\n");
    String[] type = {"timestamp", "open", "high", "low", "close", "volume"};
    Map<String, Map <String, Double>> tempPrices = new HashMap<>();

    for (int i = 1; i < value.length; i++) {
      String[] row = value[i].split(",");
      tempPrices.put(row[0], new HashMap<>());
      for (int j = 1; j < row.length; j++) {
          tempPrices.get(row[0]).put(type[j], Double.parseDouble(row[j]));
      }
    }
    this.prices.put(stockSymbol, tempPrices);
  }

  @Override
  public Double getPriceInCertainDate(String stockSymbol, String dateTime, String priceType)
          throws MalformedURLException {
    if(!this.prices.containsKey(stockSymbol))
    {
      this.getPrices(stockSymbol);
    }
    Map<String, Map<String, Double>> price = this.prices.get(stockSymbol);
    switch (priceType) {
      case "open":
        try {
          return price.get(dateTime).get("open");
        } catch (Exception exception) {
          throw new IllegalArgumentException(stockSymbol + " has no open price on" + dateTime);
        }
      case "low":
        try {
          return price.get(dateTime).get("low");
        } catch (Exception exception) {
          throw new IllegalArgumentException(stockSymbol + " has no low price on" + dateTime);
        }
      case "high":
        try {
          return price.get(dateTime).get("high");
        } catch (Exception exception) {
          throw new IllegalArgumentException(stockSymbol + " has no high price on" + dateTime);
        }
      case "close":
        try {
          return price.get(dateTime).get("close");
        } catch (Exception exception) {
          throw new IllegalArgumentException(stockSymbol + " has no close price on" + dateTime);
        }
      case "volume":
        try {
          return price.get(dateTime).get("volume");
        } catch (Exception exception) {
          throw new IllegalArgumentException(stockSymbol + " has no volume price on" + dateTime);
        }
      default:
        throw new IllegalArgumentException("No such price type");
    }
  }
}