package net.miiingle.user.api.presentation.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenericError {

    String code;
    String description;
}
