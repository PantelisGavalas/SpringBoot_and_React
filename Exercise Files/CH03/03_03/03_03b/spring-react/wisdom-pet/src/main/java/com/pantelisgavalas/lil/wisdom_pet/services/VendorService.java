package com.pantelisgavalas.lil.wisdom_pet.services;

import com.pantelisgavalas.lil.wisdom_pet.data.entities.VendorEntity;
import com.pantelisgavalas.lil.wisdom_pet.data.repositories.VendorRepository;
import com.pantelisgavalas.lil.wisdom_pet.web.errors.NotFoundException;
import com.pantelisgavalas.lil.wisdom_pet.web.models.Vendor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VendorService {

    private final VendorRepository vendorRepository;

    public VendorService(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    // GET all Operation
    public List<Vendor> getAllVendors() {
        List<Vendor> vendors = new ArrayList<>();
        Iterable<VendorEntity> entities = this.vendorRepository.findAll();
        entities.forEach(entity -> {
            vendors.add(this.translateDbToWeb(entity));
        });
        return vendors;
    }

    // GET Operation
    public Vendor getVendor(long id) {
        Optional<VendorEntity> optional = this.vendorRepository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundException("Vendor not found with id: " + id);
        }
        else {
            return this.translateDbToWeb(optional.get());
        }
    }

    // POST - PUT Operations
    public Vendor createOrUpdateVendor(Vendor vendor) {
        VendorEntity entity = this.translateWebToDb(vendor);
        entity = this.vendorRepository.save(entity);
        return this.translateDbToWeb(entity);
    }

    // DELETE Operation
    public void deleteVendor(long id) {
        this.vendorRepository.deleteById(id);
    }

    // Helper methods for Web <--> DB data exchange
    private VendorEntity translateWebToDb(Vendor vendor) {
        VendorEntity entity = new VendorEntity();
        entity.setId(vendor.getVendorId()==null?0: vendor.getVendorId());
        entity.setName(vendor.getName());
        entity.setContact(vendor.getContact());
        entity.setPhone(vendor.getPhoneNumber());
        entity.setEmail(vendor.getEmailAddress());
        entity.setAddress(vendor.getAddress());
        return entity;
    }

    private Vendor translateDbToWeb(VendorEntity entity) {
        return new Vendor(entity.getId(), entity.getName(), entity.getContact(), entity.getPhone(), entity.getEmail(), entity.getAddress());
    }
}
