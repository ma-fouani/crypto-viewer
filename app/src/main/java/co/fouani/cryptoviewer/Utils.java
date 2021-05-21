package co.fouani.cryptoviewer;

import java.text.DecimalFormat;

//Reusable code is collected here.
public class Utils {

    //static function to remove unneeded decimal part
    public static String convertToShortFloat(String data) {
        try {
            if (data != null) {
                Float f = Float.parseFloat(data);
                //if number is less than 1, then it's crucial to show more decimal part
                DecimalFormat df = new DecimalFormat(f < 1 ? "0.0000" : "0.00");
                df.setMaximumFractionDigits(2);
                return df.format(f);
            }
        } catch (Exception ignored) {
            //Log.e("CoinsRecyAdapter", "convertToShortFloat: " + e.getMessage(), e);
        }
        return data;
    }

    //static function to remove unneeded decimal part
    public static String convertMoneyBig(String data) {
        try {
            if (data != null) {
                String symbol = "";
                int floatingPoint = data.indexOf(".");
                Double f = Double.parseDouble(data);
                if (floatingPoint > 0 && floatingPoint <= 3) {
                    symbol = "";
                } else if (floatingPoint > 3 && floatingPoint <= 6) {
                    symbol = "K";
                    f /= 1000;
                } else if (floatingPoint > 6 && floatingPoint <= 9) {
                    symbol = "M";
                    f /= 1000000;
                } else if (floatingPoint > 9 && floatingPoint <= 12) {
                    symbol = "B";
                    f /= 1000000000;
                } else {
                    symbol = "T";
                    f /= 1000000000;
                    f /= 1000;
                }
                //if number is less than 1, then it's crucial to show more decimal part
                DecimalFormat df = new DecimalFormat(f < 1 ? "0.0000" : "0.00");
                df.setMaximumFractionDigits(2);
                return "$ " + df.format(f) + symbol;
            }
        } catch (Exception ignored) {
            //Log.e("CoinsRecyAdapter", "convertToShortFloat: " + e.getMessage(), e);
        }
        return data;
    }

    //static function to remove unneeded decimal part
    public static String convertPercentage(String data) {
        try {
            if (data != null) {
                String result;
                Double f = Double.parseDouble(data);
                DecimalFormat df = new DecimalFormat(f < 1 ? "0.0000" : "0.00");
                df.setMaximumFractionDigits(2);
                result = df.format(f);
                return (result.contains("-") ? "" : "+") + result + "%";
            }
        } catch (Exception ignored) {
        }
        return data;
    }

    //static function to remove unneeded decimal part
    public static String convertMoney(String data) {
        return "$ " + convertToShortFloat(data);
    }

}
