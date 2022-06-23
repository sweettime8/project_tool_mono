package com.mrd.tool.service;

import com.mrd.tool.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

@Component
public class BaseService {

    private ModelMapper modelMapper;

    public ModelMapper getModelMapper(){
        return modelMapper;
    }

    @Autowired
    public void setModelMapper(final ModelMapper pModelMapper){
        modelMapper = pModelMapper;
    }

    public void fillBaseInfo(final BaseEntity entity, final String account, boolean isUpdate){
        if(!isUpdate){
            entity.setCreatedBy(account.toLowerCase());
            entity.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            entity.setModifyAt(new Timestamp(System.currentTimeMillis()));
            entity.setModifyBy(account.toLowerCase());
            entity.setIsDeleted(0);
        }else{
            entity.setModifyBy(account.toLowerCase());
            entity.setModifyAt(new Timestamp(System.currentTimeMillis()));
        }
    }
}
