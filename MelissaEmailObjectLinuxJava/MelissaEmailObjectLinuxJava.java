import com.melissadata.*;
import java.io.*;

/**
 * Email Object allows your websites and custom applications to update email
 * addresses in your database files while verifying and correcting misspelled domain
 * names.
 *
 * <p>High-level flow of this sample:
 * <ol>
 *   <li>SETUP     - create an mdEmail instance, hand it the license string and the
 *                   path to the data files, then InitializeDataFiles() (one time).</li>
 *   <li>INPUT     - feed an email address in.</li>
 *   <li>PROCESS   - configure the lookup options, then VerifyEmail() checks and
 *                   corrects the address.</li>
 *   <li>READ      - pull the parsed pieces back out with the Get* getters
 *                   (GetMailBoxName, GetDomainName, GetTopLevelDomain, ...).</li>
 *   <li>INTERPRET - GetResults() returns comma-separated result codes describing
 *                   what the object did/found; each code has a human description.</li>
 * </ol>
 *
 * <p>The pieces in this file map onto that flow:
 * <ul>
 *   <li>main / RunAsConsole / ParseArguments : console harness (argument parsing + the interactive loop).</li>
 *   <li>EmailObject                          : thin wrapper around mdEmail that owns setup + the call sequence.</li>
 *   <li>DataContainer                        : plain holder for one record's input and output.</li>
 * </ul>
 *
 * <p>Where mdEmail comes from:
 * The mdEmail and mdEmailJNI classes in com/melissadata come from mdEmail_JavaCode.zip,
 * which the accompanying MelissaEmailObjectLinuxJava.sh script downloads and
 * expands into com/melissadata on every run. mdEmailJNI declares the native methods
 * and loads libmdEmailJavaWrapper.so, the JNI shim that calls into libmdEmail.so.
 *
 * <p>Reference:
 * <ul>
 *   <li>Quickstart    : https://docs.melissa.com/on-premise-api/email-object/email-object-quickstart.html</li>
 *   <li>Release notes : https://releasenotes.melissa.com/on-premise-api/email-object/</li>
 *   <li>Result codes  : https://docs.melissa.com/on-premise-api/email-object/result-codes.html</li>
 * </ul>
 */
public class MelissaEmailObjectLinuxJava {

  /**
   * Entry point. Reads the optional command-line arguments, then hands control to
   * RunAsConsole, which performs the actual Email Object setup and processing.
   *
   * @param args The raw command-line arguments
   * @throws IOException if reading from standard input fails
   */
  public static void main(String args[]) throws IOException {
    // Populated by ParseArguments below.
    String[] arguments = ParseArguments(args);
    String license = arguments[0];
    String testEmail = arguments[1];
    String dataPath = arguments[2];

    RunAsConsole(license, testEmail, dataPath);
  }

  /**
   * Reads the supported command-line options and returns them.
   *
   * <p>Recognized flags (each followed by its value, e.g. "--email name@example.com"):
   * <ul>
   *   <li>--license / -l   : the Melissa license string</li>
   *   <li>--email / -e     : an email address to test in one-shot mode</li>
   *   <li>--dataPath / -d  : path to the Email Object data files</li>
   * </ul>
   *
   * @param args The raw command-line arguments to parse.
   * @return A String array of { license, testEmail, dataPath }.
   */
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

  /**
   * Sets up the Email Object once, then drives the input -> process -> output cycle.
   *
   * <p>In interactive mode (no --email) it loops, asking for a new email each pass until
   * the user answers "N". In one-shot mode (--email supplied) it runs a single pass
   * and exits.
   *
   * @param license   The Melissa license string used to initialize the object.
   * @param testEmail An email address to process in one-shot mode; if empty, the program prompts interactively.
   * @param dataPath  Path to the Email Object data files.
   * @throws IOException if reading from standard input fails
   */
  public static void RunAsConsole(String license, String testEmail, String dataPath) throws IOException {
    System.out.println("\n\n============ WELCOME TO MELISSA EMAIL OBJECT LINUX JAVA ============\n");

    // Construct the wrapper. This is where the object is licensed, pointed at the
    // data files, and initialized (see the EmailObject constructor below).
    EmailObject emailObject = new EmailObject(license, dataPath);
    Boolean shouldContinueRunning = true;

    // Gate the program on a successful initialization. If the data files could not
    // be loaded (bad/expired license, missing or wrong-path data files, ...),
    // GetInitializeErrorString() returns the reason instead of "No error." and we
    // skip the processing loop entirely.
    if (!emailObject.mdEmailObj.GetInitializeErrorString().equals("No error."))
      shouldContinueRunning = false;

    while (shouldContinueRunning) {
      // Holder for this pass's input and result codes.
      DataContainer dataContainer = new DataContainer();
      BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

      if (testEmail == null || testEmail.trim().isEmpty()) {
        // Interactive mode: prompt the user for an email address.
        System.out.println("\nFill in each value to see the Email Object results");
        System.out.print("Email:");

        dataContainer.Email = stdin.readLine();
      } else {
        // One-shot mode: use the email passed on the command line.
        dataContainer.Email = testEmail;
      }

      // Print user input
      System.out.println("\n============================== INPUTS ==============================\n");
      System.out.println("\t               Email: " + dataContainer.Email);

      // Execute Email Object
      // Runs the configure + verify sequence and stores the result codes on dataContainer.
      emailObject.ExecuteObjectAndResultCodes(dataContainer);

      // Print output
      // Each Get* getter below returns one component the object produced for the most
      // recently processed email. These read directly from the mdEmail instance, which
      // still holds the results from the Execute call above.
      System.out.println("\n============================== OUTPUT ==============================\n");
      System.out.println("\n\tEmail Object Information:");

      System.out.println("\t                    Email: " + dataContainer.Email);
      System.out.println("\t              MailBoxName: " + emailObject.mdEmailObj.GetMailBoxName());
      System.out.println("\t               DomainName: " + emailObject.mdEmailObj.GetDomainName());
      System.out.println("\t           TopLevelDomain: " + emailObject.mdEmailObj.GetTopLevelDomain());
      System.out.println("\tTopLevelDomainDescription: " + emailObject.mdEmailObj.GetTopLevelDomainDescription());

      System.out.println("\t  Result Codes: " + dataContainer.ResultCodes);

      // Result codes come back as a single comma-separated string (e.g. "ES01,ES21").
      // Split it and ask the object for a readable description of each code.
      // ResultCodeDescriptionLong requests the long-form text; a short form is also
      // available via ResultCodeDescriptionShort
      String[] rs = dataContainer.ResultCodes.split(",");
      for (String r : rs) {
        System.out.println("        " + r + ":"
            + emailObject.mdEmailObj.GetResultCodeDescription(r, mdEmail.ResultCdDescOpt.ResultCodeDescriptionLong));
      }

      Boolean isValid = false;

      // In one-shot mode there is nothing more to do after a single pass: mark the
      // input handled and stop the outer loop.
      if (testEmail != null && !testEmail.trim().isEmpty()) {
        isValid = true;
        shouldContinueRunning = false;
      }

      // Interactive mode: ask whether to process another email. Keep prompting until
      // we get a valid Y/N. "N" ends the program; "Y" falls through to another pass.
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

/**
 * Wrapper that owns a single Melissa Email Object instance and encapsulates the two
 * things every Melissa object needs: one-time setup (license + data files) and the
 * per-record processing sequence. Reuse one instance across many emails; do NOT
 * re-initialize per email.
 */
class EmailObject {
  // Path to the Email Object data files.
  String dataFilePath;

  // The underlying Melissa Email Object instance.
  mdEmail mdEmailObj = new mdEmail();

  /**
   * Performs the mandatory one-time setup, in this required order:
   * <ol>
   *   <li>SetLicenseString    - authorize the object.</li>
   *   <li>SetPathToEmailFiles - tell it where the data files live.</li>
   *   <li>InitializeDataFiles - load the data into memory.</li>
   * </ol>
   *
   * @param license  The Melissa license string used to authorize the object.
   * @param dataPath Path to the folder containing the Email Object data files.
   */
  public EmailObject(String license, String dataPath) {
    // Set license string and set path to data files
    mdEmailObj.SetLicenseString(license);
    dataFilePath = dataPath;

    // Point the object at the Email Object data files.
    mdEmailObj.SetPathToEmailFiles(dataFilePath);

    // Load the data files. The returned ProgramStatus reports whether initialization succeeded.
    // If you see a different date than expected, check your license string and either download the new data files
    // or use the Melissa Updater program to update your data files.
    mdEmail.ProgramStatus pStatus = mdEmailObj.InitializeDataFiles();

    // If an issue occurred, please investigate the common causes.
    // Common causes: an invalid/expired license, or missing/wrong-path data files.
    if (pStatus != mdEmail.ProgramStatus.ErrorNone) {
      System.out.println("Failed to Initialize Object.");
      System.out.println(pStatus);
      return;
    }

    // Diagnostic information, handy for confirming the object loaded the data you expect:

    // Build date of the data files
    System.out.println("                DataBase Date: " + mdEmailObj.GetDatabaseDate());

    // When the license stops working
    System.out.println("              Expiration Date: " + mdEmailObj.GetLicenseStringExpirationDate());

    // This number should match with the file properties of the Melissa Object binary file.
    // If TEST appears with the build number, there may be a license key issue.
    System.out.println("               Object Version: " + mdEmailObj.GetBuildNumber());
    System.out.println();

  }

  /**
   * Runs the full Email Object processing sequence for one email and captures its
   * result codes. This is the canonical per-record call pattern to copy into your
   * own application:
   * configure lookup options -> VerifyEmail -> GetResults
   *
   * @param data The record to process. Its Email is read as input, and ResultCodes is
   *             populated with this run's result codes.
   */
  public void ExecuteObjectAndResultCodes(DataContainer data) {

    // These are the configurable pieces of the Email Object - they control which checks
    // VerifyEmail performs (syntax correction, database & MX lookups, fuzzy matching, ...).
    mdEmailObj.SetCacheUse(1);
    mdEmailObj.SetCorrectSyntax(true);
    mdEmailObj.SetDatabaseLookup(true);
    mdEmailObj.SetFuzzyLookup(true);
    mdEmailObj.SetMXLookup(true);
    mdEmailObj.SetStandardizeCasing(true);
    mdEmailObj.SetWSLookup(false);

    // Validate and correct the email per the options above
    mdEmailObj.VerifyEmail(data.Email);

    // Collect the result codes for this run
    // ResultsCodes explain any issues Email Object has with the object.
    // List of result codes for Email Object
    // https://docs.melissa.com/on-premise-api/email-object/result-codes.html
    data.ResultCodes = mdEmailObj.GetResults();
  }
}

/**
 * Data holder for a single record: carries the input email in and the result codes out.
 */
class DataContainer {
  // Input: the email address to process.
  public String Email;

  // Output: comma-separated result codes from GetResults().
  public String ResultCodes;
}
