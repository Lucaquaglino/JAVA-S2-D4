package Esercizio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
	public static void main(String[] args) {
		// CREO PRODOTTI
		Product prodotto1 = new Product("Harry potter e la pietra filosofale", "Books", 120);
		Product prodotto2 = new Product("Il Signore degli Anelli - Le due Torri", "Books", 55);
		Product prodotto3 = new Product("Ciccio Bello", "Baby", 60);
		Product prodotto4 = new Product("Pannolini", "Baby", 30);
		Product prodotto5 = new Product("Cintura", "Boys", 30);
		Product prodotto6 = new Product("T-shirt", "Boys", 55);

		// CREO LISTA PRODOTTI
		List<Product> products = new ArrayList<>();
		products.add(prodotto1);
		products.add(prodotto2);
		products.add(prodotto3);
		products.add(prodotto4);
		products.add(prodotto5);
		products.add(prodotto6);

		// LISTA PRODOTTI DELLA CATEGORIA BOOKS CON LIBRI CHE ABBIANO UN PREZZO
		// SUPERIORE AI 100
		List<Product> libroCostoso = products.stream().filter(product -> product.getCategory().equals("Books"))
				.filter(product -> product.getPrice() > 100).collect(Collectors.toList());
		System.out.println("Lista di prodotti Books con prezzo > 100:");
		libroCostoso.forEach(System.out::println);

		// LISTA DI ORDINI FILTRATI PER LA CATEGORIA BABY
		Order order1 = new Order();
		order1.aggiungiProdotto(prodotto3);
		order1.aggiungiProdotto(prodotto4);
		Order order2 = new Order();
		order2.aggiungiProdotto(prodotto4);
		order2.aggiungiProdotto(prodotto6);
		List<Order> orders = new ArrayList<>();
		orders.add(order1);
		orders.add(order2);
		List<Order> ordiniBaby = orders.stream().filter(order -> order.categoriaContenuta("Baby"))
				.collect(Collectors.toList());
		System.out.println("\nLista di ordini con prodotti Baby:");
		ordiniBaby.forEach(System.out::println);

		// LISTA DI PRODOTTI DELLA CATEGORIA BOOKS SCONTATI AL 10%
		List<Product> prodottiBoy = products.stream().filter(product -> product.getCategory().equals("Boys"))
				.map(product -> new Product(product.getName(), product.getCategory(), product.getPrice() * 0.9))
				.collect(Collectors.toList());
		System.out.println("\nLista di prodotti Boys con sconto del 10%:");
		prodottiBoy.forEach(System.out::println);

		// LISTA ORDINI DI CLIENTI DI TIPO TIER 2 PER ACQUISTI EFFETTUATI TRA
		// l'01-febbraio-2021 e l'01-aprile-2021

		Customer customer1 = new Customer("Aldo", 1, LocalDate.of(2021, 3, 10));
		customer1.aggiungiProdottiOrdinati(prodotto1);
		customer1.aggiungiProdottiOrdinati(prodotto2);
		Customer customer2 = new Customer("Giovanni", 2, LocalDate.of(2021, 2, 15));
		customer2.aggiungiProdottiOrdinati(prodotto3);
		customer2.aggiungiProdottiOrdinati(prodotto2);
		Customer customer3 = new Customer("Giacomo", 2, LocalDate.of(2021, 6, 5));
		customer3.aggiungiProdottiOrdinati(prodotto1);

		List<Customer> customers = new ArrayList<>();
		customers.add(customer1);
		customers.add(customer2);
		customers.add(customer3);

		Map<Customer, List<Product>> prodottiOrdinatiPerCliente = customers.stream()
				.filter(customer -> customer.getTier() == 2 && customer.getOrderDate().isAfter(LocalDate.of(2021, 2, 1))
						&& customer.getOrderDate().isBefore(LocalDate.of(2021, 4, 1)))
				.collect(Collectors.groupingBy(customer -> customer, Collectors
						.flatMapping(customer -> customer.getProdottiOrdinati().stream(), Collectors.toList())));
		System.out.println("\nLista di prodotti ordinati dai clienti divisa per cliente:");
		prodottiOrdinatiPerCliente.forEach((customer, customerProdotti) -> {
			System.out.println("Cliente: " + customer.getName());
			customerProdotti.forEach(System.out::println);
		});

	}
}
