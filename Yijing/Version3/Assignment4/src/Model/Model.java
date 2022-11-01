package Model;

/**
 * An interface of Model.
 */
public interface Model {
  /**
   * A method for creating a portfolio.
   *
   * @param portfolioName name for portfolio.
   * @return Portfolio.
   * @throws IllegalArgumentException if it cannot create, throw new error.
   */
  Portfolio createPortfolio(String portfolioName) throws IllegalArgumentException;

  /**
   * A method for buying stocks.
   *
   * @param portfolioName name for portfolio that you want to buy stock in it.
   * @param stockSymbol   ticker symbol for stock you want to buy.
   * @param quantity      how much stock you want to buy.
   * @param date          when you buy this stock.
   * @throws IllegalArgumentException if it cannot buy, throw new error.
   */
  void buyShares(String portfolioName, String stockSymbol, int quantity, String date)
          throws IllegalArgumentException;

  /**
   * Determine the total value of a portfolio on a certain date.
   *
   * @param portfolioName the name of portfolio that you want to get total value.
   * @param certainDate   the certain date.
   * @return the total value of specific portfolio in certain date.
   */
  double getTotalValue(String portfolioName, String certainDate);
}
