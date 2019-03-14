//Pislari Vadim 

public class FIFOCache implements Cache {
	Premium[] array;
	int maxsize, size = 0;

	public FIFOCache(int maxsize) {
		this.array = new Premium[maxsize + 1];
		this.maxsize = maxsize;
	}

	@Override
	public void add(Premium value) {
		if (size >= maxsize) {
			array[0] = null;
			int i = 0;
			// muta toate elementele cu o pozitie in spate
			for (; i < size - 1; i++) {
				array[i] = array[i + 1];
			}
			if (i + 1 <= maxsize)
				array[i + 1] = null;
			size--;
		}

		if (size >= maxsize)
			return;
		if (array[0] == null)
			array[0] = value;
		else
			array[size] = value;
		size++;
	}

	@Override
	public void remove(Premium value) {
		for (int i = 0; i < size; i++) {
			if (array[i].equals(value)) {
				array[i] = null;
				int j = i;
				// muta toate elementele dupa j cu o pozitie in spate
				for (; j < size - 1; j++) {
					array[j] = array[j + 1];
				}
				if (j + 1 <= maxsize)
					array[j + 1] = null;
				size--;
			}
		}
	}

	@Override
	public Premium cauta(String value) {
		for (int i = 0; i < size; i++) {
			if (value.equals(array[i].get_ps())) {
				return array[i];
			}
		}
		return null;
	}
}
