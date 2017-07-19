package Listas;

import java.util.ArrayList;
import java.util.Arrays;

/*Clase que hace las funciones de un set de objetos int[] (tengo que aprenderme los metodos
 * para programar clases que usan objetos generalistas....)
 * 
 * La precondicion en el Set es que no puede haber elementos repetidos, esto se comprueba
 * haciendo contains cada vez que hagas un add.
 * En el diseño hemos dejado que los elementos introducidos puedan ser de diferente tamaño,
 * a lo mejor habria que obligar a que fueran de un tamaño unico (ej no dejar [1,2,3] y [1,2] aunque 
 * los dos sean arrays de ints...
 * 
 * Creada para el servidor de hundir la flota para que guarde las coordenadas de los barcos de los 
 * jugadores que se obtienen como una serie de array de dos enteros que no se pueden repetir.
 */
public class IntArraySet {
	
	private ArrayList<int[]> elements;
	private int size;
	
	public IntArraySet(){
		elements = new ArrayList<int[]>();
		size = 0;
	}
	
	public boolean contains(int[] element) {
		int sameElement;
		int[] tmp;
		for (int i = 0; i < size; i++) {
			tmp = elements.get(i);
			if (tmp.length != element.length) {
				continue; //No seran iguales seguro ya que su tamaño es diferente. NOTA: Dejar que haya elementos(arrays) de diferente tamanyo o no?
			}
			sameElement = 0;
			for (int j = 0; j < element.length; j++) {
				if (tmp[j] == element[j]) {
					sameElement++;
				}
			}
			if (sameElement == element.length) { return true; }
		}
		return false;
	}
	
	
	public boolean add(int[] element) {
		if (size == 0) {
			elements.add(element);
		} 
		else {
			if (!contains(element)) {
				elements.add(element);
			} else {
				return false;
			}
		}
		size++;
		return true;	
	}
	
	public int findIndex(int[] element) {
		if (!contains(element)) { return -1; }
		int[] tmp;
		int sameElement;
		for (int i = 0; i < size; i++) {
			tmp = elements.get(i);
			sameElement = 0;
			for (int j = 0; j < element.length; j++){
				if (tmp[j] != element[j]) {
					break;
				}
				sameElement++;
			}
			if (sameElement == element.length) {
				return i;
			}
		}
		return -1;
	}
	
	public int[] remove(int index) {
		return elements.remove(index);
	}
	
	//Necessaries?
	public int[] remove(int[] element) {
		int index = findIndex(element);
		if (index >= 0) {
			return remove(index);
		}
		return null;
	}
	
	public int[] get(int index){
		return elements.get(index);
	}
	
	//Necessaries?
	public int[] get(int[] element){
		int index = findIndex(element);
		if (index >= 0) {
			return elements.get(index);
		}
		return null;	
	}
	
	public static IntArraySet setUnion(IntArraySet set1, IntArraySet set2) {
		IntArraySet unionSet = new IntArraySet();
		addElementsToUnionSet(unionSet, set1, set2);
		addElementsToUnionSet(unionSet, set2, set1);
		return unionSet;
	}
	
	private static void addElementsToUnionSet(IntArraySet targetSet, IntArraySet set1, IntArraySet set2) {
		int[] tmp;
		for (int i = 0; i < set1.size; i++) {
			tmp = set1.get(i);
			if (!targetSet.contains(tmp)) {
				targetSet.add(tmp);
			}
		}
	}
	
	public static IntArraySet setIntersection(IntArraySet set1, IntArraySet set2) {
		IntArraySet unionSet = new IntArraySet();
		addElementsToIntersectSet(unionSet, set1, set2);
		addElementsToIntersectSet(unionSet, set2, set1);
		return unionSet;
	}
	
	private static void addElementsToIntersectSet(IntArraySet targetSet, IntArraySet set1, IntArraySet set2) {
		int[] tmp;
		for (int i = 0; i < set1.size; i++) {
			tmp = set1.get(i);
			if (set2.contains(tmp)) {
				targetSet.add(tmp);
			}
		}
	}
	
	public String toString(){
		String all = "";
		for (int i = 0; i < size; i++) {
			all += Arrays.toString(elements.get(i)) + ", ";
		}
		all = all.substring(0, all.length()-2);
		return all;
	}
	
	public static void main(String[] args) {
		/*Testeo*/
		IntArraySet testSet = new IntArraySet();
		IntArraySet testSet2 = new IntArraySet();
		IntArraySet testSet3;
		int[] testArray1 = {2,4};
		int[] testArray2 = {3,6};
		int[] testArray3 = {2,2};
		int[] testArray4 = {1,8};
		int[] testArray5 = {2,6};
		int[] testArray6 = {2,4};
		int[] testArray7 = {2,2};
		int[] testArray8 = {5,2};

		System.out.println(testSet.add(testArray1));
		System.out.println(testSet.add(testArray2));
		System.out.println(testSet.add(testArray3));
		System.out.println(testSet.add(testArray6));
		System.out.println(testSet.add(testArray7));
		System.out.println(testSet2.add(testArray6));
		System.out.println(testSet2.add(testArray5));
		System.out.println(testSet2.add(testArray4));
		System.out.println(testSet2.add(testArray8));
		System.out.println(testSet2.add(testArray7));
		System.out.println(testSet.toString());
		System.out.println(testSet2.toString());
		testSet3 = setUnion(testSet,testSet2);
		System.out.println(testSet3.toString());
		System.out.println(testSet3.size);

	}

}
