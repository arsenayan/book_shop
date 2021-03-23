package com.inconcept.task.service;

import com.inconcept.task.persistence.entity.PublisherEntity;
import com.inconcept.task.persistence.repository.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PublisherService {
    private final PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public void saveAllPublisher(List<PublisherEntity> sets) {
        publisherRepository.saveAll(sets);
    }

    public PublisherEntity searchPublisher(String fio) {
        return publisherRepository.findOneByFio(fio);
    }

    public void save(PublisherEntity publisherEntity) {
        publisherRepository.save(publisherEntity);
    }
}
