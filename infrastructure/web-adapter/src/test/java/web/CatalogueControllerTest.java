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
import web.errors.ValidationCodeErreur;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
      mockMvc.perform(post("/catalogue/v1/produits")
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
          .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))
          .andExpect(jsonPath("$.title").value("Requête invalide"))
          .andExpect(jsonPath("$.erreurs[0].field").value("sku"))
          .andExpect(jsonPath("$.erreurs[0].code").value(ValidationCodeErreur.REQUIRED));
      verifyNoInteractions(gestionnaireDeProduits);
    }

    @Test
    public void refuser_la_creation_avec_une_famille_invalide() throws Exception {
      mockMvc.perform(post("/catalogue/v1/produits")
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
        .andExpect(status().isBadRequest())
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))
        .andExpect(jsonPath("$.title").value("Requête invalide"))
        .andExpect(jsonPath("$.erreurs[0].field").value("famille"))
        .andExpect(jsonPath("$.erreurs[0].code").value(ValidationCodeErreur.INVALID_ENUM));
      verifyNoInteractions(gestionnaireDeProduits);
    }

    @Test
    public void retourner_toutes_les_erreurs_de_validation() throws Exception {
      mockMvc.perform(
        post("/catalogue/v1/produits")
          .contentType(MediaType.APPLICATION_JSON)
          .content("""
            {
              "sku": "",
              "nom": "Perceuse",
              "famille": "INFORMATIQUE",
              "marque": "Bosch",
              "prixUnitaire": -10
            }"""))
          .andExpect(status().isBadRequest())
          .andExpect(jsonPath("$.erreurs").value(hasSize(3)))
          .andExpect(jsonPath("$.erreurs[*].field").value(hasItems("sku", "famille", "prixUnitaire")))
          .andExpect(jsonPath("$.erreurs[?(@.field == 'sku')].code").value(hasItem(ValidationCodeErreur.REQUIRED.name())))
          .andExpect(jsonPath("$.erreurs[?(@.field == 'famille')].code").value(hasItem(ValidationCodeErreur.INVALID_ENUM.name())))
          .andExpect(jsonPath("$.erreurs[?(@.field == 'prixUnitaire')].code").value(hasItem(ValidationCodeErreur.MUST_BE_POSITIVE.name())))
      ;

      verifyNoInteractions(gestionnaireDeProduits);
    }
  }
}