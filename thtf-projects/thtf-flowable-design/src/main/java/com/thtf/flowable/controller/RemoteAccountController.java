package com.thtf.flowable.controller;

import org.flowable.idm.engine.impl.persistence.entity.UserEntity;
import org.flowable.idm.engine.impl.persistence.entity.UserEntityImpl;
import org.flowable.ui.common.model.UserRepresentation;
import org.flowable.ui.common.security.SecurityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 模拟登录用户返回
 */
@RestController
@RequestMapping("/app")
public class RemoteAccountController {

	@GetMapping("/rest/account")
	public UserRepresentation getAccount() {
		UserEntity userEntity = new UserEntityImpl();
		userEntity.setId("admin");
		SecurityUtils.assumeUser(userEntity);

		UserRepresentation userRepresentation = new UserRepresentation();
		userRepresentation.setId("admin");
		userRepresentation.setFirstName("管理员");

		List<String> privileges = new ArrayList<>();
		privileges.add("flowable-idm");
		privileges.add("flowable-modeler");
		privileges.add("flowable-admin");
		privileges.add("flowable-task");
		privileges.add("flowable-rest");

		userRepresentation.setPrivileges(privileges);
		return userRepresentation;
	}
}
