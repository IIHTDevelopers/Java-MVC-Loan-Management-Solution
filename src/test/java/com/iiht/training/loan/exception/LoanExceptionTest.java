package com.iiht.training.loan.exception;

import com.iiht.training.loan.controller.LoanController;
import com.iiht.training.loan.entity.Loan;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.iiht.training.loan.utils.MasterData.getLoan;
import static com.iiht.training.loan.utils.TestUtils.currentTest;
import static com.iiht.training.loan.utils.TestUtils.exceptionTestFile;
import static com.iiht.training.loan.utils.TestUtils.testReport;
import static com.iiht.training.loan.utils.TestUtils.yakshaAssert;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class LoanExceptionTest {

    @InjectMocks
    private LoanController loanController;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(loanController).build();
    }

    @After
    public void afterAll() {
        testReport();
    }

    @Test
    public void testExceptionSaveLoanValidation() throws Exception {
        Loan loan = getLoan();
        loan.setFirstName(null);
        MvcResult result = this.mockMvc.perform(post("/loan")
                        .flashAttr("loan", loan))
                .andReturn();
        yakshaAssert(currentTest(), (result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"), exceptionTestFile);
    }

    @Test
    public void testExceptionUpdateLoan() throws Exception {
        Loan loan = getLoan();
        loan.setLastName(null);
        MvcResult result = this.mockMvc.perform(post("/loan/" + loan.getId())
                        .flashAttr("loan", loan))
                .andReturn();
        yakshaAssert(currentTest(), (result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"), exceptionTestFile);
    }

}
