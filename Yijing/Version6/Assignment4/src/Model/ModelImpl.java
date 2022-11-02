package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * A implement for Model interface.
 */
public class ModelImpl implements Model {
  private final Map<String, Portfolio> model;

  /**
   * A constructor for ModelImpl.
   */
  public ModelImpl() {
    this.model = new HashMap<>();
  }

  @Override
  public Portfolio createPortfolio(String portfolioName) throws IllegalArgumentException {
    // Check if portfolio already exist, we cannot create duplicate portfolio.
    if (this.model.containsKey(portfolioName)) {
      throw new IllegalArgumentException("Portfolio already exists!");
    }
    Portfolio newPortfolio = new PortfolioImpl(portfolioName);
    this.model.put(portfolioName, newPortfolio);
    return newPortfolio;
  }

  @Override
  public void buyShares(String portfolioName, String stockSymbol, int quantity, String date)
          throws IllegalArgumentException {
    // Get portfolio from our portfolio map.
    Portfolio newPortfolio = this.model.get(portfolioName);
    // If we don't have any portfolio called this name, then create a new one.
    if (newPortfolio == null) {
      newPortfolio = createPortfolio(portfolioName);
    }
    try {
      // Add this stock to our portfolio map.
      Stock stock = new StockImpl(stockSymbol, quantity, date);
      newPortfolio.setStocks(stock);
    } catch (Exception exception) {
      throw new IllegalArgumentException(exception.getMessage());
    }
  }


  @Override
  public double getTotalValue(String portfolioName, String certainDate) {
    this.emptyModelCheck();

    if (!this.model.containsKey(portfolioName)) {
      throw new IllegalArgumentException("Portfolio " + portfolioName + " doesn't exist!");
    }

    dateCheck(certainDate);

    // Use getTotalValue method from Portfolio class.
    return this.model.get(portfolioName).getTotalValue(certainDate);
  }


  @Override
  public double getTotalValueCumulative(String certainDate) {
    this.emptyModelCheck();

    dateCheck(certainDate);

    double cumulative = 0.0;

    for (String portfolioName : this.model.keySet()) {
      cumulative += getTotalValue(portfolioName, certainDate);
    }
    return cumulative;
  }


  private void dateCheck(String certainDate) {
    // Buy date cannot be empty.
    if (certainDate == null || certainDate.isEmpty()) {
      throw new IllegalArgumentException("The purchase date cannot be null!");
    }
    // Buy date cannot in the future.
    else if (LocalDate.parse(certainDate).isAfter(LocalDate.now())) {
      throw new IllegalArgumentException("The purchase date cannot on a future date!");
    }
    // Buy date cannot be weekend.
    else if (LocalDate.parse(certainDate).getDayOfWeek() == DayOfWeek.SUNDAY ||
            LocalDate.parse(certainDate).getDayOfWeek() == DayOfWeek.SATURDAY) {
      throw new IllegalArgumentException("No data on weekends");
    }
  }


  @Override
  public Boolean saveDataToFile(String portfolioName)
          throws IllegalArgumentException {
    Boolean flag = false;
    // Check if we have this portfolio.
    if (!this.model.containsKey(portfolioName)) {
      throw new IllegalArgumentException("Portfolio " + portfolioName + " doesn't exist!");
    }
    try {
      flag = this.model.get(portfolioName).saveDataToFile();
    } catch (Exception exception) {
      throw new IllegalArgumentException(exception.getMessage());
    }
    return flag;
  }

  @Override
  public void readDataFromFile(String fileName) throws IllegalArgumentException {
    String line = "";
    try {
      BufferedReader in = new BufferedReader(new FileReader(fileName));
      Portfolio newPortfolio = new PortfolioImpl(fileName);
      line = in.readLine();
      // We have to ignore the first title line, so read next line here.
      line = in.readLine();
      while (line != null) {
        try {
          String[] st;
          st = line.split(",");
          String buyDate;
          int quantity;

          try {
            buyDate = (st[2]).toString();
          } catch (Exception e) {
            throw new IllegalArgumentException("Buy date should be string!");
          }
          try {
            quantity = Integer.parseInt(st[3]);
          } catch (Exception e) {
            throw new IllegalArgumentException("Quantity should be integer!");
          }
          newPortfolio.setStocks(new StockImpl(st[0], quantity, buyDate));
        } catch (IllegalArgumentException iae) {
          throw new IllegalArgumentException(iae.getMessage());
        }
        line = in.readLine();
      }
      this.model.put(fileName, newPortfolio);
      in.close();
    } catch (Exception e) {
      throw new IllegalArgumentException("Cannot find " + fileName + "!");
    }
  }

  @Override
  public void readDataFromALL() throws IllegalArgumentException {
    // Provide a folder path including .csv file.
    final File folder = new File("C:/Users/yijin/IdeaProjects/CS5010/Assignment4");
    // Store any file that satisfy condition into a linked list.
    List<String> filenames = new LinkedList<String>();
    // Store all file into a File list.
    File[] list = folder.listFiles();

    // In this case, we don't have any file in provided path, just throw error message.
    if (list == null) {
      throw new IllegalArgumentException("No file under this path!");
    }

    // Go through each file in this path, and find which are .csv file.
    for (final File file : list) {
      if (file.getName().contains(".csv")) {
        // Read this file and also add its name into list.
        readDataFromFile(file.getName());
        filenames.add(file.getName());
      }
    }
    // If our file name list is empty which means no file satisfied our condition, then throw error.
    if (filenames.isEmpty()) {
      throw new IllegalArgumentException("No .csv file under this path!");
    }
  }

  @Override
  public String viewCompositionIndividual(String portfolioName) {
    if (!this.model.containsKey(portfolioName)) {
      throw new IllegalArgumentException("Portfolio not present");
    }
    StringBuilder output = new StringBuilder();
    Portfolio newPortfolio = this.model.get(portfolioName);
    output.append(newPortfolio.toString());
    output.append("\n");
    return output.toString();
  }


  @Override
  public String toString() {
    StringBuilder output = new StringBuilder();

    for (Portfolio portfolio : this.model.values()) {
      output.append(portfolio.toString());
      output.append("\n");
    }
    return output.toString();
  }

  @Override
  public String getPortfolioNames() {
    this.emptyModelCheck();
    StringBuilder output = new StringBuilder();
    for (String portfolioName : this.model.keySet()) {
      output.append(portfolioName + "\t");
    }
    return output.toString();
  }

  @Override
  public void emptyModelCheck() {
    if (this.model.keySet().isEmpty()) {
      throw new IllegalStateException("There are no portfolios");
    }
  }

  @Override
  public int getModelSize() {
    return this.model.size();
  }

  @Override
  public void removeEmptyPortfolio() {
    this.model.values().removeIf(portfolio -> portfolio.checkEmpty());
  }
}
