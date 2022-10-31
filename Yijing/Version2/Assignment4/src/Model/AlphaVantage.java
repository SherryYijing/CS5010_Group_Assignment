package Model;

import java.net.MalformedURLException;

public interface AlphaVantage {
  //URL getURL(String stockSymbol) throws MalformedURLException;
  //StringBuilder getDataSource();
  void getPrices(String stockSymbol) throws MalformedURLException;

  Double getPriceInCertainDate(String stockSymbol, String dateTime, String priceType)
          throws MalformedURLException;
}
