package com.iiht.training.loan.boundary;


import com.iiht.training.loan.entity.Loan;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static com.iiht.training.loan.utils.MasterData.getLoan;
import static com.iiht.training.loan.utils.MasterData.randomStringWithSize;
import static com.iiht.training.loan.utils.TestUtils.boundaryTestFile;
import static com.iiht.training.loan.utils.TestUtils.currentTest;
import static com.iiht.training.loan.utils.TestUtils.testReport;
import static com.iiht.training.loan.utils.TestUtils.yakshaAssert;

@ExtendWith(SpringExtension.class)
public class BoundaryTest {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @AfterAll
    public static void afterAll() {
        testReport();
    }

    @Test
    public void testFirstNameNotBlank() throws Exception {
        Loan loan = getLoan();
        loan.setFirstName("");
        Set<ConstraintViolation<Loan>> violations = validator.validate(loan);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testFirstNameNotNull() throws Exception {
        Loan loan = getLoan();
        loan.setFirstName(null);
        Set<ConstraintViolation<Loan>> violations = validator.validate(loan);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testFirstNameMinThree() throws Exception {
        Loan loan = getLoan();
        loan.setFirstName(randomStringWithSize(2));
        Set<ConstraintViolation<Loan>> violations = validator.validate(loan);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testFirstNameMaxFifty() throws Exception {
        Loan loan = getLoan();
        loan.setFirstName(randomStringWithSize(51));
        Set<ConstraintViolation<Loan>> violations = validator.validate(loan);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testLastNameNotBlank() throws Exception {
        Loan loan = getLoan();
        loan.setLastName("");
        Set<ConstraintViolation<Loan>> violations = validator.validate(loan);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testLastNameNotNull() throws Exception {
        Loan loan = getLoan();
        loan.setLastName(null);
        Set<ConstraintViolation<Loan>> violations = validator.validate(loan);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testLastNameMinThree() throws Exception {
        Loan loan = getLoan();
        loan.setLastName(randomStringWithSize(2));
        Set<ConstraintViolation<Loan>> violations = validator.validate(loan);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testLastNameMaxFifty() throws Exception {
        Loan loan = getLoan();
        loan.setLastName(randomStringWithSize(51));
        Set<ConstraintViolation<Loan>> violations = validator.validate(loan);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testEmailNotBlank() throws Exception {
        Loan loan = getLoan();
        loan.setEmail("");
        Set<ConstraintViolation<Loan>> violations = validator.validate(loan);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testEmailNotNull() throws Exception {
        Loan loan = getLoan();
        loan.setEmail(null);
        Set<ConstraintViolation<Loan>> violations = validator.validate(loan);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testEmailFormatNotValid() throws Exception {
        Loan loan = getLoan();
        loan.setEmail(randomStringWithSize(10));
        Set<ConstraintViolation<Loan>> violations = validator.validate(loan);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testAmountNotNull() throws Exception {
        Loan loan = getLoan();
        loan.setAmount(null);
        Set<ConstraintViolation<Loan>> violations = validator.validate(loan);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testInterestNotNull() throws Exception {
        Loan loan = getLoan();
        loan.setInterest(null);
        Set<ConstraintViolation<Loan>> violations = validator.validate(loan);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testTermNotBlank() throws Exception {
        Loan loan = getLoan();
        loan.setTerm("");
        Set<ConstraintViolation<Loan>> violations = validator.validate(loan);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testTermNotNull() throws Exception {
        Loan loan = getLoan();
        loan.setTerm(null);
        Set<ConstraintViolation<Loan>> violations = validator.validate(loan);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }
}
