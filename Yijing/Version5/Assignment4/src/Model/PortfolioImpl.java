package Model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * An implement for Portfolio interface.
 */
public class PortfolioImpl implements Portfolio {
  private final String portfolioName;
  private final Map<String, Stock> stocks;

  /**
   * A constructor for PortfolioImpl.
   *
   * @param name portfolio name.
   * @throws IllegalArgumentException if portfolio cannot be created, throw new error.
   */
  public PortfolioImpl(String name) throws IllegalArgumentException {
    // Check input name if it is empty.
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Portfolio name cannot be empty!");
    }
    this.portfolioName = name;
    this.stocks = new HashMap<>();
  }

  @Override
  public String getPortfolioName() {
    return this.portfolioName;
  }

  @Override
  public void setStocks(Stock stock) throws IllegalArgumentException {
    // Check the stock we want to set if it is already in our portfolio.
    if (this.stocks.containsKey(stock.getStockTickerSymbol())) {
      throw new IllegalArgumentException("This stock's already in portfolio!");
    }
    // Set stock and use ticker symbol as key.
    this.stocks.put(stock.getStockTickerSymbol(), stock);
  }

  @Override
  public Double getTotalValue(String certainDate) {
    Double total = 0.0;
    // Check all stock and find which stock has this certain date.
    for (Stock stock : this.stocks.values()) {
      if (Objects.equals(stock.getBuyDate(), certainDate)) {
        total += stock.getQuantity() * stock.getCurrentPrice();
      }
    }
    return total;
  }

  @Override
  public Boolean saveDataToFile() throws IllegalArgumentException {
    // New string builder and store our portfolio name followed by comma.
    StringBuilder sb = new StringBuilder().append("Stock Ticker Symbol").append(",")
            .append("Stock Buy Price").append(",")
            .append("Stock Buy Date").append(",")
            .append("Stock Quantity").append("\n");
    // Then, add all stocks under current portfolio.
    for (Stock stock : this.stocks.values()) {
      sb.append(stock.getStockTickerSymbol()).append(",")
              .append(stock.getBuyPrice()).append(",")
              .append(stock.getBuyDate()).append(",")
              .append(stock.getQuantity()).append("\n");
    }
    try {
      // Create a new file or open a exist file, store all information into it.
      File file = new File(this.portfolioName + ".csv");
      file.createNewFile();
      BufferedWriter output = new BufferedWriter(new FileWriter(file));
      output.write(sb.toString());
      output.flush();
      output.close();
      // If all work done successfully, then return true.
      return true;
    } catch (Exception exception) {
      exception.printStackTrace();
    }
    return false;
  }

  @Override
  public String toString() {
    String str = "Portfolio name: " + this.portfolioName;
    StringBuilder output = new StringBuilder();
    output.append(str);

    for (Stock stock : this.stocks.values()) {
      output.append(", ");
      output.append(stock.toString());
    }
    return output.toString();
  }
}
