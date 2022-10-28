package Model;

import java.util.LinkedList;
import java.util.List;

public class ModelImpl implements Model {
  private final AlphaVantage datasource;
  private List<Portfolio> portfolios;

  ModelImpl()
  {
    this.datasource = new AlphaVantageImpl();
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
  public void buyShares(String portfolioName, String stockSymbol, int quantity, String date,
                        String type) throws IllegalArgumentException {
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
      datasource.getPriceInCertainDate(stockSymbol, date, type);
    }
    catch (Exception exception)
    {
      throw new IllegalArgumentException("No price on this date!");
    }
    Stock stock = new StockImpl(stockSymbol, quantity, date);
    newPortfolio.setStocks(stock);
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
