package com.jdawidowska.service.services;

import org.springframework.stereotype.Service;

import com.jdawidowska.service.data.repos.EquipmentRepository;

@Service
public class EquipmentService {

    private final EquipmentRepository equipmentRepo;

    public EquipmentService(EquipmentRepository sprzetRepo) {
        this.equipmentRepo = sprzetRepo;
    }

    public Iterable<Equipment> findAllEquipment() {
        return equipmentRepo.findAll();
    }

    public boolean lendEquipment(Long id) {
        Equipment eq = equipmentRepo.findById(id).orElse(null);
        if (eq != null && eq.getAvailableAmount() > 0) {
            equipmentRepo.lendEquipment(id);
            return true;
        } else {
            return false;
        }
    }

    public boolean returnEquipment(Long id) {
        Equipment eq = equipmentRepo.findById(id).orElse(null);
        if (eq != null && eq.getAvailableAmount() < eq.getTotalAmount()) {
            equipmentRepo.returnEquipment(id);
            return true;
        } else {
            return false;
        }
    }
}