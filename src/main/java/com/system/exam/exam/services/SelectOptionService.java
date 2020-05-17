package com.system.exam.exam.services;

import com.system.exam.exam.entities.SelectOption;
import com.system.exam.exam.repositories.SelectOptionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SelectOptionService {
    private SelectOptionRepo selectOptionRepo;

    @Autowired
    public SelectOptionService(SelectOptionRepo selectOptionRepo) {
        this.selectOptionRepo = selectOptionRepo;
    }

    public List<SelectOption> getSelectOptions(){
        return this.selectOptionRepo.findAll();
    }

    public SelectOption createSelectOption(SelectOption selectOption){
        return this.selectOptionRepo.save(selectOption);
    }


}
