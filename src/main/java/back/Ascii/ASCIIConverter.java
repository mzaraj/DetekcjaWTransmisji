package back.Ascii;

public class ASCIIConverter {

    public String converterToASCI(String text) {

        byte[] bytes = text.getBytes();
        StringBuilder binary = new StringBuilder();

        for (byte b : bytes) {
            int val = b;
            for (int i = 0; i < 8; i++) {
                binary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
        }
        return binary.toString();
    }

    public String convertedToText(String byteText) {

        StringBuilder text = new StringBuilder();
        for (int index = 0; index < byteText.length(); index += 8) {
            String temp = byteText.substring(index, index + 8);
            int num = Integer.parseInt(temp, 2);
            char letter = (char) num;
            text.append(letter);
        }
        return text.toString();
    }

}
