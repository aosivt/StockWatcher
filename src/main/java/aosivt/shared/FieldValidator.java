package aosivt.shared;

public class FieldValidator {

    public static boolean isValidData(String data) {
        try {
            Integer.parseInt(data);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        return true;
    }
    public static boolean isValidMinMax(String data) {

        if (Integer.parseInt(data)<10 || Integer.parseInt(data)>100) {
            return true;
        }

        return false;
    }
}