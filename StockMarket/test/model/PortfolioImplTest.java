package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PortfolioImplTest {
  Portfolio portfolio;
  @Before
  public void setUp()
  {
    this.portfolio = new PortfolioImpl("test1");
  }
  @Test
  public void constructorTest()
  {
    try {
      Portfolio temp = new PortfolioImpl("");
    }
    catch (IllegalArgumentException iae)
    {
      assertEquals("Portfolio name cannot be empty!", iae.getMessage());
    }
  }

  @Test
  public void getPortfolioNameTest()
  {
    assertEquals("test1", this.portfolio.getPortfolioName());
  }
  @Test
  public void toStringTest()
  {
    assertEquals("Portfolio name: test1", this.portfolio.toString());
  }
  @Test
  public void setStocksTest()
  {
    Stock stock = new StockImpl("GOOG", 100, "2022-10-03");
    this.portfolio.setStocks(stock);
    assertEquals("Portfolio name: test1, Ticker name: GOOG, Buy price per stock: 99.3, "
            + "Stock quantity: 100, Purchase date: 2022-10-03", this.portfolio.toString());
  }
}