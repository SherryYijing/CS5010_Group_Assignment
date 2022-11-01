package model;

import java.util.ArrayList;
import java.util.List;

public class PortfolioImpl implements Portfolio {
  private final String portfolioName;
  private final List<Stock> stocks;

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
  public void setStocks(Stock stock) {
    this.stocks.add(stock);
  }
  @Override
  public Double getTotalValue(String certainDate)
  {
    Double total = 0.0;
    for(Stock stock: stocks)
    {
      try {
        AlphaVantage api = new AlphaVantageImpl();
        total += stock.getQuantity() * stock.getCurrentPrice();
      }
      catch (Exception exception)
      {
        throw new IllegalArgumentException("No price on that day!");
      }
    }
    return total;
  }

  @Override
  public String toString()
  {
    String str = "Portfolio name: " + this.portfolioName;
    StringBuilder output = new StringBuilder();
    output.append(str);

    for (Stock stock : this.stocks)
    {
      output.append(", ");
      output.append(stock.toString());
    }
    return output.toString();
  }
}
