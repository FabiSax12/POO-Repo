"""
class Vehiculo {
  String marca
  String modelo
  int anio
  int kilometraje

  void acelerar()

  void frenar()

  void arrancar()

  void describir()
}
"""


class Vehiculo:
  def __init__(self, marca, modelo, anio, kilometraje):
    self.marca = marca
    self.modelo = modelo
    self.anio = anio
    self.kilometraje = kilometraje

  def acelerar(self):
    print(f"{self.marca} {self.modelo} acelerando...")

  def frenar(self):
    print(f"{self.marca} {self.modelo} frenando...")

  def arrancar(self):
    print(f"{self.marca} {self.modelo} arrancando...")

  def describir(self):
    print(f"Marca: {self.marca}\nModelo: {self.modelo}\nAño: {self.anio}\nKilometraje: {self.kilometraje}")