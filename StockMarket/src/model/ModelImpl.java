package model;

import java.util.LinkedList;
import java.util.List;

public class ModelImpl implements Model {
  private List<Portfolio> portfolios;

  public ModelImpl()
  {
    this.portfolios = new LinkedList<Portfolio>();
  }
  @Override
  public Portfolio createPortfolio(String portfolioName) throws IllegalArgumentException {
    for (Portfolio portfolio: portfolios)
    {
      if(portfolioName.equals(portfolio.getPortfolioName()))
      {
        throw new IllegalArgumentException("Portfolio already exists!");
      }
    }
    Portfolio newPortfolio = new PortfolioImpl(portfolioName);
    portfolios.add(newPortfolio);
    return newPortfolio;
  }

  @Override
  public Portfolio getPortfolio(int position) throws IllegalArgumentException {
    if(position < 0 || position >= this.portfolios.size())
    {
      throw new IllegalArgumentException("Invalid position number!");
    }
    return this.portfolios.get(position);
  }

  @Override
  public void buyShares(String portfolioName, String stockSymbol, int quantity, String date)
          throws IllegalArgumentException {
    Portfolio newPortfolio = null;
    for (Portfolio portfolio: portfolios)
    {
      if(portfolioName.equals(portfolio.getPortfolioName()))
      {
        newPortfolio = portfolio;
        break;
      }
    }
    if(newPortfolio == null) {
      newPortfolio = createPortfolio(portfolioName);
    }
    try
    {
      AlphaVantage api = new AlphaVantageImpl();
      api.getPriceInCertainDate(stockSymbol, date, "close");
    }
    catch (Exception exception)
    {
      throw new IllegalArgumentException("No price on this date!");
    }
    try {
      Stock stock = new StockImpl(stockSymbol, quantity, date);
      newPortfolio.setStocks(stock);
    }
    catch (Exception me)
    {
      throw new IllegalArgumentException(me.getMessage());
    }
  }

  @Override
  public double getTotalValue(String portfolioName, String certainDate) {
    double totalValue = 0;
    for (Portfolio portfolio: portfolios)
    {
      if(portfolioName.equals(portfolio.getPortfolioName()))
      {
        totalValue += portfolio.getTotalValue(certainDate);
      }
    }
    return totalValue;
  }
}
