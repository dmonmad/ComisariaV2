/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nightm4re.comisariav2.controllers;

import com.nightm4re.comisariav2.controllers.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.nightm4re.comisariav2.modelo.AntecedentesEntity;
import java.util.ArrayList;
import java.util.List;
import com.nightm4re.comisariav2.modelo.CorreoEntity;
import com.nightm4re.comisariav2.modelo.NumeroTelefonoEntity;
import com.nightm4re.comisariav2.modelo.DireccionEntity;
import com.nightm4re.comisariav2.modelo.MatriculaEntity;
import com.nightm4re.comisariav2.modelo.DatosExtraEntity;
import com.nightm4re.comisariav2.modelo.FotoEntity;
import com.nightm4re.comisariav2.modelo.SospechosoEntity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Nightm4re
 */
public class SospechosoEntityJpaController implements Serializable {

    public SospechosoEntityJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SospechosoEntity sospechosoEntity) {
        if (sospechosoEntity.getAntecedentes() == null) {
            sospechosoEntity.setAntecedentes(new ArrayList<AntecedentesEntity>());
        }
        if (sospechosoEntity.getCorreos() == null) {
            sospechosoEntity.setCorreos(new ArrayList<CorreoEntity>());
        }
        if (sospechosoEntity.getTelefonos() == null) {
            sospechosoEntity.setTelefonos(new ArrayList<NumeroTelefonoEntity>());
        }
        if (sospechosoEntity.getDirecciones() == null) {
            sospechosoEntity.setDirecciones(new ArrayList<DireccionEntity>());
        }
        if (sospechosoEntity.getMatriculas() == null) {
            sospechosoEntity.setMatriculas(new ArrayList<MatriculaEntity>());
        }
        if (sospechosoEntity.getDatosextra() == null) {
            sospechosoEntity.setDatosextra(new ArrayList<DatosExtraEntity>());
        }
        if (sospechosoEntity.getFotos() == null) {
            sospechosoEntity.setFotos(new ArrayList<FotoEntity>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            em.persist(sospechosoEntity);

            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SospechosoEntity sospechosoEntity) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            System.out.println(sospechosoEntity);
            System.out.println(sospechosoEntity);
            System.out.println(sospechosoEntity);
            System.out.println(sospechosoEntity);
            System.out.println(sospechosoEntity);
            System.out.println(sospechosoEntity);
            System.out.println(sospechosoEntity);
            etraertf earfaerfearea
            
            SospechosoEntity persistentSospechosoEntity = em.find(SospechosoEntity.class, sospechosoEntity.getId());
            System.out.println("1. #####"+sospechosoEntity.getId()+"AFTER FIND");
            System.out.println("2. #####"+persistentSospechosoEntity);
            List<AntecedentesEntity> antecedentesOld = persistentSospechosoEntity.getAntecedentes();
            List<AntecedentesEntity> antecedentesNew = sospechosoEntity.getAntecedentes();
            System.out.println("3. #####"+antecedentesNew);
            System.out.println("4. #####"+antecedentesNew.get(0).getId());
            List<CorreoEntity> correosOld = persistentSospechosoEntity.getCorreos();
            List<CorreoEntity> correosNew = sospechosoEntity.getCorreos();
            List<NumeroTelefonoEntity> telefonosOld = persistentSospechosoEntity.getTelefonos();
            List<NumeroTelefonoEntity> telefonosNew = sospechosoEntity.getTelefonos();
            List<DireccionEntity> direccionesOld = persistentSospechosoEntity.getDirecciones();
            List<DireccionEntity> direccionesNew = sospechosoEntity.getDirecciones();
            List<MatriculaEntity> matriculasOld = persistentSospechosoEntity.getMatriculas();
            List<MatriculaEntity> matriculasNew = sospechosoEntity.getMatriculas();
            List<DatosExtraEntity> datosextraOld = persistentSospechosoEntity.getDatosextra();
            List<DatosExtraEntity> datosextraNew = sospechosoEntity.getDatosextra();
            List<FotoEntity> fotosOld = persistentSospechosoEntity.getFotos();
            List<FotoEntity> fotosNew = sospechosoEntity.getFotos();
            System.out.println("LLEGA 1");
            List<AntecedentesEntity> attachedAntecedentesNew = new ArrayList<AntecedentesEntity>();
            for (AntecedentesEntity antecedentesNewAntecedentesEntityToAttach : antecedentesNew) {
                System.out.println("LLEGA 1.1 --- "+antecedentesNewAntecedentesEntityToAttach.getId());
                antecedentesNewAntecedentesEntityToAttach = em.getReference(antecedentesNewAntecedentesEntityToAttach.getClass(), antecedentesNewAntecedentesEntityToAttach.getId());
                System.out.println("LLEGA 1.2");
                attachedAntecedentesNew.add(antecedentesNewAntecedentesEntityToAttach);
            }
            antecedentesNew = attachedAntecedentesNew;
            sospechosoEntity.setAntecedentes(antecedentesNew);
            List<CorreoEntity> attachedCorreosNew = new ArrayList<CorreoEntity>();
            for (CorreoEntity correosNewCorreoEntityToAttach : correosNew) {
                correosNewCorreoEntityToAttach = em.getReference(correosNewCorreoEntityToAttach.getClass(), correosNewCorreoEntityToAttach.getId());
                attachedCorreosNew.add(correosNewCorreoEntityToAttach);
            }
            correosNew = attachedCorreosNew;
            sospechosoEntity.setCorreos(correosNew);
            List<NumeroTelefonoEntity> attachedTelefonosNew = new ArrayList<NumeroTelefonoEntity>();
            for (NumeroTelefonoEntity telefonosNewNumeroTelefonoEntityToAttach : telefonosNew) {
                telefonosNewNumeroTelefonoEntityToAttach = em.getReference(telefonosNewNumeroTelefonoEntityToAttach.getClass(), telefonosNewNumeroTelefonoEntityToAttach.getId());
                attachedTelefonosNew.add(telefonosNewNumeroTelefonoEntityToAttach);
            }
            telefonosNew = attachedTelefonosNew;
            sospechosoEntity.setTelefonos(telefonosNew);
            List<DireccionEntity> attachedDireccionesNew = new ArrayList<DireccionEntity>();
            for (DireccionEntity direccionesNewDireccionEntityToAttach : direccionesNew) {
                direccionesNewDireccionEntityToAttach = em.getReference(direccionesNewDireccionEntityToAttach.getClass(), direccionesNewDireccionEntityToAttach.getId());
                attachedDireccionesNew.add(direccionesNewDireccionEntityToAttach);
            }
            direccionesNew = attachedDireccionesNew;
            sospechosoEntity.setDirecciones(direccionesNew);
            List<MatriculaEntity> attachedMatriculasNew = new ArrayList<MatriculaEntity>();
            for (MatriculaEntity matriculasNewMatriculaEntityToAttach : matriculasNew) {
                matriculasNewMatriculaEntityToAttach = em.getReference(matriculasNewMatriculaEntityToAttach.getClass(), matriculasNewMatriculaEntityToAttach.getId());
                attachedMatriculasNew.add(matriculasNewMatriculaEntityToAttach);
            }
            System.out.println("LLEGA 2");
            matriculasNew = attachedMatriculasNew;
            sospechosoEntity.setMatriculas(matriculasNew);
            List<DatosExtraEntity> attachedDatosextraNew = new ArrayList<DatosExtraEntity>();
            for (DatosExtraEntity datosextraNewDatosExtraEntityToAttach : datosextraNew) {
                datosextraNewDatosExtraEntityToAttach = em.getReference(datosextraNewDatosExtraEntityToAttach.getClass(), datosextraNewDatosExtraEntityToAttach.getId());
                attachedDatosextraNew.add(datosextraNewDatosExtraEntityToAttach);
            }
            datosextraNew = attachedDatosextraNew;
            sospechosoEntity.setDatosextra(datosextraNew);
            List<FotoEntity> attachedFotosNew = new ArrayList<FotoEntity>();
            for (FotoEntity fotosNewFotoEntityToAttach : fotosNew) {
                fotosNewFotoEntityToAttach = em.getReference(fotosNewFotoEntityToAttach.getClass(), fotosNewFotoEntityToAttach.getId());
                attachedFotosNew.add(fotosNewFotoEntityToAttach);
            }
            fotosNew = attachedFotosNew;
            sospechosoEntity.setFotos(fotosNew);
            sospechosoEntity = em.merge(sospechosoEntity);
            for (AntecedentesEntity antecedentesOldAntecedentesEntity : antecedentesOld) {
                if (!antecedentesNew.contains(antecedentesOldAntecedentesEntity)) {
                    antecedentesOldAntecedentesEntity.setSospechoso(null);
                    antecedentesOldAntecedentesEntity = em.merge(antecedentesOldAntecedentesEntity);
                }
            }
            for (AntecedentesEntity antecedentesNewAntecedentesEntity : antecedentesNew) {
                if (!antecedentesOld.contains(antecedentesNewAntecedentesEntity)) {
                    SospechosoEntity oldSospechosoOfAntecedentesNewAntecedentesEntity = antecedentesNewAntecedentesEntity.getSospechoso();
                    antecedentesNewAntecedentesEntity.setSospechoso(sospechosoEntity);
                    antecedentesNewAntecedentesEntity = em.merge(antecedentesNewAntecedentesEntity);
                    if (oldSospechosoOfAntecedentesNewAntecedentesEntity != null && !oldSospechosoOfAntecedentesNewAntecedentesEntity.equals(sospechosoEntity)) {
                        oldSospechosoOfAntecedentesNewAntecedentesEntity.getAntecedentes().remove(antecedentesNewAntecedentesEntity);
                        oldSospechosoOfAntecedentesNewAntecedentesEntity = em.merge(oldSospechosoOfAntecedentesNewAntecedentesEntity);
                    }
                }
            }
            System.out.println("LLEGA 3");
            for (CorreoEntity correosOldCorreoEntity : correosOld) {
                if (!correosNew.contains(correosOldCorreoEntity)) {
                    correosOldCorreoEntity.setSospechoso(null);
                    correosOldCorreoEntity = em.merge(correosOldCorreoEntity);
                }
            }
            for (CorreoEntity correosNewCorreoEntity : correosNew) {
                if (!correosOld.contains(correosNewCorreoEntity)) {
                    SospechosoEntity oldSospechosoOfCorreosNewCorreoEntity = correosNewCorreoEntity.getSospechoso();
                    correosNewCorreoEntity.setSospechoso(sospechosoEntity);
                    correosNewCorreoEntity = em.merge(correosNewCorreoEntity);
                    if (oldSospechosoOfCorreosNewCorreoEntity != null && !oldSospechosoOfCorreosNewCorreoEntity.equals(sospechosoEntity)) {
                        oldSospechosoOfCorreosNewCorreoEntity.getCorreos().remove(correosNewCorreoEntity);
                        oldSospechosoOfCorreosNewCorreoEntity = em.merge(oldSospechosoOfCorreosNewCorreoEntity);
                    }
                }
            }
            for (NumeroTelefonoEntity telefonosOldNumeroTelefonoEntity : telefonosOld) {
                if (!telefonosNew.contains(telefonosOldNumeroTelefonoEntity)) {
                    telefonosOldNumeroTelefonoEntity.setSospechoso(null);
                    telefonosOldNumeroTelefonoEntity = em.merge(telefonosOldNumeroTelefonoEntity);
                }
            }
            for (NumeroTelefonoEntity telefonosNewNumeroTelefonoEntity : telefonosNew) {
                if (!telefonosOld.contains(telefonosNewNumeroTelefonoEntity)) {
                    SospechosoEntity oldSospechosoOfTelefonosNewNumeroTelefonoEntity = telefonosNewNumeroTelefonoEntity.getSospechoso();
                    telefonosNewNumeroTelefonoEntity.setSospechoso(sospechosoEntity);
                    telefonosNewNumeroTelefonoEntity = em.merge(telefonosNewNumeroTelefonoEntity);
                    if (oldSospechosoOfTelefonosNewNumeroTelefonoEntity != null && !oldSospechosoOfTelefonosNewNumeroTelefonoEntity.equals(sospechosoEntity)) {
                        oldSospechosoOfTelefonosNewNumeroTelefonoEntity.getTelefonos().remove(telefonosNewNumeroTelefonoEntity);
                        oldSospechosoOfTelefonosNewNumeroTelefonoEntity = em.merge(oldSospechosoOfTelefonosNewNumeroTelefonoEntity);
                    }
                }
            }
            System.out.println("LLEGA 4");
            for (DireccionEntity direccionesOldDireccionEntity : direccionesOld) {
                if (!direccionesNew.contains(direccionesOldDireccionEntity)) {
                    direccionesOldDireccionEntity.setSospechoso(null);
                    direccionesOldDireccionEntity = em.merge(direccionesOldDireccionEntity);
                }
            }
            for (DireccionEntity direccionesNewDireccionEntity : direccionesNew) {
                if (!direccionesOld.contains(direccionesNewDireccionEntity)) {
                    SospechosoEntity oldSospechosoOfDireccionesNewDireccionEntity = direccionesNewDireccionEntity.getSospechoso();
                    direccionesNewDireccionEntity.setSospechoso(sospechosoEntity);
                    direccionesNewDireccionEntity = em.merge(direccionesNewDireccionEntity);
                    if (oldSospechosoOfDireccionesNewDireccionEntity != null && !oldSospechosoOfDireccionesNewDireccionEntity.equals(sospechosoEntity)) {
                        oldSospechosoOfDireccionesNewDireccionEntity.getDirecciones().remove(direccionesNewDireccionEntity);
                        oldSospechosoOfDireccionesNewDireccionEntity = em.merge(oldSospechosoOfDireccionesNewDireccionEntity);
                    }
                }
            }
            for (MatriculaEntity matriculasOldMatriculaEntity : matriculasOld) {
                if (!matriculasNew.contains(matriculasOldMatriculaEntity)) {
                    matriculasOldMatriculaEntity.setSospechoso(null);
                    matriculasOldMatriculaEntity = em.merge(matriculasOldMatriculaEntity);
                }
            }
            for (MatriculaEntity matriculasNewMatriculaEntity : matriculasNew) {
                if (!matriculasOld.contains(matriculasNewMatriculaEntity)) {
                    SospechosoEntity oldSospechosoOfMatriculasNewMatriculaEntity = matriculasNewMatriculaEntity.getSospechoso();
                    matriculasNewMatriculaEntity.setSospechoso(sospechosoEntity);
                    matriculasNewMatriculaEntity = em.merge(matriculasNewMatriculaEntity);
                    if (oldSospechosoOfMatriculasNewMatriculaEntity != null && !oldSospechosoOfMatriculasNewMatriculaEntity.equals(sospechosoEntity)) {
                        oldSospechosoOfMatriculasNewMatriculaEntity.getMatriculas().remove(matriculasNewMatriculaEntity);
                        oldSospechosoOfMatriculasNewMatriculaEntity = em.merge(oldSospechosoOfMatriculasNewMatriculaEntity);
                    }
                }
            }
            for (DatosExtraEntity datosextraOldDatosExtraEntity : datosextraOld) {
                if (!datosextraNew.contains(datosextraOldDatosExtraEntity)) {
                    datosextraOldDatosExtraEntity.setSospechoso(null);
                    datosextraOldDatosExtraEntity = em.merge(datosextraOldDatosExtraEntity);
                }
            }
            
            System.out.println("LLEGA 5");
            for (DatosExtraEntity datosextraNewDatosExtraEntity : datosextraNew) {
                if (!datosextraOld.contains(datosextraNewDatosExtraEntity)) {
                    SospechosoEntity oldSospechosoOfDatosextraNewDatosExtraEntity = datosextraNewDatosExtraEntity.getSospechoso();
                    datosextraNewDatosExtraEntity.setSospechoso(sospechosoEntity);
                    datosextraNewDatosExtraEntity = em.merge(datosextraNewDatosExtraEntity);
                    if (oldSospechosoOfDatosextraNewDatosExtraEntity != null && !oldSospechosoOfDatosextraNewDatosExtraEntity.equals(sospechosoEntity)) {
                        oldSospechosoOfDatosextraNewDatosExtraEntity.getDatosextra().remove(datosextraNewDatosExtraEntity);
                        oldSospechosoOfDatosextraNewDatosExtraEntity = em.merge(oldSospechosoOfDatosextraNewDatosExtraEntity);
                    }
                }
            }
            for (FotoEntity fotosOldFotoEntity : fotosOld) {
                if (!fotosNew.contains(fotosOldFotoEntity)) {
                    fotosOldFotoEntity.setSospechoso(null);
                    fotosOldFotoEntity = em.merge(fotosOldFotoEntity);
                }
            }
            for (FotoEntity fotosNewFotoEntity : fotosNew) {
                if (!fotosOld.contains(fotosNewFotoEntity)) {
                    SospechosoEntity oldSospechosoOfFotosNewFotoEntity = fotosNewFotoEntity.getSospechoso();
                    fotosNewFotoEntity.setSospechoso(sospechosoEntity);
                    fotosNewFotoEntity = em.merge(fotosNewFotoEntity);
                    if (oldSospechosoOfFotosNewFotoEntity != null && !oldSospechosoOfFotosNewFotoEntity.equals(sospechosoEntity)) {
                        oldSospechosoOfFotosNewFotoEntity.getFotos().remove(fotosNewFotoEntity);
                        oldSospechosoOfFotosNewFotoEntity = em.merge(oldSospechosoOfFotosNewFotoEntity);
                    }
                }
            }
            System.out.println("LLEGA 6");
            em.getTransaction().commit();
            System.out.println("########### "+sospechosoEntity.getId());
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = sospechosoEntity.getId();
                if (findSospechosoEntity(id) == null) {
                    throw new NonexistentEntityException("The sospechosoEntity with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SospechosoEntity sospechosoEntity;
            try {
                sospechosoEntity = em.getReference(SospechosoEntity.class, id);
                sospechosoEntity.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sospechosoEntity with id " + id + " no longer exists.", enfe);
            }
            List<AntecedentesEntity> antecedentes = sospechosoEntity.getAntecedentes();
            for (AntecedentesEntity antecedentesAntecedentesEntity : antecedentes) {
                antecedentesAntecedentesEntity.setSospechoso(null);
                antecedentesAntecedentesEntity = em.merge(antecedentesAntecedentesEntity);
            }
            List<CorreoEntity> correos = sospechosoEntity.getCorreos();
            for (CorreoEntity correosCorreoEntity : correos) {
                correosCorreoEntity.setSospechoso(null);
                correosCorreoEntity = em.merge(correosCorreoEntity);
            }
            List<NumeroTelefonoEntity> telefonos = sospechosoEntity.getTelefonos();
            for (NumeroTelefonoEntity telefonosNumeroTelefonoEntity : telefonos) {
                telefonosNumeroTelefonoEntity.setSospechoso(null);
                telefonosNumeroTelefonoEntity = em.merge(telefonosNumeroTelefonoEntity);
            }
            List<DireccionEntity> direcciones = sospechosoEntity.getDirecciones();
            for (DireccionEntity direccionesDireccionEntity : direcciones) {
                direccionesDireccionEntity.setSospechoso(null);
                direccionesDireccionEntity = em.merge(direccionesDireccionEntity);
            }
            List<MatriculaEntity> matriculas = sospechosoEntity.getMatriculas();
            for (MatriculaEntity matriculasMatriculaEntity : matriculas) {
                matriculasMatriculaEntity.setSospechoso(null);
                matriculasMatriculaEntity = em.merge(matriculasMatriculaEntity);
            }
            List<DatosExtraEntity> datosextra = sospechosoEntity.getDatosextra();
            for (DatosExtraEntity datosextraDatosExtraEntity : datosextra) {
                datosextraDatosExtraEntity.setSospechoso(null);
                datosextraDatosExtraEntity = em.merge(datosextraDatosExtraEntity);
            }
            List<FotoEntity> fotos = sospechosoEntity.getFotos();
            for (FotoEntity fotosFotoEntity : fotos) {
                fotosFotoEntity.setSospechoso(null);
                fotosFotoEntity = em.merge(fotosFotoEntity);
            }
            em.remove(sospechosoEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SospechosoEntity> findAllSospechosoEntityEntities() {
        return findAllSospechosoEntity();
    }

    public List<SospechosoEntity> findSospechosoEntityEntities() {
        return findSospechosoEntityEntities(true, -1, -1);
    }

    public List<SospechosoEntity> findSospechosoEntityEntities(int maxResults, int firstResult) {
        return findSospechosoEntityEntities(false, maxResults, firstResult);
    }

    private List<SospechosoEntity> findSospechosoEntityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SospechosoEntity.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    private List<SospechosoEntity> findAllSospechosoEntity() {
        EntityManager em = getEntityManager();

        List<SospechosoEntity> salida;
        try {
            salida = em.createQuery("FROM SospechosoEntity", SospechosoEntity.class).getResultList();
            for(SospechosoEntity e : salida){
                System.out.println(e);
            }
        } finally {
            em.close();
        }
        return salida;

    }

    public SospechosoEntity findSospechosoEntity(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SospechosoEntity.class, id);
        } finally {
            em.close();
        }
    }

    public int getSospechosoEntityCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SospechosoEntity> rt = cq.from(SospechosoEntity.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
