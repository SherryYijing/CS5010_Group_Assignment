package Model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * A test for PortfolioImpl.
 */
public class PortfolioImplTest {
  Portfolio portfolio;

  @Before
  public void setUp() {
    this.portfolio = new PortfolioImpl("test1");
  }

  /**
   * constructor Test: constructor takes new name for portfolio. If we try to a portfolio with empty
   * name, it will return IllegalArgumentException.
   */
  @Test
  public void constructorTest() {
    try {
      Portfolio temp = new PortfolioImpl("");
    } catch (IllegalArgumentException iae) {
      assertEquals("Portfolio name cannot be empty!", iae.getMessage());
    }
  }

  /**
   * getPortfolioName() Test: this method return current portfolio's name.
   */
  @Test
  public void getPortfolioNameTest() {
    assertEquals("test1", this.portfolio.getPortfolioName());
  }

  /**
   * toString() Test: this method return current portfolio's name and which stock under it. If no
   * stock under portfolio, it just only show the portfolio's name.
   */
  @Test
  public void toStringTest() {
    assertEquals("Portfolio name: test1", this.portfolio.toString());
  }

  /**
   * setStocks() Test: this method set new stock into our portfolio. We could use toString() method
   * to check if stock was set successfully.
   */
  @Test
  public void setStocksTest1() {
    Stock stock = new StockImpl("GOOG", 100, "2022-10-03");
    this.portfolio.setStocks(stock);
    assertEquals("Portfolio name: test1, Ticker name: GOOG, Buy price per stock: 99.3, "
            + "Stock quantity: 100, Purchase date: 2022-10-03", this.portfolio.toString());
  }

  /**
   * setStocks() Test: one more test but this time we set two stock into portfolio.
   */
  @Test
  public void setStocksTest2() {
    Stock stock1 = new StockImpl("GOOG", 100, "2022-10-03");
    Stock stock2 = new StockImpl("IBM", 100, "2022-10-03");
    this.portfolio.setStocks(stock1);
    this.portfolio.setStocks(stock2);
    assertEquals("Portfolio name: test1, Ticker name: GOOG, Buy price per stock: 99.3, "
                    + "Stock quantity: 100, Purchase date: 2022-10-03, Ticker name: IBM, Buy price per "
                    + "stock: 121.51, Stock quantity: 100, Purchase date: 2022-10-03",
            this.portfolio.toString());
  }

  /**
   * setStocks() Test: one more test but this time we try to set two same stock into our portfolio.
   * If method work, it will return IllegalArgumentException.
   */
  @Test
  public void setStocksFailTest() {
    Stock stock1 = new StockImpl("GOOG", 100, "2022-10-03");
    Stock stock2 = new StockImpl("GOOG", 100, "2022-10-03");
    this.portfolio.setStocks(stock1);
    try {
      this.portfolio.setStocks(stock2);
    } catch (IllegalArgumentException iae) {
      assertEquals("This stock's already in portfolio!", iae.getMessage());
    }
  }

  /**
   * getTotalValue() Test: this method will get total value of current portfolio. If it work, it
   * should return correct price.
   */
  @Test
  public void getTotalValueTest() {
    Stock stock1 = new StockImpl("GOOG", 100, "2022-10-03");
    Stock stock2 = new StockImpl("IBM", 100, "2022-10-03");
    this.portfolio.setStocks(stock1);
    this.portfolio.setStocks(stock2);
    assertEquals(23295, this.portfolio.getTotalValue("2022-10-03"), 0.01);
  }

  /**
   * saveDateToFile() Test: save current portfolio to .csv file. If it works, it will return true.
   */
  @Test
  public void saveDateToFileTest() {
    Stock stock1 = new StockImpl("GOOG", 100, "2022-10-03");
    Stock stock2 = new StockImpl("IBM", 100, "2022-10-03");
    this.portfolio.setStocks(stock1);
    this.portfolio.setStocks(stock2);
    Boolean flag = this.portfolio.saveDateToFile("Portfolio");
    assertEquals(true, flag);
  }
}