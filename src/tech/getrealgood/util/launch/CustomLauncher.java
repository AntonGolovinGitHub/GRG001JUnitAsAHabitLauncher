package tech.getrealgood.util.launch;

import static org.junit.platform.engine.discovery.DiscoverySelectors.*;

import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.TestPlan;
import org.junit.platform.launcher.core.LauncherConfig;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;
import org.junit.platform.reporting.legacy.xml.LegacyXmlReportGeneratingListener;
import tech.getrealgood.util.engine.CustomTestEngine;
import tech.getrealgood.util.listen.AnotherCustomTestExecutionListener;

public class CustomLauncher {

    private static final Logger log = LoggerFactory.getLogger(CustomLauncher.class);

    public static void main(String[] s) {

        try {

            CustomLauncher demo = new CustomLauncher();

            // this creates a Launcher and configures it
            Launcher l = demo.configure();

            // this can be used in a ui to display what was discovered
            TestPlan t = demo.discover(l);

            // this actually runs the tests
            demo.execute(l);

        } catch (Exception ex) {
            log.error(() -> "Could not run the test engine: " + ex.getMessage());
        }

    }


    TestPlan discover(Launcher l) throws Exception {

        try {

            LauncherDiscoveryRequest request = discoveryRequest();

            TestPlan testPlan = l.discover(request);

            log.info(() -> "Discovered some tests? " + testPlan.containsTests());

            return testPlan;
        } catch (Exception ex) {
            throw ex;
        }

    }

    void execute(Launcher l) throws Exception {

        try {

            LauncherDiscoveryRequest request = discoveryRequest();

            SummaryGeneratingListener listener = new SummaryGeneratingListener();

            l.registerTestExecutionListeners(listener);

            l.execute(request);

            TestExecutionSummary summary = listener.getSummary();

            log.info(() -> "Found these many tests: " + summary.getTestsFoundCount());
            log.info(() -> "These many tests succeeded: " + summary.getTestsSucceededCount());
            log.info(() -> "These many tests failed: " + summary.getTestsFailedCount());

        } catch (Exception ex) {
            throw ex;
        }

    }

    Launcher configure()  throws Exception {

        try {

            // you should provide your own absolute or relative path to the directory to output test-run results to
            Path reportsDir = Paths.get("D:\\Codes\\GRG001JUnitAsAHabitLauncher\\tests\\");

            PrintWriter out = new PrintWriter(System.out);

            LauncherConfig launcherConfig = LauncherConfig.builder()
                    .enableTestEngineAutoRegistration(false)
                    .enableTestExecutionListenerAutoRegistration(false)
                    .addTestEngines(new CustomTestEngine())
                    .addTestExecutionListeners(new LegacyXmlReportGeneratingListener(reportsDir, out))
                    .addTestExecutionListeners(new AnotherCustomTestExecutionListener())
                    .build();

            return LauncherFactory.create(launcherConfig);
        } catch (Exception ex) {
            throw ex;
        }

    }

    private LauncherDiscoveryRequest discoveryRequest() throws Exception {

        try {

            // you should provide your own absolute or relative path to the testX.txt files

            return LauncherDiscoveryRequestBuilder.request()
                    .selectors(
                            selectFile("D:\\Codes\\GRG001JUnitAsAHabitLauncher\\tests\\test1.txt"),
                            selectFile("D:\\Codes\\GRG001JUnitAsAHabitLauncher\\tests\\test2.txt"),
                            selectFile("D:\\Codes\\GRG001JUnitAsAHabitLauncher\\tests\\test3.txt"),
                            selectFile("D:\\Codes\\GRG001JUnitAsAHabitLauncher\\tests\\test4.txt"),
                            selectFile("D:\\Codes\\GRG001JUnitAsAHabitLauncher\\tests\\test5.txt"),
                            selectFile("D:\\Codes\\GRG001JUnitAsAHabitLauncher\\tests\\test6.txt"),
                            selectFile("D:\\Codes\\GRG001JUnitAsAHabitLauncher\\tests\\test7.txt"),
                            selectFile("D:\\Codes\\GRG001JUnitAsAHabitLauncher\\tests\\test8.txt"),
                            selectFile("D:\\Codes\\GRG001JUnitAsAHabitLauncher\\tests\\test9.txt"),
                            selectFile("D:\\Codes\\GRG001JUnitAsAHabitLauncher\\tests\\test10.txt")
                    )
                    .build();
        } catch (Exception ex) {
            throw ex;
        }
    }

}

