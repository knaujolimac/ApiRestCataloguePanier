package co.catalogue.prod.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.catalogue.prod.rest.controlleur.CatalogueControlleur;
import co.catalogue.prod.rest.exception.CatalogueProdException;
import co.catalogue.prod.rest.modele.dto.CatalogueProduitDTO;
import co.catalogue.prod.rest.modele.dto.PanierAcheteDTO;
import co.catalogue.prod.rest.modele.dto.ProduitDTO;
import co.catalogue.prod.rest.service.CatalogueService;
import co.catalogue.prod.rest.service.PanierService;

@RunWith(SpringJUnit4ClassRunner.class)
public class CatalogueControlleurTest {

	@InjectMocks
	private CatalogueControlleur cat;

	@Mock
	private CatalogueService catalogueService;

	@Mock
	private PanierService panierService;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAfficherCatalogueProduits() throws Exception {

		// on prepare la petit liste
		List<CatalogueProduitDTO> listCatalogue = creerListCatalogueProduits();
		when(catalogueService.getCatalogueProduits()).thenReturn(listCatalogue);

		List<CatalogueProduitDTO> listCatalogue2 = cat.afficherCatalogueProduits().getBody();

		// On verifie que le service a été appellé une fois
		verify(catalogueService).getCatalogueProduits();

		assertEquals("les listes ne sont pas égal", listCatalogue.size(), listCatalogue2.size());

		assertNotNull("Il n'y a pas de produits au catalogue", listCatalogue2);
	}

	@Test
	public void testAfficherDetailProduit() throws CatalogueProdException {
		ProduitDTO prodDTO = new ProduitDTO(1l, "téléphone", 10000.0, "technologique", 15);

		when(catalogueService.getDetailProduitById(1l)).thenReturn(prodDTO);
		ProduitDTO prodRetorno = cat.afficherDetailProduit(1l).getBody();

		verify(catalogueService).getDetailProduitById(1l);

		assertNotNull(prodRetorno);
		assertEquals(prodDTO.getIdProduit(), prodRetorno.getIdProduit());
	}

	@Test
	public void testAjouterProduitPanier() throws CatalogueProdException {
		ProduitDTO prodDTO = new ProduitDTO(1l, "téléphone", 10000.0, "technologique", 15);
		when(panierService.ajouterProduitPanier(1l)).thenReturn(prodDTO);
		ProduitDTO prodRespDTO = cat.ajouterProduitPanier(1l).getBody();

		verify(panierService).ajouterProduitPanier(1l);
		assertEquals("Les produits son égals", prodRespDTO.getIdProduit(), prodDTO.getIdProduit());
	}

	@Test
	public void testEnleverProduitPanier() throws CatalogueProdException {
		ProduitDTO prodDTO = new ProduitDTO(1l, "téléphone", 10000.0, "technologique", 15);
		when(panierService.enleverProduitPanier(1l)).thenReturn(prodDTO);
		cat.enleverProduitPanier(1l).getBody();

		verify(panierService).enleverProduitPanier(1l);
	}

	@Test
	public void testAfficherContenuPanier() throws CatalogueProdException {
		List<PanierAcheteDTO> listPanierAcher = creerListPanierAchete();

		when(panierService.getContenuProduitPanier()).thenReturn(listPanierAcher);

		List<PanierAcheteDTO> listPanierAcher2 = cat.afficherContenuPanier().getBody();

		// On verifie que le service a été appellé une fois
		verify(panierService).getContenuProduitPanier();

		assertEquals("les listes ne sont pas égal", listPanierAcher2.size(), listPanierAcher.size());

		assertNotNull("Il n'y a pas de produits au panier", listPanierAcher2);

	}

	/**
	 * Méthode qui cree une liste de catalogues
	 * 
	 * @return
	 */
	private List<CatalogueProduitDTO> creerListCatalogueProduits() {
		List<CatalogueProduitDTO> listCatalogue = new ArrayList<>();
		long id = 1;
		listCatalogue.add(new CatalogueProduitDTO(id++, "téléphone", "technologique"));
		listCatalogue.add(new CatalogueProduitDTO(id++, "ordinateur", "technologique"));
		listCatalogue.add(new CatalogueProduitDTO(id++, "clavier", "technologique"));
		listCatalogue.add(new CatalogueProduitDTO(id++, "télévision", "technologique"));
		listCatalogue.add(new CatalogueProduitDTO(id++, "pantalon", "vêtements"));
		listCatalogue.add(new CatalogueProduitDTO(id++, "chemise", "technologique"));
		listCatalogue.add(new CatalogueProduitDTO(id++, "sac", "technologique"));

		return listCatalogue;
	}

	private List<PanierAcheteDTO> creerListPanierAchete() {
		List<PanierAcheteDTO> listaPanier = new ArrayList<>();
		long id = 0;

		listaPanier.add(new PanierAcheteDTO(id++, 1l, "téléphone", 2, 20000.0));
		listaPanier.add(new PanierAcheteDTO(id++, 1l, "ordinateur", 1, 2000000.0));
		listaPanier.add(new PanierAcheteDTO(id++, 1l, "pantalon", 2, 100000.0));

		return listaPanier;
	}

}
