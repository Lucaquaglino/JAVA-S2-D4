package Esercizio;

import java.util.ArrayList;
import java.util.List;

class Order {
	private List<Product> products;

	public Order() {
		products = new ArrayList<>();
	}

	public void aggiungiProdotto(Product product) {
		products.add(product);
	}

	public boolean categoriaContenuta(String category) {
		return products.stream().allMatch(product -> product.getCategory().equals(category));
	}

	@Override
	public String toString() {
		return "Ordine:" + " " + products;
	}
}