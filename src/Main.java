import javax.swing.*;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Double.parseDouble;

public class Main {
    public static void main(String[] args) {

        boolean continueMainProgram = true;

        Map<String, Double> currencyValues = new HashMap<>();
        currencyValues.put("Dólar", 4168.00);
        currencyValues.put("Euro", 4552.08);
        currencyValues.put("LibraEsterlina", 5269.50);
        currencyValues.put("Yen", 28.88);
        currencyValues.put("Won", 3.17);

        DecimalFormat formatValues = new DecimalFormat("#.##");

        String[] mainOptions = {"Convertir divisas", "Convertir temperatura", "Salir"};
        String[] currencyOptions = {
                "Pesos a Dólar",
                "Pesos a Euros",
                "Pesos a Libras Esterlinas",
                "Pesos a Yen Japonés",
                "Pesos a Won Sur Coreano",
                "Dólar a Pesos",
                "Euros a Pesos",
                "Libras Esterlinas a Pesos",
                "Yen Japonés a Pesos",
                "Won Sur Coreano a Pesos"
        };
        String[] temperatureOptions = {
                "Celsius a Farenheit",
                "Celsius a Kelvin",
                "Farenheit a Celsius",
                "Farenheit a Kelvin",
                "Kelvin a Celsius",
                "Kelvin a Farenheit"
        };

        while(continueMainProgram){
            int optionSelected = JOptionPane.showOptionDialog(
                    null,
                    "¿Que quieres hacer?",
                    "ONE - Conversor",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    mainOptions,
                    mainOptions[0]);

            double value = 0.00;
            boolean cotinueSecondaryProgram = true;

            switch (optionSelected) {
                case 0: // Convertir divisas
                    while(cotinueSecondaryProgram){
                        String selectedCurrencyOption = (String) JOptionPane.showInputDialog(
                                null,
                                "Elija una opción", "Conversor de divisas",
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                currencyOptions,
                                currencyOptions[0]);

                        if(selectedCurrencyOption == null){
                            cotinueSecondaryProgram = false;
                        } else {
                            try{
                                value = enterValueToConvert();
                                convertCurrency(currencyValues, formatValues, value, selectedCurrencyOption);

                                if(getConfirmContinueProgram("¿Desea seguir usando el convertidor de divisas?") == JOptionPane.NO_OPTION) {
                                    cotinueSecondaryProgram = false;
                                    showMessageDialog("Convertidor de divisas finalizado");
                                }
                            }catch (Exception e){
                                System.out.println(e);
                                showMessageDialog("Valor no válido");
                            }
                        }
                    }
                    break;
                case 1: // Convertir temperatura
                    while(cotinueSecondaryProgram){
                        String selectedTemperatureOption = (String) JOptionPane.showInputDialog(
                                null,
                                "Elija una opción", "Conversor de temperatura",
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                temperatureOptions,
                                temperatureOptions[0]);

                        if(selectedTemperatureOption == null){
                            cotinueSecondaryProgram = false;
                        } else {
                            try{
                                value = enterValueToConvert();
                                convertTemperature(formatValues, value, selectedTemperatureOption);

                                if(getConfirmContinueProgram("¿Desea seguir usando el convertidor de temperatura?") == JOptionPane.NO_OPTION) {
                                    cotinueSecondaryProgram = false;
                                    showMessageDialog("Convertidor de temperatura finalizado");
                                }
                            }catch (Exception e){
                                System.out.println(e);
                                showMessageDialog("Valor no válido");
                            }
                        }
                    }
                    break;
                default: // Salir
                    showMessageDialog("Programa finalizado");
                    continueMainProgram = false;
                    System.exit(0);
                    break;

            }

        }

    }

    private static int getConfirmContinueProgram(String message) {
        return JOptionPane.showConfirmDialog(null, message, "Confirmar", JOptionPane.YES_NO_OPTION);
    }

    private static double enterValueToConvert() {
        return parseDouble(JOptionPane.showInputDialog(null, "Ingrese el valor a convertir"));
    }

    private static void convertCurrency(Map<String, Double> currencyValues, DecimalFormat formatValues, double value, String selectedCurrencyOption) {
        if(selectedCurrencyOption.equals("Pesos a Dólar")) {
            showMessageDialog("$ " + value + " pesos son $" + formatValues.format(value / currencyValues.get("Dólar")) + " Dólares");
        } else if (selectedCurrencyOption.equals("Pesos a Euros")){
            showMessageDialog("$ " + value + " pesos son €" + formatValues.format(value / currencyValues.get("Euro")) + " Euros");
        } else if (selectedCurrencyOption.equals("Pesos a Libras Esterlinas")){
            showMessageDialog("$ " + value + " pesos son £" + formatValues.format(value / currencyValues.get("LibraEsterlina")) + " Libras esterlinas");
        } else if (selectedCurrencyOption.equals("Pesos a Yen Japonés")){
            showMessageDialog("$ " + value + " pesos son ¥" + formatValues.format(value / currencyValues.get("Yen")) + " Yenes Japoneses");
        } else if (selectedCurrencyOption.equals("Pesos a Won Sur Coreano")){
            showMessageDialog("$ " + value + " pesos son ₩" + formatValues.format(value / currencyValues.get("Won")) + " Wones Surcoreanos");
        } else if (selectedCurrencyOption.equals("Dólar a Pesos")){
            showMessageDialog("$ " + value + " dólares son $" + formatValues.format(value * currencyValues.get("Dólar")) + " pesos");
        } else if (selectedCurrencyOption.equals("Euros a Pesos")){
            showMessageDialog("€ " + value + " euros son $" + formatValues.format(value * currencyValues.get("Euro")) + " pesos");
        } else if (selectedCurrencyOption.equals("Libras Esterlinas a Pesos")){
            showMessageDialog("£ " + value + " Libras esterlinas son $" + formatValues.format(value * currencyValues.get("LibraEsterlina")) + " pesos");
        } else if (selectedCurrencyOption.equals("Yen Japonés a Pesos")){
            showMessageDialog("¥ " + value + " Yenes Japoneses son $" + formatValues.format(value * currencyValues.get("Yen")) + " pesos");
        } else {
            showMessageDialog("₩ " + value + " Wones Surcoreanos son $" + formatValues.format(value * currencyValues.get("Won")) + " pesos");
        }
    }

    private static void convertTemperature(DecimalFormat formatValues, double value, String selectedTemperatureOption) {
        if(selectedTemperatureOption.equals("Celsius a Farenheit")) {
            showMessageDialog(value + " °C son " + formatValues.format((value * ((double) 9 /5)) + 32) + " °F");
        } else if(selectedTemperatureOption.equals("Celsius a Kelvin")) {
            showMessageDialog(value + " °C son " + formatValues.format(value + 273.15) + " °K");
        } else if(selectedTemperatureOption.equals("Farenheit a Celsius")) {
            showMessageDialog(value + " °F son " + formatValues.format((value - 32) * (double) 5/9) + " °C");
        } else if(selectedTemperatureOption.equals("Farenheit a Kelvin")) {
            showMessageDialog(value + " °F son " + formatValues.format(((value - 32) * (double) 5/9) + 273.15) + " °K");
        } else if(selectedTemperatureOption.equals("Kelvin a Celsius")) {
            showMessageDialog(value + " °K son " + formatValues.format(value - 273.15) + " °C");
        } else {
            showMessageDialog(value + " °K son " + formatValues.format(((value - 273.15) * (double) 9 / 5) + 32) + " °F");
        }

        }

    public static void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(
                null,
                message);
    }
}


