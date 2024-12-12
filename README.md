# Introducción a las Ciencias de la Computación
## Práctica Final
El objetivo de esta práctica es que el alumno ejercite la de aplicación y uso de todo lo visto a lo largo del curso.

## Equipo #11 
### Alumno 1:
* Micael Nolasco Flores

* No. Cuenta: 322132281

* Correo: micaelnf@ciencias.unam.mx

### Alumno 2:
* Seyin Xuxek Romualdo Valera

* No. Cuenta: 322298446

* Correo: seyin06rv@ciencias.unam.mx
  
## Instrucciones de uso
1. **Compilar el programa:**

```bash
javac Main.java
```

2. **Ejecutar el programa:**

```bash
java Main
```

3. **Seguir las instrucciones del programa.**

### Explicación de la primera parte del programa

### `Cración y manejo de usuarios`
Se inicia el programa y lo primero que puedes ver es un menú que te da la bienvenida, esta primera sección del menú se encarga de la creación y manejo de sesiones, ejemplo de esta parte del menú:

```bash
                                    Bienvenido a la Feria de Ciencias                              
------------------------------------------------------------------------------------------------------------------------
Por favor indica si quieres crear una nueva cuenta o si quieres reanudar una sesión.
(1) Para crear una nueva cuenta      (2) Para reanudar una sesión      (0) Salir del programa
------------------------------------------------------------------------------------------------------------------------
```

Si decides crear un nuevo usuario, de desplegará un menú que te solicitará un nombre, recuerda que este nombre no debe de existir, y en caso de que ya exista simplemente sera como
reanudar la sesión en dicha cuenta con ese nombre de usuario, también es importante saber que el programa solo permite acarácteres de la A-Z, en otro caso simplemente ignorara otros
carácteres y también es importante saber que el número de carácteres va de 3-15 carácteres válidos, ejemplo de la creación de una nueva sesión (el usuario creado cuenta con 100 creditos
iniciales y únicos, así que usalos bien):

```bash
------------------------------------------------------------------------------------------------------------------------
Por favor introduce tu nombre (ten en cuenta que solo se aceptan números y carácteres no especiales):
Ingrese un nombre: UsuarioNuevo 

Se ha creado con exito el usuario "USUARIONUEVO":
	*Nombre de usuario: USUARIONUEVO
	*Saldo actual: 100
	*Puntuación: 0

El usuario se guardo correctamente en: src/Partidas/USUARIONUEVO.txt

¡¡IMPORTANTE, recuerda que solo cuentas con 100 créditos para jugar durante los dos días!!
------------------------------------------------------------------------------------------------------------------------
```

Ahora bien si decides reanudar una sesión, es más simple ya que solo tienes que seleccionar una de las tantas sesiones ya existentes, y si aún no hay ninguan sesión, simplemente se te redirigira al menú
de creación de sesiones, así como que se te mencionara el por que estas ahí, ejemplo de reanudar una sesión:

```bash
------------------------------------------------------------------------------------------------------------------------
Por favor escoge que sesión quieres reanudar:

Usuario 1:
	*Nombre de usuario: USUARIONUEVO
	*Saldo actual: 100
	*Puntuación: 0

Escoge una opción: 1

Has escogido el usuario USUARIONUEVO
------------------------------------------------------------------------------------------------------------------------
```

---

### `Selección de días`
Esta parte es bastante simple una vez que hayas iniciado sesión, se te pedirá escoger un día, una vez escogido un día solo podrás jugar juegos de ese día sin posibilidad de
moverte de día.

```bash
------------------------------------------------------------------------------------------------------------------------
¿Que día quieres jugar?
(1) Día 1          (2) Día 2          (0) Salir del programa
------------------------------------------------------------------------------------------------------------------------
```

---

### Explicación de los juegos del programa

### `Cuadrado Mágico`
**Clase `Tablero`**  
- Con el cuadrado mágico resuelto, se crearon variantes de este para ofrecer mayor variedad de posibilidades de resolución.  

**Clase `CuadradoMagico`**  
- Se desarrollaron métodos para:  
  - Controlar que los números no se repitan.  
  - Evaluar la suma en cada línea.  
  - Determinar si es posible continuar jugando.  
  - Verificar si el tablero es un cuadrado mágico.  
  - Imprimir el tablero de juego para que el jugador interactúe.  

**Clase `JugarCuadradoMagico`**  
- Se selecciona aleatoriamente una variante del cuadrado mágico (o el original).  
- También se elige de manera aleatoria una línea inicial que se mostrará para comenzar el juego.  
- Cada vez que el jugador ingresa un número en una coordenada específica, se evalúa si el juego puede continuar.  
- El juego termina si:  
  - Una línea completa no cumple la constante mágica (34).  
  - Una línea con espacios vacíos no puede lograr la constante mágica.  
- Si el jugador completa el cuadrado mágico, gana automáticamente, ya que siempre se valida que todas las líneas cumplan la constante mágica antes de finalizar el juego.

---

### `Conecta 4`
Este juego consta de una simulación del juego de Conecta 4. Lo primero con lo que te encontraras es con un menú que te brindará las reglas a seguir y el objetivo a lograr, ejemplo:
```bash
------------------------------------------------------------------------------------------------------------------------
INSTRUCCIONES:
* El juego se juega en un tablero de 7 columnas y 6 filas.
* Dos jugadores se turnan para dejar fichas en una de las columnas.
* Las fichas caen al espacio disponible más bajo de la columna seleccionada.
* El objetivo es conectar 4 fichas consecutivas en línea horizontal, vertical o diagonal.
* Si el tablero se llena sin que nadie conecte 4 fichas, el juego termina en empate.
------------------------------------------------------------------------------------------------------------------------
Por favor escoge si quieres jugar contra la máquina o en contra de otro jugador:
(1) Para jugar contra otro jugador        (2) Para poder jugar en contra de la máquina        (0) Salir del programa
------------------------------------------------------------------------------------------------------------------------
```

En caso de que hayas seleccionado el jugar contra otro jugador se te desplegara un nuevo menú, que te dará la opción de crear un nuevo jugador, o jugar en contra de un jugador ya
existente, algo que es importante saber es que no se le cobrara el monto respectivo a este segundo usuario, por ende tampoco ganará puntos, el úncio que puede ganar puntos es el 
jugador que se selecciono al inicio del programa. Ejemplo del menú para jugar contra otro jugador:

```bash
------------------------------------------------------------------------------------------------------------------------
Por favor indica si quieres crear una nueva cuenta o si quieres reanudar una sesión.
(1) Para crear una nueva cuenta      (2) Para reanudar una sesión      (0) Salir del programa
------------------------------------------------------------------------------------------------------------------------
```

Si decides crear un nuevo usuario, este literalmente se creara y podrá usarse al volver a iniciar el programa, ejemplo de creación de una nueva cuenta.

```bash
------------------------------------------------------------------------------------------------------------------------
Por favor introduce tu nombre (ten en cuenta que solo se aceptan números y carácteres no especiales):
Ingrese un nombre: NombreIngresado

Se ha creado con exito el usuario "NOMBREINGRESADO":
	*Nombre de usuario: NOMBREINGRESADO
	*Saldo actual: 100
	*Puntuación: 0

El usuario se guardo correctamente en: src/Partidas/NOMBREINGRESADO.txt

¡¡IMPORTANTE, recuerda que solo cuentas con 100 créditos para jugar durante los dos días!!
```

Y si decides jugar con una cuenta ya existente asegurate de escoger una cuenta diferente a la tuya, y en caso de que escojas tu misma cuenta se te dará permiso de corregir tu elección
y se te avisará de lo que hiciste. Ejemplo de reanudar una sesión:

```bash
------------------------------------------------------------------------------------------------------------------------
Por favor escoge que sesión quieres reanudar:

Usuario 1:
	*Nombre de usuario: NOMBREINGRESADO
	*Saldo actual: 100
	*Puntuación: 0

Usuario 2:
	*Nombre de usuario: USUARIO1
	*Saldo actual: 100
	*Puntuación: 0

Escoge una opción: 1

Has escogido el usuario NOMBREINGRESADO
------------------------------------------------------------------------------------------------------------------------
```

Si escoges jugar en contra de la máquina simplemente te debes de preocupar por, seguir las reglas del juego, ejemplo de como se vería el juego:
Jugador vds Jugador:
```bash
------------------------------------------------------------------------------------------------------------------------
El jugador 1 "USUARIO1" jugara con las fichas O
El jugador 2 "NOMBREINGRESADO" jugara con las fichas X
------------------------------------------------------------------------------------------------------------------------
                                                                                                 PRESIONA (9) PARA SALIR

Turno del jugador "USUARIO1"

  1   2   3   4   5   6   7
-----------------------------
|   |   |   |   |   |   |   |
-----------------------------
|   |   |   |   |   |   |   |
-----------------------------
|   |   |   |   |   |   |   |
-----------------------------
|   |   |   |   |   |   |   |
-----------------------------
|   |   |   |   |   |   |   |
-----------------------------
|   |   |   |   |   |   |   |
-----------------------------

Por favor indica la columna donde quieras colocar una ficha: 1
------------------------------------------------------------------------------------------------------------------------
                                                                                                 PRESIONA (9) PARA SALIR

Turno del jugador "NOMBREINGRESADO"

  1   2   3   4   5   6   7
-----------------------------
|   |   |   |   |   |   |   |
-----------------------------
|   |   |   |   |   |   |   |
-----------------------------
|   |   |   |   |   |   |   |
-----------------------------
|   |   |   |   |   |   |   |
-----------------------------
|   |   |   |   |   |   |   |
-----------------------------
| O |   |   |   |   |   |   |
-----------------------------

Por favor indica la columna donde quieras colocar una ficha: 7
------------------------------------------------------------------------------------------------------------------------
                                                                                                 PRESIONA (9) PARA SALIR

Turno del jugador "USUARIO1"

  1   2   3   4   5   6   7
-----------------------------
|   |   |   |   |   |   |   |
-----------------------------
|   |   |   |   |   |   |   |
-----------------------------
|   |   |   |   |   |   |   |
-----------------------------
|   |   |   |   |   |   |   |
-----------------------------
|   |   |   |   |   |   |   |
-----------------------------
| O |   |   |   |   |   | X |
-----------------------------

Por favor indica la columna donde quieras colocar una ficha: 
```
Y con la máquina sería exactamente lo mismo solo que al hacer una jugada la máquina instantaneamente inserta su jugada. Los sistemas de puntuación son:
* Victoria: El jugador ganará +10 puntos.
* Empate: El jugador ganará +5 puntos.
* Derrota: El jugador ganará +2 puntos.

---

### `Salvado`
**Clase `JugarSalvado`**  
- Se inicializa un arreglo con 100 posiciones que representan personas sentadas en un círculo (del 1 al 100).  
- Se elige un número \(n\) al azar entre 1 y 100, que define el intervalo de eliminación (de cuántos en cuántos se elimina).  
- El sistema elimina a las personas en las posiciones correspondientes al intervalo, avanzando en el círculo, ignorando a quienes ya fueron eliminados.  
- El jugador debe adivinar la posición del único sobreviviente al final.  
- Si el jugador adivina correctamente, gana; de lo contrario, pierde.

---

### `Torres de Hanoi`
Este juego consta de una simulación del juego de las Torres de Hanoi. Lo primero con lo que te encontraras es con un menú que te brindará las reglas a seguir y el objetivo a lograr, ejemplo:

```bash
------------------------------------------------------------------------------------------------------------------------
INSTRUCCIONES:
* Solo puedes mover un disco a la vez.
* Un disco solo puede colocarse sobre otro más grande o en un poste vacío.
* El objetivo es mover todos los discos de la torre inicial a la torre del extremo derecho.
------------------------------------------------------------------------------------------------------------------------
```

Cuando inicias la simulación, se mostrará el estado actual de las torres, incluyendo la posición de los discos, y se te pedirá que indiques tus movimientos. Ejemplo de esto:

```bash
------------------------------------------------------------------------------------------------------------------------
                                                                                                 PRESIONA (9) PARA SALIR

      ***                   |                    |       
     *****                  |                    |       
    *******                 |                    |       
   *********                |                    |       
  ***********               |                    |       
 *************              |                    |       
    Torre 1              Torre 2              Torre 3     

Por favor indica la torre desde donde quieres mover el disco: 
1

Por favor indica la torre a donde quieres mover el disco: 
3
------------------------------------------------------------------------------------------------------------------------
                                                                                                 PRESIONA (9) PARA SALIR

       |                    |                    |       
     *****                  |                    |       
    *******                 |                    |       
   *********                |                    |       
  ***********               |                    |       
 *************              |                   ***      
    Torre 1              Torre 2              Torre 3    

Por favor indica la torre desde donde quieres mover el disco: 

```

El juego registra los movimientos realizados y evalúa el rendimiento respecto a estos, el sistema de puntuación es:
* Si el jugador resuelve el juego en el número mínimo de movimientos (63 movimientos para 6 discos), obtiene +10 puntos.
* Si se resuelve con hasta 10 movimientos adicionales, se otorgarán +5 puntos.
* Si se utiliza un número mayor de movimientos, pero se completa el juego, el jugador obtiene +2 puntos.

---
   
## Comentarios
1. Puedes utilizar todo lo revisado hasta el momento.
2. Es importante que comentes tú código en formato javadoc.
3. Se debe crear y usarlas clases indicadas con sus respectivos métodos asociados.
4. La fecha de entrega se acordará en el laboratorio de clases.
5. Al finalizar se debe compartir el link HTTPS y SSH por el classroom.
