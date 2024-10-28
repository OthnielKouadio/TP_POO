import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Interface Louable
interface Louable {
    void louer();
    void retourner();
}

// Classe abstraite Vehicule
abstract class Vehicule implements Louable {
    String immatriculation;
    String marque;
    String modele;
    int anneeMiseEnService;
    int kilometrage;
    private boolean estDisponible;

    public Vehicule(String immatriculation, String marque, String modele, int anneeMiseEnService, int kilometrage) {
        this.immatriculation = immatriculation;
        this.marque = marque;
        this.modele = modele;
        this.anneeMiseEnService = anneeMiseEnService;
        this.kilometrage = kilometrage;
        this.estDisponible = true;
    }
    public String getImmatriculation() {
        return immatriculation;
    }
    public boolean isDisponible() {
        return estDisponible;
    }

    protected void setEstDisponible(boolean estDisponible) {
        this.estDisponible = estDisponible;
    }

    public abstract double calculerPrixLocation();
}

// Classe Voiture
class Voiture extends Vehicule {
    public int nombrePlaces;
    public String typeCarburant;

    public Voiture(String immatriculation, String marque, String modele, int anneeMiseEnService, int kilometrage, int nombrePlaces, String typeCarburant) {
        super(immatriculation, marque, modele, anneeMiseEnService, kilometrage);
        this.nombrePlaces = nombrePlaces;
        this.typeCarburant = typeCarburant;
    }

    @Override
    public double calculerPrixLocation() {
        return 50.0; // Exemple de prix
    }

    @Override
    public void louer() {
        setEstDisponible(false);
    }

    @Override
    public void retourner() {
        setEstDisponible(true);
    }
}

// Classe Camion
class Camion extends Vehicule {
    public double capaciteChargement;
    public int nombreEssieux;

    public Camion(String immatriculation, String marque, String modele, int anneeMiseEnService, int kilometrage, double capaciteChargement, int nombreEssieux) {
        super(immatriculation, marque, modele, anneeMiseEnService, kilometrage);
        this.capaciteChargement = capaciteChargement;
        this.nombreEssieux = nombreEssieux;
    }

    @Override
    public double calculerPrixLocation() {
        return 80.0; // Exemple de prix
    }

    @Override
    public void louer() {
        setEstDisponible(false);
    }

    @Override
    public void retourner() {
        setEstDisponible(true);
    }
}

// Classe Client
class Client {
    String nom;
    String prenom;
    String numeroPermis;
    String numeroTelephone;
    List<Vehicule> locationsEnCours;

    public Client(String nom, String prenom, String numeroPermis, String numeroTelephone) {
        this.nom = nom;
        this.prenom = prenom;
        this.numeroPermis = numeroPermis;
        this.numeroTelephone = numeroTelephone;
        this.locationsEnCours = new ArrayList<>();
    }

    public void ajouterLocation(Vehicule vehicule) {
        locationsEnCours.add(vehicule);
    }

    public void supprimerLocation(Vehicule vehicule) {
        locationsEnCours.remove(vehicule);
    }
}


class VehiculeIndisponibleException extends Exception {
    public VehiculeIndisponibleException(String message) {
        super(message);
    }
}

// Exception ClientNonAutoriseException
class ClientNonAutoriseException extends Exception {
    public ClientNonAutoriseException(String message) {
        super(message);
    }
}

// Classe ParcAutomobile
class ParcAutomobile {
    public ArrayList<Vehicule> vehicules;

    public ParcAutomobile() {
        this.vehicules = new ArrayList<>();
    }

    public void ajouterVehicule(Vehicule vehicule) {
        vehicules.add(vehicule);
    }

    public void listerVehiculesDisponibles() {
        for (Vehicule v : vehicules) {
            if (v.isDisponible()) {
                System.out.println(v);
            }
        }
    }

    public Vehicule trouverVehicule(String immatriculation) {
        for (Vehicule v : vehicules) {
            if (v.getImmatriculation().equals(immatriculation)) {
                return v;
            }
        }
        return null;
    }
}

// Classe principale avec le menu

public class Main {
    public static ParcAutomobile parc = new ParcAutomobile();
    public static List<Client> clients = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Menu principal :");
            System.out.println("1. Ajouter un véhicule");
            System.out.println("2. Ajouter un client");
            System.out.println("3. Louer un véhicule");
            System.out.println("4. Retourner un véhicule");
            System.out.println("5. Afficher les véhicules disponibles");
            System.out.println("6. Quitter");
            System.out.print("Choisissez une option : ");
            int choix = scanner.nextInt();
            scanner.nextLine(); // Consommer le retour de ligne

            switch (choix) {
                case 1:
                    ajouterVehicule(scanner);
                    break;
                case 2:
                    ajouterClient(scanner);
                    break;
                case 3:
                    louerVehicule(scanner);
                    break;
                case 4:
                    retournerVehicule(scanner);
                    break;
                case 5:
                    parc.listerVehiculesDisponibles();
                    break;
                case 6:
                    System.exit(0);
                default:
                    System.out.println("Option invalide.");
            }
        }
    }

    private static void ajouterVehicule(Scanner scanner) {
        System.out.print("Type de véhicule (voiture/camion) : ");
        String type = scanner.nextLine();

        System.out.print("Immatriculation : ");
        String immatriculation = scanner.nextLine();
        System.out.print("Marque : ");
        String marque = scanner.nextLine();
        System.out.print("Modèle : ");
        String modele = scanner.nextLine();
        System.out.print("Année de mise en service : ");
        int annee = scanner.nextInt();
        System.out.print("Kilométrage : ");
        int kilometrage = scanner.nextInt();
        scanner.nextLine(); // Consommer le retour de ligne

        if (type.equalsIgnoreCase("voiture")) {
            System.out.print("Nombre de places : ");
            int places = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Type de carburant(essence , diesel , électrique) : ");
            String carburant = scanner.nextLine();
            parc.ajouterVehicule(new Voiture(immatriculation, marque, modele, annee, kilometrage, places, carburant));
        } else if (type.equalsIgnoreCase("camion")) {
            System.out.print("Capacité de chargement (tonnes) : ");
            double capacite = scanner.nextDouble();
            System.out.print("Nombre d'essieux : ");
            int essieux = scanner.nextInt();
            parc.ajouterVehicule(new Camion(immatriculation, marque, modele, annee, kilometrage, capacite, essieux));
        } else {
            System.out.println("Type de véhicule inconnu.");
        }
    }

    private static void ajouterClient(Scanner scanner) {
        System.out.print("Nom : ");
        String nom = scanner.nextLine();
        System.out.print("Prénom : ");
        String prenom = scanner.nextLine();
        System.out.print("Numéro de permis : ");
        String numeroPermis = scanner.nextLine();
        System.out.print("Numéro de téléphone : ");
        String numeroTelephone = scanner.nextLine();
        clients.add(new Client(nom, prenom, numeroPermis, numeroTelephone));
    }

    public static void louerVehicule(Scanner scanner) {
        // Implémentation du processus de location avec gestion des exceptions
    }

    public static void retournerVehicule(Scanner scanner) {
        // Implémentation du processus de retour de véhicule
    }
}

