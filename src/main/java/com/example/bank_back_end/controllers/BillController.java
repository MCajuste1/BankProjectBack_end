package com.example.bank_back_end.controllers;
import com.example.bank_back_end.models.Bill;
import com.example.bank_back_end.models.errors.AdvancedResponse;
import com.example.bank_back_end.models.errors.SimpleResponse;
import com.example.bank_back_end.services.AccountService;
import com.example.bank_back_end.services.BillService;
import com.example.bank_back_end.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class BillController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private BillService billService;

    @Autowired
    private CustomerService customerService;
//get bills for a certain id
    @GetMapping(value = "/accounts/{accountId}/bills")
    public ResponseEntity<?> getAllBillsById(@PathVariable Long accountId) {
        accountService.verifyAccountId(accountId, "error fetching bills, account doesn't exist");
        List<Bill> allBills = billService.getAllBillsByAccountId(accountId);
        return new ResponseEntity<>(
                AdvancedResponse.Create(allBills, "Success", HttpStatus.OK.value()),
                HttpStatus.OK);
    }

//get bill by id
    @GetMapping(value = "/bills/{billId}")
    public ResponseEntity<?> getBillById(@PathVariable Long billId) {
        billService.verifyBill(billId, "error fetching bill with id: " + billId);
        Optional<Bill> bill = billService.getBill(billId);
        return new ResponseEntity<>(
                AdvancedResponse.Create(bill, "Success", HttpStatus.OK.value()),
                HttpStatus.OK);
    }

//get all bills for customer
    @GetMapping(value = "/customer/{customerId}/bills")
    public ResponseEntity<?> getAllBillsByCustomer(@PathVariable Long customerId) {
        customerService.verifyCustomerId(customerId, "error fetching bills");

        List<Bill> customerBills = billService.getAllBillsByCustomerId(customerId);

        return new  ResponseEntity<>(
                AdvancedResponse.Create(customerBills, "Success", HttpStatus.OK.value()),
                HttpStatus.OK);
    }

//create bill
    @PostMapping("/accounts/{accountId}/bills")
    public ResponseEntity<?> createBill(@Valid @RequestBody Bill bill, @PathVariable Long accountId) {
        accountService.verifyAccountId(accountId, "Error creating bill, Account not found");
        Bill bill1 = billService.createBill(bill, accountId);
        return new ResponseEntity<>(
                AdvancedResponse.Create(
                        bill1,
                        "Created bill and added it to the account",
                        HttpStatus.CREATED.value()),
                HttpStatus.CREATED);
    }

//update bill
    @RequestMapping(value = "/bills/{billId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateBill(@Valid @RequestBody Bill bill, @PathVariable Long billId){
        billService.verifyBill(billId, "Bill ID does not exist");
        billService.updateBill(bill, billId);
        return new ResponseEntity<>(
                SimpleResponse.Create("Accepted bill modifications", HttpStatus.ACCEPTED.value()),
                HttpStatus.ACCEPTED);
    }

//delete bill
    @RequestMapping(value = "/bills/{billId}",method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteBill(@PathVariable Long billId) {
        billService.verifyBill(billId, "This id does not exist in bills");

        billService.deleteBill(billId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
