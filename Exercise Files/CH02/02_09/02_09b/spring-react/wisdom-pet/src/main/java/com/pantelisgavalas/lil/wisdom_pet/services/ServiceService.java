package com.pantelisgavalas.lil.wisdom_pet.services;

import com.pantelisgavalas.lil.wisdom_pet.data.entities.ServiceEntity;
import com.pantelisgavalas.lil.wisdom_pet.data.repositories.ServiceRepository;
import com.pantelisgavalas.lil.wisdom_pet.web.errors.NotFoundException;
import com.pantelisgavalas.lil.wisdom_pet.web.models.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class ServiceService {

    private final ServiceRepository serviceRepository;

    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    // GET all Operation
    public List<Service> getAllServices() {
        List<Service> services = new ArrayList<>();
        Iterable<ServiceEntity> entities = this.serviceRepository.findAll();
        entities.forEach(entity -> {
            services.add(this.translateDbToWeb(entity));
        });
        return services;
    }

    // GET Operation
    public Service getService(long id) {
        Optional<ServiceEntity> optional = this.serviceRepository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundException("Service not found with id: " + id);
        }
        else {
            return this.translateDbToWeb(optional.get());
        }
    }

    // POST - PUT Operations
    public Service createOrUpdateService(Service service) {
        ServiceEntity entity = this.translateWebToDb(service);
        entity = this.serviceRepository.save(entity);
        return this.translateDbToWeb(entity);
    }

    // DELETE Operation
    public void deleteService(long id) {
        this.serviceRepository.deleteById(id);
    }

    // Helper methods for Web <--> DB data exchange
    private ServiceEntity translateWebToDb(Service service) {
        ServiceEntity entity = new ServiceEntity();
        entity.setId(service.getServiceId()==null?0: service.getServiceId());
        entity.setName(service.getName());
        entity.setPrice(service.getPrice());
        return entity;
    }

    private Service translateDbToWeb(ServiceEntity entity) {
        return new Service(entity.getId(),entity.getName(),entity.getPrice());
    }
}
