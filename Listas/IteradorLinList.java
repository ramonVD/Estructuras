package Listas;

/*Class created to iterate over a LinList (linked list), keeps three nodes in memory*/
public class IteradorLinList<E> {
	protected Nodo<E> nodoAnterior;
	protected Nodo<E> nodoActual;
	protected Nodo<E> nodoSiguiente;
	protected LinList<E> linkedListActual;
	
	/*Constructor, when its created both the anterior node as the actual one will be the root node, take this into account in the first iteration.
	 * Next step you'll have anterior as root position, actual as root +1 and siguiente as root + 2 and the next steps it'll keep this progression.
	 * 
	 * Maybe the first step should be done by default and just document that the root node starts as nodoAnterior... */
	public IteradorLinList(LinList<E> lista){
		linkedListActual = lista;
		nodoAnterior = lista.primerNodo;
		nodoActual = nodoAnterior;
		nodoSiguiente = nodoActual.siguiente;
	}
	
	public boolean hasNextItem(){
		return (nodoSiguiente != null);
	}
	
	/*Function that iterates to the next node, keeps the old actual node as nodoAnterior and puts the next
	 * in memory as SiguienteNodo*/
	public Nodo<E> nextItem(){
		nodoAnterior = nodoActual;
		nodoActual = nodoAnterior.siguiente;
		nodoSiguiente = nodoActual.siguiente;
		return nodoActual;
	}
	
	public Nodo<E> getNodoSiguiente(){
		return nodoSiguiente;
	}
	
	public Nodo<E> getNodoAnterior(){
		return nodoAnterior;
	}
	
	public Nodo<E> getNodoActual(){
		return nodoActual;
	}
	
	public void toConsole(){
		if (nodoSiguiente != null){
			
			String anterior = (nodoAnterior.elemento != null) ? nodoAnterior.elemento.toString() : "";
			String actual = (nodoActual.elemento != null) ? nodoActual.elemento.toString() : "";
			String siguiente = (nodoSiguiente.elemento != null) ? nodoSiguiente.elemento.toString() : "";

			System.out.println("Nodo anterior: " + anterior + " , nodo actual: " + actual + " , nodo siguiente: " + siguiente + ".");
		}
	}
}
