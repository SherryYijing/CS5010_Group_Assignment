package model;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * An implement for AlphaVantage interface.
 */
public class AlphaVantageImpl implements AlphaVantage {
  private Map<String, Map<String, Map<String, Double>>> prices;
  private static AlphaVantageImpl instance;

  /**
   * A constructor for AlphaVantageImpl.
   */
  public AlphaVantageImpl() {
    // Initialize the prices map.
    this.prices = new HashMap<>();
  }

  /**
   * A instance for other class to call AlphaVantageImpl quickly.
   *
   * @return a instance.
   */
  public static AlphaVantageImpl getInstance() {
    // If it is null which means we don't have any instance now, so just call constructor.
    if (instance == null) {
      instance = new AlphaVantageImpl();
    }
    return instance;
  }

  @Override
  public void getPrices(String stockSymbol) {
    URL url = null;
    try {
      /*
      create the URL. This is the query to the web service. The query string includes the type of
      query (DAILY stock prices), stock symbol to be looked up, the API key and the format of the
      returned data (comma-separated values:csv).
       */

      // Our own free API key.
      String apiKey = "1QDFK9O8GUF7DU0R"; //"6ZESL7619O96BABK";

      //the API key needed to use this web service.
      url = new URL("https://www.alphavantage"
              + ".co/query?function=TIME_SERIES_DAILY"
              + "&outputsize=full"
              + "&symbol"
              + "=" + stockSymbol + "&apikey=" + apiKey + "&datatype=csv");
    } catch (MalformedURLException e) {
      throw new RuntimeException("the alpha vantage API has either changed or no longer works");
    }

    InputStream in = null;
    StringBuilder output = new StringBuilder();

    try {
      /*
      Execute this query. This will give us an InputStream object. In the csv format, it returns
      several lines, each line being separated by commas. Each line contains the date, price at
      opening time, the highest price for that date, the lowest price for that date, price at
      closing time and the volume of trade (no. of shares bought/sold) on that date.
       */
      in = url.openStream();
      int b;

      while ((b = in.read()) != -1) {
        output.append((char) b);
      }
    } catch (IOException e) {
      throw new IllegalArgumentException("No price data found for " + stockSymbol);
    }

    /*
    Now, we get an InputStream object, each line being separated by newline symbol. We separate
    them into a temporary map using date as key, and then create a new map for price of different
    type. After all done, we put this temporary map into our prices map and use ticker symbol as
    their key.
     */
    String[] value = output.toString().split("\n");
    String[] type = {"timestamp", "open", "high", "low", "close", "volume"};
    Map<String, Map<String, Double>> tempPrices = new HashMap<>();

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
          throws IllegalArgumentException {
    // First, check if our prices map doesn't contain this ticker symbol.
    if (!this.prices.containsKey(stockSymbol)) {
      // If so, then just get price from getPrices method.
      this.getPrices(stockSymbol);
    }
    // We need a temporary map to store the price for specific stock symbol.
    Map<String, Map<String, Double>> price = this.prices.get(stockSymbol);
    // Check if input date is weekend; we don't trade stock at weekend, so just get last Friday's
    // price.
    while (LocalDate.parse(dateTime).getDayOfWeek() == DayOfWeek.SUNDAY ||
            LocalDate.parse(dateTime).getDayOfWeek() == DayOfWeek.SATURDAY) {
      dateTime = LocalDate.parse(dateTime).plusDays(-1).toString();
    }
    // Then, get price for specific type from our map.
    switch (priceType) {
      case "open":
        try {
          return price.get(dateTime).get("open");
        } catch (Exception exception) {
          throw new IllegalArgumentException(stockSymbol + " has no open price on " + dateTime);
        }
      case "low":
        try {
          return price.get(dateTime).get("low");
        } catch (Exception exception) {
          throw new IllegalArgumentException(stockSymbol + " has no low price on " + dateTime);
        }
      case "high":
        try {
          return price.get(dateTime).get("high");
        } catch (Exception exception) {
          throw new IllegalArgumentException(stockSymbol + " has no high price on " + dateTime);
        }
      case "close":
        try {
          return price.get(dateTime).get("close");
        } catch (Exception exception) {
          throw new IllegalArgumentException(stockSymbol + " has no close price on " + dateTime);
        }
      case "volume":
        try {
          return price.get(dateTime).get("volume");
        } catch (Exception exception) {
          throw new IllegalArgumentException(stockSymbol + " has no volume price on " + dateTime);
        }
      default:
        throw new IllegalArgumentException("No such price type");
    }
  }
}