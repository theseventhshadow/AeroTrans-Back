package com.aerotrans.ms_aerotrans_transfers.repository;

import com.aerotrans.ms_aerotrans_transfers.model.Transfer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
    List<Transfer> findByClienteOid(String clienteOid);
}