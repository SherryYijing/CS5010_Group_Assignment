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
   * @param certainDate   the certain date at which we want current price.
   * @return the total value of specific portfolio in certain date.
   */
  double getTotalValue(String portfolioName, String certainDate);

  /**
   * Save specific portfolio into a file.
   *
   * @param portfolioName specific portfolio that you want to save.
   * @return true or false.
   * @throws IllegalArgumentException if file open fail, it will return error.
   */
  Boolean saveDataToFile(String portfolioName) throws IllegalArgumentException;

  /**
   * Read portfolio information from a file.
   *
   * @param fileName the name of file which you want to read.
   * @throws IllegalArgumentException if file open fail, it will return error.
   */
  void readDataFromFile(String fileName) throws IllegalArgumentException;

  /**
   * Read portfolio information from all exist .csv file.
   *
   * @throws IllegalArgumentException if no such file here.
   */
  void readDataFromALL() throws IllegalArgumentException;

  /**
   * view composition of a single portfolio.
   *
   * @param portfolio the portfolio name whose composition you want to view.
   * @return string containing composition of portfolio.
   */
  String viewCompositionIndividual(String portfolio);

  /**
   * lists all the portfolio names associated with user.
   *
   * @return string containing portfolio names.
   */
  String getPortfolioNames();

  /**
   * Determine the total value of a portfolio on a certain date.
   *
   * @param certainDate the certain date at which we want current price.
   * @return the total value of specific portfolio in certain date.
   */
  double getTotalValueCumulative(String certainDate);

  /**
   * Check if the model is empty.
   */
  void emptyModelCheck();

  /**
   * Get the size of model.
   *
   * @return integer of model size.
   */
  int getModelSize();

  /**
   * Remove any empty portfolio from our model.
   */
  void removeEmptyPortfolio();
}
