package br.com.saks.imobiliaria;

import br.com.saks.imobiliaria.model.Cliente;
import br.com.saks.imobiliaria.model.Imovel;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Instant;
import java.util.Date;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.yml")
class ImobiliariaApplicationTests {

	@Test
	void contextLoads() {
	}

        @Autowired
        private MockMvc mockMvc;
        
        @Autowired
        private ObjectMapper objectMapper;
        
        
        // JUNIT IMOVEL
        @Test
        void createImovel() throws Exception {
            Imovel imo = new Imovel();
            imo.setTitulo("Teste");
            imo.setDescricao("Descricao teste.");
            imo.setDataCriacao(new Date());
            imo.setValor(10000.00);
            imo.setStatus(1);
            mockMvc.perform(MockMvcRequestBuilders.post("/imovel")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(imo)))
                    .andExpect(status().isOk());
        }
        
        @Test
        void getAllImovel() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get("/imovel")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        }
        
        @Test
        void getByIdImovel() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get("/imovel/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        }
        
        @Test
        void updateImovel() throws Exception {
            Imovel imo = new Imovel();
            imo.setTitulo("Teste Updated");
            imo.setDescricao("Descricao teste.");
            imo.setDataCriacao(new Date());
            imo.setValor(10000.00);
            imo.setStatus(1);
            mockMvc.perform(MockMvcRequestBuilders.put("/imovel/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(imo)))
                .andExpect(status().isOk());
        }
        
	/*@Test
        void deleteImovel() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.delete("/imovel/{id}", 3))
                .andExpect(status().isOk());
        }*/
        
        //JUNIT CLIENTE
        @Test
        void createCliente() throws Exception {
            Cliente cli = new Cliente();
            cli.setNome("Teste");
            cli.setEmail("teste@teste.com");
            cli.setSenha("123");
            cli.setTelefone("0123456789");
            mockMvc.perform(MockMvcRequestBuilders.post("/cliente")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(cli)))
                    .andExpect(status().isOk());
        }
        
        @Test
        void getAllCliente() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get("/cliente")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        }
        
        @Test
        void getByIdCliente() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get("/cliente/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        }
        
        @Test
        void updateCliente() throws Exception {
            Cliente cli = new Cliente();
            cli.setNome("Teste Updated");
            cli.setEmail("teste@teste.com");
            cli.setSenha("123");
            cli.setTelefone("0123456789");
            mockMvc.perform(MockMvcRequestBuilders.put("/cliente/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cli)))
                .andExpect(status().isOk());
        }
        
	/*@Test
        void deleteCliente() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.delete("/cliente/{id}", 3))
                .andExpect(status().isOk());
        }*/
}
