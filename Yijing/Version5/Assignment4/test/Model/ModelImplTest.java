package Model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * A test for ModelImpl.
 */
public class ModelImplTest {
  Model model;

  @Before
  public void setUp() {
    this.model = new ModelImpl();
  }

  /**
   * createPortfolio() Test: create a new portfolio with string input as portfolio name. If this
   * method work, it should return new portfolio object.
   */
  @Test
  public void createPortfolioTest() {
    Portfolio newPortfolio = this.model.createPortfolio("Yijing's Portfolio");
    assertEquals("Yijing's Portfolio", newPortfolio.getPortfolioName());
  }

  /**
   * createPortfolio() Test: create two new portfolios with string input as portfolio name, but we
   * use same name twice. If this method work, it should return an IllegalArgumentException.
   */
  @Test
  public void createPortfolioButExistTest() {
    this.model.createPortfolio("Yijing's Portfolio");
    try {
      this.model.createPortfolio("Yijing's Portfolio");
    } catch (IllegalArgumentException iae) {
      assertEquals("Portfolio already exists!", iae.getMessage());
    }
  }

  /**
   * toString() Test: this method will show portfolios' name and all stock under each portfolio. If
   * no stock under portfolio, then just only show the portfolio's name.
   */
  @Test
  public void toStringTest() {
    this.model.createPortfolio("Yijing's Portfolio");
    assertEquals("Portfolio name: Yijing's Portfolio  ", this.model.toString());
  }

  /**
   * buyShares() Test: this method will apply for buy shares. It takes the portfolio's name that you
   * want to buy stock, ticker symbol, quantity, and buy date.
   */
  @Test
  public void buySharesTest() {
    this.model.buyShares("yijing", "GOOG", 100, "2022-10-27");
    assertEquals("Portfolio name: yijing, Ticker name: GOOG, Buy price per stock: 92.6, "
            + "Stock quantity: 100, Purchase date: 2022-10-27.", this.model.toString());
  }

  /**
   * getTotalValue Test(): this method will show how much stock value you have for specific
   * portfolio in certain date.
   */
  @Test
  public void getTotalValueTest() {
    this.model.buyShares("yijing", "GOOG", 100, "2022-10-03");
    this.model.buyShares("yijing", "IBM", 100, "2022-10-27");
    this.model.buyShares("shamekh", "GOOG", 100, "2022-10-27");
    assertEquals(9466, this.model.getTotalValue("yijing",
            "2022-10-03"), 0.01);
  }

  /**
   * saveDateToFile() Test: save current portfolio into a file. If it works, it will return true.
   */
  @Test
  public void saveDateToFileTest() {
    this.model.buyShares("yijing", "IBM", 100, "2022-10-27");
    this.model.buyShares("shamekh", "GOOG", 100, "2022-10-27");
    assertEquals(true, this.model.saveDataToFile("yijing"));
  }

  /**
   * saveDateToFile() Test: save current portfolio into a file which doesn't exist. If it works, it
   * will return IllegalArgumentException.
   */
  @Test
  public void saveDateFromFileFailTest() {
    try {
      this.model.saveDataToFile("aEmptyPortfolio");
    } catch (IllegalArgumentException iae) {
      assertEquals("Portfolio aEmptyPortfolio doesn't exist!", iae.getMessage());
    }
  }

  /**
   * readDateToFile() Test: read portfolio information from a file. If it works, it will return
   * true.
   */
  @Test
  public void readDateFromFileTest() {
    this.model.readDataFromFile("test1");
    assertEquals("Portfolio name: test1, Ticker name: GOOG, Buy price per stock: 99.3, "
                    + "Stock quantity: 100, Purchase date: 2022-10-03, Ticker name: IBM, Buy price "
                    + "per stock: 121.51, Stock quantity: 100, Purchase date: 2022-10-03.",
            this.model.toString());
  }

  /**
   * readDateToFile() Test: read portfolio information from a file which doesn't exist. If it works,
   * it will return IllegalArgumentException.
   */
  @Test
  public void readDateFromFileFailTest() {
    try {
      this.model.readDataFromFile("Yijing");
    } catch (IllegalArgumentException iae) {
      assertEquals("Cannot find Yijing!", iae.getMessage());
    }
  }
}