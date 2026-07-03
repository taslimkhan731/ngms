package com.stl.ngms.common.dao;

import java.security.Principal;

import com.stl.ngms.common.UserLocationInfo;

public interface UacService {

	UserLocationInfo getUserLocationInfo(Principal auth);
}
