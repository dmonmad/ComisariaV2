/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nightm4re.comisariav2.controllers;

import com.nightm4re.comisariav2.controllers.exceptions.NonexistentEntityException;
import com.nightm4re.comisariav2.modelo.DireccionEntity;
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
public class DireccionEntityJpaController implements Serializable {

    public DireccionEntityJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DireccionEntity direccionEntity) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SospechosoEntity sospechoso = direccionEntity.getSospechoso();
            if (sospechoso != null) {
                sospechoso = em.getReference(sospechoso.getClass(), sospechoso.getId());
                direccionEntity.setSospechoso(sospechoso);
            }
            em.persist(direccionEntity);
            if (sospechoso != null) {
                sospechoso.getDirecciones().add(direccionEntity);
                sospechoso = em.merge(sospechoso);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DireccionEntity direccionEntity) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DireccionEntity persistentDireccionEntity = em.find(DireccionEntity.class, direccionEntity.getId());
            SospechosoEntity sospechosoOld = persistentDireccionEntity.getSospechoso();
            SospechosoEntity sospechosoNew = direccionEntity.getSospechoso();
            if (sospechosoNew != null) {
                sospechosoNew = em.getReference(sospechosoNew.getClass(), sospechosoNew.getId());
                direccionEntity.setSospechoso(sospechosoNew);
            }
            direccionEntity = em.merge(direccionEntity);
            if (sospechosoOld != null && !sospechosoOld.equals(sospechosoNew)) {
                sospechosoOld.getDirecciones().remove(direccionEntity);
                sospechosoOld = em.merge(sospechosoOld);
            }
            if (sospechosoNew != null && !sospechosoNew.equals(sospechosoOld)) {
                sospechosoNew.getDirecciones().add(direccionEntity);
                sospechosoNew = em.merge(sospechosoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = direccionEntity.getId();
                if (findDireccionEntity(id) == null) {
                    throw new NonexistentEntityException("The direccionEntity with id " + id + " no longer exists.");
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
            DireccionEntity direccionEntity;
            try {
                direccionEntity = em.getReference(DireccionEntity.class, id);
                direccionEntity.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The direccionEntity with id " + id + " no longer exists.", enfe);
            }
            SospechosoEntity sospechoso = direccionEntity.getSospechoso();
            if (sospechoso != null) {
                sospechoso.getDirecciones().remove(direccionEntity);
                sospechoso = em.merge(sospechoso);
            }
            em.remove(direccionEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DireccionEntity> findDireccionEntityEntities() {
        return findDireccionEntityEntities(true, -1, -1);
    }

    public List<DireccionEntity> findDireccionEntityEntities(int maxResults, int firstResult) {
        return findDireccionEntityEntities(false, maxResults, firstResult);
    }

    private List<DireccionEntity> findDireccionEntityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DireccionEntity.class));
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

    public DireccionEntity findDireccionEntity(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DireccionEntity.class, id);
        } finally {
            em.close();
        }
    }

    public int getDireccionEntityCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DireccionEntity> rt = cq.from(DireccionEntity.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
