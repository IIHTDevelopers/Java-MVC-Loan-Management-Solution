package com.iiht.training.loan.service;

import com.iiht.training.loan.entity.Loan;
import com.iiht.training.loan.repository.LoanRepository;
import com.iiht.training.loan.service.impl.LoanServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static com.iiht.training.loan.utils.MasterData.asJsonString;
import static com.iiht.training.loan.utils.MasterData.getLoan;
import static com.iiht.training.loan.utils.MasterData.getLoanList;
import static com.iiht.training.loan.utils.MasterData.randomStringWithSize;
import static com.iiht.training.loan.utils.TestUtils.businessTestFile;
import static com.iiht.training.loan.utils.TestUtils.currentTest;
import static com.iiht.training.loan.utils.TestUtils.testReport;
import static com.iiht.training.loan.utils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;

public class LoanServiceImplTest {

    @Mock
    private LoanRepository loanRepository;

    @InjectMocks
    private LoanServiceImpl loanService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void afterAll() {
        testReport();
    }

    @Test
    public void testSaveLoan() throws Exception {
        Loan actual = getLoan();
        when(loanRepository.save(actual)).thenReturn(actual);
        Loan expected = loanService.saveLoan(actual);
        yakshaAssert(currentTest(),
                (asJsonString(expected).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testGetAllLoan() throws Exception {
        List<Loan> actual = getLoanList(5);
        when(loanRepository.findAll()).thenReturn(actual);
        List<Loan> expected = loanService.getAllLoan();
        yakshaAssert(currentTest(),
                (asJsonString(expected).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testGetLoanById() throws Exception {
        Loan actual = getLoan();
        when(loanRepository.findById(actual.getId())).thenReturn(Optional.of(actual));
        Loan expected = loanService.getLoanById(actual.getId());
        yakshaAssert(currentTest(),
                (asJsonString(expected).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testUpdateLoan() throws Exception {
        Loan actual = getLoan();
        when(loanRepository.save(actual)).thenReturn(actual);
        Loan expected = loanService.updateLoan(actual);
        yakshaAssert(currentTest(),
                (asJsonString(expected).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testDeleteLoanById() throws Exception {
        Loan actual = getLoan();
        boolean expected = loanService.deleteLoanById(actual.getId());
        yakshaAssert(currentTest(), expected ? true : false, businessTestFile);
    }

    @Test
    public void testGetByKeyword() throws Exception {
        String keyword = randomStringWithSize(1);
        List<Loan> actual = getLoanList(5);
        when(loanRepository.findByKeyword(keyword)).thenReturn(actual);
        List<Loan> expected = loanService.getByKeyword(keyword);
        yakshaAssert(currentTest(),
                (asJsonString(expected).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }
}
