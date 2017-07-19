package Listas;

/* Implementacion de una Lista linkeada de nodos que contienen Strings,
tengo que pasar de Strings a objetos generalistas*/
public class LinList {
	protected Nodo primerNodo;
	protected static final int INS_ANTES = 0;
	protected static final int INS_SOBRE = 1;
	protected static final int INS_DESPUES = 2;

	
	public LinList(String primerElemento){
		primerNodo = new Nodo(primerElemento);
	}
	
	/*Inserta un nodo con elemento determinado al final de la lista*/
	public void insertarNodoFinal(String elemento){
		Nodo tmpNodo = this.primerNodo;
		while (tmpNodo.siguiente != null){
			tmpNodo = tmpNodo.siguiente;
		}
		tmpNodo.siguiente = new Nodo(elemento);
	}
	
	public Nodo buscarNodo(String elemento){
		Nodo tmpNodo = this.primerNodo;
		while (!tmpNodo.elemento.equals(elemento) && tmpNodo.siguiente != null) {
			tmpNodo = tmpNodo.siguiente;
		}
		if (tmpNodo.elemento.equals(elemento)) return tmpNodo; //Por si simplemente ha bucleado a traves de todos los nodos
		return null;
	}
	
	/*Elimina el Nodo con elemento determinado en la lista, devuelve cierto si lo elimina falso si no lo encuentra
	 * NOTA: No podemos usar buscarNodo aqui ya que para eliminar hay que conocer el nodo anterior para cargarse la
	 * referencia en siguiente por la del siguiente nodo*/
	public boolean eliminarNodo(String elemento){
		Nodo tmpNodo = this.primerNodo;
		if (tmpNodo.elemento.equals(elemento)){ //Caso nodo raiz
			if (tmpNodo.siguiente == null){  
				System.out.println("Error, intentando eliminar nodo raiz de una lista vacia"); //Podriamos throwear Exception
				return false; 
			} 
			this.primerNodo = tmpNodo.siguiente;
			return true;
		}
		while (tmpNodo.siguiente != null){
			if (tmpNodo.siguiente.elemento.equals(elemento)){
				tmpNodo.siguiente = tmpNodo.siguiente.siguiente;
				return true;
			}
			tmpNodo = tmpNodo.siguiente;
		}
		return false;
	}
	
	/*Pone todos los nodos en consola*/
	public void verNodos(){
		Nodo tmpNodo = this.primerNodo;
		while (tmpNodo.siguiente != null){
			System.out.print(tmpNodo.elemento + " -> ");
			tmpNodo = tmpNodo.siguiente;
		}
		System.out.println(tmpNodo.elemento + " -> No hay mas elementos.");
	}
	
	/*Vacia la lista, pone el elemento raiz como "" y su siguiente sin apuntar a ningun otro nodo*/
	public void vaciarLista(){
		this.primerNodo.elemento = "";
		this.primerNodo.siguiente = null;
	}
	
	/*Inserta un Nodo con un elemento determinado(nuevoElemento) en una posicion de la lista,
	 * primero busca el nodo con elemento antiguoElemento, si lo encuentra usara tipo para determinar la insercion
	 * tipo == INS_ANTES -> Inserta el nuevoNodo antes del antiguo
	 * tipo == INS_SOBRE -> Sobreescribe el antiguo nodo por el nuevo
	 * tipo == INS_DESPUES -> Inserta el nuevoNodo despues del antiguo
	 */
	public boolean insertarNodo(String antiguoElemento, String nuevoElemento, int tipo){
		IteradorLinList iter1 = new IteradorLinList(this);
		if (iter1.nodoActual.elemento.equals(antiguoElemento)){  //Caso especial nodo raiz, intentar hacer general????
			if (tipo == INS_ANTES){ 
				this.primerNodo = new Nodo(nuevoElemento);
				this.primerNodo.siguiente = iter1.nodoActual;
				return true;
			}
			else if (tipo == INS_SOBRE){
				this.primerNodo = new Nodo(nuevoElemento);
				this.primerNodo.siguiente = iter1.getNodoSiguiente();
			}
			else if (tipo == INS_DESPUES){
				this.primerNodo.siguiente = new Nodo(nuevoElemento);
				this.primerNodo.siguiente.siguiente = iter1.getNodoSiguiente();
			}
		}
		while (iter1.hasNextItem()){
			iter1.nextItem();
			if (iter1.nodoActual.elemento.equals(antiguoElemento)){ //Caso general, elemento del nodo coincide con el que buscamos
				Nodo tmpNodo = new Nodo(nuevoElemento);
				switch (tipo){
					case INS_ANTES:
						iter1.nodoAnterior.siguiente = tmpNodo;
						iter1.nodoAnterior.siguiente.siguiente = iter1.nodoActual;
						iter1.nodoAnterior = tmpNodo;
						return true; //podriamos hacer break en todos i luego un solo return pero mas lineas de codigo :P
					case INS_SOBRE:
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
		/*Testeo de la Linked List esta*/
		LinList test1 = new LinList("hola");
		test1.insertarNodoFinal("mundo");
		test1.insertarNodoFinal("adios");
		test1.insertarNodoFinal("bye");
		test1.insertarNodoFinal("mundor");
		System.out.println(test1.insertarNodo("hola", "tesaaaat",INS_DESPUES));
		System.out.println(test1.buscarNodo("tesaaaat").elemento);
		test1.verNodos();
	}
}
