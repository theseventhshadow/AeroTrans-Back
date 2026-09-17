package com.aerotrans.bff;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.mockJwt;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.springSecurity;

import com.aerotrans.bff.client.TransfersClient;
import com.aerotrans.bff.dto.TransferResponse;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {
    "TENANT_ID=00000000-0000-0000-0000-000000000000",
    "API_AUDIENCE=api://aerotrans-api"
})
class BffSecurityTests {

    @Autowired
    private ApplicationContext context;

    private WebTestClient webTestClient;

    @MockitoBean
    private TransfersClient transfersClient;

    @BeforeEach
    void setUp() {
        webTestClient = WebTestClient.bindToApplicationContext(context)
                .apply(springSecurity())
                .build();

        when(transfersClient.listTransfers(any())).thenReturn(Flux.just(new TransferResponse(
                1L,
                "TR-001",
                "client-oid",
                "SCL",
                "AEROPUERTO",
                10L,
                2L,
                "2026-09-20T10:00:00",
                "PENDIENTE",
                new BigDecimal("180.00")
        )));

        when(transfersClient.updateStatus(anyLong(), anyString())).thenReturn(Mono.just(new TransferResponse(
                1L,
                "TR-001",
                "client-oid",
                "SCL",
                "AEROPUERTO",
                10L,
                2L,
                "2026-09-20T10:00:00",
                "CONFIRMADO",
                new BigDecimal("180.00")
        )));
    }

    @Test
    void getTransfersWithoutTokenShouldReturnUnauthorized() {
        webTestClient.get().uri("/api/transfers")
                .exchange()
                .expectStatus().isUnauthorized();
    }

    @Test
    void getTransfersWithClientRoleShouldReturnOk() {
        WebTestClient client = WebTestClient.bindToApplicationContext(context)
                .apply(springSecurity())
                .apply(mockJwt().jwt(jwt -> jwt.claim("roles", List.of("ROLE_CLIENTE")).claim("oid", "client-oid")))
                .build();

        client.get().uri("/api/transfers")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void putTransferStatusWithClientRoleShouldReturnForbidden() {
        WebTestClient client = WebTestClient.bindToApplicationContext(context)
                .apply(springSecurity())
                .apply(mockJwt().jwt(jwt -> jwt.claim("roles", List.of("ROLE_CLIENTE")).claim("oid", "client-oid")))
                .build();

        client.put().uri("/api/transfers/1/status?status=CONFIRMADO")
                .exchange()
                .expectStatus().isForbidden();
    }

    @Test
    void putTransferStatusWithOperatorRoleShouldReturnOk() {
        WebTestClient client = WebTestClient.bindToApplicationContext(context)
                .apply(springSecurity())
                .apply(mockJwt()
                        .jwt(jwt -> jwt.claim("roles", List.of("ROLE_OPERADOR")).claim("oid", "operator-oid"))
                        .authorities(new SimpleGrantedAuthority("ROLE_OPERADOR")))
                .build();

        client.put().uri("/api/transfers/1/status?status=CONFIRMADO")
                .exchange()
                .expectStatus().isOk();
    }
}
