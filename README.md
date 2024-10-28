Explication du Code : Système de Location de Véhicules
Ce code Java implémente un système de location de véhicules, en utilisant les concepts fondamentaux de la programmation orientée objet (POO). Voici les différentes notions de POO illustrées dans ce projet :

1. Classes et Objets
Classes : Plusieurs classes sont définies, représentant des entités clés :
Vehicule : classe abstraite de base.
Voiture : classe dérivée de Vehicule.
Camion : classe dérivée de Vehicule.
Client : représente un client du service de location.
ParcAutomobile : gère la collection de véhicules.
Objets : Des objets peuvent être créés à partir de ces classes, comme des instances de Voiture ou de Camion.
2. Héritage
Les classes Voiture et Camion héritent de Vehicule, permettant de réutiliser ses attributs et méthodes.
Cela favorise la réutilisation du code et permet aux sous-classes d'étendre ou de modifier le comportement de la classe parente.
3. Polymorphisme
La méthode calculerPrixLocation() dans Vehicule est redéfinie dans Voiture et Camion. Cela permet d'appeler cette méthode sur des objets de type Vehicule, sans connaître leur type exact (voiture ou camion).
4. Encapsulation
Les attributs privés, comme estDisponible dans Vehicule, empêchent l'accès direct depuis l'extérieur de la classe.
Des méthodes publiques (getters et setters) permettent de contrôler l'accès et la modification des attributs.
5. Interfaces
L'interface Louable définit des méthodes que les classes Voiture et Camion doivent implémenter (louer() et retourner()).
Cela garantit que toutes les classes louables possèdent ces méthodes, favorisant l'uniformité.
6. Classes Abstraites
Vehicule est une classe abstraite, ce qui signifie qu'elle ne peut pas être instanciée directement. Elle contient des méthodes abstraites que les classes dérivées doivent implémenter.
7. Gestion des Exceptions
Des classes d'exception personnalisées (VehiculeIndisponibleException, ClientNonAutoriseException) gèrent les erreurs spécifiques de manière élégante, permettant une meilleure gestion des flux de contrôle.
8. Collections
Utilisation de ArrayList pour stocker dynamiquement des objets Vehicule dans ParcAutomobile et des objets Client dans le système.
9. Méthodes Statique et Instanciées
La méthode main est statique, permettant de démarrer l'application sans créer d'instance de la classe Main.
D'autres méthodes, comme ajouterVehicule et ajouterClient, sont des méthodes d'instance.
