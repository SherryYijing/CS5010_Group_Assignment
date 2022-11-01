package Model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * A test for StockImpl.
 */
public class StockImplTest {
  Stock stock;

  @Before
  public void setUp() {
    this.stock = new StockImpl("IBM", 150, "2022-10-27");
  }

  /**
   * constructor test: stock's name cannot be empty. If method work, it should return an
   * IllegalArgumentException.
   */
  @Test
  public void constructorTest1() {
    try {
      new StockImpl("", 100, "2022-10-26");
    } catch (IllegalArgumentException iae) {
      assertEquals("The name of stock cannot be empty!", iae.getMessage());
    }
  }

  /**
   * constructor test: stock's quantity cannot be less than 1. If method work, it should return an
   * IllegalArgumentException.
   */
  @Test
  public void constructorTest2() {
    try {
      new StockImpl("GOOG", -1, "2022-10-26");
    } catch (IllegalArgumentException iae) {
      assertEquals("The quantity of stock must be greater than 0!", iae.getMessage());
    }
  }

  /**
   * constructor test: stock's buy date cannot be empty. If method work, it should return an
   * IllegalArgumentException.
   */
  @Test
  public void constructorTest3() {
    try {
      new StockImpl("GOOG", 100, "");
    } catch (IllegalArgumentException iae) {
      assertEquals("The purchase date cannot be null!", iae.getMessage());
    }
  }

  /**
   * constructor test: stock's buy date cannot in the future. If method work, it should return an
   * IllegalArgumentException.
   */
  @Test
  public void constructorTest4() {
    try {
      new StockImpl("GOOG", 100, "2050-10-30");
    } catch (IllegalArgumentException iae) {
      assertEquals("The purchase date cannot on a future date!", iae.getMessage());
    }
  }

  /**
   * constructor test: stock's buy date cannot in the weekend. If method work, it should return an
   * IllegalArgumentException.
   */
  @Test
  public void constructorTest5() {
    try {
      new StockImpl("GOOG", 100, "2022-10-30");
    } catch (IllegalArgumentException iae) {
      assertEquals("Only weekday could buy stocks!", iae.getMessage());
    }
  }

  /**
   * getStockTickerSymbol() test: If it works, it should return correct ticker symbol.
   */
  @Test
  public void getStockTickerSymbolTest() {
    assertEquals("IBM", this.stock.getStockTickerSymbol());
  }

  /**
   * getBuyPrice() test: If it works, it should return correct buy price.
   */
  @Test
  public void getBuyPriceTest() {
    assertEquals(134.77, this.stock.getBuyPrice(), 0.01);
  }

  /**
   * getCurrentPrice() test: If it works, it should return correct current price.
   */
  @Test
  public void getCurrentPriceTest() {
    assertEquals(138.51, this.stock.getCurrentPrice(), 0.01);
  }

  /**
   * getQuantity() test: If it works, it should return correct quantity.
   */
  @Test
  public void getQuantityTest() {
    assertEquals(150, this.stock.getQuantity());
  }

  /**
   * getBuyDate() test: If it works, it should return correct buy date.
   */
  @Test
  public void getBuyDateTest() {
    assertEquals("2022-10-27", this.stock.getBuyDate());
  }

  /**
   * toString() test: If it works, it should return correct string.
   */
  @Test
  public void toStringTest() {
    assertEquals("Ticker name: IBM, Buy price per stock: 134.77, Stock quantity: 150, "
            + "Purchase date: 2022-10-27", this.stock.toString());
  }
}