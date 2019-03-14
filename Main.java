
//Pislari Vadim 323CB

import java.io.*;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		// deschiderea fisierelor
		File inputfile = new File(args[0]);
		File outputfile = new File(args[1]);

		outputfile.createNewFile();
		PrintWriter out = new PrintWriter(outputfile);

		Scanner scan = new Scanner(inputfile);
		String type = scan.nextLine();

		// variabilele
		Memory memory = new Memory();
		int dimension = scan.nextInt();
		int nr_operation = scan.nextInt();
		FIFOCache fifo = new FIFOCache(dimension);
		LRUCache lru = new LRUCache(dimension);
		LFU lfu = new LFU(dimension);

		scan.nextLine();

		for (int i = 0; i != nr_operation; i++) {
			String operation = scan.nextLine();
			// crearea unui vector de stringuri (parsare)
			String[] part = operation.split(" ");

			if (part[0].equals("ADD")) {

				// suprascriere daca se mai gaseste elemntul in array
				int exists = -1;
				for (int j = 0; j != memory.size; j++) {
					if (part[1].equals(memory.array[j].get_ps())) {
						exists = j;
						break;
					}
				}
				if (exists != -1) {
					// daca s-a gasit elementul cautat

					if (type.equals("FIFO")) {
						fifo.remove(memory.array[exists]);
					}
					if (type.equals("LRU")) {
						lru.remove(memory.array[exists]);
					}
					if (type.equals("LFU")) {
						lfu.remove(memory.array[exists]);
					}
					if (part.length == 4) {
						Premium var = new Premium(part[1], Integer.parseInt(part[2]), Integer.parseInt(part[3]));
						memory.array[exists] = var;
					}
					if (part.length == 3) {
						Premium var = new Premium(part[1], Integer.parseInt(part[2]), 0);
						memory.array[exists] = var;
					}
					continue;
				}

				// inserare fara suprascriere
				if (part.length == 4) {
					Premium var = new Premium(part[1], Integer.parseInt(part[2]), Integer.parseInt(part[3]));
					memory.add(var);
				} else if (part.length == 3) {
					Premium var = new Premium(part[1], Integer.parseInt(part[2]), 0);
					memory.add(var);
				}

			} else if (part[0].equals("GET")) {
				Premium value = null;
				if (type.equals("FIFO"))
					value = fifo.cauta(part[1]);
				if (type.equals("LRU"))
					value = lru.cauta(part[1]);
				if (type.equals("LFU"))
					value = lfu.cauta(part[1]);

				// daca elemntul s-a gasit sau nu s-a gasit in memoria Cache
				if (value == null)
					value = memory.get(part[1]);
				else {
					if (value.get_pp() != 0) {
						out.println("0 Premium");
						value.decrement_pp();

					} else if (value.get_pb() != 0) {
						out.println("0 Basic");
						value.decrement_pb();

					} else if (value.get_pb() == 0) {
						out.println("0 Free");
					}
					continue;
				}

				// daca elementul s-a gasit sau nu s-a gasit in memoria principala
				if (value == null)
					out.println(2);
				else {
					if (value.get_pp() != 0) {
						out.println("1 Premium");
						value.decrement_pp();

					} else if (value.get_pb() != 0) {
						out.println("1 Basic");
						value.decrement_pb();

					} else if (value.get_pb() == 0) {
						out.println("1 Free");
					}
					if (type.equals("FIFO")) {
						fifo.add(value);
					}
					if (type.equals("LRU")) {
						lru.add(value);
					}
					if (type.equals("LFU")) {
						lfu.add(value);
					}
				}
			}
		}
		out.close();
		scan.close();
	}
}
