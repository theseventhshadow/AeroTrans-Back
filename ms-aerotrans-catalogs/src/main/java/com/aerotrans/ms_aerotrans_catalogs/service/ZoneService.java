package com.aerotrans.ms_aerotrans_catalogs.service;

import com.aerotrans.ms_aerotrans_catalogs.dto.ZoneRequest;
import com.aerotrans.ms_aerotrans_catalogs.dto.ZoneResponse;
import com.aerotrans.ms_aerotrans_catalogs.exception.ResourceNotFoundException;
import com.aerotrans.ms_aerotrans_catalogs.model.Zone;
import com.aerotrans.ms_aerotrans_catalogs.repository.ZoneRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ZoneService {

    private final ZoneRepository zoneRepository;

    public ZoneService(ZoneRepository zoneRepository) {
        this.zoneRepository = zoneRepository;
    }

    public List<ZoneResponse> findAll() {
        return zoneRepository.findAll().stream()
                .map(zone -> new ZoneResponse(zone.getId(), zone.getNombre(), zone.getComuna()))
                .toList();
    }

    public ZoneResponse findById(Long id) {
        return toResponse(zoneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found: " + id)));
    }

    @Transactional
    public ZoneResponse create(ZoneRequest request) {
        return toResponse(zoneRepository.save(new Zone(request.nombre(), request.comuna())));
    }

    @Transactional
    public ZoneResponse update(Long id, ZoneRequest request) {
        Zone zone = zoneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found: " + id));
        zone.setNombre(request.nombre());
        zone.setComuna(request.comuna());
        return toResponse(zone);
    }

    @Transactional
    public void delete(Long id) {
        if (!zoneRepository.existsById(id)) {
            throw new ResourceNotFoundException("Zone not found: " + id);
        }
        zoneRepository.deleteById(id);
    }

    private ZoneResponse toResponse(Zone zone) {
        return new ZoneResponse(zone.getId(), zone.getNombre(), zone.getComuna());
    }
}