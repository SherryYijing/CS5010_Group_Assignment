package model;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * An implementation of the stock interface.
 */
public class StockImpl implements Stock {
  private final String tickerSymbol;    // The ticker symbol of stock.
  private double buyPrice;           // The price of stock.
  private final int quantity;           // The quantity we buy.
  private final String buyDate;  // The date we buy this stock.
  private AlphaVantage api = new AlphaVantageImpl();
  ;

  public StockImpl(String name, int number, String date)
          throws IllegalArgumentException {
    // Check each input is valid first.
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("The name of stock cannot be empty!");
    }
    if (number < 0) {
      throw new IllegalArgumentException("The quantity of stock must be greater than 0!");
    }
    if (date == null || date.isEmpty()) {
      throw new IllegalArgumentException("The purchase date cannot be null!");
    } else if (LocalDate.parse(date).isAfter(LocalDate.now())) {
      throw new IllegalArgumentException("The purchase date cannot on a future date!");
    } else if (LocalDate.parse(date).getDayOfWeek() == DayOfWeek.SUNDAY ||
            LocalDate.parse(date).getDayOfWeek() == DayOfWeek.SATURDAY) {
      throw new IllegalArgumentException("Only weekday could buy stocks!");
    }
    this.tickerSymbol = name;
    this.quantity = number;
    this.buyDate = date;
    try {
      this.buyPrice = api.getPriceInCertainDate(name, buyDate, "close");
    } catch (Exception exception) {
      this.buyPrice = 0.0;
    }
  }

  @Override
  public String getStockTickerSymbol() {
    return this.tickerSymbol;
  }

  @Override
  public double getBuyPrice() {
    return this.buyPrice;
  }

  @Override
  public double getCurrentPrice() {
    double currentValue = 0.0;
    try {
      currentValue = this.api.getPriceInCertainDate(this.tickerSymbol,
              LocalDate.now().toString(), "close");
    } catch (Exception exception) {
      // We do nothing here.
    }
    return currentValue;
  }

  @Override
  public int getQuantity() {
    return this.quantity;
  }

  @Override
  public String getBuyDate() {
    return this.buyDate;
  }

  @Override
  public String toString() {
    return "Ticker name: " + this.tickerSymbol + ", Buy price per stock: " + this.buyPrice
            + ", Stock quantity: " + this.quantity + ", Purchase date: " + this.buyDate;
  }
}
