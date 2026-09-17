package com.aerotrans.ms_aerotrans_catalogs.service;

import com.aerotrans.ms_aerotrans_catalogs.dto.ZoneResponse;
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
}