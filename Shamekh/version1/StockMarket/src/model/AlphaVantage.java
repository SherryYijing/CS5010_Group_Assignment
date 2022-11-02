package model;

import java.net.MalformedURLException;

/**
 * An interface of AlphaVantage.
 */
public interface AlphaVantage {
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
