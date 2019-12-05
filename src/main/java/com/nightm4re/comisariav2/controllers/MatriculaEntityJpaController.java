/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nightm4re.comisariav2.controllers;

import com.nightm4re.comisariav2.controllers.exceptions.NonexistentEntityException;
import com.nightm4re.comisariav2.modelo.MatriculaEntity;
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
public class MatriculaEntityJpaController implements Serializable {

    public MatriculaEntityJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MatriculaEntity matriculaEntity) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SospechosoEntity sospechoso = matriculaEntity.getSospechoso();
            if (sospechoso != null) {
                sospechoso = em.getReference(sospechoso.getClass(), sospechoso.getId());
                matriculaEntity.setSospechoso(sospechoso);
            }
            em.persist(matriculaEntity);
            if (sospechoso != null) {
                sospechoso.getMatriculas().add(matriculaEntity);
                sospechoso = em.merge(sospechoso);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MatriculaEntity matriculaEntity) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MatriculaEntity persistentMatriculaEntity = em.find(MatriculaEntity.class, matriculaEntity.getId());
            SospechosoEntity sospechosoOld = persistentMatriculaEntity.getSospechoso();
            SospechosoEntity sospechosoNew = matriculaEntity.getSospechoso();
            if (sospechosoNew != null) {
                sospechosoNew = em.getReference(sospechosoNew.getClass(), sospechosoNew.getId());
                matriculaEntity.setSospechoso(sospechosoNew);
            }
            matriculaEntity = em.merge(matriculaEntity);
            if (sospechosoOld != null && !sospechosoOld.equals(sospechosoNew)) {
                sospechosoOld.getMatriculas().remove(matriculaEntity);
                sospechosoOld = em.merge(sospechosoOld);
            }
            if (sospechosoNew != null && !sospechosoNew.equals(sospechosoOld)) {
                sospechosoNew.getMatriculas().add(matriculaEntity);
                sospechosoNew = em.merge(sospechosoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = matriculaEntity.getId();
                if (findMatriculaEntity(id) == null) {
                    throw new NonexistentEntityException("The matriculaEntity with id " + id + " no longer exists.");
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
            MatriculaEntity matriculaEntity;
            try {
                matriculaEntity = em.getReference(MatriculaEntity.class, id);
                matriculaEntity.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The matriculaEntity with id " + id + " no longer exists.", enfe);
            }
            SospechosoEntity sospechoso = matriculaEntity.getSospechoso();
            if (sospechoso != null) {
                sospechoso.getMatriculas().remove(matriculaEntity);
                sospechoso = em.merge(sospechoso);
            }
            em.remove(matriculaEntity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MatriculaEntity> findMatriculaEntityEntities() {
        return findMatriculaEntityEntities(true, -1, -1);
    }

    public List<MatriculaEntity> findMatriculaEntityEntities(int maxResults, int firstResult) {
        return findMatriculaEntityEntities(false, maxResults, firstResult);
    }

    private List<MatriculaEntity> findMatriculaEntityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MatriculaEntity.class));
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

    public MatriculaEntity findMatriculaEntity(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MatriculaEntity.class, id);
        } finally {
            em.close();
        }
    }

    public int getMatriculaEntityCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MatriculaEntity> rt = cq.from(MatriculaEntity.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
