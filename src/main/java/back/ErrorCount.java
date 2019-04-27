package back;

import java.util.ArrayList;

public class ErrorCount {
    private int allErrors = 0;
    private ArrayList<Integer> detectedErrorsIndexes = new ArrayList<>();
    private ArrayList<Integer> correctedErrorsIndexes = new ArrayList<>();

    public int getAllErrors() {
        return allErrors;
    }

    public void setAllErrors(int allErrors) {
        this.allErrors = allErrors;
    }

    public int getDetectedErrors() {
        return detectedErrorsIndexes.size();
    }

    public int getUndetectedErrors() {
        return allErrors - this.detectedErrorsIndexes.size();
    }

    public int getCorrectedErrors() {
        return correctedErrorsIndexes.size();
    }

    public ArrayList<Integer> getDetectedErrorsIndexes() {
        return detectedErrorsIndexes;
    }

    public void setDetectedErrorsIndexes(ArrayList<Integer> detectedErrorsIndexes) {
        this.detectedErrorsIndexes = detectedErrorsIndexes;
    }

    public ArrayList<Integer> getCorrectedErrorsIndexes() {
        return correctedErrorsIndexes;
    }

    public void setCorrectedErrorsIndexes(ArrayList<Integer> correctedErrorsIndexes) {
        this.correctedErrorsIndexes = correctedErrorsIndexes;
    }
}
