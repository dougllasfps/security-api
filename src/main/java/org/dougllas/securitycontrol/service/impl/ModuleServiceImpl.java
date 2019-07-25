package org.dougllas.securitycontrol.service.impl;

import org.dougllas.securitycontrol.model.entity.Module;
import org.dougllas.securitycontrol.model.repository.ModuleRepository;
import org.dougllas.securitycontrol.service.ModuleService;
import org.dougllas.securitycontrol.service.generic.EntityServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ModuleServiceImpl extends EntityServiceImpl<Module, Long, ModuleRepository> implements ModuleService {

	@Override
	public Long countModules() {
		return getRepository().count();
	}

}