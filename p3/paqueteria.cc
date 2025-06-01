/*******************************************************************************
 * Curso de Tecnología de Programación. Práctica 3
 * Autores: Athanasios Usero (NIP:839543)y Eduardo Sanchez Sarsa (NIP:901813)
 * Última revisión: 10/03/2025
 * Resumen: Fichero de implementación <<paqueteria.cc>> con la implementación de los métodos 
 *          de todas las clases no genéricas asociadas a la práctica3:
 *          <<ElementoPaquetería>>, <<ElementoGuardable>>, <<Carga>> , <<Producto>> ,
 *          <<Toxico>> , <<SerVivo>> y <<Camion>>.
 ******************************************************************************/

#include "paqueteria.h"

/*---------------------------------CLASE BASE ELEMENTO PAQUETERIA: ABSTRACTA---------------------------------*/
// Constructor para Objeto
// Pre: Los valores de volumen y peso son no negativos
// Post: Construye un ElementoPaqueteria con el nombre, volumen y peso especificados
ElementoPaqueteria::ElementoPaqueteria(std::string nombre, double volumen, double peso)
    : _nombre(nombre), _volumen(volumen), _peso(peso) {}

// Pre: --
// Post: Devuelve el nombre de <<this>>
std::string ElementoPaqueteria::nombre() const {
    return _nombre;
}

// Pre: --
// Post: Devuelve el volumen de <<this>>
double ElementoPaqueteria::volumen() const {
    return _volumen;
}

// Pre: --
// Post: Devuelve el peso de <<this>>
double ElementoPaqueteria::peso() const {
    return _peso;
}

// Pre: El flujo de salida <<os>> está abierto y disponible para escritura
// Post: Escribe la representación en cadena de <<elemento>> en el flujo <<os>> y devuelve el flujo
ostream& operator<<(ostream& os, const ElementoPaqueteria& elemento){
    os << elemento.to_string(0);
    return os;
}


/*---------------------------------CLASE ELEMENTO GUARDABLE: ABSTRACTA---------------------------------*/

// Pre: Los valores de volumen y peso son no negativos
// Post: Construye un ElementoGuardable con el nombre, volumen y peso especificados
ElementoGuardable::ElementoGuardable(std::string nombre, double volumen, double peso)
    : ElementoPaqueteria(nombre, volumen, peso) {}

/*---------------------------------CLASE CARGA: ABSTRACTA---------------------------------*/

const string Carga::TIPO = "Carga Estandar";
// Pre: Los valores de volumen y peso son no negativos
// Post: Construye una Carga con el nombre, volumen y peso especificados
Carga::Carga(std::string nombre, double volumen, double peso)
    : ElementoGuardable(nombre, volumen, peso) {}


/*---------------------------------CLASE PRODUCTO---------------------------------*/

// Pre: Los valores de volumen y peso son no negativos
// Post: Construye un Producto con el nombre, volumen y peso especificados
Producto::Producto(std::string nombre, double volumen, double peso)
    : Carga(nombre, volumen, peso) {}

// Pre: <<nivel>> es no negativo 
// Post: Devuelve una representación (Nombre + Volumen + Peso) en cadena del Producto 
//      con la indentación especificada <<nivel>>.
string Producto::to_string(const int nivel) const { 
    string indent;
    for (int i = 0; i < nivel; i++) {
        indent += "  ";
    }
    return indent + (this->nombre() + " [" + std::to_string(this->volumen()) + " m3] [" + std::to_string(this->peso()) + " kg]\n" );
}


/*---------------------------------CLASE TOXICO---------------------------------*/

const string Toxico::TIPO = "Productos Toxicos";

// Pre: Los valores de volumen y peso son no negativos
// Post: Construye un Toxico con el nombre, volumen y peso especificados
Toxico::Toxico(std::string nombre, double volumen, double peso)
    : ElementoGuardable(nombre, volumen, peso) {}

// Pre: <<nivel>> es no negativo 
// Post: Devuelve una representación (Nombre + Volumen + Peso) en cadena del Toxico 
//      con la indentación especificada <<nivel>>.
string Toxico::to_string(const int nivel) const {
    string indent;
    for (int i = 0; i < nivel; i++) {
        indent += "  ";
    }
    return indent + (this->nombre() + " [" + std::to_string(this->volumen()) + " m3] [" + std::to_string(this->peso()) + " kg]\n" );
}


/*---------------------------------CLASE SER VIVO---------------------------------*/

const string SerVivo::TIPO = "Seres Vivos";

// Pre: Los valores de volumen y peso son no negativos
// Post: Construye un SerVivo con el nombre, volumen y peso especificados
SerVivo::SerVivo(const std::string& nombre, double volumen, double peso)
    : ElementoGuardable(nombre, volumen, peso) {}

// Pre: <<nivel>> es no negativo 
// Post: Devuelve una representación (Nombre + Volumen + Peso) en cadena del SerVivo con la indentación especificada <<nivel>>
string SerVivo::to_string(const int nivel) const {
    string indent;
    for (int i = 0; i < nivel; i++) {
        indent += "  ";
    }
    return indent + (this->nombre() + " [" + std::to_string(this->volumen()) + " m3] [" + std::to_string(this->peso()) + " kg]\n" );
}


/*---------------------------------CLASE CAMION---------------------------------*/

// Pre: <<capacidad>> es no negativa
// Post: Construye un Camion con la capacidad especificada
Camion::Camion(double capacidad)
    : Almacen<Carga>("Camion", capacidad) {}

// Pre: <<nivel>> es no negativo 
// Post: Devuelve una representación en cadena del Camion con la indentación especificada <<nivel>>,
//       incluyendo la representación de todos los elementos guardados en <<this>>.
string Camion::to_string(const int nivel) const {
    string result;
    result += "Camion [" + std::to_string(volumen()) + " m3] [" + std::to_string(peso()) + " kg]\n";
    for (auto carga : this->cargas) {
        result += carga->to_string(nivel+1);
    }
    return result;
}

