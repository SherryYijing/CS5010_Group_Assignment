package Model;

import java.net.MalformedURLException;
import java.net.URL;

public interface AlphaVantage {
  URL getURL(String stockSymbol) throws MalformedURLException;
  StringBuilder getDataSource(String stockSymbol) throws MalformedURLException;
  void getPrices(String stockSymbol) throws MalformedURLException;

  Double getPriceInCertainDate(String stockSymbol, String dateTime, String priceType)
          throws MalformedURLException;
}
