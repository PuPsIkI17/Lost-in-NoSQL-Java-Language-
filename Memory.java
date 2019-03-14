//Pislari Vadim 323CB
//Clasa auxiliara care ajuta la implementarea memoriei principale

public class Memory {
	Premium[] array = new Premium[10];
	int size = 0;

	public Memory() {

	}

	// adaugarea in memoria principala
	public void add(Premium value) {
		if (size == array.length) {
			// "realocare" cu o dimensiune dubla
			Premium copy[] = new Premium[2 * array.length];
			System.arraycopy(array, 0, copy, 0, array.length);
			array = copy;
		}
		array[size++] = value;
	}

	// cauta un element in vector
	public Premium get(String value) {
		for (int j = 0; j != size; j++) {
			if (value.equals(array[j].get_ps())) {
				return array[j];
			}
		}
		return null;
	}
}
