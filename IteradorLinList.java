package Listas;

/*Clase para iterar sobre una LinList, guarda tres Nodos en memoria*/
public class IteradorLinList {
	protected Nodo nodoAnterior;
	protected Nodo nodoActual;
	protected Nodo nodoSiguiente;
	protected LinList linkedListActual;
	
	/*Constructor, a tener en cuenta que cuando se construye a partir de la linked list tanto el nodo anterior como el actual son el nodo raiz
	 * debido a que sino comenzaria con el actual en pos + 1 a la raiz. A considerar cuando iteras, en los demas pasos tendras anterior, actual i siguiente
	 * (en el primer paso el anterior es el raiz, el actual raiz + 1 i el siguiente raiz + 2 i los siguientes pasos la progresion sigue bien...)
	 * 
	 * a lo mejor hacerle dar el primer paso by default y documentar que el nodo raiz comienza en nodoAnterior...	 */
	public IteradorLinList(LinList lista){
		linkedListActual = lista;
		nodoAnterior = lista.primerNodo;
		nodoActual = nodoAnterior;
		nodoSiguiente = nodoActual.siguiente;
	}
	
	public boolean hasNextItem(){
		return (nodoSiguiente != null);
	}
	
	/*Funcion que itera al siguiente nodo, mantiene el antiguo actual como anterior y pone el siguiente en memoria*/
	public Nodo nextItem(){
		nodoAnterior = nodoActual;
		nodoActual = nodoAnterior.siguiente;
		nodoSiguiente = nodoActual.siguiente;
		return nodoActual;
	}
	
	public Nodo getNodoSiguiente(){
		return nodoSiguiente;
	}
	
	public Nodo getNodoAnterior(){
		return nodoAnterior;
	}
	
	public Nodo getNodoActual(){
		return nodoActual;
	}
	
	public void toConsole(){
		if (nodoSiguiente != null){
			System.out.println("Nodo anterior: " + nodoAnterior.elemento + " , nodo actual: " + nodoActual.elemento + " , nodo siguiente: " + nodoSiguiente.elemento + ".");
		}
	}
}
