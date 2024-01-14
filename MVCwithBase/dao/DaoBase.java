package by.javaguru.MVCwithBase.dao;

import by.javaguru.dao.Dao;
import by.javaguru.entity.BaseEntity;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class DaoBase<K extends Serializable, E extends BaseEntity> implements Dao<K, E> {
    private final Class<E> eClass;
    private final EntityManager entityManager;

    @Override
    public E save(E entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public boolean delete(K id) {
        entityManager.remove(id);
        entityManager.flush();
        return true;
    }

    @Override
    public boolean update(E entity) {
        entityManager.merge(entity);
        return true;
    }

    @Override
    public Optional<E> findById(K id) {
        return Optional.ofNullable(entityManager.find(eClass, id));
    }

    @Override
    public List<E> findAll() {
        var criteria = entityManager.getCriteriaBuilder().createQuery(eClass);
        criteria.from(eClass);
        return entityManager.createQuery(criteria).getResultList();
    }
}
