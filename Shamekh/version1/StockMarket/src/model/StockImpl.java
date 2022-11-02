package model;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * An implementation of the stock interface.
 */
public class StockImpl implements Stock {
  private final String tickerSymbol;    // The ticker symbol of stock.
  private double buyPrice;              // The price of stock.
  private final int quantity;           // The quantity we buy.
  private final String buyDate;         // The date we buy this stock.
  private final AlphaVantage api = AlphaVantageImpl.getInstance();

  /**
   * A constructor for StockImpl.
   *
   * @param name   ticker symbaol for stock.
   * @param number quantity of stock.
   * @param date   when we buy this stock.
   * @throws IllegalArgumentException if it cannot get stock, throw new error.
   */
  public StockImpl(String name, int number, String date)
          throws IllegalArgumentException {
    // Check each input is valid first. We cannot have a stock without ticker symbol.
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("The name of stock cannot be empty!");
    }
    // We cannot buy the stock which quantity is less than 1.
    if (number <= 0) {
      throw new IllegalArgumentException("The quantity of stock must be greater than 0!");
    }
    // Buy date cannot be empty.
    if (date == null || date.isEmpty()) {
      throw new IllegalArgumentException("The purchase date cannot be null!");
    }
    // Buy date cannot in the future.
    else if (LocalDate.parse(date).isAfter(LocalDate.now())) {
      throw new IllegalArgumentException("The purchase date cannot on a future date!");
    }
    // Buy date cannot be weekend.
    else if (LocalDate.parse(date).getDayOfWeek() == DayOfWeek.SUNDAY ||
            LocalDate.parse(date).getDayOfWeek() == DayOfWeek.SATURDAY) {
      throw new IllegalArgumentException("Only weekday could buy stocks!");
    }
    this.tickerSymbol = name;
    this.quantity = number;
    this.buyDate = date;
    try {
      // We set buy price as close price of the day.
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
    // Find stock from API.
    try {
      currentValue = this.api.getPriceInCertainDate(this.tickerSymbol,
              LocalDate.now().plusDays(-1).toString(), "close");
    } catch (Exception exception) {
      // We do nothing here.
    }
    return currentValue;
  }

  @Override
  public double getPriceOnDate(String certainDate) {
    double currentValue = 0.0;
    // Find stock from API.
    try {
      currentValue = this.api.getPriceInCertainDate(this.tickerSymbol,
              certainDate, "close");
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
