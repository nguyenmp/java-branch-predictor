package com.nguyenmp.branch_predictor;

import java.io.*;
import java.util.Scanner;

public class Driver {

    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        BranchPredictor predictor = new SingleBitBimodalPredictor(1024);

        int total = 0;
        int correct = 0;

        Scanner scanner1 = new Scanner(new FileInputStream("/home/mark/ramdisk/serv1.trace"));
        scanner1.nextLine();
        scanner1.useDelimiter("\\A");
        String input = scanner1.next();
        ByteArrayInputStream stream = new ByteArrayInputStream(input.getBytes());
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        char[] addressBuffer = new char[7];
        char[] addressBufferP = new char[8];
        int prediction = 0;
        int magic = 0;
        while ((input = reader.readLine()) != null) {
            prediction = input.charAt(input.length() - 1) - '0';
            total++;
            long pc = Long.parseLong(input.substring(2, input.indexOf(' ')), 16);
            if (predictor.getPrediction(pc).equals(prediction)) {
                correct++;
            }
            predictor.update(pc, BranchPrediction.fromInt(prediction));
        }
        long end = System.currentTimeMillis();
        System.out.printf("%d\n", (end - start));
        System.out.printf("Total: %d Correct: %d Miss Rate: %g\n", total, correct, (double) (total - correct) / (double) total);
    }
}