package com.eyesofkhepri.adsense.demo.main;

import com.eyesofkhepri.adsense.demo.sample.*;
import com.google.api.client.auth.oauth2.BearerToken;
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
import com.google.api.services.adsense.model.*;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class OuputReportData {

    private static final int MAX_LIST_PAGE_SIZE = 50;
    private static final int MAX_REPORT_PAGE_SIZE = 50;

    /**
     * Be sure to specify the name of your application. If the application name is {@code null} or
     * blank, the application will log a warning. Suggested format is "MyCompany-ProductName/1.0".
     */
    private static final String APPLICATION_NAME = "Daisy6";

    private static LocalServerReceiver localServerReceiver = null;

    /**
     * Global instance of the {@link DataStoreFactory}. The best practice is to make it a single
     * globally shared instance across your application.
     */
    private static FileDataStoreFactory dataStoreFactory;

    /** Global instance of the JSON factory. */
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    /** Global instance of the HTTP transport. */
    private static HttpTransport httpTransport;

    /** Directory to store user credentials. */
    private static final java.io.File DATA_STORE_DIR =
            new java.io.File(System.getProperty("user.home"), ".store/adsense_management_sample");

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
        /*GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                httpTransport, JSON_FACTORY, clientSecrets,
                Collections.singleton(AdSenseScopes.ADSENSE_READONLY)).setDataStoreFactory(
                dataStoreFactory).build();

        localServerReceiver = new LocalServerReceiver.Builder().setPort(8080).setHost("foxandsmile.com").build();*/

        // authorize
//        return new AuthorizationCodeInstalledApp(flow, localServerReceiver).authorize("user");
//        return new AuthorizationCodeInstalledApp(flow, localServerReceiver).authorize("user").setAccessToken("4/-bOpakqiaIM3ZmLfxkj9nUD1z9hu4TGs_ZgsjDulnz8");

        Credential credential = new Credential(BearerToken.authorizationHeaderAccessMethod());
        return credential.setAccessToken("4/-bOpakqiaIM3ZmLfxkj9nUD1z9hu4TGs_ZgsjDulnz8");
    }

    /**
     * Performs all necessary setup steps for running requests against the API.
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
            String chosenAccount = chooseAccount(accounts);

            AdClients adClients = GetAllAdClients.run(adsense, chosenAccount, MAX_LIST_PAGE_SIZE);

            List<AdClient> adClientList = adClients.getItems();

            for(AdClient a : adClientList) {
                System.out.println(a.getId());
            }

            //String exampleAdClientId = adClients.getItems().get(0).getId();


            GenerateReport.run(adsense, chosenAccount, "ca-app-pub-2186419942925048");
//            GenerateReport.run(adsense, chosenAccount, "365-156-8865");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
