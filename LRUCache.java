//Pislari Vadim 323CB

public class LRUCache implements Cache {
	Premium[] array;
	// vector de indici
	int[] vector;
	int maxsize, size = 0;

	public LRUCache(int maxsize) {
		this.array = new Premium[maxsize + 1];
		this.vector = new int[maxsize + 1];
		this.maxsize = maxsize;
	}

	@Override
	public void add(Premium value) {
		for (int j = 0; j != size; j++) {
			vector[j]++;
		}
		if (size == maxsize) {
			int min = -1, var = -1;
			// cautarea elementului care ar trebui eliminat
			for (int i = 0; i < size; i++) {
				if (vector[i] > min) {
					min = vector[i];
					var = i;
				}
			}
			array[var] = value;
			vector[var] = 0;

		} else {
			array[size++] = value;
		}
	}

	@Override
	public void remove(Premium value) {
		for (int i = 0; i < size; i++) {
			if (array[i].equals(value)) {
				array[i] = null;
				vector[i] = 0;
				int j = i;
				// muta toate elementele dupa j cu o pozitie in spate
				for (; j < size; j++) {
					array[j] = array[j + 1];
					vector[j] = vector[j + 1];
				}
				size--;
				break;
			}
		}
	}

	@Override
	public Premium cauta(String value) {
		for (int j = 0; j != size; j++) {
			vector[j]++;
		}
		for (int i = 0; i < size; i++) {
			if (value.equals(array[i].get_ps())) {
				vector[i] = 0;
				return array[i];
			}
		}
		return null;
	}

}
