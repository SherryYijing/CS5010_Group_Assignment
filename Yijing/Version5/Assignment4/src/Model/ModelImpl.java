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
    this.model = new HashMap<>();
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
  public Boolean saveDataToFile(String portfolioName)
          throws IllegalArgumentException {
    Boolean flag = false;
    // Check if we have this portfolio.
    if (!this.model.containsKey(portfolioName)) {
      throw new IllegalArgumentException("Portfolio " + portfolioName + " doesn't exist!");
    }
    try {
      flag = this.model.get(portfolioName).saveDataToFile();
    } catch (Exception exception) {
      throw new IllegalArgumentException(exception.getMessage());
    }
    return flag;
  }

  @Override
  public void readDataFromFile(String fileName) throws IllegalArgumentException {
    String line = "";
    try {
      BufferedReader in = new BufferedReader(new FileReader(fileName + ".csv"));
      Portfolio newPortfolio = new PortfolioImpl(fileName);
      line = in.readLine();
      // We have to ignore the first title line, so read next line here.
      line = in.readLine();
      while (line != null) {
        try {
          String[] st;
          st = line.split(",");
          String buyDate;
          int quantity;

          try {
            buyDate = (st[2]).toString();
          } catch (Exception e) {
            throw new IllegalArgumentException("Buy date should be string!");
          }
          try {
            quantity = Integer.parseInt(st[3]);
          } catch (Exception e) {
            throw new IllegalArgumentException("Quantity should be integer!");
          }
          newPortfolio.setStocks(new StockImpl(st[0], quantity, buyDate));
        } catch (IllegalArgumentException iae) {
          throw new IllegalArgumentException(iae.getMessage());
        }
        line = in.readLine();
      }
      this.model.put(fileName, newPortfolio);
      in.close();
    } catch (Exception e) {
      throw new IllegalArgumentException("Cannot find " + fileName + "!");
    }
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
