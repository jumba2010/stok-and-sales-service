package provenda.pos.backend.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum InventoryStatus {
    PENDING(true, 0), FINISHED(false, 1);

    private final boolean pending;
    private final int code;
}
