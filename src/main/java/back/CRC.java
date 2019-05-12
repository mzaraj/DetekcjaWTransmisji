package back;

import java.util.ArrayList;

public class CRC {

    public static final int CRC16 = 1;
    public static final int CRC16REVERSE = 2;
    public static final int SDLC = 3;
    public static final int SDLCREVERSE = 4;

    private static final int BLOCK_LENGTH = 8;
    private static final int TOTAL_LENGTH = BLOCK_LENGTH + 16;


    public String send(String receivedData, int method) {

        String allData = receivedData;

        int modulo = allData.length() % BLOCK_LENGTH;
        if (modulo != 0) {
            StringBuilder stringBuilder = new StringBuilder(allData);
            for (int i = 0; i < BLOCK_LENGTH - modulo; i++) {
                stringBuilder.append("0");
            }
            allData = stringBuilder.toString();
        }

        int dataBytes = allData.length() / BLOCK_LENGTH;
        int data[][] = new int[dataBytes][BLOCK_LENGTH];
        for (int i = 0; i < dataBytes; i++) {
            for (int j = 0; j < BLOCK_LENGTH; j++)
                data[i][j] = Integer.parseInt(allData.substring(BLOCK_LENGTH * i + j, BLOCK_LENGTH * i + j + 1));
        }

        int crc[][] = new int[dataBytes][TOTAL_LENGTH];
        for (int i = 0; i < dataBytes; i++) {
            crc[i] = generateCRC(data[i], method, true);
        }

        StringBuilder sb = new StringBuilder();

        for (int block[] : crc) {
            for (int digit : block) {
                sb.append(digit);
            }
        }
        return sb.toString();
    }

    public String receive(String allData, int method) {

        int dataBytes = allData.length() / TOTAL_LENGTH;
        int data[][] = new int[dataBytes][TOTAL_LENGTH];
        for (int i = 0; i < dataBytes; i++) {
            for (int j = 0; j < TOTAL_LENGTH; j++)
                data[i][j] = Integer.parseInt(allData.substring(TOTAL_LENGTH * i + j, TOTAL_LENGTH * i + j + 1));
        }

        ArrayList<Integer> detectedErrors = new ArrayList<>();

        for (int i = 0; i < dataBytes; i++) {
            int remainder[] = generateCRC(data[i], method, false);
            for (int j = 0; j < remainder.length; j++) {
                if (remainder[j] != 0) {
                    detectedErrors.add(i);
                    break;
                }
//                if (j == remainder.length - 1)
//                    System.out.println("No errors");
            }
        }
        ErrorCount.setDetectedErrorsIndexes(detectedErrors);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dataBytes; i++)
            for (int j = 0; j < BLOCK_LENGTH; j++)
                sb.append(data[i][j]);
        return sb.toString();
    }

    private int[] generateCRC(int partialData[], int method, boolean toCRC) {
        int generator[] = new int[17];
        int crc[] = new int[TOTAL_LENGTH];
        switch (method) {
            case CRC16:
                generator = new int[]{1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1};
                break;
            case CRC16REVERSE:
                generator = new int[]{1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1};
                break;
            case SDLC:
                generator = new int[]{1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1};
                break;
            case SDLCREVERSE:
                generator = new int[]{1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1};
                break;
        }

        int divident[] = new int[TOTAL_LENGTH];
        for (int i = 0; i < partialData.length; i++) {
            divident[i] = partialData[i];
        }
        int remainder[] = new int[TOTAL_LENGTH];
        System.arraycopy(divident, 0, remainder, 0, divident.length);

        int current = 0;
        do {
            for (int i = 0; i < generator.length; i++)
                remainder[current + i] = remainder[current + i] ^ generator[i];

            while (remainder[current] == 0 && current != remainder.length - 1)
                current++;

        } while (remainder.length - current >= generator.length);

        if (toCRC) {
            for (int i = 0; i < divident.length; i++) {
                crc[i] = divident[i] ^ remainder[i];
            }
            return crc;
        } else {
            return remainder;
        }
    }
}
