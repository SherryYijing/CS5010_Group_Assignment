package model;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class AlphaVantageImplTest {
  AlphaVantage api;
  @Before
  public void setUp() {
    this.api = new AlphaVantageImpl();
  }

  @Test
  public void getOpenPriceTest()
  {
    try {
      double open = api.getPriceInCertainDate("GOOG", "2022-10-26",
              "open");
      assertEquals(96.76, open, 0.01) ;
    }
    catch (Exception exception)
    {
      // We do nothing here.
    }
  }
  @Test
  public void getOpenPriceFailTest()
  {
    try {
      double open = api.getPriceInCertainDate("GOOG", "2027-10-26",
              "open");
      fail("GOOG has no open price on 2027-10-26");
    }
    catch (Exception exception)
    {
      // We do nothing here.
    }
  }
  @Test
  public void getLowPriceTest()
  {
    try {
      double open = api.getPriceInCertainDate("GOOG", "2022-10-26",
              "low");
      assertEquals(94.57, open, 0.01) ;
    }
    catch (Exception exception)
    {
      // We do nothing here.
    }
  }
  @Test
  public void getLowPriceFailTest()
  {
    try {
      double open = api.getPriceInCertainDate("GOOG", "2027-10-26",
              "low");
      fail("GOOG has no low price on 2027-10-26");
    }
    catch (Exception exception)
    {
      // We do nothing here.
    }
  }
  @Test
  public void getHighPriceTest()
  {
    try {
      double open = api.getPriceInCertainDate("GOOG", "2022-10-26",
              "high");
      assertEquals(98.54, open, 0.01);
    }
    catch (Exception exception)
    {
      // We do nothing here.
    }
  }
  @Test
  public void getHighPriceFailTest()
  {
    try {
      double open = api.getPriceInCertainDate("GOOG", "2027-10-26",
              "high");
      fail("GOOG has no high price on 2027-10-26");
    }
    catch (Exception exception)
    {
      // We do nothing here.
    }
  }
  @Test
  public void getClosePriceTest()
  {
    try {
      double open = api.getPriceInCertainDate("GOOG", "2022-10-26",
              "close");
      assertEquals(94.82, open, 0.01) ;
    }
    catch (Exception exception)
    {
      // We do nothing here.
    }
  }
  @Test
  public void getClosePriceFailTest()
  {
    try {
      double open = api.getPriceInCertainDate("GOOG", "2027-10-26",
              "close");
      fail("GOOG has no close price on 2027-10-26");
    }
    catch (Exception exception)
    {
      // We do nothing here.
    }
  }
  @Test
  public void getVolumePriceTest()
  {
    try {
      double open = api.getPriceInCertainDate("GOOG", "2022-10-26",
              "volume");
      assertEquals(71671814, open, 0.01) ;
    }
    catch (Exception exception)
    {
      // We do nothing here.
    }
  }
  @Test
  public void getVolumePriceFailTest()
  {
    try {
      double open = api.getPriceInCertainDate("GOOG", "2027-10-26",
              "volume");
      fail("GOOG has no volume price on 2027-10-26");
    }
    catch (Exception exception)
    {
      // We do nothing here.
    }
  }
  @Test
  public void test()
  {
    System.out.print(LocalDate.now().toString());
  }
}