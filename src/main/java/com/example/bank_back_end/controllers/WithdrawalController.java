package com.example.bank_back_end.controllers;


import com.example.bank_back_end.models.Withdrawal;
import com.example.bank_back_end.models.errors.AdvancedResponse;
import com.example.bank_back_end.models.errors.SimpleResponse;
import com.example.bank_back_end.services.AccountService;
import com.example.bank_back_end.services.WithdrawalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class WithdrawalController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private WithdrawalService withdrawalService;

    //get withdrawal accounts

    @GetMapping(value = "accounts/{accountId}/withdrawals")

    public ResponseEntity<?> getAllWithdrawals(@PathVariable Long accountId){
        accountService.verifyAccountId( accountId,"error fetching withdrawals son" );
        List<Withdrawal> allWithdrawals = withdrawalService.getWithdrawalsByAccountId( accountId );
        return new ResponseEntity<>( AdvancedResponse.Create( allWithdrawals,"Success", HttpStatus.OK.value()),
                HttpStatus.OK);

    }
    // get withdrawal by id

@GetMapping(value = "withdrawal/{withdrawalId}")

    public ResponseEntity<?> getWithdrawalsById(@PathVariable Long withdrawalId){
        withdrawalService.verifyWithdrawalId( withdrawalId,"error fetching withdrawal" );
        Optional<Withdrawal> withdrawal = withdrawalService.getWithdrawalById( withdrawalId );
        return new ResponseEntity<>( AdvancedResponse.Create( withdrawal,"Success", HttpStatus.OK.value() ),
                HttpStatus.OK);
    }
// creates withdrawal
    @PostMapping(value = "accounts/{accountId}/accounts")
    public ResponseEntity<?> createWithdrawal(@Valid @RequestBody Withdrawal withdrawal, @PathVariable Long accountId){
        withdrawalService.verifyWithdrawalId( accountId,"Error retrieving withdrawal");
        Withdrawal withdrawal1 = withdrawalService.createWithdrawal( withdrawal,accountId);

        return new ResponseEntity<>( AdvancedResponse.Create( withdrawal1,"Withdrawal applied",HttpStatus.CREATED.value() ),
                HttpStatus.CREATED);


        //update withdrawal
    }
    @PutMapping(value = "/withdrawal/{withdrawal}")
    public ResponseEntity<?> updateWithdrawal(@Valid @RequestBody Withdrawal withdrawal, @PathVariable Long withdrawalId){
      withdrawalService.verifyWithdrawalId(withdrawalId, "Accepted withdrawal");
      withdrawalService.updateWithdrawal( withdrawal,withdrawalId );
      return new ResponseEntity<>( SimpleResponse.Create( "Accepted new withdrawals",HttpStatus.ACCEPTED.value() ),
              HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/withdrawal/{withdrawalId}")
    public ResponseEntity<?> deleteWithdrawal( @PathVariable Long withdrawalId){

        withdrawalService.verifyWithdrawalId( withdrawalId,"Withdrawal not found" );

        withdrawalService.deleteWithdrawal( withdrawalId );

        return  new ResponseEntity<>( HttpStatus.NO_CONTENT );
    }

//    @GetMapping(value = "/withdrawal/{withdrawalId}")
//    public ResponseEntity<?> getWithdrawalsByAccountId(@PathVariable Long withdrawalId){
//        withdrawalService.getWithdrawalsByAccountId( withdrawalId );
//
//
//        return new ResponseEntity<>( HttpStatus.OK );
//
//    }


}


