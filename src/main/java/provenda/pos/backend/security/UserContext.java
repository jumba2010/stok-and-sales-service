package provenda.pos.backend.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserContext {

private String userName;

private String applicationName;

private String ipAddress;

private Long id;

private String device;

private Long sucursalId;

    public UserContext(String userId) {
    }

    public static UserContext getDefaultContext(){
    return new UserContext("admin","SGS","127.0.0.1",1L, "Localhost",1L);
}

}
