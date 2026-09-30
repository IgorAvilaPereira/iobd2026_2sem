package controller;

import java.util.Base64;

public abstract class Controller {
    public static final String PATH_TEMPLATES = "src/main/resources/templates/";
    public static final String PATH_PUBLIC = "/src/main/resources/public/";


    public static String encodeToBase64(byte[] arquivo) {
        return Base64.getEncoder().encodeToString(arquivo);
    }
}
