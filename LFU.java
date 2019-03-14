// Pislari Vadim 323CB
// BONUS

public class LFU implements Cache {

	Premium[] array;
	// vector de indici
	int[] vector;
	// vector de numere de utilizari ale variabilelor
	int[] used;
	int maxsize, size = 0;

	public LFU(int maxsize) {
		this.array = new Premium[maxsize + 1];
		this.vector = new int[maxsize + 1];
		this.used = new int[maxsize + 1];
		this.maxsize = maxsize;
	}

	@Override
	public void add(Premium value) {
		for (int j = 0; j != size; j++) {
			vector[j]++;
		}
		if (size == maxsize) {
			int max = Integer.MAX_VALUE, var = -1;
			// cautarea elementului care ar trebui eliminat un "min"
			for (int i = 0; i < size; i++) {
				if (used[i] < max) {
					max = used[i];
					var = i;
				} else if (used[i] == max) {
					if (vector[i] > vector[var]) {
						var = i;
					}
				}
			}
			used[var] = 0;
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
				used[i] = 0;
				int j = i;
				// muta toate elementele dupa j cu o pozitie in spate
				for (; j < size; j++) {
					array[j] = array[j + 1];
					vector[j] = vector[j + 1];
					used[j] = used[j + 1];
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
				used[i]++;
				return array[i];
			}
		}
		return null;
	}

}
