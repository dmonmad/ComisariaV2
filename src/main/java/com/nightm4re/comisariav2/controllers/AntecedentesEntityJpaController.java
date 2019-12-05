/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nightm4re.comisariav2.controllers;

import com.nightm4re.comisariav2.controllers.exceptions.NonexistentEntityException;
import com.nightm4re.comisariav2.modelo.AntecedentesEntity;
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
public class AntecedentesEntityJpaController implements Serializable {

    public AntecedentesEntityJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AntecedentesEntity antecedentesEntity) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SospechosoEntity sospechoso = antecedentesEntity.getSospechoso();
            if (sospechoso != null) {
                sospechoso = em.getReference(sospechoso.getClass(), sospechoso.getId());
                antecedentesEntity.setSospechoso(sospechoso);
            }
            em.persist(antecedentesEntity);
            if (sospechoso != null) {
                sospechoso.getAntecedentes().add(antecedentesEntity);
                sospechoso = em.merge(sospechoso);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AntecedentesEntity antecedentesEntity) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AntecedentesEntity persistentAntecedentesEntity = em.find(AntecedentesEntity.class, antecedentesEntity.getId());
            SospechosoEntity sospechosoOld = persistentAntecedentesEntity.getSospechoso();
            SospechosoEntity sospechosoNew = antecedentesEntity.getSospechoso();
            if (sospechosoNew != null) {
                sospechosoNew = em.getReference(sospechosoNew.getClass(), sospechosoNew.getId());
                antecedentesEntity.setSospechoso(sospechosoNew);
            }
            antecedentesEntity = em.merge(antecedentesEntity);
            if (sospechosoOld != null && !sospechosoOld.equals(sospechosoNew)) {
                sospechosoOld.getAntecedentes().remove(antecedentesEntity);
                sospechosoOld = em.merge(sospechosoOld);
            }
            if (sospechosoNew != null && !sospechosoNew.equals(sospechosoOld)) {
                sospechosoNew.getAntecedentes().add(antecedentesEntity);
                sospechosoNew = em.merge(sospechosoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = antecedentesEntity.getId();
                if (findAntecedentesEntity(id) == null) {
                    throw new NonexistentEntityException("The antecedentesEntity with id " + id + " no longer exists.");
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
            AntecedentesEntity antecedentesEntity;
            try {
                antecedentesEntity = em.getReference(AntecedentesEntity.class, id);
                antecedentesEntity.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The antecedentesEntity with id " + id + " no longer exists.", enfe);
            }
            SospechosoEntity sospechoso = antecedentesEntity.getSospechoso();
            if (sospechoso != null) {
                sospechoso.getAntecedentes().remove(antecedentesEntity);
                sospechoso = em.merge(sospechoso);
            }
            em.remove(antecedentesEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AntecedentesEntity> findAntecedentesEntityEntities() {
        return findAntecedentesEntityEntities(true, -1, -1);
    }

    public List<AntecedentesEntity> findAntecedentesEntityEntities(int maxResults, int firstResult) {
        return findAntecedentesEntityEntities(false, maxResults, firstResult);
    }

    private List<AntecedentesEntity> findAntecedentesEntityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AntecedentesEntity.class));
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

    public AntecedentesEntity findAntecedentesEntity(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AntecedentesEntity.class, id);
        } finally {
            em.close();
        }
    }

    public int getAntecedentesEntityCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AntecedentesEntity> rt = cq.from(AntecedentesEntity.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
