package view;
import java.io.PrintStream;

public class ViewImpl implements View {

  private PrintStream out;

  public ViewImpl(PrintStream out) {
    this.out = out;
  }

  @Override
  public void show(String s){
    out.println("\n"+s);
  }
  @Override
  public void showStart() {
    out.println("\nWELCOME TO THE SHING TRADING PLATFORM");
    out.println("--Get ready to rock the stock market--");
  }

  @Override
  public void showOptions() {
    //print the UI
    out.println("\nMenu: ");
    out.println("1: Create portfolio");
    out.println("2: View portfolio composition");
    out.println("3: View portfolio total");
    out.println("E: Exit\n");
    out.println("Enter your choice: ");
  }

  @Override
  public void showCreateOptions() {
    //print the UI
    out.println("\nCreate portfolio menu: ");
    out.println("1: Add stocks manually");
    out.println("2: Upload file");
    out.println("M: Main menu");
    out.println("E: Exit\n");
    out.println("Enter your choice: ");
  }

  @Override
  public void showViewCompositionOptions() {
    //print the UI
    out.println("\nView portfolio composition menu: ");
    out.println("1: Individual portfolio");
    out.println("2: All portfolios");
    out.println("M: Main menu");
    out.println("E: Exit\n");
    out.println("Enter your choice: ");
  }

  @Override
  public void showViewTotalOptions() {
    //print the UI
    out.println("\nView Portfolio Total Menu: ");
    out.println("1: Individual portfolio");
    out.println("2: All portfolios");
    out.println("M: Main menu");
    out.println("E: Exit\n");
    out.println("Enter your choice: ");
  }

  @Override
  public void showUploadOptions() {
    out.println("\nChoose a file type (JSON, XML, CSV, TXT) and give path");
  }

  @Override
  public void showAddStockDetails() {
    //print the UI
    out.println("\nPlease enter the following: ");
    out.println("Stock name");
    out.println("Stock quantity");
    out.println("Stock buy date (FORMAT)");
  }

  @Override
  public void showAddMore() {
    //print the UI
    out.println("\nAdd more stocks? (Y/N)");
    out.println("DISCLAIMER: once portfolio is created it cannot be modified");
  }

  @Override
  public void showEnterPortfolioName() {
    out.println("\nEnter Portfolio Name");
  }

  @Override
  public void showOptionError() {
    out.println("\nInvalid option. Please try again.");
  }

  @Override
  public void showPortolioNames(String s) {
    out.println("\nAll portfolios: "+s);
  }

  @Override
  public void showEnd() {
    out.println("\nThank you for using Shing Trading Platform. Session Ended");
  }




}
