/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nightm4re.comisariav2.controllers;

import com.nightm4re.comisariav2.controllers.exceptions.NonexistentEntityException;
import com.nightm4re.comisariav2.modelo.FotoEntity;
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
public class FotoEntityJpaController implements Serializable {

    public FotoEntityJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FotoEntity fotoEntity) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SospechosoEntity sospechoso = fotoEntity.getSospechoso();
            if (sospechoso != null) {
                sospechoso = em.getReference(sospechoso.getClass(), sospechoso.getId());
                fotoEntity.setSospechoso(sospechoso);
            }
            em.persist(fotoEntity);
            if (sospechoso != null) {
                sospechoso.getFotos().add(fotoEntity);
                sospechoso = em.merge(sospechoso);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FotoEntity fotoEntity) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FotoEntity persistentFotoEntity = em.find(FotoEntity.class, fotoEntity.getId());
            SospechosoEntity sospechosoOld = persistentFotoEntity.getSospechoso();
            SospechosoEntity sospechosoNew = fotoEntity.getSospechoso();
            if (sospechosoNew != null) {
                sospechosoNew = em.getReference(sospechosoNew.getClass(), sospechosoNew.getId());
                fotoEntity.setSospechoso(sospechosoNew);
            }
            fotoEntity = em.merge(fotoEntity);
            if (sospechosoOld != null && !sospechosoOld.equals(sospechosoNew)) {
                sospechosoOld.getFotos().remove(fotoEntity);
                sospechosoOld = em.merge(sospechosoOld);
            }
            if (sospechosoNew != null && !sospechosoNew.equals(sospechosoOld)) {
                sospechosoNew.getFotos().add(fotoEntity);
                sospechosoNew = em.merge(sospechosoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = fotoEntity.getId();
                if (findFotoEntity(id) == null) {
                    throw new NonexistentEntityException("The fotoEntity with id " + id + " no longer exists.");
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
            FotoEntity fotoEntity;
            try {
                fotoEntity = em.getReference(FotoEntity.class, id);
                fotoEntity.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The fotoEntity with id " + id + " no longer exists.", enfe);
            }
            SospechosoEntity sospechoso = fotoEntity.getSospechoso();
            if (sospechoso != null) {
                sospechoso.getFotos().remove(fotoEntity);
                sospechoso = em.merge(sospechoso);
            }
            em.remove(fotoEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FotoEntity> findFotoEntityEntities() {
        return findFotoEntityEntities(true, -1, -1);
    }

    public List<FotoEntity> findFotoEntityEntities(int maxResults, int firstResult) {
        return findFotoEntityEntities(false, maxResults, firstResult);
    }

    private List<FotoEntity> findFotoEntityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FotoEntity.class));
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

    public FotoEntity findFotoEntity(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FotoEntity.class, id);
        } finally {
            em.close();
        }
    }

    public int getFotoEntityCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FotoEntity> rt = cq.from(FotoEntity.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
