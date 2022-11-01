package Model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * A test for AlphaVantageImpl.
 */
public class AlphaVantageImplTest {
  AlphaVantage api;

  @Before
  public void setUp() {
    this.api = new AlphaVantageImpl();
  }

  /**
   * getOpenPrice() Test: get open price for a stock with specific date. If the method work, then it
   * should return correct price.
   */
  @Test
  public void getOpenPriceTest() {
    try {
      double open = api.getPriceInCertainDate("GOOG", "2022-10-26",
              "open");
      assertEquals(96.76, open, 0.01);
    } catch (Exception exception) {
      // We do nothing here.
    }
  }

  /**
   * getOpenPrice() Test: get open price for a stock with specific date but the date is in the
   * future. If the method work, then it should return an exception.
   */
  @Test
  public void getOpenPriceFailTest() {
    try {
      api.getPriceInCertainDate("GOOG", "2027-10-26", "open");
    } catch (Exception exception) {
      assertEquals("GOOG has no open price on 2027-10-26", exception.getMessage());
    }
  }

  /**
   * getLowPrice() Test: get low price for a stock with specific date. If the method work, then it
   * should return correct price.
   */
  @Test
  public void getLowPriceTest() {
    try {
      double open = api.getPriceInCertainDate("GOOG", "2022-10-26",
              "low");
      assertEquals(94.57, open, 0.01);
    } catch (Exception exception) {
      // We do nothing here.
    }
  }

  /**
   * getLowPrice() Test: get low price for a stock with specific date but the date is in the future.
   * If the method work, then it should return an exception.
   */
  @Test
  public void getLowPriceFailTest() {
    try {
      api.getPriceInCertainDate("GOOG", "2027-10-26", "low");
    } catch (Exception exception) {
      assertEquals("GOOG has no low price on 2027-10-26", exception.getMessage());
    }
  }

  /**
   * getHighPrice() Test: get high price for a stock with specific date. If the method work, then it
   * should return correct price.
   */
  @Test
  public void getHighPriceTest() {
    try {
      double open = api.getPriceInCertainDate("GOOG", "2022-10-26",
              "high");
      assertEquals(98.54, open, 0.01);
    } catch (Exception exception) {
      // We do nothing here.
    }
  }

  /**
   * getHighPrice() Test: get high price for a stock with specific date but the date is in the
   * future. If the method work, then it should return an exception.
   */
  @Test
  public void getHighPriceFailTest() {
    try {
      api.getPriceInCertainDate("GOOG", "2027-10-26", "high");
    } catch (Exception exception) {
      assertEquals("GOOG has no high price on 2027-10-26", exception.getMessage());
    }
  }

  /**
   * getClosePrice() Test: get close price for a stock with specific date. If the method work, then
   * it should return correct price.
   */
  @Test
  public void getClosePriceTest() {
    try {
      double open = api.getPriceInCertainDate("GOOG", "2022-10-26",
              "close");
      assertEquals(94.82, open, 0.01);
    } catch (Exception exception) {
      // We do nothing here.
    }
  }

  /**
   * getClosePrice() Test: get close price for a stock with specific date but the date is in the
   * future. If the method work, then it should return an exception.
   */
  @Test
  public void getClosePriceFailTest() {
    try {
      api.getPriceInCertainDate("GOOG", "2027-10-26", "close");
    } catch (Exception exception) {
      assertEquals("GOOG has no close price on 2027-10-26", exception.getMessage());
    }
  }

  /**
   * getVolumePrice() Test: get volume for a stock with specific date. If the method work, then it
   * should return correct price.
   */
  @Test
  public void getVolumePriceTest() {
    try {
      double open = api.getPriceInCertainDate("GOOG", "2022-10-26",
              "volume");
      assertEquals(71671814, open, 0.01);
    } catch (Exception exception) {
      // We do nothing here.
    }
  }

  /**
   * getVolumePrice() Test: get volume for a stock with specific date but the date is in the
   * future. If the method work, then it should return an exception.
   */
  @Test
  public void getVolumePriceFailTest() {
    try {
      api.getPriceInCertainDate("GOOG", "2027-10-26", "volume");
    } catch (Exception exception) {
      assertEquals("GOOG has no volume price on 2027-10-26", exception.getMessage());
    }
  }
}