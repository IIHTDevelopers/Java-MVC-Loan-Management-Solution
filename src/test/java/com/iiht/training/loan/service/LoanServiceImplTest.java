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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static com.iiht.training.loan.utils.MasterData.asJsonString;
import static com.iiht.training.loan.utils.MasterData.getLoan;
import static com.iiht.training.loan.utils.MasterData.getLoanList;
import static com.iiht.training.loan.utils.MasterData.randomBoolean;
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
        Pageable pageable = PageRequest.of(0, 5);
        List<Loan> loans = getLoanList(5);
        Page<Loan> expected = new PageImpl<>(loans);
        when(loanRepository.findAll(pageable)).thenReturn(expected);
        Page<Loan> actual = loanService.getAllLoan(pageable);
        yakshaAssert(currentTest(),
                (asJsonString(expected.getContent()).equals(asJsonString(actual.getContent()))
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
        List<Loan> loans = getLoanList(5);
        Pageable pageable = PageRequest.of(0, 5);
        Page<Loan> expected = new PageImpl<>(loans);
        when(loanRepository.findByKeyword(keyword, pageable)).thenReturn(expected);
        Page<Loan> actual = loanService.getByKeyword(keyword, pageable);
        yakshaAssert(currentTest(),
                (asJsonString(expected.getContent()).equals(asJsonString(actual.getContent()))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testServiceUpdateStatus() throws Exception {
        Loan actual = getLoan();
        boolean expected = loanService.updateLoanStatus(randomBoolean(), actual.getId());
        yakshaAssert(currentTest(), (expected), businessTestFile);
    }
}
