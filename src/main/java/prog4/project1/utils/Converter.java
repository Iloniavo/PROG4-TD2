package prog4.project1.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

public class Converter {
    public static String convertToBase64 (MultipartFile file) throws IOException {
        try {
            byte[] photoBytes = file.getBytes();
            return Base64.getEncoder().encodeToString(photoBytes);
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static String getFullName (String firstName, String lastName){
        return firstName + " " + lastName;
    }

    public static String generateRandomNumber() {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            stringBuilder.append(random.nextInt(10));
        }
        return stringBuilder.toString();
    }

    public static List<String> transformMultiplePhoneNumbers(String input){
        List<String> elementsList = new ArrayList<>();

        String[] elementsArray = input.split(",\\s*");
        Collections.addAll(elementsList, elementsArray);

        return elementsList;
    }

    public static String concatenatePhoneNumbers(List<String> phoneNumbers){
        return String.join(", ", phoneNumbers);
    }


}
