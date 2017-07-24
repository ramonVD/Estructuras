package Listas;

import java.util.ArrayList;
import java.util.Arrays;

/*Clase que hace las funciones de un set de objetos genericos de tipo array...
 * 
 * La precondicion en el Set es que no puede haber elementos repetidos, esto se comprueba
 * haciendo contains cada vez que hagas un add.
 * En el diseño hemos dejado que los elementos introducidos puedan ser de diferente tamaño,
 * a lo mejor habria que obligar a que fueran de un tamaño unico (ej no dejar [1,2,3] y [1,2] aunque 
 * los dos sean arrays de ints...
*/
public class myArraySet<E> {
	
	private ArrayList<E[]> elements;
	private int size;
	
	public myArraySet(){
		elements = new ArrayList<E[]>();
		size = 0;
	}
	public boolean contains(E[] element) {
		int sameElement;
		E[] tmp;
		if (size == 0) {
			return false;
		} 
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
	
	
	public boolean add(E[] element) {
		if (!contains(element)) {
			@SuppressWarnings("unchecked")  //Problems may arise here...
			E[] deepCopy = (E[])new Object[element.length];
			for (int i = 0; i < element.length; i++) {
				deepCopy[i] = element[i];
			}
			elements.add(deepCopy);
			size++;
			return true;
		} else {
			return false;
		}	
	}
	
	public int findIndex(E[] element) {
		if (!contains(element)) { return -1; }
		E[] tmp;
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
	
	public E[] remove(int index) {
		return elements.remove(index);
	}
	
	//Necessaries?
	public E[] remove(E[] element) {
		int index = findIndex(element);
		if (index >= 0) {
			return remove(index);
		}
		return null;
	}
	
	public E[] get(int index){
		return elements.get(index);
	}
	
	public myArraySet<E> setUnion(myArraySet<E> set1, myArraySet<E> set2) {
		myArraySet<E> unionSet = new myArraySet<E>();
		addElementsToUnionSet(unionSet, set1, set2);
		addElementsToUnionSet(unionSet, set2, set1);
		return unionSet;
	}
	
	private  void addElementsToUnionSet(myArraySet<E> targetSet, myArraySet<E> set1, myArraySet<E> set2) {
		E[] tmp;
		for (int i = 0; i < set1.size; i++) {
			tmp = set1.get(i);
			if (!targetSet.contains(tmp)) {
				targetSet.add(tmp);
			}
		}
	}
	
	public myArraySet<E> setIntersection(myArraySet<E> set1, myArraySet<E> set2) {
		myArraySet<E> unionSet = new myArraySet<E>();
		addElementsToIntersectSet(unionSet, set1, set2);
		addElementsToIntersectSet(unionSet, set2, set1);
		return unionSet;
	}
	
	private  void addElementsToIntersectSet(myArraySet<E> targetSet, myArraySet<E> set1, myArraySet<E> set2) {
		E[] tmp;
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
	
	
	public int size(){
		return this.size;
	}
	
	/*//Alternative way to print the set to console
	public String toString(){
		String all = "";
		E[] tmp;
		for (int i = 0; i < size; i++) {
			tmp = elements.get(i);
			for (int j = 0; j < tmp.length; j++) {
				all += tmp[j] + ", ";
			}
		}
		if (all.length() > 2){
			all = all.substring(0, (all.length()-2));
		}
		return all;
	}*/

	
	public static void main(String[] args) {
		/*Testeo*/
		myArraySet<Integer> testSet = new myArraySet<Integer>();
		myArraySet<Integer> testSet2 = new myArraySet<Integer>();
		myArraySet<Integer> testSet3 = new myArraySet<Integer>();
		Integer[] testArray1 = {2,4};
		Integer[] testArray2 = {3,6};
		Integer[] testArray3 = {2,2};
		Integer[] testArray4 = {1,8};
		Integer[] testArray5 = {2,6};
		Integer[] testArray6 = {2,4};
		Integer[] testArray7 = {2,2};
		Integer[] testArray8 = {5,2};

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
		testSet3 = testSet3.setUnion(testSet,testSet2);
		System.out.println(testSet3.toString());
		System.out.println(testSet3.size());

	}

}
