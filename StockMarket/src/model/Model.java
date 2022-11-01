package model;

public interface Model {
  Portfolio createPortfolio(String portfolioName) throws IllegalArgumentException;
  Portfolio getPortfolio(int position) throws IllegalArgumentException;
  void buyShares(String portfolioName, String stockSymbol, int quantity, String date)
          throws  IllegalArgumentException;
  /**
   * Determine the total value of a portfolio on a certain date.
   * @param portfolioName the name of portfolio that you want to get total value.
   * @param certainDate the certain date.
   * @return the total value of specific portfolio in certain date.
   */
  double getTotalValue(String portfolioName, String certainDate);
}
