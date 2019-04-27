package back;

import java.util.ArrayList;

public class ErrorCount {
    private int allErrors = 0;
    private int detectedErrors = 0;
    private int correctedErrors = 0;
    private ArrayList<Integer> errorsIndexes;

    public int getAllErrors() {
        return allErrors;
    }

    public void setAllErrors(int allErrors) {
        this.allErrors = allErrors;
    }

    public int getDetectedErrors() {
        return detectedErrors;
    }

    public void setDetectedErrors(int detectedErrors) {
        this.detectedErrors = detectedErrors;
    }

    public int getUndetectedErrors() {
        return this.allErrors - this.detectedErrors;
    }

    public int getCorrectedErrors() {
        return correctedErrors;
    }

    public void setCorrectedErrors(int correctedErrors) {
        this.correctedErrors = correctedErrors;
    }

    public ArrayList<Integer> getErrorsIndexes() {
        return errorsIndexes;
    }

    public void setErrorsIndexes(ArrayList<Integer> errorsIndexes) {
        this.errorsIndexes = errorsIndexes;
    }
}
