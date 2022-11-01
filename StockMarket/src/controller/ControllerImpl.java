package controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

import javax.sound.sampled.Line;

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
          //ask for name
          view.showEnterPortfolioName();
          String portfolioName = in.next();
          model.createPortfolio(portfolioName);
          view.show("Created portfolio successfully");
          // create options
          nested_switch_create(portfolioName);
          break;

        case "2":
          view.showViewCompositionOptions();
          view.showEnterPortfolioName();
model.getPortfolio()
          break;

        case "3":
          view.showViewTotalOptions();
          view.showEnterPortfolioName();
          model.getTotalValue(in.next(), "2022-10-08");
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

    boolean quit = false;
    String createOption;
    String stockName;
    int stockQuantity;
    String stockBuyDate;

    view.showCreateOptions();
    createOption = in.next().toUpperCase();

    switch (createOption) {
      case "1":
        view.showAddMore();
        while (in.next().toUpperCase().equals("Y")) {
          view.showAddStockDetails();
          stockName = in.next();
          stockQuantity = in.nextInt();
          stockBuyDate = in.next();
          model.buyShares(portfolioName, stockName, stockQuantity, stockBuyDate);
          view.showAddMore();
        }
        //helper to store all details
        break;
      case "2":
        view.showUploadOptions();
        break;
      case "M":
        view.show("Unsaved Progress will be lost");
        break;
      default:
        view.showOptionError();
        break;
    }
  }
}


