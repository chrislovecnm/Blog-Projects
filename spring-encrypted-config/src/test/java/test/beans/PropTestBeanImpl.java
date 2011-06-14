package test.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("propTest")
public class PropTestBeanImpl implements PropTest {
	
	@Value("#{props['cnm.username']}")
	protected String username;

	@Value("#{props['cnm.foo']}")
	protected String password;

	public String getUsername() {
		return password;
	}
	
	public String getFoo() {
		return username;
	}
}
