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

## Explicación juegos
1. **Cuadrado Mágico:**
   ### Clase Tablero:
   * Con el cuadrado mágico resuelto, se crearon variantes de éste para mayor variedad de posibilidades de resolución.
   ### Clase CuadradoMagico:
   * Se crearon metodos para controlar que los números no se repitan,evaluar la suma en cada linea,si se puede continuar jugando, si es cuadrado mágico, y para imprimir el tablero para jugar.
   ### Clase JugarCuadradoMagico:
   * Se elige al azar una de las variantes o la original y también se elige de forma aleatoria la linea que se mostrará para comenzar el juego.
   * Cada que el jugador teclea un número en una coordenada especifica se evalua si se puede seguir jugando.
   * Si en una linea completa no se cumple la constante magica (es decir 34),
   * o si en una linea con espacios vacios ya no se puede conseguir la constante, se detiene el juego y el usuario pierde.
   * De lo contrario el juego sigue hasta completar el cuadrado mágico (si se completa quiere decir que es cuadrado magico ya que siempre se detendrá si ya no se cumple la suma en alguna linea).
   * Se evalua al final si se cumple la constante magica en cada linea y si es asi, el jugador gana. 
2. **Conecta 4:**
3. **Salvado:**
   ### Clase JugarSalvado:
   * Se inicializa un arreglo de 100 espacios con números del 1 al 100 (simulando a 100 personas sentadas cada una en una silla).
   * Se escoge un número n al azar del 1 al 100, lo cuál definira los saltos (de cuanto en cuanto va eliminando a las personas).
   * Conforme va avanzando el juego, el sistema va eliminando a las personas que caigan en la posición n (sin contar a las que ya hayan sido eliminadas).
   * El jugador deberá adivinar en que posición del 1 al 100 esta la persona que sobrevivió al final, y si no le atinó pierde.
4. **Torres de Hanoi:**

## Comentarios
1. Puedes utilizar todo lo revisado hasta el momento.
2. Es importante que comentes tú código en formato javadoc.
3. Se debe crear y usarlas clases indicadas con sus respectivos métodos asociados.
4. La fecha de entrega se acordará en el laboratorio de clases.
5. Al finalizar se debe compartir el link HTTPS y SSH por el classroom.
