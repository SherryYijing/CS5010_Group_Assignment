package model;

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
   * A method set new stock into portfolio.
   *
   * @param stock the stock you want to set.
   */
  void setStocks(Stock stock);

  /**
   * Determine the total value of a portfolio on a certain date.
   *
   * @param certainDate input certain date.
   * @return the total value.
   */
  Double getTotalValue(String certainDate);

  /**
   * Save current portfolio to .csv file.
   *
   * @return true or false.
   * @throws IllegalArgumentException if open file fail, return error.
   */
  Boolean saveDataToFile() throws IllegalArgumentException;
}
