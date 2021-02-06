package COM;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
class Item {
	
	String name;
	int price;

	public Item(String name, int price) {     //initialize the object
		this.name = name;
		this.price = price;
	}
	@Override
	public String toString() {                 //Override the toString() method to return file data
		return this.name + ": " + this.price;
	}
}

public class Mainclass {
	public static void main(String[] args) throws Exception {
		Scanner s=new Scanner(System.in);                           
		FileInputStream files=new FileInputStream("sample_input.txt");      //Creating a File  path  to read the file data  
		Scanner sc=new Scanner(files);                                    //Reading the File data

		System.out.println("Number of the employees: ");
		int employees = s.nextInt();                                       // Reading no of employee from USER Input
		sc.nextLine(); sc.nextLine();

		ArrayList<Item> g_items = new ArrayList<Item>();                  // Creating ArrayList to store the file data

		while(sc.hasNextLine())  
		{
			String t_items[] = sc.nextLine().split(": ");
			g_items.add(new Item(t_items[0], Integer.parseInt(t_items[1])));    //add the data to ArrayList in terms of price and items
		}
		sc.close();

		Collections.sort(g_items, new Comparator<Item>(){                    //sort the ArrayList data in terms of price in Ascending order
			public int compare(Item a, Item b) { 
				return a.price - b.price; 
			} 
		});
		

		int min_price = g_items.get(g_items.size()-1).price;          // Store the last index price 
		int index = 0;
		for(int i=0;i<g_items.size()-employees+1;i++) {
			int dif = g_items.get(employees+i-1).price-g_items.get(i).price;   //looping the ArrayList to comparing the items price  

			if(dif<=min_price) {         
				min_price = dif;                                                // comparing the items price   
				index = i;
			}
		}

		FileWriter fw = new FileWriter("sample_output.txt");
		fw.write("Number of the employees: "+employees+"\n");
		fw.write("Here the goodies that are selected for distribution are:\n");
		for(int i=index;i<index + employees; i++) {
			fw.write(g_items.get(i).toString() + "\n");  //Printing the best possible goodie with respect to  employee size to the sample_output file
		}

		fw.write("\nAnd the difference between the chosen goodie with highest price and the lowest price is " + min_price);  //store the  difference highest and lowest price
		fw.close();

		System.out.println("Number of the employees: "+employees);
		System.out.println("Here the goodies that are selected for distribution are:\n");
		for(int i=index;i<index + employees; i++) {           
			System.out.println(g_items.get(i).toString() + "\n");  // Printing the best possible goodie with respect to  employee size

		}

		System.out.println("\nAnd the difference between the chosen goodie with highest price and the lowest price is " + min_price);
	}
}