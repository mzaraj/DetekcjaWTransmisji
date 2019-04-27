package back;

public class ErrorCount {
    private static int allErrors = 0;
    private static int detectedErrors = 0;

    public static int getAllErrors() {
        return allErrors;
    }

    public static void setAllErrors(int allErrors) {
        ErrorCount.allErrors = allErrors;
    }

    public static int getDetectedErrors() {
        return detectedErrors;
    }

    public static void setDetectedErrors(int detectedErrors) {
        ErrorCount.detectedErrors = detectedErrors;
    }

    public static int getUndetectedErrors() {
        return allErrors - detectedErrors;
    }
}
