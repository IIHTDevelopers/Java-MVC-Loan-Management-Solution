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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
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
        this.mockMvc = MockMvcBuilders.standaloneSetup(loanController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
    }

    @After
    public void afterAll() {
        testReport();
    }

    @Test
    public void testControllerListLoansHome() throws Exception {
        Pageable pageable = PageRequest.of(0, 5);
        List<Loan> loans = getLoanList(5);
        Page<Loan> loanPage = new PageImpl<>(loans);
        when(loanService.getAllLoan(pageable)).thenReturn(loanPage);
        MvcResult result = this.mockMvc.perform(get("/")).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("list-loan"), businessTestFile);
    }

    @Test
    public void testControllerListLoans() throws Exception {
        Pageable pageable = PageRequest.of(0, 5);
        List<Loan> loans = getLoanList(5);
        Page<Loan> loanPage = new PageImpl<>(loans);
        when(loanService.getAllLoan(pageable)).thenReturn(loanPage);
        MvcResult result = this.mockMvc.perform(get("/list")).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("list-loan"), businessTestFile);
    }

    @Test
    public void testControllerShowFormForAdd() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/showFormForAdd")).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("loan-add"), businessTestFile);
    }

    @Test
    public void testControllerSaveLoan() throws Exception {
        Loan loan = getLoan();
        MvcResult result = this.mockMvc.perform(post("/saveLoan")
                .flashAttr("loan", loan)).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("redirect:/loan/list"), businessTestFile);
    }

    @Test
    public void testControllerShowFormForUpdate() throws Exception {
        Loan loan = getLoan();
        when(loanService.getLoanById(loan.getId())).thenReturn(loan);
        MvcResult result = this.mockMvc.perform(get("/showFormForUpdate")
                .param("loanId", loan.getId().toString())).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("loan-add"), businessTestFile);
    }

    @Test
    public void testControllerShowFormForDeleteLoan() throws Exception {
        Loan loan = getLoan();
        MvcResult result = this.mockMvc.perform(get("/showFormForDelete")
                .param("loanId", loan.getId().toString())).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("redirect:/loan/list"), businessTestFile);
    }

    @Test
    public void testControllerUpdateStatus() throws Exception {
        Loan loan = getLoan();
        MvcResult result = this.mockMvc.perform(get("/updateStatus")
                        .param("id", loan.getId().toString())
                        .param("active", "false"))
                .andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("redirect:/loan/list"), businessTestFile);
    }

    @Test
    public void testControllerSearchLoansWithSearchKey() throws Exception {
        String keyword = randomStringWithSize(5);
        String status = "COMPLETED";
        Pageable pageable = PageRequest.of(0, 5);
        List<Loan> loans = getLoanList(5);
        Page<Loan> loanPage = new PageImpl<>(loans);
        when(loanService.getByKeyword(keyword, pageable)).thenReturn(loanPage);
        MvcResult result = this.mockMvc.perform(get("/search")
                        .param("theSearchName", keyword)
                        .param("status", status))
                .andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("list-loan"), businessTestFile);
    }
}
