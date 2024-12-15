/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.pace3;

import com.mycompany.pace3.Utils.HibernateUtil;
import com.mycompany.pace3.models.Bussejador;
import com.mycompany.pace3.models.Equip;
import com.mycompany.pace3.models.LlocDeBusseig;
import com.mycompany.pace3.models.Master;
import com.mycompany.pace3.Utils.Utilitats;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author carles
 */
public class PACE3 {

    public static void main(String[] args) {
        System.out.println("Hibernate funcionant");
        int opcio;
        do {
            System.out.println("\n\t\t- - - - CRUD - - - - \n");
            System.out.println("\t\t 0. Quit");
            System.out.println("\t\t 1. Insert");
            System.out.println("\t\t 2. Delete");
            System.out.println("\t\t 3. Update");
            System.out.println("\t\t 4. Select");
            opcio = Utilitats.leerEnteroC("\n\tSelect an option (0-4): ");

            switch (opcio) {
                case 1:
                    insertMenu();
                    break;

                case 2:
                    deleteMenu();
                    break;

                case 3:
                    updateMenu();
                    break;

                case 4:
                    selectMenu();
                    break;

                case 0:
                    break;
                default:
                    System.out.println("Incorrect option.");
                    break;
            }
        } while (opcio != 0);
    }

    private static void insertMenu() {
        int opcio;
        do {
            Session laSesion = HibernateUtil.getSessionFactory().getCurrentSession();
            laSesion.getTransaction().begin();
            System.out.println("\n\t\t- - - - INSERT - - - - \n");
            System.out.println("\t\t 1. Bussejador");
            System.out.println("\t\t 2. Equip");
            System.out.println("\t\t 3. Master");
            System.out.println("\t\t 4. Lloc de busseig");
            opcio = Utilitats.leerEnteroC("De quina taula vols fer el insert? (0 per eixir)");

            switch (opcio) {
                case 1:
                    Bussejador b = new Bussejador();
                    String nom = Utilitats.leerTextoC("Quin nom vols posar-li al bussejador? ");
                    b.setNom(nom);

                    String nivell = Utilitats.leerTextoC("Quin nivell té el bussejador? ");
                    b.setNivell(nivell);
                    laSesion.persist(b);
                    break;

                case 2:
                    Equip e = new Equip();
                    String tipus = Utilitats.leerTextoC("De quin tipus és l'equip? ");
                    e.setTipus(tipus);
                    laSesion.persist(e);
                    break;

                case 3:
                    Master m = new Master();
                    String nomM = Utilitats.leerTextoC("Nom del master: ");
                    m.setNom(nomM);

                    String esp = Utilitats.leerTextoC("Especialitat: ");
                    m.setEspecialitat(esp);
                    laSesion.persist(m);
                    break;

                case 4:
                    LlocDeBusseig l = new LlocDeBusseig();
                    String nomL = Utilitats.leerTextoC("Nom del lloc: ");
                    l.setNom(nomL);

                    String ubi = Utilitats.leerTextoC("Ubicació: ");
                    l.setUbicacio(ubi);
                    laSesion.persist(l);
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Incorrect option.");
                    break;
            }
            laSesion.getTransaction().commit();
            laSesion.close();
        } while (opcio != 0);
    }

    private static void deleteMenu() {
        int opcio;
        do {
            System.out.println("\n\t\t- - - - DELETE - - - - \n");
            System.out.println("\t\t 1. Bussejador");
            System.out.println("\t\t 2. Equip");
            System.out.println("\t\t 3. Master");
            System.out.println("\t\t 4. Lloc de busseig");
            System.out.println("\t\t 0. Quit");
            opcio = Utilitats.leerEnteroC("De quina taula vols fer el delete?");

            switch (opcio) {
                case 1:
                    mostrarBussejadors();
                    Session laSesion = HibernateUtil.getSessionFactory().getCurrentSession();
                    laSesion.getTransaction().begin();
                    int idBus = Utilitats.leerEnteroC("Dis-me el ID del bussejador que vols eliminar? ");
                    Bussejador b = laSesion.get(Bussejador.class, idBus);
                    laSesion.delete(b);
                    laSesion.getTransaction().commit();
                    laSesion.close();
                    break;

                case 2:
                    mostrarEquips();
                    Session laSesion2 = HibernateUtil.getSessionFactory().getCurrentSession();
                    laSesion2.getTransaction().begin();
                    int idEq = Utilitats.leerEnteroC("Dis-me el ID de l'equip que vols eliminar? ");
                    Equip e = laSesion2.get(Equip.class, idEq);

                    Query<Bussejador> q = laSesion2.createQuery("from Bussejador");

                    List<Bussejador> elsBussejadors = q.list();
                    for (Bussejador be : elsBussejadors) {
                        if (be.getEquip() != null && be.getEquip().getId_equip() == idEq) {
                            be.setEquip(null);
                        }
                    }

                    laSesion2.delete(e);
                    laSesion2.getTransaction().commit();
                    laSesion2.close();
                    break;

                case 3:
                    mostrarMasters();
                    Session laSesion3 = HibernateUtil.getSessionFactory().getCurrentSession();
                    laSesion3.getTransaction().begin();
                    int idMa = Utilitats.leerEnteroC("Dis-me el ID de l'equip que vols eliminar? ");
                    Master me = laSesion3.get(Master.class, idMa);

                    Query<Bussejador> qm = laSesion3.createQuery("from Bussejador");

                    List<Bussejador> elsBusejadors = qm.list();
                    for (Bussejador be : elsBusejadors) {
                        if (be.getMaster() != null && be.getMaster().getId_master() == idMa) {
                            be.setMaster(null);
                        }
                    }

                    laSesion3.delete(me);
                    laSesion3.getTransaction().commit();
                    laSesion3.close();
                    break;

                case 4:
                    mostrarLlocs();
                    Session laSesion4 = HibernateUtil.getSessionFactory().getCurrentSession();
                    laSesion4.getTransaction().begin();

                    int idLloc = Utilitats.leerEnteroC("Dis-me el ID del lloc de busseig que vols eliminar? ");

                    LlocDeBusseig lloc = laSesion4.get(LlocDeBusseig.class, idLloc);
                    if (lloc != null) {
                        Query<Bussejador> queryBussejadors = laSesion4.createQuery("select b from Bussejador b join b.elsLlocs l where l.id_lloc = :idLloc", Bussejador.class);
                        List<Bussejador> bussejadors = queryBussejadors.setParameter("idLloc", idLloc).list();

                        for (Bussejador bussejador : bussejadors) {
                            bussejador.getElsLlocs().remove(lloc);
                        }

                        laSesion4.delete(lloc);
                        System.out.println("Lloc de busseig eliminat correctament.");
                    } else {
                        System.out.println("No s'ha trobat cap lloc de busseig amb aquest ID.");
                    }

                    laSesion4.getTransaction().commit();
                    laSesion4.close();
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Incorrect option.");
                    break;
            }
        } while (opcio != 0);
    }

    private static void updateMenu() {
        int opcio;
        do {
            Session laSesion = HibernateUtil.getSessionFactory().getCurrentSession();
            laSesion.getTransaction().begin();
            System.out.println("\n\t\t- - - - UPDATE - - - - \n");
            System.out.println("\t\t 1. Bussejador");
            System.out.println("\t\t 2. Equip");
            System.out.println("\t\t 3. Master");
            System.out.println("\t\t 4. Lloc de busseig");
            System.out.println("\t\t 0. Quit");
            opcio = Utilitats.leerEnteroC("De quina taula vols fer el update?");

            switch (opcio) {
                case 1:
                    mostrarBussejadors();
                    int idBusUpd = Utilitats.leerEnteroC("Dis-me el id del bussejador que vols actualitzar: ");
                    Bussejador bUp = laSesion.get(Bussejador.class, idBusUpd);

                    String nouNom = Utilitats.leerTextoC("Quin nom vols posar-li? ");
                    bUp.setNom(nouNom);

                    String nouNivell = Utilitats.leerTextoC("Quin nivell vols posar-li?");
                    bUp.setNivell(nouNivell);
                    laSesion.update(bUp);
                    break;

                case 2:
                    mostrarEquips();
                    int idEqUpd = Utilitats.leerEnteroC("Dis-me el id del bussejador que vols actualitzar: ");
                    Bussejador eUp = laSesion.get(Bussejador.class, idEqUpd);

                    String nouTipus = Utilitats.leerTextoC("Quin tipus vols posar-li? ");
                    eUp.setNom(nouTipus);
                    laSesion.update(eUp);
                    break;

                case 3:
                    mostrarMasters();
                    int idMaUpd = Utilitats.leerEnteroC("Dis-me el id del bussejador que vols actualitzar: ");
                    Bussejador mUp = laSesion.get(Bussejador.class, idMaUpd);

                    String nouNomM = Utilitats.leerTextoC("Quin nom vols posar-li? ");
                    mUp.setNom(nouNomM);
                    String novaEsp = Utilitats.leerTextoC("Quina especialitat vols posar-li? ");
                    mUp.setNom(novaEsp);

                    laSesion.update(mUp);
                    break;

                case 4:
                    mostrarLlocs();
                    int idLlocUpd = Utilitats.leerEnteroC("Dis-me el id del bussejador que vols actualitzar: ");
                    Bussejador llUp = laSesion.get(Bussejador.class, idLlocUpd);

                    String nouNomL = Utilitats.leerTextoC("Quin nom vols posar-li? ");
                    llUp.setNom(nouNomL);
                    String novaUb = Utilitats.leerTextoC("Quina ubicació vols posar-li? ");
                    llUp.setNom(novaUb);

                    laSesion.update(llUp);
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Incorrect option.");
                    break;
            }
            laSesion.getTransaction().commit();
            laSesion.close();
        } while (opcio != 0);
    }

    private static void selectMenu() {
        int opcio;
        do {
            System.out.println("\n\t\t- - - - SELECT - - - - \n");
            System.out.println("\t\t 1. Bussejador");
            System.out.println("\t\t 2. Equip");
            System.out.println("\t\t 3. Master");
            System.out.println("\t\t 4. Lloc de busseig");
            System.out.println("\t\t 0. Quit");
            opcio = Utilitats.leerEnteroC("De quina taula vols fer el select?");

            switch (opcio) {
                case 1:
                    mostrarBussejadors();
                    break;

                case 2:
                    mostrarEquips();
                    break;

                case 3:
                    mostrarMasters();
                    break;

                case 4:
                    mostrarLlocs();
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Incorrect option.");
                    break;
            }
        } while (opcio != 0);
    }

    public static void mostrarBussejadors() {
        Session laSesion = HibernateUtil.getSessionFactory().getCurrentSession();
        laSesion.getTransaction().begin();
        Query<Bussejador> q = laSesion.createQuery("from Bussejador");

        List<Bussejador> elsBussejadors = q.list();
        System.out.printf("%-6s %-20s %-20s%n", "--ID--", "--Nom--", "--Nivell--");
        for (Bussejador b : elsBussejadors) {
            System.out.printf("%-6d %-20s %-20s%n", b.getId_bussejador(), b.getNom(), b.getNivell());
        }

        // Preguntar una vez si se quieren ver los sitios de buceo para todos los buceadores
        String mostrarLlocs = Utilitats.leerTextoC("Vols veure els llocs de busseig associats als bussejadors? (si/no): ").toLowerCase();

        // Si la respuesta es "si", mostrar los sitios de buceo para todos los buceadores
        if (mostrarLlocs.equals("si")) {
            System.out.println("\n\tMostrant llocs de busseig per a tots els bussejadors:");

            // Recorrer los buceadores y mostrar los sitios de buceo asociados
            for (Bussejador b : elsBussejadors) {
                if (!b.getElsLlocs().isEmpty()) {
                    System.out.printf("\nBussejador: %s\n", b.getNom());
                    System.out.printf("%-6s %-20s %-30s%n", "--ID--", "--Nom--", "--Ubicació--");
                    for (LlocDeBusseig lloc : b.getElsLlocs()) {
                        System.out.printf("%-6d %-20s %-30s%n", lloc.getId_lloc(), lloc.getNom(), lloc.getUbicacio());
                    }
                }
            }
        }

        laSesion.getTransaction().commit();
        laSesion.close();
    }

    public static void mostrarEquips() {
        Session laSesion = HibernateUtil.getSessionFactory().getCurrentSession();
        laSesion.getTransaction().begin();
        Query<Equip> e = laSesion.createQuery("from Equip");

        List<Equip> elsEquips = e.list();
        System.out.printf("%-6s %-20s%n", "--ID--", "--Tipus--");
        for (Equip eq : elsEquips) {
            System.out.printf("%-6d %-20s%n", eq.getId_equip(), eq.getTipus());
        }
        laSesion.getTransaction().commit();
        laSesion.close();
    }

    public static void mostrarMasters() {
        Session laSesion = HibernateUtil.getSessionFactory().getCurrentSession();
        laSesion.getTransaction().begin();
        Query<Master> m = laSesion.createQuery("from Master");

        List<Master> elsMasters = m.list();
        System.out.printf("%-6s %-20s %-30s%n", "--ID--", "--Nom--", "--Especialitat--");
        for (Master ma : elsMasters) {
            System.out.printf("%-6d %-20s %-30s%n", ma.getId_master(), ma.getNom(), ma.getEspecialitat());
        }
        laSesion.getTransaction().commit();
        laSesion.close();
    }

    public static void mostrarLlocs() {
        Session laSesion = HibernateUtil.getSessionFactory().getCurrentSession();
        laSesion.getTransaction().begin();
        Query<LlocDeBusseig> l = laSesion.createQuery("from LlocDeBusseig");

        List<LlocDeBusseig> elsLlocs = l.list();
        System.out.printf("%-6s %-20s %-30s%n", "--ID--", "--Nom--", "--Ubicació---");
        for (LlocDeBusseig ll : elsLlocs) {
            System.out.printf("%-6d %-20s %-30s%n", ll.getId_lloc(), ll.getNom(), ll.getUbicacio());
        }
        laSesion.getTransaction().commit();
        laSesion.close();
    }
}
