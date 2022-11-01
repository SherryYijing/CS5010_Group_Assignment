package Model;

import java.util.HashMap;
import java.util.Map;

/**
 * A implement for Model interface.
 */
public class ModelImpl implements Model {
  private final Map<String, Portfolio> model;

  /**
   * A constructor for ModelImpl.
   */
  public ModelImpl() {
    this.model = new HashMap<String, Portfolio>();
  }

  @Override
  public Portfolio createPortfolio(String portfolioName) throws IllegalArgumentException {
    // Check if portfolio already exist, we cannot create duplicate portfolio.
    if (this.model.containsKey(portfolioName)) {
      throw new IllegalArgumentException("Portfolio already exists!");
    }
    Portfolio newPortfolio = new PortfolioImpl(portfolioName);
    this.model.put(portfolioName, newPortfolio);
    return newPortfolio;
  }

  @Override
  public void buyShares(String portfolioName, String stockSymbol, int quantity, String date)
          throws IllegalArgumentException {
    // Get portfolio from our portfolio map.
    Portfolio newPortfolio = this.model.get(portfolioName);
    // If we don't have any portfolio called this name, then create a new one.
    if (newPortfolio == null) {
      newPortfolio = createPortfolio(portfolioName);
    }
    try {
      // Add this stock to our portfolio map.
      Stock stock = new StockImpl(stockSymbol, quantity, date);
      newPortfolio.setStocks(stock);
    } catch (Exception me) {
      throw new IllegalArgumentException("Cannot buy this stock!");
    }
  }

  @Override
  public double getTotalValue(String portfolioName, String certainDate) {
    // Use getTotalValue method from Portfolio class.
    return this.model.get(portfolioName).getTotalValue(certainDate);
  }

  @Override
  public String toString() {
    StringBuilder output = new StringBuilder();

    for (Portfolio portfolio : this.model.values()) {
      output.append(portfolio.toString());
      output.append(".");
    }
    return output.toString();
  }
}
