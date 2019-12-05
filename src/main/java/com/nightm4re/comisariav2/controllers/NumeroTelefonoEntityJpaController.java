/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nightm4re.comisariav2.controllers;

import com.nightm4re.comisariav2.controllers.exceptions.NonexistentEntityException;
import com.nightm4re.comisariav2.modelo.NumeroTelefonoEntity;
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
public class NumeroTelefonoEntityJpaController implements Serializable {

    public NumeroTelefonoEntityJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(NumeroTelefonoEntity numeroTelefonoEntity) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SospechosoEntity sospechoso = numeroTelefonoEntity.getSospechoso();
            if (sospechoso != null) {
                sospechoso = em.getReference(sospechoso.getClass(), sospechoso.getId());
                numeroTelefonoEntity.setSospechoso(sospechoso);
            }
            em.persist(numeroTelefonoEntity);
            if (sospechoso != null) {
                sospechoso.getTelefonos().add(numeroTelefonoEntity);
                sospechoso = em.merge(sospechoso);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(NumeroTelefonoEntity numeroTelefonoEntity) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            NumeroTelefonoEntity persistentNumeroTelefonoEntity = em.find(NumeroTelefonoEntity.class, numeroTelefonoEntity.getId());
            SospechosoEntity sospechosoOld = persistentNumeroTelefonoEntity.getSospechoso();
            SospechosoEntity sospechosoNew = numeroTelefonoEntity.getSospechoso();
            if (sospechosoNew != null) {
                sospechosoNew = em.getReference(sospechosoNew.getClass(), sospechosoNew.getId());
                numeroTelefonoEntity.setSospechoso(sospechosoNew);
            }
            numeroTelefonoEntity = em.merge(numeroTelefonoEntity);
            if (sospechosoOld != null && !sospechosoOld.equals(sospechosoNew)) {
                sospechosoOld.getTelefonos().remove(numeroTelefonoEntity);
                sospechosoOld = em.merge(sospechosoOld);
            }
            if (sospechosoNew != null && !sospechosoNew.equals(sospechosoOld)) {
                sospechosoNew.getTelefonos().add(numeroTelefonoEntity);
                sospechosoNew = em.merge(sospechosoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = numeroTelefonoEntity.getId();
                if (findNumeroTelefonoEntity(id) == null) {
                    throw new NonexistentEntityException("The numeroTelefonoEntity with id " + id + " no longer exists.");
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
            NumeroTelefonoEntity numeroTelefonoEntity;
            try {
                numeroTelefonoEntity = em.getReference(NumeroTelefonoEntity.class, id);
                numeroTelefonoEntity.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The numeroTelefonoEntity with id " + id + " no longer exists.", enfe);
            }
            SospechosoEntity sospechoso = numeroTelefonoEntity.getSospechoso();
            if (sospechoso != null) {
                sospechoso.getTelefonos().remove(numeroTelefonoEntity);
                sospechoso = em.merge(sospechoso);
            }
            em.remove(numeroTelefonoEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<NumeroTelefonoEntity> findNumeroTelefonoEntityEntities() {
        return findNumeroTelefonoEntityEntities(true, -1, -1);
    }

    public List<NumeroTelefonoEntity> findNumeroTelefonoEntityEntities(int maxResults, int firstResult) {
        return findNumeroTelefonoEntityEntities(false, maxResults, firstResult);
    }

    private List<NumeroTelefonoEntity> findNumeroTelefonoEntityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(NumeroTelefonoEntity.class));
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

    public NumeroTelefonoEntity findNumeroTelefonoEntity(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(NumeroTelefonoEntity.class, id);
        } finally {
            em.close();
        }
    }

    public int getNumeroTelefonoEntityCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<NumeroTelefonoEntity> rt = cq.from(NumeroTelefonoEntity.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
