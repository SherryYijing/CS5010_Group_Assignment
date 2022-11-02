package controller;

import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

import model.Model;
import view.View;

public class ControllerImpl implements Controller {

  private Model model;
  private View view;
  private Scanner in;

  public ControllerImpl(Model model, InputStream in, View view) {
    this.model = model;
    this.in = new Scanner(in);
    this.view = view;
  }


  @Override
  public void go() {
    //tell view to show start page
    view.showStart();
    //tell view to show options
    begin();
    view.showEnd();
  }

  private void begin() {
    boolean quit = false;
    while (!quit) {
      //accept user input
      view.showOptions();
      String option = in.next().toUpperCase();
      // main menu
      switch (option) {

        // create portfolio
        case "1":
          String portfolioName = "";
          //ask for name
          boolean flag = true;
          while (flag) {
            try {
              view.showEnterPortfolioName();
              portfolioName = in.next();
              model.createPortfolio(portfolioName);
              flag = false;
            } catch (Exception e) {
              view.show("Portfolio name already used!");
            }
          }
          view.show("Created portfolio successfully");
          // create options
          nested_switch_create(portfolioName);
          break;

        case "2":
          try {
            String ports = model.getPortfolioNames();
          } catch (Exception e) {
            view.show("No portfolios present, please create portfolio first!");
            break;
          }
          nested_switch_composition();
          break;

        case "3":
          try {
            String ports = model.getPortfolioNames();
          } catch (Exception e) {
            view.show("No portfolios present, please create portfolio first!");
            break;
          }

          String certainDate;
          view.show("Please enter date on which you would like to"
                  + " see total value (in YYYY-MM-DD format)");
          certainDate = in.next();
          nested_switch_total(certainDate);
          break;

        case "E":
          quit = true;
          break;

        default:
          view.showOptionError();
          break;
      }
    }
  }

  private void nested_switch_create(String portfolioName) {
    String createOption;
    String stockName;
    int stockQuantity;
    String stockBuyDate;
    boolean quit = false;

    while (!quit) {
      view.showCreateOptions();
      createOption = in.next().toUpperCase();
      switch (createOption) {
        case "1":
          String add = "Y";
          while (add.equals("Y")) {
            view.showAddStockDetails();

            try {
              stockName = in.next();
              stockQuantity = in.nextInt();
              stockBuyDate = in.next();
              model.buyShares(portfolioName, stockName, stockQuantity, stockBuyDate);
            } catch (InputMismatchException e) {
              view.show("please enter integer number for stock quantity");
            } catch (IllegalArgumentException e) {
              view.show("Invalid information: Please ensure all information is provided, "
                      + "quantity is greater than 0 and BuyDate is not in the future!");
            }

            view.showAddMore();
            add = in.next().toUpperCase();

            while (!(add.equals("N") || add.equals("Y"))) {
              view.showOptionError();
              view.showAddMore();
              add = in.next().toUpperCase();
            }
          }
          quit = true;
          //helper to store all details
          break;

        case "2":
          view.showUploadOptions();
          quit = true;
          break;
        case "B":
          quit = true;
          break;
        default:
          view.showOptionError();
          break;
      }
    }
  }


  private void nested_switch_composition() {
    boolean quit = false;
    String viewCompOption;

    while (!quit) {

      view.showViewCompositionOptions();
      viewCompOption = in.next().toUpperCase();

      switch (viewCompOption) {

        case "1":
          boolean flag = true;
          while (flag) {
            String portfolioName = "";
            String ports = model.getPortfolioNames();
            view.showPortfolioNames(ports);
            view.showEnterPortfolioName();
            portfolioName = in.next();
            // portfolio type check
            try {
              String s = model.viewCompositionIndividual(portfolioName);
              view.show(s);
              flag = false;
            } catch (Exception e) {
              view.show("No portfolio with this name");
            }
          }
          break;

        case "2":
          String all = model.toString();
          view.show(all);
          break;

        case "B":
          quit = true;
          break;

        default:
          view.showOptionError();
          break;
      }
    }
  }


  private void nested_switch_total(String certainDate) {
    boolean quit = false;
    String viewTotalOption;

    while (!quit) {

      view.showViewCompositionOptions();
      viewTotalOption = in.next().toUpperCase();
      boolean flag = true;

      switch (viewTotalOption) {

        case "1":
          while (flag) {
            String portfolioName = "";
            String ports = model.getPortfolioNames();
            view.showPortfolioNames(ports);
            view.showEnterPortfolioName();
            portfolioName = in.next();
            // portfolio type check
            try {
              double d = model.getTotalValue(portfolioName, certainDate);
              view.show("Portfolio " + portfolioName + "value on " + certainDate + " is "
                      + d);
              flag = false;
            } catch (Exception e) {
              view.show("No information on portfolio name or date");
            }
          }
          break;


        case "2":
          while (flag) {
            try {
              double d = model.getTotalValueCumulative(certainDate);
              view.show("Grand total value of all portfolios on " + certainDate + " is "
                      + d);
              flag = false;
            } catch (Exception e) {
              view.show("No information on this date");
            }
          }
          break;

        case "B":
          quit = true;
          break;

        default:
          view.showOptionError();
          break;
      }
    }
  }

}


