package org.dougllas.securitycontrol.service;

import org.dougllas.securitycontrol.model.entity.Module;
import org.dougllas.securitycontrol.service.generic.EntityService;

public interface ModuleService extends EntityService<Module, Long> {

	Long countModules();

}