package pl.sda.rafal.zientara.programowanie2.lesson4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ByteFileComparator {
    private final static int BUFFER_SIZE = 10;
    private char[] buffer1 = new char[BUFFER_SIZE];
    private char[] buffer2 = new char[BUFFER_SIZE];
    private int read1;
    private int read2;
    private FileReader reader1;
    private FileReader reader2;

    long startTime;

    public boolean compareByBytes(File png, File possibleDuplicate) {
        try {
            startTime = System.currentTimeMillis();
            reader1 = new FileReader(png);
            reader2 = new FileReader(possibleDuplicate);
            do {
                read1 = reader1.read(buffer1);
                read2 = reader2.read(buffer2);
                if (read1 != read2) {
                    return duplicateNotFoundConfimed();
                } else {
                    for (int i = 0; i < buffer1.length; i++) {
                        char c1 = buffer1[i];
                        char c2 = buffer2[i];
                        if (c1 != c2) {
                            return duplicateNotFoundConfimed();
                        }
                    }
                }
            } while (read1 != -1 && read2 != -1);
            closeReaders();
            reportDuplicateFound();
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("Nie ma takiego pliku!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Błąd odczytu!");
            e.printStackTrace();
        }

        return false;
    }

    private boolean duplicateNotFoundConfimed() throws IOException {
        closeReaders();
        reportWasteOfTime();
        return false;
    }

    private void closeReaders() throws IOException {
        reader1.close();
        reader2.close();
    }

    private void reportDuplicateFound() {
        printReport("Duplikat! Znaleziono po [ms] ");
    }

    private void reportWasteOfTime() {
        System.out.println("Inne! " + read1 + "!=" + read2);
        String text = "Nie duplikat! Zmarnowałem [ms] ";
        printReport(text);
    }

    private void printReport(String text) {
        long diff = getTimeDiff();
        System.out.println(text + diff);
    }

    private long getTimeDiff() {
        long end = System.currentTimeMillis();
        return end - startTime;
    }
}
