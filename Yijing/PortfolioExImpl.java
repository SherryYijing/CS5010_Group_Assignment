package model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class PortfolioExImpl extends AbstractPortfolio implements PortfolioEx {
  // A HashMap for store all stock's quantity and the date we make transaction.
  private final Map<String, Map<String, Integer>> stockHistory;
  // The stock we sold out.
  private final Map<String, Stock> soldStock;

  /**
   * A constructor for PortfolioImpl.
   *
   * @param name portfolio name.
   * @throws IllegalArgumentException if portfolio cannot be created, throw new error.
   */
  public PortfolioExImpl(String name) throws IllegalArgumentException {
    super(name);
    this.stockHistory = new HashMap<>();
    this.soldStock = new HashMap<>();
  }

  @Override
  public void setStocks(Stock stock) throws IllegalArgumentException {
    // Set stock and use ticker symbol as key.
    super.setStocks(stock);
    // Set stock history.
    setStockHistory(stock.getStockTickerSymbol(), stock.getBuyDate(), stock.getQuantity());
  }

  @Override
  public void setSoldStock(String name, Stock stock) {
    this.soldStock.put(name, stock);
  }

  @Override
  public void setStockHistory(String name, String date, Integer quantity) {
    if (!this.stockHistory.containsKey(name)) {
      this.stockHistory.put(name, new LinkedHashMap<>());
    }
    this.stockHistory.get(name).put(date, quantity);
  }

  @Override
  public void changeStockQuantity(String stockName, Integer newQuantity, String certainDate) {
    // If our portfolio don't contain that stock, then we cannot make any operation.
    if (!this.stocks.containsKey(stockName)) {
      throw new IllegalArgumentException("No stock name is " + stockName + " in current portfolio "
              + this.portfolioName);
    }
    try {
      // Set new stock into our map.
      this.stocks.get(stockName).setQuantity(newQuantity);
      setStockHistory(stockName, certainDate, newQuantity);
    } catch (Exception exception) {
      throw new IllegalArgumentException(exception.getMessage());
    }
  }

  @Override
  public Double costBasis(String certainDate) {
    double total = 0.0;
    // Check all stock and find which stock has this certain date.
    for (String stockName : this.stockHistory.keySet()) {
      Boolean flag = false;
      for (String date : this.stockHistory.get(stockName).keySet()) {
        if (!LocalDate.parse(date).isAfter(LocalDate.parse(certainDate))) {
          flag = true;
          break;
        }
      }
      if(flag) {
        if (this.stocks.containsKey(stockName)) {
          total += this.stocks.get(stockName).getCost();
        } else {
          if (this.soldStock.containsKey(stockName)) {
            total += this.soldStock.get(stockName).getCost();
          }
        }
        flag = false;
      }
    }
    return total;
  }

  @Override
  public Double getTotalValue(String certainDate) {
    double total = 0.0;
    for (String stockName : this.stockHistory.keySet()) {
      Integer quantity = 0;
      for (String date : this.stockHistory.get(stockName).keySet()) {
        if (!LocalDate.parse(date).isAfter(LocalDate.parse(certainDate))) {
          quantity = this.stockHistory.get(stockName).get(date);
        }
      }
      if (!this.stocks.containsKey(stockName)) {
        total += quantity * this.soldStock.get(stockName).getPriceOnDate(certainDate);
      } else {
        total += quantity * this.stocks.get(stockName).getPriceOnDate(certainDate);
      }
    }
    return total;
  }

  @Override
  public void removeStock(String stockName, String date) {
    setSoldStock(stockName, this.stocks.get(stockName));
    this.stocks.remove(stockName);
    setStockHistory(stockName, date, 0);
  }

  @Override
  public Boolean saveDataToFile() throws IllegalArgumentException {
    StringBuilder sb = new StringBuilder();
    sb.append(super.getDataIntoStringBuilder(this.stocks));
    sb.append("stockHistory").append("\n");
    sb.append("Stock Ticker Symbol").append(",")
            .append("Transaction Date").append(",")
            .append("Stock New Quantity").append("\n");
    // Then, add all stocks under current portfolio.
    for (String stock : this.stockHistory.keySet()) {
      for (String date : this.stockHistory.get(stock).keySet()) {
        sb.append(stock).append(",")
                .append(date).append(",").
                append(this.stockHistory.get(stock).get(date)).append("\n");
      }
    }
    sb.append("soldStock").append("\n");
    sb.append(super.getDataIntoStringBuilder(this.soldStock));
    if (super.portfolioName.contains("flex")) {
      return super.saveDataToFileHelper(sb, super.portfolioName);
    } else {
      return super.saveDataToFileHelper(sb, super.portfolioName + "flex");
    }
  }
}
