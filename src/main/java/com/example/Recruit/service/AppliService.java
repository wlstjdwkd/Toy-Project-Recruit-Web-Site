package com.example.Recruit.service;

import com.example.Recruit.model.AppliEntity;
import com.example.Recruit.persistence.AppliRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AppliService {
    @Autowired
    private AppliRepository appliRepository;

    public List<AppliEntity> create(final AppliEntity entity){
        validate(entity);
        appliRepository.save(entity);
        return appliRepository.findByUserId(entity.getUserId());
    }

    public List<AppliEntity> retrieveAppli(final String id){
        return appliRepository.findByPostId(id);
    }

    public List<AppliEntity> update(final AppliEntity entity){
        if(appliRepository.existsById(entity.getId())){
            appliRepository.save(entity);
        }
        else{
            throw new RuntimeException("Unknown id");
        }
        return appliRepository.findByPostId(entity.getPostId());
    }

    public void validate(final AppliEntity entity){
        if(entity == null){
            throw new RuntimeException("Entity cannot be null");
        }
        if(entity.getUserId()==null){
            throw new RuntimeException("Unknow user");
        }
    }
}
