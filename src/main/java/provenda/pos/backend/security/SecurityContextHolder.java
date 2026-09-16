package provenda.pos.backend.security;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access=AccessLevel.PRIVATE)
public class SecurityContextHolder {
	
public static UserContext getUserContext() {
	UserContext userContext=new UserContext();
	userContext.setUserName("Jumba");
	userContext.setId(0L);
	return userContext;
}

}
