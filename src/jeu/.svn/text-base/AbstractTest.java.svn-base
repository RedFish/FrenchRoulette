package jeu;

public abstract class AbstractTest {

    protected int testCount;
    protected int testFailed;
    protected int testSucceeded;
    protected int testExpected;
    protected String testName;

    public AbstractTest() {
        this.testName = this.getClass().getName();
        init();
        runTests();
        printSummary();
    }

    protected void init() {
        this.testCount = 0;
        this.testFailed = 0;
        this.testSucceeded = 0;
        this.testExpected = 0;
    }

    public int getTestCount() {
        return this.testCount;
    }

    public int getTestFailedCount() {
        return this.testFailed;
    }

    public int getTestSucceededCount() {
        return this.testSucceeded;
    }

    public int getTestExpectedCount() {
        return this.testExpected;
    }

    protected void printSummary() {
        System.out.println("------------------------------------");
        System.out.println("-- " + this.testName);
        System.out.println("Test run: " + this.testCount + "\tTest failed: " + this.testFailed + "\tTest succeeded: " + this.testSucceeded + "\t[expected to be run:" + this.testExpected + "]");
    }

    protected void assertTrue(boolean condition, String msg) {
        this.testCount++;

        try {
            if (!condition) {
                this.testFailed++;
                Exception e = new Exception();
                StackTraceElement methodName = e.getStackTrace()[1];
                System.err.println("Test Failed: [" + msg + "] in " + methodName);
            } else {
                this.testSucceeded++;
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.testFailed++;
        }
    }

    protected void assertEquals(Object expected, Object other) {
        this.testCount++;

        try {
            if (!expected.equals(other)) {
                this.testFailed++;
                Exception e = new Exception();
                StackTraceElement methodName = e.getStackTrace()[1];
                System.err.println("Test Failed: Expected [" + expected + "] got [" + other + "] in " + methodName);
            } else {
                this.testSucceeded++;
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.testFailed++;
        }
    }
    
    
    protected void assertNotEquals(Object expected, Object other) {
        this.testCount++;

        try {
            if (expected.equals(other)) {
                this.testFailed++;
                Exception e = new Exception();
                StackTraceElement methodName = e.getStackTrace()[1];
                System.err.println("Test Failed: Expected [" + expected + "] got [" + other + "] in " + methodName);
            } else {
                this.testSucceeded++;
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.testFailed++;
        }
    }



    protected void assertEquals(int expected, int other) {
        this.testCount++;
        try {
            if (expected != other) {
                this.testFailed++;
                Exception e = new Exception();
                StackTraceElement methodName = e.getStackTrace()[1];
                System.err.println("Test Failed: Expected [" + expected + "] got [" + other + "] in " + methodName);
            } else {
                this.testSucceeded++;
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.testFailed++;
        }

    }

    protected void assertEquals(long expected, long other) {
        this.testCount++;
        try {
            if (expected != other) {
                this.testFailed++;
                Exception e = new Exception();
                StackTraceElement methodName = e.getStackTrace()[1];
                System.err.println("Test Failed: Expected [" + expected + "] got [" + other + "] in " + methodName);
            } else {
                this.testSucceeded++;
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.testFailed++;
        }

    }

    protected void assertEquals(double expected, double other) {
        this.testCount++;
        if (Double.isNaN(other) || Math.abs(expected - other) > 0.00001) {
            this.testFailed++;
            Exception e = new Exception();
            StackTraceElement methodName = e.getStackTrace()[1];
            System.err.println("Test Failed: Expected [" + expected + "] got [" + other + "] in " + methodName);
        } else {
            this.testSucceeded++;
        }
    }


    protected void assertNull(Object obj) {
        this.testCount++;
        if (obj != null) {
            this.testFailed++;
            Exception e = new Exception();
            StackTraceElement methodName = e.getStackTrace()[1];
            System.err.println("Test Failed: Expected [" + null + "] got [" + obj + "] in " + methodName);
        } else {
            this.testSucceeded++;
        }
    }


    protected abstract void runTests();
}
