package Model;

import java.io.BufferedReader;
import java.io.FileReader;
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
  public Boolean saveDateToFile(String portfolioName, String fileName)
          throws IllegalArgumentException {
    Boolean flag = false;
    // Check if we have this portfolio.
    if (!this.model.containsKey(portfolioName)) {
      throw new IllegalArgumentException("Portfolio " + portfolioName + " doesn't exist!");
    }
    try {
      flag = this.model.get(portfolioName).saveDateToFile(fileName);
    } catch (Exception exception) {
      throw new IllegalArgumentException(exception.getMessage());
    }
    return flag;
  }

  @Override
  public void readDateFromFile(String fileName) throws IllegalArgumentException {
    StringBuilder sb = new StringBuilder();
    String res;
    String line = "";
    try {
      BufferedReader in = new BufferedReader(new FileReader(fileName + ".csv"));
      line = in.readLine();
      while (line != null) {
        sb.append(line);
        line = in.readLine();
      }
      in.close();
    } catch (Exception e) {
      throw new IllegalArgumentException("Cannot find " + fileName + "!");
    }

    String[] st;
    try {
      st = sb.toString().split(",");
    } catch (IllegalArgumentException iae) {
      throw new IllegalArgumentException(iae.getMessage());
    }

    if (st[0].equals("")) {
      throw new IllegalArgumentException("Portfolio name should not be empty.");
    }
    Portfolio newPortfolio = new PortfolioImpl(st[0]);
    int index = 1;
    while (index + 1 < st.length) {
      String symbol = st[index];
      double price;
      String buyDate;
      int quantity;
      price = Double.parseDouble(st[index + 1]);
      try {
        buyDate = (st[index + 2]).toString();
      } catch (Exception e) {
        throw new IllegalArgumentException("Buy date should be string!");
      }
      try {
        quantity = Integer.parseInt(st[index + 3]);
      } catch (Exception e) {
        throw new IllegalArgumentException("Quantity should be integer!");
      }
      index += 4;
      newPortfolio.setStocks(new StockImpl(symbol, quantity, buyDate));
    }
    this.model.put(st[0], newPortfolio);
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
