package com.example.bank_back_end.services;


import com.example.bank_back_end.exceptions.ResourceNotFoundException;
import com.example.bank_back_end.models.Withdrawal;
import com.example.bank_back_end.repositories.WithdrawalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class WithdrawalService {


    @Autowired
    WithdrawalRepository withdrawalRepository;

    public ArrayList<Withdrawal> getWithdrawalsByAccountId(Long id){

       return new ArrayList<Withdrawal>( withdrawalRepository.findAllByPayerId( id ));



    }  public Optional<Withdrawal> getWithdrawalById(Long id){
        return withdrawalRepository.findById( id );



        }

        public Withdrawal createWithdrawal(Withdrawal withdrawal, Long withdrawalId){
        withdrawal.setPayerId( withdrawalId );
        return withdrawalRepository.save( withdrawal );


        }

        public Withdrawal updateWithdrawal(Withdrawal withdrawal, Long withdrawalId){
        withdrawal.setId( withdrawalId );
        return withdrawalRepository.save( withdrawal );

        }

        public void deleteWithdrawal(Long withdrawalId){

        withdrawalRepository.deleteById( withdrawalId );
        }


        public void verifyWithdrawalId(Long withdrawalId, String message) throws
                ResourceNotFoundException { Optional<Withdrawal> withdrawal =  getWithdrawalById( withdrawalId );

                if( ! withdrawal.isPresent())
                    throw new ResourceNotFoundException( message );

        }


}
