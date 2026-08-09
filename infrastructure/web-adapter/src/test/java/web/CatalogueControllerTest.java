package web;

import com.github.app.application.usecases.PourGererLeCatalogue;
import com.github.app.domain.PourGererLesProduits;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;

import static org.mockito.Mockito.verifyNoInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Catalogue Controller")
@WebMvcTest(CatalogueController.class)
@MockitoBean(types = PourGererLeCatalogue.class)
public class CatalogueControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private PourGererLesProduits gestionnaireDeProduits;

  @Nested
  @DisplayName("Lors de la creation")
  class LorsDeLaCreation {

    @Test
    public void refuser_la_creation_avec_un_sku_invalide() throws Exception {
      mockMvc.perform(post("/catalogue/produits")
        .contentType(MediaType.APPLICATION_JSON)
        .content("""
          {
            "sku": "",
            "nom": "Perceuse sans fil 500W",
            "famille": "outillage",
            "marque": "Bosch",
            "prixUnitaire": 89.90,
            "attributsSpecifiques": { "puissance": "500W", "poidsKg": 1.8 }
          }"""))
          .andExpect(status().isBadRequest())
          .andDo(print());
      verifyNoInteractions(gestionnaireDeProduits);
    }

    @Test
    public void refuser_la_creation_avec_une_famille_invalide() throws Exception {
      mockMvc.perform(post("/catalogue/produits")
      .contentType(MediaType.APPLICATION_JSON)
      .content("""
        {
          "sku": "TRN-PRC-500W",
          "nom": "Perceuse sans fil 500W",
          "famille": "informatique",
          "marque": "Bosch",
          "prixUnitaire": 89.90,
          "attributsSpecifiques": { "puissance": "500W", "poidsKg": 1.8 }
        }"""))
        .andExpect(status().isBadRequest()).andDo(print());
      verifyNoInteractions(gestionnaireDeProduits);
    }
  }
}