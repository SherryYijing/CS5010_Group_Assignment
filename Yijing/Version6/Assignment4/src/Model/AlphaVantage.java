package Model;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * An interface of AlphaVantage.
 */
public interface AlphaVantage {
  /**
   * Create the URL. This is the query to the web service. The query string includes the type of
   * query (DAILY stock prices), stock symbol to be looked up, the API key and the format of the
   * returned data (comma-separated values:csv).
   *
   * @param stockSymbol stock symbol.
   * @return URL contains all information.
   * @throws IllegalArgumentException if it opens URL failed, return error.
   */
  URL getURL(String stockSymbol) throws IllegalArgumentException;

  /**
   * Get output from input URL.
   *
   * @param stockSymbol stock symbol.
   * @param url         the data source from a URL.
   * @return StringBuilder contains all stock information.
   * @throws IllegalArgumentException
   */
  StringBuilder getOutput(String stockSymbol, URL url) throws IllegalArgumentException;

  /**
   * Check if input stock symbol is valid.
   *
   * @param companyName the stock symbol you want to check.
   * @throws IllegalArgumentException if stock symbol is invalid, it will throw error.
   */
  void checkTikerAvailable(String companyName) throws IllegalArgumentException;

  /**
   * A method that get all date price from Alphavantage.
   *
   * @param stockSymbol ticker symbol for stock.
   * @throws MalformedURLException if it cannot find stock then throw error.
   */
  void getPrices(String stockSymbol) throws MalformedURLException;

  /**
   * A method that get price in certain date.
   *
   * @param stockSymbol ticker symbol for stock.
   * @param dateTime    a certain date.
   * @param priceType   open price, high price, low price, close price, or volume.
   * @return price.
   * @throws MalformedURLException if it cannot find stock then throw error.
   */
  Double getPriceInCertainDate(String stockSymbol, String dateTime, String priceType)
          throws MalformedURLException;
}
