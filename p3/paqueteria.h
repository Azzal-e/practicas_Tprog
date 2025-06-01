/*******************************************************************************
 * Curso de Tecnología de Programación. Práctica 3
 * Autores: Athanasios Usero (NIP:839543)y Eduardo Sanchez Sarsa (NIP:901813)
 * Última revisión: 10/03/2025
 * Resumen: Fichero de interfaz <<paquetería.h>> con la definición de todas las clases
 *          (con sus atributos y métodos) asociadas a las clases de la práctica 3.
 *          Contiene una clase base abstracta <<ElementoPaquetería>>, que sirve para implementar herencia mediante
 *          polimorfismo  por inclusión, y de la que se heredan sus métodos   <<nombre>> , <<volumen>> y  <<peso>>  por las clases
 *          <<ElementoGuardable>> y <<Almacen>>, las cuales también son abstractas y sirven para implementar polimorfismo por inclusión.
 *          A su vez, <<Carga>> sirve para implementar polimorfismo por herencia para las clases <<Producto>> y <<Contenedor>>
 *          
 *          Las clases instanciables son <<Producto>>, <<Toxico>>, <<SerVivo>>, <<Contenedor>> y <<Camion>>. La clase <<Contenedor>> 
 *          tiene herencia multiple de <<Almacen>> y <<Carga>>. Además, se emplea el mecanismo de programación genérica para permitir
 *          definir almacenes de diferentes tipos de elementos guardables.
 ******************************************************************************/

#pragma once
#include <iostream>
#include <vector>
#include <string>
#include <concepts>

using namespace std;

/*---------------------------------CLASE BASE ELEMENTO PAQUETERIA: ABSTRACTA---------------------------------*/
// Clase base abstracta para todos los elementos que forman parte de la paquetería. Todo elemento de la
// paquetería tiene un nombre, volumen y peso asociado.
class ElementoPaqueteria {
    protected: // Accesible a clases derivadas, pero encapsulado para exterior.
        string _nombre;
        double _volumen;
        double _peso;
    public:
        // Pre: Los valores de volumen y peso son no negativos
        // Post: Construye un ElementoPaqueteria con el nombre, volumen y peso especificados
        ElementoPaqueteria(string nombre, double volumen, double peso);
        
        // Pre: --
        // Post: Devuelve el nombre de <<this>>
        virtual string nombre() const;
        
        // Pre: --
        // Post: Devuelve el volumen de <<this>>
        virtual double volumen() const;  
        
        // Pre: --
        // Post: Devuelve el peso de <<this>>
        virtual double peso() const;

        // Pre: <<nivel>> es no negativo 
        // Post: Devuelve una representación en cadena del ElementoPaqueteria 
        //      con la indentación especificada <<nivel>>.
        // TODO: El método <<to_string>> es el que determina la existencia del
        // TODO    polimorfismo por inclusión, ya que es virtual puro y no permite instanciar.
        // TODO:  De este modo, cada clase "hoja" de la jerarquía implementa su propio to_string,
        // TODO:  y no las intermedias.
        virtual string to_string(const int nivel) const = 0; 

        // Pre: --
        // Post: Libera los recursos asociados al ElementoPaqueteria
        // NOTA: Se define como virtual para que se gestione correctamente
        //      la destrucción de los objetos derivados de ElementoPaqueteria
        //      cuando se tratan como punteros a ElementoPaqueteria.
        virtual ~ElementoPaqueteria() = default;
};

// Pre: El flujo de salida os está abierto y disponible para escritura
// Post: Escribe la representación en cadena de <<elemento>> en el flujo <<os>> y devuelve el flujo
ostream& operator<<(ostream& os, const ElementoPaqueteria& elemento);

/*---------------------------------CLASE ELEMENTO GUARDABLE: ABSTRACTA---------------------------------*/


// Clase que representa a todo objeto que podrá ser guardado en un almacén. 
class ElementoGuardable : public ElementoPaqueteria{
public:

    // Pre: Los valores de volumen y peso son no negativos
    // Post: Construye un ElementoGuardable con el nombre, volumen y peso especificados
    ElementoGuardable(string nombre, double volumen, double peso);

    // Pre: --
    // Post: Libera los recursos asociados al ElementoGuardable
    // NOTA: Se define como virtual para que se gestione correctamente
    //      la destrucción de los objetos derivados de ElementoGuardable
    //      cuando se tratan como punteros a ElementoGuardable.
    virtual ~ElementoGuardable() = default;

};

/*---------------------------------CLASE CARGA: ABSTRACTA---------------------------------*/

// Clase que representa un objeto guardable de tipo: "Carga Estandar".
class Carga : public ElementoGuardable {
public:
    static const string TIPO; // Constante que representa el tipo de elemento guardable.

    // Pre: Los valores de volumen y peso son no negativos
    // Post: Construye una Carga con el nombre, volumen y peso especificados
    Carga(string nombre, double volumen, double peso);

    // Pre: --
    // Post: Libera los recursos asociados a la Carga
    // NOTA: Se define como virtual para que se gestione correctamente
    //      la destrucción de los objetos derivados de Carga
    //      cuando se tratan como punteros a Carga.
    virtual ~Carga() = default;
};

/*---------------------------------CLASE PRODUCTO---------------------------------*/

// Clase que representa un objeto guardable de tipo: "Producto".
class Producto : public Carga {
public:
    // Pre: Los valores de volumen y peso son no negativos
    // Post: Construye un Producto con el nombre, volumen y peso especificados
    Producto(string nombre, double volumen, double peso);
    
    // Pre: <<nivel>> es no negativo    
    // Post: Devuelve una representación (Nombre + Volumen + Peso) en cadena del Producto 
    //      con la indentación especificada <<nivel>>.
    string to_string(const int nivel) const override;
};

/*---------------------------------CLASE TOXICO---------------------------------*/

// Clase que representa un objeto guardable de tipo: "Productos Toxicos".
class Toxico : public ElementoGuardable {
public:
    static const string TIPO; // Constante que representa el tipo de elemento guardable.

    // Pre: Los valores de volumen y peso son no negativos
    // Post: Construye un Toxico con el nombre, volumen y peso especificados
    Toxico(string nombre, double volumen, double peso);

    // Pre: <<nivel>> es no negativo 
    // Post: Devuelve una representación (Nombre + Volumen + Peso) en cadena del Toxico 
    //      con la indentación especificada <<nivel>>.
    string to_string(const int nivel) const override;
};

/*---------------------------------CLASE SERVIVO---------------------------------*/

// Clase que representa un objeto guardable de tipo: "Seres Vivos".
class SerVivo : public ElementoGuardable {
public:
    static const string TIPO; // Constante que representa el tipo de elemento guardable.
    
    // Pre: Los valores de volumen y peso son no negativos
    // Post: Construye un SerVivo con el nombre, volumen y peso especificados
    SerVivo(const string& nombre, double volumen, double peso);
    
    // Pre: <<nivel>> es no negativo 
    // Post: Devuelve una representación (Nombre + Volumen + Peso) en cadena del SerVivo con la indentación especificada
    string to_string(const int nivel) const override;
};

/*---------------------------------CLASE ALMACEN---------------------------------*/

// Clase genérica que representa un almacén de elementos guardables.
// T es un tipo genérico que debe de ser derivado de ElementoGuardable.
template <typename T>
requires std::derived_from<T, ElementoGuardable>
class Almacen : public ElementoPaqueteria {
protected:  // Accesible a clases derivadas, pero encapsulado para exterior.

    double volumen_actual; // Volumen actual del Almacen.
    vector<T*> cargas; // Vector de punteros a elementos guardables (del mismo tipo derivado T).

public:
    // Pre: Los valores de volumen, peso y capacidad son no negativos
    // Post: Construye un Almacen con el nombre, volumen, peso y capacidad especificados
    Almacen(string nombre, double capacidad);
    
    // Pre: elemento no es nullptr
    // Post: Si hay suficiente espacio en el Almacen (volumen + elemento->volumen() <= capacidad),
    //       guarda el elemento, actualiza el volumen y peso del Almacen, y devuelve true.
    //       De lo contrario, devuelve false.
    bool guardar(T* elemento);
    
    // Pre: --
    // Post: Libera la memoria de todos los elementos guardados en el Almacen
    ~Almacen() override;
};

// Pre: Los valores de volumen, peso y capacidad son no negativos
// Post: Construye un Almacen con el nombre, volumen, peso y capacidad especificados
template <typename T>
requires std::derived_from<T, ElementoGuardable>
Almacen<T>::Almacen(string nombre, double capacidad)
    : ElementoPaqueteria(nombre, capacidad, 0), volumen_actual(0) {}



// Pre: elemento no es nullptr
// Post: Si hay suficiente espacio en el Almacen (volumen + elemento->volumen() <= capacidad),
//       guarda el elemento, actualiza el volumen y peso del Almacen, y devuelve true.
//       De lo contrario, devuelve false.
template <typename T>
requires std::derived_from<T, ElementoGuardable>
bool Almacen<T>::guardar(T* elemento) {
    if (this->volumen_actual + elemento->volumen() <= this->volumen()) {
        // Si hay suficiente espacio para el elemento, se guarda
        // y se actualiza el peso y volumen actuales del Almacen.
        cargas.push_back(elemento);
        this->volumen_actual += elemento->volumen();
        this->_peso += elemento->peso();
        return true;
    }
    return false;
}

// Pre: --
// Post: Libera la memoria de todos los elementos guardados en el Almacen
template <typename T>
requires std::derived_from<T, ElementoGuardable>
Almacen<T>::~Almacen(){
    for(auto c : cargas){ 
        delete c;
    }
}

/*---------------------------------CLASE CONTENEDOR---------------------------------*/

// Clase que representa un objeto de tipo almacen que a su vez tambien puede ser guardado en otros almacenes de tipo = "Carga Estandar".
template <typename T>
requires std::derived_from<T, ElementoGuardable>
class Contenedor : public Almacen<T>, public Carga {
public:

    // Pre: La capacidad es no negativa
    // Post: Construye un Contenedor con la capacidad especificada
    Contenedor(double capacidad);
    
    // Pre: --
    // Post: Devuelve la capacidad/volumen del Contenedor.
    double volumen() const override;
    
    // Pre: --
    // Post: Devuelve el peso total de los elementos guardados en el Contenedor
    double peso() const override;
    
    // Pre: --
    // Post: Devuelve el nombre del Contenedor
    string nombre() const override;
    
    // Pre: <<nivel>> es no negativo 
    // Post: Devuelve una representación en cadena del Contenedor con la indentación especificada <<nivel>>,
    //       incluyendo la representación de todos los elementos guardados con su representación particular.
    //       y también con la indentación adecuada por nivel.
    string to_string(const int nivel) const override;
};


// Pre: La capacidad es no negativa
// Post: Construye un Contenedor con la capacidad especificada
template <typename T>
requires std::derived_from<T, ElementoGuardable>
Contenedor<T>::Contenedor(double capacidad)
    : Almacen<T>("Contenedor", capacidad), Carga("Contenedor", capacidad, 0) {}

// TODO: Hay que sobrescribir los métodos heredados de elementoPaqueteria <<volumen>>, <<peso>> y <<nombre>>
// TODO  ya que son heredados múltiplemente de <<Almacen>> y <<Carga>>, generando ambiguedad.

// Pre: --
// Post: Devuelve la capacidad/volumen del Contenedor.
template <typename T>
requires std::derived_from<T, ElementoGuardable>
double Contenedor<T>::volumen() const {
    return Almacen<T>::volumen();
}

// Pre: --
// Post: Devuelve el peso total de los elementos guardados en el Contenedor
template <typename T>
requires std::derived_from<T, ElementoGuardable>
double Contenedor<T>::peso() const {
    return Almacen<T>::peso();
}

// Pre: --
// Post: Devuelve el nombre del Contenedor
template <typename T>
requires std::derived_from<T, ElementoGuardable>
string Contenedor<T>::nombre() const {
    return Almacen<T>::nombre();
}

// Pre: <<nivel>> es no negativo 
// Post: Devuelve una representación en cadena del Contenedor con la indentación especificada <<nivel>>,
//       incluyendo la representación de todos los elementos guardados con su representación particular.
//       y también con la indentación adecuada por nivel.
template <typename T>
requires std::derived_from<T, ElementoGuardable>
string Contenedor<T>::to_string(const int nivel) const {
    string result; 
    for (int i = 0; i < nivel; i++) {
        result += "  ";
    }
    result += this->nombre() + " [" + std::to_string(this->volumen()) + " m3] [" + std::to_string(this->peso()) + " kg] de " + T::TIPO +"\n";
    for (auto carga : this->cargas) {
        result += carga->to_string(nivel+1);
    }
    return result;
}

/*---------------------------------CLASE CAMION---------------------------------*/

class Camion : public Almacen<Carga> {
public:
    // Pre: <<capacidad>> es no negativa
    // Post: Construye un Camion con la capacidad especificada
    Camion(double capacidad);
    
    // Pre: <<nivel>> es no negativo 
    // Post: Devuelve una representación en cadena del Camion con la indentación especificada <<nivel>>,
    //       incluyendo la representación de todos los elementos guardados
    string to_string(const int nivel) const override;
};