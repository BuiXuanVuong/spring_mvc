package com.mvc.service.impl;

import com.mvc.converter.NewConverter;
import com.mvc.dto.NewDTO;
import com.mvc.entity.CategoryEntity;
import com.mvc.entity.NewEntity;
import com.mvc.repository.CategoryRepository;
import com.mvc.repository.NewRepository;
import com.mvc.service.INewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewService implements INewService {

    @Autowired
    private NewRepository newRepository;

    @Autowired
    private NewConverter newConverter;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<NewDTO> findAll(Pageable pageable) {
        List<NewDTO> models = new ArrayList<>();
        List<NewEntity> entities = newRepository.findAll(pageable).getContent();
        for (NewEntity item : entities) {
            NewDTO newDTO = newConverter.toDTO(item);
            models.add(newDTO);
        }
        return models;
    }

    @Override
    public int getTotalItem() {
        return (int) newRepository.count();
    }

    @Override
    public NewDTO findById(long id) {
        NewEntity entity = newRepository.findOne(id);
        return newConverter.toDTO(entity);
    }

    @Override
    @Transactional
    public NewDTO save(NewDTO dto) {
        CategoryEntity category = categoryRepository.findOneByCode(dto.getCategoryCode());
        NewEntity newEntity = new NewEntity();
        if (dto.getId() != null) {
            NewEntity oldNew = newRepository.findOne(dto.getId());
            oldNew.setCategory(category);
            newEntity = newConverter.toEntity(oldNew, dto);
        } else {
            newEntity = newConverter.toEntity(dto);
            newEntity.setCategory(category);
        }
        return newConverter.toDTO(newRepository.save(newEntity));
    }

}

