package Esercizio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class Customer {
	private String name;
	private int tier;
	private LocalDate orderDate;
	private List<Product> prodottiOrdinati;

	public Customer(String name, int tier, LocalDate orderDate) {
		this.name = name;
		this.tier = tier;
		this.orderDate = orderDate;
		prodottiOrdinati = new ArrayList<>();
	}

	public void aggiungiProdottiOrdinati(Product product) {
		prodottiOrdinati.add(product);
	}

	public int getTier() {
		return tier;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public List<Product> getProdottiOrdinati() {
		return prodottiOrdinati;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
}