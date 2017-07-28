package Listas;

/* Implementation of a linked list with generalist nodes*/
public class LinList<E> {
	protected Nodo<E> primerNodo;
	protected static final int INS_BEFORE = 0;
	protected static final int INS_OVERWRITE = 1;
	protected static final int INS_AFTER = 2;

	
	public LinList(E primerElemento){
		primerNodo = new Nodo<E>(primerElemento);
	}
	
	public LinList(){
		primerNodo = new Nodo<E>(null);
	}
	
	/*Function that compares two nodes, its set to test both by equality and
	 * reference, change this to your elements needs	 */
	private boolean compareNodes(E elemento1, E elemento2){
		if ( (elemento1 == null || elemento2 == null) && !(elemento1 == null && elemento2 == null)) { return false; }
		if (elemento1 == elemento2 || elemento1.equals(elemento2)){
			return true;
		}
		return false;
	}
	
	/*Inserts a node at the end of the list*/
	public void pushNode(E elemento){
		Nodo<E> tmpNodo = this.primerNodo;
		if (tmpNodo.elemento == null) { //In the case of an empty list...
			tmpNodo.elemento = elemento;
		} else {
			while (tmpNodo.siguiente != null){
				tmpNodo = tmpNodo.siguiente;
			}
			tmpNodo.siguiente = new Nodo<E>(elemento);
		}
	}
	
	public Nodo<E> findNode(E elemento){
		Nodo<E> tmpNodo = this.primerNodo;
		while (!compareNodes(tmpNodo.elemento, elemento) && tmpNodo.siguiente != null) {
			tmpNodo = tmpNodo.siguiente;
		}
		if (compareNodes(tmpNodo.elemento, elemento)) return tmpNodo; //Por si simplemente ha bucleado a traves de todos los nodos
		return null;
	}
	
	/*Deletes the node with the chosen element from the list, returns true if it found and deleted it false if it couldnt*/
	public boolean deleteNode(E elemento){
		Nodo<E> tmpNodo = this.primerNodo;
		if (tmpNodo == null) { return false; }
		if (compareNodes(tmpNodo.elemento,elemento)){ //root node
			if (tmpNodo.siguiente == null){  
				System.out.println("Error, intentando eliminar nodo raiz de una lista vacia"); //Could throw exception here...
				return false; 
			} 
			this.primerNodo = tmpNodo.siguiente;
			return true;
		}
		while (tmpNodo.siguiente != null){
			if (compareNodes(tmpNodo.siguiente.elemento , elemento)){
				tmpNodo.siguiente = tmpNodo.siguiente.siguiente;
				return true;
			}
			tmpNodo = tmpNodo.siguiente;
		}
		return false;
	}
	
	/*Lists all elements in the nodes in the console*/
	public void listNodes(){
		Nodo<E> tmpNodo = this.primerNodo;
		while (tmpNodo.siguiente != null){
			if (tmpNodo.elemento != null) {
				System.out.print(tmpNodo.elemento.toString() + " -> ");
			}
			tmpNodo = tmpNodo.siguiente;
		}
		System.out.println(tmpNodo.elemento + " -> No hay mas elementos.");
	}
	
	/*Empties the linked list and its references*/
	public void vaciarLista(){
		this.primerNodo.elemento = null;
		this.primerNodo.siguiente = null;
	}
	
	/*Inserts a node with the entered element in the list position determined 
	 * by both the searched node (searched looking for its element) position and an 
	 * insertion type:
	 * type == INS_BEFORE -> It'll insert the new node before the old node.
	 * type == INS_OVERWRITE -> It'll overwrite the old node with the new one
	 * type == INS_AFTER -> It'll insert the new node after the old one.
	 * In all cases it'll return true if it found the other node and inserted the new
	 * one and false if it couldn't find it.
	 */
	public boolean insertarNodo(E antiguoElemento, E nuevoElemento, int tipo){
		IteradorLinList<E> iter1 = new IteradorLinList<E>(this);
		if (compareNodes(iter1.nodoActual.elemento, antiguoElemento)){  //Root node, special case, could change it???
			if (tipo == INS_BEFORE){ 
				this.primerNodo = new Nodo<E>(nuevoElemento);
				this.primerNodo.siguiente = iter1.nodoActual;
			}
			else if (tipo == INS_OVERWRITE){
				this.primerNodo = new Nodo<E>(nuevoElemento);
				this.primerNodo.siguiente = iter1.getNodoSiguiente();
				return true;

			}
			else if (tipo == INS_AFTER){
				this.primerNodo.siguiente = new Nodo<E>(nuevoElemento);
				this.primerNodo.siguiente.siguiente = iter1.getNodoSiguiente();
			}
			return true;
		}
		while (iter1.hasNextItem()){
			iter1.nextItem();
			if (compareNodes(iter1.nodoActual.elemento, antiguoElemento)){ //General case
				Nodo<E> tmpNodo = new Nodo<E>(nuevoElemento);
				switch (tipo){
					case INS_BEFORE:
						iter1.nodoAnterior.siguiente = tmpNodo;
						iter1.nodoAnterior.siguiente.siguiente = iter1.nodoActual;
						iter1.nodoAnterior = tmpNodo;
						return true; //podriamos hacer break en todos i luego un solo return pero mas lineas de codigo :P
					case INS_OVERWRITE:
						iter1.nodoAnterior.siguiente = tmpNodo;
						iter1.nodoActual = tmpNodo;
						iter1.nodoActual.siguiente = iter1.getNodoSiguiente();
						return true;
					default:
						iter1.nodoActual.siguiente = tmpNodo;
						iter1.nodoActual.siguiente.siguiente = iter1.getNodoSiguiente();
						iter1.nodoSiguiente = tmpNodo;
						return true;
				}
			}
		}
		return false;
	}
		
	public static void main(String[] args){
		/*Some light testing*/
		LinList<String> test1 = new LinList<String>("hola");
		test1.pushNode("mundo");
		test1.pushNode("adios");
		test1.pushNode("bye");
		test1.pushNode("mundor");
		System.out.println(test1.insertarNodo("adios", "test",INS_AFTER));
		System.out.println(test1.deleteNode("mundor"));
		System.out.println(test1.findNode("test"));
		test1.listNodes();
	}
}
