package com.system.exam.exam.services;

import com.system.exam.exam.entities.MCOption;
import com.system.exam.exam.repositories.MCOptionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MCOptionService {

    private MCOptionRepo mcOptionRepo;

    @Autowired
    public MCOptionService(MCOptionRepo mcOptionRepo) {
        this.mcOptionRepo = mcOptionRepo;
    }

    public MCOption createQuestionOption(MCOption mcOption){
        return this.mcOptionRepo.save(mcOption);
    }

    public MCOption getQuestionOptionById(long id){
        Optional<MCOption> data = this.mcOptionRepo.findById(id);

        return data.isPresent() ? data.get() : null;
    }


    public MCOption updateQuestionOption(MCOption mcOption){
        return this.mcOptionRepo.save(mcOption);
    }
}
