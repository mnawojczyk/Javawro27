package pl.sda.rafal.zientara.programowanie2.lesson4;

import java.io.*;

public class NamesMain {

    public static void main(String[] args) {
        System.out.println(new File("").getAbsolutePath());
        String[] maleNames = getNamesFromFile("maleNames.txt");
        String[] femaleNames = getNamesFromFile("femaleNames.txt");

//        Circle.class.getSimpleName()
        String report = getResultReport(maleNames, femaleNames);
        saveReportToFile(report);
    }

    private static void saveReportToFile(String report) {
        File file = new File("report.txt");
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(report);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        openTextEditor(file);
    }

    private static void openTextEditor(File file) {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("open", file.getAbsolutePath());
        try {
            processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getResultReport(String[] maleNames, String[] femaleNames) {
        StringBuilder builder = new StringBuilder();
        for (char i = 'A'; i <= 'Z'; i++) {
            char currentLetter = i;
            int maleCount = getNamesWithLetterCount(currentLetter, maleNames);
            int femaleCount = getNamesWithLetterCount(currentLetter, femaleNames);
            if (maleCount > femaleCount) {
                builder.append("Wiêcej mêskich imion na literê ").append(currentLetter);
            } else if (maleCount < femaleCount) {
                builder.append("Wiêcej ¿eñskich imion na literê ").append(currentLetter);
            } else {
                builder.append("Tyle samo imion na literê ").append(currentLetter);
            }
            if (i < 'Z')
                builder.append(System.lineSeparator());

        }
        System.out.println(builder);
        return builder.toString();
    }

    private static int getNamesWithLetterCount(char firstLetter, String[] names) {
        int count = 0;
//        String firstChar = String.valueOf(firstLetter);//sposob 1
        for (String name : names) {
//            if(name.startsWith(firstChar)) {//sposob 1
            if (name.charAt(0) == firstLetter) {//sposob 2
                count++;
            }
        }
        return count;
    }

    private static String[] getNamesFromFile(String fileName) {
        String allNames = getFileData(fileName);
        return getNormalizedNames(allNames);
    }

    private static String getFileData(String fileName) {
        String allNames = "";
        File file = new File(fileName);
        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            allNames = bufferedReader.readLine();
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allNames;
    }

    private static String[] getNormalizedNames(String allNames) {
        String[] names = allNames.split(",");

        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            name = name.trim();//"      Krzyœ " -> " Krzyœ"
//            System.out.println("#" + name + "#");
            names[i] = name;
        }
        return names;
    }
}
