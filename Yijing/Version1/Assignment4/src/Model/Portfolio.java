package Model;

import java.util.List;

/**
 * An interface of portfolio. A portfolio of stocks is simply a collection of stocks.
 */
public interface Portfolio {
  /**
   * A getter for portfolio name.
   *
   * @return portfolio name.
   */
  String getPortfolioName();

  /**
   * A getter for portfolio stocks.
   *
   * @return stocks.
   */
  List<Stock> getStocks();
  void setStocks(Stock stock);

  /**
   * Allow a user to create one or more portfolios with shares of one or more stock. Once created,
   * shares cannot be added or removed from the portfolio.
   */
  //void buyShares(String name, double cost, int number, String date);

  /**
   * Determine the total value of a portfolio on a certain date.
   *
   * @param certainDate input certain date.
   * @return the total value.
   */
  Double getTotalValue(String certainDate);
}
