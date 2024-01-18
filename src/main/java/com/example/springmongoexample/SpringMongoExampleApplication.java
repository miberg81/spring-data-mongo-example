package com.example.springmongoexample;

import com.example.springmongoexample.model.TVItem;
import com.example.springmongoexample.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
//@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@EnableMongoRepositories
public class SpringMongoExampleApplication implements CommandLineRunner {

	@Autowired
	ItemRepository TVItemRepo;

	List<TVItem> itemList = new ArrayList<>();

	public static void main(String[] args) {
		SpringApplication.run(SpringMongoExampleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Clean up any pervious data
		System.out.println("\nCleaning up old data...");
		TVItemRepo.deleteAll(); // Doesn't delete the collection

		System.out.println("\nCreating items");
		createTVItems();

		System.out.println("\nShowing all tv items");
		showAllTVItems();

		System.out.println("\nGetting item by name");
		getTVItemByName("Crash Landing on You");

		System.out.println("\nGet items by category");
		getItemsByCategory("comedy");

		System.out.println("\nUpdating category name of all items");
		updateCategoryName("drama");

		System.out.println("\nDeleting an item");
		deleteTVItem("30 Rock");

		System.out.println("\nCounting items");
		findCountOfTVItems();
	}

	// C= CREATE
	void createTVItems() {
		TVItemRepo.save(new TVItem("1","Crash Landing on You", 5, "kdrama"));
		TVItemRepo.save(new TVItem("2","Abbot Elementary", 2, "comedy"));
		TVItemRepo.save(new TVItem("3","30 Rock", 2, "comedy"));
		TVItemRepo.save(new TVItem("4","Ted Lasso", 1, "comedy"));
		TVItemRepo.save(new TVItem("5","The good place", 6, "comedy"));

		System.out.println("Data creation completed");
	}

	// R = READ
	private void showAllTVItems() {
		itemList = TVItemRepo.findAll();
		itemList.forEach(item-> System.out.println(item));
	}


	private void updateCategoryName(String newCategory) {

		List<TVItem> list = TVItemRepo.findAll("comedy");

		list.forEach(item->item.setCategory(newCategory));

		List<TVItem> itemsUpdated = TVItemRepo.saveAll(list);

		if (itemsUpdated != null) {
			System.out.println("Successfully update " + itemsUpdated.size() + " items");
		}
	}

	private void getTVItemByName(String name) {
		System.out.println("Getting item by name: " + name);
		TVItem item = TVItemRepo.findItemByName(name);
		System.out.println(item);
	}

	private void getItemsByCategory(String category) {
		System.out.println("Getting items for category " + category);
		List<TVItem> list = TVItemRepo.findAll(category);
		list.forEach(item -> System.out.println(item));
	}


	private void findCountOfTVItems() {
		long count = TVItemRepo.count();
		System.out.println("Number of docs in the collections = " + count);
	}

	private void deleteTVItem(String id) {
		TVItemRepo.deleteById(id);
		System.out.println("Item with id " + id + " deleted ");
	}


}
