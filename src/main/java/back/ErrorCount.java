package back;

import java.util.ArrayList;

public class ErrorCount {
    private static int allErrors = 0;
    private static ArrayList<Integer> detectedErrorsIndexes = new ArrayList<>();
    private static ArrayList<Integer> correctedErrorsIndexes = new ArrayList<>();
    private static ArrayList<Integer> allErrorsIndexes = new ArrayList<>();

    public static int getAllErrors() {
        return allErrors;
    }

    public static void setAllErrors(int allErrors) {
        ErrorCount.allErrors = allErrors;
    }

    public static int getDetectedErrors() {
        return detectedErrorsIndexes.size();
    }

    public static int getUndetectedErrors() {
        return allErrors - detectedErrorsIndexes.size();
    }

    public static int getCorrectedErrors() {
        return correctedErrorsIndexes.size();
    }

    public static ArrayList<Integer> getDetectedErrorsIndexes() {
        return detectedErrorsIndexes;
    }

    public static void setDetectedErrorsIndexes(ArrayList<Integer> detectedErrorsIndexes) {
        ErrorCount.detectedErrorsIndexes = detectedErrorsIndexes;
    }

    public static ArrayList<Integer> getCorrectedErrorsIndexes() {
        return correctedErrorsIndexes;
    }

    public static void setCorrectedErrorsIndexes(ArrayList<Integer> correctedErrorsIndexes) {
        ErrorCount.correctedErrorsIndexes = correctedErrorsIndexes;
    }

    public static ArrayList<Integer> getAllErrorsIndexes() {
        return allErrorsIndexes;
    }

    public static void setAllErrorsIndexes(ArrayList<Integer> allErrorsIndexes) {
        ErrorCount.allErrorsIndexes = allErrorsIndexes;
    }

    public static void countErrors(String oldString, String newString) {
        allErrors = 0;
        allErrorsIndexes = new ArrayList<>();
        if (!oldString.equals(newString)) {
            if (oldString.length() == newString.length()) {
                for (int i = 0; i < oldString.length(); i++) {
                    if (oldString.charAt(i) != newString.charAt(i)) {
                        allErrors++;
                        allErrorsIndexes.add(i);
                    }
                }
            } else
                System.out.println("Wiadomości różnej długości");
        }
    }
}
