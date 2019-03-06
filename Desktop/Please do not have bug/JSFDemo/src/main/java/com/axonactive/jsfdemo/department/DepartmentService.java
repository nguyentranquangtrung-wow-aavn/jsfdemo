package com.axonactive.jsfdemo.department;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.axonactive.jsfdemo.persistence.AbstractCRUDBean;
import com.axonactive.jsfdemo.persistence.PersistenceService;

@Stateless
public class DepartmentService extends AbstractCRUDBean<DepartmentEntity> {
	@Inject
	private PersistenceService<DepartmentEntity> persistenceService;

	@Override
	protected PersistenceService<DepartmentEntity> getPersistenceService() {
		return this.persistenceService;
	}

	public DepartmentEntity toEntity(DepartmentBOM bom) {
		if (bom != null) {
			return new DepartmentEntity(bom.getId() != null ? bom.getId() : 0, bom.getName());
		}
		return null;
	}

	public DepartmentBOM toBom(DepartmentEntity entity) {
		if (entity != null) {
			return new DepartmentBOM(entity.getId(), entity.getName());
		}
		return null;
	}

	public List<DepartmentEntity> toEntities(List<DepartmentBOM> boms) {
		if (boms == null) {
			return Collections.emptyList();
		}
		List<DepartmentEntity> entities = new ArrayList<>();
		boms.stream().map(each -> toEntity(each)).filter(Objects::nonNull).forEach(entity -> entities.add(entity));
		return entities;
	}

	public List<DepartmentBOM> toBoms(List<DepartmentEntity> entities) {
		if (entities == null) {
			return Collections.emptyList();
		}
		List<DepartmentBOM> boms = new ArrayList<>();
		entities.stream().map(each -> toBom(each)).filter(Objects::nonNull).forEach(bom -> boms.add(bom));
		return boms;
	}

	@SuppressWarnings("unchecked")
	public List<DepartmentEntity> readAll() {
		return persistenceService.createQuery("FROM DepartmentEntity order by name ASC").getResultList();
	}

//	public DepartmentEntity getDepartmentByID(int id) {
//		Query query = persistenceService.createQuery("from DepartmentEntity where id = :id");
//		query.setParameter("id", id);
//		List<DepartmentEntity> deptEntityList = query.getResultList();
//		return deptEntityList.get(0);
//	}

}
