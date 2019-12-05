/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nightm4re.comisariav2.controllers;

import com.nightm4re.comisariav2.controllers.exceptions.NonexistentEntityException;
import com.nightm4re.comisariav2.modelo.DatosExtraEntity;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.nightm4re.comisariav2.modelo.SospechosoEntity;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Nightm4re
 */
public class DatosExtraEntityJpaController implements Serializable {

    public DatosExtraEntityJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DatosExtraEntity datosExtraEntity) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SospechosoEntity sospechoso = datosExtraEntity.getSospechoso();
            if (sospechoso != null) {
                sospechoso = em.getReference(sospechoso.getClass(), sospechoso.getId());
                datosExtraEntity.setSospechoso(sospechoso);
            }
            em.persist(datosExtraEntity);
            if (sospechoso != null) {
                sospechoso.getDatosextra().add(datosExtraEntity);
                sospechoso = em.merge(sospechoso);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DatosExtraEntity datosExtraEntity) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DatosExtraEntity persistentDatosExtraEntity = em.find(DatosExtraEntity.class, datosExtraEntity.getId());
            SospechosoEntity sospechosoOld = persistentDatosExtraEntity.getSospechoso();
            SospechosoEntity sospechosoNew = datosExtraEntity.getSospechoso();
            if (sospechosoNew != null) {
                sospechosoNew = em.getReference(sospechosoNew.getClass(), sospechosoNew.getId());
                datosExtraEntity.setSospechoso(sospechosoNew);
            }
            datosExtraEntity = em.merge(datosExtraEntity);
            if (sospechosoOld != null && !sospechosoOld.equals(sospechosoNew)) {
                sospechosoOld.getDatosextra().remove(datosExtraEntity);
                sospechosoOld = em.merge(sospechosoOld);
            }
            if (sospechosoNew != null && !sospechosoNew.equals(sospechosoOld)) {
                sospechosoNew.getDatosextra().add(datosExtraEntity);
                sospechosoNew = em.merge(sospechosoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = datosExtraEntity.getId();
                if (findDatosExtraEntity(id) == null) {
                    throw new NonexistentEntityException("The datosExtraEntity with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DatosExtraEntity datosExtraEntity;
            try {
                datosExtraEntity = em.getReference(DatosExtraEntity.class, id);
                datosExtraEntity.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The datosExtraEntity with id " + id + " no longer exists.", enfe);
            }
            SospechosoEntity sospechoso = datosExtraEntity.getSospechoso();
            if (sospechoso != null) {
                sospechoso.getDatosextra().remove(datosExtraEntity);
                sospechoso = em.merge(sospechoso);
            }
            em.remove(datosExtraEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DatosExtraEntity> findDatosExtraEntityEntities() {
        return findDatosExtraEntityEntities(true, -1, -1);
    }

    public List<DatosExtraEntity> findDatosExtraEntityEntities(int maxResults, int firstResult) {
        return findDatosExtraEntityEntities(false, maxResults, firstResult);
    }

    private List<DatosExtraEntity> findDatosExtraEntityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DatosExtraEntity.class));
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

    public DatosExtraEntity findDatosExtraEntity(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DatosExtraEntity.class, id);
        } finally {
            em.close();
        }
    }

    public int getDatosExtraEntityCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DatosExtraEntity> rt = cq.from(DatosExtraEntity.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
