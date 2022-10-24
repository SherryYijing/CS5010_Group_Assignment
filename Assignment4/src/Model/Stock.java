package Model;

import java.time.LocalDateTime;

/**
 * A interface for stock that has all information about stock including the name of stock, the price
 * of stock, the quantity of stock, and the purchase date of stock.
 */
public interface Stock {
  /**
   * A getter for stock ticker symbol.
   *
   * @return stock ticker symbol.
   */
  String getStockTickerSymbol();

  /**
   * A getter for stock price.
   *
   * @return stock price.
   */
  double getPrice();

  /**
   * A getter for stock quantity.
   *
   * @return stock quantity.
   */
  int getQuantity();

  /**
   * A getter for stock purchase date.
   *
   * @return stock purchase date.
   */
  LocalDateTime getBuyDate();
}
