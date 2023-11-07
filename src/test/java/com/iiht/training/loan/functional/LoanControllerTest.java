package com.iiht.training.loan.functional;

import com.iiht.training.loan.controller.LoanController;
import com.iiht.training.loan.entity.Loan;
import com.iiht.training.loan.service.LoanService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static com.iiht.training.loan.utils.MasterData.getLoan;
import static com.iiht.training.loan.utils.MasterData.getLoanList;
import static com.iiht.training.loan.utils.MasterData.randomStringWithSize;
import static com.iiht.training.loan.utils.TestUtils.businessTestFile;
import static com.iiht.training.loan.utils.TestUtils.currentTest;
import static com.iiht.training.loan.utils.TestUtils.testReport;
import static com.iiht.training.loan.utils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class LoanControllerTest {

    @Mock
    private LoanService loanService;

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
    public void testHome() throws Exception {
        List<Loan> loans = getLoanList(5);
        when(loanService.getAllLoan()).thenReturn(loans);
        MvcResult result = this.mockMvc.perform(get("/")).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView().getViewName().contentEquals("loan_list") ? true : false, businessTestFile);
    }

    @Test
    public void testHomeSearch() throws Exception {
        String keyword = randomStringWithSize(1);
        List<Loan> loans = getLoanList(5);
        when(loanService.getByKeyword(keyword)).thenReturn(loans);
        MvcResult result = this.mockMvc.perform(get("/search")
                .param("keyword", keyword)).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView().getViewName().contentEquals("loan_list") ? true : false, businessTestFile);
    }

    @Test
    public void testSaveLoan() throws Exception {
        Loan loan = getLoan();
        MvcResult result = this.mockMvc.perform(post("/loan")
                .flashAttr("loan", loan)).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView().getViewName().contentEquals("redirect:/loan") ? true : false, businessTestFile);
    }

    @Test
    public void testCreateLoanForm() throws Exception {
        List<Loan> loans = getLoanList(5);
        when(loanService.getAllLoan()).thenReturn(loans);
        MvcResult result = this.mockMvc.perform(get("/loan/new")).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView().getViewName().contentEquals("create_loan") ? true : false, businessTestFile);
    }

    @Test
    public void testEditLoanForm() throws Exception {
        Loan loan = getLoan();
        when(loanService.getLoanById(loan.getId())).thenReturn(loan);
        MvcResult result = this.mockMvc.perform(get("/loan/edit/" + loan.getId())).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView().getViewName().contentEquals("edit_loan") ? true : false, businessTestFile);
    }

    @Test
    public void testUpdateLoan() throws Exception {
        Loan loan = getLoan();
        when(loanService.getLoanById(loan.getId())).thenReturn(loan);
        MvcResult result = this.mockMvc.perform(post("/loan/" + loan.getId())
                .flashAttr("loan", loan)).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView().getViewName().contentEquals("redirect:/loan") ? true : false, businessTestFile);
    }

    @Test
    public void testDeleteLoan() throws Exception {
        Loan loan = getLoan();
        when(loanService.getLoanById(loan.getId())).thenReturn(loan);
        MvcResult result = this.mockMvc.perform(get("/loan/" + loan.getId())).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView().getViewName().contentEquals("redirect:/loan") ? true : false, businessTestFile);
    }
}
