package com.iiht.training.loan.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.iiht.training.loan.entity.Loan;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MasterData {

    private static Random rnd = new Random();

    public static Loan getLoan() {
        Loan loan = new Loan();
        loan.setId(rnd.nextLong());
        loan.setFirstName(randomStringWithSize(10));
        loan.setLastName(randomStringWithSize(10));
        loan.setEmail(randomStringWithSize(10) + "@" + "gmail.com");
        loan.setAmount(rnd.nextDouble());
        loan.setInterest(rnd.nextLong());
        loan.setTerm(randomStringWithSize(5));
        return loan;
    }

    public static List<Loan> getLoanList(int size) {
        Long id = 0L;
        List<Loan> loans = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Loan loan = new Loan();
            loan.setId(rnd.nextLong());
            loan.setFirstName(randomStringWithSize(10));
            loan.setLastName(randomStringWithSize(10));
            loan.setEmail(randomStringWithSize(10) + "@" + "gmail.com");
            loan.setAmount(rnd.nextDouble());
            loan.setInterest(rnd.nextLong());
            loan.setTerm(randomStringWithSize(5));
            loans.add(loan);
        }
        return loans;
    }

    public static String randomStringWithSize(int size) {
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rnd = new Random();
        String s = "";
        for (int i = 0; i < size; i++) {
            s = s + (String.valueOf(alphabet.charAt(rnd.nextInt(alphabet.length()))));
        }
        return s;
    }

    public static boolean randomBoolean() {
        String alphabet = "1234567890";
        Random rnd = new Random();
        return rnd.nextInt(alphabet.length()) % 2 == 0;
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            final String jsonContent = mapper.writeValueAsString(obj);

            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
