package saltstack4j.saltstack.utils;

import java.util.Random;

public class SaltUtils {
    private final static String SUFFIX = " -t 180 --out=json";
    private final static String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXVZ0123456789";

    private SaltUtils() {
    }

    public static String generateCmdList(String destination, String module, String slsName, String pillar) {
        StringBuilder builder = new StringBuilder();

        builder.append("salt -L \"");
        builder.append(destination);
        builder.append("\"");

        builder.append(" state.sls ");
        builder.append(module).append(".").append(slsName);
        builder.append(" pillar='");
        builder.append(pillar);
        builder.append("'");

        builder.append(SUFFIX);
        return builder.toString();
    }

    public static String getRandomString(int length) {
        StringBuffer sb = new StringBuffer();

        int number;
        int alphabetSize = ALPHABET.length();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            number = random.nextInt(alphabetSize);
            sb.append(ALPHABET.charAt(number));
        }

        return sb.toString();
    }
}
