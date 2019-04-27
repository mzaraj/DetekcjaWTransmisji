package back;

public interface ErrorDetection {

    String encode(String message);

    String findErrors(String message);

    String decode(String message);
}