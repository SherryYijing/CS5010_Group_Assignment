package Model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StockImplTest {
  Stock stock;

  @Before
  public void setUp() {
    this.stock = new StockImpl("IBM", 150, "2022-10-27");
  }

  @Test
  public void constructorTest1() {
    try {
      Stock tempStock = new StockImpl("", 100, "2022-10-26");
    } catch (Exception exception) {
      assertEquals("The name of stock cannot be empty!", exception.getMessage());
    }
  }

  @Test
  public void constructorTest2() {
    try {
      Stock tempStock = new StockImpl("GOOG", -1, "2022-10-26");
    } catch (Exception exception) {
      assertEquals("The quantity of stock must be greater than 0!", exception.getMessage());
    }
  }

  @Test
  public void constructorTest3() {
    try {
      Stock tempStock = new StockImpl("GOOG", 100, "");
    } catch (Exception exception) {
      assertEquals("The purchase date cannot be null!", exception.getMessage());
    }
  }

  @Test
  public void constructorTest4() {
    try {
      Stock tempStock = new StockImpl("GOOG", 100, "2050-10-30");
    } catch (Exception exception) {
      assertEquals("The purchase date cannot on a future date!", exception.getMessage());
    }
  }

  @Test
  public void constructorTest5() {
    try {
      Stock tempStock = new StockImpl("GOOG", 100, "2022-10-30");
    } catch (Exception exception) {
      assertEquals("Only weekday could buy stocks!", exception.getMessage());
    }
  }

  @Test
  public void getStockTickerSymbolTest() {
    assertEquals("IBM", this.stock.getStockTickerSymbol());
  }

  @Test
  public void getBuyPriceTest() {
    assertEquals(134.77, this.stock.getBuyPrice(), 0.01);
  }

  @Test
  public void getCurrentPriceTest() {
    assertEquals(138.51, this.stock.getCurrentPrice(), 0.01);
  }

  @Test
  public void getQuantityTest() {
    assertEquals(150, this.stock.getQuantity());
  }

  @Test
  public void getBuyDateTest() {
    assertEquals("2022-10-27", this.stock.getBuyDate());
  }

  @Test
  public void toStringTest() {
    assertEquals("Ticker name: IBM, Buy price per stock: 134.77, Stock quantity: 150, "
            + "Purchase date: 2022-10-27", this.stock.toString());
  }
}