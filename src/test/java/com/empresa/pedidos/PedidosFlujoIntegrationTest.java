package com.empresa.pedidos;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.empresa.pedidos.dominio.eventos.PedidoProcesadoEvent;
import com.empresa.pedidos.infraestructura.notificaciones.NotificacionEmail;
import com.empresa.pedidos.infraestructura.notificaciones.NotificacionLog;

@SpringBootTest
@AutoConfigureMockMvc
class PedidosFlujoIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockitoSpyBean
    private NotificacionEmail notificacionEmail;
    @MockitoSpyBean
    private NotificacionLog notificacionLog;

    @Test
    void postProcesaPersistePublicaEventoYPermiteConsultar() throws Exception {
        MvcResult resultado = mockMvc.perform(post("/api/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"tipo":"EXPRESS","subtotal":100.00}
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.tipo").value("EXPRESS"))
                .andExpect(jsonPath("$.estado").value("PROCESADO"))
                .andExpect(jsonPath("$.costo").value(130.00))
                .andReturn();

        JsonNode pedidoCreado = objectMapper.readTree(resultado.getResponse().getContentAsString());
        long id = pedidoCreado.get("id").asLong();

        mockMvc.perform(get("/api/pedidos/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id));

        verify(notificacionEmail).notificar(any(PedidoProcesadoEvent.class));
        verify(notificacionLog).notificar(any(PedidoProcesadoEvent.class));

        mockMvc.perform(get("/api/pedidos/{id}", id + 999))
                .andExpect(status().isNotFound());
    }
}
