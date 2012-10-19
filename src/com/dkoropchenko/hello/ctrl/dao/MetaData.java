package com.dkoropchenko.hello.ctrl.dao;

import com.dkoropchenko.hello.model.HelloType;

public interface MetaData {

	HelloType getType(); // get root type
	HelloType getType(int id)
}
