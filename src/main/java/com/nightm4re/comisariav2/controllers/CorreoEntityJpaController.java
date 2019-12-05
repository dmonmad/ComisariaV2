/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nightm4re.comisariav2.controllers;

import com.nightm4re.comisariav2.controllers.exceptions.NonexistentEntityException;
import com.nightm4re.comisariav2.modelo.CorreoEntity;
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
public class CorreoEntityJpaController implements Serializable {

    public CorreoEntityJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CorreoEntity correoEntity) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SospechosoEntity sospechoso = correoEntity.getSospechoso();
            if (sospechoso != null) {
                sospechoso = em.getReference(sospechoso.getClass(), sospechoso.getId());
                correoEntity.setSospechoso(sospechoso);
            }
            em.persist(correoEntity);
            if (sospechoso != null) {
                sospechoso.getCorreos().add(correoEntity);
                sospechoso = em.merge(sospechoso);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CorreoEntity correoEntity) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CorreoEntity persistentCorreoEntity = em.find(CorreoEntity.class, correoEntity.getId());
            SospechosoEntity sospechosoOld = persistentCorreoEntity.getSospechoso();
            SospechosoEntity sospechosoNew = correoEntity.getSospechoso();
            if (sospechosoNew != null) {
                sospechosoNew = em.getReference(sospechosoNew.getClass(), sospechosoNew.getId());
                correoEntity.setSospechoso(sospechosoNew);
            }
            correoEntity = em.merge(correoEntity);
            if (sospechosoOld != null && !sospechosoOld.equals(sospechosoNew)) {
                sospechosoOld.getCorreos().remove(correoEntity);
                sospechosoOld = em.merge(sospechosoOld);
            }
            if (sospechosoNew != null && !sospechosoNew.equals(sospechosoOld)) {
                sospechosoNew.getCorreos().add(correoEntity);
                sospechosoNew = em.merge(sospechosoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = correoEntity.getId();
                if (findCorreoEntity(id) == null) {
                    throw new NonexistentEntityException("The correoEntity with id " + id + " no longer exists.");
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
            CorreoEntity correoEntity;
            try {
                correoEntity = em.getReference(CorreoEntity.class, id);
                correoEntity.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The correoEntity with id " + id + " no longer exists.", enfe);
            }
            SospechosoEntity sospechoso = correoEntity.getSospechoso();
            if (sospechoso != null) {
                sospechoso.getCorreos().remove(correoEntity);
                sospechoso = em.merge(sospechoso);
            }
            em.remove(correoEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CorreoEntity> findCorreoEntityEntities() {
        return findCorreoEntityEntities(true, -1, -1);
    }

    public List<CorreoEntity> findCorreoEntityEntities(int maxResults, int firstResult) {
        return findCorreoEntityEntities(false, maxResults, firstResult);
    }

    private List<CorreoEntity> findCorreoEntityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CorreoEntity.class));
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

    public CorreoEntity findCorreoEntity(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CorreoEntity.class, id);
        } finally {
            em.close();
        }
    }

    public int getCorreoEntityCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CorreoEntity> rt = cq.from(CorreoEntity.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
