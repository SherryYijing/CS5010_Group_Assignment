package Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PortfolioImpl implements Portfolio {
  private String portfolioName;
  private List<Stock> stocks;

  PortfolioImpl(String name) throws IllegalArgumentException {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Portfolio name cannot be empty!");
    }
    this.portfolioName = name;
    this.stocks = new ArrayList<>();
  }

  @Override
  public String getPortfolioName() {
    return this.portfolioName;
  }

  @Override
  public List<Stock> getStocks() {
    return this.stocks;
  }

  @Override
  public void buyShares(String name, double cost, int number, LocalDateTime date) {
    Stock newStock = new StockImpl(name, cost, number, date);
    this.stocks.add(newStock);
  }

  // The total value of a portfolio is then the sum of the values of its individual holdings.
  @Override
  public int getTotalValue(LocalDateTime certainDate) {
    return 0;
  }
}
