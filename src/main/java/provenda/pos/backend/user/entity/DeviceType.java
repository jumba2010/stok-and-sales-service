package provenda.pos.backend.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DeviceType {
MOBILE("MOBILE"),COMPUTER("COMPUTER");

private String type;
}
