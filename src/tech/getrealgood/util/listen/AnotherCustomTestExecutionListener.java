package tech.getrealgood.util.listen;

import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.engine.reporting.ReportEntry;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;
import org.junit.platform.launcher.TestPlan;

public class AnotherCustomTestExecutionListener implements TestExecutionListener {

    private static final Logger log = LoggerFactory.getLogger(AnotherCustomTestExecutionListener.class);

    public void testPlanExecutionStarted(TestPlan testPlan) {

        log.info(() -> "Started to execute test plan");

    }

    public void testPlanExecutionFinished(TestPlan testPlan) {

        log.info(() -> "Finished executing test plan");

    }

    public void dynamicTestRegistered(TestIdentifier testIdentifier) {

        log.info(() -> "Registered a dynamic test");

    }

    public void executionSkipped(TestIdentifier testIdentifier, String reason) {

        log.info(() -> "Skipped a test");

    }

    public void executionStarted(TestIdentifier testIdentifier) {

        log.info(() -> "Started executing a test");

    }

    public void executionFinished(TestIdentifier testIdentifier, TestExecutionResult testExecutionResult) {

        log.info(() -> "Finished executing a test");

    }

    public void reportingEntryPublished(TestIdentifier testIdentifier, ReportEntry entry) {

        log.info(() -> "Published a reporting entry");

    }

}
