/*
 * Copyright (c) 2011 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.eyesofkhepri.adsense.demo.sample;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.adsense.AdSense;
import com.google.api.services.adsense.AdSenseScopes;
import com.google.api.services.adsense.model.Account;
import com.google.api.services.adsense.model.Accounts;
import com.google.api.services.adsense.model.AdClients;
import com.google.api.services.adsense.model.AdUnits;
import com.google.api.services.adsense.model.AdsenseReportsGenerateResponse;
import com.google.api.services.adsense.model.CustomChannels;
import com.google.api.services.adsense.model.SavedReports;

import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * A sample application that runs multiple requests against the AdSense Management API. These
 * include:
 * <ul>
 * <li>Listing all AdSense accounts for a user</li>
 * <li>Listing the sub-account tree for an account</li>
 * <li>Listing all ad clients for an account</li>
 * <li>Listing all ad units for an ad client</li>
 * <li>Listing all custom channels for an ad unit</li>
 * <li>Listing all custom channels for an ad client</li>
 * <li>Listing all ad units for a custom channel</li>
 * <li>Listing all URL channels for an ad client</li>
 * <li>Running a report for an ad client, for the past 7 days</li>
 * <li>Running a paginated report for an ad client, for the past 7 days</li>
 * <li>Listing all saved reports for an account</li>
 * <li>Running a saved report for an account</li>
 * <li>Listing all saved ad styles for an account</li>
 * <li>Listing all alerts for an account</li>
 * <li>Listing all dimensions for the user</li>
 * <li>Listing all metrics for the user</li>
 * </ul>
 */
public class AdSenseSample {

  /**
   * Be sure to specify the name of your application. If the application name is {@code null} or
   * blank, the application will log a warning. Suggested format is "MyCompany-ProductName/1.0".
   */
  private static final String APPLICATION_NAME = "";

  /** Directory to store user credentials. */
  private static final java.io.File DATA_STORE_DIR =
      new java.io.File(System.getProperty("user.home"), ".store/adsense_management_sample");

  /**
   * Global instance of the {@link DataStoreFactory}. The best practice is to make it a single
   * globally shared instance across your application.
   */
  private static FileDataStoreFactory dataStoreFactory;

  /** Global instance of the JSON factory. */
  private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

  // Request parameters.
  private static final int MAX_LIST_PAGE_SIZE = 50;
  private static final int MAX_REPORT_PAGE_SIZE = 50;

  /** Global instance of the HTTP transport. */
  private static HttpTransport httpTransport;

  /** Authorizes the installed application to access user's protected data. */
  private static Credential authorize() throws Exception {
    // load client secrets
    GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY,
        new InputStreamReader(AdSenseSample.class.getResourceAsStream("/client_secrets.json")));
    if (clientSecrets.getDetails().getClientId().startsWith("Enter")
        || clientSecrets.getDetails().getClientSecret().startsWith("Enter ")) {
      System.out.println("Enter Client ID and Secret from "
          + "https://code.google.com/apis/console/?api=adsense into "
          + "adsense-cmdline-sample/src/main/resources/client_secrets.json");
      System.exit(1);
    }
    // set up authorization code flow
    GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
        httpTransport, JSON_FACTORY, clientSecrets,
        Collections.singleton(AdSenseScopes.ADSENSE_READONLY)).setDataStoreFactory(
        dataStoreFactory).build();
    // authorize
    return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
  }

  /**
   * Performs all necessary setup steps for running requests against the API.
   *
   * @return An initialized AdSense service object.
   * @throws Exception
   */
  private static AdSense initializeAdsense() throws Exception {
    // Authorization.
    Credential credential = authorize();

    // Set up AdSense Management API client.
    AdSense adsense = new AdSense.Builder(
        new NetHttpTransport(), JSON_FACTORY, credential).setApplicationName(APPLICATION_NAME)
        .build();

    return adsense;
  }

  /**
   * Runs all the AdSense Management API samples.
   *
   * @param args command-line arguments.
   */
  public static void main(String[] args) {
    try {
      httpTransport = GoogleNetHttpTransport.newTrustedTransport();
      dataStoreFactory = new FileDataStoreFactory(DATA_STORE_DIR);
      AdSense adsense = initializeAdsense();

      Accounts accounts = GetAllAccounts.run(adsense, MAX_LIST_PAGE_SIZE);
      if ((accounts.getItems() != null) && !accounts.getItems().isEmpty()) {
        // Get an example account ID, so we can run the following sample.
        String chosenAccount = chooseAccount(accounts);
        GetAccountTree.run(adsense, chosenAccount);
        AdClients adClients = GetAllAdClients.run(adsense, chosenAccount, MAX_LIST_PAGE_SIZE);

        if ((adClients.getItems() != null) && !adClients.getItems().isEmpty()) {
          // Get an ad client ID, so we can run the rest of the samples.
          String exampleAdClientId = adClients.getItems().get(0).getId();

          AdUnits units =
              GetAllAdUnits.run(adsense, chosenAccount, exampleAdClientId, MAX_LIST_PAGE_SIZE);
          if ((units.getItems() != null) && !units.getItems().isEmpty()) {
            // Get an example ad unit ID, so we can run the following sample.
            String exampleAdUnitId = units.getItems().get(0).getId();
            GetAllCustomChannelsForAdUnit.run(
                adsense, chosenAccount, exampleAdClientId, exampleAdUnitId, MAX_LIST_PAGE_SIZE);
          }

          CustomChannels channels = GetAllCustomChannels.run(adsense, chosenAccount,
              exampleAdClientId, MAX_LIST_PAGE_SIZE);
          if ((channels.getItems() != null) && !channels.getItems().isEmpty()) {
            // Get an example custom channel ID, so we can run the following sample.
            String exampleCustomChannelId = channels.getItems().get(0).getId();
            GetAllAdUnitsForCustomChannel.run(
                adsense, chosenAccount, exampleAdClientId, exampleCustomChannelId,
                MAX_LIST_PAGE_SIZE);
          }

          GetAllUrlChannels.run(adsense, exampleAdClientId, MAX_LIST_PAGE_SIZE);
          GenerateReport.run(adsense, chosenAccount, exampleAdClientId);
          GenerateReportWithPaging.run(adsense, chosenAccount, exampleAdClientId,
              MAX_REPORT_PAGE_SIZE);
          CollateReportData.run(adsense, chosenAccount, exampleAdClientId);
        } else {
          System.out.println("No ad clients found, unable to run remaining methods.");
        }

        SavedReports savedReports =
            GetAllSavedReports.run(adsense, chosenAccount, MAX_REPORT_PAGE_SIZE);
        if ((savedReports.getItems() != null) && !savedReports.getItems().isEmpty()) {
          // Get a saved report ID, so we can generate its report.
          String exampleSavedReportId = savedReports.getItems().get(0).getId();
          GenerateSavedReport.run(adsense, chosenAccount, exampleSavedReportId);
        } else {
          System.out.println("No saved report found.");
        }

        GetAllSavedAdStyles.run(adsense, chosenAccount, MAX_LIST_PAGE_SIZE);

        GetAllAlerts.run(adsense, chosenAccount);

        HandleAccountErrors.run(adsense, chosenAccount, MAX_LIST_PAGE_SIZE);
      }

      GetAllDimensions.run(adsense);
      GetAllMetrics.run(adsense);

    } catch (IOException e) {
      System.err.println(e.getMessage());
    } catch (Throwable t) {
      t.printStackTrace();
    }
  }

  /**
   * Lists all AdSense accounts the user has access to, and prompts them to choose one.
   *
   * @param accounts the list of accounts to choose from.
   * @return the ID of the chosen account.
   */
  public static String chooseAccount(Accounts accounts) {
    String accountId = null;

    if (accounts == null || accounts.getItems() == null || accounts.getItems().size() == 0) {
      System.out.println("No AdSense accounts found. Exiting.");
      System.exit(-1);
    } else if (accounts.getItems().size() == 1) {
      accountId = accounts.getItems().get(0).getId();
      System.out.printf("Only one account found (%s), using it.\n", accountId);
    } else {
      System.out.println("Please choose one of the following accounts:");
      for (int i = 0; i < accounts.getItems().size(); i++) {
        Account account = accounts.getItems().get(i);
        System.out.printf("%d. %s (%s)\n", i + 1, account.getName(), account.getId());
      }
      System.out.printf("> ");
      Scanner scan = new Scanner(System.in);
      accountId = accounts.getItems().get(scan.nextInt() - 1).getId();
      System.out.printf("Account %s chosen, resuming.\n", accountId);
    }

    System.out.println();
    return accountId;
  }

  /**
   * Fills in missing date ranges from a report. This is needed because null
   * data for a given period causes that reporting row to be ommitted, rather than
   * set to zero (or the appropriate missing value for the metric in question).
   *
   * NOTE: This code assumes you have a single dimension in your report, and that
   * the dimension is either DATE or MONTH. The number of metrics is not relevant.
   *
   * @param response the report response received from the API.
   * @return the full set of report rows, complete with missing dates.
   */
  public static List<List<String>> fillMissingDates(AdsenseReportsGenerateResponse response)
      throws ParseException {
    DateFormat fullDate = new SimpleDateFormat("yyyy-MM-dd");
    Date startDate = fullDate.parse(response.getStartDate());
    Date endDate = fullDate.parse(response.getEndDate());

    int currentPos = 0;

    // Check if the results fit the requirements for this method.
    if (response.getHeaders() == null && !response.getHeaders().isEmpty()) {
      throw new RuntimeException("No headers defined in report results.");
    }

    if (response.getHeaders().size() < 2 ||
        !response.getHeaders().get(0).getType().equals("DIMENSION")) {
      throw new RuntimeException("Insufficient dimensions and metrics defined.");
    }

    if (response.getHeaders().get(1).getType().equals("DIMENSION")) {
      throw new RuntimeException("Only one dimension allowed.");
    }

    DateFormat dateFormat = null;
    Date date = null;
    // Adjust output format and start date according to time period.
    if (response.getHeaders().get(0).getName().equals("DATE")) {
      dateFormat = fullDate;
      date = startDate;
    } else if (response.getHeaders().get(0).getName().equals("MONTH")) {
      dateFormat = new SimpleDateFormat("yyyy-MM");
      date = fullDate.parse(dateFormat.format(startDate) + "-01");
    } else {
      // Return existing report rows without date filling.
      return response.getRows();
    }

    List<List<String>> processedData = new ArrayList<List<String>>();

    while (date.compareTo(endDate) <= 0) {
      Date rowDate = null;
      List<String> currentRow = null;
      if (response.getRows() != null && response.getRows().size() > currentPos) {
        currentRow = response.getRows().get(currentPos);
        // Parse date on current row.
        if (response.getHeaders().get(0).getName().equals("DATE")) {
          rowDate = fullDate.parse(currentRow.get(0));
        } else if (response.getHeaders().get(0).getName().equals("MONTH")) {
          rowDate = fullDate.parse(currentRow.get(0) + "-01");
        }
      }

      // Is there an entry for this date?
      if (rowDate != null && date.equals(rowDate)) {
        processedData.add(currentRow);
        currentPos += 1;
      } else {
        List<String> newRow = new ArrayList<String>();
        newRow.add(dateFormat.format(date));
        for (int i = 1; i < response.getHeaders().size(); i++) {
          newRow.add("no data");
        }
        processedData.add(newRow);
      }

      // Increment date accordingly.
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);
      if (response.getHeaders().get(0).getName().equals("DATE")) {
        calendar.add(Calendar.DATE, 1);
      } else if (response.getHeaders().get(0).getName().equals("MONTH")) {
        calendar.add(Calendar.MONTH, 1);
      }
      date = calendar.getTime();
    }

    return processedData;
  }
}
