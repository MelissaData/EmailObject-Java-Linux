import com.melissadata.*;
import java.io.*;

public class MelissaEmailObjectLinuxJava {

  public static void main(String args[]) throws IOException {
    // Variables
    String[] arguments = ParseArguments(args);
    String license = arguments[0];
    String testEmail = arguments[1];
    String dataPath = arguments[2];

    RunAsConsole(license, testEmail, dataPath);
  }

  public static String[] ParseArguments(String[] args) {
    String license = "", testEmail = "", dataPath = "";
    for (int i = 0; i < args.length; i++) {
      if (args[i].equals("--license") || args[i].equals("-l")) {
        if (args[i + 1] != null) {
          license = args[i + 1];
        }
      }
      if (args[i].equals("--email") || args[i].equals("-e")) {
        if (args[i + 1] != null) {
          testEmail = args[i + 1];
        }
      }
      if (args[i].equals("--dataPath") || args[i].equals("-d")) {
        if (args[i + 1] != null) {
          dataPath = args[i + 1];
        }
      }
    }
    return new String[] { license, testEmail, dataPath };

  }

  public static void RunAsConsole(String license, String testEmail, String dataPath) throws IOException {
    System.out.println("\n\n============ WELCOME TO MELISSA EMAIL OBJECT LINUX JAVA ============\n");
    EmailObject emailObject = new EmailObject(license, dataPath);
    Boolean shouldContinueRunning = true;

    if (!emailObject.mdEmailObj.GetInitializeErrorString().equals("No error."))
      shouldContinueRunning = false;

    while (shouldContinueRunning) {
      DataContainer dataContainer = new DataContainer();
      BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

      if (testEmail == null || testEmail.trim().isEmpty()) {
        System.out.println("\nFill in each value to see the Email Object results");
        System.out.print("Email:");

        dataContainer.Email = stdin.readLine();
      } else {
        dataContainer.Email = testEmail;
      }

      // Print user input
      System.out.println("\n============================== INPUTS ==============================\n");
      System.out.println("\t               Email: " + dataContainer.Email);

      // Execute Email Object
      emailObject.ExecuteObjectAndResultCodes(dataContainer);

      // Print output
      System.out.println("\n============================== OUTPUT ==============================\n");
      System.out.println("\n\tEmail Object Information:");

      System.out.println("\t                    Email: " + dataContainer.Email);
      System.out.println("\t              MailBoxName: " + emailObject.mdEmailObj.GetMailBoxName());
      System.out.println("\t               DomainName: " + emailObject.mdEmailObj.GetDomainName());
      System.out.println("\t           TopLevelDomain: " + emailObject.mdEmailObj.GetTopLevelDomain());
      System.out.println("\tTopLevelDomainDescription: " + emailObject.mdEmailObj.GetTopLevelDomainDescription());

      System.out.println("\t  Result Codes: " + dataContainer.ResultCodes);

      String[] rs = dataContainer.ResultCodes.split(",");
      for (String r : rs) {
        System.out.println("        " + r + ":"
            + emailObject.mdEmailObj.GetResultCodeDescription(r, mdEmail.ResultCdDescOpt.ResultCodeDescriptionLong));
      }

      Boolean isValid = false;
      if (testEmail != null && !testEmail.trim().isEmpty()) {
        isValid = true;
        shouldContinueRunning = false;
      }

      while (!isValid) {
        System.out.println("\nTest another email? (Y/N)");
        String testAnotherResponse = stdin.readLine();

        if (testAnotherResponse != null && !testAnotherResponse.trim().isEmpty()) {
          testAnotherResponse = testAnotherResponse.toLowerCase();
          if (testAnotherResponse.equals("y")) {
            isValid = true;
          } else if (testAnotherResponse.equals("n")) {
            isValid = true;
            shouldContinueRunning = false;
          } else {
            System.out.println("Invalid Response, please respond 'Y' or 'N'");
          }
        }
      }
    }
    System.out.println("\n=============== THANK YOU FOR USING MELISSA JAVA OBJECT ============\n");

  }
}

class EmailObject {
  // Path to Email Object data files (.dat, etc)
  String dataFilePath;

  // Create instance of Melissa Email Object
  mdEmail mdEmailObj = new mdEmail();

  public EmailObject(String license, String dataPath) {
    // Set license string and set path to data files (.dat, etc)
    mdEmailObj.SetLicenseString(license);
    dataFilePath = dataPath;
    mdEmailObj.SetPathToEmailFiles(dataFilePath);

    // If you see a different date than expected, check your license string and
    // either download the new data files or use the Melissa Updater program to
    // update your data files.
    mdEmail.ProgramStatus pStatus = mdEmailObj.InitializeDataFiles();

    if (pStatus != mdEmail.ProgramStatus.ErrorNone) {
      // Problem during initialization
      System.out.println("Failed to Initialize Object.");
      System.out.println(pStatus);
      return;
    }

    System.out.println("                DataBase Date: " + mdEmailObj.GetDatabaseDate());
    System.out.println("              Expiration Date: " + mdEmailObj.GetLicenseStringExpirationDate());

    /**
     * This number should match with the file properties of the Melissa Object
     * binary file.
     * If TEST appears with the build number, there may be a license key issue.
     */
    System.out.println("               Object Version: " + mdEmailObj.GetBuildNumber());
    System.out.println();

  }

  // This will call the lookup function to process the input email as well as
  // generate the result codes
  public void ExecuteObjectAndResultCodes(DataContainer data) {

    mdEmailObj.SetCacheUse(1);
    mdEmailObj.SetCorrectSyntax(true);
    mdEmailObj.SetDatabaseLookup(true);
    mdEmailObj.SetFuzzyLookup(true);
    mdEmailObj.SetMXLookup(true);
    mdEmailObj.SetStandardizeCasing(true);
    mdEmailObj.SetWSLookup(false);

    mdEmailObj.VerifyEmail(data.Email);

    data.ResultCodes = mdEmailObj.GetResults();

    // ResultsCodes explain any issues Email Object has with the object.
    // List of result codes for Email Object
    // https://wiki.melissadata.com/?title=Result_Code_Details#Email_Object

  }
}

class DataContainer {
  public String Email;
  public String ResultCodes;
}
