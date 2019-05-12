package back.errorService;

import back.ErrorCount;

import java.util.ArrayList;
import java.util.Random;

public class ErrorGenerator {

    public String generateError(int errorLevel, String code) {

        ArrayList<Integer> errorNumber = numberErrorGenerator(errorLevel, code.length());
        ErrorCount.setAllErrors(errorNumber.size());
        ErrorCount.setAllErrorsIndexes(errorNumber);

        return makeMistake(errorNumber, code);
    }

    private String makeMistake(ArrayList<Integer> errorIndexes, String code) {
        StringBuilder editCode = new StringBuilder(code);
        for (Integer al : errorIndexes) {
            if (code.charAt(al) == '1') {
                editCode.setCharAt(al, '0');
            } else
                editCode.setCharAt(al, '1');
        }
        return editCode.toString();
    }

    private ArrayList<Integer> numberErrorGenerator(int errorLevel, int length) {

        ArrayList<Integer> resultList = new ArrayList<>();
        int numberOfErrors = (int) Math.ceil(length * errorLevel / 100);
        int randomNumber;
        Random random = new Random();

        while (numberOfErrors > resultList.size()) {
            randomNumber = random.nextInt(length - 1);
            if (!resultList.contains(randomNumber))
                resultList.add(randomNumber);
        }

        return resultList;
    }
}
